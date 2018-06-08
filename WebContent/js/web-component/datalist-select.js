;+function(){
window.addEventListener("load",function(){
	document.querySelectorAll("select[list]").forEach(function(selectEL){
		var datalist=selectEL.getAttribute("list");
		if(!datalist)return;
		loadDataList(datalist).then(function(data){
			var value=selectEL.getAttribute("value");
			for(var k in data){
				if(data.hasOwnProperty(k)){
					var optionEL=document.createElement("option");
					optionEL.textContent="("+k+")"+data[k];
					optionEL.setAttribute("value",k);
					if(k===value||data[k]===value){
						optionEL.selected=true;
					}
					selectEL.appendChild(optionEL);
				}
			}
		});
	});
},false);

var loadDataList=function(dataType){
	var src="data-"+dataType;
	var scripts=document.querySelectorAll("head>script[src]");
	var scriptTag;
	for(var i=scripts.length;i-->0;){
		scriptTag=scripts[i];
		if(endWith(scriptTag.src,src)){
			return;
		}
	}
	//load 
	return new Promise(function(ok,fail){
		var scriptTag=document.createElement("script");
		scriptTag.onload=function(evt){
			ok(window[src.toUpperCase()]);
		}
		scriptTag.src="js/data/"+src+".js";
		document.head.appendChild(scriptTag);
	});
	
};
var endWith=function(target,endWith){
	for(var i=endWith.length;i-->0;){
		if(target.charAt(i)!==endWith.charAt(i)){
			return false;
		}
	}
	return true;
}

		

}();