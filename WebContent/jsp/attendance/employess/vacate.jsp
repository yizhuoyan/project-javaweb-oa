<%@page pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

	<head>
		<%@include file="/WEB-INF/jspf/head.jsp"%>
	</head>

	<body>
		<h1>请假</h1>
		<form action="attendance/employess/vacate/apply.ajax" method="post">
			<p>开始时间<input type="date"  name="startTime"/>
				<select name="startM">
					<option>上午</option>
					<option>下午</option>
				</select>
			</p>
			<p>结束时间
				<input type="date" name="endTime" />
				<select name="endM">
					<option>上午</option>
					<option>下午</option>
				</select>
				<p>请假类型
					<select name="vacateType">
						<option>事假</option>
						<option>病假</option>
						<option>出差</option>
						<option>其他</option>
					</select>
				</p>
				<p>请假理由<textarea name="reason"></textarea></p>
				<button type="submit">提交</button>				
		</form>
		<script type="text/javascript">
		$("form").submit(function(evt){
			$.post(this.action,$(this).serialize(),function(resp){
				if(resp.code==="ok"){
					if(!confirm("添加成功，是否继续添加？")){
						
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