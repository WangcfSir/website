/**
 * yanqb
 *
 */
// 字符串和日期转换
function dateToString(date){
    var year = date.getFullYear();
    var month =(date.getMonth() + 1).toString();
    var day = (date.getDate()).toString();
    if (month.length == 1) {
        month = "0" + month;
    }
    if (day.length == 1) {
        day = "0" + day;
    }
    var dateTime = year + "-" + month + "-" + day;
    return dateTime;
}
function stringToDate(dateStr){
    var dateArr = dateStr.split("-");
    var year = parseInt(dateArr[0]);
    var month;
    //处理月份为04这样的情况
    if(dateArr[1].indexOf("0") == 0){
        month = parseInt(dateArr[1].substring(1));
    }else{
        month = parseInt(dateArr[1]);
    }
    var day = parseInt(dateArr[2]);
    var date = new Date(year,month -1,day);
    return date;
}

//验证邮件地址
function checkEmail(email){
    if( email == null || email === "" ){
        return true;
    }
    var regular = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
    var regExp = new RegExp(regular);
    return regExp.test(email);
}

//判断字符串是否为手机
function checkMobile(value){
    if( value == null || value === "" ){
        return true;
    }
    var regular =/^[1][3,5,8][0-9]{9}$/;
    var re = new RegExp(regular);
    return re.test(value);
}


/**********************************************************************************************************************
 *
 */

/**
 * 检查输入对像是否为数字,供JS使用
 * @return {Boolean}
 */
function checkObjIsNum(obj) {
	if (checkIntNum(obj.val()) == 0) {
		// displayTips("输入项只能为数字");
		obj.focus();
		return false;
	}
	return true;
}


/**
 * 检查输入对像是否为空,供JS使用
 *
 * @return {Boolean}
 */
function checkObjIsNull(obj) {
	// console.dir(obj);
	if(!obj||!obj.val()||!$.trim(obj.val())){
		return false
	}
	return true;
}


/**
 * 检查开始日期是否大于结束日期
 *            beginDateObj 开始日期表单对像
 *            endDateObj 结束日期表单对像
 * @return {Boolean} 如果开始日期大于结束日期,返回false,否则返回true
 */
function checkBeginDateAndEndDate(beginDateObj, endDateObj) {
	var beginYear, beginMonth, benginDay, endYear, endMonth, endDay;
	var beginVal = beginDateObj.val();
	var endVal = endDateObj.val();
	var beginArr = beginVal.split('-');
	var endArr = endVal.split('-');
	if (!compareDate2(beginArr[0], beginArr[1], beginArr[2], endArr[0],
			endArr[1], endArr[2])) {
		// displayTips("开始日期不能大于结束日期");
		beginDateObj.focus();
		return false;
	}
	return true;
}

/**
 * 检查开始日期是否大于结束日期
 *            beginDateObj 开始日期表单值
 *            endDateObj 结束日期表单对像
 * @return {Boolean} 如果开始日期大于结束日期,返回false,否则返回true
 */
function checkBeginDateAndEndDateValue(beginVal, endVal) {
	var beginYear, beginMonth, benginDay, endYear, endMonth, endDay;
	var beginArr = beginVal.split('/');
	var endArr = endVal.split('/');
	if (!compareDate2(beginArr[0], beginArr[1], beginArr[2], endArr[0],
			endArr[1], endArr[2])) {
		// displayTips("开始日期不能大于结束日期");
		return false;
	}
	return true;
}


/**
 * 得到字符串的字符长度（一个汉字占两个字符长） 将验证的函数改成128以类的为单字符。避免“·”符号
 */
function getBytesLength(str) {
	var i;
	var sum = 0;
	for (i = 0; i < str.length; i++) {
		if ((str.charCodeAt(i) >= 0) && (str.charCodeAt(i) <= 128))
			sum = sum + 1;
		else
			sum = sum + 2;
	}
	return sum;
}

/**
 * 去掉字符串前后空格
 */
function trim(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
}

// 函数名：checkNUM
// 功能介绍：检查是否为数字
// 参数说明：要检查的数字
// 返回值：1为是数字，0为不是数字
function checkIntNum(Num) {
	var i, j, strTemp;
	strTemp = "0123456789";
	if (Num.length == 0)
		return 0
	for (i = 0; i < Num.length; i++) {
		j = strTemp.indexOf(Num.charAt(i));
		if (j == -1) {
			// 说明有字符不是数字
			return 0;
		}
	}
	// 说明是数字
	return 1;
}

