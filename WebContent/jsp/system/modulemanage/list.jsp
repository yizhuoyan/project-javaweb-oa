<%@page pageEncoding="utf-8"%>
<%@taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jspf/head.jsp"%>
<%@include file="/WEB-INF/jspf/head-fancytree.jsp"%>
<script src="js/component/module-table-tree.js"></script>
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
            <li>系统管理</li>
            <li>
                <b>功能列表</b>
            </li>
        </ol>
    </nav>
    <section class="action-bar layout-row">
        <a
            class="btn btn-primary"
            href="jsp/system/modulemanage/add.jsp">新增</a>
    </section>

    <section class="pad-h layout-box-grow">
        <table
            class="data"
            id="functionalityTableTree">
            <colgroup>
                <col width="50px"></col>
                <col width="300px"></col>
                <col width="200px"></col>
                <col width="*"></col>
                <col width="*"></col>
            </colgroup>
            <thead>
                <tr>
                    <th>#</th>
                    <th>名称</th>
                    <th>操作</th>
                    <th>URL</th>
                    <th>备注</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td></td>
                    <td style="text-align:left;"></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </tbody>
        </table>
    </section>
    <script>
                    //功能树标签容器
                    window.loadModuleTableTree("#functionalityTableTree",{
                        checkbox :false,
                        renderColumns : function(evt, ctx) {
                            var node = ctx.node;
                            var model = node.data;
                            var cells = node.tr.getElementsByTagName("td");
                            cells[0].textContent = node.getIndexHier();
                            //cells[1]为名称
                            var checkUrl = "jsp/system/modulemanage/check.jsp?" + model.id;
                            cells[2].innerHTML = '<a href="'+checkUrl+'" >查看</a>';
                            var addChildUrl = "jsp/system/modulemanage/add.jsp?pid=" + model.id;
                            cells[2].innerHTML += '&nbsp;&nbsp;<a href="'+addChildUrl+'" >添加子功能</a>';
                            cells[3].textContent = $$(model.url);
                        }
                    });

    </script>
</body>

</html>