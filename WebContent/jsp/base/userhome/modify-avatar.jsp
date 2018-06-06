<%@page pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
	 <%@include file="/WEB-INF/jspf/head.jsp"%>
	</head>

	<body>
		<header>
			<section class="content-header">
				<h1>
        			修改头像
      			</h1>
				<ol class="breadcrumb">
					<li>
						<a href="javascript:window.top.dashboard()"><i class="fa fa-dashboard"></i>首页</a>
					</li>
					<li>
						<a>个人中心</a>
					</li>
					<li>
						<a>修改头像</a>
					</li>
				</ol>
			</section>
		</header>
		<main class="container-fluid">
			<form role="form" action="user/modifyAvatar.do"  method="post" enctype="multipart/form-data" class="box">
				<div class="box-body">
					<p>
						<strong id="messageEL"></strong>
					</p>
					<p class="form-group">
						<label for="avatarEL">*请选择头像文件(仅支持gif、png格式，不得超过2m)：</label>
						<input type="file" name="avatar"  accept=".png,.gif" class="form-control" id="avatarEL" >
					</p>
				</div>

				<div class="box-footer">
					<button type="submit" class="btn btn-lg btn-primary">提交</button>
					<a type="button" class="btn btn-info" href="javascript:window.top.dashboard()">返回</a>
				</div>
			</form>

		</main>
		
	</body>

</html>