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
                <a href="system/role/manage.do">系统角色管理</a>
            </li>
            <li>
                <b>新增</b>
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
        action="api/system/role/add"
        method="post"
        class="label3em">
            <p class="form-group">
                <label for="codeEL">*代号：</label>
                <input
                    type="text"
                    name="code"
                    maxlength="32"
                    class="grow"
                    id="codeEL"
                    placeholder="3-32个字符，支持字母，数字，中横线">
            </p>
            <p class="form-group">
                <label for="nameEL">*名称：</label>
                <input
                    type="text"
                    name="name"
                    maxlength="32"
                    class="grow"
                    id="nameEL"
                    placeholder="不超过32个字符">
            </p>
            <p class="form-group">
                <label for="remarkEL">描述：</label>
                <input
                    type="text"
                    name="remark"
                    maxlength="128"
                    class="grow"
                    placeholder="不超过128个字符"
                    id="remarkEL">
            </p>
        <p class="form-action">
            <button
                type="submit"
                class="btn">提交</button>
        </p>
    </form>
    <script>
                    $("form").submit(function() {
                        //防止重复提交
                        var submitBtn = $("button[type='submit']", this)[0];
                        submitBtn.disabled = true;
                        $.post(this.action, $(this).serializeArray(), function(resp) {
                            if (resp.code === "ok") {
                                if (!confirm("新增成功，是否继续添加？")) {
                                    window.location.href = "system/role/manage.do";
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