// 函数名：checkEmail
// 功能介绍：检查是否为Email Address
// 判断的规则是不能以'@'或'.'开头，'@'后面至少要有三个字符，不能以'.'结束
// 参数说明：要检查的字符串
// 返回值：0：不是 1：是
function checkEmail(a) {
	re = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
	if (!re.test(a)) {
		return 0;
	}
	return 1;
}

// 函数名：checkTEL
// 功能介绍：检查是否为电话号码
// 参数说明：要检查的字符串
// 返回值：1为是合法，0为不合法
function checkTel(tel) {
	var i, j, strTemp;
	strTemp = "0123456789-";
	for (i = 0; i < tel.length; i++) {
		j = strTemp.indexOf(tel.charAt(i));
		if (j == -1) {
			// 说明有字符不合法
			return 0;
		}
	}
	// 说明合法
	return 1;
}

// 函数名：checkChar
// 功能介绍：检查是否含有非字母字符
// 参数说明：要检查的字符串
// 返回值：0：含有 1：全部为字母
function checkChar(str) {
	var strSource = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.()& ";
	var ch;
	var i;
	var temp;
	for (i = 0; i <= (str.length - 1); i++) {
		ch = str.charAt(i);
		temp = strSource.indexOf(ch);
		if (temp == -1) {
			return 0;
		}
	}
	return 1;
}

/*
 * 检查只有数字和26个字母 //返回值：0：含有 1：全部为数字或字母
 */
function checkCharOrNum(str) {
	var strSource = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	var ch;
	var i;
	var temp;
	for (i = 0; i <= (str.length - 1); i++) {
		ch = str.charAt(i);
		temp = strSource.indexOf(ch);
		if (temp == -1) {
			return 0;
		}
	}
	return 1;
}
/*
 * 检查只有数字和'.'p字符 //返回值：0：含有 ,1：全部为数字或'.'符号
 */
function checkPointOrNum(str) {
	var strSource = "0123456789.";
	var ch;
	var i;
	var temp;
	for (i = 0; i <= (str.length - 1); i++) {
		ch = str.charAt(i);
		temp = strSource.indexOf(ch);
		if (temp == -1) {
			return 0;
		}
	}
	return 1;
}
// 函数名：checkCharOrDigital
// 功能介绍：检查是否含有非数字或字母
// 参数说明：要检查的字符串
// 返回值：0：含有 1：全部为数字或字母
function checkCharOrDigital(str) {
	var strSource = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.()& ";
	var ch;
	var i;
	var temp;
	for (i = 0; i <= (str.length - 1); i++) {
		ch = str.charAt(i);
		temp = strSource.indexOf(ch);
		if (temp == -1) {
			return 0;
		}
	}
	return 1;
}

// 函数名：checkChinese
// 功能介绍：检查是否含有汉字
// 参数说明：要检查的字符串
// 返回值：0：含有 1：没有
function checkChinese(strTemp) {
	var i, sum;
	for (i = 0; i < strTemp.length; i++) {
		if ((strTemp.charCodeAt(i) < 0) || (strTemp.charCodeAt(i) > 255)) {
			return 0;
		}
	}
	return 1;
}

// 函数名：compareDate()
// 功能介绍： 比较日期大小
// 参数说明：beginYear开始年，beginMonth开始月,benginDay开始日
// endYear结束年，endMonth结束月，endMonth结束日
// 返回值：0：true 表示 开始时间大于等于结束时间，false 相反
function compareDate(beginYear, beginMonth, benginDay, endYear, endMonth,
		endDay) {
	var date1 = new Date(beginYear, beginMonth - 1, benginDay);
	var date2 = new Date(endYear, endMonth - 1, endDay);
	if (date1.getTime() >= date2.getTime()) {
		return false;
	}
	return true;
}

// 清除左边空格
function js_ltrim(deststr) {
	if (deststr == null)
		return "";
	var pos = 0;
	var retStr = new String(deststr);
	if (retStr.lenght == 0)
		return retStr;
	while (retStr.substring(pos, pos + 1) == " ")
		pos++;
	retStr = retStr.substring(pos);
	return (retStr);
}

// 清除右边空格
function js_rtrim(deststr) {
	if (deststr == null)
		return "";
	var retStr = new String(deststr);
	var pos = retStr.length;
	if (pos == 0)
		return retStr;
	while (pos && retStr.substring(pos - 1, pos) == " ")
		pos--;
	retStr = retStr.substring(0, pos);
	return (retStr);
}

// 清除左边和右边空格
function js_trim(deststr) {
	if (deststr == null) {
		return "".trim();
	}
	var retStr = new String(deststr);
	var pos = retStr.length;
	if (pos == 0) {
		return retStr.trim();
	}
	retStr = js_ltrim(retStr);
	retStr = js_rtrim(retStr);
	return retStr.trim();
}

