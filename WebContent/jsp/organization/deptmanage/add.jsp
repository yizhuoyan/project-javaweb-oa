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
        <h1>新增部门</h1>

        <ol class="breadcrumb">
            <li>
                <a href="javascript:window.top.dashboard()">
                    <i class="fa fa-dashboard"></i>
                    首页
                </a>
            </li>
            <li>
                <a href="jsp/organization/deptmanage/list.jsp">系统功能模块管理</a>
            </li>
            <li>
                <a>新增</a>
            </li>
        </ol>
        <hr>
    </header>

    <main class="container">
    <form action="deptmanage/add.ajax" method="post">
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
            <label for="managerIdEL">部门经理：</label>
            <input type="text" name="managerIdEL"  
                class="form-control" id="managerIdEL" >
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
        </div>
    </form>
    </main>
    <script>
    	$(function() {
    		loadCanBeParents();
    		$("form").submit(handleFormSubmit);
    		
    	});
	</script>
    <script>
		/**
		加载可为父模块列表，默认选中pid
		*/
		var loadCanBeParents = function(pid) {
			pid=pid||window.location.params("pid");
			var url = "deptmanage/canBeParent.ajax";
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
		var handleFormSubmit=function(){
			//防止重复提交
			var submitBtn = $("button[type='submit']", this)[0];
			submitBtn.disabled = true;
			$.post(this.action,$(this).serializeArray(),function(resp) {
				if (resp.code === "ok") {
					if (!confirm("新增成功，是否继续添加？")) {
						window.location.href = "jsp/organization/deptmanage/list.jsp";
					}else{
						loadCanBeParents();
					}
				} else {
					toast(resp.message);
				}
				submitBtn.disabled = false;
			});
			return false;
	 };
	</script>
</body>
</html>