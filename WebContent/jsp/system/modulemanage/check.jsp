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
            <li>系统管理</li>
            <li>
                <a href="system/module/list.do">功能模块管理</a>
            </li>
            <li>
                <a>查看</a>
            </li>
        </ol>
    </nav>
    <section class="action-bar layout-row">
             <button
                id="modifyBtn"
                type="submit"
                class="btn">提交修改</button>
            <a
                id="deleteBtn"
                class="btn"
                href="javascript:deleteConfirm()">删除此模块</a>
    </section>
    <form
        class="label7em"
        action="api/system/module/mod"
        method="post">
        <input
            type="hidden"
            name="id">
        <p>
            <label for="parentIdEL">父模块：</label>
            <select
                id="root-parents"
                name="parentId"
                class="grow">
                <option value="">顶层模块(null)</option>
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
                placeholder="3-32个字符，支持字母，数字，中横线">
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
            <label for="urlEL">URL：</label>
            <input
                type="text"
                name="url"
                maxlength="128"
                class="grow"
                id="urlEL"
                placeholder="不超过32个字符">
        </p>
        <p>
            <label for="showOrderEL">显示排序号：</label>
            <input
                type="text"
                name="showOrder"
                maxlength="32"
                class="grow"
                id="showOrderEL"
                placeholder="不超过32个字符">
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
        <p>
            <label for="createUserEL">创建人：</label>
            <input
                type="text"
                class="grow"
                id="createUserEL"
                readonly>
        </p>
        <p>
            <label for="createTimeEL">创建时间：</label>
            <input
                type="text"
                class="grow"
                id="createTimeEL"
                readonly>
        </p>

    </form>
    <script>
                    var id = window.location.search.substr(1);
                    //加载模块
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
                                toast(resp.message);
                            }
                            submitBtn.disabled = false;
                        });
                        return false;
                    });
                </script>
    <script>
                    var loadModel = function(id) {
                        var url = "api/system/module/get?id=" + id;
                        $.getJSON(url, function(resp) {
                            if (resp.code === "ok") {
                                paintView(resp.data);
                            } else {
                                toast(resp.message);
                            }
                        })
                    }
                </script>
    <script>
                    var paintParentSelect = function(parentId) {
                        var url = "api/system/module/listRoot";
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
                                toast(resp.message);
                            }
                        });
                    };
                    var paintView = function(m) {
                        var form = $("form")[0];
                        form.id.value = $$(m.id);

                        var parentId;
                        if (m.parent) {
                            parentId = m.parent.id;
                        }
                        paintParentSelect(parentId);
                        form.code.value = $$(m.code);
                        form.name.value = $$(m.name);
                        form.url.value = $$(m.url);
                        // form.icon.value = $$(m.icon);
                        form.showOrder.value = $$(m.showOrder);
                        form.remark.textContent = $$(m.remark);
                        $("#createUserEL").val($$(m.createUser.name));
                        $("#createTimeEL").val(Date.format(new Date(m.createTime)));

                        //控制权限
                        var currentUserId = sessionStorage.getItem("currentUserId");
                        var createUserId = m.createUser.id;
                        if (currentUserId !== createUserId) {
                            $("#deleteBtn").hide();
                            $("#modifyBtn").hide();
                        }

                    }
                    var deleteConfirm = function() {
                        if (window.confirm("确认删除？")) {
                            var url = "api/system/module/del?id=" + id;
                            $.getJSON(url, function(resp) {
                                if (resp.code === "ok") {
                                    alert("删除成功！");
                                    window.location.href = "system/module/manage.do";
                                } else {
                                    toast(resp.message);
                                }
                            });
                        }
                    }
                </script>
</body>

</html>