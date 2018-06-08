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
				href="http://127.0.0.1:8080/oa/jsp/base/document/recyclemanage/list.jsp"
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
					<!-- 把requestScope中的result对象放入到pageScope -->
					<c:set var="result" value="${requestScope.result}" scope="page"></c:set>
					<c:if test="${result.found}">
						<c:forEach var="r" items="${result.rows}" varStatus="vs">
							<tr>
								<td>${vs.count}.</td>
								<td>${r.name}</td>
								<td>${r.documentId}</td>
								<td>${r.path}</td>
								<td>${r.createTime}</td>
								<td>${r.createUserId}</td>
								<td>${r.deptId}</td>
								<td>
									<a href="document/recycle/attachmentmod.do?id=${r.id}">还原</a> 
									<a href="document/recycle/attachmentdelete.ajax?id=${r.id}">删除</a>
								</td>
								<td>${r.remark}</td>

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
		<a>213123213</a>
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
			toast.show("已还原");
			qryForm.submit();
		};
	</script>
</body>

</html>