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
                <a>新增</a>
            </li>
        </ol>
        </nav>
     <section class="action-bar layout-row">
            <button
                type="submit"
                form="addForm"
                class="btn">提交</button>
     </section>
        <form
            id="addForm"
            class="label7em"
            action="api/system/module/add"
            method="post">
                <p>
                    <label for="parentIdEL">*父模块：</label>
                    <select
                        id="root-parents"
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
        </form>
        <script>
                                    var pid = window.location.params("pid");
                                    $(function() {
                                        loadRootModule(pid);
                                    });
                                </script>
        <script>
                                    //加载父模块
                                    var loadRootModule = function(pid) {
                                        var url = "api/system/module/listRoot";
                                        $.getJSON(url, function(resp) {
                                            if (resp.code === "ok") {
                                                var data = resp.data;
                                                var $selectEL = $("#root-parents");

                                                $selectEL.empty();

                                                $("<option/>", {
                                                    text : "顶层模块(null)",
                                                    value : ""
                                                }).appendTo($selectEL);

                                                $.each(data, function(i, item) {
                                                    $("<option/>", {
                                                        text : item.name + "(" + item.code + ")",
                                                        value : item.id,
                                                        selected : item.id === pid
                                                    }).appendTo($selectEL);
                                                });
                                            } else {
                                                toast(resp.message);
                                            }
                                        });
                                    };

                                    $("form").submit(function() {
                                        //防止重复提交
                                        var submitBtn = $("button[type='submit']", this)[0];
                                        submitBtn.disabled = true;
                                        $.post(this.action, $(this).serializeArray(), function(resp) {
                                            if (resp.code === "ok") {
                                                if (!confirm("新增成功，是否继续添加？")) {
                                                    window.location.href = "system/module/manage.do";
                                                }
                                            } else {
                                                toast(resp.message);
                                            }
                                            submitBtn.disabled = false;
                                        });
                                        return false;
                                    });
                                </script>
</body>

</html>