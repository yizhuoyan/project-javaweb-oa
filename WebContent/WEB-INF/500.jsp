<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<title>500</title>
<%@include file="/WEB-INF/jspf/head.jsp"%>
<style>
	body{
		padding:2em;
	}
</style>
</head>
<body>
	<header class="page-header">
		<h1>500
			<small>糟了，出粗了！</small> 
		</h1>
	</header>
	<section>
  		<p>
			别慌，我们正在修复中。。。
		</p>
		<p>
			<span>同时，你可以玩玩下面这个游戏等会再试。或者返回</span></span>
			<a href="dashboard.do"><i class="fa fa-dashboard"></i>首页</a>。
		</p>
	</section>
	
	<section>
		<button onclick="beginGame()">开始游戏</button>
		<script>
			var beginGame=function(){
					var KICKASSVERSION='2.0';
					var s = document.createElement('script');
					s.type='text/javascript';
					s.src='js/kickass.js';
					document.body.appendChild(s);
			}
		</script>
		
	</section>
	
	
</body>
</html>