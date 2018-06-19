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
		<h1>日志管理</h1>
		<ol class="breadcrumb">
			<li><a href="javascript:window.top.dashboard()"><i
					class="fa fa-dashboard"></i>首页</a></li>
			<li><a>文档管理</a></li>
			<li><a>日志管理</a></li>
		</ol>
		<hr>
	</header>

	<main class="container-fluid"> <!--action bar-->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<form id="qryForm" action="logDocument.do" method="post"
				class="navbar-form navbar-right">
				<input type="hidden" name="pageNo" value="1">
				<div class="form-group">
					&nbsp;&nbsp;&nbsp;名称：<input type="text" name="target" value=""
						class="form-control" placeholder="名称">
					&nbsp;&nbsp;&nbsp;操作类型：<select name="operation"
						class="form-control">
						<option value="">--请选择--</option>
						<option value="1">放入回收站</option>
						<option value="2">创建</option>
						<option value="3">下载</option>
						<option value="4">还原</option>
						<option value="5">彻底删除</option>
					</select> &nbsp;&nbsp;&nbsp;<input type="date" name="beginoperationTime"
						value="" class="form-control" placeholder="开始时间">到 <input
						type="date" name="endoperationTime" value="" class="form-control"
						placeholder="结束时间"> &nbsp;&nbsp;&nbsp;操作者：<input
						type="text" name="operatorId" value="" class="form-control"
						placeholder="操作者">

				</div>
				<button type="submit" class="btn btn-default">Go</button>
			</form>
		</div>
	</nav>
	<section class="box">
		<div class="box-body">
			<table class="table table-bordered">
				<tbody>
					<tr style="">
						<th width="40">序号</th>
						<th width="150">名称</th>
						<th width="100">操作</th>
						<th width="150">操作时间</th>
						<th width="100">操作者</th>
						<th width="100">概述</th>

					</tr>
					<!-- 把requestScope中的result对象放入到pageScope -->
					<c:set var="result" value="${requestScope.result}" scope="page"></c:set>
					<c:if test="${result.found}">
						<c:forEach var="r" items="${result.rows}" varStatus="vs">
							<tr>
								<td>${vs.count}</td>
								<td>${r.target.name}</td>
								<c:choose>
									<c:when test="${r.operation==1}">
										<td>放入回收站</td>
									</c:when>
									<c:when test="${r.operation==2}">
										<td>创建</td>
									</c:when>
									<c:when test="${r.operation==3}">
										<td>下载</td>
									</c:when>
									<c:when test="${r.operation==4}">
										<td>还原</td>
									</c:when>
									<c:when test="${r.operation==5}">
										<td>彻底删除</td>
									</c:when>
								</c:choose>

								<td>${r.operationTime}</td>
								<td>${r.operator.name}</td>
								<td>${r.content}</td>
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
		<footer class="box-footer">
			<div class="row">
				<div class="col-xs-6">
					<span> 共<b>${r.totalRows}</b>条
					</span> <span> 每页<b>${r.pageSize}</b>条
					</span> <span> 共<b>${r.totalPages}</b>页
					</span>
				</div>

				<div class="col-xs-6" style="text-align: right">
					<c:if test="${r.pageNo!=1}">
						<a href="javascript:_(1)">首页</a>
						<a href="javascript:_(${r.pageNo-1})">上一页</a>
					</c:if>
					<c:if test="${r.pageNo==1}">
						<span>首页</span>
						<span>上一页</span>
					</c:if>
					<b>${r.pageNo}</b>
					<c:if test="${r.pageNo!=r.totalPages}">
						<a href="javascript:_(${r.pageNo+1})">下一页</a>
						<a href="javascript:_(${r.totalPages})">末页</a>
					</c:if>
					<c:if test="${r.pageNo==r.totalPages}">
						<span>下一页</span>
						<span>末页</span>
					</c:if>
				</div>
			</div>
			<script>
				function _(no) {
					$
					{
						param.pageClickHandler
					}
					(no);
				}
			</script>
		</footer>
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