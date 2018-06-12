<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
 <%@include  file="/WEB-INF/jspf/head.jsp"%>
 <style>
 tr[locked]{
 	background:#eeeeee;
 }
 </style>
 
</head>

<body>

	<main class="container-fluid"> <!--action bar-->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
		
			<form id="qryForm" action="attendance/employess/vacate/apply.do" method="post"
				class="navbar-form navbar-right">
				<input type="hidden" name="pageNo" value="1">
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
						<th width="10">#</th>
						<th width="150">申请时间</th>
						<th width="100">请假开始时间</th>
						<th width="100">请假开始上下午</th>
						<th width="100">请假结束时间</th>
						<th width="100">请假开始上下午</th>
						<th width="100">请假类型</th>
						<th width="100">请假原因</th>
						<th width="100">请假结果</th>
						<th width="100">审批人</th>
						<th width="100">审批时间</th>
						<th>备注</th>
					</tr>
					<!-- 把requestScope中的result对象放入到pageScope -->
					<c:set var="vacateresult"  value="${requestScope.vacateresult}" scope="page"></c:set>
					<c:if test="${vacateresult.found}">
						<c:forEach var="r" items="${vacateresult.rows}"  varStatus="vs">
							<tr>
								<td>${vs.count}.</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${r.when}" /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd " value="${r.starttime}" /></td>
								<td>${r.startM }</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd " value="${r.endtime}" /></td>
								<td>${r.endM }</td>
								<td>${r.type }</td>
								<td>${r.reason }</td>
								<td>${r.result }</td>
								<td>${r.approver }</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${r.approvetime }" /></td>
								<td>${r.remark }</td>
							</tr>
						</c:forEach>
						
					</c:if>
					<c:if test="${vacateresult.notFound}">
						<tr>
							<td colspan="99" align="center">很抱歉，未找到相关数据！</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		<!-- /.box-body -->
		<c:if test="${vacateresult.found}">
			<jsp:include page="/WEB-INF/jspf/pagination.jsp" >
				<jsp:param name="resultVarName" value="vacateresult"/>
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