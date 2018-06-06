<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jspf/head.jsp"%>
</head>

<body>
	<header class="content-header">
		<h1>新增员工</h1>
		<ol class="breadcrumb">
			<li><a href="javascript:window.top.dashboard()"><i
					class="fa fa-dashboard"></i>首页</a></li>
			<li><a href="sysuser/list.do">员工管理</a></li>
			<li><a>新增</a></li>
		</ol>
		<hr>
	</header>
	<main class="container-fluid">
	<form  action="empmanage/add.ajax" method="post" class="box">
		<div class="box-body">
			<p class="form-group">
				<label for="departmentEL">*所属部门：</label>
				 <select class="form-control" id="departmentEL" 
					name="departmentId">
					<option value="1">--请选择--</option>

				</select>
				<script type="text/javascript">
							
						</script>
			</p>
			<p class="form-group">
				<label for="hiredateEL">*入职时间：</label> <input type="datetime-local"
					name="hiredate" class="form-control" id="hiredateEL">
			</p>

			<p class="form-group">
				<label for="accountEL">*账户：</label> <input type="text"
					name="account" maxlength="32" class="form-control" id="accountEL"
					placeholder="3-32个字符，支持字母，数字，中横线">
			</p>

			<p class="form-group">
				<label for="nameEL">*名称：</label> <input type="text" name="name"
					maxlength="32" class="form-control" id="nameEL"
					placeholder="不超过32个字符">
			</p>
			<p class="form-group">
				<label for="idcardEL">*身份证号：</label> <input type="text"
					name="idcard" maxlength="18" class="form-control" id="idcardEL">
			</p>
			<p class="form-group">
				<label for="nationalEL">*民族：</label> <input type="text"
					name="national" maxlength="5" class="form-control" id="nationalEL"
					value="汉族">
			</p>
			<p class="form-group">
				<label for="nativePlaceEL">*籍贯：</label> 
					 <input class="form-control" 
					id='nativePlaceEL' name="nativePlace">
			</p>
			<p class="form-group">
				<label for="domicilePlaceEL">*户口所在地：</label>
				 <input class="form-control" 
					id='domicilePlaceEL' name="domicilePlace">
			</p>
			<p class="form-group">
				<label for="politicalStatusEL">*政治面貌：</label>
				
				 <select class="form-control" 
					id="politicalStatusEL" name="politicalStatus">
					<option>01中共党员</option>
					<option>02中共预备党员 </option>
					<option>03共青团员</option>
					<option>04民革党员 </option>
					<option>05民盟盟员</option>
					<option>06民建会员</option>
					<option>07民进会员 </option>
					<option>08农工党党员</option>
					<option>09致公党党员 </option>
					<option>10九三学社社员</option>
					<option>11台盟盟员 </option>
					<option>12无党派人士</option>
					<option>13群众</option>
				</select>
			</p>
			<p class="form-group">
				<label for="marriageStateEL">*婚姻状况：</label>
				<select class="form-control"  id="marriageStateEL" name="marriageState">
					<option value="0">未婚</option>
					<option value="2">已婚</option>
					<option value="1">离异</option>
					<option value="9">丧偶</option>
				</select>
			</p>
			<p class="form-group">
				<label for="workphoneEL">*办公电话：</label> 
				<input type="text" name="workPhone"
					maxlength="32" class="form-control"
					 id="workphoneEL"
					placeholder="不超过32个字符">
			</p>
			<p class="form-group">
				<label for="homePhoneEL">*家庭电话：</label>
				 <input type="text" name="homePhone"
					maxlength="32" class="form-control" 
					id="homePhoneEL"
					placeholder="不超过32个字符">
			</p>
			<p class="form-group">
				<label for="emailEL">*邮箱：</label>
				 <input type="email" name="email"
					class="form-control" id="emailEL"
					placeholder="不超过32个字符">
			</p>
			<p class="form-group">
				<label for="addressEL">*住址：</label> 
				<input type="text" name="address"
					maxlength="32" class="form-control" 
					id="addressEL"
					placeholder="不超过32个字符">
			</p>

			<p class="form-group">
				<label for="remarkEL">描述：</label>
				<textarea name="remark" maxlength="512" class="form-control"
					id="remarkEL" 　rows="5"></textarea>
			</p>
		</div>
		<!-- /.box-body -->

		<div class="box-footer">
			<button type="submit" class="btn btn-lg btn-primary">保存</button>
			<a type="button" class="btn btn-info" href="sysuser/list.do">返回</a>
		</div>
	</form>

	</main>
	<script>
			$(function(){
				loadDepartments();
			});
		</script>
	<script>
			//加载部门
			var loadDepartments=function(){
				var url="deptmanage/list.ajax";
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
						toast(resp.message);
					}
				});
			};
			
		
		$("form").submit(function(){
			//防止重复提交
			var submitBtn=$("button[type='submit']",this)[0];
			submitBtn.disabled=true;
			$.post(this.action,$(this).serializeArray(),function(resp){
				if(resp.code==="ok"){
					if(!confirm("新增成功，是否继续添加？")){
						window.location.href="empmanage/list.do";
					}
				}else{
					toast(resp.message);
				}
				submitBtn.disabled=false;
			});
			return false;
		});
		</script>
</body>

</html>