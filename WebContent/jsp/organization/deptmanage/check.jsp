<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jspf/head.jsp"%>
<script src="js/yi.tree-select.js"></script>
</head>
<body>
    <header class="content-header">
        <h1>查看/修改部门</h1>

        <ol class="breadcrumb">
            <li>
                <a href="javascript:window.top.dashboard()">
                    <i class="fa fa-dashboard"></i>
                    首页
                </a>
            </li>
            <li>
                <a href="jsp/base/organization/deptmanage/list.jsp">系统功能模块管理</a>
            </li>
            <li>
                <a>查看/修改部门</a>
            </li>
        </ol>
        <hr>
    </header>

    <main class="container">
    <form action="deptmanage/mod.ajax" method="post">
        <input name="id" type="hidden">
        <p>
            <label for="parentIdEL">*父部门：</label>
            <select id="parentIdEL" name="parentId"
                class="form-control">
            </select>
        </p>
        <p>
            <label for="codeEL">*代号：</label>
            <input type="text" name="code" maxlength="32"
                class="form-control" id="codeEL"
                placeholder="两位一组">
        </p>
        <p>
            <label for="nameEL">*名称：</label>
            <input type="text" name="name" maxlength="32"
                class="form-control" id="nameEL" placeholder="不超过32个字符">
        </p>
      
        <p>
            <label for="managerNameEL">部门经理：</label>
            <input type="hidden" name="managerId">
            <input type="text" id="managerNameEL"   
                class="form-control"  >
                
        </p>
        <p>
            <label>部门人数：</label>
            <input type="text" disabled  class="form-control" id="memberEL" >
        </p>
         <p>
            <label>创建时间：</label>
            <input type="text" disabled
                class="form-control" id="createTimeEL" >
        </p>
        <p>
            <label for="remarkEL">描述：</label>
            <textarea name="remark" maxlength="512" class="form-control"
                id="remarkEL" rows="5"></textarea>
        </p>
        <div class="action-box">
            <button type="submit" class="btn btn-lg btn-primary">保存</button>
            <a type="button" class="btn btn-info"
                href="jsp/organization/deptmanage/list.jsp">返回</a>
                
            <a  href="javascript:deleteConfirm()">删除</a>  
        </div>
    </form>
    </main>
    <script>
    var id = window.location.search.substr(1);
	//加载父模块
	$(function() {
		loadModel(id).then(function(){
			$("form").submit(handleFormSubmit);
		})
	});
	</script>
    <script>
    	var handleFormSubmit=function(){
    		//防止重复提交
    		var submitBtn = $("button[type='submit']", this)[0];
    		submitBtn.disabled = true;
    		$.post(this.action, $(this).serializeArray(), function(resp) {
    			if (resp.code === "ok") {
    				alert("修改成功，请返回");
    			} else {
    				toast(resp.message);
    			}
    			submitBtn.disabled = false;
    		});
    		return false;
    	};
</script>
<script>
	
	var loadModel = function(id) {
		return new Promise(function(ok,fail){
			var url = "deptmanage/check.ajax?id=" + id;
			$.getJSON(url, function(resp) {
				if (resp.code === "ok") {
					paintFormView(resp.data);
					ok();
				} else {
					toast(resp.message);
				}
			});
		});
	};
</script>
<script type="text/javascript">
/**
加载可为父模块列表，默认选中pid
*/
var loadCanBeParents = function(pid) {
	var url = "deptmanage/canBeParent.ajax?id="+id;
	$.getJSON(url, function(resp) {
		if (resp.code === "ok") {
			var $selectEL = $("#parentIdEL");
			$selectEL.empty();
			$selectEL.html("<option value=''>(null)</option>");
			var treeSelect=new TreeSelect($selectEL); 					
			var rows = resp.data;
			for(var i=0;i<rows.length;i++){
				var r=rows[i];
				var content="("+r.code+")"+r.name;
				var option=treeSelect.insert(r.parentId,r.id,content,{
					value:r.id,
					selected:r.id===pid
				});
			}
			treeSelect.done();
		} else {
			toast(resp.message);
		}
	});
};
	
	var paintFormView = function(m) {
		var form = $("form")[0];
		form.id.value=m.id;
		//父部门
		var parentId;
		if (m.parent) {
			parentId=m.parent.id;
		}
		//加载可用父部门
		loadCanBeParents(parentId);
		//部门经理
		form.managerId.value =$$("id",m.manager);
		$("#manageNameEL").val($$("name"),m.manager);
		
		form.id.value =m.id;
		form.code.value =m.code;
		form.name.value = $$(m.name);
		$("#createTimeEL").val(Date.format(new Date(m.createTime)));
		$("#memberEL").val(m.members);
		form.remark.textContent = $$(m.remark);
	}
	
	var deleteConfirm = function() {
		if (window.confirm("确认删除？")) {
			var url = "deptmanage/del.ajax?id=" + id;
			$.getJSON(url,function(resp) {
				if (resp.code === "ok") {
					toast("删除成功！");
					window.location.href = "jsp/organization/deptmanage/list.jsp";
				} else {
					toast(resp.message);
				}
			});
		}
	};
</script>
		
		
</body>
</html>