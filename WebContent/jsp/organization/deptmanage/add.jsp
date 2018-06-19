<%@page pageEncoding="utf-8"%>
<%@taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jspf/head.jsp"%>
</head>
<body class="layout-vbox">
    <nav class="nav-bar">
        <a
            href="javascript:history.back()"
            class="fa fa-back"></a>
        <ol class="breadcrumb">
            <li>
                <a
                    href="javascript:window.top.dashboard()"
                    class="fa fa-dashboard">首页 </a>
            </li>
            <li>组织管理</li>
            <li>
                <a href="organization/department/manage.do">员工管理</a>
            </li>
            <li>
                <b>新增</b>
            </li>
        </ol>
    </nav>
    <section class="action-bar layout-row">
        <button
            id="submitFormBtn"
            type="submit"
            form="addForm"
            class="btn">提交</button>
    </section>
    <form
        id="addForm"
        class="label5em"
        action="api/organization/department/add"
        method="post">
        <p>
            <label for="parentIdEL">*父部门：</label>
            <select
                id="parentIdEL"
                name="parentId"
                class="grow">
            </select>
        </p>
        <p>
            <label for="codeEL">*代号：</label>
            <input
                type="text"
                name="code"
                maxlength="32"
                class="grow"
                id="codeEL"
                placeholder="两位一组">
        </p>
        <p>
            <label for="nameEL">*名称：</label>
            <input
                type="text"
                name="name"
                maxlength="32"
                class="grow"
                id="nameEL"
                placeholder="不超过32个字符">
        </p>
        <p>
            <label for="managerIdEL">部门经理：</label>
            <input
                type="text"
                name="managerIdEL"
                class="grow"
                id="managerIdEL">
        </p>
        <p>
            <label for="remarkEL">描述：</label>
            <textarea
                name="remark"
                maxlength="512"
                class="grow"
                id="remarkEL"
                rows="5"></textarea>
        </p>
    </form>
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
                        pid = pid || window.location.params("pid");
                        var url = "api/organization/department/beParent";
                        $.getJSON(url, function(resp) {
                            if (resp.code === "ok") {
                                var $selectEL = $("#parentIdEL");
                                $selectEL.empty();
                                $selectEL.html("<option value=''>(null)</option>");
                                var treeSelect = new TreeSelect($selectEL);
                                var rows = resp.data;
                                for (var i = 0; i < rows.length; i++) {
                                    var r = rows[i];
                                    var content = "(" + r.code + ")" + r.name;
                                    var option = treeSelect.insert(r.parentId, r.id, content, {
                                        value : r.id,
                                        selected : r.id === pid
                                    });
                                }
                                treeSelect.done();
                            } else {
                                toast(resp.message);
                            }
                        });
                    };
                    var handleFormSubmit = function() {
                        //防止重复提交
                        var submitBtn = $("#submitFormBtn")[0];
                        submitBtn.disabled = true;
                        $.post(this.action, $(this).serializeArray(), function(resp) {
                            if (resp.code === "ok") {
                                if (!confirm("新增成功，是否继续添加？")) {
                                    window.location.href = "organization/department/manage.do";
                                } else {
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