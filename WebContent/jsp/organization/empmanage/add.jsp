<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jspf/head.jsp"%>
<script src="js/web-component/datalist-select.js"></script>
<script src="js/yi.autosize-input.js"></script>
<style>
.form-control{
    display: inline-block;
    padding-left: 4px;
    padding-right: 0px;
    width:auto;
    min-width: 200px;
}
form.table{
    padding:0em 1em;
}
form.table p{
    display: flex;
    flex-flow: row wrap;
    justify-content: flex-start;
    align-items: center;
    margin:0;
}
form.table p>span{
    display: flex;
    flex-flow: row nowrap;
    justify-content: flex-start;
    align-items: center;
    max-width: 33.33%;
    padding: 5px;
}
form.table p>span.full{
    min-width: 100%;
}
form.table p>span>label{
    min-width: 7em;
    text-align:right;
    display: inline-block;
    flex: none;
}
form.table p>span>*{
    flex:auto;
}




.form-control.inputError{
    background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAMAAAAoLQ9TAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAGBQTFRF6ikW9isX/oJ1+WM2804q/FQu+U4r3hQL7DMc5RoO0gYD2wsGzjge4op7910y5iERzgMC7j0h8EUl5yQT8Hlw5mxq6XBr+Yp5/YV39VYu8EAi9EQl9lox5CAR+mk5////cSPVLQAAACB0Uk5T/////////////////////////////////////////wBcXBvtAAAAmUlEQVR42mRP2RKEMAij2kular22nv3/vxSob8sDyWQIBMg5h5BLMQECxKIIAYKqEqUQIDAmIQmYjKmQBGWf2mJAWz9WIVtUX/cJE3VFFvae+6V/+tpPWcpuOLTWB/AmKPf8NE1ebpWJxccY/fJNBLwB5nsGuL9g2zAPDh31TYKtrmkc5WBYOVjbda0kFcKWcfx+YfL37SvAAIWoEZFQ+hmUAAAAAElFTkSuQmCC);
    background-repeat: no-repeat;
    background-position: 230px center;
    background-size: 15px 15px;
}


