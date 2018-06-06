<%@page pageEncoding="utf-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		 <%@include file="/WEB-INF/jspf/head.jsp"%>
	</head>
	<body>
		<section class="content">
			<div class="alert alert-info alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				<strong><b>${sessionScope.loginUser.name}</b>你好！</strong> 
				您上一次登陆时间是:
				<fmt:formatDate value="${sessionScope.loginUser.lastLoginTime}" pattern="yyyy年MM月dd日 hh:mm:ss"/>
				登陆IP是:${sessionScope.loginUser.lastLoginIp}
				如果不是您的操作，请<a href="javascript:" onclick="window.top.openMenu(this.textContent)">修改密码</a>
				<span>
					今天的天气气温是${sessionScope.weather.temperature}
					
				</span>
					
			</div>
		</section>
		
		<section class="content">
			<div class="row">
				<div class="col-lg-3 col-xs-6">
					<!-- small box -->
					<div class="small-box bg-aqua">
						<div class="inner">
							<h3 id="onlineUserEL">加载中...</h3>
							<p>当前在线用户数</p>
						</div>
						<div class="icon">
							<i class="ion ion-person"></i>
						</div>
						<a class="small-box-footer">查看详情 <i class="fa fa-arrow-circle-right"></i></a>
					</div>
				</div>
				<!-- ./col -->
				<div class="col-lg-3 col-xs-6">
					<!-- small box -->
					<div class="small-box bg-green">
						<div class="inner">
							<h3 id="totalAccountEL">加载中...</h3>
							<p>注册用户数</p>
						</div>
						<div class="icon">
							<i class="ion ion-person-stalker"></i>
						</div>
						<a  class="small-box-footer">查看详情 <i class="fa fa-arrow-circle-right"></i></a>
					</div>
				</div>
				<!-- ./col -->
				<div class="col-lg-3 col-xs-6">
					<!-- small box -->
					<div class="small-box bg-yellow">
						<div class="inner">
							<h3 id="cpuPayloadEL">加载中...</h3>
							<p>服务器CPU负荷</p>
						</div>
						<div class="icon">
							<i class="ion ion-arrow-graph-up-right"></i>
						</div>
						<a  class="small-box-footer">查看详情 <i class="fa fa-arrow-circle-right"></i></a>
					</div>
				</div>
				<!-- ./col -->
				<div class="col-lg-3 col-xs-6">
					<!-- small box -->
					<div class="small-box bg-red">
						<div class="inner">
							<h3 id="memoryPayloadEL">加载中...</h3>
							<p>服务器内存负荷</p>
						</div>
						<div class="icon">
							<i class="ion ion-battery-half"></i>
						</div>
						<a class="small-box-footer">查看详情<i class="fa fa-arrow-circle-right"></i></a>
					</div>
				</div>
				<!-- ./col -->
			</div>
		</section>
		<script title="加载dashbord数据">
			$(function() {
				var url="system/statistics.do";
				$.get(url,function(resp){
					if(resp.code==="ok"){
						var data=resp.data;
						$("#onlineUserEL").text(data.onlineUsers);
						$("#totalAccountEL").text(data.totalAccount);
						$("#cpuPayloadEL").text(data.cpuPayload+"%");
						$("#memoryPayloadEL").text(data.memoryPayload+"%");
					}else{
						$("#onlineUserEL").text("未知");
						$("#totalAccountEL").text("未知");
						$("#cpuPayloadEL").text("未知");
						$("#memoryPayloadEL").text("未知");
					}
				})
			});
		</script>
	</body>

</html>