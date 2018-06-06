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
        			新增系统功能模块
      			</h1>
      			
			<ol class="breadcrumb">
				<li>
					<a href="javascript:window.top.dashboard()"><i class="fa fa-dashboard"></i>首页</a>
				</li>
				<li>
					<a href="jsp/base/modulemanage/list.jsp">系统功能模块管理</a>
				</li>
				<li>
					<a>新增</a>
				</li>
			</ol>
			<hr>
		</header>
		
		<main class="container-fluid"  >
			<div id="message-container" class="hide alert alert-info " role="alert">
				<strong id="messageEL"></strong>					
			</div>
			<form  class="box" action="sysmodule/add.ajax" method="post" >
				<div class="box-body">
					<p class="form-group">
						<label for="parentIdEL">*父模块：</label>
						<select id="root-parents"  name="parentId" class="form-control" >
							
						</select>
					</p>
					<p class="form-group">
						<label for="codeEL">*代号：</label>
						<input type="text" name="code" maxlength="32"  class="form-control" id="codeEL" placeholder="3-32个字符，支持字母，数字，中横线">
					</p>
					<p class="form-group">
						<label for="nameEL">*名称：</label>
						<input type="text" name="name" maxlength="32"  class="form-control" id="nameEL" placeholder="不超过32个字符">
					</p>
					<p class="form-group">
						<label for="urlEL">URL：</label>
						<input type="text" name="url" maxlength="128"  class="form-control" id="urlEL" placeholder="不超过32个字符">
					</p>
					<p class="form-group">
						<label for="showOrderEL">显示排序号：</label>
						<input type="text" name="showOrder" maxlength="32"  class="form-control" id="showOrderEL" placeholder="不超过32个字符">
					</p>
					<p class="form-group">
						<label for="remarkEL">描述：</label>
						<textarea name="remark" maxlength="512" class="form-control" id="remarkEL" 　rows="5"></textarea>
					</p>
				</div>
				<div class="box-footer">
					<button type="submit" class="btn btn-lg btn-primary">保存</button>
					<a type="button" class="btn btn-info" href="jsp/base/modulemanage/list.jsp">返回</a>
				</div>
			</form>
		</main>
		<script>
			$(function(){
				loadRootModule();
			});
		</script>
		<script>
			//加载父模块
			var loadRootModule=function(){
				var url="sysmodule/listRoot.ajax";
				$.getJSON(url,function(resp){
					if(resp.code==="ok"){
						var data=resp.data;
						var $selectEL=$("#root-parents");
						
						$selectEL.empty();
						
						$("<option/>",{
							text:"顶层模块(null)",
							value:""
						}).appendTo($selectEL);
						
						$.each(data,function(i,item){
							$("<option/>",{
								text:item.name+"("+item.code+")",
								value:item.id
							}).appendTo($selectEL);
						});
					}else{
						$("#messageEL").text(resp.message);
						$("#message-container").show();
					}
				});
			};
		
		$("form").submit(function(){
			//防止重复提交
			var submitBtn=$("button[type='submit']",this)[0];
			submitBtn.disabled=true;
			$.post(this.action,$(this).serializeArray(),function(resp){
				if(resp.code==="ok"){
					if(confirm("新增成功，是否继续添加？")){
						
					}else{
						window.location.href="jsp/base/modulemanage/list.jsp";
					}
				}else{
					$("#messageEL").text(resp.message);
					$("#message-container").show();
				}
				submitBtn.disabled=false;
			});
			return false;
		});
		</script>
	</body>

</html>