<%@page pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>NEUSOFT·OA|重置密码</title>
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
				background: #008EAD;
				height: 60%;
				margin: 0;
			}
			
			body>header {
				padding-top: 100px;
			}
			
			body>section {
				position: absolute;
				bottom: -100px;
				left: 0;
				right: 0;
				box-sizing: border-box;
				width: 400px;
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
			
			body>header>h1 {
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
			
			input.icoTip {
				border: 1px solid #D3D3D3;
				border-radius: 5px;
				padding: 10px 0px 10px 30px;
				background-repeat: no-repeat;
				width: 100%;
				box-sizing: border-box;
				background-position: 5px center;
			}
			
			input.icoTip:focus {
				border-color: #3333FF;
				outline: 0;
			}
			
			
			.ico-account {
				background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAANCAMAAACXZR4WAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAIdQTFRF////pqaooqGip6ywsLGwuLe47+/v8+/xxsfJm6euxMTE9/f3xsvPsa+z19fbqrS65ufroZuY7efk8/Pzzs/R9/v34+PjzNTZ09PX//v/29vb+///7+vr8/P319vclZ2jzMfF5+fjy8fL+/v7+/f3s73G3NfV7+/qvcLA39/f5+fnsquo09PTXbQ8vQAAAJRJREFUeNpcjkcSwzAMA0HKVLOcuKX3Xv//vlDx5JLFDRyAAFBaa90gtwIwlUqKH/EEzJkNDxiuzkAnTMbQVzzSiHveiSnD1dYugH2yo4rHevZtXZRA2kndCgd6dz0FNTyxmblrWh8uGlMjaifL8pFe2mSyESh/0RKFNhrhwIZCE3RIYybArZfoxcd49F7yjj8+AgwA3xsGi1Yum4kAAAAASUVORK5CYII=);
			}
			
			.ico-password {
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
		<header>
			<h1>重置密码</h1>
		</header>
		<section>
			<h2></h2>
			<p>请输入您的账号和密保邮箱后，系统会发送一封邮件到您的邮箱中。
			</p>
			<form action="">
				<p>
					<input>
				</p>
				<p>
					<input>
				</p>
				<p>
					<input>
				</p>
				<p>
					<button>确定</button>
					<a>返回登陆</a>
				</p>
				
			</form>
		</section>
		<footer>
			&copy;东软睿道 2000-2100 All Rights Reservered.
		</footer>
	</body>
	
	<script type="text/javascript" src="js/plugins/jquery.min.js" ></script>
	<script>
	
	</script>

</html>