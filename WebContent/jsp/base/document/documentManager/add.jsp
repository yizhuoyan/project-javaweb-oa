<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<%@include file="/WEB-INF/jspf/head.jsp"%>
</head>

<body>
	<header class="content-header">
		<h1>新建文档</h1>
		<ol class="breadcrumb">
			<li><a href="javascript:window.top.dashboard()"><i
					class="fa fa-dashboard"></i>首页</a></li>
			<li><a href="jsp/base/document/documentManager/list.jsp">文档管理</a>
			</li>
			<li><a>新建文档</a></li>
		</ol>
		<hr>
	</header>

	<main class="container-fluid"> <c:if test="${not empty message}">
		<div class="alert alert-info " user="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			${message}
		</div>
	</c:if> <!-- form start -->
	<form user="form" action="documentManage/add.ajax" method="post"
		class="box">
		<div class="box-body">

			<p class="form-group">
				<label for="nameEL">*文档名称：</label> <input type="text" name="name"
					maxlength="32" class="form-control" id="nameEL"
					placeholder="不超过32个字符">
			</p>
			<p class="form-group">
				<label for="nameEL">*文档属性说明：</label> <input type="text"
					name="property" maxlength="32" class="form-control" id="propertyEL"
					placeholder="不超过32个字符">
			</p>
			<p class="form-group">
				<label for="nameEL">*备注：</label> <input type="text" name="remark"
					maxlength="32" class="form-control" id="ramarkEL"
					placeholder="不超过32个字符">
			</p>
		</div>
		<!-- /.box-body -->

		<div class="box-footer">
			<button type="submit" class="btn btn-lg btn-primary">保存</button>
			<a type="button" class="btn btn-info"
				href="jsp/base/document/documentManager/list.jsp">返回</a>
		</div>
	</form>

	</main>
	<script type="text/javascript">
		$("form").submit(function() {
			//防止重复提交
			var submitBtn = $("button[type='submit']", this)[0];
			submitBtn.disabled = true;
			$.post(this.action, $(this).serializeArray(), function(resp) {
				if (resp.code === "ok") {
					if (!confirm("新增成功，是否继续添加？")) {
						//window.location.href = "empmanage/list.do";
					}
				} else {
					alert(resp.message);
				}
				submitBtn.disabled = false;
			});
			return false;
		});
	</script>
	<script src="js/plugins/jquery.min.js"></script>
	<script src="js/plugins/bootstrap.min.js"></script>
</body>

</html>