// 日期比较只正对yyyy-MM-dd类型
function compareDates(date1, date2) {
	return replaceDate(date1) - replaceDate(date2);
}

function replaceDate(date) {
	if (date.indexOf('-') >= 0) {
		while (date.indexOf('-') >= 0) {
			date = date.replace('-', '')
		}
	}
	return parseInt(date, 10);
}

/***********************************************************************************************************************
 */

//判断是否为数字且不能以零开头
function dataCheck(value){
	var str = /^[1-9]\d*$/;
	if(!str.test(value)){
		return false;
	}
	return true;
}

//对日期加减天数，返回格式yyyy-MM-dd,输入格式也是yyyy-MM-dd
function addDay(srcDay,num){
    var arr1=srcDay.split('-');
    arr1[1]=arr1[1]-1;
    var d1=new Date(arr1[0],arr1[1],arr1[2]);
    d1.setDate(d1.getDate() + num);
    return formateDate(d1);
}
//对日期进行加减月份 返回格式yyyy-MM-dd
function addMonth(srcDay,num){
    var arr1=srcDay.split('-');
    arr1[1]=arr1[1]-1;
    var d1=new Date(arr1[0],arr1[1],arr1[2]);
    d1.setMonth(d1.getMonth() + num);
    return formateDate(d1);
}
//对日期进行加减年份 返回格式yyyy-MM-dd
function addYear(srcDay,num){
    var arr1=srcDay.split('-');
    var d1=new Date(arr1[0],arr1[1],arr1[2]);
    d1.setDate(d1.getFullYear() + num);
    return formateDate(d1);
}
//计算两个日期之间相差天数 日期格式是yyyy-MM-dd
function DiffDay(toDate,fromDate){
    var arr1=fromDate.split('-');
    var d1=new Date(arr1[0],arr1[1]-1,arr1[2]);
    var arr2=toDate.split('-');
    var d2=new Date(arr2[0],arr2[1]-1,arr2[2]);
    var iDays=Math.ceil((d2-d1)/1000/60/60/24);
    return iDays;
}
//计算两个日期之间相差月数 日期格式是yyyy-MM-dd
function DiffMonth(toDate,fromDate){
    var arr1=fromDate.split('-');
    var d1=new Date(arr1[0],arr1[1],arr1[2]);
    var arr2=toDate.split('-');
    var d2=new Date(arr2[0],arr2[1],arr2[2]);
    var iMonth=Math.ceil((d2-d1)/1000/60/60/24/30);
    return iMonth;
}
//当前时间，格式为yyyy-MM-dd
function getDate(){
//获得当前日期
    var now=new Date();
    var month=now.getMonth();
    now.setMonth(month);
    return formateDate(now);
}

function validataBirthday(birthday){
    var reg = new  RegExp(/^(\d{6}|\d{8})$/);
    if(!reg.test(birthday)){
        alert("生日格式不正确!");
        return false;
    }
    var y=Number(birthday.substring(0,4));
    if(y<1900||y>3000){
        alert("请输入正确年份!");
        return false;
    }
    var m=Number(birthday.substring(4,6));
    if(m<1||m>12){
        alert("请输入正确月份!");
        return false;
    }
    var d=1;
    if(birthday.length==8){
        reg = new  RegExp(/^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229)$/);
        if(!reg.test(birthday)){
            alert("生日格式不正确!");
            return false;
        }
        d=Number(birthday.substring(6,8));
    }
    var n=new Date();
    if(y>n.getYear()
        ||(y==n.getYear()&&m>(n.getMonth()+1))
        ||(y==n.getYear()&&m==(n.getMonth()+1)&&d>n.getDate())
    ){
        //alert("出生日期不能大于当天!");
        //return false;
    }
    return true;
}
function getAge(birthday){
    var bd=birthday.substring(0,4)+"/"+birthday.substring(4,6)+"/"+birthday.substring(6,8);
    var birthday=new Date(bd);
    var d=new Date();
    var age = d.getFullYear()-birthday.getFullYear()-((d.getMonth()<birthday.getMonth()|| d.getMonth()==birthday.getMonth() && d.getDate()<birthday.getDate())?1:0);
    return age;
}
//从身份证获取生日和性别
function getBirthdayAndSexByIdNo(idNo){
    if(idNo.length == 15){
        return parse15Id(idNo)
    }else if(idNo.length == 18){
        return parse18Id(idNo)
    }else{
        return new Array(null,null);
    }
}

