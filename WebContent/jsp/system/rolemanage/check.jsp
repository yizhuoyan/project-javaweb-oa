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
                <a href="javascript:window.top.dashboard()">
                    <i class="fa fa-dashboard"></i>
                    首页
                </a>
            </li>
            <li>系统管理</li>
            <li>
                <a href="system/role/manage.do">系统角色管理</a>
            </li>
            <li>查看</li>
        </ol>
    </nav>

    <section class="action-bar layout-row">
        <a
            id="modifyBtn"
            class="btn"
            href="javascript:submitForm()">提交修改</a>
        <a
            id="grantRoleBtn"
            class="btn"
            href="javascript:grantRole()">分配功能</a>
        <a
            class="btn"
            id="deleteBtn"
            href="javascript:deleteConfirm()">删除此角色</a>
    </section>

    <form
        id="modForm"
        action="api/system/role/mod"
        method="post"
        class="label5em">
        <input
            type="hidden"
            name="id">
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
            <label for="remarkEL">描述：</label>
            <input
                type="text"
                name="remark"
                maxlength="128"
                placeholder="不超过128个字符"
                class="grow"
                id="remarkEL">
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
                    //加载模型
                    $(function() {
                        loadModel(id);
                    });

                    var submitForm=function(){
                        //防止重复提交
                        var submitBtn = $("#modifyBtn")[0];
                        submitBtn.disabled = true;
                        var form=$("form")[0];
                        
                        $.post(form.action, $(form).serializeArray(), function(resp) {
                            if (resp.code === "ok") {
                                alert("修改成功，请返回");
                            } else {
                                toast(resp.message);
                            }
                            submitBtn.disabled = false;
                        });
                    };
                </script>
    <script>
                    var loadModel = function(id) {
                        var url = "api/system/role/get?id=" + id;
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
                    var paintView = function(m) {
                        var form = $("form")[0];
                        form.id.value = $$(m.id);
                        form.code.value = $$(m.code);
                        form.name.value = $$(m.name);
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
                            var url = "api/system/role/del?id=" + id;
                            $.getJSON(url, function(resp) {
                                if (resp.code === "ok") {
                                    alert("删除成功！");
                                    window.location.href = "system/role/manage.do";
                                } else {
                                    toast(resp.message);
                                }
                            });
                        }
                    };
                    
                    var grantRole=function(){
                        location.href="jsp/system/rolemanage/grant-module.jsp?"+id;
                    }
                    
                    
                </script>
                
</body>

</html>