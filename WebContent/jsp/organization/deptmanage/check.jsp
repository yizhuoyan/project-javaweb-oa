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
                <b>查看</b>
            </li>
        </ol>
    </nav>
    <section class="action-bar layout-row">
        <button
            id="submitFormBtn"
            type="submit"
            form="modForm"
            class="btn">提交</button>
        <a
            id="deleteBtn"
            class="btn"
            href="javascript:deleteConfirm()">删除</a>
    </section>

    <form
        id="modForm"
        class="label5em"
        action="api/organization/department/mod"
        method="post">
        <input
            name="id"
            type="hidden">
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
                required="required"
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
                required="required"
                placeholder="不超过32个字符">
        </p>

        <p>
            <label for="managerNameEL">部门经理：</label>
            <input
                type="hidden"
                name="managerId">
            <input
                type="text"
                id="managerNameEL"
                class="grow">

        </p>
        <p>
            <label>部门人数：</label>
            <input
                type="text"
                readonly="readonly"
                class="grow"
                id="memberEL">
        </p>
        <p>
            <label>创建时间：</label>
            <input
                type="text"
                class="grow"
                readonly="readonly"
                id="createTimeEL">
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
                    var id = window.location.search.substr(1);
                    //加载父模块
                    $(function() {
                        loadModel(id).then(function() {
                            $("form").submit(handleFormSubmit);
                        })
                    });
                </script>
    <script>
                    var handleFormSubmit = function() {
                        //防止重复提交
                        var submitBtn = $("#submitFormBtn")[0];
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
                        return new Promise(function(ok, fail) {
                            var url = "api/organization/department/get?id=" + id;
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
    <script>
                    /**
                     加载可为父模块列表，默认选中pid
                     */
                    var loadCanBeParents = function(pid) {
                        var url = "api/organization/department/beParent?id=" + id;
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

                    var paintFormView = function(m) {
                        var form = $("form")[0];
                        form.id.value = m.id;
                        //父部门
                        var parentId;
                        if (m.parent) {
                            parentId = m.parent.id;
                        }
                        //加载可用父部门
                        loadCanBeParents(parentId);
                        //部门经理
                        form.managerId.value = $$("id", m.manager);
                        $("#manageNameEL").val($$("name"), m.manager);

                        form.id.value = m.id;
                        form.code.value = m.code;
                        form.name.value = $$(m.name);
                        $("#createTimeEL").val(Date.format(new Date(m.createTime)));
                        $("#memberEL").val(m.members);
                        form.remark.textContent = $$(m.remark);
                    }

                    var deleteConfirm = function() {
                        if (window.confirm("确认删除？")) {
                            var url = "api/organization/department/del?id=" + id;
                            $.getJSON(url, function(resp) {
                                if (resp.code === "ok") {
                                    window.top.toast("删除成功！");
                                    window.location.href = "organization/department/manage.do";
                                } else {
                                    toast(resp.message);
                                }
                            });
                        }
                    };
                </script>


</body>
</html>