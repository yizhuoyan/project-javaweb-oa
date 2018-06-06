<%@page pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
	if(window!=window.top){
		window.top.location.href=window.location.href;
	}
</script>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="/WEB-INF/jspf/head.jsp"%>
		<style>
			html {
				background: #EBEBEB;
				height: 100%;
				font-size: 13px;
			}
			strong {
				color: #f00;
			}
			form,p{
				margin: 0;
			}
			body {
				position: relative;
				margin: 0;
				background:#008EAD url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAMACAMAAADSf/2DAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAABtQTFRFAI6t6+vr1ePm//Tx7e3tAIanGpq16uvrHZaywmcFoAAAACZJREFUeNpiYGMYBaNgFAwuwMrAwcDEwMzAzsA4CkchzSALQIABAM7rAVOaZR0nAAAAAElFTkSuQmCC") repeat-x;
			}
			
			body>main {
				position: absolute;
				top:80px;
				left: 0;
				right: 0;
				box-sizing: border-box;
				width:400px;
				margin-left: auto;
				margin-right: auto;
				z-index: 1;
			}
			
			body>footer {
				text-align: center;
				position: fixed;
				bottom: 10px;
				left: 0;
				right: 0;
				line-height: 30px;
			}
			
			body>main>h1 {
				margin: 0;
				color: #fff;
				font-size: 500%;
				text-align: center;
			}
			.message-box {
				line-height: 30px;
				min-height:30px;
			}
			.form-box {
				background: white;
				text-align: center;
				border-radius: 2px;
			}
			
			input.ico {
				border: 1px solid #D3D3D3;
				border-radius: 5px;
				padding: 10px 0px 10px 30px;
				background-repeat: no-repeat;
				box-sizing: border-box;
				background-position: 5px center;
				flex-grow: 1;
				
			}
			
			input.ico:focus {
				border-color: #3333FF;
				outline: 0;
			}
			
			
			.ico-account {
				background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAANCAMAAACXZR4WAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAIdQTFRF////pqaooqGip6ywsLGwuLe47+/v8+/xxsfJm6euxMTE9/f3xsvPsa+z19fbqrS65ufroZuY7efk8/Pzzs/R9/v34+PjzNTZ09PX//v/29vb+///7+vr8/P319vclZ2jzMfF5+fjy8fL+/v7+/f3s73G3NfV7+/qvcLA39/f5+fnsquo09PTXbQ8vQAAAJRJREFUeNpcjkcSwzAMA0HKVLOcuKX3Xv//vlDx5JLFDRyAAFBaa90gtwIwlUqKH/EEzJkNDxiuzkAnTMbQVzzSiHveiSnD1dYugH2yo4rHevZtXZRA2kndCgd6dz0FNTyxmblrWh8uGlMjaifL8pFe2mSyESh/0RKFNhrhwIZCE3RIYybArZfoxcd49F7yjj8+AgwA3xsGi1Yum4kAAAAASUVORK5CYII=);
			}
			
			.ico-password {
				background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAANCAMAAACXZR4WAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAGBQTFRF/////f///Pv7/v//oqOk/P//oaOm//39mpmc/v39nJubmZibo5+d/v7/nZ2gn6Km/vz7/v7+oqOl/vz8/Pz8/f39mZ2gmqGmlJWXlpeZmJ6k//79/Pz9lpSVn6Gj////TjWZsQAAACB0Uk5T/////////////////////////////////////////wBcXBvtAAAAbklEQVR42kyOUQ+DQAiDuZrVe9iMq5rL4rL//zMnHEb7QPJBKZiFVgDFboKo7SIU6KPDhM6kwCZ3oTempw7xV7OBQLlxyYxAEucFBVJCZjTGUqvdA59o8oI5M8z/mN/Yg4d0ve6v2xdjefjoL8AALC4FNEIwQfIAAAAASUVORK5CYII=);
			}
			.ico-captcha{
				background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAANCAMAAACXZR4WAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAGBQTFRF/////f///Pv7/v//oqOk/P//oaOm//39mpmc/v39nJubmZibo5+d/v7/nZ2gn6Km/vz7/v7+oqOl/vz8/Pz8/f39mZ2gmqGmlJWXlpeZmJ6k//79/Pz9lpSVn6Gj////TjWZsQAAACB0Uk5T/////////////////////////////////////////wBcXBvtAAAAbklEQVR42kyOUQ+DQAiDuZrVe9iMq5rL4rL//zMnHEb7QPJBKZiFVgDFboKo7SIU6KPDhM6kwCZ3oTempw7xV7OBQLlxyYxAEucFBVJCZjTGUqvdA59o8oI5M8z/mN/Yg4d0ve6v2xdjefjoL8AALC4FNEIwQfIAAAAASUVORK5CYII=);
			}
			
			.form-fields {
				padding-left:15px;
				padding-right:15px;
			}
			.form-action {
				border-top: 1px solid #E7E7E7;
				padding: 10px 15px;
				display: table;
				width: 100%;
				box-sizing: border-box;
				vertical-align: middle;
			}
			.form-fields>p {
				margin-top: 0;
				margin-bottom: 15px;
				display: flex;
				height: 50px;
			}
			.form-action>:first-child {
				display: table-cell;
				text-align: left;
			}
			
			.form-action>:last-child {
				display: table-cell;
				text-align: right;
			}
			
			button[type=submit] {
				background: #008EAD;
				border: 1px solid #008EaF;
				color: #fff;
				border-radius: 5px;
				font-weight: bold;
				padding: 10px 15px;
				cursor: pointer;
			}
			button[type=submit]:hover{
				background: #694530;
			}
			
		</style>

		<style>
			.eagel-box {
				text-align: center;
				position: relative;
				bottom: -100px;
				z-index: -1;
			}
			.sprt-eagle {
				background-image: url(img/sprt_eagle.png);
				background-repeat: no-repeat;
				display: inline-block;
				vertical-align: bottom;
				position: relative;
			}
			
			.sprt-eagle.head {
				height: 91px;
				width: 97px;
				background-position: -30px 0px;
				z-index: 100;
			}
			.sprt-eagle.hand-left {
				height: 20px;
				width: 30px;
				background-position: 0px -71px;
				left: -30px;
				z-index: 200;
			}
			.sprt-eagle.hand-left.hiding {
				height: 20px;
				background-position: 0px -37px;
			}
			.sprt-eagle.hand-left.hided {
				height: 37px;
				background-position: 0px 0px;
			}
			.sprt-eagle.hand-right {
				height: 20px;
				width: 30px;
				background-position: 0px -71px;
				left: 30px;
				z-index: 200;
			}
			.sprt-eagle.hand-right.hiding {
				height: 20px;
				background-position: -127px -37px;
			}
			.sprt-eagle.hand-right.hided {
				height: 37px;
				background-position: -127px 0px;
			}
		</style>
	</head>

	<body>
		<main>
			<h1>东软·OA</h1>
			<div class="eagel-box">
				<i class="sprt-eagle hand-left"></i>
				<i class="sprt-eagle head"></i>
				<i class="sprt-eagle hand-right"></i>
			</div>
			
			<div class="form-box">
				<p class="message-box">
					<strong id="messageEL">${message}</strong>
					<%session.removeAttribute("message");%>
				</p>
				<form  action="login.ajax"  method="post">
					<div class="form-fields">
					
						<p>
							<input class="ico ico-account" type="text" name="account" value="system" placeholder="账号">
						</p>
						
						<p>
							<input class="ico ico-password" name="password" type="password" value="admin" placeholder="密码">
						</p>
						<p>
							<input class="ico ico-password" type="text" name="captcha" value="1" placeholder="请输入验证码"/>
							<img id="captchaImgEL" src="captcha.do"  width="200" height="50" style="cursor: pointer" title="看不清，点击图片切换"
							 onclick="this.src='captcha.do?w='+this.width+'&h='+this.height+'&'+Date.now()" >
						</p>
					</div>
					<div class="form-action">
						<span>
							<a href="jsp/forgetPassword.jsp">忘记密码？</a>
						</span>
						<span>
							<button id="submitBtn" type="submit" >登陆</button>
						</span>
					</div>
					
				</form>
			</div>
		
		</main>
		
		<footer>
			&copy;东软睿道 2000-<span id="yearNowEL"></span> All Rights Reservered.
		</footer>

	<script src="js/plugins/jquery.min.js" ></script>
	<script>
		$("#yearNowEL").text(new Date().getFullYear());
		
		$("form").submit(function(){  
			//防止重复提交
			$("#submitBtn")[0].disabled=true;
			$.post(this.action,$(this).serializeArray(),function(resp){
				if(resp.code==="ok"){
					window.location.href="index.do";
				}else{
					//$("#messageEL").html(resp.message);
					toast(resp.message);
					shakeIt("body>main");
					$("#submitBtn")[0].disabled=false;
					//重新生成验证码
					$("#captchaImgEL").click();
				}
			});
			return false;
		});
	</script>
	
	<script>
		var shakeIt=function(e){
			e=document.querySelector(e);
			var offsets=[0,2,-2,4,-4,8,-8,16,-16];
			offsets=offsets.concat(offsets,offsets);
			var run=function(){
				e.style.transform="translate("+offsets.pop()+"px,0px)";
				if(offsets.length){
					setTimeout(run,1000/30);	
				}
			}
			run();
		};
	</script>
	
	<script>
		
		var eagleLeftHandEL=$(".hand-left");
		var eagleRightHandEL=$(".hand-right");
		var eagleHandHidedStatus=[{left:45,bottom:6},{left:-45,bottom:6}];
		$("input[type=password]").focus(function(){
			eagleLeftHandEL.animate(eagleHandHidedStatus[0],{
				step:hidingStep,
				duration:500
			});
			eagleRightHandEL.animate(eagleHandHidedStatus[1],{
				step:hidingStep,
				duration:500,
				complete:function(){
					setTimeout(eaglePeek,1000);
				}
			});
		}).blur(function(){
			//立即结束动画
			eagleRightHandEL.stop(true,true);
			eagleLeftHandEL.stop(true,true);
			
			
			eagleLeftHandEL[0].hideStatus=0;
			eagleLeftHandEL[0].className="sprt-eagle hand-left";
			eagleLeftHandEL[0].style="";
			
			eagleRightHandEL[0].hideStatus=0;
			eagleRightHandEL[0].className="sprt-eagle hand-right";
			eagleRightHandEL[0].style="";
			
		});
		var eagleShow=function(){
			$(".eagel-box").animate({
				bottom:-6
			},1000,function(){
				this.style.zIndex=100;
			});
		};
		/**
		 * the hiding step control
		 * @param {Object} now
		 * @param {Object} fx
		 */
		var hidingStep=function(now,fx){
			if(fx.prop==="bottom"){
				now=Math.floor(now);
				if(now===3&&this.hideStatus!==1){
					this.classList.add("hiding");
					this.hideStatus=1;
				}else if(now===5&&this.hideStatus!==2){
					this.classList.remove("hiding");
					this.classList.add("hided");
					this.hideStatus=2;
				}
			}
		};
		/**
		 * the eagel peak
		 */
		var eaglePeek=function(){
			if(eagleRightHandEL[0].hideStatus===2){
				eagleRightHandEL.animate({
					bottom:3,
					left:-25
				},300,function(){
					setTimeout(function(){
						if(eagleRightHandEL[0].hideStatus===2){
							eagleRightHandEL.animate(eagleHandHidedStatus[1],200);
						}
					},500);
				});
			}
		};
		eagleShow();
	</script>
	
	<script>
	
	</script>
	</body>
</html>