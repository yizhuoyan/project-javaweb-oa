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
		<h1>邮件查询</h1>
		<ol class="breadcrumb">
			<li><a href="javascript:window.top.dashboard()"><i
					class="fa fa-dashboard"></i>首页</a></li>
			<li><a>邮件查询</a></li>
		</ol>
		<hr>
	</header>

	<main class="container-fluid"> <!--action bar-->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<a type="button" href="welcome.jsp"
				class="btn btn-primary navbar-btn">首页</a> 
			<a type="button" href="welcome.jsp"
				class="btn btn-primary navbar-btn">返回</a>
			<form id="qryForm" action="email/ListAllEmailServlet.do" method="post"
				class="navbar-form navbar-right">
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
						<th width="150">主题</th>
						<th width="80">发件人</th>
						<th width="80">收件人</th>
						<th width="100">发送时间</th>
						<th width="60">查看状态</th>
						<th width="150">删除状态</th>
					</tr>
					<!-- 把requestScope中的result对象放入到pageScope -->
					<c:set var="result" value="${requestScope.result}" scope="page"></c:set>
					<c:if test="${result.found}">
						<c:forEach var="r" items="${result.rows}" varStatus="vs">
							<tr>
								<td>${r.title}</td>
								<td>${r.sender.name}</td>
								<td>${r.recipient.name}</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
										value="${r.sendTime}" /></td>
								<td>
									<c:if test="${r.checkedByRecipient==true}">已读</c:if> 
									<c:if test="${r.checkedByRecipient==false}">未读</c:if>
								</td>
								<td>
									<c:choose>
										<c:when test="${r.deletedBySender==true}">
											<c:choose>
												<c:when test="${r.deletedByRecipient==true}">
													发件人和收件人都已将该邮件放入回收站
												</c:when>
												<c:otherwise>已被发件人放入回收站</c:otherwise>
											</c:choose>										
										</c:when>
										<c:when test="${r.deletedByRecipient==true}">
											<c:choose>
												<c:when test="${r.deletedBySender==true}">
													发件人和收件人都已将该邮件放入回收站
												</c:when>
												<c:otherwise>已被收件人放入回收站</c:otherwise>
											</c:choose>	
										</c:when>
										<c:when test="${r.completelyDeletedBySender==true}">
											<c:choose>
												<c:when test="${r.completelyDeletedByRecipient==true}">
													发件人和收件人都已将该邮件彻底删除
												</c:when>
												<c:otherwise>已被发件人彻底删除</c:otherwise>
											</c:choose>
										</c:when>
										<c:when test="${r.completelyDeletedByRecipient==true}">
											<c:choose>
												<c:when test="${r.completelyDeletedBySender==true}">
													发件人和收件人都已将该邮件彻底删除
												</c:when>
												<c:otherwise>已被收件人彻底删除</c:otherwise>
											</c:choose>
										</c:when>
									</c:choose>
								</td>
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
</body>

</html>