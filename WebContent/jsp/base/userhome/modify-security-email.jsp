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
        			修改密保邮箱
      			</h1>
				<ol class="breadcrumb">
					<li>
						<a href="javascript:window.top.dashboard()"><i class="fa fa-dashboard"></i>首页</a>
					</li>
					<li>
						<a href="javascript:">个人中心</a>
					</li>
					<li>
						<a>修改密保邮箱</a>
					</li>
				</ol>
			</section>
		</header>
		<main class="container-fluid">
			<div id="message-container" class="hide alert alert-info " role="alert">
				<strong id="messageEL">${message}</strong>					
			</div>
			<form role="form" action="user/modifypassword.ajax"  method="post" class="box">
				
				<div class="box-body">
					<p>
						系统会把激活新的密保邮箱链接发送一封邮件到您的密保邮箱，
					</p>
					<p class="form-group">
						<label for="newSecurityEmailEL">*新的密保邮箱：</label>
						<input type="email"  
						name="email"  
						class="form-control"
						 id="newSecurityEmailEL" 
						 placeholder="请输入新的密保邮箱" required>
					</p>
					<p class="form-group form-inline">
						<input id="captchaEL" class="form-control"  type="text" name="captcha" value="1" placeholder="请输入验证码"/>
						<img src="captcha.do"  width="150" height="34" style="cursor: pointer" title="看不清，点击图片切换"
							 onclick="this.src='captcha.do?'+Date.now()" >
					    <button id="sendEmailBtn" class="btn btn-default">发送邮件</button>		 
					</p>
				</div>

				<div class="box-footer">
					<button type="submit" class="btn btn-lg btn-primary">确定</button>
					<a type="button" class="btn btn-info" href="dashboard.do">返回</a>
				</div>
			</form>

		</main>
		<script>
			$("#sendEmailBtn").click(function(){
				this.disabled=true;
				var btn=this;
				btn._times=15;
				var showProceed=function(){
					btn.textContent="发送中("+btn._times+"s)";
					btn._times--;
					if(btn._times>=0){
						setTimeout(showProceed,1000);
					}else{
						btn.textContent="再次发送";
						btn.disabled=false;
					}
				}
				showProceed();
				var url="user/modifySecurityEmail/sendEmail.ajax";
				$.post(url,{
					email:$("#newSecurityEmailEL").val(),
					captcha:$("#captchaEL").val()
				},function(resp){
					if(resp.code==="ok"){
						btn._times=0;
						alert("发送成功，请查看邮箱!");
					}else{
						alert("发送失败，请确认邮箱地址")
					}
				})
				
			})
		</script>
		

		
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
						submitBtn.disabled=false;
					}
				});
				return false;
			});
		</script>
		
	</body>

</html>