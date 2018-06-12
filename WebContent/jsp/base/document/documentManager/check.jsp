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
        			修改/删除
      			</h1>
			<ol class="breadcrumb">
				<li>
					<a href="javascript:window.top.dashboard()"><i class="fa fa-dashboard"></i>首页</a>
				</li>
				<li>
					<a href="jsp/base/document/documentManager/list.jsp">文档管理</a>
				</li>
				<li>
					<a>修改/删除</a>
				</li>
			</ol>
			<hr>
		</header>
		
		<main class="container-fluid">
			<div class="alert alert-info " id="message-box" style="display: none">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
				</button> 
				<strong id="messageEL"> </strong>				
			</div>
			<!-- form start -->
			<form  action="documentManage/mod.ajax" method="post" class="box">
				<input type="hidden" name="id" id="nameEL" value="${vo==null?param.id:vo.id}">
				<div class="box-body">
					<p class="form-group">
						<label for="nameEL">*文档名称：</label>
						<input type="text" name="name" value="${vo.name}" maxlength="32"  class="form-control" id="nameEL" placeholder="不超过32个字符">
					</p>
					<p class="form-group">
						<label for="nameEL">*文档属性说明：</label>
						<input type="text" name="property" value="${vo.property}" maxlength="32"  class="form-control" id="propertyEL" placeholder="不超过32个字符">
					</p>
					<p class="form-group">
						<label for="nameEL">*文档位置：</label>
						<input type="text" name="path" value="${vo.path}" maxlength="32" class="form-control" id="pathEL" readonly>
					</p>
					
					<p class="form-group">
						<label for="nameEL">*文档创建者：</label>
						<input type="text" name="createUserId" value="${vo.createUserId.id}" maxlength="32"  class="form-control" id="createUserIdEL" readonly>
					</p>
					<p class="form-group">
						<label for="nameEL">*文档创建时间：</label>
						<input type="text" name="createTime" value="${vo.createTime}"  maxlength="32"  class="form-control" id="createTimeEL" readonly>
					</p>
					<p class="form-group">
						<label for="nameEL">*备注：</label>
						<input type="text" name="remark" value="${vo.remark}"  maxlength="32" class="form-control" id="remarkEL" placeholder="不超过32个字符">
					</p>
					<p class="form-group">
						<label for="nameEL">*文档状态：</label>
						<input type="text" name="flag" value="${vo.flag}" maxlength="32"  class="form-control" id="flagEL" readonly>
					</p>
					<p class="form-group">
						<label for="nameEL">*所属部门：</label>
						<input type="text" name="deptId" value="${vo.deptId.id}" maxlength="32" class="form-control" id="deptIdEL" readonly>
					</p>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<button type="submit" class="btn btn-lg btn-primary">修改</button>
					<a type="button" class="btn btn-info" href="documentManage/list.do">返回</a>
					<a type="button" class="btn btn-link" href="javascript:deleteConfirm()" >删除</a>
					
				</div>
			</form>

		</main>
		<script>
			$("form").submit(function(){
				$.post(this.action,$(this).serializeArray(),function(resp){
					$("#message-box").show();
					if(resp.code==="ok"){
						$("#messageEL").html("修改成功，请返回！");	
					}else{
						$("#messageEL").html(resp.message);
					}
				});
				return false;
			});
		</script>
		<script>
			var deleteConfirm=function(){
				if(window.confirm("确认删除？")){
					window.location.href="docuemntManage/del.do?id=${vo==null?param.id:vo.id}"					
				}
			}
		
		</script>
	</body>

</html>