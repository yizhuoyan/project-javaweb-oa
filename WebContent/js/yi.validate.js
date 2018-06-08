;+function(window,document){
	
	var validateIDCard=function(idcard){
		if(idcard.length!==18)return false;
		//1-17位系数
		var MODULUS17=[7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2];
		//求1-17位每一位和系数相乘之和
		var sum=0;
		for(var i=idcard.length-1;i-->0;){
			sum+=(parseInt(idcard.charAt(i),10))*MODULUS17[i];
		}
		//除11求余验证
		return "10X98765432".charAt(sum%11)==idcard.charAt(17);
	};
	
	
	window.Validate={
			isIDCard:validateIDCard
	};
	
}(window,document);