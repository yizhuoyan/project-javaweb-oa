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
        			修改密码
      			</h1>
				<ol class="breadcrumb">
					<li>
						<a href="javascript:window.top.dashboard()"><i class="fa fa-dashboard"></i>首页</a>
					</li>
					<li>
						<a>个人中心</a>
					</li>
					<li>
						<a>修改密码</a>
					</li>
				</ol>
			</section>
		</header>
		<main class="container-fluid">
			<div id="message-container" class="hide alert alert-info " role="alert">
				<strong id="messageEL">${message}</strong>					
			</div>
			<!-- form start -->
			<form role="form" action="user/modifyPassword.ajax"  method="post" class="box">
				
				<div class="box-body">
					<p>
						<strong id="messageEL"></strong>
					</p>
					<p class="form-group">
						<label for="oldPasswordEL">*原密码：</label>
						<input type="password" name="oldPassword"  class="form-control" id="oldPasswordEL" placeholder="请输入旧密码">
					</p>
					<p class="form-group">
						<label for="newPasswordEL">*新密码：</label>
						<input type="password" name="newPassword" maxlength="16"  class="form-control" id="newPasswordEL" placeholder="6-16个字符，必须包含字母，数字，特殊字符">
					</p>
					<p class="form-group">
						<label for="newPasswordConfirmEL">*新密码确认：</label>
						<input type="password" name="newPasswordConfirm" maxlength="16"  class="form-control" id="newPasswordConfirmEL" placeholder="请再次输入您的新密码">
					</p>
					
				</div>

				<div class="box-footer">
					<button type="submit" class="btn btn-lg btn-primary">保存</button>
					<a type="button" class="btn btn-info" href="javascript:window.top.dashboard()">返回</a>
				</div>
			</form>

		</main>
		
		

		
		<script type="text/javascript">
			$("form").submit(function(){
				//防止重复提交
				var submitBtn=$("button[type='submit']",this)[0];
				submitBtn.disabled=true;
				$.post(this.action,$(this).serializeArray(),function(resp){
					if(resp.code==="ok"){
						alert("密码修改成功，请重新登陆!");
						//window.location.href="jsp/login.jsp";
					}else{
						$("#messageEL").html(resp.message);
						$("#message-container").show();
						
					}
					submitBtn.disabled=false;
				});
				return false;
			});
		</script>
		
	</body>

</html>