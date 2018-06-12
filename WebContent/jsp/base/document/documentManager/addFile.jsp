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
        			添加附件
      			</h1>
			<ol class="breadcrumb">
				<li>
					<a href="javascript:window.top.dashboard()"><i class="fa fa-dashboard"></i>首页</a>
				</li>
				<li>
					<a href="jsp/base/document/documentManager/list.jsp">文档管理</a>
				</li>
				<li>
					<a>添加附件</a>
				</li>
			</ol>
			<hr>
		</header>
		
		<main class="container-fluid">
			<c:if test="${not empty message}">
			<div class="alert alert-info " user="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
				</button> 
				${message}					
			</div>
			</c:if>
			<!-- form start -->
			<form user="form" action="attachmentManage/add.do" method="post" class="box" enctype="multipart/form-data">
				
				<div class="box-body">
					<p class="form-group">
						<label for="nameEL">*附件：</label>
						<input type="file" name="file1" >
					</p>
					<input name="documentId" type="hidden" value="${param.documentId}" >
					<p class="form-group">
						<label for="nameEL">*备注：</label>
						<input type="text" name="remark" maxlength="32" class="form-control" id="remarkEL" placeholder="不超过32个字符">
					</p>
					<p class="form-group">
						<label for="nameEL">*属性说明：</label>
						<input type="text" name="property" maxlength="32" class="form-control" id="propertyEL" placeholder="不超过32个字符">
					</p>
					
				</div>
				<!-- /.box-body -->
				<div class="box-footer">
					<button type="submit" class="btn btn-lg btn-primary">保存</button>
					<a type="button" class="btn btn-info" href="jsp/base/document/documentManager/list.jsp">返回</a>
				</div>
			</form>

		</main>
		<footer>

		</footer>
		<script src="js/plugins/jquery.min.js"></script>
		<script src="js/plugins/bootstrap.min.js"></script>
	</body>

</html>