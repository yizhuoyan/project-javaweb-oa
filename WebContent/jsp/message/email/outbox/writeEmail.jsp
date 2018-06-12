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
	<header class="content-header">
		<h1>写邮件</h1>
		<ol class="breadcrumb">
			<li>
			<a href="javascript:window.top.dashboard()"><i class="fa fa-dashboard"></i>首页</a>
			</li>
			<li><a>写邮件</a></li>
		</ol>
		<hr>
	</header>
	<c:if test="${not empty message}">
			<div class="alert alert-info " user="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
				</button> 
				${message}					
		</div>
	</c:if>
	<a style="position:relative;left:3%" href="emailAddressBookServlet.do"><button type="button" class="btn btn-primary">邮件通讯录</button></a>
	<a style="position:relative;left:3%" href="email/ListSentAndReceivedEmailServlet.do"><button type="button" class="btn btn-primary">返回</button></a>
	
	<main class="container-fluid">
		<center>
		<form class="bs-example bs-example-form"  name="form" action="addEmail.do" enctype="multipart/form-data" method="post">
		<div style="padding: 10px 10px 10px;">
				<div class="input-group">
					<span class="input-group-addon" >收件人</span>
					<input type="text" id="recipient" class="form-control" value="${requestScope.result.recipient.name}" placeholder="Receiver" name="recipient">
				</div>
				<br>
				<div class="input-group">
					<span class="input-group-addon">主&emsp;题</span>
					<input type="text" class="form-control" id="title" name="title" value="${requestScope.result.title}" placeholder="Theme">
				</div>
				<br>
				<div class="input-group">
					<textarea class="form-control" rows="10" cols="300" id="content" placeholder="内容描述" name="content">${requestScope.result.content}</textarea>
				</div>
				<br>
				<input type="file" name="attachment"/><br>
		</div>
			<button type="submit" name="submit" value="1" class="btn btn-primary">保存</button>
			<button type="submit" name="submit" value="0" class="btn btn-primary">发送</button>		
		</form>
		</center>
	</main>
</body>
</html>