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
        			新增系统角色
      			</h1>
			<ol class="breadcrumb">
				<li>
					<a href="javascript:window.top.dashboard()"><i class="fa fa-dashboard"></i>首页</a>
				</li>
				<li>
					<a href="sysrole/list.do">系统角色管理</a>
				</li>
				<li>
					<a>新增</a>
				</li>
			</ol>
			<hr>
		</header>
		
		<main class="container-fluid">
			<c:if test="${not empty message}">
			<div class="alert alert-info " role="alert">
				${message}					
			</div>
			</c:if>
			<form role="form" action="sysrole/add.do" method="post" class="box">
				<div class="box-body">
					<p class="form-group">
						<label for="codeEL">*代号：</label>
						<input type="text" name="code" maxlength="32" value="${param.code}" class="form-control" id="codeEL" placeholder="3-32个字符，支持字母，数字，中横线">
					</p>
					<p class="form-group">
						<label for="nameEL">*名称：</label>
						<input type="text" name="name" maxlength="32" value="${param.name}" class="form-control" id="nameEL" placeholder="不超过32个字符">
					</p>
					<p class="form-group">
						<label for="remarkEL">描述：</label>
						<textarea name="remark" maxlength="512" class="form-control" id="remarkEL" 　rows="5">${param.remark}</textarea>
					</p>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<button type="submit" class="btn btn-lg btn-primary">保存</button>
					<a type="button" class="btn btn-info" href="sysrole/list.do">返回</a>
				</div>
			</form>

		</main>
		<footer>

		</footer>
	</body>

</html>