<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
 <%@include file="/WEB-INF/jspf/head.jsp"%>
 <%@include file="/WEB-INF/jspf/head-fancytree.jsp"%>
 <script src="js/component/module-table-tree.js"></script>
</head>
<body class="layout-vbox">
    <nav class="nav-bar">
        <a
            href="javascript:history.back()"
            class="fa fa-back"></a>
        <ol class="breadcrumb">
            <li>
                <a
                    href="javascript:window.top.dashboard()"
                    class="fa fa-dashboard">首页 </a>
            </li>
            <li>系统管理</li>
			<li>角色【<b id="roleNameEL">加载中</b>】功能分配</li>
		</ol>
	</nav>
    <section class="action-bar layout-row">
         <button id="confirmGrantBtn" type="button" class="btn btn-default btn-box-tool">
                            确定
         </button>
    </section>
	<section class="pad" >
    <table
            class="data"
           id="module-table-tree">
            <colgroup>
                <col width="50px"></col>
                <col width="400px"></col>
                <col width="*"></col>
            </colgroup>
            <thead>
                <tr>
                    <th>#</th>
                    <th>名称</th>
                    <th>备注</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td style="text-align: left;"></td>
                    <td style="text-align: left;"></td>
                    <td style="text-align: left;"></td>
                </tr>
            </tbody>
        </table>
	</section>
	<script>
		//当前选中角色Id
		var id=location.search.slice(1);
		
		$(function(){
			//加载角色信息
			loadRoleModel(id);
			//加载功能模块树
			loadModuleTableTree("#module-table-tree",{
			    renderColumns : function(evt, ctx) {
                    var node = ctx.node;
                    var model = node.data;
                    var cells = node.tr.getElementsByTagName("td");
                    cells[0].textContent = node.getIndexHier();
                    //cells[1]为名称
                    cells[2].textContent = $$(model.remark);
                }
			}).then(function(){
			    loadGrantedModules(id);
			});
			
		});
		
		//加载角色
		var loadRoleModel=function(){
			var url="api/system/role/get?id="+id;
			return $.getJSON(url,function(resp){
				if(resp.code==="ok"){
					var r=resp.data;
					paintRole(r);
				}else{
					alert(resp.message);
				}
			});
		};
		var paintRole=function(r){
		    $("#roleNameEL").text(r.name);
		}
	</script>
	<script>
		var loadGrantedModules=function(rid){
			//加载角色下功能模块
			var url="api/system/role/module/list?id="+rid;
			$.getJSON(url,function(resp){
				if(resp.code==="ok"){
					var modules=resp.data;
					var moduleIdArray=modules.map(function(m){
					    return m.id;
					})
					var tree=$("#module-table-tree").fancytree("getTree");
					tree.visit(function(node){
						//如果是父节点，则不选择
						if(node.hasChildren()){
							node.setSelected(false);
						}else{
							node.setSelected(moduleIdArray.indexOf(node.key)!=-1);
						}
					})
				}else{
					toast(resp.message);
				}
			})
		}
	</script>
	<script>
		$("#confirmGrantBtn").click(function(evt){
			var tree=$("#module-table-tree").fancytree("getTree");
			var selectedNodes=tree.getSelectedNodes();
			var selectedNodeIdStr=selectedNodes.map(function(node){
				return node.key;
			}).join(",");
			var url="api/system/role/module/grant";
			$.post(url,{
				modules:selectedNodeIdStr,
				roleId:id
				},function(resp){
				if(resp.code==="ok"){
					toast("分配权限成功！");
				}else{
					toast("分配权限失败，原因是:"+resp.message);
				}
			});
			
		});
	</script>
</body>

</html>