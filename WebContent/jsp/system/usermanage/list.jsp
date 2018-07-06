<%@page pageEncoding="utf-8"%>
<%@taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib
    prefix="fmt"
    uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<%@include file="/WEB-INF/jspf/head.jsp"%>
<style>
tr[locked] {
	background: #eeeeee;
}
</style>

</head>

<body class="layout-vbox">
    <nav class="nav-bar">
        <ol class="breadcrumb">
            <li>
                <a class="fa fa-dashboard" href="javascript:window.top.dashboard()">
                                    首页
                </a>
            </li>
            <li>系统管理</li>
            <li>
                <b>系统用户管理</b>
            </li>
        </ol>
    </nav>

    <section class="action-bar layout-justify">
        <aside>
            <a
                type="button"
                href="jsp/system/usermanage/add.jsp"
                class="btn btn-primary navbar-btn">新增</a>
        </aside>
        <aside>
            <form
                id="qryForm"
                class="inline"
                action="system/user/list.do"
                method="post">
                <input
                    type="hidden"
                    name="pageNo"
                    value="1">
                <span class="input-group">
                    <input
                        type="text"
                        name="key"
                        value="${param.key}"
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
                    <th>账号</th>
                    <th>名称</th>
                    <th>电话</th>
                    <th>状态</th>
                    <th>最后登陆/IP</th>
                </tr>
                <colgroup>
                    <col width="50" align="right">
                    <col width="50">
                    <col width="200">
                    <col width="150">
                    <col width="150">
                    <col width="50">
                    <col width="*">
                </colgroup>
                </thead>
            </table>
        </header>
        <main>
            <table class="data">
                <tbody>
                    <!-- 把requestScope中的result对象放入到pageScope -->
                    <c:set
                        var="result"
                        value="${requestScope.result}"
                        scope="page"></c:set>
                    <c:if test="${result.found}">
                        <c:forEach
                            var="r"
                            items="${result.rows}"
                            varStatus="vs">
                            <tr ${r.flag==1?"locked":""}>
                                <td>${vs.count}</td>
                                <td><a href="jsp/system/usermanage/check.jsp?${r.id}">查看</a></td>
                                <td>${r.account}</td>
                                <td>${r.name}</td>
                                <td>${r.phone}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${r.flag==0}">
											正常
										</c:when>
                                        <c:when test="${r.flag==1}">
											锁定
										</c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <fmt:formatDate
                                        pattern="yyyy-MM-dd HH:mm:ss"
                                        value="${r.lastLoginTime}" />
                                    / ${r.lastLoginIP}
                                </td>

                            </tr>
                        </c:forEach>

                    </c:if>
                    <c:if test="${result.notFound}">
                        <tr>
                            <td
                                colspan="99"
                                align="center">很抱歉，未找到相关数据！</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </main>
        <footer>
            <c:if test="${result.found}">
                <jsp:include page="/WEB-INF/jspf/pagination.jsp">
                    <jsp:param
                        name="resultVarName"
                        value="result" />
                    <jsp:param
                        name="pageClickHandler"
                        value="gotoPage" />
                </jsp:include>
            </c:if>
        </footer>
    </section>
    <script>
                    var getSelectedUserId = function() {
                        var userIdCheckboxs = document.getElementsByName("userId");
                        for (var i = userIdCheckboxs.length; i-->0;) {
                            if (userIdCheckboxs[i].checked) {
                                return userIdCheckboxs[i].value;
                            }
                        }
                        return null;
                    }
                    var grantRole = function() {
                        var id = getSelectedUserId();
                        if (!id) {
                            toast("请先选择");
                            return;
                        }
                        location.href = "jsp/system/usermanage/grant-role.jsp?" + id;
                    }
                </script>
    <script>
                    var gotoPage = function(no) {
                        var qryForm = document.getElementById("qryForm");
                        qryForm.pageNo.value = String(no);
                        qryForm.submit();
                    };
                </script>
</body>

</html>