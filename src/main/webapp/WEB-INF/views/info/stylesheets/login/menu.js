// JavaScript Document<SCRIPT LANGUAGE="JavaScript">

<!--
var speed=10;//�ٶ�
var ci = 10;//�˶�����
var left=0;//������λ��
var top=0;//������λ��
var width=0;//�����
var height=0;//�����
var aimleft=0;//Ŀ����λ��
var aimtop=0;//Ŀ����λ��
var aimwidth=0;//Ŀ���
var aimheight=0;//Ŀ���
var lb=0;//�󲽳�
var tb=0;//�ϲ���
var wb=0;//����
var hb=0;//�߲���
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
* ���÷��򲽳�����߲���
*/
function setStep(){
lb = (aimleft-left)/ci;
tb = (aimtop-top)/ci;
wb = (aimwidth-width)/ci;
hb = (aimheight-height)/ci;
}
/**
* �ƶ�
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
* ȡ��ĳԪ����ҳ�������ҳ�����϶����λ��
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