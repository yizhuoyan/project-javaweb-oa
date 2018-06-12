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
		<h1>收件箱</h1>
		<ol class="breadcrumb">
			<li><a href="javascript:window.top.dashboard()"><i
					class="fa fa-dashboard"></i>首页</a></li>
			<li><a>收件箱</a></li>
		</ol>
		<hr>
	</header>
	
	<div>
		<p style="position: relative; left: 2%;">
			收件箱(共 <b>${requestScope.total}</b>封)
	</div>
	
	<main class="container-fluid"> <!--action bar-->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<a type="button" href="welcome.jsp"
				class="btn btn-primary navbar-btn">首页</a> 
			<input type="button" onclick="del()"
				 class="btn btn-primary navbar-btn" value="删除" /> 
			<a type="button" href="email/ListSentAndReceivedEmailServlet.do"
				class="btn btn-primary navbar-btn">返回</a>
			<form id="qryForm" action="email/ListReceivedEmailServlet.do" method="post"
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
						<th width="10">选择</th>
						<th width="150">主题</th>
						<th width="150">发件人</th>
						<th width="150">发送时间</th>
						<th width="10"></th>
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
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
										value="${r.sendTime}" /></td>

								<c:if test="${r.checkedByRecipient==true}">
									<td>已读</td>
								</c:if>
								<c:if test="${r.checkedByRecipient==false}">
									<td>未读</td>
									<td><a href="CheckUnreadEmailServlet.do?id=${r.id}"><button>查看</button></a></td>
								</c:if>

								<td><a href="inRecycleBinServlet.do?id=${r.id}&pageId=2">删除</a></td>
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
				window.location.href = "email/DeleteCheckedEmailServlet.do?flag=2&id="+ v.join(",");
			}
		}
	</script>
</body>

</html>