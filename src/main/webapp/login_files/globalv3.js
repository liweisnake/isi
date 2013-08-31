function SemiTCqty(){var myPins="";var myPackage="";var pinOptions=document.getElementById("pins").options;for(var i=0;i<pinOptions.length;i++){myPins="pins"+pinOptions[i].value;document.getElementById(myPins).style.display="none";}
document.getElementById("tc_qty").innerHTML="N/A";myPins="pins"+document.getElementById("pins").value;myPackage="package"+document.getElementById("pins").value;document.getElementById(myPins).style.display="block";document.getElementById(myPackage).selectedIndex=0;}
function DeleteThis(){}
function MM_swapImgRestore(){var i,x,a=document.MM_sr;for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++)x.src=x.oSrc;}
function MM_preloadImages(){var d=document;if(d.images){if(!d.MM_p)d.MM_p=new Array();var i,j=d.MM_p.length,a=MM_preloadImages.arguments;for(i=0;i<a.length;i++)
if(a[i].indexOf("#")!=0){d.MM_p[j]=new Image;d.MM_p[j++].src=a[i];}}}
function MM_findObj(n,d){var p,i,x;if(!d)d=document;if((p=n.indexOf("?"))>0&&parent.frames.length){d=parent.frames[n.substring(p+1)].document;n=n.substring(0,p);}
if(!(x=d[n])&&d.all)x=d.all[n];for(i=0;!x&&i<d.forms.length;i++)x=d.forms[i][n];for(i=0;!x&&d.layers&&i<d.layers.length;i++)x=MM_findObj(n,d.layers[i].document);if(!x&&d.getElementById)x=d.getElementById(n);return x;}
function MM_swapImage(){var i,j=0,x,a=MM_swapImage.arguments;document.MM_sr=new Array;for(i=0;i<(a.length-2);i+=3)
if((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x;if(!x.oSrc)x.oSrc=x.src;x.src=a[i+2];}}
function MM_jumpMenu(targ,selObj,restore){eval("window.open('"+selObj.options[selObj.selectedIndex].value+"','newarkinone')");if(restore)selObj.selectedIndex=0;}
var css_browser_selector=function(){var
ua=navigator.userAgent.toLowerCase(),is=function(t){return ua.indexOf(t)!=-1;},h=document.getElementsByTagName('html')[0],b=(!(/opera|webtv/i.test(ua))&&/msie\x20(\d)/.test(ua))?('ie ie'+RegExp.$1):is('gecko/')?'gecko':is('opera/9')?'opera opera9':/opera\x20(\d)/.test(ua)?'opera opera'+RegExp.$1:is('konqueror')?'konqueror':is('applewebkit/')?'webkit safari':is('mozilla/')?'gecko':'',os=(is('x11')||is('linux'))?' linux':is('mac')?' mac':is('win')?' win':'';var c=b+os+' js';
h.className+=h.className?' '+c:c;}();var isIE=(navigator.appVersion.indexOf("MSIE")!=-1)?true:false;var isWin=(navigator.appVersion.toLowerCase().indexOf("win")!=-1)?true:false;var isOpera=(navigator.userAgent.indexOf("Opera")!=-1)?true:false;function ControlVersion(){var version;var axo;var e;try{axo=new ActiveXObject("ShockwaveFlash.ShockwaveFlash.7");version=axo.GetVariable("$version");}catch(e){}
if(!version){try{axo=new ActiveXObject("ShockwaveFlash.ShockwaveFlash.6");version="WIN 6,0,21,0";axo.AllowScriptAccess="always";version=axo.GetVariable("$version");}catch(e){}}
if(!version){try{axo=new ActiveXObject("ShockwaveFlash.ShockwaveFlash.3");version=axo.GetVariable("$version");}catch(e){}}
if(!version){try{axo=new ActiveXObject("ShockwaveFlash.ShockwaveFlash.3");version="WIN 3,0,18,0";}catch(e){}}
if(!version){try{axo=new ActiveXObject("ShockwaveFlash.ShockwaveFlash");version="WIN 2,0,0,11";}catch(e){version=-1;}}
return version;}
function GetSwfVer(){var flashVer=-1;if(navigator.plugins !=null&&navigator.plugins.length>0){if(navigator.plugins["Shockwave Flash 2.0"]|| navigator.plugins["Shockwave Flash"]){var swVer2=navigator.plugins["Shockwave Flash 2.0"]?" 2.0":"";var flashDescription=navigator.plugins["Shockwave Flash"+swVer2].description;var descArray=flashDescription.split(" ");var tempArrayMajor=descArray[2].split(".");var versionMajor=tempArrayMajor[0];var versionMinor=tempArrayMajor[1];
if(descArray[3]!=""){tempArrayMinor=descArray[3].split("r");}else{tempArrayMinor=descArray[4].split("r");}
var versionRevision=tempArrayMinor[1]>0?tempArrayMinor[1]:0;var flashVer=versionMajor+"."+versionMinor+"."+versionRevision;}}else if(navigator.userAgent.toLowerCase().indexOf("webtv/2.6")!=-1)flashVer=4;else if(navigator.userAgent.toLowerCase().indexOf("webtv/2.5")!=-1)flashVer=3;else if(navigator.userAgent.toLowerCase().indexOf("webtv")!=-1)flashVer=2;else if(isIE&&isWin&& !isOpera){flashVer=ControlVersion();}
return flashVer;}
function DetectFlashVer(reqMajorVer,reqMinorVer,reqRevision){versionStr=GetSwfVer();if(versionStr==-1){return false;}else if(versionStr !=0){if(isIE&&isWin&& !isOpera){tempArray=versionStr.split(" ");tempString=tempArray[1];versionArray=tempString.split(",");}else{versionArray=versionStr.split(".");}
var versionMajor=versionArray[0];var versionMinor=versionArray[1];var versionRevision=versionArray[2];if(versionMajor>parseFloat(reqMajorVer)){return true;}else if(versionMajor==parseFloat(reqMajorVer)){if(versionMinor>parseFloat(reqMinorVer))
return true;else if(versionMinor==parseFloat(reqMinorVer)){if(versionRevision>=parseFloat(reqRevision))
return true;}}
return false;}}
function AC_AddExtension(src,ext){if(src.indexOf('?')!=-1)
return src.replace(/\?/,ext+'?');else
return src+ext;}
function AC_Generateobj(objAttrs,params,embedAttrs){var str='';if(isIE&&isWin&& !isOpera){str+='<object ';for(var i in objAttrs)
str+=i+'="'+objAttrs[i]+'" ';for(var i in params)
str+='><param name="'+i+'" value="'+params[i]+'" /> ';str+='></object>';}else{str+='<embed ';for(var i in embedAttrs)
str+=i+'="'+embedAttrs[i]+'" ';str+='> </embed>';}
if(typeof embedAttrs['targetObj']=='undefined'|| embedAttrs['targetObj']==null){document.write(str);}else{embedAttrs['targetObj'].update(str);}}
function AC_FL_RunContent(){var ret=
AC_GetArgs
(arguments,".swf","movie","clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"
,"application/x-shockwave-flash"
);AC_Generateobj(ret.objAttrs,ret.params,ret.embedAttrs);}
function AC_GetArgs(args,ext,srcParamName,classid,mimeType){var ret=new Object();ret.embedAttrs=new Object();ret.params=new Object();ret.objAttrs=new Object();for(var i=0;i<args.length;i=i+2){var currArg=args[i].toLowerCase();switch(currArg){case "classid":break;case "pluginspage":ret.embedAttrs[args[i]]=args[i+1];break;case "src":case "movie":args[i+1]=AC_AddExtension(args[i+1],ext);ret.embedAttrs["src"]=args[i+1];ret.params[srcParamName]=args[i+1];break;case "onafterupdate":
case "onbeforeupdate":case "onblur":case "oncellchange":case "onclick":case "ondblClick":case "ondrag":case "ondragend":case "ondragenter":case "ondragleave":case "ondragover":case "ondrop":case "onfinish":case "onfocus":case "onhelp":case "onmousedown":case "onmouseup":case "onmouseover":case "onmousemove":case "onmouseout":case "onkeypress":case "onkeydown":case "onkeyup":case "onload":case "onlosecapture":case "onpropertychange":case "onreadystatechange":case "onrowsdelete":case "onrowenter":
case "onrowexit":case "onrowsinserted":case "onstart":case "onscroll":case "onbeforeeditfocus":case "onactivate":case "onbeforedeactivate":case "ondeactivate":case "type":case "codebase":case "id":ret.objAttrs[args[i]]=args[i+1];break;case "width":case "height":case "align":case "vspace":case "hspace":case "class":case "title":case "accesskey":case "name":case "tabindex":ret.embedAttrs[args[i]]=ret.objAttrs[args[i]]=args[i+1];break;default:ret.embedAttrs[args[i]]=ret.params[args[i]]=args[i+1];}}
ret.objAttrs["classid"]=classid;if(mimeType)ret.embedAttrs["type"]=mimeType;return ret;}
var requiredMajorVersion=8;var requiredMinorVersion=0;var requiredRevision=0;sfHover=function(){var ieNavs=document.getElementsByTagName('ul');for(i=0;i<ieNavs.length;i++){var ul=ieNavs[i];if(/\bdropdown\b/.test(ul.className)|| /\bmenu-satu\b/.test(ul.className)|| /\btest-dropdown\b/.test(ul.className)){setHover(ul);}}}
function setHover(nav){var ieULs=nav.getElementsByTagName('ul');if(navigator.appVersion.substr(22,3)!="5.0"){for(j=0;j<ieULs.length;j++){var ieMat=null;if(document.location.protocol=="https:"){ieMat=document.createElement('<iframe src="https://'+document.location.host+'/js/empty.html">');}else if(window.opera !="undefined"){ieMat=document.createElement('iframe');ieMat.src="";}else{ieMat=document.createElement('iframe');ieMat.src="javascript:false";}
ieMat.scrolling="no";ieMat.frameBorder="0";ieMat.style.marginLeft="-4px";ieMat.style.paddingRight="4px";ieMat.style.width=ieULs[j].offsetWidth+"px";ieMat.style.height=ieULs[j].offsetHeight+"px";ieMat.style.zIndex="-1";if(ieULs[j].firstChild){ieULs[j].insertBefore(ieMat,ieULs[j].firstChild);ieULs[j].style.zIndex="2000";}}
var ieLIs=nav.getElementsByTagName('li');for(var i=0;i<ieLIs.length;i++)if(ieLIs[i]){ieLIs[i].onmouseover=function(){if(!/\bsfhover\b/.test(this.className))
this.className+=" sfhover";}
ieLIs[i].onmouseout=function(){if(!this.contains(event.toElement))
this.className=this.className.replace(' sfhover','');}}}else{var ieLIs=document.getElementById('nav').getElementsByTagName('li');for(var i=0;i<ieLIs.length;i++)if(ieLIs[i]){ieLIs[i].onmouseover=function(){this.className+=" sfhover";hideSelects();}
ieLIs[i].onmouseout=function(){this.className=this.className.replace(' sfhover','');showSelects()}}}}
function hideSelects(){var oSelects=document.getElementsByTagName("select");for(var i=0;i<oSelects.length;i++)
oSelects[i].className+=" hide";}
function showSelects(){var oSelects=document.getElementsByTagName("select");for(var i=0;i<oSelects.length;i++)
oSelects[i].className=oSelects[i].className.replace(" hide","");}
var ieOnWinVer=(function(){var a=navigator.userAgent;var i=a.indexOf("MSIE");if(i==-1|| navigator.platform.indexOf("Win")==-1)return 0;return parseFloat(a.substring(i+5));})();if(window.attachEvent&&ieOnWinVer<=6.0&&ieOnWinVer>0.0)window.attachEvent('onload',sfHover);try{document.execCommand('BackgroundImageCache',false,true);}catch(e){}function setUserLocaleCookie(){var cookieValue=document.changeDisplayLocaleForm.userSelectedCookie.value;setCookie("userSelectedLocale",cookieValue,365);return true;}
function setCookie(cookieName,cookieValue,expirationDays){var exdate=new Date();exdate.setDate(exdate.getDate()+expirationDays);document.cookie=cookieName+"="+escape(cookieValue)+
(";expires="+exdate.toGMTString())+
(";path=/");}
function getCookie(cookieName){if(document.cookie.length>0){startIndex=document.cookie.indexOf(cookieName);if(startIndex!=-1){startIndex=startIndex+cookieName.length+1;endIndex=document.cookie.indexOf(";",startIndex);if(endIndex==-1)endIndex=document.cookie.length;return unescape(document.cookie.substring(startIndex,endIndex));}}
return "";}
function xbDetectBrowser(){var oldOnError=window.onerror;var element=null;window.onerror=null;window.saveNavigator=window.navigator;navigator.OS='';navigator.version=parseFloat(navigator.appVersion);navigator.org='';navigator.family='';var platform;if(typeof(window.navigator.platform)!='undefined'){platform=window.navigator.platform.toLowerCase();if(platform.indexOf('win')!=-1)
navigator.OS='win';else if(platform.indexOf('mac')!=-1)
navigator.OS='mac';else if(platform.indexOf('unix')!=-1|| platform.indexOf('linux')!=-1|| platform.indexOf('sun')!=-1)
navigator.OS='nix';}
var i=0;var ua=window.navigator.userAgent.toLowerCase();if(ua.indexOf('safari')!=-1){i=ua.indexOf('safari');navigator.family='safari';navigator.org='safari';navigator.version=parseFloat('0'+ua.substr(i+7),10);}else if(ua.indexOf('opera')!=-1){i=ua.indexOf('opera');navigator.family='opera';navigator.org='opera';navigator.version=parseFloat('0'+ua.substr(i+6),10);}else if((i=ua.indexOf('msie'))!=-1){navigator.org='microsoft';navigator.version=parseFloat('0'+ua.substr(i+5),10);if(navigator.version<4)
navigator.family='ie3';else
navigator.family='ie4'
}else if(ua.indexOf('gecko')!=-1){navigator.family='gecko';var rvStart=ua.indexOf('rv:');var rvEnd=ua.indexOf(')',rvStart);var rv=ua.substring(rvStart+3,rvEnd);var rvParts=rv.split('.');var rvValue=0;var exp=1;for(var i=0;i<rvParts.length;i++){var val=parseInt(rvParts[i]);rvValue+=val / exp;exp*=100;}
navigator.version=rvValue;if(ua.indexOf('netscape')!=-1)
navigator.org='netscape';else if(ua.indexOf('compuserve')!=-1)
navigator.org='compuserve';else
navigator.org='mozilla';}else if((ua.indexOf('mozilla')!=-1)&&(ua.indexOf('spoofer')==-1)&&(ua.indexOf('compatible')==-1)&&(ua.indexOf('opera')==-1)&&(ua.indexOf('webtv')==-1)&&(ua.indexOf('hotjava')==-1)){var is_major=parseFloat(navigator.appVersion);if(is_major<4)
navigator.version=is_major;else{i=ua.lastIndexOf('/')
navigator.version=parseFloat('0'+ua.substr(i+1),10);}
navigator.org='netscape';navigator.family='nn'+parseInt(navigator.appVersion);}else if((i=ua.indexOf('aol'))!=-1){navigator.family='aol';navigator.org='aol';navigator.version=parseFloat('0'+ua.substr(i+4),10);}else if((i=ua.indexOf('hotjava'))!=-1){navigator.family='hotjava';navigator.org='sun';navigator.version=parseFloat(navigator.appVersion);}
window.onerror=oldOnError;}
xbDetectBrowser();function submitFormOnce(grayButtonImage){formInputImageTypeObjs=document.getElementsByName("buyNow");for(i=0;i<formInputImageTypeObjs.length;i++){if(formInputImageTypeObjs[i].type.toLowerCase()=="image"){formInputImageTypeObjs[i].src=grayButtonImage;formInputImageTypeObjs[i].disabled=true;}}}
function submitCatOnce(obj){formInputCatLinks=document.getElementsByName("catResults");for(i=0;i<formInputCatLinks.length;i++){formInputCatLinks[i].style.color="#9D9DA1";if(obj!=formInputCatLinks[i]){formInputCatLinks[i].removeAttribute('href');}
formInputCatLinks[i].onclick=function(){return false;};}}
function submitPageOnce(obj){formInputPageLinks=document.getElementsByName("pageLink");for(i=0;i<formInputPageLinks.length;i++){formInputPageLinks[i].style.color="#9D9DA1";if(obj!=formInputPageLinks[i]){formInputPageLinks[i].removeAttribute('href');}
formInputPageLinks[i].onclick=function(){return false;}}}
function disableAllLinks(xHow){objLinks=document.links;for(i=0;i<objLinks.length;i++){objLinks[i].disabled=xHow;objLinks[i].onclick=function(){return false;}
objLinks[i].style.color="#9D9DA1";}}
function disableQuickPasteSubmitButton(){document.getElementById("submitQuickPaste").disabled=true;document.getElementById("submitQuickPaste").style.color="#9D9DA1";setTimeout("document.getElementById('prepage').style.visibility='visible';",0);}
