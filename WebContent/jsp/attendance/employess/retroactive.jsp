<%@page pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

	<head>
		<%@include file="/WEB-INF/jspf/head.jsp"%>">
		<style>
			tr,th{
				text-align: center;
			}
			#show{
				display: none;
			}
			input{
				border: 0px;outline:none;
				cursor: pointer;			
			}
			
		</style>
	</head>
	<body>
		<h1>补签</h1>
		<div>			
				<table class="table table-bordered">
					<tr>
						<th>补签id</th>
						<th>签到时间</th>
						<th>上下午</th>
						<th>补签原因</th>
						<th>操作</th>
					</tr>
					<c:if test="${requestScope.singleRetroactiveresult!=null}">
					<c:forEach var="r" items="${requestScope.singleRetroactiveresult}"
						varStatus="vs">
						<form action="attendance/employess/retroactive/apply.ajax" method="post">
							<tr>
								<td><input type="text" name="id" value="${r.id}" readonly="readonly"/></td>
								<td>${r.retTime}</td>
								<td>${r.retM}</td>
								<td>
									<select name="reason">
										<option>忘记打卡</option>
										<option>拉肚子上厕所了</option>
										<option>我就不想打卡</option>
									</select>
								</td>
								<td><button type="submit" id="btn">补签</button></td>
							</tr>
						</form>
					</c:forEach>
				</c:if>
			</table>
			
			<script type="text/javascript">
			$("form").submit(function(evt){
				$.post(this.action,$(this).serialize(),function(resp){
					if(resp.code==="ok"){
						if(!confirm("提交成功，是否继续提交？")){
							
						}
					}else{
						alert(resp.message);
					}
				})
				return false;
			})			
			</script>
	</body>
</html>