<%@page pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		 <%@include file="/WEB-INF/jspf/head.jsp"%>
	</head>

	<body>
		<header class="content-header">
			<h1>
        			系统角色管理
      			</h1>
			<ol class="breadcrumb">
				<li>
					<a href="javascript:window.top.dashboard()"><i class="fa fa-dashboard"></i>首页</a>
				</li>
				<li>
					<a>系统角色</a>
				</li>
			</ol>
			<hr>
		</header>
		
		<main class="container-fluid">
			<!--action bar-->
			<nav class="navbar navbar-default">
				<div class="container-fluid">
						<a type="button" href="sysrole/add.do" class="btn btn-primary navbar-btn">新增</a>
						<form action="sysrole/list.do" method="post" class="navbar-form navbar-right">
							<div class="form-group">
								<input type="text" name="key" value="${param.key}" class="form-control" placeholder="Search">
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
								<th width="10">#</th>
								<th width="100">操作</th>
								<th width="100">代号</th>
								<th width="150">名称</th>
								<th>备注</th>
							</tr>
							<c:if test="${requestScope.result.found}">
								<c:forEach var="r" items="${requestScope.result.rows}" varStatus="vs">
								<tr>
									<td>${vs.count}.</td>
									<td>
										<a href="sysrole/check.do?id=${r.id}">查看/修改</a>
									</td>
									<td>${r.code}</td>
									<td>${r.name}</td>
									<td>${r.remark}</td>
								</tr>
								</c:forEach>
							</c:if>
							<c:if test="${requestScope.result.notFound}">
								<tr>
									<td colspan="99">
										很抱歉，未找到相关数据！
									</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
				<!-- /.box-body -->
				<footer class="box-footer clearfix">
					<div>
						共<b>${requestScope.result.totalRows}</b>条数据
					</div>
				</footer>
			</section>
		</main>
	</body>
</html>