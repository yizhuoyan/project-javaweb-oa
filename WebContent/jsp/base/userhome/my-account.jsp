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
        			我的账号
      			</h1>
				<ol class="breadcrumb">
					<li>
						<a href="javascript:window.top.dashboard()"><i class="fa fa-dashboard"></i>首页</a>
					</li>
					<li>
						<a>个人中心</a>
					</li>
					<li>
						<a>我的账号</a>
					</li>
				</ol>
			</section>
		</header>
			
			<main class="container-fluid">
				<c:set var="vo" value="${sessionScope.loginUser}"/>
				<div class="box-body">
					<fieldset>
					<legend>基础信息</legend>
					<p>
						<label>头像：</label>
						<img alt="头像" width="160" height="160" src="${app}/${vo.avatar}" title="点击浏览大图">
						<a href="javascript:modifyAvatar()">修改头像</a>
					</p>
					<p>
						<label for="accountEL">账号：</label>
						<b>${vo.account}</b>
					</p>
					<p>
						<label for="nameEL">名称：</label>
						<input maxlength="32" data-value="${vo.name}" value="${vo.name}" id="nameEL" placeholder="不超过32个字符" required>
						<button id="modifyNameBtn">修改</button>
					</p>
					<p >
						<label>密保邮箱：</label>
						<b>${vo.securityEmail}</b>
						<a href="javascript:" onclick="window.top.openMenu(this.textContent)">修改密保邮箱</a>
						
					</p>
					</fieldset>
					<fieldset>
					<legend>安全信息 <small>(如果不是您的操作，请<a href="javascript:" onclick="window.top.openMenu(this.textContent)">修改密码</a>)</small></legend>
					
					<p >
						<label>最后登陆时间：</label>
						<b>
							${vo.lastLoginTime}
						</b>
					</p>
					<p >
						<label>最后登陆IP：</label>
						<b>${vo.lastLoginIp}</b>
						
					</p>
					<p >
						<label>最后一次密码修改时间：</label>
						<b>${vo.lastModPasswordTime}</b>
					</p>
					</fieldset>
					
				</div>

				<div class="box-footer">
					<a type="button" class="btn btn-info" href="javascript:window.top.dashboard()">返回</a>
				</div>
		</main>
		
		

		
		
		<script type="text/javascript">
			$("#modifyNameBtn").click(function(){
				
				
				var url="user/modifyName.ajax";
				
				var newName=$("#nameEL").val().trim();
				var oldName=$("#nameEL").attr("data-value");
				if($.isBlank(newName)){
					return;
				}
				if(oldName===newName){
					alert("修改成功！");
					return;
				}
				//防止重复提交
				this.disabled=true;
				
				$.post(url,{
					name:newName
				},function(resp){
					if(resp.code==="ok"){
						alert("修改成功，请重新登陆!");
						setTimeout(function(){
							window.location.href="jsp/login.jsp";	
						},600);
						
					}else{
						alert(resp.message);
						
					}
					this.disabled=false;
				});
				return false;
			});
			
			var modifyAvatar=function(){
				location.href="jsp/base/userhome/modify-avatar.jsp";
				
			}
		</script>
		
	</body>

</html>