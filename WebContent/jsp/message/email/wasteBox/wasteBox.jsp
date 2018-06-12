<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<body>
	<header class="content-header">
		<h1>废件箱</h1>
		<ol class="breadcrumb">
			<li><a href="javascript:window.top.dashboard()"><i
					class="fa fa-dashboard"></i>首页</a></li>
			<li><a>废件箱</a></li>
		</ol>
		<hr>
	</header>

	<main class="container-fluid"> <!--action bar-->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<a type="button" href="welcome.jsp"
				class="btn btn-primary navbar-btn">首页</a> <a type="button"
				href="email/ListSentAndReceivedEmailServlet.do" class="btn btn-primary navbar-btn">返回</a>
			<form id="qryForm" action="searchRecycleBinServlet.do" method="post"
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
						<th width="100">主题</th>
						<th width="150">发件人</th>
						<th width="150">收件人</th>
						<th width="150">发件时间</th>
						<th width="10"></th>
						<th width="10"></th>
					</tr>
					<c:set var="result" value="${requestScope.result}" scope="page"></c:set>
					<c:forEach var="r" items="${requestScope.result}" varStatus="vs">
					       <tr>
								<td>${r.title}</td>
								<td>${r.sender.name}</td>
								<td>${r.recipient.name}</td>
								<td>${r.sendTime}</td>
								<td><a href="CompletelyDeleteServlet.do?emailId=${r.id}"><button>彻底删除</button></a></td>
								<td><a href="RecoverEmailServlet.do?emailId=${r.id}"><button>恢复</button></a></td>
							</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<footer class="box-footer clearfix">
					<div class="col-xs-6" style="text-align: right">
						<c:if test="${requestScope.pageNo!=1}">
							<a href="emailAddressBookServlet.do&key=${requestScope.key}">首页</a>
							<a href="emailAddressBookServlet.do?pageNo=${requestScope.pageNo-1}&key=${requestScope.key}">上一页</a>
						</c:if>
						<c:if test="${requestScope.pageNo==1}">
							<span>首页</span>
							<span>上一页</span>
						</c:if>
						共<b>${requestScope.total}</b>条
						第<b>${requestScope.pageNo}</b>页
						<c:if test="${requestScope.pageNo!=requestScope.totalPages}">
							<a href="emailAddressBookServlet.do?pageNo=${requestScope.pageNo+1}&key=${requestScope.key}">下一页</a>
							<a href="emailAddressBookServlet.do?pageNo=${requestScope.totalPages}&key=${requestScope.key}">末页</a>
						</c:if>
						<c:if test="${requestScope.pageNo==requestScope.totalPages}">
							<span>下一页</span>
							<span>末页</span>
						</c:if>
					</div>
		        </footer>
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