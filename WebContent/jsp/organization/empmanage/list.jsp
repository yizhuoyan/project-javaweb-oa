<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
 <%@include  file="/WEB-INF/jspf/head.jsp"%>
</head>

<body>
	<header class="content-header">
		<h1>员工管理</h1>
		<ol class="breadcrumb">
			<li>
			<a href="javascript:window.top.dashboard()"><i class="fa fa-dashboard"></i>首页</a>
			</li>
			<li><a>员工管理</a></li>
		</ol>
		<hr>
	</header>

	<main class="container-fluid"> 
	<nav class="navbar navbar-default">
		<div class="container-fluid">
		
			<a type="button" href="jsp/organization/empmanage/add.jsp"
				class="btn btn-primary navbar-btn">新增
			</a>
			
			<form id="qryForm" action="empmanage/list.do" method="post"
				class="navbar-form navbar-right">
				<input type="hidden" name="pageNo" value="1">
				<input type="hidden" name="pageSize" value="10">
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
						<th width="10">#</th>
						<th width="100">操作</th>
						<th width="150">工号(账号)</th>
						<th width="150">名称</th>
						<th width="100">性别/年龄</th>
						<th width="150">工作电话/邮箱</th>
						<th>备注</th>
					</tr>
					<!-- 把requestScope中的result对象放入到pageScope -->
					<c:set var="result" value="${requestScope.result}" scope="page"></c:set>
					<c:if test="${result.found}">
						<c:forEach var="r" items="${result.rows}" varStatus="vs">
							<tr>
								<td>${vs.count}.</td>
								<td><a href="jsp/organization/empmanage/check.jsp?${r.id}">查看/修改</a></td>
								<td>${r.account}</td>
								<td>${r.name}</td>
								<td>${r.male?"男":"女"}/${r.age}</td>
								<td>${r.workPhone}/${r.workEmail}</td>
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
		<c:if test="${result.found}">
			<jsp:include page="/WEB-INF/jspf/pagination.jsp" >
				<jsp:param name="resultVarName" value="result"/>
				<jsp:param name="pageClickHandler" value="gotoPage"/>
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