//18位身份证
function parse18Id(idNo){
    var re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
    var arrSplit = idNo.match(re);
    var birthday = arrSplit[2] + arrSplit[3] + arrSplit[4];
    var sex;
    if(idNo.substr(16,1) % 2 == 0){
        sex=2;
    }else{
        sex=1;
    }
    return new Array(birthday,sex);
}
//公共验证姓名方法
/**
 目前姓名中允许中英文、空格 ,
 限制不允许超过50个长度，一个中文按照2个长度计算
 **/
function validateName(name){
    name=trim(name);
    var len=getStrLength(name);
    if(len<=0){
        alert("姓名不能为空");
        return false;
    }
    if(len>50){
        alert("姓名长度不能超过50个字符");
        return false;
    }
    return true;
}
/**
 公共验证证件号码方法,目前不验证里面什么特殊字符
 只限制长度不能超过规定长度18个长度，一个中文按照2个字符计算
 **/
function validateIdNo(idNo){
    idNo=trim(idNo);
    var len=getStrLength(idNo);
    if(len<=0){
        alert("证件号码不能为空");
        return false;
    }
    if(len>18){
        alert("证件号码长度不能超过18个字符");
        return false;
    }
    return true;
}
/**
 获取字符串长度，中文按照2个长度计算，英文按照一个长度计算
 **/
function getStrLength(str) {
    var cArr = str.match(/[^\x00-\xff]/ig);
    return str.length + (cArr == null ? 0 : cArr.length);
}

/**
 * 所有输入框去前后空格
 **/
function trimAllInput(){
    var allInput = $("input[type='text']");
    for(var i=0;i<allInput.length;i++){
        var v = $(allInput[i]).val();
        var v_trim = $.trim(v);
        if(v&&v!=v_trim){
            $(allInput[i]).val(v_trim);
        }
    }
}

/**
 * 隐藏电话号码中间4位
 **/
function hidePhoneNo(phoneNo){
    if(!phoneNo||phoneNo.length<=4) return phoneNo;
    var len=phoneNo.length;
    var suffix = phoneNo.substr(len-4);
    if(len<8){
        return someChar(len-4,"*") + suffix;
    }else{
        return phoneNo.substr(0,len-8) + someChar(4,"*") + suffix;
    }
}
function someChar(num,c){
    var str="";
    for(;num;num--){
        str+=c;
    }
    return str;
}
/**
 * 根据name获取checkbox的值,同样适用radio
 */
function getCheckboxValue(name){
    var result = "";
    $("[name="+name+"]").each(function(){
        if(this.checked){
            result += $(this).val()+",";
        }
    });
    result = result.substr(0,result.length-1);
    return result;
}

var allScriptError="";
function logError(error){
    allScriptError+="\n"+error;
}
function showScriptError(){
    alert(allScriptError);
}
window.onerror = function(errorMessage, scriptURI, lineNumber,columnNumber,errorObj){
    logError(errorMessage+"|"+scriptURI+"|"+lineNumber+"|"+columnNumber+"|"+errorObj);
};

/**
 * 比较两个版本的大小
 * @param ver1 版本1
 * @param ver2 版本2
 * @returns {boolean}
 */
function compareVersion(ver1,ver2) {
    var v1 = ver1.split(".");
    var v2 = ver2.split(".");
    // 存储两个分割后版本中最大的长度
    var arrLength;
    if (v1.length >= v2.length) {
        arrLength = v1.length;
    } else {
        arrLength = v2.length;
    }
    for (var i = 0; i < arrLength; i++) {

        // 防止1和01比较出错
        var first = parseInt(v1[i]);
        var second = parseInt(v2[i]);
        /*
         * 1.遍历切割后的版本号，比较大小
         * 2.若不相等，直接返回比较结果；
         * 3.若相等，循环下次，当两个版本长度不同，最长的版本最大
         */
        if (first !== second) {
            return first > second;
        } else if (v2[i] === undefined) {
            return true;
        } else if (v1[i] === undefined) {
            return false;
        }
    }
}

$(function() {
    $(".hide-phone").each(function(index){
        $(this).text(hidePhoneNo($(this).text()));
        $(this).val(hidePhoneNo($(this).val()));
    });

    // jQuery 1.9 之后不再支持live方法
    var jqVersion = jQuery.fn.jquery;
    if (compareVersion("1.9.0",jqVersion)) {
        $("input[type='text']").live("blur", function () {
            var v = this.value;
            var v_trim = $.trim(v);
            this.value = v_trim;
        });
    } else {
        $("input[type='text']").on("blur", function () {
            var v = this.value;
            var v_trim = $.trim(v);
            this.value = v_trim;
        });
    }
});
