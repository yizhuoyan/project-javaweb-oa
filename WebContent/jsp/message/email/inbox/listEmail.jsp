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
		<h1>邮件查看</h1>
		<ol class="breadcrumb">
			<li><a href="javascript:window.top.dashboard()"><i
					class="fa fa-dashboard"></i>首页</a></li>
			<li><a>邮件查看</a></li>
		</ol>
		<hr>
	</header>

	<main class="container-fluid">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<a type="button" href="email/ListSentAndReceivedEmailServlet.do"
				class="btn btn-primary navbar-btn">返回邮件管理首页</a>
		</div>
	</nav>
		<center>
		<form class="bs-example bs-example-form"  name="form" action="addEmail.do">
		<div style="padding: 10px 10px 10px;">
				<div class="input-group">
					<span class="input-group-addon" >发件人</span>
					<input type="text" id="recipient" class="form-control" placeholder="Receiver" value="${requestScope.result.sender.name}">
				</div>
				
				<br>
				<div class="input-group">
					<span class="input-group-addon">主&emsp;题</span>
					<input type="text" class="form-control" id="title" name="title" placeholder="Theme" value="${requestScope.result.title}">
				</div>
				<br>
				<div class="input-group">
					<textarea class="form-control" rows="10" cols="300" id="content" placeholder="内容描述" name="content">${requestScope.result.content}</textarea>
				</div>
				<br>
					<a href="downloadAttachment.do?id=${requestScope.result.id}">查看附件</a>
				</div>
		</form>
		</center>
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