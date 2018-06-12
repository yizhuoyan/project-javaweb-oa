<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<%@include file="/WEB-INF/jspf/head.jsp"%>
</head>

<body>
	<header class="content-header">
		<h1>工作日设置查看/修改</h1>
		<ol class="breadcrumb">
			<li><a href="javascript:window.top.dashboard()"><i
					class="fa fa-dashboard"></i>首页</a></li>
			<li><a href="attendance/admin/setdate/list.do">工作日列表</a></li>
			<li><a>新增</a></li>
		</ol>
		<hr>
	</header>

	<main class="container-fluid">
	<div class="alert alert-info " id="message-box" style="display: none">
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<strong id="messageEL"> </strong>
	</div>
	<!-- form start -->
	<form action="attendance/admin/setdate/mod.do" method="post"
		class="box">
		<div class="box-body">
			<input type="hidden" name="id" value="${result.id}">
			<p class="form-group">
				<label for="whenDayEL">*日期：</label> <input type="date"
					name="whenDay" maxlength="32" class="form-control" id="whenDayEL"
					value="${result.whenDay}" readonly>
			</p>
			<c:if test="${result.workDay=='是'}">
				<p class="form-group">
					<label for="workDayEL">*是否工作日：</label> 是<input type="radio"
						name="workDay" maxlength="32" checked id="workDayEL" value="是"
						onclick="$('#settime').show()">否<input type="radio"
						name="workDay" maxlength="32" id="workDayEL" value="否"
						onclick="$('#settime').hide()">
				</p>
			</c:if>
			<c:if test="${result.workDay=='否'}">
				<p class="form-group">
					<label for="workDayEL">*是否工作日：</label> 是<input type="radio"
						name="workDay" maxlength="32" id="workDayEL" value="是"
						onclick="$('#settime').show()">否<input type="radio"
						name="workDay" maxlength="32" id="workDayEL" value="否" checked
						onclick="$('#settime').hide()">
				</p>
			</c:if>
				<div id="settime">
					<p class="form-group">
						<label>上班时间：</label> <input type="time" class="form-control"
							name="onDuty" value="${result.onDuty}" >
					</p>
					<p class="form-group">
						<label>下班时间：</label> <input type="time" class="form-control"
							name="offDuty" value="${result.offDuty}" >
					</p>
					<p class="form-group">
						<label>上班打卡开始时间：</label> <input type="time" class="form-control"
							name="signInStart" value="${result.signInStart}" >
					</p>
					<p class="form-group">
						<label>上班打卡结束时间：</label> <input type="time" class="form-control"
							name="signInEnd" value="${result.signInEnd}" >
					</p>
					<p class="form-group">
						<label>下班打卡开始时间：</label> <input type="time" class="form-control"
							name="signOutStart" value="${result.signOutStart}" >
					</p>
					<p class="form-group">
						<label>下班打卡结束时间：</label> <input type="time" class="form-control"
							name="signOutEnd" value="${result.signOutEnd}" >
					</p>
				</div>
			<p class="form-group">
				<label>备注：</label> <input type="text" class="form-control"
					name="remark" value="${result.remark}">
			</p>
		</div>
		<!-- /.box-body -->

		<div class="box-footer">
			<button type="submit" class="btn btn-lg btn-primary">保存</button>
			<a type="button" class="btn btn-info"
				href="attendance/admin/setdate/list.do">返回</a>

		</div>
	</form>

	</main>
	<c:if test="${result.workDay=='是'}"><script type="text/javascript">$('#settime').show()</script></c:if>
	<c:if test="${result.workDay=='否'}"><script type="text/javascript">$('#settime').hide()</script></c:if>
			
	<script>
		
		$("form").submit(function() {
			$.post(this.action, $(this).serializeArray(), function(resp) {
				if (resp.code === "ok") {
					alert("设置成功，请返回！");
				} else {
					alert(resp.message);
				}
			});
			return false;
		});
	</script>
</body>

</html>