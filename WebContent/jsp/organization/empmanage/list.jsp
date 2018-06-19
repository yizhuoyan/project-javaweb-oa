<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
 <%@include  file="/WEB-INF/jspf/head.jsp"%>
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
			<li><b>员工管理</b></li>
		</ol>
	</nav>
 <section class="action-bar layout-justify">
        <aside>
            <a
                href="jsp/organization/empmanage/add.jsp"
                class="btn">新增</a>
        </aside>
        <aside>
            <form class="inline"
                action="/organization/employee/list.do"
                method="post"
                class="navbar-form navbar-right">
               <input type="hidden" name="pageNo" value="1">
                <input type="hidden" name="pageSize" value="10"> 
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
	
	<section class="layout-table layout-box-grow">
         <header>
            <table>
                     <tr>
                        <th>#</th>
                        <th >操作</th>
                        <th >工号(账号)</th>
                        <th >名称</th>
                        <th >性别/年龄</th>
                        <th >工作电话/邮箱</th>
                    </tr>
                <colgroup>
                    <col width="30" >
                    <col width="50">
                    <col width="200">
                    <col width="150">
                    <col width="80">
                    <col width="*">
                </colgroup>
            </table>
        </header>
		<main>
			<table>
				<tbody>
					<!-- 把requestScope中的result对象放入到pageScope -->
					<c:set var="result" value="${requestScope.result}" scope="page"></c:set>
					<c:if test="${result.found}">
						<c:forEach var="r" items="${result.rows}" varStatus="vs">
							<tr>
								<td>${vs.count}.</td>
								<td><a href="jsp/organization/empmanage/check.jsp?${r.id}">查看</a></td>
								<td>${r.account}</td>
								<td>${r.name}</td>
								<td>${r.male?"男":"女"}/${r.age}</td>
								<td>${r.workPhone}/${r.workEmail}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${result.notFound}">
						<tr>
							<td colspan="99" align="center">很抱歉，未找到相关数据！</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</main>
        <footer>
		<!-- /.box-body -->
		<c:if test="${result.found}">
			<jsp:include page="/WEB-INF/jspf/pagination.jsp" >
				<jsp:param name="resultVarName" value="result"/>
				<jsp:param name="pageClickHandler" value="gotoPage"/>
			</jsp:include>
		</c:if>
        </footer>
	</section>
	
	<script>
		var gotoPage = function(no) {
			var qryForm = document.getElementById("qryForm");
			qryForm.pageNo.value = String(no);
			qryForm.submit();
		};
	</script>
</body>

</html>