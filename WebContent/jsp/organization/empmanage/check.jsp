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
                <a href="/organization/employee/manage.do">员工管理</a>
            </li>
            <li>
                <a>查看</a>
            </li>
        </ol>
    </nav>
    <section class="action-bar layout-row">
        <button
            type="submit"
            form="modForm"
            id="submitFormBtn">提交修改</button>
        <a
            class="btn"
            href="javascript:deleteConfirm()">删除</a>
    </section>
    <form
        id="modForm"
        class="label7em"
        action="api/organization/employee/mod"
        method="post">
        <input
            type="hidden"
            name="id">
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
                        readonly
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
                        required="required"
                        class="grow"
                        id="homePhoneEL"
                        placeholder="用于紧急联系">
                </span>
                <span>
                    <label for="nativePlaceEL">*籍贯：</label>
                    <input
                        class="grow"
                        type="text"
                        required="required"
                        id='nativePlaceEL'
                        placeholder="XX省XXX市"
                        name="nativePlace">
                </span>
                <span>
                    <label for="domicilePlaceEL">*户口所在地：</label>
                    <input
                        type="text"
                        class="grow"
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
                <span class="full">
                    <label for="addressEL">*现居住地：</label>
                    <input
                        type="text"
                        name="address"
                        maxlength="128"
                        class="grow"
                        required="required"
                        id="addressEL"
                        value="测试数据现居住地"
                        placeholder="请填写详细地址">
                </span>
            </p>
        </fieldset>
        <fieldset>
            <legend>入职信息</legend>
            <p>
                <span>
                    <label for="departmentIdEL">*入职部门：</label>
                    <select
                        class="grow"
                        id="departmentIdEL"
                        name="departmentId">
                        <option value="">--请选择--</option>
                    </select>
                </span>
                <span>
                    <label for="hiredateEL">*入职日期：</label>
                    <input
                        type="date"
                        name="hiredate"
                        required="required"
                        class="grow"
                        id="hiredateEL">
                </span>
                <span>
                    <label>*员工工号：</label>
                    <input
                        type="text"
                        id="accountEL"
                        class="grow"
                        required="required"
                        readonly>
                </span>
                <script>
                                                                    
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
                        required="required"
                        id="workphoneEL"
                        placeholder="">
                </span>

                <span>
                    <label for="workEmailEL">*工作邮箱：</label>
                    <input
                        type="text"
                        class="grow"
                        required="required"
                        id="workEmailEL"
                        readonly>
                </span>
                <span></span>
            </p>
            <p>
                <span>
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
                    var id = window.location.search.substr(1);
                    $(function() {
                        //加载员工信息
                        loadModel(id).then(function(m) {
                            paintView(m);
                            //添加表单提交事件监听
                            $("form").submit(handleFormSubmit);

                        });

                    });
                </script>
    <script>
                    var loadModel = function(id) {
                        return new Promise(function(ok, fail) {
                            var url = "api/organization/employee/get?id=" + id;
                            $.getJSON(url, function(resp) {
                                if (resp.code === "ok") {
                                    ok(resp.data);
                                } else {
                                    toast(resp.message);
                                }
                            });
                        });
                    };
                    var paintView = function(m) {
                        var form = $("form")[0];
                        //基础信息
                        form.id.value = m.id;
                        form.name.value = $$(m.name);
                        form.idcard.value = m.idcard;
                        $("#genderEL").val(m.male ? "男" : "女");
                        $("#ageEL").val(m.age);
                        $("#birthdayEL").val(m.birthday);
                        form.homePhone.value = $$(m.homePhone);
                        form.domicilePlace.value = $$(m.domicilePlace);
                        form.nativePlace.value = $$(m.nativePlace);
                        form.nationality.value = m.nationality;
                        form.politicalStatus.value = m.politicalStatus;
                        form.marriageState.value = m.marriageState;
                        form.address.value = $$(m.address);

                        //入职信息
                        //加载所有部门
                        loadDepartments($$("id", m.department));
                        form.hiredate.value = m.hiredate;
                        $("#accountEL").val(m.account);
                        form.workPhone.value = $$(m.workPhone);
                        $("#workEmailEL").val($$(m.workEmail));
                        form.remark.value = $$(m.remark);
                    };
                </script>
    <script>
                    var handleFormSubmit = function() {
                        //防止重复提交
                        var submitBtn = $("#submitFormBtn")[0];
                        submitBtn.disabled = true;
                        $.post(this.action, $(this).serializeArray(), function(resp) {
                            if (resp.code === "ok") {
                                alert("修改成功");
                                //window.location.href = "empmanage/list.do";
                            } else {
                                toast(resp.message);
                            }
                            submitBtn.disabled = false;
                        });
                        return false;
                    };
                    var deleteConfirm = function() {
                        if (window.confirm("确认删除？")) {
                            var url = "api/organization/employee/del?id=" + id;
                            $.getJSON(url, function(resp) {
                                if (resp.code === "ok") {
                                    window.top.toast("删除成功！");
                                    window.location.href = "organization/employee/manage.do";
                                } else {
                                    toast(resp.message);
                                }
                            });
                        }
                    };
                </script>
</body>

</html>