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
                    class="fa fa-dashboard"
                    href="javascript:window.top.dashboard()"> 首页 </a>
            </li>
            <li>系统管理</li>
            <li>
                <a href="system/user/manage.do">系统用户管理</a>
            </li>
            <li>
                <b>查看</b>
            </li>
        </ol>
    </nav>
    <section class="action-bar layout-row">
        <a
            id="modifyBtn"
            class="btn"
            href="javascript:submitModify()">提交修改</a>
        <a
            href="javascript:grantRole()"
            class="btn">分配角色</a>
        <a
            id="changeStateBtn"
            class="btn">锁定/解锁</a>
        <a
            id="deleteBtn"
            type="button"
            href="javascript:deleteUser()"
            class="btn">删除用户</a>
    </section>
    <form
        action="api/system/user/mod"
        method="post"
        class="label7em">
        <input name="id" type="hidden">
        <p>
            <label for="avatorEL">头像：</label>
            <img
                id="avatarEL"
                class="avatar"
                width="50"
                onclick="showAvatar(this)"
                height="50"
                alt="用户头像">
            <script title="查看用户头像大图">
            var showAvatar=function(img){
                if(img&&img.src){
                    new FrameWindow().open(img.src);
                }
            }
            </script>    
        </p>
        <p>
            <label for="accountEL">*账户：</label>
            <input
                type="text"
                name="account"
                maxlength="32"
                class="grow"
                id="accountEL"
                required="required"
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
                required="required"
                placeholder="不超过32个字符">
        </p>
        <p>
            <label>*账号状态：</label>
            <b id="accountStateEL">未知 </b>
        </p>
        <p>
            <label for="remarkEL">备注：</label>
            <input
                type="text"
                name="remark"
                maxlength="128"
                class="grow"
                placeholder="不超过128个字符"
                id="remarkEL">
        </p>
        <p>
            <span>
                <label for="phoneEL">联系电话：</label>
                <input
                    type="text"
                    class="grow"
                    id="phoneEL"
                    readonly>
            </span>
            <span>
                <label for="securityEmailEL">密保邮箱：</label>
                <input
                    type="text"
                    class="grow"
                    id="securityEmailEL"
                    readonly>
            </span>
        </p>

        <p>
            <span>
                <label for="lastLoginTimeEL">最近登录：</label>
                <input
                    type="text"
                    class="grow"
                    id="lastLoginTimeEL"
                    readonly>
            </span>
            <span>
                <label for="lastLoginIPEL">最近登录IP：</label>
                <input
                    type="text"
                    class="grow"
                    id="lastLoginIPEL"
                    readonly>
            </span>
            <span>
                <label for="lastModPasswordTimeEL">最近修改密码：</label>
                <input
                    type="text"
                    class="grow"
                    id="lastModPasswordTimeEL"
                    readonly>
            </span>
        </p>
        <p>
            <span>
                <label for="createUserEL">创建人：</label>
                <input
                    type="text"
                    class="grow"
                    id="createUserEL"
                    readonly>
            </span>
            <span>
                <label for="createTimeEL">创建时间：</label>
                <input
                    type="text"
                    class="grow"
                    id="createTimeEL"
                    readonly>
            </span>
        </p>
    </form>
    <script>
                    var id = location.search.slice(1);
                    $(function() {
                        //加载用户模型
                        loadModel(id);

                    });
                </script>
    <script>
                    var loadModel = function(id) {
                        var url = "api/system/user/get?id=" + id;
                        $.getJSON(url, function(resp) {
                            if (resp.code === "ok") {
                                paintView(resp.data);
                            } else {
                                toast(resp.message);
                            }
                        })
                    };
                    var paintView = function(m) {
                        var form = $("form")[0];
                        form.id.value = $$(m.id);
                        if(m.avatar){
                            $("#avatarEL").attr("src",m.avatar.slice(1));
                        }
                        form.account.value = $$(m.account);
                        form.name.value = $$(m.name);
                        //状态
                        paintAccountState(m.locked);
                        form.remark.value = $$(m.remark);
                        
                        $("#phoneEL").val($$(m.phone));
                        $("#securityEmailEL").val($$(m.securityEmail));
                        $("#lastLoginTimeEL").val(Date.format(m.lastLoginTime));
                        $("#lastLoginIPEL").val($$(m.lastLoginIP));
                        $("#lastModPasswordTimeEL").val(Date.format(m.lastModPasswordTime));
                        $("#createUserEL").val($$("name",m.createUser));
                        $("#createTimeEL").val(Date.format(m.createTime));
                        //控制权限
                        var currentUserId = sessionStorage.getItem("currentUserId");
                        var createUserId = m.createUser.id;
                        if (currentUserId !== createUserId) {
                            $("#deleteBtn").hide();
                            $("#modifyBtn").hide();
                        }

                    };
                    var paintAccountState=function(locked){
                        if(locked){
                            $("#accountStateEL").text("锁定");
                            $("#changeStateBtn").text("解锁账号").attr("href","javascript:unlockUser()");
                        }else{
                            $("#accountStateEL").text("正常");
                            $("#changeStateBtn").text("锁定账号").attr("href","javascript:lockUser()");
                        }
                    }
                </script>
    <script>
                    var submitModify = function() {
                        //防止重复提交
                        var submitBtn = $("#modifyBtn")[0];
                        submitBtn.disabled = true;
                        var form = $("form")[0];
                        $.post(form.action, $(form).serializeArray(), function(resp) {
                            if (resp.code === "ok") {
                                toast("修改成功，请返回");
                            } else {
                                toast(resp.message);
                            }
                            submitBtn.disabled = false;
                        });
                    };
                    var lockUser=function(){
                        var url = "api/system/user/lock?id=" + id;
                        $.post(url, function(resp) {
                            if (resp.code === "ok") {
                                toast("锁定成功，用户不能进行登录");
                                paintAccountState(true);
                            } else {
                                toast(resp.message);
                            }
                        });   
                    };
                    var unlockUser=function(){
                        var url = "api/system/user/unlock?id=" + id; 
                        $.post(url, function(resp) {
                            if (resp.code === "ok") {
                                toast("解锁成功，用户可以正常进行登录了");
                                paintAccountState(false);
                            } else {
                                toast(resp.message);
                            }
                        });
                    };
                    
                    var deleteUser=function(){
                        var url = "api/system/user/del?id=" + id;
                        $.post(url, function(resp) {
                            if (resp.code === "ok") {
                                window.top.toast("删除成功");
                                location.href="system/user/manage.do";
                            } else {
                                toast(resp.message);
                            }
                        })
                    };
                    
                    var grantRole=function(){
                        location.href="jsp/system/usermanage/grant-role.jsp?"+id;
                    };
                    
                    
                </script>
</body>

</html>