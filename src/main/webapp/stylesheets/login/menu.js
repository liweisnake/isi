// JavaScript Document<SCRIPT LANGUAGE="JavaScript">

<!--
var speed=10;//速度
var ci = 10;//运动次数
var left=0;//方框左位置
var top=0;//方框上位置
var width=0;//方框宽
var height=0;//方框高
var aimleft=0;//目标左位置
var aimtop=0;//目标上位置
var aimwidth=0;//目标宽
var aimheight=0;//目标高
var lb=0;//左步长
var tb=0;//上步长
var wb=0;//宽步长
var hb=0;//高步长
var fk = null;
var aim = null;
var aim1 = null;
function initObj(oid){
if (!fk){fk = document.getElementById('fk');}
if (!aim){aim = document.getElementById('aim');}
if (!aim1)aim1 = document.getElementById('aim1');
if (oid)
append(fk,document.getElementById(oid),true);
}
function append(o,oc,cloned){
while (o.hasChildNodes())o.removeChild(o.firstChild);
if (cloned)oc = oc.cloneNode(true);
oc.className = 'show';
o.appendChild(oc);
}
function setSource(obj,oid){
initObj(oid);
left      = getOffset(obj).Left;
top       = getOffset(obj).Top;
width     = obj.offsetWidth;
height    = obj.offsetHeight;
aimleft   = getOffset(aim).Left;
aimtop    = getOffset(aim).Top;
aimwidth  = aim.offsetWidth;
aimheight = aim.offsetHeight;
fk.style.display='';
clearInterval(MyMar);
}
/**
* 设置方向步长、宽高步长
*/
function setStep(){
lb = (aimleft-left)/ci;
tb = (aimtop-top)/ci;
wb = (aimwidth-width)/ci;
hb = (aimheight-height)/ci;
}
/**
* 移动
*/
function move(){
setStep();
left+=lb;
top+=tb;
width+=wb;
height+=hb;
if(left<aimleft-2 || top<aimtop-2 || width<aimwidth-2 || height<aimheight-2){
fk.style.left = left+"px";
fk.style.top = top+"px";
fk.style.width = width+"px";
fk.style.height = height+"px";
}else{
if (fk)
while(fk.hasChildNodes()){append(aim1,fk.firstChild);}
hiddenFK();
clearInterval(MyMar)
}
}
function hiddenFK(){
initObj();
fk.style.display='none';
}
/**
* 取得某元素在页面中相对页面左上顶点的位置
*/
function getOffset(obj){
var offsetleft = obj.offsetLeft;
var offsettop = obj.offsetTop;
while (obj.offsetParent != document.body)
{
obj = obj.offsetParent;
offsetleft += obj.offsetLeft;
offsettop += obj.offsetTop;
}
return {Left : offsetleft, Top : offsettop};
}
var MyMar=setInterval(move,speed);
//-->
///SCRIPT>