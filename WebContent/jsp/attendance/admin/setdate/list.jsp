<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jspf/head.jsp"%>
<style>
tr, th {
	text-align: center;
}
</style>
</head>
<body>
	<h1>工作日列表</h1>
	<main class="container-fluid"> 
	<nav class="navbar navbar-default">
		<div class="container-fluid">
		
			<a type="button" href="jsp/attendance/admin/setdate/set.jsp"
				class="btn btn-primary navbar-btn">设置工作日
			</a>
			
			<form id="qryForm" action="attendance/admin/setdate/list.do" method="post"
				class="navbar-form navbar-right">
				<input type="hidden" name="pageNo" value="1">
				<input type="hidden" name="pageSize" value="10">
				<div class="form-group">
					<input type="text" name="key" value="${param.key}"
						class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Go</button>
			</form>
			
		</div>
	</nav>
	<section class="box">
		<div class="box-body">
			<table class="table table-bordered">
				<tbody>
					<tr>
						<th width="50px">#</th>
						<th>操作</th>
						<th>日期</th>
						<th>是否工作日</th>
						<th>备注</th>
					</tr>
					<!-- 把requestScope中的result对象放入到pageScope -->
					<c:set var="result" value="${requestScope.result}" scope="page"></c:set>
					<c:if test="${result.found}">
						<c:forEach var="r" items="${result.rows}" varStatus="vs">
							<tr>
								<td>${vs.count}.</td>
								<td><a href="attendance/admin/setdate/check.do?id=${r.id}">查看/修改</a></td>
								<td>${r.whenDay}</td>
								<td>${r.workDay}</td>
								<td>${r.remark}</td>
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
		</div>
		<!-- /.box-body -->
		<c:if test="${result.found}">
			<jsp:include page="/WEB-INF/jspf/pagination.jsp" >
				<jsp:param name="resultVarName" value="result"/>
				<jsp:param name="pageClickHandler" value="gotoPage"/>
			</jsp:include>
		</c:if>
	</section>
	
	</main>
	
	<script>
		var gotoPage = function(no) {
			var qryForm = document.getElementById("qryForm");
			qryForm.pageNo.value = String(no);
			qryForm.submit();
		};
	</script>
</body>
</html>