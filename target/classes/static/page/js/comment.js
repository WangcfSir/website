//时间对象的格式化  format="yyyy-MM-dd hh:mm:ss";
Date.prototype.format = function(format){
    var o = {
        "M+" : this.getMonth() + 1,
        "d+" : this.getDate(),
        "h+" : this.getHours(),
        "m+" : this.getMinutes(),
        "s+" : this.getSeconds(),
        "q+" : Math.floor((this.getMonth() + 3) / 3),
        "S" : this.getMilliseconds()
    };
    if (/(y+)/.test(format)){
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4- RegExp.$1.length));
    }
    for (var k in o){
        if (new RegExp("(" + k + ")").test(format)){
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? o[k]: ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};


// 加入收藏夹
function addCollect(){
    alert('您可以尝试通过快捷键 ctrl + D 加入到收藏夹~');
    $('.list_lh li:even').addClass('lieven');
}