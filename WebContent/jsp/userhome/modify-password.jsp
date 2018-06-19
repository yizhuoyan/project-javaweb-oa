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
        <ol class="breadcrumb">
            <li>
                <a
                    href="javascript:window.top.dashboard()"
                    class="fa fa-dashboard">首页 </a>
            </li>
            <li>个人中心</li>
            <li>
                <b>修改密码</b>
            </li>
        </ol>
    </nav>
    <section class="layout-row action-bar">
        <a
            id="submitFormBtn"
            href="javascript:submitForm()"
            class="btn">提交修改</a>
    </section>

    <form
        class="label7em"
        action="user/modifyPassword.ajax"
        method="post">
        <p>
            <label for="oldPasswordEL">*原密码：</label>
            <input
                type="password"
                name="oldPassword"
                class="grow"
                id="oldPasswordEL"
                placeholder="请输入旧密码">
        </p>
        <p>
            <label for="newPasswordEL">*新密码：</label>
            <input
                type="password"
                name="newPassword"
                maxlength="16"
                class="grow"
                id="newPasswordEL"
                placeholder="6-16个字符，必须包含字母，数字，特殊字符">
        </p>
        <p>
            <label for="newPasswordConfirmEL">*新密码确认：</label>
            <input
                type="password"
                name="newPasswordConfirm"
                maxlength="16"
                class="grow"
                id="newPasswordConfirmEL"
                placeholder="请再次输入您的新密码">
        </p>
    </form>
    <script>
    var submitForm=function(){
        //防止重复提交
        var submitBtn = $("submitFormBtn")[0];
        submitBtn.disabled = true;
        var form=$("form")[0];
        $.post(form.action, $(form).serializeArray(), function(resp) {
            if (resp.code === "ok") {
                toast("密码修改成功，请重新登陆!");
                window.top.location.href="jsp/login.jsp";
            } else {
                toast(resp.message);
            }
            submitBtn.disabled = false;
        });
    };
</script>
</body>

</html>