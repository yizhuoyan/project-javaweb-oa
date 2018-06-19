;+function(window,document){
    
window.$$=(function(){
    var div=document.createElement("div");
    var $$=function(){
        if(arguments.length===0)return "";
        if(arguments.length===1){
            var v=arguments[0];
            if(typeof v==="undefined"||v===null||v===""){
                return "";
            }
            if(typeof v==="number"||typeof v==="boolean"){
                return String(v);
            }
            div.textContent=String(v);
            return div.innerHTML;
        }
        if(arguments.length===2){
            var current=arguments[1];
            if(!current){
                return $(arguments[0]);
            }
            var exps=arguments[0].split("\.");
            for(var i=0;i<exps.length;i++){
                current=current[exps[i]];
                if(typeof current==="undefined"||current===null){
                    current="";
                    break;
                }
            }
            return $$(current);
        }
    }
    return $$; 
})();

$.isBlank=function(s){
    if(s===null||typeof s==="undefined"){
        return true;
    }
    if(typeof s==="string"){
        return s.trim().length==0;  
    }
    return false;
};

Date.prototype.format = function (fmt) {
    fmt = fmt || "yyyy-MM-dd HH:mm:ss";
    var date = this;
    var o = {
        "M+": date.getMonth() + 1, //月份
        "d+": date.getDate(), //日
        "H+": date.getHours(), //小时
        "m+": date.getMinutes(), //分
        "s+": date.getSeconds(), //秒
        "S": date.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    var k;
    for (k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
};

Date.format = function (d, fmt) {
    fmt=fmt||'yyyy-MM-dd HH:mm:ss';
    if (typeof d === "number") {
        return new Date(d).format(fmt);
    } else if (d instanceof Date) {
        return d.format(fmt);
    } else if(typeof d==="undefined"||d===null){
        return "";
    }else{
        return String(d);
    }
};

window.location.params=function(name){
    var result = window.location.search.match(new RegExp("(?:\\?|&)" + name + "=([^&]*)(?:[$#&]?)"));
    if (result)return decodeURI(result[1]);
    return null;
};

}(window,document);