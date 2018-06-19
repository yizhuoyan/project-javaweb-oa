<%@page pageEncoding="utf-8"%>
<%@taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<%@include file="/WEB-INF/jspf/head.jsp"%>
<%@include file="/WEB-INF/jspf/head-fancytree.jsp"%>
<script src="js/component/department-table-tree.js"></script>
<style>
</style>
</head>
<body class="layout-vbox">
    <nav class="nav-bar">
        <ol class="breadcrumb">
            <li>
                <a
                    href="javascript:window.top.dashboard()"
                    class="fa fa-dashboard">首页 </a>
            </li>
            <li>组织管理</li>
            <li>
                <b>部门管理</b>
            </li>
        </ol>
    </nav>
    <section class="action-bar layout-row">
        <a
            href="jsp/organization/deptmanage/add.jsp"
            class="btn">新增</a>
    </section>

    <table
        class="data"
        id="deptTableTree">
        <colgroup>
            <col width="30px"></col>
            <col width="180px"></col>
            <col width="200px"></col>
            <col width="50px"></col>
            <col width="100px"></col>
            <col width="*"></col>
        </colgroup>
        <thead>
            <tr>
                <th>#</th>
                <th>(代号)名称</th>
                <th>操作</th>
                <th>人数</th>
                <th>部门经理</th>
                <th>备注</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td></td>
                <td style="text-align: left;"></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </tbody>
    </table>
    <script>
                    $(function() {
                        loadTree();
                    });
                </script>
    <script>
                    var tree;
                    //功能树标签容器
                    var $tableTreeEL = $("#deptTableTree");
                    var loadTree = function() {
                        window.loadDepartmentTableTree($tableTreeEL,{
                            renderColumns : function(evt, ctx) {
                                var node = ctx.node;
                                var model = node.data;
                                var cells = node.tr.getElementsByTagName("td");
                                cells[0].textContent = node.getIndexHier();
                                //cells[1]为名称
                                var modUrl = "jsp/organization/deptmanage/check.jsp?" + model.id;
                                var addUrl = "jsp/organization/deptmanage/add.jsp?pid=" + model.id;
                                cells[2].innerHTML = '<a href="'+modUrl+'" >查看</a>' + '　　<a href="'+addUrl+'" >添加子部门</a>';

                                cells[3].textContent = model.members;
                                cells[4].textContent = $$("manage.name", model);
                                cells[5].textContent = $$(model.remark);
                            }
                        });
                    };
                </script>
</body>

</html>