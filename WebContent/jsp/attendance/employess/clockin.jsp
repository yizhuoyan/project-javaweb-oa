<%@page pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="/WEB-INF/jspf/head.jsp"%>
		<style>
			tr,th{
				text-align: center;
			}
		</style>
	</head>
	<body>
		<h1>打卡</h1>
		<div>
			<form action="attendance/employess/clock-in.do" method="post" id="myForm">
				<table class="table table-bordered ">
					<tr>
						<th>日期</th>											
						<th>打卡时间</th>
						<th>上下午</th>
						<th>是否有效</th>
					</tr>
				<c:if test="${requestScope.clockinresult!=null}">
					<c:forEach var="r" items="${requestScope.clockinresult}"
						varStatus="vs">
						<tr>
							<td>${r.whenDay}</td>
							<td>${r.signinTime}</td>
							<td>${r.ampm eq 0? "上午":"下午"}</td>
							<td>${r.isvalid}</td>
						</tr>
					</c:forEach>
				</c:if>
				</table>
				<button type="button" id="btn">打卡</button>
			</form>
		</div>
		<script type="text/javascript">
			
		
		</script>
		<script type="text/javascript">		
		$("#btn").click(function(){
			url="attendance/employess/clock-in.ajax";
			$.post(url,function(resp){
				if(resp.code==="ok"){
					alert("添加成功");
					window.location.href="attendance/employess/clock-in/list.do";
				}else {
					alert(resp.message);
				}
			});
			});
		</script>
	</body>
</html>