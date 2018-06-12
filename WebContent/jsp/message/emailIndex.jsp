<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<%@include file="/WEB-INF/jspf/head.jsp"%>
<style>
tr[locked] {
	background: #eeeeee;
}
</style>

</head>

<body>
	<header class="content-header">
		<h1>邮件管理</h1>
		<ol class="breadcrumb">
			<li><a href="javascript:window.top.dashboard()"><i
					class="fa fa-dashboard"></i>首页</a></li>
			<li><a>邮件管理</a></li>
		</ol>
		<hr>
	</header>
	<div>
		<p style="position: relative; left: 2%;">
			已收和已发邮件(共 <b>${requestScope.total}</b>封)
	</div>

	<main class="container-fluid"> <!--action bar-->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<a type="button" href="welcome.jsp"
				class="btn btn-primary navbar-btn">首页</a> 
			<a type="button"
				href="email/ListSentEmailServlet.do"
				class="btn btn-primary navbar-btn">发件箱</a> 
			<a type="button"
				href="email/ListReceivedEmailServlet.do"
				class="btn btn-primary navbar-btn">收件箱</a> 
			<a type="button"
				href="email/SelectsDraftBinsServlet.do"
				class="btn btn-primary navbar-btn">草稿箱</a> 
			<a type="button"
				href="searchRecycleBinServlet.do"
				class="btn btn-primary navbar-btn">废件箱</a> 
			<a type="button" href="jsp/message/email/outbox/writeEmail.jsp"
				class="btn btn-primary navbar-btn">新建邮件</a>
			<input type="button" onclick="del()"
				class="btn btn-primary navbar-btn" value="删除" />
			<form id="qryForm" action="email/ListSentAndReceivedEmailServlet.do"
				method="post" class="navbar-form navbar-right">
				<input type="hidden" name="pageNo" value="1">
				<div class="form-group">
					<input type="text" name="key" value="${param.key}"
						class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Go</button>
			</form>
		</div>
	</nav>
	<section class="box">
		<div class="box-body">
			<table class="table table-bordered">
				<tbody>
					<tr>
						<th width="10">选择</th>
						<th width="150">主题</th>
						<th width="150">发件人</th>
						<th width="150">收件人</th>
						<th width="150">发送时间</th>
						<th width="10"></th>
						<th width="10"></th>
					</tr>
					<!-- 把requestScope中的result对象放入到pageScope -->
					<c:set var="result" value="${requestScope.result}" scope="page"></c:set>
					<c:if test="${result.found}">
						<c:forEach var="r" items="${result.rows}" varStatus="vs">
							<tr>
								<td><input type="checkbox" name="checkedId" value="${r.id}"></td>
								<td>${r.title}</td>
								<td>${r.sender.name}</td>
								<td>${r.recipient.name}</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
										value="${r.sendTime}" /></td>
								<td><c:set var="user" value="${requestScope.user}"
										scope="page"></c:set> 
									<c:if test="${r.recipient.name eq user}">
										<c:choose>
											<c:when test="${r.checkedByRecipient==true}">
												已读
											</c:when>
											<c:when test="${r.checkedByRecipient==false}">
												未读
											</c:when>
										</c:choose>
									</c:if></td>
								<td><a href="inRecycleBinServlet.do?id=${r.id}&pageId=1">删除</a></td>
							</tr>
						</c:forEach>

					</c:if>
					<c:if test="${result.notFound}">
						<tr>
							<td colspan="99" align="center">很抱歉，未找到相关数据！</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		<!-- /.box-body -->
		<c:if test="${result.found}">
			<jsp:include page="/WEB-INF/jspf/pagination.jsp">
				<jsp:param name="resultVarName" value="result" />
				<jsp:param name="pageClickHandler" value="gotoPage" />
			</jsp:include>
		</c:if>
	</section>

	</main>
	<script>
		var gotoPage = function(no) {
			var qryForm = document.getElementById("qryForm");
			qryForm.pageNo.value = String(no);
			qryForm.submit();
		};
	</script>
	
	<script>				
		function del(){
			
			var s = $("input[name='checkedId']");		
			var v =[];
			s.each(function(i) {
				if (this.checked) {
					v.push(this.value);
				}						
			});
			if(v.length){
				window.location.href = "email/DeleteCheckedEmailServlet.do?flag=1&id=" + v.join(",");
			}
		}
	</script>
</body>

</html>