<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
 <%@include  file="/WEB-INF/jspf/head.jsp"%>
 <style>
 tr[locked]{
 	background:#eeeeee;
 }
 </style>
 
</head>

<body>
	<header class="content-header">
		<h1>邮件通信录</h1>
		<ol class="breadcrumb">
			<li>
			<a href="javascript:window.top.dashboard()"><i class="fa fa-dashboard"></i>首页</a>
			</li>
			<li><a>邮件通信录</a></li>
		</ol>
		<hr>
	</header>

	<main class="container-fluid"> <!--action bar-->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
		<a type="button" href="jsp/message/email/outbox/writeEmail.jsp"
				class="btn btn-primary navbar-btn">群发</a>
		<a type="button" href="jsp/message/email/outbox/writeEmail.jsp"
				class="btn btn-primary navbar-btn">返回</a>
				
			<form id="qryForm" action="emailAddressBookServlet.do" method="post"
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
						<th width="150">选择</th>
						<th width="150">名字</th>
						<th width="150">邮件箱地址</th>
						<th width="150">所在部门</th>
						<th width="100">发送邮件</th>
					</tr>
					<!-- 把requestScope中的result对象放入到pageScope -->
					<c:set var="result" value="${requestScope.result}" scope="page"></c:set>
						<c:forEach var="r" items="${requestScope.result}" varStatus="vs">
						     <tr> 
						        <td><input type="checkbox"> </td>
								<td>${r.name}</td>
								<td>${r.email}</td>
								<td>${r.department}</td>
								<td><a href="jsp/message/email/outbox/writeEmail.jsp"><button>发送邮件</button></a></td>
							</tr>
						</c:forEach>		     
				</tbody>
				
			</table>
			
		</div>
		     <footer class="box-footer clearfix">
					<div class="col-xs-6" style="text-align: right">
						<c:if test="${requestScope.pageNo!=1}">
							<a href="emailAddressBookServlet.do&key=${requestScope.key}">首页</a>
							<a href="emailAddressBookServlet.do?pageNo=${requestScope.pageNo-1}&key=${requestScope.key}">上一页</a>
						</c:if>
						<c:if test="${requestScope.pageNo==1}">
							<span>首页</span>
							<span>上一页</span>
						</c:if>
						共<b>${requestScope.total}</b>条
						第<b>${requestScope.pageNo}</b>页
						<c:if test="${requestScope.pageNo!=requestScope.totalPages}">
							<a href="emailAddressBookServlet.do?pageNo=${requestScope.pageNo+1}&key=${requestScope.key}">下一页</a>
							<a href="emailAddressBookServlet.do?pageNo=${requestScope.totalPages}&key=${requestScope.key}">末页</a>
						</c:if>
						<c:if test="${requestScope.pageNo==requestScope.totalPages}">
							<span>下一页</span>
							<span>末页</span>
						</c:if>
					</div>
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