</style>
</head>
<body>
    <header class="content-header">
        <h1>新增员工</h1>
        <ol class="breadcrumb">
            <li>
                <a href="javascript:window.top.dashboard()">
                    <i class="fa fa-dashboard"></i>
                                            首页
                </a>
            </li>
            <li>
                <a href="sysuser/list.do">员工管理</a>
            </li>
            <li>
                <a>新增</a>
            </li>
        </ol>
        <hr>
    </header>
    <main>
    <form class="table"  action="empmanage/add.ajax" method="post">
        <fieldset>
            <legend>基础信息</legend>
            <p>
                <span>
                    <label for="nameEL">*姓名：</label>
                    <input   type="text" name="name" maxlength="16"
                        class="form-control" id="nameEL"
                        placeholder="请填入身份证上姓名">
                </span>
                <span>
                    <label for="idcardEL">*身份证：</label>
                    <input type="text" name="idcard" maxlength="18"
                        class="form-control"
                        placeholder="18位" 
                        id="idcardEL">
                </span>
                
                <span>
                    <label for="idcardEL">*有效期：</label>
                    <span title="请查看身份证背面" class="flex-row">
                        <input type="date" class="form-control grow"
                            style="min-width:auto;">
                        <b>-</b>
                        <input type="date" class="form-control grow"
                            style="min-width:auto;">
                    </span>
                </span>
            </p>

            <p>
                <span>
                    <label>*性别：</label>
                    <input id="genderEL" type="text" name="sex" value="男" readonly
                        class="form-control"
                        title="根据身份证号码自动识别">
                </span>
                 <span>
                    <label>*年龄：</label>
                    <input type="text" name="age" readonly
                        title="根据身份证号码自动识别"
                        id="ageEL"
                        class="form-control">
                </span>
                 <span>
                    <label>*生日：</label>
                    <input id="birthdayEL" type="date" name="birthday" readonly
                    title="根据身份证号码自动识别"
                        class="form-control">
                </span>
                <script>
                	$("#idcardEL").keyup(function(evt){
                		this.classList.remove("inputError");
                		var input=this.value;
                		if(input.length!==18)return;
                		if(!Validate.isIDCard(input)){
                			this.classList.add("inputError");
                			return;
                		}
                		//1取性别
                		//取倒数第二位
						var gender=parseInt(input.charAt(16))%2===0?"女":"男";
                		$("#genderEL").val(gender);
						//2取生日
                		var birthStr=input.substr(6,8);
        				var year=parseInt(birthStr.substr(0,4),10);
        				var month=parseInt(birthStr.substr(4,2),10);
        				var day=parseInt(birthStr.substr(6),10);
        				var birthDay=new Date(year,month-1,day);
        				$("#birthdayEL").val(birthDay.format("yyyy-MM-dd"));
        				//3取年龄
        				var intervalDays=Math.abs(Date.now()-birthDay.getTime());
        				var days= Math.round(intervalDays/(24*60*60*1000));
        				var age=Math.floor(days/365);
        				$("#ageEL").val(age);
                	})
                </script>
            </p>
            <p>
                <span>
                    <label for="homePhoneEL">*家庭电话：</label>
                    <input type="text" name="homePhone" maxlength="32"
                        class="form-control" id="homePhoneEL"
                        placeholder="用于紧急联系">
                </span>
                <span>
                    <label for="nativePlaceEL">*籍贯：</label>
                    <input class="form-control" type="text" id='nativePlaceEL'
                        placeholder="XX省XXX市"
                        name="nativePlace">
                       
                </span>
               <span>
                    <label for="domicilePlaceEL">*户口所在地：</label>
                    <input class="form-control" id='domicilePlaceEL'
                        placeholder="XX省XXX市"
                        name="domicilePlace">
                </span>
            </p>
            <p>
                 <span>
                    <label for="nationalEL">*民族：</label>
                    <input type="hidden" name="national"  >
                    <select list="nationality" class="form-control" id="nationalEL"></select>
                </span>
                 <span>
                    <label for="politicalStatusEL">*政治面貌：</label>
                    <select list="political-status" class="form-control" id="politicalStatusEL"
                        name="politicalStatus">
                    </select>
                </span>
                 <span>
                    <label for="marriageStateEL">*婚姻状况：</label>
                    <select list="marital-status" class="form-control" id="marriageStateEL"
                        name="marriageState">
                    </select>
                </span>
            </p>
            <p>
                 <span class="full">
                    <label for="addressEL">*现居住地：</label>
                    <input  type="text" name="address" maxlength="128"
                        class="form-control" id="addressEL"
                        placeholder="请填写详细地址">
                </span>
            </p>
        </fieldset>
        <fieldset>
            <legend>入职信息</legend>
            <p>
               <span>
                    <label class="control-label" for="departmentEL">*入职部门：</label>
                    <select class="form-control" id="departmentEL"
                        name="departmentId">
                        <option value="1">--请选择--</option>
                    </select>
                </span>
                <span>
                    <label class="control-label" for="hiredateEL">*入职时间：</label>
                    <input type="date" name="hiredate"
                        class="form-control" id="hiredateEL">
                </span>
                <span>
                    <label>*员工工号：</label>
                    <input type="text" name="account" 
                        class="form-control"
                        placeholder="根据部门和入职时间自动生成">
                </span>
            </p>
            <p>
               <span>
                    <label for="workphoneEL">办公电话：</label>
                    <input type="text" name="workPhone" maxlength="32"
                        class="form-control" id="workphoneEL"
                        placeholder="">
                </span>
               
                <span>
                    <label for="workEmailEL">*工作邮箱：</label>
                    <input type="text"  name="workEmail"
                      class="form-control" id="workEmailEL"
                      maxlength="32"
                    placeholder="尽量使用拼音">
                        <b> @neusoft.com</b>
                </span>
                <span>
                </span>
            </p>
            <p>
               <span class="full">
                    <label for="remarkEL">备注：</label>
                      <input type="text" name="remark" maxlength="128"
                        class="form-control" id="remarkEL"
                        placeholder="不超过128个字符">
                </span>
            </p>
        </fieldset>
        <p style="margin: 1em">
                <button type="submit" class="btn btn-lg btn-primary">保存</button>
                  　　　
                <a type="button" class="btn btn-info" href="sysuser/list.do">返回</a>
        </p>
    </form>
    </main>
    <script>
					$(function() {
						loadDepartments();
					});
				</script>
    <script>
					//加载部门
					var loadDepartments = function() {
						var url = "deptmanage/list.ajax";
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
									$(
											"<option/>",
											{
												text : item.name + "("
														+ item.code + ")",
												value : item.id
											}).appendTo($selectEL);
								});
							} else {
								toast(resp.message);
							}
						});
					};

					$("form")
							.submit(
									function() {
										//防止重复提交
										var submitBtn = $(
												"button[type='submit']", this)[0];
										submitBtn.disabled = true;
										$
												.post(
														this.action,
														$(this)
																.serializeArray(),
														function(resp) {
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