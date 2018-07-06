<%@page pageEncoding="utf-8"%>
<%@taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jspf/head.jsp"></jsp:include>
</head>
<body class="layout-vbox">
    <nav class="nav-bar">
        <ol class="breadcrumb">
            <li>
                <a href="javascript:window.top.dashboard()"
                class="fa fa-dashboard"
                >首页 </a>
            </li>
            <li>系统管理</li>
            <li>
                <b>角色列表</b>
            </li>
        </ol>
    </nav>
    <section class="action-bar layout-justify">
        <aside>
            <a
                href="jsp/system/rolemanage/add.jsp"
                class="btn">新增</a>
        </aside>
        <aside>
            <form class="inline"
                action="system/role/list.do"
                method="post"
                class="navbar-form navbar-right">
                <span class="input-group">
                    <input
                        type="text"
                        name="key"
                        value="${param.key}"
                        class="form-control"
                        placeholder="Search">
                    <button
                        type="submit"
                        class="btn btn-default">Go</button>
                </span>

            </form>
        </aside>
    </section>
    <section class="layout-table">
            <header>
                <table class="data">
                    <thead>
                     <tr>
                        <th>#</th>
                        <th>操作</th>
                        <th>代号</th>
                        <th>名称</th>
                        <th>备注</th>
                    </tr>
                    <colgroup>
                        <col width="50" align="right">
                        <col width="100">
                        <col width="200">
                        <col width="150">
                        <col>
                    </colgroup>
                    </thead>
                </table>
            </header>
            <main>
            <table class="data">
                <tbody >
                    <c:if test="${requestScope.result.found}">
                        <c:forEach
                            var="r"
                            items="${requestScope.result.rows}"
                            varStatus="vs">
                            <tr>
                                <td>${vs.count}</td>
                                <td>
                                    <a href="jsp/system/rolemanage/check.jsp?${r.id}">查看</a>
                                </td>
                                <td>${r.code}</td>
                                <td>${r.name}</td>
                                <td>${r.remark}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${requestScope.result.notFound}">
                        <tr>
                            <td colspan="99">很抱歉，未找到相关数据！</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
           </main>
            
        <footer>
                 共
            <b>${requestScope.result.totalRows}</b>
                条数据
        </footer>
    </section>
</body>
</html>