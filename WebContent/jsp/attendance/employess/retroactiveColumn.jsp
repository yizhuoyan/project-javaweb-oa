<%@page pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

	<head>
		<%@include file="/WEB-INF/jspf/head.jsp"%>">
		<style>
			
			#a,#b{
				width: 400px;
				margin-left: 90px;	
			}
			#abc{
				width:100%;
				height:500px;
			}
		</style>
	</head>

	<body>
		<div>
			<button id="a" type="button" class="btn btn-primary btn-lg">补签</button>
			<button id="b" type="button" class="btn btn-primary btn-lg">补签记录</button>
			
			<iframe id="abc">
		
			</iframe>
		</div>
	</body>
	<script src="js/jquery-3.3.1.js"></script>
	<script>
		$("#a").click(function() {
			document.getElementById("abc").src = "attendance/employess/retroactive/apply.do";
		});
		$("#b").click(function() {
			document.getElementById("abc").src = "attendance/employess/retroactive/list.do	";
		});
	</script>

</html>