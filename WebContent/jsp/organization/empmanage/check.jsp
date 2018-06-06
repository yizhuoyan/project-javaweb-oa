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
        			查看/修改系统用户
      			</h1>
			<ol class="breadcrumb">
				<li>
					<a href="javascript:window.top.dashboard()"><i class="fa fa-dashboard"></i>首页</a>
				</li>
				<li>
					<a href="sysuser/list.do">系统用户管理</a>
				</li>
				<li>
					<a>查看/修改</a>
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
			<form  action="sysuser/mod.ajax" method="post" class="box">
				<input type="hidden" name="id" value="${vo==null?param.id:vo.id}">
				<div class="box-body">
					<p class="form-group">
						<label for="accountEL">*账号：</label>
						<input type="text" name="account" maxlength="32" value="${vo==null?param.account:vo.account}" class="form-control" id="accountEL" placeholder="3-32个字符，支持字母，数字，中横线">
					</p>
					<p class="form-group">
						<label for="nameEL">*名称：</label>
						<input type="text" name="name" maxlength="32" value="${vo==null?param.name:vo.name}" class="form-control" id="nameEL" placeholder="不超过32个字符">
					</p>
					
					<p class="form-group">
						<label>最后登陆时间：</label>
						<input type="text"   value="${vo.lastLoginTime}" class="form-control"  readonly>
					</p>
					<p class="form-group">
						<label>最后登陆IP：</label>
						<input type="text"   value="${vo.lastLoginIP}" class="form-control"  readonly>
					</p>
					<p class="form-group">
						<label>最后一次密码修改时间：</label>
						<input type="text"   value="${vo.lastModPasswordTime}" class="form-control"  readonly>
					</p>
					<p class="form-group">
						<label >密保电话：</label>
						<input type="text"   value="${vo.phone}" class="form-control"  readonly>
					</p>
					<p class="form-group">
						<label>密保邮箱：</label>
						<input type="text"   value="${vo.securityEmail}" class="form-control"  readonly>
					</p>
					<p class="form-group">
						<label>状态：</label>
						<span>
							<label><input type="radio" name="flag" value="0" checked>正常</label>
							<label><input type="radio" name="flag" value="1" ${r.flag==1?"checked":"" }>锁定</label>
						</span>
					</p>
					<p class="form-group">
						<label for="remarkEL">描述：</label>
						<textarea name="remark" maxlength="512" class="form-control" id="remarkEL" 　rows="5">${vo==null?param.remark:vo.remark}</textarea>
					</p>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<button type="submit" class="btn btn-lg btn-primary">修改</button>
					<a type="button" class="btn btn-info" href="sysuser/list.do">返回</a>
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
					window.location.href="sysuser/del.do?id=${vo==null?param.id:vo.id}"					
				}
			}
		
		</script>
	</body>

</html>