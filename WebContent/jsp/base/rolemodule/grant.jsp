<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
 <%@include file="/WEB-INF/jspf/head.jsp"%>
 <%@include file="/WEB-INF/jspf/head-fancytree.jsp"%>
</head>

<body>
	<header class="content-header">
		<h1>角色权限分配</h1>
		<ol class="breadcrumb">
			<li>
			<a href="javascript:window.top.dashboard()"><i class="fa fa-dashboard"></i>首页</a>
			</li>
			<li><a>系统管理</a></li>
			<li><a>角色权限分配</a></li>
		</ol>
		<hr>
	</header>
	<main class="container-fluid">
	<div class="row">
		<aside class="col-xs-4 role-box">
			<div class="box box-default">
				<header class="box-header with-border">
					<h3 class="box-title">系统角色列表</h3>
				</header>
				<section class="box-body">
					<ul id="role-box" class="nav nav-pills nav-stacked">
		                <li>加载中...</li>
	              	</ul>
				</section>
			</div>
		</aside>
		<aside class="col-xs-8  module-box">
			<div class="box box-default">
				<header class="box-header with-border">
					<h3 class="box-title">系统功能模块树</h3>
					
					<div class="box-tools pull-right">
		                <button id="resetBtn" type="button" class="btn btn-default btn-box-tool">
		                	重置
		                </button>
		                <button id="confirmGrantBtn" type="button" class="btn btn-default btn-box-tool">
		                	确定
		                </button>
             		 </div>
				</header>
				<section id="module-tree-box" class="box-body">
					 
				</section>
			</div>
		</aside>
	</div>
	</main>
	<script>
		//当前选中角色Id
		var currentSelectedRoleId;
		
		$(function(){
			//加载角色列表
			loadRolesList();
			//加载功能模块树
			loadModuleTree();
		})
			//加载功能模块
		var loadModuleTree=function(){
			var url="loadallmodules.ajax";
			$.getJSON(url,function(resp){
				if(resp.code==="ok"){
					$("#module-tree-box").fancytree({
						 source:resp.data,
						 checkbox: true,
						 selectMode: 3,
						 minExpandLevel:2,
						 icon: false,
						 keyboard: false
					});
				}else{
					alert(resp.message);
				}
			});
		};
		//加载角色列表
		var loadRolesList=function(){
			var url="loadallrole.ajax";
			$.getJSON(url,function(resp){
				if(resp.code==="ok"){
					var roles=resp.data;
					$("#role-box").empty();
					roles.forEach(function(r){
						$("<li/>").append($("<a/>",{
							text:r.name,
							href:"javascript:handleRoleItemClick('"+r.id+"')"
						})).appendTo($("#role-box"));
					});
				}else{
					alert(resp.message);
				}
			});
		}
	</script>
	<script type="text/javascript">
		var handleRoleItemClick=function(rid){
			currentSelectedRoleId=rid;
			//加载角色下功能模块
			var url="loadmodulesofrole.ajax?rid="+rid;
			$.getJSON(url,function(resp){
				if(resp.code==="ok"){
					var modules=resp.data;
					var tree=$("#module-tree-box").fancytree("getTree");
					tree.visit(function(node){
						//如果是父节点，则不选择
						if(node.hasChildren()){
							node.setSelected(false);
						}else{
							node.setSelected(modules.indexOf(node.key)!=-1);
						}
					})
				}else{
					alert(resp.message);
				}
			})
		}
	</script>
	<script type="text/javascript">
		$("#confirmGrantBtn").click(function(evt){
			if(!currentSelectedRoleId)return;
			
			var tree=$("#module-tree-box").fancytree("getTree");
			var selectedNodes=tree.getSelectedNodes();
			var selectedNodeIdStr=selectedNodes.map(function(node){
				return node.key;
			}).join(",");
			var url="grantmoudels2role.ajax";
			
			$.post(url,{
				modules:selectedNodeIdStr,
				roleId:currentSelectedRoleId
				},function(resp){
				if(resp.code==="ok"){
					alert("分配权限成功！");
				}else{
					alert("分配权限失败，原因是:"+resp.message);
				}
			});
			
		});
		
		$("#resetBtn").click(function(evt){
			handleRoleItemClick(currentSelectedRoleId);
		});
	</script>
</body>

</html>