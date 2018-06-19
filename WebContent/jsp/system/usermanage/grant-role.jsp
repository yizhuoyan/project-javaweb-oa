<%@page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
<%@include file="/WEB-INF/jspf/head.jsp"%>
<%@include file="/WEB-INF/jspf/head-fancytree.jsp"%>
<script src="js/component/role-table-tree.js"></script>
</head>

<body class="layout-vbox">
    <nav class="nav-bar">
        <a
            href="javascript:history.back()"
            class="fa fa-back"></a>
        <ol class="breadcrumb">
            <li>
                <a
                    class="fa fa-dashboard"
                    href="javascript:window.top.dashboard()"> 首页 </a>
            </li>
            <li>系统管理</li>
            <li>
                <a href="system/user/manage.do">系统用户管理</a>
            </li>
            <li>
                <b>【<b id="userNameEL">加载中</b>】角色分配</b>
            </li>
        </ol>
        </nav>
        <section class="action-bar layout-row">
                <button
                id="confirmGrantBtn"
                type="button"
                class="btn">确定分配</button>
        </section>
      <section class="layout-box-grow">
        <table
            class="data"
            id="roleTableTreeEL">
            <colgroup>
                <col width="50px"></col>
                <col width="300px" align="left"></col>
                <col width="200px"></col>
                <col width="*"></col>
                <col width="*"></col>
            </colgroup>
            <thead>
                <tr>
                    <th>#</th>
                    <th>名称</th>
                    <th>代号</th>
                    <th>备注</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </tbody>
        </table>
    </section>
        <script>
                                    var id = window.location.search.substr(1);
                                    $(function() {
                                        //0加载用户信息
                                        loadUser(id);
                                        //1加载角色列表
                                        loadAllRoles().then(function(s) {
                                            //2加载用户拥有角色
                                            loadGrantUserRoles(id);
                                        });
                                    });
                                </script>
        <script>
                                    var loadUser = function(id) {
                                        var url = "api/system/user/get?type=simple&id=" + id;
                                        $.getJSON(url, function(resp) {
                                            if (resp.code === "ok") {
                                                var user = resp.data;
                                                paintUser(user);
                                            }
                                        });
                                    };
                                    var paintUser = function(u) {
                                        $("#userNameEL").text(u.name);
                                    }
                                    var loadAllRoles = function() {
                                        return new Promise(function(ok){
                                            window.loadRoleTableTree("#roleTableTreeEL",{
                                                checkbox :true,
                                                renderColumns : function(evt, ctx) {
                                                    var node = ctx.node;
                                                    var model = node.data;
                                                    var cells = node.tr.getElementsByTagName("td");
                                                    cells[0].textContent = node.getIndexHier();
                                                    //cells[1]为名称
                                                    cells[2].textContent=model.code 
                                                    cells[3].textContent = $$(model.remark);
                                                },
                                                init:function(evt, ctx){
                                                    ok();
                                                }
                                            });
                                        });
                                    };
                                   
                                    /**
                                     *显示已选则角色
                                     */
                                    var loadGrantUserRoles = function(uid) {
                                        var url = "api/system/user/role/list?id=" + uid;
                                        $.getJSON(url, function(resp) {
                                            if (resp.code === "ok") {
                                                var roles = resp.data;
                                                selectRole(roles.map(function(r){
                                                    return r.id;
                                                }));
                                            }
                                        });
                                    }
                                    /**
                                     *选择角色
                                     */
                                    var selectRole = function(needSelecteds) {
                                        var tree=$("#roleTableTreeEL").fancytree("getTree");
                                        tree.visit(function(node){
                                            //如果是父节点，则不选择
                                            if(node.hasChildren()){
                                                node.setSelected(false);
                                            }else{
                                                node.setSelected(needSelecteds.indexOf(node.key)!==-1);
                                            }
                                        })
                                    }

                                    $("#confirmGrantBtn").click(function() {
                                        this.disabled = true;
                                        var tree=$("#roleTableTreeEL").fancytree("getTree");
                                        var selectedNodes=tree.getSelectedNodes();
                                        var selectedNodeIdStr=selectedNodes.map(function(node){
                                            return node.key;
                                        }).join(",");
                                        var url = "api/system/user/role/grant";
                                        $.post(url, {
                                            userId : id,
                                            roles : selectedNodeIdStr
                                        }, function(resp) {
                                            if (resp.code === "ok") {
                                                toast("分配成功！");
                                            } else {
                                                toast(resp.message);
                                            }
                                        }).always(function() {
                                            document.querySelector("#confirmGrantBtn").disabled = false;
                                        });
                                    });
                                </script>
</body>

</html>