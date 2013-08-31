// JavaScript Document<script type="text/javascript">

//<[CDATA[
var sl = 20;	//初始left值
var el = 500;	//结束left值
var sw = 100;//初始width值
var ew = 580;//结束width值
var p = 10;	//缓冲变量
var t = 20;	//时间变量
var d1 = document.getElementById('d1');
var d2 = document.getElementById('d2');
var debug = document.getElementById('debug');
var open1 = document.getElementById('open1');
var close1 = document.getElementById('close1');
var open2 = document.getElementById('open2');
var close2 = document.getElementById('close2');
function fo1() {
	var cl = parseInt(getStyle(d1,'left'));	//当前left值
	if (cl<el) {
		d1.style.left = cl + Math.ceil((el-cl)/p) + 'px';	//当前值+缓冲增量
		debug.innerHTML = getStyle(d1,'left');	//cl + 'px';
		setTimeout('fo1()', t);
	} else {
		d1.style.left = el + 'px';
		open1.style.display = 'none';
		close1.style.display = 'block';
	}
}
function fc1() {
	var cl = parseInt(getStyle(d1,'left'));	//当前left值
	if (cl>sl) {
		d1.style.left = cl - Math.ceil((cl-sl)/p) + 'px';	//当前值-缓冲增量
		debug.innerHTML = getStyle(d1,'left');	//cl + 'px';
		setTimeout('fc1()', t);
	} else {
		d1.style.left = sl + 'px';
		open1.style.display = 'block';
		close1.style.display = 'none';
	}
}
function fo2() {
	var cw = parseInt(getStyle(d2,'width'));	//当前width值
	if (cw<ew) {
		d2.style.width = cw + Math.ceil((ew-cw)/p) + 'px';	//当前值+缓冲增量
		debug.innerHTML = getStyle(d2,'width');	//cw + 'px';
		setTimeout('fo2()', t);
	} else {
		d2.style.width = ew + 'px';
		open2.style.display = 'none';
		close2.style.display = 'block';
	}
}
function fc2() {
	var cw = parseInt(getStyle(d2,'width'));	//当前width值
	if (cw>sw) {
		d2.style.width = cw - Math.ceil((cw-sw)/p) + 'px';	//当前值-缓冲增量
		debug.innerHTML = getStyle(d2,'width');	//cw + 'px';
		setTimeout('fc2()', t);
	} else {
		d2.style.width = sw + 'px';
		open2.style.display = 'block';
		close2.style.display = 'none';
	}
}
function getStyle( elem, name ) { 
	if (elem.style[name]) { return elem.style[name]; } 
	else if (elem.currentStyle) { return elem.currentStyle[name]; } 
	else if (document.defaultView && document.defaultView.getComputedStyle) { 
		name = name.replace(/([A-Z])/g,"-$1"); 
		name = name.toLowerCase(); 
		var s = document.defaultView.getComputedStyle(elem,""); 
		return s && s.getPropertyValue(name); 
	} 
	else { return null; } 
}
//]]>
///script>