<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<%@include file="/WEB-INF/jspf/head.jsp"%>
<style>
.userdata-table>tbody>tr {
	cursor: pointer;
}

.userdata-table>tbody>tr.selected {
	background-color: #eeccee;
}
</style>
</head>

<body>
	<header class="content-header">
		<h1>用户角色分配</h1>
		<ol class="breadcrumb">
			<li>
			<a href="javascript:window.top.dashboard()"><i class="fa fa-dashboard"></i>首页</a>
			</li>
			<li>
				<a href="javascript:">系统管理</a>
			</li>
			<li><a>用户角色分配</a></li>
		</ol>
		<hr>
	</header>
	<!-- 把requestScope中的result对象放入到pageScope -->
	<c:set var="result" value="${requestScope.result}" scope="page"></c:set>
	<main class="container-fluid">
	<section class="row">
		<aside class="col-xs-6" style="overflow: auto">
			<div class="box box-default">
				<header class="box-header with-border">
					<h3 class="box-title">用户列表</h3>
					<div class="box-tools pull-right">
						<form id="qryForm" action="userrole/list.do" method="post">
							<input type="hidden" name="pageNo" value="1">
								<input type="text" autofocus name="key" value="${param.key}"
									class="form-control" style="min-width: 200px" maxlength="32"
									placeholder="按回车直接搜索">
						</form>
					</div>
				</header>
				<section class="box-body">
					<ul id="role-box" class="nav nav-pills nav-stacked">
						<c:forEach var="r" items="${result.rows}" varStatus="vs" >
							<li>
							<a href="javascript:handlerUserClick('${r.id}')">
								<b>${r.name}(${r.account})</b> 
								<span>${r.flag==0?"正常":"锁定"} </span>
								</a>
							</li>
						</c:forEach>
						
						<c:if test="${result.notFound}">
							<li>很抱歉，未找到相关数据！</li>
						</c:if>
					</ul>
				</section>
				
				<footer class="box-footer">
					<c:if test="${result.found}">
						<jsp:include page="/WEB-INF/jspf/pagination.jsp">
							<jsp:param name="resultVarName" value="result" />
							<jsp:param name="pageClickHandler" value="gotoPage" />
						</jsp:include>
					</c:if>
				</footer>
			</div>
		</aside>
		<aside class="col-xs-6">
			<div class="box box-default">
				<header class="box-header with-border">
					<h3 class="box-title">角色列表/已分配角色</h3>
					<div class="box-tools pull-right">
						<button id="resetBtn" type="button"
							class="btn btn-default btn-box-tool">重置</button>
						<button id="confirmGrantBtn" type="button"
							class="btn btn-default btn-box-tool">确定</button>
					</div>
				</header>
				<section class="box-body">
					<ul id="role-box" class="nav nav-pills nav-stacked">
						<c:forEach var="r" items="${roles}">
							<li><a><label><input type="checkbox" name="roleId"
									value="${r.id}">${r.name}</label>
									</a>
									</li>
						</c:forEach>
					</ul>
				</section>
			</div>
		</aside>
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
		var currentUserId;

		var handlerUserClick=function(userId) {
			currentUserId = userId;
			showGrantedRoles(userId);
		};
		/**
		 *显示已选则角色
		 */
		var showGrantedRoles = function(userId) {
			var url = "loadUserRoles.ajax?userId=" + userId;
			$.getJSON(url, function(resp) {
				if (resp.code === "ok") {
					var roles = resp.data;
					selectRole(roles);
				}
			});
		}
		/**
		 *选择角色
		 */
		var selectRole = function(needSelecteds) {
			var rolesArr = document.getElementsByName("roleId");
			for (var i = rolesArr.length, r; i-->0;) {
				r = rolesArr[i];
				if (needSelecteds.indexOf(r.value) != -1) {
					r.checked = true;
				} else {
					r.checked = false;
				}
			}
		}
		
		$("#resetBtn").click(function() {
			if (showGrantedRoles) {
				showGrantedRoles(currentUserId);
			}
		});
		$("#confirmGrantBtn").click(function() {
			var grantRoles = [];
			var rolesArr = document.getElementsByName("roleId");
			for (var i = rolesArr.length, r; i-->0;) {
				r = rolesArr[i];
				if (r.checked) {
					grantRoles.push(r.value);
				}
			}
			;
			var url = "grantrole2user.ajax";
			$.post(url, {
				userId : currentUserId,
				roles : grantRoles.join(",")
			}, function(resp) {
				if (resp.code === "ok") {
					alert("分配成功！");
				}
			});
		});
	</script>
</body>

</html>