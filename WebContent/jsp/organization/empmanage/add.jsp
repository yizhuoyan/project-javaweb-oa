<%@page pageEncoding="utf-8"%>
<%@taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jspf/head.jsp"%>
<script src="js/web-component/datalist-select.js"></script>
<style>
form p>span {
	flex-basis: 33%;
}
</style>
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
                <a href="organization/employee/manage.do">员工管理</a>
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
        class="label7em"
        action="api/organization/employee/add"
        method="post">
        <fieldset>
            <legend>基础信息</legend>
            <p>
                <span>
                    <label for="nameEL">*姓名：</label>
                    <input
                        type="text"
                        name="name"
                        maxlength="16"
                        class="grow"
                        id="nameEL"
                        required="required"
                        placeholder="请填入身份证上姓名">
                </span>
                <span>
                    <label for="idcardEL">*身份证：</label>
                    <input
                        type="text"
                        name="idcard"
                        maxlength="18"
                        class="grow"
                        placeholder="18位"
                        required="required"
                        id="idcardEL">
                </span>

                <span>
                    <label for="idcardEL">*有效期：</label>
                    <span
                        title="请查看身份证背面"
                        class="grow layout-row">
                        <input
                            type="date"
                            class="grow">
                        <b>-</b>
                        <input
                            type="date"
                            class="grow">
                    </span>
                </span>
            </p>

            <p>
                <span>
                    <label>*性别：</label>
                    <input
                        id="genderEL"
                        type="text"
                        name="sex"
                        value="男"
                        readonly
                        class="grow"
                        required="required"
                        title="根据身份证号码自动识别">
                </span>
                <span>
                    <label>*年龄：</label>
                    <input
                        type="text"
                        name="age"
                        readonly
                        title="根据身份证号码自动识别"
                        id="ageEL"
                        required="required"
                        class="grow">
                </span>
                <span>
                    <label>*生日：</label>
                    <input
                        id="birthdayEL"
                        type="date"
                        name="birthday"
                        readonly="readonly"
                        required="required"
                        title="根据身份证号码自动识别"
                        class="grow">
                </span>
            </p>
            <p>
                <span>
                    <label for="homePhoneEL">*家庭电话：</label>
                    <input
                        type="text"
                        name="homePhone"
                        maxlength="32"
                        class="grow"
                        required="required"
                        id="homePhoneEL"
                        placeholder="用于紧急联系">
                </span>
                <span>
                    <label for="nativePlaceEL">*籍贯：</label>
                    <input
                        class="grow"
                        type="text"
                        id='nativePlaceEL'
                        placeholder="XX省XXX市"
                        required="required"
                        name="nativePlace">
                </span>
                <span>
                    <label for="domicilePlaceEL">*户口所在地：</label>
                    <input
                        class="grow"
                        type="text"
                        id='domicilePlaceEL'
                        placeholder="XX省XXX市"
                        required="required"
                        name="domicilePlace">
                </span>
            </p>
            <p>
                <span>
                    <label for="nationalityEL">*民族：</label>
                    <select
                        list="nationality"
                        name="nationality"
                        class="grow"
                        id="nationalityEL"></select>
                </span>
                <span>
                    <label for="politicalStatusEL">*政治面貌：</label>
                    <select
                        list="political-status"
                        class="grow"
                        id="politicalStatusEL"
                        name="politicalStatus">
                    </select>
                </span>
                <span>
                    <label for="marriageStateEL">*婚姻状况：</label>
                    <select
                        list="marital-status"
                        class="grow"
                        id="marriageStateEL"
                        name="marriageState">
                    </select>
                </span>
            </p>
            <p>
                <span>
                    <label for="addressEL">*现居住地：</label>
                    <input
                        type="text"
                        name="address"
                        maxlength="128"
                        class="grow"
                        id="addressEL"
                        required="required"
                        value="测试数据现居住地"
                        placeholder="请填写详细地址">
                </span>
            </p>
        </fieldset>
        <fieldset>
            <legend>入职信息</legend>
            <p>
                <span>
                    <label
                        class="control-label"
                        for="departmentIdEL">*入职部门：</label>
                    <select
                        class="grow"
                        id="departmentIdEL"
                        name="departmentId">
                        <option value="">--请选择--</option>
                    </select>
                </span>
                <span>
                    <label
                        class="control-label"
                        for="hiredateEL">*入职日期：</label>
                    <input
                        type="date"
                        name="hiredate"
                        class="grow"
                        required="required"
                        id="hiredateEL">
                </span>
                <span>
                    <label for="accountEL">*员工工号：</label>
                    <input
                        type="text"
                        readonly
                        name="account"
                        id="accountEL"
                        required="required"
                        class="grow"
                        placeholder="根据部门和入职时间自动生成">
                </span>
                <script>
                                                                    $("#departmentIdEL").change(function(evt) {
                                                                        var departmentId = this.value;
                                                                        var hiredate = $("#hiredateEL").val();
                                                                        if (hiredate && departmentId) {
                                                                            generateEmployeeAccount(departmentId, hiredate);
                                                                        }
                                                                    });
                                                                    $("#hiredateEL").change(function(evt) {
                                                                        var hiredate = this.value;
                                                                        var departmentId = $("#departmentIdEL").val();
                                                                        if (hiredate && departmentId) {
                                                                            generateEmployeeAccount(departmentId, hiredate);
                                                                        }
                                                                    });

                                                                    var generateEmployeeAccount = function(deptId, hiredate) {
                                                                        var url = "api/organization/employee/nextAccount";
                                                                        $.getJSON(url, {
                                                                            departmentId : deptId,
                                                                            hiredate : hiredate
                                                                        }).then(function(resp) {
                                                                            if (resp.code === "ok") {
                                                                                $("#accountEL").val(resp.data);
                                                                            }
                                                                        });
                                                                    };
                                                                </script>
            </p>
            <p>
                <span>
                    <label for="workphoneEL">*办公电话：</label>
                    <input
                        type="text"
                        name="workPhone"
                        maxlength="32"
                        class="grow"
                        id="workphoneEL"
                        required="required"
                        placeholder="">
                </span>

                <span>
                    <label for="workEmailEL">*工作邮箱：</label>
                    <input
                        type="text"
                        name="workEmail"
                        class="grow"
                        id="workEmailEL"
                        required="required"
                        readonly
                        placeholder="邮箱名默认通过名字自动生成">
                    <script title="邮箱名默认通过名字自动生成">
                                                                                    $("#nameEL").blur(function(evt) {
                                                                                        var name = this.value.trim();
                                                                                        if (name.length > 0) {
                                                                                            $.getJSON("api/organization/employee/generateWorkEmail", {
                                                                                                name : name
                                                                                            }, function(resp) {
                                                                                                if (resp.code === "ok") {
                                                                                                    $("#workEmailEL").val(resp.data);
                                                                                                } else {
                                                                                                    $("#workEmailEL").val(resp.message);
                                                                                                }
                                                                                            });
                                                                                        }
                                                                                    });
                                                                                </script>
                </span>
                <span></span>
            </p>
            <p>
                <span class="full">
                    <label for="remarkEL">备注：</label>
                    <input
                        type="text"
                        name="remark"
                        maxlength="128"
                        class="grow"
                        id="remarkEL"
                        placeholder="不超过128个字符">
                </span>
            </p>
        </fieldset>
    </form>
    <script src="jsp/organization/empmanage/add_check-common.js"></script>
    <script>
                    $(function() {
                        loadDepartments();
                    });
                    $("form").submit(function() {
                        //防止重复提交
                        var submitBtn = $("#submitFormBtn")[0];
                        submitBtn.disabled = true;
                        $.post(this.action, $(this).serializeArray(), function(resp) {
                            if (resp.code === "ok") {
                                if (!confirm("新增成功，是否继续添加？")) {
                                    window.location.href = "empmanage/list.do";
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