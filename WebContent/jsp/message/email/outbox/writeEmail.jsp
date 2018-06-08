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
		<h1>写邮件</h1>
		<ol class="breadcrumb">
			<li>
			<a href="javascript:window.top.dashboard()"><i class="fa fa-dashboard"></i>首页</a>
			</li>
			<li><a>写邮件</a></li>
		</ol>
		<hr>
	</header>

	<a style="position:relative;left:3%" href="emailAddressBookServlet.do"><button type="button" class="btn btn-primary">邮件通讯录</button></a>
	<a style="position:relative;left:3%" href="jsp/message/email/outbox/outbox.jsp"><button type="button" class="btn btn-primary">返回</button></a>
	
	<main class="container-fluid">
		<center>
		<div style="padding: 10px 10px 10px;">
			<form class="bs-example bs-example-form"  name="form" action="addEmail.ajax">
				<div class="input-group">
					<span class="input-group-addon" >收件人</span>
					<input type="text" class="form-control" placeholder="Receiver" name="recipient">
				</div>
				<br>
				<div class="input-group">
					<span class="input-group-addon">主&emsp;题</span>
					<input type="text" class="form-control" name="title" placeholder="Theme">
				</div>
				<br>
				<input id="savee" type="hidden" name="save" value="">
				<div class="input-group">
					<textarea class="form-control" rows="10" cols="300" placeholder="内容描述" name="content"></textarea>
				</div>
				<br>
				<center>
					<div class="row" style="height: 80px">
					<input id="input-1a" type="file" class="file" data-show-preview="false" name="attachment">
					</div>
				</center>
			
		</div>
			<button id="submitBtn" type="submit" class="btn btn-primary" onclick="save()">保存</button>
			<button  id="button" type="submit" class="btn btn-primary" onclick="send()">发送</button>			
		</center>
		</form>
	</main>
</body>
<script src="js/plugins/jquery.min.js" ></script>
	<script>
	    $("#button").submit(function(){  
		$("#savee").val(true);
		$.post(this.action,$(this).serializeArray(),function(resp){
			if(resp.code==="ok"){
				toast("发送成功");
				//防止重复提交
				$("#submitBtn")[0].disabled=true;
				//window.location.href="index.do";
			}else{
				toast(resp.message);
			}
		});
		return false;
	   });
		$("#submitBtn").submit(function(){  
			$("#savee").val(false);
			$.post(this.action,$(this).serializeArray(),function(resp){
				if(resp.code==="ok"){
					toast("保存成功");
					//window.location.href="index.do";
				}else{
					toast(resp.message);
				}
			});
			return false;
		});
		
	</script>
    <script type="text/javascript">
    //  function  save() {
    //	  document.form1.action="addEmail.do?save=true";
    //	toast("发送");
    //		document.form1.submit();
	// }
     // function  send() {
  	//document.form1.action="addEmail.do?save=flase";
  	//	toast("发送");
  	//	document.form1.submit();
  	//}

		
    </script>
</html>