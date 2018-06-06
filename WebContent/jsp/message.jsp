<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jspf/head.jsp" %>
<meta http-equiv="refresh" content="5; url=${nextUrl}">
</head>
<body>
	<p>
		${message}
	</p>
	<p>
		5s后自动返回，如果未返回请点击<a href="${nextUrl}">这里</a>
	</p>
</body>
</html>