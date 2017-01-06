;(function(window){
  //window.advertise_space_id必须存在
if(window.advertise_space_id==''||window.advertise_space_id==null||window.advertise_space_id==undefined||isNaN(window.advertise_space_id)){
  throw "window.advertise_space_id 没有配置！";
}

var advertiseSpace=(function(){
/**
**对象初始化
*/
    function advertiseSpace(config,UI){

      //每日数据
      this.DataDay={
        data_day_id:'',//初始化每日数据id
         pvs:0,//初始化pv数目
         uvs:0,//初始化uv数目
         ips:0,//初始化ip数目
         forward:0,//初始化转发数
         daily_day:'',//每日日期
         advertise_space_id:window.advertise_space_id||''//初始化广告id
      };
      //广告位对象配置
    	this.config={
       	advertise_space_id:window.advertise_space_id||'',//广告位id
       	name:'',//名称
        platform:'移动端',//平台
        type:'悬浮',//类型
        status:'',//状态
        trasition:false,//转态  false为正常
        update_time:timeStampToDate(new Date()),//更新时间
        channel_id:''//渠道id
       };
       //广告UI
       this.UI={
       	width:'100%',//默认移动端
       	height:'100px',
       	position:"relative",//默认固定
       	left:'0',//默认0
       	top:'0',//默认0
        margin:'auto',
       	location:'',//位置
       	zindex:'99999999',
       	imgUrl:'http://www.yayao8.com/resources/img/logo.jpg',
       	link:'http://www.baidu.com'
       };
     
		//$.extend(this.config,config);
		this.config=extendObj(this.config,config);
		this.UI=extendObj(this.UI,UI);
    }
    /**
    **对象方法定义
    */
    advertiseSpace.prototype={
/**
**配置整体广告
*/
        getAdvertiseSpaceConfig:function(){
          var _this=this;

           //  $.get("/advertiseSpace/"+advertiseSpace_id,function(data){
           //   if(data==''||data==null){ return;}
    //this.UI.location='顶部';
    _this.UI.location='底部';
   // this.config.type="悬浮";
    _this.config.type="内嵌";
   // console.log(this.config.update_time)
           // });
 myAjax({
        url: "http://man.fuwu88.cn/news/8995",     //请求地址
        type: "GET",                       //请求方式
        //data: { name: "super", age: 20 },        //请求参数
        dataType: "json",
        success: function (response, xml) {
            // 此处放成功后执行的代码
            console.log(JSON.parse(response))
           
           // console.log(xml)
        },
        fail: function (status) {
            // 此处放失败后执行的代码
        }
    });
     return _this.config;
        },
/**
**获取广告层结构
*thisAdvertiseSpaceConfig getAdvertiseSpaceConfig()的返回值
*location  广告位置
*/
 getAdvertiseSpaceDiv:function(thisAdvertiseSpaceConfig,location){
            //创建div
             var thisDiv=document.createElement("div");
             thisDiv.setAttribute("style","position:"+this.UI.position+";left:"+this.UI.left+";top:"+this.UI.top+";height:"+this.UI.height+";width:"+this.UI.width+";margin:"+this.UI.margin);
             thisDiv.setAttribute('id',"ui"+thisAdvertiseSpaceConfig.advertise_space_id);
             //创建第一个a
             var firsta=document.createElement("a");
             firsta.setAttribute("href",this.UI.link);
             firsta.setAttribute("target",'view_window');
             firsta.setAttribute("style","box-sizing:border-box;display:block;position:absolute;left:0;top:0;border:1px solid #ccc;width:100%;height:100%;;");
             //创建第一个a里面的img
             var firstimg=document.createElement("img");
             firstimg.setAttribute("style","width:100%;height:100%;z-index:"+this.UI.zindex);
             firstimg.setAttribute("src",this.UI.imgUrl);
             firsta.appendChild(firstimg);
             //把a导入div,把div导入body
             thisDiv.appendChild(firsta);
             var bodyNode=document.getElementsByTagName("body")[0];
             //bodyNode.appendChild(thisDiv);
             //动态添加
             bodyNode[location](thisDiv,bodyNode.childNodes[0]);
             //创建第二个a
             var seconda=document.createElement("a");
             seconda.innerHTML="×";
             seconda.setAttribute("href",'javascript:;');
             seconda.setAttribute("style","position:absolute;top:0;right:1px;z-index:10000;border:1px solid #ccc; background-color:#ccc;color:white;height:20px;width:20px;line-height:15px;text-align:center;font-size:30px;text-decoration:none;");
             thisDiv.appendChild(seconda);
             //监听第二个a的事件，删除整个div
             var _this=this;
            document.querySelector('#ui'+thisAdvertiseSpaceConfig.advertise_space_id+" a:last-child").addEventListener('click',function(){
            document.querySelector('#ui'+thisAdvertiseSpaceConfig.advertise_space_id).remove();
            },false);

            //监听第一个
             document.querySelector('#ui'+thisAdvertiseSpaceConfig.advertise_space_id+" a:first-child").addEventListener('click',function(){
             

              _this.DataDay.pvs++;
              localStorage.setItem("DataDay",JSON.stringify(_this.DataDay));
              //setCookie("DataDay",_this.DataDay.pvs,5);
             // console.log(getCookie("DataDay"));
             console.log(JSON.parse(localStorage.getItem("DataDay")).pvs)
             if(_this.DataDay.daily_day==''){
              _this.DataDay.daily_day=timeStampToDate(new Date());
              _this.DataDay.ips++;
             }else{
              if(!new Date( _this.DataDay.daily_day).getDay()==new Date().getDay() 
               ){
                 _this.DataDay.daily_day=timeStampToDate(new Date());
               _this.DataDay.ips++;
                
              }
             }
               myAjax({
                  url: "/advertiseSpace/update",     //请求地址
                  type: "POST",                       //请求方式
                  data: {
                    data_day_id:  _this.DataDay.data_day_id,
                    pvs:  _this.DataDay.pvs,
                    uvs: _this.DataDay.uvs,
                    ips:_this.DataDay.ips,
                    forward:_this.DataDay.forward,
                    daily_day:_this.DataDay.daily_day,
                    advertise_space_id:_this.DataDay.advertise_space_id
                    },        //请求参数
                  dataType: "json",
                  success: function (response, xml) {
                      // 此处放成功后执行的代码
                      console.log(JSON.parse(response))
                     
                     // console.log(xml)
                  },
                  fail: function (status) {
                      // 此处放失败后执行的代码
                  }
              });


            },false);
            // $("body")[location](
            //   "<div style='position:relative;left:0;top:0;height:"+this.UI.height+";width:"+this.UI.width+"' id='ui"+this.getAdvertiseSpaceConfig().advertiseSpace_id+"' >"+
            //   "<a href='"+this.UI.link+"' target='view_window' style='box-sizing:border-box;display:block;position:absolute;left:0;top:0;border:1px solid #ccc;width:100%;height:100%;"+
            //   ";position:"+this.UI.position+";left:"+this.UI.left+";top:"+this.UI.top+";'>"+
            //   "<img style='width:100%;height:100%;z-index:"+this.UI.zindex+";' src='"+this.UI.imgUrl+"'/>"+
            //   "</a>"+
            //   "<a style='position:absolute;top:0px;right:1px;z-index:10000;border:1px solid #9839e4; background-color:#9839e4;color:white;height:20px;width:20px;line-height:15px;text-align:center;font-size:30px;text-decoration:none;'"+
            //   " href='javascript:document.querySelector("+'"#ui'+this.getAdvertiseSpaceConfig().advertiseSpace_id+'"'+").removeChild(document.querySelector("+'"#ui'+this.getAdvertiseSpaceConfig().advertiseSpace_id+'"'+").childNodes[0]);document.querySelector("+'"#ui'+this.getAdvertiseSpaceConfig().advertiseSpace_id+'"'+").removeChild(document.querySelector("+'"#ui'+this.getAdvertiseSpaceConfig().advertiseSpace_id+'"'+").childNodes[0]);'>×</a>"+
            //    "</div>"
            //   );
            },
/**
**根据配置获取广告层UI
*/
        showAdvertiseSpaceUI:function(){
          var thisAdvertiseSpaceConfig=this.getAdvertiseSpaceConfig();
            var location;
          if(thisAdvertiseSpaceConfig.type=='内嵌'){
            if(this.UI.location=='顶部'){
              location='insertBefore';
            }
            if(this.UI.location=='底部'){
              location='appendChild';
            }
            this.getAdvertiseSpaceDiv(thisAdvertiseSpaceConfig,location);
           
            
        	}
          if(thisAdvertiseSpaceConfig.type=='悬浮'){
            this.UI.position='fixed';
            if(this.UI.location=='顶部'){
              location='insertBefore';
            }
            if(this.UI.location=='底部'){
              location='appendChild';
              this.UI.top="100%";
              this.UI.margin="-"+this.UI.height+" 0px";
            }
            this.getAdvertiseSpaceDiv(thisAdvertiseSpaceConfig,location);
           
            
          }

        }

    };

    return advertiseSpace;
}());
  //工具方法
 /**
   * 时间戳转yyyy-MM-dd hh:mm:ss
   * 
   */
  function timeStampToDate(timeStamp){
    var date = new Date(timeStamp);
    Y = date.getFullYear() + '-';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
    m = date.getMinutes() + ':';
    s = date.getSeconds(); 
  return Y+M+D+h+m+s; 
  }
  /**
**复制对象
*/
function cloneObj(oldObj) { 
if (typeof(oldObj) != 'object') return oldObj;
if (oldObj == null) return oldObj;
var newObj = new Object();
for (var i in oldObj)
newObj[i] = cloneObj(oldObj[i]);
return newObj;
};
/**
**扩展对象
*/
function extendObj() { 
var args = arguments;
if (args.length < 2) return;
var temp = cloneObj(args[0]); //调用复制对象方法
for (var n = 1; n < args.length; n++) {
for (var i in args[n]) {
temp[i] = args[n][i];
}
}
return temp;
}
/**
   * cookie
   * 
   */
  // 写cookie
  function setCookie (name,value,expires)
  {
  var exp = new Date();
  exp.setTime(exp.getTime() + expires*1000);
  document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
  }
  // 读取cookie
  function getCookie (name)
  {
  var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
  if(arr=document.cookie.match(reg))
  return unescape(arr[2]);
  else
  return null;
  }
  // 删除cookie
  function delCookie(name)
  {
  var exp = new Date();
  exp.setTime(exp.getTime() - 1);
  var cval=myUtils.getCookie(name);
  if(cval!=null)
  document.cookie= name + "="+cval+";expires="+exp.toGMTString();
  }
//ajax

    function myAjax(options) {
        options = options || {};
        options.type = (options.type || "GET").toUpperCase();
        options.dataType = options.dataType || "json";
        var params = formatParams(options.data);

        //创建 - 非IE6 - 第一步
        if (window.XMLHttpRequest) {
            var xhr = new XMLHttpRequest();
        } else { //IE6及其以下版本浏览器
            var xhr = new ActiveXObject('Microsoft.XMLHTTP');
        }

        //接收 - 第三步
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4) {
                var status = xhr.status;
                if (status >= 200 && status < 300) {
                    options.success && options.success(xhr.responseText, xhr.responseXML);
                } else {
                    options.fail && options.fail(status);
                }
            }
        }

        //连接 和 发送 - 第二步
        if (options.type == "GET") {
            xhr.open("GET", options.url + "?" + params, true);
            xhr.send(null);
        } else if (options.type == "POST") {
            xhr.open("POST", options.url, true);
            //设置表单提交时的内容类型
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.send(params);
        }
    //格式化参数
    function formatParams(data) {
        var arr = [];
        for (var name in data) {
            arr.push(encodeURIComponent(name) + "=" + encodeURIComponent(data[name]));
        }
        arr.push(("v=" + Math.random()).replace(".",""));
        return arr.join("&");
    }
    }

//对象调用
new advertiseSpace().showAdvertiseSpaceUI();
//window.advertiseSpace= advertiseSpace;
})(window);