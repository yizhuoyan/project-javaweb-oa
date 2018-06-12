<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<%@include file="/WEB-INF/jspf/head.jsp"%>
<style>
</style>

</head>

<body>
	<header class="content-header">
		<h1>回收站</h1>
		<ol class="breadcrumb">
			<li><a href="javascript:window.top.dashboard()"><i
					class="fa fa-dashboard"></i>文档管理</a></li>
			<li><a>回收站</a></li>
		</ol>
		<hr>
	</header>

	<main class="container-fluid"> <!--action bar-->
	<nav class="navbar navbar-default">

		<div class="container-fluid">
		
			<a type="button" 
				href="document/recycle/emptyrecycle.do"
				class="btn btn-primary navbar-btn">清空回收站</a>
		
			<form id="qryForm" action="document/recycle/list.do" method="post"
				class="navbar-form navbar-right">
				<input type="hidden" name="pageNo" value="1">
				<div class="form-group">
					<input type="text" name="key" value="${param.key}"
						class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Go</button>
			</form>
			<c:if test="${not empty message}">
			<div class="alert alert-success " role="alert" >
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
				</button> 
				${message}					
			</div>
			</c:if>
		</div>
	</nav>
	<section class="box">
		<div class="box-body">
			<table class="table table-bordered">
				<tbody>
					<tr>
						<th width="10">序号</th>
						<th width="100">名称</th>
						<th width="100">所属文档</th>
						<th width="150">位置</th>
						<th width="180">创建时间</th>
						<th width="100">创建者</th>
						<th width="100">部门</th>
						<th width="100">操作</th>
						<th width="100">备注</th>
					</tr>
					
						<!-- 把requestScope中的docResult对象放入到pageScope -->
					<c:set var="docResult" value="${requestScope.docResult}" scope="page"></c:set>
					<c:if test="${docResult.found}">
						<c:forEach var="docR" items="${docResult.rows}" varStatus="vs">
							
							
							<tr>
								<td>${vs.count}.</td>
								<td>${docR.name}</td>
								<td>/全部文档</td>
								<td>${docR.path}</td>
								<td>${docR.createTime}</td>
								<td>${docR.createUserId.getId()}</td>
								<td>${docR.deptId}</td>
								<td>
									<a href="document/recycle/documentmod.do?id=${docR.id}">还原</a> 
									<a href="document/recycle/documentdelete.do?id=${docR.id}">删除</a>
								</td>
								<td>${docR.remark}</td>
							</tr>
						</c:forEach>
					</c:if>
					
					
					<!-- 把requestScope中的atmResult对象放入到pageScope -->
					<c:set var="atmResult" value="${requestScope.atmResult}" scope="page"></c:set>
					<c:if test="${atmResult.found}">
						<c:forEach var="atmR" items="${atmResult.rows}" varStatus="vs">
							<tr>
								<td>${vs.count}.</td>
								<td>${atmR.name}</td>
								<td>${atmR.documentId.getId()}</td>
								<td>${atmR.path}</td>
								<td>${atmR.createTime}</td>
								<td>${atmR.createUserId.getId()}</td>
								<td>${atmR.deptId}</td>
								
									<td>
										<a href="document/recycle/attachmentmod.do?id=${atmR.id}">还原</a> 
										<a href="document/recycle/attachmentdelete.do?id=${atmR.id}">删除</a>
									</td>
					
								<td>${atmR.remark}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${atmResult.notFound && docResult.notFound}">
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