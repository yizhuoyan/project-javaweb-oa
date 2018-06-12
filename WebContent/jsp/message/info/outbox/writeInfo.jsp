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
		<h1>写消息</h1>
		<ol class="breadcrumb">
			<li>
			<a href="javascript:window.top.dashboard()"><i class="fa fa-dashboard"></i>首页</a>
			</li>
			<li><a>写消息</a></li>
		</ol>
		<hr>
	</header>

	<main class="container-fluid">
		<center>
		<div style="padding: 10px 10px 10px;">
			<form class="bs-example bs-example-form" role="form">
				<div class="input-group">
					<span class="input-group-addon">收件人</span>
					<input type="text" class="form-control" placeholder="Receiver">
				</div>
				<br>
				<div class="input-group">
					<span class="input-group-addon">主&emsp;题</span>
					<input type="text" class="form-control" placeholder="Theme">
				</div>
				<br>
				<div class="input-group">
					<textarea class="form-control" rows="10" cols="300" placeholder="内容描述" name=textarea></textarea>
				</div>
			</form>
		</div>
			<button type="button" class="btn btn-primary">保存</button>
			<button type="button" class="btn btn-primary">发送</button>
			<button type="button" class="btn btn-primary">返回</button>
		</center>
	</main>
</body>

</html>