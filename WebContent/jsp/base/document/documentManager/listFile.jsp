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
		<h1>附件查看</h1>
		<ol class="breadcrumb">
			<li><a href="javascript:window.top.dashboard()"><i
					class="fa fa-dashboard"></i>首页</a></li>
			<li><a>附件查看</a></li>
		</ol>
		<hr>
	</header>

	<main class="container-fluid"> <!--action bar-->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<a type="button"
				href="jsp/base/document/documentManager/addFile.jsp?documentId=${requestScope.documentId}"
				class="btn btn-primary navbar-btn">添加附件</a>

		</div>
	</nav>
	<section class="box">
		<div class="box-body">
			<h4>附件所在根目录</h4>
			<table class="table table-bordered">
				<tbody>
					<tr>
						<th width="10">#</th>
						<th width="150">附件名称</th>
						<th width="150">附件所属文档</th>
						<th width="150">附件位置</th>
						<th width="100">附件创建者</th>
						<th width="100">附件创建时间</th>
						<th width="100">附件状态</th>
						<th width="100">操作</th>
						<th>备注</th>
					</tr>


					<!-- 把requestScope中的result对象放入到pageScope -->
					<c:set var="result" value="${requestScope.result}" scope="page"></c:set>
					<c:forEach var="r" items="${result}" varStatus="vs">
						<tr>
							<td>${vs.count}.</td>
							<td>${r.name}</td>
							<td>${r.documentId.name}</td>
							<td>${r.path}</td>
							<td>${r.createUserId.name}</td>
							<td>${r.createTime}</td>
							<td>${r.flag}</td>
							<td><a href="attachmentManage/download.do?id=${r.id}">下载</a>
								<a href="attachmentManage/del.do?id=${r.id}">删除</a></td>
							<td>${r.remark}</td>
						</tr>
					</c:forEach>
					<c:if test="${empty result}">
						<tr>
							<td colspan="99" align="center">很抱歉，当前文档没有附件！</td>
						</tr>
					</c:if>
				</tbody>
			</table>
			<div class="box-footer">

				<a type="button" class="btn btn-info" href="documentManage/list.do">返回</a>
			</div>
		</div>
		<!-- /.box-body -->
	</section>

	</main>
</body>

</html>