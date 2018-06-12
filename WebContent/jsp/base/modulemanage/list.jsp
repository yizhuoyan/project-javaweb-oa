<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<%@include file="/WEB-INF/jspf/head.jsp"%>
<%@include file="/WEB-INF/jspf/head-fancytree.jsp"%>

<style>
</style>
</head>

<body>
	<header class="content-header">
		<h1>系统功能模块管理</h1>
		<ol class="breadcrumb">
			<li>
			<a href="javascript:window.top.dashboard()"><i class="fa fa-dashboard"></i>首页</a>
			
			</li>
			<li><a>系统功能模块</a></li>
		</ol>
		<hr>
	</header>

	<main class="container-fluid"> <!--action bar-->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<a type="button" href="jsp/base/modulemanage/add.jsp"
				class="btn btn-primary navbar-btn">新增</a>
		</div>
	</nav>
	<section>
		<table class="table table-bordered" id="functionalityTableTree">
			<colgroup>
				<col width="30px"></col>
				<col width="180px"></col>
				<col width="100px"></col>
				<col width="*"></col>
				<col width="*"></col>
			</colgroup>
			<thead>
				<tr>
					<th>#</th>
					<th>名称</th>
					<th>操作</th>
					<th>代号</th>
					<th>URL</th>
					<th>备注</th>
				</tr>
			</thead>
			<tbody>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
			</tbody>
		</table>
	</section>

	</main>
	<script>
		$(function() {
			loadTree();
		});
	</script>
	<script>
		var tree;
		//功能树标签容器
		var $tableTreeEL = $("#functionalityTableTree");
		var loadTree = function() {
			$tableTreeEL.fancytree({
				source : {
					url : "sysmodule/list.ajax",
					cache : true
				},
				postProcess : function(evt, data) {
					var resp = data.response;
					if (resp.code === "ok") {
						data.result = covertFlatList2Tree(resp.data.rows);
					} else {
						alert(resp.message);
					}
				},
				icon:false,
				checkbox : false,
				selectMode : 1,
				minExpandLevel:1,
				extensions : [ "table" ],
				table : {
					nodeColumnIdx : 1,
					indentation : 16
				},
				renderColumns : function(evt, ctx) {
					var node = ctx.node;
					var model = node.data;
					var cells = node.tr.getElementsByTagName("td");
					cells[0].textContent=node.getIndexHier();
					//cells[1]为名称
					var modUrl="jsp/base/modulemanage/check.jsp?"+model.id;
					cells[2].innerHTML ='<a href="'+modUrl+'" >修改</a>' ;
					cells[3].textContent = model.code;
					cells[4].textContent = $$(model.url);
				},
				strings : {
					loading : "加载中...请稍后",
					loadError : "加载数据异常，请联系管理员",
					moreData : "More...",
					noData : "无功能数据"
				},
				init : function(evt, ctx, flag) {
					tree = ctx.tree;
				},
				activate : function(evt, ctx) {
					var node = ctx.node;
					node.setSelected(true);
				},
				deactivate : function(evt, ctx) {
					var node = ctx.node;
					node.setSelected(false);
				},
				select : function(evt, ctx) {
					var node = ctx.node;
					handlerItemSelected(node, node.selected);
				}
			});
		};
		var covertFlatList2Tree = function(list) {
			//保存所有node的map
			var nodeMap = {};
			//保存所有根节点
			var roots = [];
			list.filter(function(item) {
				item.key = item.id;
				item.title = item.name;
				nodeMap[item.id] = item;
				if (item.parent) {
					return true;
				} else {
					roots.push(item);
					return false;
				}
			}).forEach(function(item) {
				var parent = nodeMap[item.parent.id];
				if (parent.children) {
					parent.children.push(item);
				} else {
					parent.children = [ item ];
				}
			});
			return roots;
		};

		var handlerItemSelected = function() {

		}
	</script>
</body>

</html>