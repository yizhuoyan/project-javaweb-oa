<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<%@include file="/WEB-INF/jspf/head.jsp"%>
</head>

<body>
	<header class="content-header">
		<h1>查看/修改系统功能模块</h1>
		<ol class="breadcrumb">
			<li>
			<a href="javascript:window.top.dashboard()"><i class="fa fa-dashboard"></i>首页</a>
			</li>
			<li><a href="jsp/base/modulemanage/list.jsp">系统功能模块管理</a></li>
			<li><a>查看/修改</a></li>
		</ol>
		<hr>
	</header>

	<main class="container-fluid">
	<div id="message-container" class="hide alert alert-info" role="alert">
		<strong id="messageEL"></strong>
	</div>
	<!-- form start -->
	<form role="form" action="sysmodule/mod.ajax" method="post" class="box">
		<input type="hidden" name="id">
		<div class="box-body">
			<p class="form-group">
				<label for="parentIdEL">父模块：</label> <select id="root-parents"
					name="parentId" class="form-control">
					<option value="">顶层模块(null)</option>
				</select>
			</p>
			<p class="form-group">
				<label for="codeEL">*代号：</label> <input type="text" name="code"
					maxlength="32" class="form-control" id="codeEL"
					placeholder="3-32个字符，支持字母，数字，中横线">
			</p>
			<p class="form-group">
				<label for="nameEL">*名称：</label> <input type="text" name="name"
					maxlength="32" class="form-control" id="nameEL"
					placeholder="不超过32个字符">
			</p>
			<p class="form-group">
				<label for="urlEL">URL：</label> <input type="text" name="url"
					maxlength="128" class="form-control" id="urlEL"
					placeholder="不超过32个字符">
			</p>
			<p class="form-group">
				<label for="showOrderEL">显示排序号：</label> <input type="text"
					name="showOrder" maxlength="32" class="form-control"
					id="showOrderEL" placeholder="不超过32个字符">
			</p>
			<p class="form-group">
				<label for="remarkEL">描述：</label>
				<textarea name="remark" maxlength="512" class="form-control"
					id="remarkEL" 　rows="5"></textarea>
			</p>
		</div>

		<div class="box-footer">
			<button type="submit" class="btn btn-lg btn-primary">修改</button>
			<a type="button" class="btn btn-info"
				href="jsp/base/modulemanage/list.jsp">返回</a> <a type="button"
				class="btn btn-link" href="javascript:deleteConfirm()">删除</a>
		</div>
	</form>

	</main>
	<script>
		var id = window.location.search.substr(1);
		//加载父模块
		$(function() {
			loadModel(id);
		});
		$("form").submit(function() {
			//防止重复提交
			var submitBtn = $("button[type='submit']", this)[0];
			submitBtn.disabled = true;
			$.post(this.action, $(this).serializeArray(), function(resp) {
				if (resp.code === "ok") {
					alert("修改成功，请返回");
				} else {
					$("#messageEL").html(resp.message);
					$("#message-container").show();

				}
				submitBtn.disabled = false;
			});
			return false;
		});
	</script>
	<script>
		
		var loadModel = function(id) {
			var url = "sysmodule/check.ajax?id=" + id;
			$.getJSON(url, function(resp) {
				if (resp.code === "ok") {
					paintFormView(resp.data);
				} else {
					$("#messageEL").html(resp.message);
					$("#message-container").show();
				}
			})
		}
	</script>
	<script type="text/javascript">
		var paintParentSelect = function(parentId) {
				var url = "sysmodule/listRoot.ajax";
				$.getJSON(url, function(resp) {
					if (resp.code === "ok") {
						var roots = resp.data;
						var datalist = $("#root-parents");
						$.each(roots, function(i, item) {
							var $opt = $("<option/>", {
								text : item.name + "(" + item.code + ")",
								value : item.id
							});
							if (parentId === item.id) {
								$opt.attr("selected", "selected");
							}
							$opt.appendTo(datalist);
						});
					} else {
						$("#messageEL").text(resp.message);
						$("#message-container").show();
					}
				});
		};
		var paintFormView = function(m) {
			var form = $("form")[0];
			form.id.value = $$(m.id);
			var parentId;
			if (m.parent) {
				parentId=m.parent.id;
			}
			paintParentSelect(parentId);
			form.code.value = $$(m.code);
			form.name.value = $$(m.name);
			form.url.value = $$(m.url);
			form.icon.value = $$(m.icon);
			form.showOrder.value = $$(m.showOrder);
			form.remark.textContent = $$(m.remark);
		}
		var deleteConfirm = function() {
			if (window.confirm("确认删除？")) {
				var url = "sysmodule/del.ajax?id=" + id;
				$
						.getJSON(
								url,
								function(resp) {
									if (resp.code === "ok") {
										alert("删除成功！");
										window.location.href = "jsp/base/modulemanage/list.jsp";
									} else {
										alert(resp.message);
									}
								});
			}
		}
	</script>
</body>

</html>