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
		<h1>邮件草稿箱</h1>
		<ol class="breadcrumb">
			<li>
			<a href="javascript:window.top.dashboard()"><i class="fa fa-dashboard"></i>首页</a>
			</li>
			<li><a>邮件草稿箱</a></li>
		</ol>
		<hr>
	</header>

	<main class="container-fluid"> <!--action bar-->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
		<a type="button" href="welcome.jsp"
				class="btn btn-primary navbar-btn">首页</a>
		<input type="button" class="btn btn-primary navbar-btn" 
				onclick="del()" value="删除"/> 
		<a type="button" href="email/ListSentAndReceivedEmailServlet.do"
				class="btn btn-primary navbar-btn">返回</a>
			<form id="qryForm" action="email/SelectsDraftBinsServlet.do" method="post"
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
						<th width="150">主题</th>
						<th width="150">内容</th>
						<th width="150">收件人</th>
						<th width="150"></th>
						<th width="150"></th>
					</tr>
					<!-- 把requestScope中的result对象放入到pageScope -->
					<c:set var="result" value="${requestScope.result}" scope="page"></c:set>
						<c:forEach var="r" items="${requestScope.result}" varStatus="vs">
						    <c:if test="${r.draftBin==true}">
						    <tr>
						    	<td><input type="checkbox" name="checkedId" value="${r.id}"></td>
								<td>${r.title}</td>
								<td>${r.content}</td>
								<td>${r.recipient.name}</td>
								<td><a href="inRecycleBinServlet.do?id=${r.id}&pageId=4">删除</a></td>
								<td><a href="selectSendEmail.do?id=${r.id}">发送邮件</a></td>
							</tr> 
							</c:if>
						</c:forEach>		     
				</tbody>
				
			</table>
			
		</div>
		     <footer class="box-footer clearfix">
					<div class="col-xs-6" style="text-align: right">
						<c:if test="${requestScope.pageNo!=1}">
							<a href="email/SelectsDraftBinsServlet.do">首页</a>
							<a href="email/SelectsDraftBinsServlet.do?pageNo=${requestScope.pageNo-1}">上一页</a>
						</c:if>
						<c:if test="${requestScope.pageNo==1}">
							<span>首页</span>
							<span>上一页</span>
						</c:if>
						共<b>${requestScope.total}</b>条
						第<b>${requestScope.pageNo}</b>页
						<c:if test="${requestScope.pageNo!=requestScope.totalPages}">
							<a href="email/SelectsDraftBinsServlet.do?pageNo=${requestScope.pageNo+1}">下一页</a>
							<a href="email/SelectsDraftBinsServlet.do?pageNo=${requestScope.totalPages}">末页</a>
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
				window.location.href = "email/DeleteCheckedEmailServlet.do?flag=4&id=" + v.join(",");
			}
		}
	</script>
</body>

</html>
