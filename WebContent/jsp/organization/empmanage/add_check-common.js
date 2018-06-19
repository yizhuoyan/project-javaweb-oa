
    //自动填入性别，年龄，出生日期
    $("#idcardEL").keyup(function(evt) {
        this.classList.remove("error");
        var input = this.value;
        if(input.length !== 18)return;
        if(!Validate.isIDCard(input)){
            this.classList.add("error");
            return;
        }
        //1取性别
        //取倒数第二位
        var gender = parseInt(input.charAt(16)) % 2 === 0 ? "女" :"男";
        $("#genderEL").val(gender);
        //2取生日
        var birthStr = input.substr(6,8);
        var year = parseInt(birthStr.substr(0,4),10);
        var month = parseInt(birthStr.substr(4,2),10);
        var day = parseInt(birthStr.substr(6),10);
        var birthDay = new Date(year,month - 1,day);
        $("#birthdayEL").val(birthDay.format("yyyy-MM-dd"));
        //3取年龄
        var intervalDays = Math.abs(Date.now() -birthDay.getTime());
        var days = Math.round(intervalDays /(24 * 60 * 60 * 1000));
        var age = Math.floor(days / 365);
        $("#ageEL").val(age);
        //4取籍贯
        var regionCode=input.substr(0,6);
        var idcardRegionData=window["idcard-region"];
        if(idcardRegionData){
            //籍贯
            $("#nativePlaceEL").val(idcardRegionData[regionCode]);
            //户口所在地
            $("#domicilePlaceEL").val(idcardRegionData[regionCode]);
        }
    });
    
    //入职日期默认当前
    $("#hiredateEL").val(Date.format(new Date(),"yyyy-MM-dd"));
    
    //加载部门
    var loadDepartments = function(value) {
        var url = "api/organization/department/list?type=simple";
        $.getJSON(url, function(resp) {
            if (resp.code === "ok") {
                var $selectEL = $("#departmentIdEL");
                var treeSelect=new TreeSelect($selectEL);                   
                var rows = resp.data;
                for(var i=0;i<rows.length;i++){
                    var r=rows[i];
                    var content="("+r.code+")"+r.name;
                    var option=treeSelect.insert(r.parentId,r.id,content,{
                        value:r.id,
                        selected:value===r.id
                    });
                }
                treeSelect.done();
            } else {
                toast(resp.message);
            }
        });
    };