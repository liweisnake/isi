/*------------------------------------------------------------------------------------------------*/

/**
 * Javascript code to access cookies via an object-oriented interface.
 * The objects can store data as either simple text or using JSON to encode
 * more complex data types into a string.
 *
 * Uses prototype.js 1.6 (http://www.prototypejs.org)
 * 
 * Author:  David Winterbottom 
 * Website: http://codeinthehole.com
 * License: Creative Commons Attribution-ShareAlike 2.5
 *          http://creativecommons.org/licenses/by-sa/2.5/
 * Version: 0.1
 * Updated: November 09, 2008
 */

/*
 * This could be factored into its own file
 */
 

// Vanilla cookie object, capable of storing string data only
var Cookies = Class.create({
    // Initialise with optional path and domain arguments
    initialize: function(path, domain) {
        this.path = path || '/';
        this.domain = domain || null;
    },
    // Sets a cookie with an optional expiry length in days
    set: function(key, value, days) {
        if (typeof key != 'string') {
            throw "Invalid key";
        }
        if (typeof value != 'string' && typeof value != 'number') {
            throw "Invalid value";
        }
        if (days && typeof days != 'number') {
            throw "Invalid expiration time";
        }
        var setValue = key+'='+escape(new String(value));
        if (days) {
            var date = new Date();
            date.setTime(date.getTime()+(days*24*60*60*1000));
            var setExpiration = "; expires="+date.toGMTString();
        } else var setExpiration = "";
        var setPath = '; path='+escape(this.path);
        var setDomain = (this.domain) ? '; domain='+escape(this.domain) : '';
        var cookieString = setValue+setExpiration+setPath+setDomain;
        document.cookie = cookieString;
    },
    // Returns a cookie value or false
    get: function(key) {
        var keyEquals = key+"=";
        var value = false;
        document.cookie.split(';').invoke('strip').each(function(s){
            if (s.startsWith(keyEquals)) {
                value = unescape(s.substring(keyEquals.length, s.length));
                throw $break;
            }
        });
        return value;
    },
    // Clears a cookie
    clear: function(key) {
        document.cookie = key+"=; path="+escape(this.path);
    },
    // Clears all cookies (regardless of domain and path)
    clearAll: function() {
        document.cookie.split(';').collect(function(s){
            return s.split('=').first().strip();
        }).each(function(key){
            this.clear(key);
        }.bind(this));
    }
});

// Fancier cookies that can store arrays and Javascript objects using JSON
var JsonCookies = Class.create(Cookies, {});
JsonCookies.addMethods({
    // Overridden set method to JSON-encode value
    set: function($super, key, value, days) {
        switch (typeof value) {
            case 'undefined':
            case 'function':
            case 'unknown': 
                throw "Invalid value type";
                break;
            case 'boolean': 
            case 'string': 
            case 'number': 
                value = String(value.toString());
            break;
        }
        $super(key, Object.toJSON(value), days);
    },
    // Overriden get method to JSON-decode the value
    get: function($super, key) {
        var value = $super(key);
        return (value) ? value.evalJSON() : false;
    }
});

// Cookie Utilities

var cookies = new Cookies();

function setBooleanCookie(key, value, expires)
{
	cookies.clear(key);
	var boolValue = value ? "yes" : "no";
	if (expires)
	{
		cookies.set(key, boolValue, expires);
	} else
	{
		cookies.set(key, boolValue);
	}
}

function getBooleanCookie(key)
{
	return cookies.get(key) == "yes";
}

/*------------------------------------------------------------------------------------------------*/

/*
 Utils.js code
 */
/*
 * Onload event marshaller
 */

// use addOnloadEvent to have functions run after page load
function addOnloadEvent(func) {
    document.observe('dom:loaded', func);
}

// if there's anything in runAfterPageLoadList, it's an array of functions that are meant to load after dom:loaded
// that was created before the prototype library was loaded
if (typeof runAfterPageLoadList != 'undefined' && runAfterPageLoadList.length > 0) {
	while (runAfterPageLoadList.length > 0 ) {
		document.observe('dom:loaded', runAfterPageLoadList.shift());
	}
}


/**
 * Gets a new XMLHttpRequest object.
 */
function getXmlHttpRequest() {
    try { return new XMLHttpRequest(); }
    catch (ex) { try {  return new ActiveXObject('Msxml2.XMLHTTP'); }
        catch (ex1) { try { return new ActiveXObject('Microsoft.XMLHTTP'); }
            catch(ex1) {       return new ActiveXObject('Msxml2.XMLHTTP.4.0'); }
        }
    }
}

/**
 * Utility method to make a request to the supplied URL and invoked hte callback
 * function, passing it the XMLHttpRequest.
 */
function fetchFromUrl(url, callbackFunction) {
    var xmlHttpRequest = getXmlHttpRequest();
    xmlHttpRequest.onreadystatechange = function() {
        if (xmlHttpRequest.readyState == 4 && callbackFunction != null) {
            callbackFunction(xmlHttpRequest);
        }
    }
    xmlHttpRequest.open("GET", url, true, "", "");
    xmlHttpRequest.send("");

    return xmlHttpRequest;
}

function hideLoading()
{
  document.getElementById("loading").style.display = "none";
}

var stringLength = 1;
var string = ".";

function doDots()
{
  if (stringLength > 5)
  {
    stringLength = 1;
    string = ".";
  }
  else
  {
      string += ".";
      stringLength += 1;
  }

   var dots = document.getElementById("dots");
   if (dots) {
       dots.innerHTML = string;
       setTimeout("doDots()", 500);
   }
}

function showLoading()
{
  document.getElementById("loading").style.display = "block";
  doDots();
}


/**
 * Takes a mouseout event and returns true if
 * the mouse has actually left the calling DIV
 * and false if it's now over a child element of
 *
 * See http://www.quirksmode.org/js/events_mouse.html
 */
function isMouseExitedLayer(e) {

    if (!e) var e = window.event;

    var tg = (window.event) ? e.srcElement : e.target;
    if (tg.nodeName != 'DIV') return false;

    var reltg = (e.relatedTarget) ? e.relatedTarget : e.toElement;
    while (reltg != tg && reltg.nodeName != 'BODY') {
        reltg = reltg.parentNode
    }

    return (reltg != tg);
}

/**
 * Submits a form based on a user selecting an item in a list
 * Used to get around an ATG bug in OnChange events.
 */
function submitOption(select, formId) {
   
    if (select.options[select.selectedIndex].value.length > 0) {
        document.getElementById(formId).click();
    }
}

/**
 * Scans through elements of a particular type and makes a change
 * to each one. MattC 18-May-06.
 * e.g. multiElementChange('select', '', 'refinements', '', 'selectedIndex=-1');
 *  or  multiElementChange('input', 'checkbox', '', 'compare', 'checked='+this.checked);
 */
function multiElementChange(sElementType, sElementSubType, sControlName, sControlId, sAction) {
    // Matches at the start of the element name, or at the beginning of the elements ID
    a=document.getElementsByTagName(sElementType);
    for(b=0;b<a.length;b++){
        var el=a[b];
        if (el==null) break;
        if (sElementSubType!='' && el.type!=sElementSubType) continue;
        if (sControlName!='' && (el.name.length-sControlName.length>-1) && (el.name.substring(el.name.length-sControlName.length, el.name.length)==sControlName)) eval('el.' + sAction);
        if (sControlId!='' && (el.id.substring(0, sControlId.length)==sControlId)) eval('el.' + sAction);
    }
}

 
// old function from before prototype
function getElementsByStyleClass (className) {
	return $$("." + className);
}

/*
 * from jod.js -- needs proper merging into this file
 */
 
var JSONP = {};
(function(){
	var id = 0, head = $$('head')[0], global = this;
	global.JSONP.exec = function(url, options) { // setter not correct ck pattern
		var script = document.createElement('script'), token = '__jsonp' + id;

		if (options.reference) {
			token = options.reference;
		}

		if (!options.reference) {
			global[token] = options.callback;
		}

		var cacheDefeat = ""
		if (options.defeatCache) {
			cacheDefeat = '&cacheDefeat=' + (new Date()).getTime();
		}
		var callbackExits=false;
		if(url.indexOf("{callback}") == -1)
		{
			var q = (url.length > 1 ? url.substring(1).split("&") : []);
			for(var i = 0; i < q.length; i++)
			{
				if(q[i].indexOf('callback=') >-1
				&&  window[q[i].replace(/^[^=]+=?/, "")])
				{

					callbackExits=true;
					break;
				}

			}
		}
		else
			{
				callbackExits=true;
			}
			if(callbackExits)
			{
				script.src = url.replace(/\{callback\}/, token ) + cacheDefeat;
				// clean up on load: remove script tag, null script variable and delete global callback function
				script.onload = function() {
					script.remove();
					script = null;
					if (!options.reference)
					{
						delete global[token];
					}

				};

				head.appendChild(script);
			}
			id++;
		}

	}
)();



/*
    Ajax cart code
*/
var Behaviour = {
    list : new Array,
   
    register : function(sheet){
        Behaviour.list.push(sheet);
    },
   
    start : function(){
        Behaviour.addLoadEvent(function(){
            Behaviour.apply();
        });
    },
   
    apply : function(){
        for (h=0;sheet=Behaviour.list[h];h++){
            for (selector in sheet){
                list = document.getElementsBySelector(selector);
               
                if (!list){
                    continue;
                }

                for (i=0;element=list[i];i++){
                    sheet[selector](element);
                }
            }
        }
    },
   
    addLoadEvent : function(func){
        var oldonload = window.onload;
       
        if (typeof window.onload != 'function') {
            window.onload = func;
        } else {
            window.onload = function() {
                oldonload();
                func();
            }
        }
    }
}

Behaviour.start();


function getAllChildren(e) {
  // Returns all children of element. Workaround required for IE5/Windows. Ugh.
  return e.all ? e.all : e.getElementsByTagName('*');
}


document.getElementsBySelector = function(selector) {
	return $$(selector);
}


   
   
	var microCartPending;
	var buyButton;
	var orgButton;
	var uPrice;
	var busyElement;

	function loading(element)
	{
		buyButton=element;
		microCartPending=true;
		busyElement=$(element).previous('.busyImage');
		busyElement.show();
		var cartButton=$('buyNow') ;
		if (cartButton != undefined)
		{ 
			orgButton = $('buyNow').src
			element.src= $('grayBuy').src;
		}
	}
	
	function loadingLink(element)
	{
		microCartPending=true;
		var pos=findPos(element);
		busyElement=$(element).previous('.busyImage');
		busyElement.show();
	}

	function updateCart(request)
	{		
		// move into body tag because IE6 won't fix to the right side of browser otherwise
		if ($$('body > #quickCartPopup').length == 0) {
			$$('body')[0].insert($('quickCartPopup'));
		}
		busyElement.hide();
		Effect.BlindDown('quickCartPopup', {duration:0.25});
		var req = request.responseXML;
		if (orgButton != null)
		{ 
			buyButton.src= orgButton;
		} 
		microCartPending=false; 
		if (req != null)
		{   
				       
		    lazyDisplayImages();                                       
		    var sku = req.getElementsByTagName("sku")[0].childNodes[0].nodeValue;
		    var qty = req.getElementsByTagName("quantity")[0].childNodes[0].nodeValue;
		   
		    var manfName = req.getElementsByTagName("manufacturer")[0].childNodes[0].nodeValue;
		    var name = req.getElementsByTagName("name")[0].childNodes[0].nodeValue;
		   
		    toggleDivDisplay('qcProductName',name);
		    var unitPriceInXml = req.getElementsByTagName("unitprice").length;
		    if(unitPriceInXml>0)
		    {
			uPrice=req.getElementsByTagName("unitprice")[0].childNodes[0].nodeValue;
		    }
		    toggleDivDisplay('qcPrice',uPrice);
		    toggleDivDisplay('qcOrderCode',sku);
		    toggleDivDisplay('qcManufacturer',manfName);
		    toggleDivDisplay('qcQuantity',qty);
		    toggleDivDisplay('minicarttotal',req.getElementsByTagName("total")[0].childNodes[0].nodeValue);
		    toggleDivDisplay('minicartitemcount',req.getElementsByTagName("itemcount")[0].childNodes[0].nodeValue);                               
		    toggleDivDisplay('popupmessage',req.getElementsByTagName("itemStatus")[0].childNodes[0].nodeValue);
		   
					   
		    //Check if the accesories element is present in response XML
		    var hasAccs = req.getElementsByTagName("accessories").length;
		    if(hasAccs>0)
		    {
			$('viewAccesories').innerHTML="<a href='/jsp/search/altsSubs.jsp?type=accs&sku=" + sku + "' >"+req.getElementsByTagName('accessories')[0].childNodes[0].nodeValue+"</a>";   
		    }
		   
		    //Check if the alternatives element is present in response XML
		    var hasAltrs = req.getElementsByTagName("alternatives").length;                                   
		    if(hasAltrs>0)
		    {
			$('viewAlternates').innerHTML="<a href='/jsp/search/altsSubs.jsp?type=alts&sku=" + sku + "'>"+req.getElementsByTagName('alternatives')[0].childNodes[0].nodeValue+"</a>";   
		    }   
				       
	
		    //to remove item from cart
		    var removeParameters ="product="+sku+"&qty="+qty;
		    createRemoveLink(removeParameters);                               
		    // Check for warnings/errors
		    var messageList = req.getElementsByTagName("message");
		    $('errorMessages').innerHTML = "";
		    $('informationMessages').innerHTML = "";
		   
		    if (messageList != null && messageList.length>0)
		    {                                               
			for (i=0;i<messageList.length;i++)
			{                          
			    type = messageList[i].childNodes[0].childNodes[0].nodeValue;
			    var pDescription = messageList[i].childNodes[1].childNodes[0].nodeValue;
				   
			    if (type != null && type == 'error')
			    {                          
				$('errorMessages').innerHTML+=pDescription + "<br/>";
				$('removeItem').show(); //Show  the remove icon only if there are any error messages
			    }
			    else if (type != null && type == 'warning')
			    {                          
				$('errorMessages').innerHTML+=pDescription + "<br/>";;
			    }   
			    else if (type != null && type == 'severe')
			    {                          
				$('errorMessages').innerHTML+=pDescription + "<br/>";;
			    }                   
			    else if (type != null && type == 'information')
			    {                          
				$('informationMessages').innerHTML+=pDescription + "<br/>";;
			    }                                                                   
			}
		    }
	
		}
		else
		{       
			if (!parametricsFilterPending) {         
		    	$('errorMessages').innerHTML="<p>Ajax Error</p>";
		    }
		}
		scroll(0,0);
	}
    function createRemoveLink(removeParameter)
    {
        $('ajaxRemove').href="/jsp/shoppingCart/processMicroCart.jsp?action=remove&"+removeParameter;
        $('removeItem').hide();
    }
   
    function getFormParameters(myForm)
    {
        var queryStr="null";

        //add hidden varible count in case of adiing multiple products at time
                var count=myForm.count;
       
        if(count!='undefined' || count!=null  || count.value>1)
        {
            queryStr="product="+myForm.product.value+"&qty="+myForm.qty.value;
            uPrice = myForm.unitPrice.value;
        }
        else
        {
            //if count is null means adding single product to cart.so we need to pass
            queryStr="product="+myForm.product.value+"&qty="+myForm.qty.value;
            if(isElementExists(myForm.addedFromCwbt))
            {
                queryStr+='&addedFromCwbt='+myForm.addedFromCwbt.value;
            }
           
            uPrice = myForm.unitPrice.value;
   
        }   
       
        return queryStr;
    }
     
    function getUrlParameters(url)
    {
          var qString = url.split('?');  
          var qtyStr=qString[1].split('~');
          var output=qtyStr[0];
          if(qtyStr.length>1)
          {
            output+="&qty="+qtyStr[1];
          }
          else
          {
            output+="&qty=1"
          }
          return output;
    }
   
    var ajaxrules ={
                    'input.ajaxAddToCart' : function(element)
                    {
                        element.onclick = function()
                        {
                           
                            if(!microCartPending)
                            {
                                var url = "/jsp/shoppingCart/processMicroCart.jsp?action=buy&" + getFormParameters(this.form) ;
                                var ar = new Ajax.Request(url,{method:"post",onCreate: loading(element), onComplete: updateCart});   
                            }
                               
                            return false;
                        }
                    },
                    'input.ajaxPopupButton' : function(element)
                    {
                        element.onclick = function()
                        {
                            Effect.BlindUp('quickCartPopup', {duration:0.25});
                            return false;
                        }
                    },
                    'a.ajaxPopUpClose' : function(element)
                    {
                        element.onclick = function()
                        {
                           Effect.BlindUp('quickCartPopup', {duration:0.25});
                           return false;
                        }
                    },
                    'a.ajaxRemove' : function(element)
                    {
                        element.onclick = function()
                        {
                            if(!microCartPending)
                            {
                               var ar = new Ajax.Request(element.href,{method:"post",onCreate: loading(element), onComplete: updateCart});   
                            }
                            return false;
                        }
                    },
                    'a.ajaxAddToCart' : function(element)
                    {
                        element.onclick = function()
                        {
                            if(!microCartPending)
                            {
                                   var url = "/jsp/shoppingCart/processMicroCart.jsp?" + getUrlParameters(element.href) ;
                                var ar = new Ajax.Request(url,{method:"post",onCreate: loadingLink(element), onComplete: updateCart});   
                            }
                            return false;
                        }
                    } 
                    ,
                    'a.ajaxAddToCartExtLink' : function(element)
                    {
                        element.onclick = function()
                        {
                            if(!microCartPending)
                            {
                                   var url = "/jsp/shoppingCart/processMicroCart.jsp?" + getUrlParameters(element.href) ;
                                var ar = new Ajax.Request(url,{method:"post",onCreate: loadingLink(element), onComplete: updateCart});   
                            }
                            return false;
                        }
                    }                          
                }
                Behaviour.register(ajaxrules);

//We can't use behaviour for product compare pages.This invoked using the onlcik event of buy button image
function productCompareAjaxAddToCart(element,formId)
{
    var url = "/jsp/shoppingCart/processMicroCart.jsp?action=buy&" + getFormParameters($(formId)) ;
    var ar = new Ajax.Request(url,{method:"post",onCreate: loading(element), onComplete: updateCart});   
    return false;
}

function fromAjaxPageAjaxAddToCart(url,element)
{
   if(!microCartPending)
   {
        var url = "/jsp/shoppingCart/processMicroCart.jsp?" + getUrlParameters(url) ;
        var ar = new Ajax.Request(url,{method:"post",onCreate: loadingLink(element), onComplete: updateCart}); 
   }
   return false;
}

function findPos(obj)
{
    var curleft = curtop = 0;
    if (obj.offsetParent) {
        curleft = obj.offsetLeft
        curtop = obj.offsetTop
        while (obj = obj.offsetParent) {
            curleft += obj.offsetLeft
            curtop += obj.offsetTop
        }
    }
    return [curleft,curtop];
}

function toggleDivDisplay(divId,key)
{
    if(key!=null)
    {
        $(divId).innerHTML=key;
        $(divId).show();
    }
    else
    {
        $(divId).hide();
    }                   
}

function isElementExists(element)
{
    if(element!='undefined' || element!=null )
    {
        return true;
    }
    else
    {
        return false;
    }
}

function f_scrollTop() {
    return f_filterResults (
        window.pageYOffset ? window.pageYOffset : 0,
        document.documentElement ? document.documentElement.scrollTop : 0,
        document.body ? document.body.scrollTop : 0
    );
}

function f_filterResults(n_win, n_docel, n_body) {
    var n_result = n_win ? n_win : 0;
    if (n_docel && (!n_result || (n_result > n_docel)))
        n_result = n_docel;
    return n_body && (!n_result || (n_result > n_body)) ? n_body : n_result;
}

function lazyDisplayImages()
{   
    var imgLoaded = $('qcImgLoaded').innerHTML;
    if(imgLoaded=='false')
    {
        $('quickCartBottom').innerHTML="<img src='" + $('qcBottomImg').innerHTML + "' class='no_border'/>"    ;
        $('quickCartTop').innerHTML="<img src='" + $('qcTopImg').innerHTML + "' class='no_border'/>"    ;
        $('quickCartMiddle').style.backgroundImage = "url("+$('qcMiddleImg').innerHTML+")"
        $('cart').innerHTML="<img src='" + $('qcCartImg').innerHTML + "' class='no_border'/>"    ;
        $('quickCartRemove').innerHTML="<img src='" + $('qcRemoveImg').innerHTML + "' class='no_border'/>"    ;
        $('quickCartButtons').innerHTML="<a href='/jsp/shoppingCart/shoppingCart.jsp'><img src='"+ $('qcButtonImg').innerHTML+"'/></a>";
        $('qcImgLoaded').innerHTML=true;
    }
   
}

/* mouseover popup images for parts on search results / altsubs */

function showImg(id) {
 var img = document.getElementById('img' + id);
 // If the standard is 1x1 it means that a spacer has been returned and that the image doesn't exist and shouldn't be shown.
 if (img && img.width != 1 && img.height != 1) {
  img.nextStyle='block'; // Make sure that nextStyle is set back to block.
  img.style.display='block';
 }
}
function hideImg(id) {
 var img = document.getElementById('img' + id);
 img.nextStyle='none'; // as onmouseout may be fired when img is displayed, later onmouseover events will set nextStyle back to block.
 var closure=function() {
	 img.style.display=img.nextStyle;
 }
 setTimeout(closure,100);
}


/*
    Search lookahead code
*/


function registerSearchLookahead(){
   
    try {
    $('globalsearchsuggestions').show();
   
	//OT45036 Extending the Ajax.Autocompleter Class to add additional validations before doing the ajax call.
	SearchLookAhead = {};

	SearchLookAhead =  Class.create(Ajax.Autocompleter, {
		//Overriding the built-in funtion, Ajax.Autocompleter.getToken()
		getToken: function($super) {
			var returnToken = $super();
			var searchTextValue = this.element.value;
			if ($('productSearchTextHelp') != undefined) {
				var defaultMessage = $('productSearchTextHelp').value;
				if( searchTextValue == defaultMessage){
					//Reseting the Token to single space character to avoid unwanted ajax calls
					returnToken = ' ';
				}
			}
			return returnToken;
		},
			show: function($super) {
					$super();
					if ($$('.globalHeader .headerSearch').length > 0) {
						$$('.globalHeader .headerSearch')[0].addClassName('headerSearch-lookAheadActive');
					}
		},
	
			hide: function($super) {
					$super();
					if ($$('.globalHeader .headerSearch').length > 0) {
						$$('.globalHeader .headerSearch')[0].removeClassName('headerSearch-lookAheadActive');
					}
		}
										

	});

    // this now uses get to allow caching at the apache layer
   
    var searchAutocompleter = new SearchLookAhead($('lookahead_searchbox').value, $('lookahead_searchdiv').value, $('lookahead_servleturi').value, {
        method: "POST",
		encoding: "UTF-8",
		contentType: "application/x-www-form-urlencoded",
        paramName: "lookaheadSearchTerms",
        minChars: $('lookahead_minchars').value,
        parameters:$('lookahead_parameters').value,
        updateElement: function( selectedElement) {
        if(selectedElement != undefined && selectedElement.id !='')
          {
             document.location = selectedElement.getElementsByTagName("A")[0].getAttribute("href");
          }              
        }
    });
   
    }
    catch (ex)
    {
        // intentionally eat all excptions featuire is not critical
    }
}
addOnloadEvent(registerSearchLookahead);

function viewall() {
        document.textsearch.elements['/pf/search/TextSearchFormHandler.suggestions'].value=true;
        document.textsearch.submit();
}

function hideSuggestions() {
  toggleSuggestions('false','globalsearchsuggestions','hideimage');                           
}

// Function to toggle the visiblity of suggestions div
function toggleSuggestions(visible,divId,imageId){
    if ($(divId) == null || $(imageId) == null) {
        return false;
    }

    if(visible=='true') {
        setTimeout(function(){
            var divEl = document.getElementById(divId);
            divEl.style.visibility="visible";
            var searchTextValue = document.textsearch.searchTerms.value;
            if( searchTextValue != null && searchTextValue != '' && searchTextValue.length >= 3 ){
                divEl.style.display='';
            }
            $(divEl).setOpacity(1);
            var ImageEl = document.getElementById(imageId);
            ImageEl.style.display='none';
            $('go').style.position = 'relative';
            $('go').style.left = '0px';
			if ($$('.globalHeader .headerSearch').length > 0) {
					$$('.globalHeader .headerSearch')[0].addClassName('headerSearch-lookAheadActive');
				}
            // ImageEl.style.visibility="hidden";
            // storing the object names to session-only cookie
            document.cookie="hiddenObjectName="+imageId + ";path=/";
            document.cookie="visibleObjectName="+divId+ ";path=/";
        }, 600);
      }
      else
       {
       
        // Hidding the suggestions div
        var el = document.getElementById(divId);
        el.style.visibility="hidden";
        el = document.getElementById(imageId);
        el.style.visibility="visible";
        el.style.display='';
        $('go').style.position = 'relative';
        $('go').style.left = '-16px';
        // storing the object names to session-only cookie
        document.cookie="hiddenObjectName="+divId+ ";path=/";
        document.cookie="visibleObjectName="+imageId+ ";path=/";
		if ($$('.globalHeader .headerSearch').length > 0) {
				$$('.globalHeader .headerSearch')[0].removeClassName('headerSearch-lookAheadActive');
			}
       
    }
    document.textsearch.searchTerms.focus();
}

// Routine Function to retrieve the value from a session cookie.
function get_cookie(Name) {
    var search = Name + "="
    var returnvalue = "";
    if (document.cookie.length > 0) {
        offset = document.cookie.indexOf(search)
        // if cookie exists
        if (offset != -1) {
            offset += search.length
            // set index of beginning of value
            end = document.cookie.indexOf(";", offset);
            // set index of end of cookie value
            if (end == -1)
                end = document.cookie.length;
            returnvalue=unescape(document.cookie.substring(offset, end))
        }
    }
    return returnvalue;
}

// Function to initialize the visibility of hide suggestions image.
function initializeHideImage() {
	if (get_cookie("hiddenObjectName")!="" && get_cookie("visibleObjectName")!=""){
		document.getElementById(get_cookie("hiddenObjectName")).style.visibility="hidden";
		document.getElementById(get_cookie("visibleObjectName")).style.visibility="visible";
		if(get_cookie("visibleObjectName") == "hideimage"){
			document.getElementById(get_cookie("visibleObjectName")).style.display='';
			document.getElementById('go').style.position = 'relative';
			document.getElementById('go').style.left = '-16px';
		}
	}
}
addOnloadEvent(initializeHideImage);

// variable to hold the id of the current highlighted li
// initializing it to the top most li of the div.
var currentRowId = 'suggestionroot';

var keyEventOccured = false; // This variable is used to check for the keyevent FIX for OT

function highlightRows(ev){
    var key = (window.event) ? window.event.keyCode : ev.keyCode;

    keyEventOccured = true;

    var ARRUP = 38;
    var ARRDN = 40;
    var ENTER_KEY = 13;
   
    // Ending the function if the div is not yet displayed.
    if($(currentRowId) == undefined){
        return;
    }

    //resetHightlight();

    switch(key)
    {
        case ARRUP:
            this.moveHighlightUp();
            break;

        case ARRDN:
            this.moveHighlightDown();
            break;

        case ENTER_KEY:
            this.submitOnEnterKey();
            break;

        default:
            resetHightlight();
            currentRowId = 'suggestionroot';
            break;
    }

}

// The functions uses functions from Protoaculous library.

function moveHighlightUp(){
    if($(currentRowId).previousSiblings() != undefined
            && $(currentRowId).previousSiblings().first() != undefined
            && $(currentRowId).previousSiblings().first().id != 'suggestionroot'
            && $(currentRowId).previousSiblings().first().down('A') != undefined){

        resetHightlight();
		// Skipping the element if it is the category header
		if($(currentRowId).previous().hasClassName('categoryListHeader')){
			currentRowId = $(currentRowId).previous().id;
		}
        $(currentRowId).previousSiblings().first().down('A').setStyle({backgroundColor: '#ffb'});
        currentRowId = $(currentRowId).previous().id;
    }
}

function moveHighlightDown(){
    if(currentRowId == 'suggestionroot'){

        currentRowId = $(currentRowId).down() != undefined ? $(currentRowId).down().id : 'sugg_1';
        $(currentRowId).down('A').setStyle({backgroundColor: '#ffb'});
    }
    else if($(currentRowId).nextSiblings() != undefined 
				&& $(currentRowId).nextSiblings().first() != undefined
				&& $(currentRowId).nextSiblings().first().down('A') != undefined){

        resetHightlight();
		// Skipping the element if it is the category header
		if($(currentRowId).next().hasClassName('categoryListHeader')){
			currentRowId = $(currentRowId).next().id;
		}
        $(currentRowId).nextSiblings().first().down('A').setStyle({backgroundColor: '#ffb'});
        currentRowId = $(currentRowId).next().id;
    }
}

function resetHightlight(){
   if($(currentRowId) != undefined && $(currentRowId).down('A') != undefined){
        $(currentRowId).down('A').style.backgroundColor='';
    }
}
/***********************
    OT33360 Fix End
 ***********************/

function mouseOverHandler(){
    if ($('globalsearchsuggestions') == null) {
        return false;
    }
   
    Event.observe('globalsearchsuggestions', 'mousemove', function(event) {
        var elt = Event.element(event);
        if (elt != undefined) {
            if(elt.tagName == 'Li' && elt.id != undefined && elt.id != ''){
                mouseOverHighlight(elt.id);
            }
            else if(elt.tagName == 'A' && elt.up().tagName == 'LI'){
                mouseOverHighlight(elt.up().id);
            }
        }
    });
}
addOnloadEvent(mouseOverHandler);

function mouseOverHighlight(rowId){
    if(rowId == '' || rowId == null || $(rowId) == undefined){
        return;
    }
    if(currentRowId != 'suggestionroot'){
        resetHightlight();
    }
    currentRowId = rowId;
    $(currentRowId).down('A').setStyle({backgroundColor: '#ffb'});
}

function resetOnMouseOver(){
    if(currentRowId != 'suggestionroot'){
        resetHightlight();
        currentRowId = 'suggestionroot';
	}
}

// Function for Submitting the search form if Enter Key is pressed
function submitOnEnterKey(){

    if($(currentRowId).id != 'suggestionroot')
    {
        document.location.href = $(currentRowId).down('A').href;
    }
    else
    {
        document.textsearch.submit();
    }
   
}

function getQueryString(param) 
{
	var queryStringURI = window.location.search.substring(1);
	if(queryStringURI!=null)
	{
		var queryStrings= queryStringURI.split("&");
		var queryString;
		for (i=0;i<queryStrings.length;i++)
		{
			queryString = queryStrings[i].split("=");
			if (queryString[0] == param) 
			{
				return queryString[1];
			}
		}
	}
	return "";
}

function callForSuggestion(){
    var searchTextValue = document.textsearch.searchTerms.value;
	if ($('productSearchTextHelp') != undefined)
	{
		var defaultMessage = $('productSearchTextHelp').value;
	}
    if( searchTextValue != null && searchTextValue != '' && searchTextValue.length >= 3 
        && searchTextValue != defaultMessage && getQueryString('Ntt')!=searchTextValue){
        var params = 'lookaheadSearchTerms='+searchTextValue+'&'+ $('lookahead_parameters').value
        new Ajax.Updater('globalsearchsuggestions', $('lookahead_servleturi').value, {
          parameters: params
        });
    }

    if(keyEventOccured){
        $('globalsearchsuggestions').show();
    }
}
addOnloadEvent(callForSuggestion);

// links the specified object's visibility to the state of a checkbox
var visibilityController = Class.create({
    initialize: function(theTarget, theCheckbox) {
        if (typeof theTarget == 'string') {
            theTarget = $(theTarget);
        }
        if (typeof theCheckbox == 'string') {
            theCheckbox = $(theCheckbox);
        }
       
        if(theCheckbox == null || theTarget == null) {
            return false;
        }       
        this.theTarget = theTarget;
        this.theCheckbox = theCheckbox;
        this.theCheckbox.observe('click', this.setVisibility.bind(this));
        this.setVisibility();
    },
   
    setVisibility: function () {
        if (this.theCheckbox.checked) {
            this.theTarget.show();
        } else {
            this.theTarget.hide();
        }
    }
});

//F0249363 - Issue with look ahead search field on Firefox4. Unable to properly use search field.

addOnloadEvent(function(){
	$('searchTerms').observe('click',initializeProductSearchTextmsg).observe('keydown',initializeProductSearchTextmsg);
	});

/* Initialize Product search Instruction text message BAU 41014 */
function initializeProductSearchTextmsg(){
    // The id ProductSearchTextHelp holds the message to be displayed within search textbox
		searchTermsField = $('searchTerms');
		hiddenElem = $('productSearchTextHelp');
		if(searchTermsField.value == hiddenElem.value) {
			searchTermsField.value='';
			searchTermsField.focus();
		}
		searchTermsField.style.color = "black";
		searchTermsField.style.fontWeight = "normal";
}



function resetProductSearchTextmsg(){
	hiddenElem = $('productSearchTextHelp');
	searchTermsField = $('searchTerms');
	if((searchTermsField.value == '') || (searchTermsField.value == hiddenElem.value)){
	  searchTermsField.style.color = "#D2D2C9";
	  searchTermsField.style.fontWeight = "bold";
	  searchTermsField.value = hiddenElem.value;
	}
}
if(navigator.userAgent.indexOf("Firefox") > 0){
	window.onbeforeunload=resetProductSearchTextmsg;
}
addOnloadEvent(resetProductSearchTextmsg);
/* advanced search */

var newItemsCheckbox = new visibilityController('searchTimeRange', 'onlyNewProducts');

/*
 * Support for cancelling Ajax requests. This is required if there is a possibility that there will be two or more Ajax request
  * which will use up the default number of connections that a browser can make (although this is 6 in IE8, it can still be an issue).
  * This can happen if the Ajax request is long running (returns a lot of data or the latency on the connection is too high). This
  * is the case for searchlookahead in APAC for instance.
  * For example, to enable the AjaxCanelPolicy for search lookahead, call the following JavaScript
  *     AjaxCancelPolicy.register(/^\/\w+\/SuggestionLookupServlet/);
 */

var AjaxCancelPolicy =
{
	patterns: $A(),

	register: function(pattern) {this.patterns.push(pattern);},

	matchingPattern: function(url)
	{
		return this.patterns.find(function(pattern)
		{
			var matches = url.match(pattern);
			return (matches != null && matches.size() > 0);
		});
	}
};

Ajax.Responders.register({
	pendingResponses: $H(),

	onCreate: function(r)
	{
		// Current request is stored in the pendingResponses hash, keyed on the pattern
		var pattern = AjaxCancelPolicy.matchingPattern(r.url);
		var current = this.pendingResponses.get(pattern);

		if (current && current.transport)
		{
			current.transport.abort();
		}

		this.pendingResponses.set(pattern,r);
	},

	onComplete: function(r)
	{
		var pattern = AjaxCancelPolicy.matchingPattern(r.url);

		this.pendingResponses.unset(pattern);
	}
});

/**
 * The following javascript functions are used for homeZone3 product display in the home page
 * These are used in ../home/fragments/homePage/home.jsp
 *
 */
 // updated to support delayed protoaculous load - OT 36068
function homeZone3Page()
{		
	if(typeof homeZone3 != 'undefined')
	{
		setTimeout(function() 
					{
						var url = "/jsp/profile/recommendedProductHomeZone3.jsp?fromPage=homeZone";
						var ar = new Ajax.Request(url,{method:"post",onCreate: loadBusy, onComplete: updateHomeZone3Page});
					}, 1500 );	
	}
}
// add onload event handler for homeZone3
addOnloadEvent(homeZone3Page);

function loadBusy()
{
	
}

//Removes leading whitespaces
function LTrim( value ) 
{
	var re = /\s*((\S+\s*)*)/;
	return value.replace(re, "$1");	
}

// Removes ending whitespaces
function RTrim( value ) {
	
	var re = /((\s*\S+)*)\s*/;
	return value.replace(re, "$1");
	
}

// Removes leading and ending whitespaces
function trim( value ) 
{
	return LTrim(RTrim(value));	
}

function updateHomeZone3Page(request)
{
	var divId = $('homeZone3DivId').value;
	var req = request.responseText;
	if(trim(req) != 'empty')
	{		
		$(divId).hide();
		$(divId).innerHTML=req;
		Effect.SlideDown($(divId), { duration: 2.0 });		
	}	
}

/** MCSS functions end here **/

// fix for OT35922
function showeMail()
{
  if (document.getElementById("optOutEmail")) {
	var eMailChecked = document.getElementById("optOutEmail").checked;
	// emaildiv will not be available for NEWARK Brand and some locales of the other environments
	if(document.getElementById("emaildiv") != undefined){

	  if (eMailChecked==true)
	  {
		document.getElementById("emaildiv").style.display = "block";
	  } 
	  else 
	  {
		document.getElementById("emaildiv").style.display = "none";
	  }
	}
  }
}

addOnloadEvent(showeMail);

function changeFocusOnMouseOver(){
	Event.observe('hideimage', 'mouseover', function(event) {
		$('go').focus();
	});
	Event.observe('hideimage', 'mouseout', function(event) {
		document.textsearch.searchTerms.focus();
	});
}
addOnloadEvent(changeFocusOnMouseOver);


function clearSearchTextMsg(event,clearbox)
{
   if((event==2))
   {
	   if((event==2))
	   {
		if(clearbox.value==$('productSearchTextHelp').value)
	   	{
	     		clearbox.value="";
	  	}
	   	clearbox.style.color = "black";
		clearbox.style.fontWeight = "normal";
	   }
   }
}

/**
 *  secondary navigation
 *
 */
 
 
var totalRenderingTime = 0;
var outputTiming = window.location.href.indexOf('outputSecNavTiming')!=-1;
// init secnav: set each LI with a contained UL as a trigger
var secondaryNavigation = Class.create({
  
  initialize: function(theStart) {
	  secnavstart = new Date();
		// unfortunate but unavoidable browser sniffing for ie6 and whether to add iframe
		var addIframes = false;
		if (Prototype.Browser.IE && navigator.userAgent.toLowerCase().indexOf('msie 6') != -1) {
			addIframes = true;
		}
		
		var theTarget = null;
		if (theStart.match('ul')) { // sent in a ul, start here
			theTarget = theStart;
		} else if (theStart.down('ul')) { // use the first ul contained by sent object
			theTarget = theStart.down('ul');
		} else { // nothing to do
			return false;
		}
		
    this.triggerList = new Array();
    var thisSecNav = this;
    
    // put trigger on only the LIs directly under this UL; recursively look for ULs to make new secnavs
    var thisSecNavId = theTarget.identify();
 		$$("#"+thisSecNavId+" > li").each(function (trigger) {
				/* To be used when we have more than two levels of display (submenu inside a submenu)
				var containedUL = trigger.down("ul");
				if (containedUL) {
					thisSecNav.subsecNav = new secondaryNavigation(containedUL);
				}*/
				thisSecNav.triggerList.push(new secondaryNavigationMenu(trigger, thisSecNav, addIframes));
		});
secnavend = new Date();
totalRenderingTime += (secnavend - secnavstart);
if (outputTiming) console.log(totalRenderingTime);
	}
	
});

var secondaryNavigationMenu = Class.create({
	initialize: function(trigger, parent, addIframes) {
		this.addIframes = addIframes;
		this.parent = parent;
		
		this.id = $(trigger).identify();
		// mouseover/out triggers for revealing the menu
		$(trigger).observe('mouseover', this.activateMouseover.bind(this) );
		$(trigger).observe('mouseout', this.deactivateMouseout.bind(this) );
		
		this.subMenuUL = trigger.down('ul'); // object that contains the UL with the subnav items
		
		if (!this.subMenuUL) { // no submenu; just an LI
			this.hasNoMenu = true;
			return;
		}
		
	},
	

	postInitialize: function() {
		this.maxHeight=400;
		this.scrollRate=200; // pixels per second
		this.scrolling=false; // flag for whether this menu is currently scrolling
		this.scrollTriggerHeight=25; // height as defined in the stylesheet (in pixels)
		this.isActive=false;
		this.subMenuULId = $(this.subMenuUL).identify();
		// get the height of the UL now; after the .wrap it'll be 0
		this.subMenuULHeight = this.subMenuUL.getHeight();

		var trigger = $(this.id);
        var addIframes=this.addIframes;

		// create submenu elements
		this.subMenu = this.subMenuUL.wrap('div', { 'class' : 'subMenu' } );
		this.subMenuId = this.subMenu.identify();
		this.upTrigger = new Element('div', { 'class' : 'scrollArea up' } );
		this.downTrigger = new Element('div', { 'class' : 'scrollArea down' } );
		this.subMenuUL.addClassName('active');
		
		this.setScrollTriggersDisplay('none');
		this.subMenu.appendChild(this.upTrigger);
		this.subMenu.appendChild(this.downTrigger);


		if (addIframes) {
			this.iFrame = new Element('iframe');
			this.subMenu.appendChild(this.iFrame);
		}
				
		$(trigger).addClassName('hasSubMenu');
		
		// mouseover/out triggers for nav up/down areas
		$(this.upTrigger).observe('mouseover', this.startScroll.bind(this, 'up'));
		$(this.upTrigger).observe('mouseout', this.stopScroll.bind(this, 'up'));
		$(this.downTrigger).observe('mouseover', this.startScroll.bind(this, 'down'));
		$(this.downTrigger).observe('mouseout', this.stopScroll.bind(this, 'down'));

	},
	


	// mouseover on the right thing?
	activateMouseover: function(e) {
		var reltg = (e.relatedTarget) ? e.relatedTarget : e.toElement;
		//console.log("reltg: %o" ,reltg);
//		if (!$(reltg) || $(reltg).up('#'+this.id)) { return; }
		if ( $(reltg) && $(reltg).up('#'+this.id)) { return; }
		//console.log("deactivate: %s", $A(arguments));
		this.activate();
	},
	
	activate: function() {

		if (this.isActive==null) { this.postInitialize();}

		if (this.isActive) { return; }
		
		// check for other active menus and deactivate if necessary
		this.parent.triggerList.each( function(trigger) {
			if (trigger.isActive) { 
				trigger.deactivate();
			}
		});
		
		this.isActive = true;
		var trigger = $(this.id);

		
		if (this.hasNoMenu) { // no submenu, just do the highlight
			trigger.addClassName('active');
			return;
		}
		
		//console.log("in trigger: %o isActive: %s", trigger, this.isActive);
		var subMenu = $(this.subMenuId);
		
		// height of the UL in this box
		var theULHeight = this.subMenuULHeight;
		var theHeight = Math.min(this.maxHeight, theULHeight);
		var subMenuHeight = theHeight;
		var theCurrentOffset = trigger.viewportOffset()[1];
		var limitTop = 0;
		
		// don't overlap nav if it's visible
		if ($('messagebar')) {
			limitTop = Math.max($('messagebar').viewportOffset()[1] + $('messagebar').getHeight(), limitTop);
		} else if ($('navigation')) {
			limitTop = Math.max($('navigation').viewportOffset()[1] + $('navigation').getHeight(), limitTop);
		} else if ($('navBar2')) {
			limitTop = Math.max($('navBar2').viewportOffset()[1] + $('navBar2').getHeight(), limitTop);
		}

		limitBottom = document.viewport.getHeight() - 2;
		
		newOffset = theCurrentOffset;
		// if it's below the bottom of the screen, move it up
		if (newOffset + theHeight > limitBottom) {
			newOffset = newOffset - (theHeight - (limitBottom - theCurrentOffset));
		}
		// unless that would put it above navigation (or the top of the viewport)
		if (newOffset < limitTop) {
			newOffset = limitTop + 2;
		}
		
		// if the contents are larger than the submenu block, move and reveal arrows
		//alert(this.subMenuULHeight + " " + theHeight);
		if (this.subMenuULHeight > theHeight) {
			this.subMenuUL.setStyle({ top: this.scrollTriggerHeight + 'px'});
			this.setScrollTriggersDisplay('block');
		}
		;
		
		// what to set the style to: theCurrentOffset is the vertical position of the LI relative to the top of the
		// viewport, but we have to give the style a value relative to the LI's position
		newPosition = newOffset - theCurrentOffset;

		if ((this.subMenuULHeight > theHeight) && ((newOffset + theHeight < limitBottom))) {
			// If the scrollbars need to be displayed, position the flyout such that the level 1 category aligns with the level 0 category -
			// and does not align with the scrolling arrows.
			// The latter condition makes sure that the offset is not reduced if the menu is popping up
			newPosition = newPosition - 25;
		}
		//alert(newPosition+", "+ limitTop +", "+limitBottom  +", "+theCurrentOffset+", "+theHeight);
		subMenu.setStyle({
			 top: newPosition + 'px',
			 height: theHeight + 'px'
		});

		if (this.iFrame) {
			this.iFrame.setStyle({ 'height': theHeight + 'px' });
		}

		trigger.addClassName('active');
		subMenu.addClassName('active');
		this["upTrigger"].addClassName("upInActive");
		this["downTrigger"].removeClassName("downInActive");
		
		// get size of active scroll area
		this.visibleScrollSize = this.downTrigger.positionedOffset()[1] - this.scrollTriggerHeight;
		
		//var this.downTrigger.positionedOffset()[1]);
		
	},
	
	setScrollTriggersDisplay: function(dispVal) {
		this.upTrigger.setStyle({ display : dispVal });
		this.downTrigger.setStyle({ display : dispVal });
	},
	
	startScroll: function(dir, e) {
		//console.log("startScroll");
		if (this.scrolling) { return; }
		this[dir+"Trigger"].addClassName(dir+"Active");
		var currentY = this.subMenuUL.positionedOffset()[1];
		if(dir == 'down') {
			this["upTrigger"].removeClassName("upInActive");
		} else {
			this["downTrigger"].removeClassName("downInActive");
		}

		//alert(currentY + " " + this.subMenuUL.getOffsetParent().identify() + " " + this.downTrigger.positionedOffset()[1]);
		if (dir == 'down') {
			var targetY = this.scrollTriggerHeight - (this.subMenuULHeight - this.visibleScrollSize);
		} else {
			var targetY = this.scrollTriggerHeight;
		}
		if (currentY == targetY) { return; }
		
		var scrollDelta = -(currentY - targetY);
		var scrollTime = Math.abs(scrollDelta) / this.scrollRate;
		//console.log("currentY %d, targetY %d, scrollDelta %d, scrollTime %d", currentY, targetY, scrollDelta, scrollTime);
		//alert(currentY + " " + targetY + " " + scrollDelta + " " + scrollTime);
		this.scrolling = true;
		this.moveEffect = new Effect.Move(this.subMenuUL, {
			x:0, y:scrollDelta, 
			duration:scrollTime,
			transition: Effect.Transitions.linear,
			beforeUpdate: this.checkForStop.bind(this),
			afterFinish: this.afterDone.bind(this, dir)
		});
	
	},
	
	checkForStop: function() {
		if (!this.scrolling) {
			this.moveEffect.cancel();
		}
	},
	
	afterDone: function(dir, e) {
		this.scrolling = false;
		var currentY = this.subMenuUL.positionedOffset()[1];
		var targetY;
		if (dir == 'down') {
			targetY = this.scrollTriggerHeight - (this.subMenuULHeight - this.visibleScrollSize);
		} else {
			targetY = this.scrollTriggerHeight;
			}
			if (currentY === targetY) {
			this[dir+"Trigger"].addClassName(dir+"InActive");
		}
	},

	stopScroll: function(dir, e) {
		this.scrolling = false;
		this[dir+"Trigger"].removeClassName(dir+"Active");
	},
	
	// mouseout on the right thing?
	deactivateMouseout: function(e) {
		var reltg = (e.relatedTarget) ? e.relatedTarget : e.toElement;
		try
		{
			if (!$(reltg) || $(reltg).up('#'+this.id)) { return; }
		}
		catch (e)
		{
			// OT41375 Ignore error for textarea 'up' property
		}
		
		this.deactivate();
	},
	
	deactivate: function() {
		var trigger = $(this.id);
		if (!this.isActive) { return; }
		//console.log("out trigger: %o event element: %o", trigger, Event.element(e));
		this.isActive=false;
		
		if (this.hasNoMenu) { // no submenu, just remove the highlight
			trigger.removeClassName('active');
			return; 
		} 
		
		var subMenu = $(this.subMenuId);
		trigger.removeClassName('active');
		subMenu.removeClassName('active');
		subMenu.setStyle({ top: '0px' });
		this.subMenuUL.setStyle({top: '0px'});
		this.setScrollTriggersDisplay('none');
		//console.log("out trigger: %o isActive: %s", trigger, this.isActive);
	}
	
});

addOnloadEvent( function () {
	if ($('secnav') != null) {
		pf_secNav = new secondaryNavigation($('secnav'));
	}
});
 

/**
 *  end secondary navigation
 *
 */
 
 


/*
	"Lightbox Gone Wild" Modal Dialog Library functions
*/
/*
Created By: Chris Campbell
Website: http://particletree.com
Date: 2/1/2006

Inspired by the lightbox implementation found at http://www.huddletogether.com/projects/lightbox/
*/

/*-------------------------------GLOBAL VARIABLES------------------------------------*/

var detect = navigator.userAgent.toLowerCase();
var OS,browser,version,total,thestring;

/*-----------------------------------------------------------------------------------------------*/

//Browser detect script origionally created by Peter Paul Koch at http://www.quirksmode.org/

function getBrowserInfo() {
	if (checkIt('konqueror')) {
		browser = "Konqueror";
		OS = "Linux";
	}
	else if (checkIt('safari')) browser 	= "Safari"
	else if (checkIt('omniweb')) browser 	= "OmniWeb"
	else if (checkIt('opera')) browser 		= "Opera"
	else if (checkIt('webtv')) browser 		= "WebTV";
	else if (checkIt('icab')) browser 		= "iCab"
	else if (checkIt('msie')) browser 		= "Internet Explorer"
	else if (!checkIt('compatible')) {
		browser = "Netscape Navigator"
		version = detect.charAt(8);
	}
	else browser = "An unknown browser";

	if (!version) version = detect.charAt(place + thestring.length);

	if (!OS) {
		if (checkIt('linux')) OS 		= "Linux";
		else if (checkIt('x11')) OS 	= "Unix";
		else if (checkIt('mac')) OS 	= "Mac"
		else if (checkIt('win')) OS 	= "Windows"
		else OS 								= "an unknown operating system";
	}
}

function checkIt(string) {
	place = detect.indexOf(string) + 1;
	thestring = string;
	return place;
}

/*-----------------------------------------------------------------------------------------------*/

Event.observe(window, 'load', lightboxInitialize, false);
Event.observe(window, 'load', getBrowserInfo, false);
if (Event.unloadCache)
{
	Event.observe(window, 'unload', Event.unloadCache, false);
}

var lightbox = Class.create();

lightbox.prototype = {

	yPos : 0,
	xPos : 0,

	initialize: function(ctrl) {
		this.content = ctrl.href;
		Event.observe(ctrl, 'click', this.activate.bindAsEventListener(this), false);
		ctrl.onclick = function(){return false;};
	},
	
	// Turn everything on - mainly the IE fixes
	activate: function(){
		if (browser == 'Internet Explorer'){
			this.getScroll();
			this.prepareIE('100%', 'hidden');
			this.setScroll(0,0);
			this.hideSelects('hidden');
		}
		this.displayLightbox("block");
	},
	
	// Ie requires height to 100% and overflow hidden or else you can scroll down past the lightbox
	prepareIE: function(height, overflow){
		bod = document.getElementsByTagName('body')[0];
		bod.style.height = height;
		bod.style.overflow = overflow;
  
		htm = document.getElementsByTagName('html')[0];
		htm.style.height = height;
		htm.style.overflow = overflow; 
	},
	
	// In IE, select elements hover on top of the lightbox
	hideSelects: function(visibility){
		selects = document.getElementsByTagName('select');
		for(i = 0; i < selects.length; i++) {
			selects[i].style.visibility = visibility;
		}
	},
	
	// Taken from lightbox implementation found at http://www.huddletogether.com/projects/lightbox/
	getScroll: function(){
		if (self.pageYOffset) {
			this.yPos = self.pageYOffset;
		} else if (document.documentElement && document.documentElement.scrollTop){
			this.yPos = document.documentElement.scrollTop; 
		} else if (document.body) {
			this.yPos = document.body.scrollTop;
		}
	},
	
	setScroll: function(x, y){
		window.scrollTo(x, y); 
	},
	
	displayLightbox: function(display){
		$('overlay').style.display = display;
		$('lightbox').style.display = display;
		if(display != 'none') this.loadInfo();
	},
	
	// Begin Ajax request based off of the href of the clicked linked
	loadInfo: function() {
		var myAjax = new Ajax.Request(
        this.content,
        {method: 'post', parameters: "", onComplete: this.processInfo.bindAsEventListener(this)}
		);
		
	},
	
	// Display Ajax response
	processInfo: function(response){
		info = "<div id='lbContent'>" + response.responseText + "</div>";
		new Insertion.Before($('lbLoadMessage'), info)
		$('lightbox').className = "done";	
		this.actions();			
	},
	
	// Search through new links within the lightbox, and attach click event
	actions: function(){
		lbActions = $('lightbox').select('input.lbAction','a.lbAction');

		for(i = 0; i < lbActions.length; i++) {
			Event.observe(lbActions[i], 'click', this[lbActions[i].rel].bindAsEventListener(this), false);
			lbActions[i].onclick = function(){return false;};
		}

	},
	
	// Example of creating your own functionality once lightbox is initiated
	insert: function(e){
	   link = Event.element(e).parentNode;
	   Element.remove($('lbContent'));
	 
	   var myAjax = new Ajax.Request(
			  link.href,

			  {method: 'post', parameters: "", onComplete: this.processInfo.bindAsEventListener(this)}

	   );
	 
	},
	
	// Example of creating your own functionality once lightbox is initiated
	deactivate: function(){
		Element.remove($('lbContent'));
		
		if (browser == "Internet Explorer"){
			this.setScroll(0,this.yPos);
			//this.prepareIE("auto", "auto");
			this.prepareIE("", "");
			this.hideSelects("visible");
		}
		
		this.displayLightbox("none");
	}
}

/*-----------------------------------------------------------------------------------------------*/

// Onload, make all links that need to trigger a lightbox active
function lightboxInitialize(){
	addLightboxMarkup();
	lbox = $$('lbOn');
	if (lbox) {
		for(i = 0; i < lbox.length; i++) {
			valid = new lightbox(lbox[i]);
		}
	}
}

// Onload, make all links that need to trigger a lightbox active on loading of page
function initializeOnLoad(){
	addLightboxMarkup();
	lbox = $$('lbOn');
	for(i = 0; i < lbox.length; i++) {
		valid = new lightbox(lbox[i]);
		valid.start(valid);
	}
}

// Add in markup necessary to make this work. Basically two divs:
// Overlay holds the shadow
// Lightbox is the centered square that the content is put into.
function addLightboxMarkup() {
	bod 				= document.getElementsByTagName('body')[0];
	overlay 			= document.createElement('div');
	overlay.id		= 'overlay';
	lb					= document.createElement('div');
	lb.id				= 'lightbox';
	lb.className 	= 'loading';
	lb.innerHTML	= '<div id="lbLoadMessage">' +
						  '</div>';
	bod.appendChild(overlay);
	bod.appendChild(lb);
}


// PF function
// Display a Lightbox instance. Pass the URL of HTML and the instance
function displayLightbox(fileName, myLightbox){
	addLightboxMarkup();
	myLightbox.content = fileName;
	myLightbox.activate();
}

/*
	End "Lightbox Gone Wild"
*/

/*
spotted error lightbox on product detail page -- should be done cleaner but I have to move
the Class.create off the jsp to a place after prototype is loaded
*/
SpottedErrorLightBox = {};

if (typeof displaySpottedErrorLightBox == 'function') {
	SpottedErrorLightBox = Class.create(lightbox, {
	    initialize: function() {
	        this.processed=false;
	    },
	    clickYes: function() {
	    	if(this.processed==false){
		        var typeOfErrorSelected = false;
		        var errorTypeCheckboxes = $$("input.error_type");
		        var checkBoxesSelected = "";
		        var other=$("error_type_other");
		        var additionalInfo = $("additionalInfo").value;
		        for(i = 0 ; i < errorTypeCheckboxes.length ; ++i) {
		            if($(errorTypeCheckboxes[i]).checked == true) {
		                typeOfErrorSelected = true;
		                if(checkBoxesSelected.length != 0 ) {
		                    checkBoxesSelected += ";" ;
		                }
		                checkBoxesSelected += $(errorTypeCheckboxes[i]).value;
		            }
		        }
		        if(!typeOfErrorSelected) {
		            $("checkboxes").style.border = "1px dashed red";
		            $("typeOfErrorHidden").style.display = "block";
		            return;
		        }
		        else if(other.checked && additionalInfo==""){
		        	$("additionalInfoContaner").style.border = "1px dashed red";
		         	$("additionalInfoError").show();
		         	$("checkboxes").style.border = "none";
		         	$("typeOfErrorHidden").hide();
		            return;
		        }
		         else {
		         	this.processed=true;
		            var url = "/jsp/viewDefault/search/fragments/processSpottedError.jsp" ;
		            var orderCode = $("orderCode").value;
		            var mfrName = $("mfrName").value;
		            var mfrPart = $("mfrPart").value;
		            var mfrDesc = $("mfrDesc").value;
		            var additionalInfo = $("additionalInfo").value;
		            var name = $("name").value;
		            var email = $("email").value;
		
		            var parameters = "";
		            parameters += "orderCode=" + encodeURIComponent(orderCode);
		            parameters += "&mfrName=" + encodeURIComponent(mfrName);
		            parameters += "&mfrPart=" + encodeURIComponent(mfrPart);
		            parameters += "&mfrDesc=" + encodeURIComponent(mfrDesc);
		            parameters += "&typesOfError=" + encodeURIComponent(checkBoxesSelected);
		            parameters += "&additionalInfo=" + encodeURIComponent(additionalInfo);
		            parameters += "&name=" + encodeURIComponent(name);
		            parameters += "&email=" + encodeURIComponent(email);
		
		            new Ajax.Request(
		                url, {
		                method:"post",
		                parameters: parameters,
		                onCreate: this.loadBusy,
		                onComplete: this.displaySuccessMessage
		            });
		        }
		    }
		    return false;
	        
	    },
	    clickNo: function() {
	    	if(this.processed==false)
	    	{
	        	this.deactivate();
	        }
	    },
	    displaySuccessMessage: function() {
	    	 $("lightbox").style.top = "75%";
	    	 $("showmessageform").hide();
	   		 $("successMessage").show();
	    },
	    loadBusy: function() {
	    	 $("spottedAnErrorBusy").show();
	    }
	});
	
	function toggleAdditionalInfoMandatorySymbol(element)
	{
		if(element.checked)
		{
			$("additionalInfoMandatorySymbol").show();
		}
		else
		{
			$("additionalInfoMandatorySymbol").hide();
			$("additionalInfoContaner").style.border = "none";
	        $("additionalInfoError").hide();
		}
		
	}
}


/** Added for Secondary nav flyout closure in New leftnav */

function flyoutCloseLevel1(element)
{
	var elementId = document.getElementById(element).id;
	var containerId="container_"+elementId;

	var ultags = $(containerId).getElementsByClassName("newSubmenu");
	ultags[0].style.display = "none";
}

function setFocusToSearchBox()
{	
	var myUrl = window.location.href;

	/* 
	 *	BAU42471
	 *	The variable, 'setFocusToSearchBox' has been introduced to restrict setting 
	 *	of focus to search field in pages other than Home page.
	 */

	//In the global search page set the focus to the Search text field
	if (myUrl.indexOf("#") == -1 && setFocusToSearchBox == true) 
	{
		for (var f=0; f < document.forms.length; f++) 
		{
			if (document.forms[f].name == "textsearch")
			{
				document.forms[f].searchTerms.focus();
			}
		}
		 
	}
	
	// In Login/Register/PasswordRest page set the focus to the Login text field
	if (myUrl.indexOf("register") != -1||myUrl.indexOf("login") != -1||myUrl.indexOf("reset") != -1)
	{
		for (var f=0; f < document.forms.length; f++) 
		{
			if (document.forms[f].name == "requestPassword")
			{
				document.forms[f].forgotUser.focus();
				break;
			}
			if (document.forms[f].name == "loginfragment")
			{							
				document.forms[f].login.focus();
				break;
			}
		} 
	}
	
   
}
//Set the focus to the Search box after the page loads
addOnloadEvent(setFocusToSearchBox);

function checkRadio(radioButtonName){
	if(radioButtonName=="regBizSelectionRadioBusiness"){
		$("regBizSelectionRadioBusiness").checked=true;
		$("regBizSelectionRadioHome").checked=false;
		$("BUSINESS").value="BUSINESS";
	}
	if(radioButtonName=="regBizSelectionRadioHome"){
		$("regBizSelectionRadioHome").checked=true;
		$("regBizSelectionRadioBusiness").checked=false;
		$("BUSINESS").value="HOME";
	}
}

/*-----------------------------------------------------------------------------------------------*/

/*  
 *  Date:9-october-2009 Developer:Sajeesh Vijayan Summary: US iBuy.
 *  Utility function for Custom Field textboxid's when onfocus clearbackground,
 *  loads an image and onblur refreshbackground. 
 */
function suppressEnterKey(event)
{
	if (event.keyCode == 13)
	{
		return false;
	}
	else
	{
		return true;
	}
}

Helptext1 = "none";

function clearBackground(id)
{
	elem = $(id);
	Helptext1 = elem.style.backgroundImage;
	elem.style.backgroundImage = "none";
}

function refreshBackground(id)
{
	elem = $(id);
	if (elem.value == "")
	{
		elem.style.backgroundImage = Helptext1;
	}
}

/* This function parses through the custom fields textboxid's in the shopping cart page for commerce
 * and quoted items and clears the background image on onload if it finds a value in the textboxes.
 */
function cartcFieldsetup()
{
    /* fetch the value of commerce item rowline count which is variable commercelinecount ,value of 
	 * totallines included in the shopping cart( totallinecount = commerce rowline count + quote rowline count)
	 * from the jsp through a hidden variable commercerowcount and totallinesincart.
	 * Always commercelinecount will have a default value of 1 in the absence of commerce items in cart.
	 */
	
	var commercelinecount = $('commercerowcount').value; 
	var totallinecount = $('totallinesincart').value;

	/* if there are only commerce items in the cart then set totallinecount to value of commerce 
	 * items row count
	 */
	
	if (totallinecount == 0 || totallinecount == null)
	{
		totallinecount = commercelinecount;
	} 
	
	/* Fetch the empty/Blank line count from commerce item shopping cart which is var totalblanklinescount.
	 * If there are no empty lines then set total empty/Blank line count to commercelinecount else take the
     * calculated value.This is required in case if user adds blank lines in the cart and we need to clear 
	 * the background image in the textboxes having values for the commerce and quote lines custom fields
	 * textboxes. 
	 */
	
	var totalblanklinescount = $('emptylinesincart').value;
	if (totalblanklinescount == null )
	{   
        var totalblanklinescount = commercelinecount;
		var initialblanklinecount = commercelinecount;
	} 
	else
	{
	    var initialblanklinecount = commercelinecount - totalblanklinescount ;
	}

    /* Initialize the textbox index positions txtboxindexi and txtboxindexj for the custom field textbox id's 
	 * in the shopping cart for commerce and quoted items.For ex: "carthelptext11" so in a loop we create unique
	 * textbox id's for all the custom field textboxes. txtboxindexj can take maximum value of 2.This creates 
	 * textbox id sequence like "carthelptxt(txtboxindexi)(txtboxindexj)" where (txtboxindexi)(txtboxindexj)=11, 12, 21,
	 * 22, 31,32 so on for both commerce and quoted lines in the cart.
	 */
	var txtboxindexi = 1;
	var txtboxindexj = 1;

    /* for the total number of lines in the cart iterate through the textboxid sequence
	 * first index position txtboxindexi.
	 */
	
	for(txtboxindexi = 1; txtboxindexi <= totallinecount; txtboxindexi++) 
	{
	   var  txtboxindexj = 1;

        /* skip the empty blank lines in commerce items cart lines and continue iterating over the remaining textboxid's
		 * in the loop sequence
		 */
        
		if (initialblanklinecount>0 && txtboxindexi == initialblanklinecount)
		{   
			txtboxindexi = txtboxindexi + parseInt(totalblanklinescount);
			continue;
		}

		/* if there are two custom field textbox id's in a single commerce or quote lines 
		 * using the index postion txtboxindexj to create unique textboxid's.
		 */
		for (txtboxindexj = 1; txtboxindexj<3; txtboxindexj++)
		{
			
			/* if there is only one customfield textbox for a commerce or quote line then
             * continue iterating over the remaining textboxid's in the loop sequence
			 * and clear the background image if textbox object exists and has some value.
			 */
            elem = $("carthelptxt"+ txtboxindexi + txtboxindexj);
			if (elem && elem.value != "")
			{
			  clearBackground("carthelptxt"+ txtboxindexi + txtboxindexj);
			}
		}
	}
}

/*  This function is called during onload of shipping and billing pages and clears the background 
 *  image if there is a value in the custom fields textboxes.The textboxid will be of the form 
 *	helptxt(txtboxindexi) :where txtboxindexi=0,1,2 till there are custom field textboxid's exist
 *  in the page.At a maximum there can only be 2 customfield textboxids in the shipping and
 *  billing page so checking condition txtboxindexi<3 in the while loop.Also call the 
 *  clearBackground function only if such a textbox object exists in the page.
 */

function shipcFieldsetup() 
{
	var txtboxindexi=0;
	condition = $("helptxt"+txtboxindexi);
	while (condition!=null && txtboxindexi<3)
	{
		elem = $("helptxt"+txtboxindexi);
        if (elem && elem.value != "") 
		{
			clearBackground("helptxt"+txtboxindexi);
		}
		txtboxindexi++;
	}
}

/*-----------------------------------------------------------------------------------------------*/

/* 
 * To display the collect account number in the "Bill My Shipping Account" textbox.
 * The value in "Bill My Shipping Account" will update whenever the delivery methods dropdown value changes.
 */

var carrierAccountNumber = new Array();

function setCarrierAccountNumber(count,data) 
{	
	carrierAccountNumber[count-1] = data;	
}

function updateCarrierAccountNumber()
{		
	var shippingMethodIndex = $('shippingMethodsSelect').selectedIndex;	
	var carrierfield = $('carrierAccountNumber');
	var carrierAccount = carrierAccountNumber[shippingMethodIndex];
	if(carrierfield != null)
	{
		if (carrierAccount != null )
		{
			carrierfield.value = carrierAccountNumber[shippingMethodIndex];
			$('cacctlabel').style.display='block';
			$('cacctfield').style.display='block';
		}
		else
		{
			carrierfield.value = "";
			$('cacctlabel').style.display='none';
			$('cacctfield').style.display='none';
		}
	}
}

/*
 * This function will display a standard window dialogue to the user with
 * the passed in message as the message in the dialogue box. If the user
 * selects YES then it will set the attachExistingAccToOrg form field
 * value in accountInformation.jsp to true and submit the form.
 */
function attachExistingAccToOrg(msg)
{
	var attachExistingAccToOrg = confirm(msg);
	if (attachExistingAccToOrg) 
	{
		$("attachExistingAccToOrg").value = true;
		$("account").submit();
	}
}

/**********************
 *
 * Parametric Search
 *
 */

var previousQuery = '';
var parametricsFormURL = '';
var searchQueryURL = '';
var applyButtonValue = '';
var staticFilterDiv = '';
var isAppliedFilters = false;
var dimensionIds = '';
var dimensionIdsSet = false;
var checkedParametricCheckboxes = new Object;
var lastClickedIndexes = {};
var originalQueryURL = '';
var originalModel;
var previousAjaxQuery = "";
var parametricsFilterPending = false;
var appliedFiltersHidden = false;
var selectFiltersAreaHidden = false;
var AUTO_APPLY_DEFAULT = false;
var AUTO_APPLY_COOKIE_NAME = "param_auto_apply";
var AUTO_APPLY_EXPIRE_DAYS = 365*5; // 5 years
var PARAMETRIC_AVAILABLE = "AVAILABLE";
var PARAMETRIC_APPLIED = "APPLIED";
var PARAMETRIC_NOTAVAILABLE = "NOTAVAILABLE";
var removedNValues = '';
var productCountValue = ''; 
var enableRecurrentAjaxCall=true;
var previousAJAXParametricCallNValues='';
var appliedFilterWithoutParametrics = false;
function parametrics_load() {
	parametrics_setSearchURL();
	parametrics_initExpandCollapse();
	parametrics_initAutoApply();
	parametrics_settimeout();
	parametrics_setParametricsFormURL();
	parametrics_retrieveDimensions();
	parametrics_addObservers();
	parametrics_resize();
}
function parametrics_appliedFilter_load(){
	parametrics_setSearchURL();
	parametrics_settimeout();
	parametrics_setParametricsFormURL();
	parametrics_retrieveDimensions();
}



// Handles shift clicking event handling before passing on to parametrics_clickAction
function parametrics_respondToClick(ev) {
	var t = Event.element(ev);
	if (t && t.nodeName == "DIV" && t.className != 'notavailable')
	{
		// Text clicked rather than checkbox
		t = t.down();
		// Need to manually check/uncheck the checkbox
		if (t.checked) {
			t.checked = false;
			t.defaultChecked = false;
		}
		else
		{
			t.checked = true;
			t.defaultChecked = true;
		}
	}
	if (t && t.type=="checkbox") // if this target is a checkbox
	{     
		var h = t.up(1);                // find its holder
		var es = h.childElements();     // get the child elements of that holder
		es = es.collect(function(elm) {
			return elm.childElements();
		}).flatten();
		var e = es.indexOf(t);          // find where the target exists in those elements
		var lastClicked = lastClickedIndexes[h.id];
		if (ev.shiftKey && lastClicked != null)
		{
			// Find lowest and highest
			var start = e < lastClicked ? e : lastClicked;
			var end = e > lastClicked ? e : lastClicked;
			for (var i = start; i <= end; i++)
			{
				if (es[i].type=='checkbox' && es[i].checked != t.checked && es[i].up().className != 'notavailable')
				{
					es[i].up().toggleClassName('selectedRefinement');
					parametrics_clickAction(es[i], t.checked);
				}
			}
		}
		t.up().toggleClassName('selectedRefinement');
		parametrics_clickAction(t, t.checked);
		lastClickedIndexes[h.id] = e; // update value of last clicked index
		//Check whether the parametrics dropdown feature is available/not
		var parametricFeaturesAvailable = $('parametricFeaturesAvailable').value;
		if(parametricFeaturesAvailable!=null && parametricFeaturesAvailable=="true")
		{
			var chkGrpName = t.className;
			var dimensionId = chkGrpName.substring(1);
			parametrics_setMinForDimension(dimensionId,null);
			parametrics_setMaxForDimension(dimensionId,null);
			var minDropdown =  $('min'+dimensionId);
			var maxDropdown = $('max'+dimensionId);
			parametrics_enableMinMaxOptions( minDropdown, maxDropdown, true );
		}
		if (PARAMETRICS_AJAX) {
			parametrics_retrieveDimensions();
		}
	}
}

// Resizes parametrics area to get round CSS bug in different browsers
function parametrics_resize() {
	try {
		var searchBrowsabilityDisabled = $('searchBrowsabilityEnabled') == null;
		var newPageDesignOffset = 300;
		if(searchBrowsabilityDisabled){
			newPageDesignOffset = 0;
		}
		var viewPortWidth = document.viewport.getWidth()-50-newPageDesignOffset;
		$('parametricsForm').style.width=viewPortWidth + 'px';
		$('parametricFilters').style.width=viewPortWidth + 'px';
	} catch(error) {}
}

function parametrics_settimeout() {
	// Register global responders that will occur on all AJAX requests
	Ajax.Responders.register({
	onCreate: function(request) {
	request['timeoutId'] = window.setTimeout(
		function() {
			// If we have hit the timeout and the AJAX request is active, abort it and let the user know
			if (request.url.match(/parametrics.form/) && callInProgress(request.transport)) {
				request.transport.abort();
				parametrics_fail();
				// Run the onFailure method if we set one up when creating the AJAX object
				if (request.options['onFailure']) {
					request.options['onFailure'](request.transport, request.json);
				}
			}
		},
		60000 // 60 seconds
		);
		},
		onComplete: function(request) {
			// Clear the timeout, the request completed ok
			window.clearTimeout(request['timeoutId']);
		}
	});

}

function parametrics_setSearchURL() {
	//$('productGroupDimensionIdContainer') will be available for product group detail page only
	if($('productGroupDimensionIdContainer'))
	{
		searchQueryURL = '/jsp/search/productGroupDetails.jsp?' + $F('requestParamStr');
	}
	else
	{
		searchQueryURL = '/jsp/search/browse.jsp?' + $F('requestParamStr');
	}	
	removeURLParameter(searchQueryURL, '_requestid'); // Added by ATG sendLocalRedirect after product compare. Not wanted.
	
	parametrics_updateSearchString('replace','0','No'); // Ensure that we will go to the first page of search results when we next Apply
	parametrics_updateSearchString('replace','true','getResults'); // Used by ATG when Apply/Show button clicked
	parametrics_updateSearchString('replace','true','appliedparametrics'); // Used by numberofresults.jsp to display correct message
	
	var displayLocaleInput = $('parametrics_displayLocale');
	if (displayLocaleInput) {
		parametrics_updateSearchString('replace', displayLocaleInput.value, 'locale'); // Required by JBOSS
	}

	var divisionLocaleInput = $('parametrics_divisionLocale');
	if (divisionLocaleInput) {
		parametrics_updateSearchString('replace', divisionLocaleInput.value, 'divisionLocale'); // Required by JBOSS
	}
	
	var catalogIdInput = $('catalogId');
	if (catalogIdInput) {
		parametrics_updateSearchString('replace', catalogIdInput.value, 'catalogId'); // Required by JBOSS
	}

	var skipManufacturer = $('skipManufacturer');
	if (skipManufacturer) {
		parametrics_updateSearchString('replace', skipManufacturer.value, 'skipManufacturer'); // Required by JBOSS
	}

	var skipParametricAttributeId = $('skipParametricAttributeId');
	if (skipParametricAttributeId) {
		parametrics_updateSearchString('replace', skipParametricAttributeId.value, 'skipParametricAttributeId'); // Required by JBOSS to hide a paramateric attribute
	}

	if (previousQuery == '') {
		// If we have results, this is our latest good query
		previousQuery = getURLParameter(searchQueryURL, 'N');
	}
	parametrics_updateSearchString('replace', previousQuery, 'prevNValues');
	
	// Set original query URL, for use when user selects Clear Selected
	if (searchQueryURL.indexOf('originalQueryURL') >= 0) {
		originalQueryURL = decodeURIComponent(getURLParameter(searchQueryURL, 'originalQueryURL'));
		searchQueryURL = parametrics_removeOriginalQueryURL(searchQueryURL);
	}
	if (originalQueryURL == null || originalQueryURL == '') {
		originalQueryURL = searchQueryURL;
	}
}

function parametrics_setParametricsFormURL() {
    parametricsFormURL = '/pffind/Spring/parametrics.form';
}

function parametrics_addObservers() {
	$("autoApplyCheckBox").observe('click', parametrics_toggleAutoApply);
	$('applyButtonTop').observe('click', parametrics_doFilteredSearch);
	if($('staticFilterButton'))
	{
		$('staticFilterButton').observe('click', staticFiltersParametricsApply);
	}
	$('applyButtonBottom').observe('click', parametrics_doFilteredSearch);
	$('clearSelectionsButtonTop').observe('click', parametrics_clearSelections);
	$('clearSelectionsButtonBottom').observe('click', parametrics_clearSelections);
	Event.observe(document.onresize ? document : window, "resize", function() {
		parametrics_resize();
	});
	if ($('parametricFilters')) {
		Event.observe('parametricFilters', 'click', parametrics_respondToClick);
	}
}

function parametrics_retrieveDimensions() {
    var debug = false;
    if (debug)
    {
	    console.log('originalQueryURL=' + originalQueryURL);
	    console.log('------------------------');
	    console.log('searchQueryURL=  ' + searchQueryURL);
	    console.log('=========================');
    }
	if($('appliedFilterWithoutParametrics')!= null)
	{
		appliedFilterWithoutParametrics=$('appliedFilterWithoutParametrics').value;
	}    
    if (!originalModel && searchQueryURL != originalQueryURL)
    {
	    // This must be after a page reload, so we need to get the original refinements again.
	    // The response from this should be cached in the browser(and/or proxy) (as long as we haven't modified originalQueryURL)
	    if (debug) {
		    console.log('Retrieving original refinements passing original query ' + originalQueryURL);
	    }
	    parametrics_makeAjaxCall(originalQueryURL, 'parametrics_setOriginalModelAndMakeNewAjaxCall');
    } else {
	    // Retrieve new set of refinements
	    parametrics_makeAjaxCall(searchQueryURL, 'parametrics_render');
    }
}

function parametrics_makeAjaxCall(query, callBack) {
    var debug = false;
    var requestMethod = 'get'; // Prefer this, to allow browser/proxy caching
	previousAJAXParametricCallNValues = getURLParameter(query, 'N');
    //F0184822 - Special character not getting url-encoded correctly
    var queryHash = toQueryParamsHash(query);
    
    if((parametricsFormURL+'?'+queryHash.toQueryString()).length > 2000) {
			requestMethod = 'post';
    }
    previousAjaxQuery = query;
    new Ajax.Request(parametricsFormURL, {
        method: requestMethod,
        parameters: queryHash,
        encoding: 'UTF-8',
        onCreate:function() {
            parametrics_showProgressBox();
        },
        onSuccess: function(transport) {
            parametricsFilterPending = false;
            parametrics_hideProgressBox();
            var data = transport.responseText;
            if (debug) {
		    console.log(transport.responseText);
	    }
            eval(callBack + '(data)');
        },
        onFailure: function() {
		parametricsFilterPending = false;
		parametrics_fail();
        }
    });
}

function parametrics_setOriginalModelAndMakeNewAjaxCall(data) {
	var debug = false;
	if (debug) {
		console.log('parametrics_setOriginalModelAndMakeNewAjaxCall called with data:\n' + data);
	}
	originalModel = data.evalJSON(true);
	parametrics_makeAjaxCall(searchQueryURL, 'parametrics_render');
}

function parametrics_render(responseJSON) {
	var debug = false;
	var data = responseJSON.evalJSON(true);
	if (parametrics_checkParametricsReturned(data)) {
		var model;
		if (originalModel)
		{
			// Merge this new responseJSON with the original refinements
			model = parametrics_mergeModels(originalModel, data);
			if (debug) {
				console.log('Merged model=\n' + Object.toJSON(model));
			}
		} else
		{
			// This must be our "first visit" to the page, so set the originalModel to be this
			originalModel = data;
			model = originalModel;
		}
		if(appliedFilterWithoutParametrics)
		{
			var manufacturerDimensionId = null;
			if($('manufacturerDimensionId')!= null)
			{
				manufacturerDimensionId=$('manufacturerDimensionId').value;
			}
			var selectedDimensionsRow = parametric_select_dimensionRow(model.selectedDimensions,manufacturerDimensionId);
			parametrics_applyFilter(selectedDimensionsRow);
		}
		else
		{
			parametrics_renderModel(model);
		}
	}
}

function parametrics_checkParametricsReturned(data) {
	// OT40696 - It's possible to receive no parametrics in a response
	if (data.hasCategory==true && !(data.hasParametric && data.hasParametric==true)) {
		//F184822 - Exit the loop when redirecting is happening more than once.
		var exitLoopParam = getURLParameter(searchQueryURL, 'exitLoop');
		if (exitLoopParam && exitLoopParam == 'true') {
			parametrics_fail();
		}else{
			// Redirect to browse.jsp again and let that handle it
			parametrics_updateSearchString('replace','conditional','getResults'); // Probably don't want to display results
			parametrics_updateSearchString('replace','true','exitLoop');
			parametrics_doFilteredSearch();
		}
		return false;
	} else {
		return true;
	}
}

function parametrics_renderModel(model) {
	var debug = false;
	// OT37311 - removed setting visibility of parametrics form to visible
	$('parametricsForm').style.height = 'auto';

	var staticFilters = model.staticFilters;
	try {
		if($('staticFiltersContainer'))
		{
			$('staticFiltersContainer').innerHTML = '';
			var staticFilterDiv = parametrics_generateStaticFiltersHTML(staticFilters);
			if (staticFilterDiv) {
				var paraTag = document.createElement('p');
				paraTag.appendChild(staticFilterDiv);
				$('staticFiltersContainer').appendChild(paraTag);
			}
		}
	} catch (error) {
		console.log("parametrics_generateStaticFiltersHTML " + error);
	}

	var scrollOffsets = parametrics_collectScrollOffsets();
	var availableDimensions = model.dimensions;
	var manufacturerDimensionId = null;
	if($('manufacturerDimensionId')!= null)
	{
		manufacturerDimensionId=$('manufacturerDimensionId').value;
	}
	var searchBrowsabilityDisabled = $('searchBrowsabilityEnabled') == null;
	try {
		parametrics_generateDimsAvailHTML(availableDimensions,manufacturerDimensionId);
	} catch (error) {
		if (debug) {
			console.log("parametrics_generateDimsAvailHTML " + error);
		}
	}
	parametrics_setScrollOffsets(availableDimensions, scrollOffsets);	
	var selectedDimensions = model.selectedDimensions;
	var selectedDimensionsRow = parametric_select_dimensionRow(selectedDimensions,manufacturerDimensionId);

	numberOfResults = model.totalNumberOfResults;
	try {
		parametrics_generateNumberOfRecordsHTML();
	} catch (error) {
		if (debug) {
			console.log(error);
		}
	}
	
	parametrics_showZeroResultsError();

	var	appliedFiltersContainer = $('appliedFilterContainer');
	var	currentAppliedFilters = $('appliedFilterInnerContainer');
	if (currentAppliedFilters) {
		appliedFiltersHidden = (!currentAppliedFilters.visible());
		appliedFiltersContainer.removeChild(currentAppliedFilters);
	}
	currentAppliedFilters = parametrics_applyFilter(selectedDimensionsRow);

	// Handle the collapsing of the different sections if needed
	// This is done here because IE6 can't cope with populating a hidden div, so we leave them
	// unhidden until we're finished.
	if (selectFiltersAreaHidden) {
		var selectFiltersArrow = $('selectFiltersArrow');
		if (selectFiltersArrow) {
			selectFiltersArrow.title = expandText;
		}
		var selectFiltersArea = $('myParametricsForm');
		if (selectFiltersArea) {
			selectFiltersArea.hide();
		}
		var informaticText = $('informaticText');
		var parametricsFormAutoApply = $('parametricsFormAutoApply');
		if (informaticText && parametricsFormAutoApply) {
			informaticText.hide();
			parametricsFormAutoApply.hide();
		}
	}
	if (appliedFiltersHidden) {
		var appliedFiltersArrow = $('appliedFiltersArrow');
		if (appliedFiltersArrow) {
			appliedFiltersArrow.title = expandText;
		}
		var appliedFiltersInnerContainer = $('appliedFiltersInnerContainer');
		if (appliedFiltersInnerContainer) {
			appliedFiltersInnerContainer.hide();
		}
	}
	parametric_enabledDimensionSync(availableDimensions); 
}

function parametric_select_dimensionRow(selectedDimensions,manufacturerDimensionId){

	var selectedDimensionsRow;
	var searchBrowsabilityDisabled = $('searchBrowsabilityEnabled') == null;
	try 
	{
		// Table rows if searchBrowsabilit is switched off and inner divs else.
		if(searchBrowsabilityDisabled)
		{
			selectedDimensionsRow = parametrics_generateDimsSelectedHTMLTable(selectedDimensions);
		}
		else
		{
			selectedDimensionsRow = parametrics_generateDimsSelectedHTMLDiv(selectedDimensions,manufacturerDimensionId);
		}
	} 
	catch (error) 
	{
		if (debug) 
		{
			console.log("parametrics_generateDimsSelectedHTMLTable/parametrics_generateDimsSelectedHTMLDiv " + error);
		}
	}
	return selectedDimensionsRow;
}

function parametrics_applyFilter(selectedDimensionsRow){

	var searchBrowsabilityDisabled = $('searchBrowsabilityEnabled') == null;
	//var selectedDimensionsRow = parametric_select_dimensionRow(selectedDimensions);
	var currentAppliedFilters = null;
	
	// Table in container if searchBrowsabilit is switched off and div else.
	if(searchBrowsabilityDisabled)
	{
		appliedFiltersContainer = $('appliedFiltersContainer');
		currentAppliedFilters = $('appliedFiltersInnerContainer');
		if (isAppliedFilters && selectedDimensionsRow) 
		{
			var appliedFiltersInnerContainerDiv = document.createElement('div');
			appliedFiltersInnerContainerDiv.id = 'appliedFiltersInnerContainer';
			var appliedFiltersTable = document.createElement('table');
			var appliedFiltersTableBody = document.createElement('tbody');
			appliedFiltersTableBody.appendChild(selectedDimensionsRow);
			appliedFiltersTable.appendChild(appliedFiltersTableBody);
			appliedFiltersInnerContainerDiv.appendChild(appliedFiltersTable);
			appliedFiltersContainer.appendChild(appliedFiltersInnerContainerDiv);
		}
	}
	else
	{
		appliedFiltersContainer = $('appliedFilterContainer');
		currentAppliedFilters = $('appliedFilterInnerContainer');
		if (isAppliedFilters && selectedDimensionsRow) 
		{
			var appliedFiltersInnerContainerDiv = document.createElement('div');
			appliedFiltersInnerContainerDiv.id = 'appliedFilterInnerContainer';
			var appliedFiltersDiv = document.createElement('div');
			var appliedFiltersInnerDiv = document.createElement('div');
			appliedFiltersInnerDiv.appendChild(selectedDimensionsRow);
			appliedFiltersDiv.appendChild(appliedFiltersInnerDiv);
			appliedFiltersInnerContainerDiv.appendChild(appliedFiltersDiv);
			appliedFiltersContainer.appendChild(appliedFiltersInnerContainerDiv);
		}
	}
	return currentAppliedFilters;
}

//Method for make reccuring ajax call for the re enabled dimensions based on the min max dropdwon range 
function parametric_enabledDimensionSync(dimensions) 
{
	var parametricFeaturesAvailable = $('parametricFeaturesAvailable').value;
	
	if(parametricFeaturesAvailable  && enableRecurrentAjaxCall) //if clear button is pressed then we need to avoid reccuring ajax call
	{
		var doAjaxCall=false;
		dimensions.each(function (dimension) 
		{
			if (dimension) 
			{
				var maxValue=parametrics_getMaxForDimension(dimension.id);
				var minValue=parametrics_getMinForDimension(dimension.id);
				if(isEmpty(minValue)==false || isEmpty(maxValue)==false)//if a min max range is specified for the dimension the n only we need to do the check from enabled dimensions
				{
					if(doAjaxCall)
					{
						parametrics_resetCheckboxRanges(dimension.id,minValue,maxValue);
					}
					else
					{
						doAjaxCall=parametrics_resetCheckboxRanges(dimension.id,minValue,maxValue);
					}

				}
			}
		});
		
		if(doAjaxCall)
		{
			setTimeout(parametrics_retrieveDimensions,100);//to sequence multiple ajax request 
		}
		
	}
	enableRecurrentAjaxCall=true; //we need to reset this value
}

//Method for resetting the enabled check box if this dimensionID is  in the min max range and 
function parametrics_resetCheckboxRanges(dimensionID, minValue, maxValue)
{
	var doAjaxCall=false;
	var checkboxes=document.getElementsByClassName('d'+dimensionID);
	var minIndex=0;
	var maxIndex=checkboxes.length-1;
	if(isEmpty(minValue)==false)
	{
		minIndex=parametrics_findChkboxIndex(checkboxes,minValue,0);
	}
	if(isEmpty(maxValue)==false)
	{
		maxIndex=parametrics_findChkboxIndex(checkboxes,maxValue,checkboxes.length-1);
	}
	
	var maxIndex=parametrics_findChkboxIndex(checkboxes,maxValue,checkboxes.length-1);
	for(i=minIndex; i<=maxIndex; i++) 
	{
		checkBox=checkboxes[i];
		if(!checkBox.disabled && !checkBox.checked)
		{
			parametrics_clickAction($('rcb'+checkBox.value), true);//add the current dim value to N 
			doAjaxCall=true;
		}
	}
	return doAjaxCall;
}

function parametrics_collectScrollOffsets() {
	var debug = false;
	var scrollOffsets = {};
	try {
		var dimensionBoxes = $$('div.dimsAvailCol');
		if (dimensionBoxes) {
			dimensionBoxes.each(function(dimensionBox) {
				scrollOffsets[dimensionBox.id] = dimensionBox.scrollTop;
				if (debug) {
					console.log('Offset of ' + dimensionBox.id + ' = ' + dimensionBox.scrollTop);
				}
			});
		}
	} catch (error) {
		if (debug) {
			console.log(error);
		}
	}
	return scrollOffsets;
}

function parametrics_setScrollOffsets(availableDimensions, scrollOffsets) {
	var debug = false;
	try {
		availableDimensions.each(function(dim) {
			var offset = scrollOffsets['d'+dim.id];
			if (offset && offset > 0) {
				var dimBox = $('d'+dim.id);
				if (dimBox) {
					dimBox.scrollTop = offset;
				}
			}
		});
	} catch (error) {
		if (debug) {
			console.log(error);
		}
	}
	return scrollOffsets;
}

// Expands or collapses the applied filters area and changes the "twisty" icon
// The rendered parameter ensures that the div isn't hidden before it has been populated with the
// parametrics - IE6 can't cope with that. The final hiding is done at the end of parametrics_renderModel
function parametrics_toggleAppliedFilters(rendered){
	var appliedFiltersArrow = $('appliedFiltersArrow');
	if (appliedFiltersArrow) {
		appliedFiltersArrow.toggleClassName('appliedFiltersArrowUp');
	}
	if (appliedFiltersHidden==true) {
		if (rendered) {
			appliedFiltersHidden=false;
		}
		var appliedFiltersArrow = $('appliedFiltersArrow');
		if (appliedFiltersArrow) {
			appliedFiltersArrow.title = collapseText;
		}
	} else {
		if (rendered) {
			appliedFiltersHidden=true;
		}
		var appliedFiltersArrow = $('appliedFiltersArrow');
		if (appliedFiltersArrow) {
			appliedFiltersArrow.title = expandText;
		}
	}
	if (rendered && $('appliedFiltersInnerContainer')) {
		Effect.toggle('appliedFiltersInnerContainer', 'blind', { duration: 0.8 });
	}
}

// Expands or collapses the parametrics area and changes the "twisty" icon
// The rendered parameter ensures that the div isn't hidden before it has been populated with the
// parametrics - IE6 can't cope with that. The final hiding is done at the end of parametrics_renderModel
// Note that the ordering of the hiding and showing is deliberate to provide the smoothest effect.
function parametrics_toggleSelectFilters(rendered){
	var selectFiltersArrow = $('selectFiltersArrow');
	if (selectFiltersArrow) {
		selectFiltersArrow.toggleClassName('selectFiltersArrowUp');
	}
	if (selectFiltersAreaHidden==true) {
		if (rendered) {
			Effect.toggle('myParametricsForm', 'blind', { duration: 1 });
			selectFiltersAreaHidden=false;
		}
		var informaticText = $('informaticText');
		var parametricsFormAutoApply = $('parametricsFormAutoApply');
		if (rendered && informaticText && parametricsFormAutoApply) {
			informaticText.toggle();
			parametricsFormAutoApply.toggle();
		}
		if (selectFiltersArrow) {
			selectFiltersArrow.title = collapseText;
		}
	} else {
		var informaticText = $('informaticText');
		var parametricsFormAutoApply = $('parametricsFormAutoApply');
		if (rendered) {
			Effect.toggle('myParametricsForm', 'blind', { duration: 1 });
			selectFiltersAreaHidden=true;
			if (informaticText && parametricsFormAutoApply) {
				setTimeout("$('informaticText').toggle()", 1000);
				setTimeout("$('parametricsFormAutoApply').toggle()", 1000);
			}
		}
		if (selectFiltersArrow) {
			selectFiltersArrow.title = expandText;
		}
	}
}

function parametrics_fail() {
   parametrics_hideProgressBox();
     var parametricsForm = $('search-result-parametrics');
	if (parametricsForm) {
	    parametricsForm.hide();
	}
    $('parametricsFilterError').show();
}

//TODO Improve this method to use classes rather than styles
function parametrics_showProgressBox() {
	var pb = document.createElement('div');
	Element.extend(pb);
	pb.id = 'progressBox';
	pb.innerHTML = '<img src = \"/images/roller.gif\" class = \"progressIcon\">';
	pb.style.width = '32px';
	pb.style.height = '32px';
	pb.style.position = 'absolute';
	pb.style.zIndex = '5001';
	if(appliedFilterWithoutParametrics)
	{
		pb.style.top = '1%';
		pb.style.left = '5%';
		$('appliedFilterContainer').appendChild(pb);
	}
	else
	{
		pb.style.top = '50%';
		pb.style.left = '50%';
		document.body.appendChild(pb);		
	}
}

function parametrics_hideProgressBox() {
	var progressBox = $('progressBox');    
	if (progressBox) {
		progressBox.remove();
	}
}

function parametrics_showZeroResultsError()
{
	var el = $('zeroResultsErrorMessage');
	if (numberOfResults == 0)
	{
		el.show();
	} else
	{
		el.hide();
	}
}

function parametrics_doFilteredSearch(event) {
	var debug = false;
	if (!microCartPending) {
		parametricsFilterPending = true;
		searchQueryURL = searchQueryURL.replace("N=&", "N=0&");
		searchQueryURL = setURLParameter(searchQueryURL, 'filtersHidden', ''+selectFiltersAreaHidden);
		searchQueryURL = setURLParameter(searchQueryURL, 'appliedHidden', ''+appliedFiltersHidden);
		searchQueryURL = setURLParameter(searchQueryURL, 'autoApply', ''+$("autoApplyCheckBox").checked);
		var showResultsURL = searchQueryURL + "&originalQueryURL=" + encodeURIComponent(originalQueryURL);
		// F0253409 - Check for url over 2000 characters ,USE Post method
		if (showResultsURL.length > 2000) {
			var parametricsForm = $('myParametricsForm');
			if (parametricsForm) {
				// POST the form 
				if($('productGroupDimensionIdContainer'))
				{
					parametricsForm.action = '/jsp/search/productGroupDetails.jsp';
				}
				else
				{
					parametricsForm.action = '/jsp/search/browse.jsp';
				}
				parametricsForm.method = 'POST';
				$('longSearchQuery').value = showResultsURL;
				parametricsForm.submit();
			} else {
				if (debug) {
					console.log('No form "myParametricsForm"');
				}
			}
		} else {
			// GET
			window.location.href = showResultsURL;			
		}
 	}
}

//F0266548 - User getting a 403 Error when trying to sort by extended attributes
function doPostUrl(obj)
{
	var debug = false;
	if (!microCartPending) {
		var originalURL = obj;
		var actionURL = originalURL.substring(0,originalURL.indexOf('?'));
		var showResultsURL = originalURL.substring(originalURL.indexOf('?')+1) + "&originalQueryURL=" + encodeURIComponent(originalQueryURL);
		if (showResultsURL.length > 2000) {
			var sortAttributeForm = $('myForm');
			if (sortAttributeForm) {
				// POST the form 
				sortAttributeForm.action = actionURL;
				sortAttributeForm.method = 'POST';
				$('longQuery').value = showResultsURL;
				sortAttributeForm.submit();
			} 
			else {
				if (debug) {
					console.log('No form "myForm"');
				}
			}
		} 
		else {
			// GET
			window.location.href = obj + "&originalQueryURL=" + encodeURIComponent(originalQueryURL);
		}
 	}
}

function parametrics_initExpandCollapse() {
	var appliedHiddenParam = getURLParameter(searchQueryURL, 'appliedHidden');
	if (appliedHiddenParam && appliedHiddenParam == 'true') {
		appliedFiltersHidden = true;
		parametrics_toggleAppliedFilters(false);
	}
	var filtersHiddenParam = getURLParameter(searchQueryURL, 'filtersHidden');
	if (filtersHiddenParam && filtersHiddenParam == 'true') {
		selectFiltersAreaHidden = true;
		parametrics_toggleSelectFilters(false);
	}
}

/**
	Sets a cookies holding the default auto apply state, if it hasn't already been set
*/
function parametrics_initAutoApply()
{
	// access cookies directly to determine whether it's set (limitation of Cookies framework)
	if (!cookies.get(AUTO_APPLY_COOKIE_NAME))
	{
        if($("autoApplyCheckBox").checked)
        {
            parametrics_setAutoApply(true);
        }
        else
        {
	        parametrics_setAutoApply(AUTO_APPLY_DEFAULT);
        }
	}

	var checked = parametrics_getAutoApply(AUTO_APPLY_COOKIE_NAME);

	$("autoApplyCheckBox").checked = checked;
	PARAMETRICS_AJAX = checked;
}

/**
	Sets the auto apply cookie to the current state of the checkbox and and updates
*/
function parametrics_toggleAutoApply(event)
{
	var checked = Event.element(event).checked;
	
	/**
	  The following check is added on Auto Apply to control the display of the message 
	  displaying the number of products found based on the parameters selected. 

	 */
	if(checked)
	{
		$('dispNoOfProductFoundMsg').show();
	}
	else
	{
		$('dispNoOfProductFoundMsg').hide();
	}

	// Set cookie to persist state
	parametrics_setAutoApply(checked);
	
	// Set global variable
	PARAMETRICS_AJAX = checked;
	var searchBrowsabilityDisabled = $('searchBrowsabilityEnabled') == null;
	if(searchBrowsabilityDisabled)
	{
		hide_show_parametricDivClose(checked);
	}
	if (PARAMETRICS_AJAX) 
	{
		parametrics_retrieveDimensions();
	}
}

function hide_show_parametricDivClose(checked)
{

	if (checked)
	{
		$$('a.parametricDivClose').invoke('show');
		parametrics_generateNumberOfRecordsHTML();
		if (previousAjaxQuery != searchQueryURL) 
		{
			parametrics_retrieveDimensions();
		}
	} 
	else 
	{
		$$('a.parametricDivClose').invoke('hide');
		parametrics_generateNumberOfRecordsHTML();
	}
}

/*This function is used hide/show the close button
on the left side of selected filters list in the left panel */
function hide_show_newParametricDivClose(checked)
{

	if (checked)
	{
		$$('a.newParametricDivClose').invoke('show');
		parametrics_generateNumberOfRecordsHTML();
		if (previousAjaxQuery != searchQueryURL) 
		{
			parametrics_retrieveDimensions();
		}
	} 
	else 
	{
		$$('a.newParametricDivClose').invoke('hide');
		parametrics_generateNumberOfRecordsHTML();
	}
}

function parametrics_setAutoApply(value)
{
	//cookies.clear(AUTO_APPLY_COOKIE_NAME);
	setBooleanCookie(
		AUTO_APPLY_COOKIE_NAME,
		value,
		AUTO_APPLY_EXPIRE_DAYS);
}

function parametrics_getAutoApply()
{
	return getBooleanCookie(AUTO_APPLY_COOKIE_NAME);
}

function parametrics_generateStaticFiltersHTML(staticFilters)
{
    var staticFilterDiv = document.createElement('div');
    staticFilterDiv.id = 'staticFilters';
    if (staticFilters && staticFilters.refinements.length > 0) {
        staticFilters.refinements.each(function (staticRefinement) {
            if (staticRefinement) {
                var staticFilterCheckbox = document.createElement('input');
                staticFilterCheckbox.id = 'static' + staticRefinement.id;
                staticFilterCheckbox.type= 'checkbox';
                staticFilterCheckbox.onclick= function(evt) {parametrics_clickRefinement(evt, this.checked);};
		staticFilterCheckbox.checked = (staticRefinement.status == PARAMETRIC_APPLIED);
		staticFilterCheckbox.defaultChecked = staticFilterCheckbox.checked;
		staticRefinementValues = staticRefinement.id.split('+');
		staticFilterCheckbox.setAttribute('value', staticRefinement.id);
                var spacer = document.createTextNode('   ');
		var name = document.createTextNode('');		
		//added to reverse the behaviour of the filter checkbox.creating hidden object with id eg:'toggleFilterChkBoxBehaviour422'
		if (staticRefinement.toggleFilterChkBoxBehaviour )
		{
			var staticFilterHidden = document.createElement('input');
                staticFilterHidden.id = 'toggleFilterChkBoxBehaviour' + staticRefinement.id;
                staticFilterHidden.type= 'hidden';
				staticFilterHidden.setAttribute('value', "true");
				staticFilterDiv.appendChild(staticFilterHidden);
			staticFilterCheckbox.checked = (staticRefinement.status != PARAMETRIC_APPLIED);
			staticFilterCheckbox.defaultChecked =staticFilterCheckbox.checked;
		}
		if (staticRefinement.helpurl ) {
			name = parametrics_createStaticFilterLink(staticRefinement,staticFilterCheckbox);
		}
		else {
			// we do this to prevent escaping by the browsers
			name = document.createElement('span');
			name.innerHTML=staticRefinement.name;
		}

                
		var searchBrowsabilityDisabled = $('searchBrowsabilityEnabled') == null;
		/*If the searchBrowsability is enabled then we will have to display
		the static filters one below the other*/
		if(searchBrowsabilityDisabled)
		{
			 staticFilterDiv.appendChild(spacer);
			 staticFilterDiv.id = 'staticFilters';
		}
		else
		{
			var breakElement = document.createElement('br');
			staticFilterDiv.appendChild(breakElement);
			staticFilterDiv.id = 'searhBrowseStaticFilters';
		}
		staticFilterDiv.appendChild(staticFilterCheckbox);
		staticFilterDiv.appendChild(name);
            }
        });
    }
    return staticFilterDiv;
}

function parametrics_checkCheckbox(checkBox) {
	checkBox.checked=true;
	checkBox.defaultChecked=true;
	parametrics_rememberSelection(checkBox);
}

function parametrics_uncheckCheckbox(checkBox) {
	checkBox.checked=false;
	checkBox.defaultChecked=false;
	parametrics_forgetSelection(checkBox);
}

function parametrics_rememberSelection(checkBox) {
	checkedParametricCheckboxes[checkBox.id] = checkBox;
}

function parametrics_forgetSelection(checkBox) {
	delete checkedParametricCheckboxes[checkBox.id];
}

function parametrics_uncheckAll() {
	for (var key in checkedParametricCheckboxes) {
		var checkedCheckBox = checkedParametricCheckboxes[key];
		if (checkedCheckBox && checkedCheckBox!=null) {
			parametrics_uncheckCheckbox(checkedCheckBox);
			parametrics_updateSearchStringNValue('removeValue', ''+checkedCheckBox.value);
		}
	}
}

function parametrics_createStaticFilterLink(staticRefinement,staticFilterCheckbox) {
	var span = document.createElement('span');
	var left= staticRefinement.left;
	var right = staticRefinement.right;
	if (!left){
		left="";
	}
	if (!right) {
		right="";
	}
	
	span.innerHTML= left + "<a href=" + staticRefinement.helpurl + ">" + staticRefinement.linktext + "</a>" + right;
	return span;
}

// function used to mark the  checkbox filter value, when user click on the the text next to checkbox.
function selectFilter(filterCheckbox){
	
	filterCheckbox.checked = !filterCheckbox.checked;
	
	parametrics_clickAction(filterCheckbox, filterCheckbox.checked);
}

function parametrics_generateDimsAvailHTML(dimensions,manufacturerDimensionId) {
	var debug = false;
	if (dimensions) {
		// Create the new DOM subtree for the dimsAvailTable
		var dimsAvailDiv = document.createElement('div');
		dimsAvailDiv.id = 'dimsAvailDiv';
		var dimsAvailTable = document.createElement('table');
		dimsAvailTable.id = 'dimsAvailTable';
		var dimsAvailTableBody = document.createElement('tbody');
		var dimsAvailRow = document.createElement('tr');
		dimsAvailRow.id = 'dimsAvailRow';
		dimsAvailTableBody.appendChild(dimsAvailRow);
		dimsAvailTable.appendChild(dimsAvailTableBody);
		dimsAvailDiv.appendChild(dimsAvailTable);
		var searchBrowsabilityEnabled = $('searchBrowsabilityEnabled') != null;
		// Populate the cells with the dimensions
		dimensions.each(function (dimension) {
			if (dimension) 
				{
					if(searchBrowsabilityEnabled && dimension.id==manufacturerDimensionId )
					{
						loadManufacturerFlyout(dimension.refinements);
					}
					else
					{
						var dimensionCell = parametrics_createDimensionCell(dimension);
						if (dimensionCell) 
						{
							var dimsAvailCol = document.createElement('td');
							dimsAvailCol.appendChild(dimensionCell);	
							dimsAvailRow.appendChild(dimsAvailCol);
						}
					}
				}
		});
		
		// Replace dimsAvailTable with new one
		var parametricFiltersDiv = $('parametricFilters');
		var currentDimsAvail = $('dimsAvailDiv');
		if (currentDimsAvail) {
			//selectFiltersAreaHidden = (!currentDimsAvail.visible());xxx
			parametricFiltersDiv.removeChild(currentDimsAvail);
		}
		parametricFiltersDiv.appendChild(dimsAvailDiv);
	}
}

function parametrics_generateRefinementsHTML(id, refinementsDiv, refinements) {
	var debug = false;
	if (!refinementsDiv) {
		refinementsDiv = document.createElement('div');
		refinementsDiv.className = 'dimsAvailCol';
		refinementsDiv.id = 'd' + id;
	}
	if (refinements) {
		refinements.each(function (refinement) {

			var refinementDiv = document.createElement('div');
			refinementDiv.id = 'r' + refinement.id;

			var refinementCheckbox = document.createElement('input');

			refinementCheckbox.type='checkbox';
			refinementCheckbox.id='rcb' + refinement.id;
			refinementCheckbox.setAttribute('value', refinement.id);
			refinementCheckbox.className='d'+id;

			if (refinement.status == PARAMETRIC_NOTAVAILABLE)
			{
				refinementDiv.className = "notavailable";
				refinementCheckbox.disabled = "yes";
			}

			refinementNameText = refinement.name;

			if (refinement.status == PARAMETRIC_APPLIED)
			{
				refinementCheckbox.checked = true;
				refinementCheckbox.defaultChecked = true;
				refinementDiv.className = "selectedRefinement"; 
			} else
			{
				refinementNameText += ' (' + refinement.recordCount + ')';
			}
			refinementName = document.createTextNode(entityToText(refinementNameText));

			refinementDiv.appendChild(refinementCheckbox);
			refinementDiv.appendChild(refinementName);
			refinementsDiv.appendChild(refinementDiv);

		});
	}
	return refinementsDiv;
}

//New function to create the HTML for the dropdowns
//Returns DOM Element representing the dropdown HTML
function parametrics_generateMinMaxDropdownsHTML(dimensionID, refinements) 
{
		var debug = false;
		var minMaxDropdownDiv = document.createElement('div');
		minMaxDropdownDiv.className = "dimsMinMaxContainer"; 
		if (refinements) 
		{
			var minDropdownContainer = document.createElement('div');
			minDropdownContainer.className = "dimsMinMaxDropdownContainer"; 
			var maxDropdownContainer = document.createElement('div');
			maxDropdownContainer.className = "dimsMinMaxDropdownContainer"; 
			var minSelector = document.createElement('select');
			minSelector.id = 'min'+dimensionID;
			minSelector.name = 'min'+dimensionID;
			minSelector.className = "dimsMinMaxDropDown"; 
			minSelector.onchange= function(evt) {parametrics_respondToMinDropdown(evt, this);};
			var maxSelector = document.createElement('select');
			maxSelector.id = 'max'+dimensionID;
			maxSelector.name = 'max'+dimensionID;
			maxSelector.className = "dimsMinMaxDropDown";
			maxSelector.onchange= function(evt) {parametrics_respondToMaxDropdown(evt, this);};
			var minOptions = document.createElement('option');
			var maxOptions = document.createElement('option');
			minOptions.value = "";
			maxOptions.value = "";
			minOptions.appendChild(document.createTextNode($('selectMinText').value));
			maxOptions.appendChild(document.createTextNode($('selectMaxText').value));
			minSelector.appendChild(minOptions);
			maxSelector.appendChild(maxOptions);
			var selectedMinValue = parametrics_getMinForDimension(dimensionID);
			var selectedMaxValue = parametrics_getMaxForDimension(dimensionID);
			var index=0;
			var minIndex=0;
			var maxIndex=-1;
			refinements.each(function (refinement) 
			{
				minOptions = document.createElement('option');
				maxOptions = document.createElement('option');
				minOptions.value = refinement.id;
				maxOptions.value = refinement.id;
				minOptions.appendChild(document.createTextNode(entityToText(refinement.name)));
				maxOptions.appendChild(document.createTextNode(entityToText(refinement.name)));
				if(selectedMinValue!="" && selectedMinValue==refinement.id )
				{
					minOptions.selected="selected";
					minIndex=index;
				}
				if(selectedMaxValue!="" && selectedMaxValue==refinement.id )
				{
					maxOptions.selected="selected";
					maxIndex=index;
				}
				minSelector.appendChild(minOptions);
				maxSelector.appendChild(maxOptions);
				index++;
			});
				
			minDropdownContainer.appendChild(minSelector);
			maxDropdownContainer.appendChild(maxSelector);
			if(maxIndex == -1)
			{
				maxIndex = index-1;
			}
			parametrics_restrictMinMaxOptions(minSelector, (minIndex+1), maxSelector, (maxIndex+1));
	}
	minMaxDropdownDiv.appendChild(minDropdownContainer);
	minMaxDropdownDiv.appendChild(maxDropdownContainer);
	return minMaxDropdownDiv;
}

function entityToText(specialText) {
	var d = document.createElement("div");
	d.innerHTML = specialText;
	return d.firstChild.nodeValue;
}

function parametrics_createDimensionCell(dimension) 
{
    if (dimension) 
	{
        var contentDiv = document.createElement('div');
        contentDiv.className = 'parametricFiltersContent';
		var title = document.createElement('h3');
        title.className = 'dimsAvailHead';
        title.innerHTML = dimension.name;
        var lineBreak = document.createElement('br');
        var refinementsDiv = parametrics_generateRefinementsHTML(dimension.id, null, dimension.refinements);
		contentDiv.appendChild(title);
        contentDiv.appendChild(lineBreak);
        contentDiv.appendChild(refinementsDiv);
		//To check whether the dropdown functionality is switch off/on
		var parametricFeaturesAvailable = $('parametricFeaturesAvailable').value;
		if(parametricFeaturesAvailable!=null && parametricFeaturesAvailable=="true")
		{
			//CALL parametrics_generateMinMaxDropdownsHTML() and add as a child of the ?parametricFiltersContent? Div.
			var minMaxRefinementsDiv = parametrics_generateMinMaxDropdownsHTML(dimension.id, dimension.refinements);
			contentDiv.appendChild(minMaxRefinementsDiv);
		}
        return contentDiv;
    }
}

function parametrics_clickRefinement(evt, check) {
	evt = (evt) ? evt:((event) ? event:null);
	var clickedCheckbox = Event.element(evt);
	parametrics_clickAction(clickedCheckbox, check);
	if (PARAMETRICS_AJAX) {
		parametrics_retrieveDimensions();
	}
}

function parametrics_clickAction(clickedCheckbox, check) 
{
	var chkId=clickedCheckbox.id
	var toggleFilter="toggleFilterChkBoxBehaviour"+chkId.replace('static',"");//to get the corresponding hidden toggleFilterChkBoxBehaviour element name 
	var toggleOrNot=check;//default value .To keep the normal behaviuor to other check box whether toggleFilterChkBoxBehaviour is not exits 
	
	if(document.getElementById(toggleFilter) && document.getElementById(toggleFilter).value)//if this hidden element is exists then only changing the bahaviour
	{
		//while checking on filter check box this filter is removed and while unchecking the checkbox this filter is applied
		toggleOrNot=!check;//added to reverse the behaviour of the filter checkbox
	}
	
	if (toggleOrNot) 
	{
		parametrics_selectRefinement(clickedCheckbox);
	}
	else 
	{
		
		parametrics_deselectRefinement(clickedCheckbox);
	}

	if(check)
	{
		parametrics_checkCheckbox(clickedCheckbox);
	}
	else
	{
		parametrics_uncheckCheckbox(clickedCheckbox);
	}
	
}


function parametrics_selectRefinement(selectedFilter) {
    nValue = selectedFilter.value;
    parametrics_updateSearchStringNValue('addValue', ''+nValue);
}

function parametrics_deselectRefinement(selectedFilter) {
    nValue = selectedFilter.value;
    parametrics_updateSearchStringNValue('removeValue', ''+nValue);
}

function parametrics_deselectAppliedRefinement(nValue,dimensionId) {
    var refinementCB = $('rcb' + nValue);
    if (refinementCB) {
	parametrics_uncheckCheckbox(refinementCB);
    }
    parametrics_updateSearchStringNValue('removeValue', ''+nValue);
    var parametricFeaturesAvailable = $('parametricFeaturesAvailable').value;
	if(parametricFeaturesAvailable!=null && parametricFeaturesAvailable=="true")
	{
		if (dimensionId) 
		{
			parametrics_setMinForDimension(dimensionId,null);
			parametrics_setMaxForDimension(dimensionId,null);
			var minDropdown =  $('min'+dimensionId);
			var maxDropdown = $('max'+dimensionId);
			parametrics_enableMinMaxOptions( minDropdown, maxDropdown, true );
		}
	}
    
    if (PARAMETRICS_AJAX) {
	    parametrics_retrieveDimensions();
    }
}

function parametrics_selectFilter(event) {
    var selectedFilter = Event.element(event);
    nValue = selectedFilter.value;
    parametrics_updateSearchStringNValue('addValue', nValue);
    parametrics_rememberSelection(selectedFilter);
    //OT37311 - Removed AJAX call parametrics_retrieveDimensions();
    if (PARAMETRICS_AJAX) {
	    parametrics_retrieveDimensions();
    }
}

function parametrics_deselectFilter(event) {
    var selectedFilter = Event.element(event);
    nValue = selectedFilter.value;
    parametrics_updateSearchStringNValue('removeValue', nValue);
    parametrics_forgetSelection(selectedFilter);
    //OT37311 - Removed AJAX call parametrics_retrieveDimensions();
    if (PARAMETRICS_AJAX) {
	    parametrics_retrieveDimensions();
    }
}

/*This function is used to populate the Applied Filters area in
the left panel of the browsability parametric page*/
function parametrics_generateDimsSelectedHTMLDiv(selectedDimensions,manufacturerDimensionId) 
{
	var appliedFiltersDiv=document.createElement('div');
	if (selectedDimensions && selectedDimensions.length > 0) 
	{
		selectedDimensions.each(function (dimension) 
		{
			/* 
				The below condition dimension.id!=manufacturerDimensionId is added inorder to remove the 
				selected manufacture getting displayed in the Applied Filters.
			*/

			if (dimension && dimension.id!=manufacturerDimensionId) 
			{
				var refinements = dimension.refinements;
				if (refinements) 
				{
					// Add to Applied Filters section
					var appliedFilterContainer = document.createElement('div');
					appliedFilterContainer.id = "appliedFilterDimension";
					var dimensionTitleHeader = document.createElement('div');
					dimensionTitleHeader.innerHTML=dimension.name + ": ";

					// I commented this out because ie6 doesn't like it? a text element with a class name
					//dimensionTitle.className = "appliedFilter";
					appliedFilterContainer.appendChild(dimensionTitleHeader);
					var refinementCount = 0;					
					refinements.each(function (refinement) 
					{
						if(refinementCount > 0)
						{
							var breakElement = document.createElement('br');
							appliedFilterContainer.appendChild(breakElement);
						}

						var refinementName = document.createTextNode(entityToText(refinement.name));	
						// The link to remove the filter would only be visible if the
						// user has selected to Auto Apply Parametrics
						var link = document.createElement('a');
						link.setAttribute('href', 'javascript: void(0)');
						link.onclick=function(evt) {parametricSelectionCloseEvent(refinement.id,dimension.id);};
						
						link.className = 'newParametricDivClose';
						link.innerHTML = '&nbsp;';
						appliedFilterContainer.appendChild(link);					
						appliedFilterContainer.appendChild(refinementName);						
						refinementCount++;
					});
					var appliedFilters = document.createElement('div');
					var appliedFilterCell = document.createElement('p');
					appliedFilterCell.appendChild(appliedFilterContainer);
					appliedFilters.appendChild(appliedFilterCell);
					appliedFiltersDiv.appendChild(appliedFilters);
					isAppliedFilters = true;

				}
			}
		});
	}
	return appliedFiltersDiv;
}
/*This function is called on clicking the close button of the selected filter displayed in left panel of the page*/
function parametricSelectionCloseEvent(refinementId,dimensionId)
{
	var isClosingSelectedProductGroup = false;
	var showResultsURL;
	//$('productGroupDimensionIdContainer') will be available for product group detail page only
	if($('productGroupDimensionIdContainer'))
	{
		var productGroupDimensionId = $('productGroupDimensionIdContainer').innerHTML;
		if(productGroupDimensionId == dimensionId)
		{
			//This function is called on the close event of selected product group from selected filters section
			originalQueryURL = modifyQueryURLForPrdGrpRemoval(originalQueryURL,refinementId,dimensionId);
			searchQueryURL = modifyQueryURLForPrdGrpRemoval(searchQueryURL,refinementId,dimensionId);
			isClosingSelectedProductGroup = true;
			showResultsURL = searchQueryURL + "&originalQueryURL=" + encodeURIComponent(originalQueryURL);
		}
	}

	if(isClosingSelectedProductGroup && showResultsURL)
	{
		window.location.href = showResultsURL;
	}
	else if (PARAMETRICS_AJAX == false) 
	{
	   removeParamFilter_NonAjax(refinementId,dimensionId);
	}
	else
	{
		parametrics_deselectAppliedRefinement(refinementId,dimensionId );
	}
}

/*This function will modify the passed in queryURL by removing the passed in 
refinementId and dimensionId. This is called on clicking the close button of
the selected product group in the product group detail page.*/
function modifyQueryURLForPrdGrpRemoval(queryURL,refinementId,dimensionId)
{
	var modifiedQueryURL = queryURL.replace("productGroupDetails.jsp","browse.jsp");

	//remove product group refinement id from N value
	var nValuesInQueryURL = getURLParameter(modifiedQueryURL, 'N');
	nValuesInQueryURL = nValuesInQueryURL.replace('+'+refinementId,'');
	modifiedQueryURL = setURLParameter(modifiedQueryURL, 'N', nValuesInQueryURL);

	//remove product group refinement id from prevNValues value
	var prevNValuesInQueryURL = getURLParameter(modifiedQueryURL, 'prevNValues');
	prevNValuesInQueryURL = prevNValuesInQueryURL.replace('+'+refinementId,'');
	modifiedQueryURL = setURLParameter(modifiedQueryURL, 'prevNValues', prevNValuesInQueryURL);

	//remove dimension id from skipParametricAttributeId
	modifiedQueryURL = modifiedQueryURL.replace(dimensionId,'');

	//Add selectedPg parameter
	modifiedQueryURL = modifiedQueryURL + "&selectedPg="+ dimensionId;
	return modifiedQueryURL;
}

function parametrics_generateDimsSelectedHTMLTable(selectedDimensions) {
	var appliedFilters;
	if (selectedDimensions && selectedDimensions.length > 0) {
		appliedFilters = document.createElement('tr');
		selectedDimensions.each(function (dimension) {
			if (dimension) {
				var refinements = dimension.refinements;
				if (refinements) {
					// Add to Applied Filters section
					var appliedFilterContainer = document.createElement('div');
					appliedFilterContainer.id = "appliedFiltersDimension";
					var dimensionTitleHeader = document.createElement('h3');
					dimensionTitleHeader.innerHTML=dimension.name;

					// I commented this out because ie6 doesn't like it? a text element with a class name
					//dimensionTitle.className = "appliedFilter";
					appliedFilterContainer.appendChild(dimensionTitleHeader);
					var lineBreak = document.createElement('br');
					appliedFilterContainer.appendChild(lineBreak);
					refinements.each(function (refinement) {
						var refinementNameHeader = document.createElement('h4');
						var refinementName = document.createTextNode(refinement.name);
						refinementNameHeader.appendChild(refinementName);
						appliedFilterContainer.appendChild(refinementNameHeader);
						// The link to remove the filter would only be visible if the
						// user has selected to Auto Apply Parametrics
						var link = document.createElement('a');
						link.setAttribute('href', 'javascript: void(0)');
						link.onclick=function(evt) {parametrics_deselectAppliedRefinement(refinement.id,dimension.id );};
						link.className = 'parametricDivClose';
						link.innerHTML = '&nbsp;';
						appliedFilterContainer.appendChild(link);
						if (PARAMETRICS_AJAX == false) {
							Element.hide(link);
						}
					});
					var appliedFilterCell = document.createElement('td');
					appliedFilterCell.appendChild(appliedFilterContainer);
					appliedFilters.appendChild(appliedFilterCell);

					isAppliedFilters = true;

				}
			}
		});
	}
	return appliedFilters;
}

function parametrics_updateSearchStringNValue(command, nValue) {
	parametrics_updateSearchString(command, nValue, "N");
}

function parametrics_updateSearchString(command, newValue, parameterName) {
	parametrics_updateSearchStringWithSeparator(command, newValue, parameterName, "+");
}

function parametrics_updateSearchStringWithSeparator(command, newValue, parameterName, multiValueSeparator) {
    // Get the current value
    currentNValue = getURLParameter(searchQueryURL, parameterName);
    if (command=='addValue' || command=='updateValue') {
        if (currentNValue != '') {
            // Replace the N Value
            newNValue = currentNValue + multiValueSeparator + newValue;
            searchQueryURL = searchQueryURL.replace(parameterName+'='+currentNValue, parameterName+'='+newNValue);
        } else {
            // Set the N Value
            searchQueryURL = setURLParameter(searchQueryURL, parameterName, newValue);
        }
    } else if (command=='replace') {
	    searchQueryURL = setURLParameter(searchQueryURL, parameterName, newValue);
    } else if (command=='remove') {
	    searchQueryURL = removeURLParameter(searchQueryURL, parameterName);
    } else if (command=='removeValue') {
    	removedNValues = removedNValues + multiValueSeparator + newValue;
        if (currentNValue != '') {
            if (currentNValue == newValue) {
                // If that was the only N value, change to N=
                searchQueryURL = setURLParameter(searchQueryURL, parameterName, '');
            } else {
                if (currentNValue.indexOf(newValue) == 0) {
                    // If the value to remove is the first one then remove it and the + after it
                    searchQueryURL = searchQueryURL.replace(newValue + multiValueSeparator, '');
                } else {
                    // If the value to remove isn't the first one, then remove it and the + before it
                    searchQueryURL = searchQueryURL.replace(multiValueSeparator + newValue, '');
                }
            }
        }
    }
}

function parametrics_removeOriginalQueryURL(queryUrl) {
	return removeURLParameter(queryUrl, 'originalQueryURL');
}

function parametrics_clearSelections() 
{
		/*Calculating the difference between the N values in the originalQueryURL and current searchQueryURL
		and that will be the list of N values to be removed*/
		var nValuesInSearchQueryURL = getURLParameter(searchQueryURL, 'N');
        var nValuesInOriginalQueryURL = getURLParameter(originalQueryURL, 'N');
		if(nValuesInSearchQueryURL != null && nValuesInOriginalQueryURL != null)
		{
			removedNValues = nValuesInSearchQueryURL.replace(nValuesInOriginalQueryURL+'+','');
		}

		searchQueryURL = originalQueryURL;
		enableRecurrentAjaxCall=false;
		parametrics_renderModel(originalModel);
		var searchBrowsabilityEnabled = $('searchBrowsabilityEnabled') != null;
		
		//Clear static filters
		if(searchBrowsabilityEnabled)
		{
	        try 
	        {
				var staticFltrChkboxes = $$('input.chkboxStaticFltr');
				if (staticFltrChkboxes) 
				{
					staticFltrChkboxes.each(function(staticFltrChkbox) 
					{
						staticFltrChkbox.checked = false;					
					});
				}
			} 
	        catch (error) 
	        {
				if (debug) 
			    {
					console.log(error);
				}
	        }
		}

		//Show category specific no of results message instead of results messages for filter selection
		if($('NoOfResultsWithFiltersSelected'))
		{
			$('NoOfResultsWithFiltersSelected').hide();
			$('NoOfResultsWithFiltersCleared').show();
		}

		//Below set of code is to remove the hidden filter selections from previousAJAXParametricCallNValues which is used in next query fired on selecting any other manufacturer
		var removeManuIds = $$('span.removeManuIds');
		if (removeManuIds) 
		{
			var removedManufacturer = '';
			removeManuIds.each(function(removeManuId) 
			{			
				removedManufacturer=removedManufacturer+'+'+removeManuId.innerHTML;			
			});
		}		
		document.refineForm.currentQuery.value = document.refineForm.currentQuery.value.replace(previousAJAXParametricCallNValues,"");
		document.refineForm.currentQuery.value = document.refineForm.currentQuery.value.replace(removedManufacturer,"");
		previousAJAXParametricCallNValues="";

		//Clear manufacturer selection display
		 $('selectedManuList').innerHTML="";

		//added for OT50549 : Drop down options not getting cleared after clicking on clear selected button
		//Check whether the parametrics dropdown feature is available/not
		var parametricFeaturesAvailable = $('parametricFeaturesAvailable').value;
		if(parametricFeaturesAvailable!=null && parametricFeaturesAvailable=="true")
		{
			var availableDimensions = originalModel.dimensions;
			availableDimensions.each(function (dimension) 
			{
				if (dimension) 
				{
					parametrics_setMinForDimension(dimension.id,null);
					parametrics_setMaxForDimension(dimension.id,null);
					var minDropdown =  $('min'+dimension.id);
					var maxDropdown = $('max'+dimension.id);
					parametrics_enableMinMaxOptions( minDropdown, maxDropdown, true );
				}
			});
		}
}

function parametrics_generateNumberOfRecordsHTML() {
	var searchBrowsabilityDisabled = $('searchBrowsabilityEnabled') == null;
	if(searchBrowsabilityDisabled){
		parametrics_fillNumberOfRecordsSlots();
	}
	else{
		parametrics_browsability_fillNumberOfRecordsSlots();
	}
	
	if (numberOfResults > 0 || !PARAMETRICS_AJAX) {
		$('applyButtonTop').disabled = false;
		$('applyButtonTop').style.color = '#000000';
		$('applyButtonBottom').disabled = false;
		$('applyButtonBottom').style.color = '#000000';
	} else {
		$('applyButtonTop').disabled = true;
		$('applyButtonTop').style.color = '#D0CDC2';
		$('applyButtonBottom').disabled = true; 
		$('applyButtonBottom').style.color = '#D0CDC2';
	}

}
/*This function populate the applyButtonTop with the no of results information whensearch browsability feature is disabled*/
function parametrics_fillNumberOfRecordsSlots() 
{	
	if (applyButtonValue == ''){		
		applyButtonValue = $('applyButtonTop').value	
	}	
	if (PARAMETRICS_AJAX){		
		$('applyButtonTop').value = applyButtonValue + ' (' + numberOfResults + ')';
		$('applyButtonBottom').value = applyButtonValue + ' (' + numberOfResults + ')';
	} 	
	else{		
		$('applyButtonTop').value = applyButtonValue;
		$('applyButtonBottom').value = applyButtonValue;	
	}
}

/*This function format number by adding the number separator after three digits*/
function addSeparator(nStr)
{
	//A four digit number is hardcoded in numberofresults.jsp to fetch the number formatting separator character.
	var numberFormat = $('numberFormat').value;
	var numberSeparator = numberFormat.charAt(1);
	var rgx = /(\d+)(\d{3})/;
	if(numberSeparator != null && numberSeparator != ''){
		while (rgx.test(nStr)) {
			nStr = nStr.replace(rgx, '$1' + numberSeparator + '$2');
		}
	}
	return nStr;
}

/*This function populate the different holders in parametric pagewith the no of results information when search browsability feature is enabled*/
function parametrics_browsability_fillNumberOfRecordsSlots() 
{
	var formattedNumberOfResults = addSeparator(numberOfResults);
	if (productCountValue == '') {		
	productCountValue = $('totalNoResultsSlotAtTop').innerHTML	
		}	
	if (PARAMETRICS_AJAX) {		
		$('productTabTotalNoResultsSlot').innerHTML = formattedNumberOfResults;
		$('totalNoResultsSlotAtTop').innerHTML = formattedNumberOfResults;
		//totalNoResultsSlotAtTop_AfterFiltersCleared is a hidden div to be displayed when a maufactuerer is selected and then clear slection is done.
		if($('totalNoResultsSlotAtTop_AfterFiltersCleared'))
		{
			$('totalNoResultsSlotAtTop_AfterFiltersCleared').innerHTML = formattedNumberOfResults;
		}
		$('parametricMsgTotalNoResults').innerHTML = formattedNumberOfResults;
		$('dispNoOfProductFoundMsg').show();
	} 
	else {		
		$('productTabTotalNoResultsSlot').innerHTML = productCountValue;		
		$('totalNoResultsSlotAtTop').innerHTML = productCountValue;		
		$('parametricMsgTotalNoResults').innerHTML = productCountValue;	
		$('dispNoOfProductFoundMsg').hide();
	}
}
// handles connection timeout 
function callInProgress (xmlhttp) {
	switch (xmlhttp.readyState) {
	case 1: case 2: case 3:
	return true;
	break;
	// Case 4 and 0
	default:
	return false;
	break;
}
}

/**
 * parametrics_mergeModels
 * Accepts two parametrics models (as might be generated by the parametrics_generateModel function)
 * and merges them based on the following assumptions and rules:
 * - The first argument is the original set of parametrics received by the user,
 *   which is cached by the Javascript (and maintained between page reloads).
 * - The second argument is the new model, which contains a subset of the original
 *   refinements and the refinements which have already been applied.
 * - The two are merged such that all original refinements exist in the merged model
 *   with a status of AVAILABLE, APPLIED, or NOTAVAILABLE
 * Returns an Object representation of the merged model.
*/
function parametrics_mergeModels(originalModel, newModel) {
	var debug = false;
	var timing = false;
	var start;
	if (timing) {
		start = new Date();
	}
	var mergedModel = parametrics_deepClone(originalModel);
	try {
		// Update total number of results
		mergedModel.totalNumberOfResults = newModel.totalNumberOfResults;

		var originalDimensions = mergedModel.dimensions;
		var returnedDimensions = newModel.dimensions;
		var originalDimensionPointer = 0;
		var returnedDimensionPointer = 0;
		var dimensionCount = 0; // Prevent endless loop
		var reachedEndOfOriginalDimensions = (originalDimensionPointer >= originalDimensions.length);
		var reachedEndOfReturnedDimensions = (returnedDimensionPointer >= returnedDimensions.length);
		
		if (debug) {
			console.log('Starting mergeModels');
		}
		
		while ((reachedEndOfOriginalDimensions==false || reachedEndOfReturnedDimensions==false) && dimensionCount<1000) {
			dimensionCount++;
			if (debug) {
				console.log('originalDimensionPointer=' + originalDimensionPointer + ', reached end=' + reachedEndOfOriginalDimensions);
				console.log('returnedDimensionPointer=' + returnedDimensionPointer + ', reached end=' + reachedEndOfReturnedDimensions);
			}
			var dimensionResolved = false;
			var originalDimension = (reachedEndOfOriginalDimensions==true ? null : originalDimensions[originalDimensionPointer]);
			var returnedDimension = (reachedEndOfReturnedDimensions==true ? null : returnedDimensions[returnedDimensionPointer]);
			
			if (debug) {
				console.log('originalDimension.id=' + (reachedEndOfOriginalDimensions==true ? 'null' : originalDimension.id));
				console.log('returnedDimension.id=' + (reachedEndOfReturnedDimensions==true ? 'null' : returnedDimension.id));
			}
			
			if (reachedEndOfOriginalDimensions==false && reachedEndOfReturnedDimensions==false && originalDimension.id == returnedDimension.id) {
				// Update the dimension's status
				originalDimension.status = returnedDimension.status;
				
				// Compare refinements
				if (debug) {
					console.log('Found dimension in new model. Comparing refinements...');
				}
				
				var originalRefinements = originalDimension.refinements;
				var returnedRefinements = returnedDimension.refinements;
				var originalRefinementPointer = 0;
				var returnedRefinementPointer = 0;
				var refinementCount = 0; // Prevent endless loop
				var reachedEndOfOriginalRefinements = (originalRefinementPointer >= originalRefinements.length);
				var reachedEndOfReturnedRefinements = (returnedRefinementPointer >= returnedRefinements.length);
				
				while (reachedEndOfOriginalRefinements==false || reachedEndOfReturnedRefinements==false && refinementCount<1000) {
					refinementCount++;
					if (debug) {
						console.log('originalRefinementPointer=' + originalRefinementPointer + ', reached end=' + reachedEndOfOriginalRefinements);
						console.log('returnedRefinementPointer=' + returnedRefinementPointer + ', reached end=' + reachedEndOfReturnedRefinements);
					}
					var refinementResolved = false;
					var originalRefinement = (reachedEndOfOriginalRefinements==true ? null : originalRefinements[originalRefinementPointer]);
					var returnedRefinement = (reachedEndOfReturnedRefinements==true ? null : returnedRefinements[returnedRefinementPointer]);
					if (debug) {
						console.log('originalRefinement.id=' + (reachedEndOfOriginalRefinements==true ? 'null' : originalRefinement.id));
						console.log('returnedRefinement.id=' + (reachedEndOfReturnedRefinements==true ? 'null' : returnedRefinement.id));
					}
					// Update the dimension's refinements
					if (reachedEndOfOriginalRefinements==false && reachedEndOfReturnedRefinements==false && returnedRefinement.id == originalRefinement.id) {
						if (debug) {
							console.log('Found refinement in new model');
						}
						originalRefinement.status = returnedRefinement.status;
						originalRefinement.recordCount = returnedRefinement.recordCount;
						originalRefinementPointer++;
						returnedRefinementPointer++;
						refinementResolved = true;
					}
					if (refinementResolved == false) {
						//1. Could be in selected
						if (reachedEndOfOriginalRefinements==false) {
							if (debug) {
								console.log('Not found refinement in new model. Looking in selectedDimensions for dimension ' + originalDimension.id);
							}
							var selectedDimension = newModel.selectedDimensions.find(function(d) { return d.id == originalDimension.id });
							if (selectedDimension) {
								if (debug) {
									console.log('Found selected dimension in new model. Looking for selected refinement ' + originalRefinement.id);
								}
								if (selectedDimension.refinements.find(function(r) { return r.id == originalRefinement.id })) {
									if (debug) {
										console.log('Found selected refinement id ' + originalRefinement.id + ' in new model. Setting to APPLIED');
									}
									originalRefinement.status = PARAMETRIC_APPLIED;
									delete originalRefinement.recordCount;
									parametrics_merge_AddToSelected(mergedModel, originalDimension, originalRefinement);
									originalRefinementPointer++;
									refinementResolved = true;
								}
							}
						}
						//2. Could be not available
						if ((refinementResolved == false && reachedEndOfReturnedRefinements==true) || (refinementResolved == false && reachedEndOfReturnedRefinements==false && $A(originalRefinements.slice(originalRefinementPointer)).find(function(r) {return r.id == returnedRefinement.id}))) {
							// Must be unavailable
							if (debug) {
								console.log('Did not find refinement in new model (but present in original model). Setting to NOTAVAILABLE');
							}
							originalRefinement.status = PARAMETRIC_NOTAVAILABLE;
							originalRefinementPointer++;
							refinementResolved = true;
						}
						
					}
					if (refinementResolved == false && reachedEndOfReturnedRefinements==false) {
						// 3. This is a new unknown refinement. Add to model.
						if (debug) {
							console.log('Did not find refinement ' + returnedRefinement.id + ' in original model. Adding to model');
						}
						insertIntoArray(originalRefinements, returnedRefinement, originalRefinementPointer); // Insert into merged model
						//TODO //insertIntoArray(originalModel.dimensions, returnedRefinement, originalRefinementPointer); // Store in original model
						originalRefinementPointer++; // We only move this on because we've put the new refinement above it
						returnedRefinementPointer++;
						refinementResolved = true;
					}
					reachedEndOfOriginalRefinements = (originalRefinementPointer >= originalRefinements.length);
					reachedEndOfReturnedRefinements = (returnedRefinementPointer >= returnedRefinements.length);
				}// end of while loop for refinements
				if (debug) {
					console.log('originalRefinementPointer=' + originalRefinementPointer + ', reached end=' + reachedEndOfOriginalRefinements);
					console.log('returnedRefinementPointer=' + returnedRefinementPointer + ', reached end=' + reachedEndOfReturnedRefinements);
				}
				originalDimensionPointer++;
				returnedDimensionPointer++;
				dimensionResolved = true;
			} 
			if (dimensionResolved == false) {
				if (reachedEndOfOriginalDimensions==false) {
					// 1. Could be in selected
					if (debug) {
						console.log('Dimension not found in new model. Checking selected dimensions');
					}
					var selectedDimension = newModel.selectedDimensions.find(function(d) { return d.id == originalDimension.id });
					if (selectedDimension) {
						if (debug) {
							console.log('Found selected dimension in selected dimensions. Comparing refinements');
						}
						originalDimension.refinements.each( function (originalRefinement) {
							var allSelected = true;
							if (selectedDimension.refinements.find(function(r) { return r.id == originalRefinement.id })) {
								// Found in selectedDimension.refinements
								originalRefinement.status = PARAMETRIC_APPLIED;
								delete originalRefinement.recordCount;
								parametrics_merge_AddToSelected(mergedModel, originalDimension, originalRefinement);
							} else
							{
								originalRefinement.status = PARAMETRIC_NOTAVAILABLE;
								allSelected = false;
								
							}
							if (allSelected == true) {
								originalDimension.status = PARAMETRIC_APPLIED;
							} else {
								originalDimension.status = PARAMETRIC_NOTAVAILABLE;
							}
						});
						originalDimensionPointer++;
						dimensionResolved = true;
					}
				}
				if ((dimensionResolved == false && reachedEndOfReturnedDimensions==true) || (dimensionResolved == false && reachedEndOfReturnedDimensions==false && $A(originalDimensions.slice(originalDimensionPointer)).find(function(d) {return d.id == returnedDimension.id}))) {
					// Found, so this isn't available
					if (debug) {
						console.log('NOT FOUND this dimension in selected dimensions (but present in original model). Setting to NOTAVAILABLE');
					}
					// Dimension no longer available
					originalDimension.status = PARAMETRIC_NOTAVAILABLE;
					// Update the dimension's refinements' statuses to NOTAVAILABLE
					originalDimension.refinements.each( function (originalRefinement) {
						originalRefinement.status = PARAMETRIC_NOTAVAILABLE;
					});
					originalDimensionPointer++;
					dimensionResolved = true;
				}
			}
			if (dimensionResolved == false) {
				// Not found, so this is an unknown dimension. Add it to the model.
				if (debug) {
					console.log('Dimension id ' + returnedDimension.id + ' NOT FOUND in original model. Must be new dimension. Adding to model.');
				}
				insertIntoArray(originalDimensions, returnedDimension, originalDimensionPointer); // Insert into merged model
				insertIntoArray(originalModel.dimensions, returnedDimension, originalDimensionPointer); // Store in original model
				originalDimensionPointer++; // We only adjust this pointer because we have just added something before it in the array
				returnedDimensionPointer++;
				dimensionResolved = true;
			};
			reachedEndOfOriginalDimensions = (originalDimensionPointer >= originalDimensions.length);
			reachedEndOfReturnedDimensions = (returnedDimensionPointer >= returnedDimensions.length);
		}//end of while loop for dimensions
		if (debug) {
			console.log('originalDimensionPointer=' + originalDimensionPointer + ', reached end=' + reachedEndOfOriginalDimensions);
			console.log('returnedDimensionPointer=' + returnedDimensionPointer + ', reached end=' + reachedEndOfReturnedDimensions);
		}
		
		// Update static filters
		mergedModel.staticFilters = newModel.staticFilters;

	} catch (error) {
		if (debug) {
			console.log(error);
		}
	}
	if (timing) {
		console.log('Merging of models took ' + ((new Date()) - start));
	}
	return mergedModel;
}

function parametrics_merge_AddToSelected(mergedModel, originalDimension, originalRefinement) {
	var debug = false;
	try
	{
		// Init if necessary
		if (!mergedModel.selectedDimensions)
		{
			mergedModel.selectedDimensions = [];
		}
		var dims = mergedModel.selectedDimensions;

		// Try to find an existing dimension in the list with the same id
		var dim = dims.find(function(d) { return d.id == originalDimension.id });

		if (!dim)
		{
			// Create new dimension
			dim = {};
			dim.id = originalDimension.id;
			dim.name = originalDimension.name;
			dim.refinements = [];

			dims.push(dim);
		}
		
		// Add the refinement
		var ref = {};
		ref.id = originalRefinement.id;
		ref.name = originalRefinement.name;
		ref.status = originalRefinement.status;
		dim.refinements.push(ref);
	}
	catch (error)
	{
		if (debug) {
			console.log(error);
		}
	}

}


function parametrics_deepClone(obj) {
	return (Object.toJSON(obj)).evalJSON();
}

/*This function is called on the onfocus event of searchwithinRange
text box in the parametric page left panel*/
function clearSearchText(id)
{
	elem = $(id);
	var searchwithinRangeText = $('searchwithinRangeText');
	if(elem.value == searchwithinRangeText.value)
	{
		elem.value='';
	}
	elem.style.color = "black";
	elem.style.fontWeight="normal";
}

/*This function is called on the onblur event of searchwithinRange
text box in the parametric page left panel*/
function refreshSearchText(id)
{
	elem = $(id);
	var searchwithinRangeText = $('searchwithinRangeText');
	if (typeof elem != 'undefined') {
		if (elem != null) {
			if ((elem.value == "") || (elem.value == searchwithinRangeText.value))
			{
				elem.style.color = "#D2D2C9";
				elem.style.fontWeight="bold";
				elem.value=searchwithinRangeText.value;
			}
		}
	}
}

//Declarare Hash Table for parametrics MinMax dropdowns
var parametricMinMaxHashTable;

//New function to parse the URL for Min and Max Values for dimensions and cache the results
function parametrics_initializeMinAndMaxValues() 
{
	parametricMinMaxHashTable = new Hash();
	//Populate the values from the url
	var mm =getURLParameter(searchQueryURL, 'mm');
	if(mm!=null)
	{
	var diamentions = mm.split(',');
		for ( var count = 0; count < diamentions.length; count++ )
		{
			minMaxValue = diamentions[count].split('|');
			var minMaxHashTable = new Hash();
			minMaxHashTable.set('min', minMaxValue[1]);
			minMaxHashTable.set('max', minMaxValue[2]);
			parametricMinMaxHashTable.set(minMaxValue[0],minMaxHashTable);
		}
	}
}

//Handle the select event of the min dropdown
function parametrics_respondToMinDropdown(evt,element)
{
	var dimId=parametrics_extractDimensionId(element.id)
	parametrics_setMinForDimension(dimId, element.value);	
	var maxValue=parametrics_getMaxForDimension(dimId);
	parametrics_applyMinMaxRange(dimId,element.value,maxValue);
}

//Handle the select event of the max dropdown
function parametrics_respondToMaxDropdown(evt,element)
{
	var dimId=parametrics_extractDimensionId(element.id)
	parametrics_setMaxForDimension(dimId, element.value);
	var minValue=parametrics_getMinForDimension(dimId);
	parametrics_applyMinMaxRange(dimId,minValue,element.value);
}

//Remove the first three letters from the elementId, which will be either 'min' or 'max'
function parametrics_extractDimensionId(elementId)
{
	return elementId.substring(3);
}

//Update the HashTable once the user selects the min value. Also updates the search URL.
function parametrics_setMinForDimension(dimensionID, minValue) 
{
	if(parametricMinMaxHashTable == null)
	{
		parametrics_initializeMinAndMaxValues();
	}
	
	var minMaxHashTable = parametricMinMaxHashTable.get(dimensionID);
	
	if(minMaxHashTable == null)
	{
		minMaxHashTable = new Hash();
	}	
	minMaxHashTable.set('min', minValue);
	
	parametricMinMaxHashTable.set(dimensionID, minMaxHashTable);
	
	//Set the url
	parametrics_updateSearchString('replace',parametrics_getMinMaxParamString(),'mm');
	
	if($('searchFiltersMMValues'))
	{
		$('searchFiltersMMValues').value = parametrics_getMinMaxParamString();
	}
}


//Update the HashTable once the user selects the max value. Also update the search URL.
function parametrics_setMaxForDimension(dimensionID, maxValue) 
{
	if(parametricMinMaxHashTable == null)
	{
		parametrics_initializeMinAndMaxValues();
	}
	var minMaxHashTable = parametricMinMaxHashTable.get(dimensionID);
	if(minMaxHashTable == null)
	{
		minMaxHashTable = new Hash();
	}
	
	minMaxHashTable.set('max', maxValue);
	parametricMinMaxHashTable.set(dimensionID, minMaxHashTable);

	//Set the url
	parametrics_updateSearchString('replace',parametrics_getMinMaxParamString(),'mm');
	if($('searchFiltersMMValues'))
	{
		$('searchFiltersMMValues').value = parametrics_getMinMaxParamString();
	}
}


//Generate the minMaxParam string form the HashTable
function parametrics_getMinMaxParamString()
{
	var newURL = "";
	parametricMinMaxHashTable.each(function(pair) 
	{
		var minMaxHashTable = parametricMinMaxHashTable.get(pair.key);
		if(minMaxHashTable != null && pair.key!="")
		{
			newURL = newURL + pair.key + '|';
			var minValue = minMaxHashTable.get('min');
			if(minValue != null)
			{
				newURL = newURL + minValue;
			}
			newURL = newURL + '|';
			
			var maxValue = minMaxHashTable.get('max');
			if(maxValue != null)
			{
				newURL = newURL + maxValue;
			}			
			newURL = newURL + ',';
		}
	});
	
	return newURL;
}

//Set the state of the dropdowns given any previously selected min and max values:
//Return the Min value for the dimension.
function parametrics_getMinForDimension(dimensionID)
{
	var minForDimension = null;
	if(parametricMinMaxHashTable == null)
	{
		parametrics_initializeMinAndMaxValues();
	}
	var minMaxForDimension = parametricMinMaxHashTable.get(dimensionID);
	if(minMaxForDimension != null && minMaxForDimension.get('min')!=null)
	{
		minForDimension = minMaxForDimension.get('min');
	}
	return minForDimension;
}

//Return the Max value for the dimension.
function parametrics_getMaxForDimension(dimensionID)
{
	var maxForDimension = null;
	if(parametricMinMaxHashTable == null)
	{
		parametrics_initializeMinAndMaxValues();
	}
	var minMaxForDimension = parametricMinMaxHashTable.get(dimensionID);
	if(minMaxForDimension != null && minMaxForDimension.get('max')!=null)
	{
		maxForDimension = minMaxForDimension.get('max');
	}
	return maxForDimension;
}

//return true if the value is empty or null
function isEmpty(value)
{
	var retValue=false;
	if(value=="" || value==null )
	{
		retValue=true;
	}
	return retValue;
}
//Toggle the checkboxes for a dimension given a min and max value
function parametrics_applyMinMaxRange(dimensionID, minValue, maxValue)
{
	var checkboxes=document.getElementsByClassName('d'+dimensionID);
	var minIndex=parametrics_findChkboxIndex(checkboxes,minValue,0);
	var maxIndex=parametrics_findChkboxIndex(checkboxes,maxValue,checkboxes.length-1);
	var minDropDownObject = $('min'+dimensionID);
	var maxDropDownObject = $('max'+dimensionID);
	if(isEmpty(minValue) && isEmpty(maxValue))
	{
		parametrics_setCheckboxRange( checkboxes, minIndex, maxIndex);
	}
	else
	{
		if(minIndex>0)
		{
			parametrics_setCheckboxRange( checkboxes, 0, minIndex-1);
		}
		parametrics_setCheckboxRange(checkboxes,minIndex,maxIndex,true);
		if( maxIndex < checkboxes.length-1)
		{
			parametrics_setCheckboxRange( checkboxes, maxIndex+1,checkboxes.length-1);
		}
	}
	parametrics_restrictMinMaxOptions(minDropDownObject, (minIndex+1), maxDropDownObject, (maxIndex+1));
	if (PARAMETRICS_AJAX) 
	{
		parametrics_retrieveDimensions();
	}
	
}

//Method for find the index of checkbox
function parametrics_findChkboxIndex(chkBoxes, minMaxValue, defaultValue)
{
	var chkboxIndex=defaultValue;
	if(minMaxValue!=null){
		for (i=0; i<chkBoxes.length; i++) 
		{
			if(chkBoxes[i].value==minMaxValue)
			{
				chkboxIndex=i;
				break;
			}
		}
	}
	return chkboxIndex;
}

// select checkboxes between min and max
function parametrics_setCheckboxRange(checkboxes, minIndex, maxIndex, checked)
{
	for(i=minIndex; i<=maxIndex; i++) 
	{
		var chekbox=checkboxes[i];

		if(chekbox.checked!=checked)
		{
			var parentDiv=chekbox.up();
			if(parentDiv.className != 'notavailable')
			{
				if(checked)
				{
					parentDiv.className='selectedRefinement';
				}
				else
				{
					parentDiv.className='';
				}
				parametrics_clickAction(chekbox, checked);
			}
			
		}
	}
}

//This function is used to disable options in Min-Max dropdowns
function parametrics_restrictMinMaxOptions(minDropdown, minIndex, maxDropdown, maxIndex)
{
	var maxDropdownOptions = maxDropdown.getElementsByTagName("option");
	var minDropdownOptions = minDropdown.getElementsByTagName("option");
	if(maxIndex>0)
	{
		// Disable options in the Max Dropdown that are <  value selected in Min Dropdown, Enable the rest
		for (var i=1;i<maxDropdownOptions.length;i++) 
		{
			var item=maxDropdownOptions[i];
			if(i < minIndex )
			{			  
				item.disabled=true;
			}
			else
			{
				item.disabled=false;
			}
		}
	}
	if(minIndex>0)
	{
		// Disable options in the Min Dropdown that are >  value selected in Max Dropdown, Enable the rest
		for (var i=1;i<minDropdownOptions.length;i++) 
		{
			var item=minDropdownOptions[i];
			if(i > maxIndex)
			{			  
				item.disabled=true;
			}
			else
			{
				item.disabled=false;
			}
		}
	}
}

//This function is used to enable all options in Min-Max dropdowns
function parametrics_enableMinMaxOptions(minDropdown, maxDropdown, enable)
{
	try
	{ 
	var maxDropdownOptions = maxDropdown.getElementsByTagName("option");
	var minDropdownOptions = minDropdown.getElementsByTagName("option");
	if(enable)
	{

		for (var i=0;i<minDropdownOptions.length;i++) 
		{
			var item=minDropdownOptions[i];
			if(i == 0)
			{
				item.selected="selected";
			}
			else
			{
			item.disabled=false;
			}
		}

		for (var i=0;i<maxDropdownOptions.length;i++) 
		{
			var item=maxDropdownOptions[i];
			if(i == 0)
			{
				item.selected="selected";
			}
			else
			{
			item.disabled=false;
			}
		}
	}
	}
	catch (ex)
	{
	}
}

/**********************
 *
 * End - Parametric Search
 *
 */

//LEADTIME AJAX
function toggleleadTimeInfo(element,sku,quantity)
{
	var moreLinkArrow = $('moreLinkArrow-'+sku);
	var ajaxIndicator = $('leadTimeAjaxRoller-'+sku);
	var leadTimeInfoContainer = $('leadTimeAjaxHolder-'+sku);
	if (leadTimeInfoContainer.innerHTML == ''){	
		var url = "/jsp/commonfragments/processLeadTimeInfo.jsp?productIds=" + sku+"&quantities="+quantity;	
		new Ajax.Request(url,
							{
								method:"post",
								onCreate: function()
								{	
									ajaxIndicator.show();
								}, 
								onComplete: function(request)
								{
									moreLinkArrow.className='moreLinkArrowDown';
	                           		leadTimeInfoContainer.innerHTML=request.responseText;
									ajaxIndicator.hide();
								}
							}
						);
	}
	else 
	{
		if(leadTimeInfoContainer.visible())
		{
			moreLinkArrow.className='moreLinkArrowUp';
		}
		else
		{
			moreLinkArrow.className='moreLinkArrowDown';
		}
		leadTimeInfoContainer.toggle();
	}
}

//END LEAD TIME AJAX

//Fix added to correct the leadtime hover over message display in product compare page
function hoverOverZindex(elementId,indexVal)
{
	if(indexVal <= 2)
	{
		$(elementId).style.zIndex=50;
	}
}

function hoverOutZindex(elementId)
{
	$(elementId).style.zIndex=10;
}

//PRJ50766 - Contact Me When InStock

/* Function to display Notification popup
 * Assumption: all the links with class="notificationLink" will have an Id defined.
 */
function displayNotificationLightbox(url, linkElement)
{	
	NotificationLightBox = Class.create(lightbox, {
		initialize: function() {},
	    showBusy: function() {
	    	 $("busyImage").show();
	    },
	    hidePopup: function() {
			$("busyImage").hide();
			this.deactivate();
	    },
		submitAjaxCall: function(url, parameters, lightboxObj) {
			/* OT49115:
			 * IE caches the 'get' method requests and the caching is done on the basis of url.
			 * Adding a dummy parameter, 'cacheDefeat' so that the url will be different each time
			 * and thus we can overcome caching of the Ajax request in IE.
			 * Though we have given "Pragma:no-cache" to 'requestHeaders', that didn't seem to work.
			 */
			var d=new Date();
			parameters += "&cacheDefeat=" + encodeURIComponent(d.getTime());

			//The request method should be 'get' as we need all the parameters in the url for proper redirection. 
			new Ajax.Request(
				url, {
				method:"get",
				requestHeaders: {Pragma: 'no-cache'},
				parameters: parameters,
				onCreate: this.showBusy,
				onComplete: function(request){
					if (trim(request.responseText)=='OK')
					{
						lightboxObj.hidePopup();
					}
					else if (trim(request.responseText)=='NOT_LOGGED_IN')
					{
						//lightboxObj.hidePopup();
						window.location = "/jsp/profile/register.jsp?fromPage=true";
					}
				}
			});
		},
		registerNotification: function() {
			var url = "/jsp/viewDefault/notification/registerProductNotification.jsp" ;
			var skuId = $("skuId").value;
			var notificationType = $("notificationType").value;

			var parameters = "";
			parameters += "sku=" + encodeURIComponent(skuId);
			parameters += "&notificationType=" + encodeURIComponent(notificationType);
			parameters += "&redirectUrl=" + encodeURIComponent(window.location.href);

			this.submitAjaxCall(url, parameters, this);

			return false;
			
		},
		cancel: function() {
			this.deactivate();
			return false;
		}
	});

	displayLightbox(url, new NotificationLightBox(linkElement));
}

/* Function to handle the click event of a notification links
 * Assumption: all the links with class="notificationLink" will have an Id defined.
 */
function initializeNotificationPopup(event)
{
	var clickedElement = event.element();
	var elementId = clickedElement.id;
	var splittedIds = elementId.split('_');
	var sku = splittedIds[1];
	var notificationType = splittedIds[2];
	var ajaxUrl = "/jsp/viewDefault/notification/productNotificationPopup.jsp?sku="+sku+"&notificationType="+notificationType;
	displayNotificationLightbox(ajaxUrl, clickedElement);
}

// Function to attach Click event to notification links
function initializeNotification()
{
	var linksArray = $$('a.notificationLink');
	// Custom loop with cached length property: maximum full-loop performance on very large arrays!
	for (var index = 0, len = linksArray.length; index < len; ++index) {
	  var linkItem = linksArray[index];
	  linkItem.observe('click', initializeNotificationPopup);
	}
}

addOnloadEvent(initializeNotification);

//js for new navbar dropdowns
startNavMenu2  = function() {
	if (document.all&&document.getElementById) {
		navRoot = document.getElementById("testNav");
		if (navRoot != undefined){
			for (i=0; i<navRoot.childNodes.length; i++) {
				node = navRoot.childNodes[i];
				if (node.nodeName=="LI") {
					node.onmouseover=function() {
					this.className+=" over";
				  }
					node.onmouseout=function() {
					this.className=this.className.replace(" over", "");
				   }
				  }
			  }
			 }
	}
}
addOnloadEvent(startNavMenu2 );

//4/29/11 J.Jones added for fault #47614
toolTipPositioning = function() {
	//This function fixes the issue with the help text popup floating off the end of the page when . This will reposition the popup so the entire message is seen
		$$('a.helpLink').each(function(item) {

			item.observe('mouseover', function (e) {
				var width = document.viewport.getWidth(); 
				var height = document.viewport.getHeight();
				var link = Event.element(e);
				var link_left = link.viewportOffset();
				var linkHeight = link.getHeight();
				var primary_link = $('primary').viewportOffset();	
				var linkText = this.select('span.helpText')[0];
				var blockWidth = linkText.getWidth();
				var blockHeight = linkText.getHeight();
				var blockCheckRightEdge = blockWidth+link_left[0];
				var blockCheckLeftEdge = link_left[0]-blockWidth;
				var blockCheckBottomEdge = blockHeight+link_left[1];
				var blockCheckTopEdge = link_left[1]-blockHeight;
				var blockOffset = -blockWidth+20;
				var topOffset = blockHeight+2;
				//check horizontal
				if (width < blockCheckRightEdge && 0 <= blockCheckLeftEdge)
					{
						linkText.setStyle({left: blockOffset+'px'});
					}
				else
					{
						linkText.setStyle({left: '20px'});
					}
				//check vertical
				if (height < blockCheckBottomEdge && 0 <= blockCheckTopEdge )
					{
						linkText.setStyle({top: -topOffset+'px'});
					}
				else
					{
						linkText.setStyle({top: '1.5em'});
					}
			});
			item.observe('mouseout', function (e) {
				var linkText = this.select('span.helpText')[0];				
				linkText.setStyle({left: '-10000px'});
			});
		});
}
addOnloadEvent(toolTipPositioning);

addOnloadEvent(function () {
	var isIE6 = Prototype.Browser.IE && parseInt(navigator.userAgent.substring(navigator.userAgent.indexOf("MSIE")+5)) == 6;
	if (isIE6) {
		Event.observe(window, 'resize', function() {
			var primary = $('primary');
			var secondary = $('secondary');
			var body = $$('body.productdetail')[0];
			
			
			if (primary && secondary && body) {
				
				primary.toggleClassName('random-class-for-refresh-trigger');
				
			}
		});
	}
});

/* Added for Search Browsability Manufacturer Section - PRJ50799 */
/* Function to add a manufacturer by clicking on the manufacturer name listed in the flyout on the left panel on search page*/
function manuFilter(selectedValue)
{
	if (parametricsFormURL.length > 0)
	{
		// if we are on the parametrics page then we want to perform the same processing
		// as would happen when clicking the apply button on the parametrics panel
		// so at the manufacturer n value to the query and call the same function
		parametrics_updateSearchStringNValue('addValue', ''+selectedValue);
		parametrics_doFilteredSearch();
	}
	else 
	{
		// otherwise update the form details and submit the refine form
		var selectedAllManufacturer = false;
		if (selectedValue=='ALL')
		{
			selectedAllManufacturer = true;
		}
		var revisedQuery;	
		var currentQuery = document.refineForm.currentQuery.value;
		var posOfN = currentQuery.indexOf("N=");
		var queryAfterN;
		var breakAfterN;
		var lastPartOfQuery;
		var nValue;
		var isNZeroPresent;
		var isPlusPresent;
		var nValueStripped;
		var lastIndexofPlus;
		var plusPrefixedPreviousAJAXParametricCallNValues= "";
		if(previousAJAXParametricCallNValues != "")
		{
			plusPrefixedPreviousAJAXParametricCallNValues="+"+previousAJAXParametricCallNValues;
		}

		if (posOfN < 0)
		{
			if (selectedAllManufacturer)
			{
				revisedQuery = currentQuery + "&N=0" + plusPrefixedPreviousAJAXParametricCallNValues;
			}
			else
			{
				revisedQuery = currentQuery + "&N=0"+plusPrefixedPreviousAJAXParametricCallNValues+"+" + selectedValue + ";";
			}
		} 	
		else
		{
			queryAfterN = currentQuery.substring(posOfN);
			breakAfterN = queryAfterN.indexOf("&");
			if (breakAfterN >= 0)
			{
				lastPartOfQuery=queryAfterN.substring(breakAfterN);
				nValue = queryAfterN.substring(0, breakAfterN);
			}
			else
			{
				lastPartOfQuery="";
				nValue = queryAfterN;
			}
			if(previousAJAXParametricCallNValues != "")
			{
				nValue="N="+previousAJAXParametricCallNValues;
			}
			isNZeroPresent = nValue.indexOf("N=0");
			if (isNZeroPresent >= 0) 
			{
				if (selectedAllManufacturer)
				{
					revisedQuery = "N=0"+ plusPrefixedPreviousAJAXParametricCallNValues + lastPartOfQuery;
				}
				else
				{
					if(nValue == "N=0")
					{
						revisedQuery = "N=0"+plusPrefixedPreviousAJAXParametricCallNValues + "+" + selectedValue +  lastPartOfQuery;
					}
					else
					{
						revisedQuery = nValue + "+" + selectedValue +  lastPartOfQuery;
					}
				}
			} 
			else 
			{
				isPlusPresent = nValue.indexOf("+");
				if (selectedAllManufacturer)
				{
					if (isPlusPresent < 0) 
					{
						revisedQuery = nValue + lastPartOfQuery;
					}
					else
					{
						lastIndexofPlus=nValue.lastIndexOf("+");
						nValueStripped=nValue.substring(0, lastIndexofPlus);
						revisedQuery = nValueStripped + lastPartOfQuery;	
					}
				}
				else
				{			// otherwise update the form details and submit the refine form

					if (isPlusPresent < 0)
					{
						revisedQuery = nValue + "+" + selectedValue +  lastPartOfQuery;
					}
					else
					{
						nValueStripped = nValue;
						revisedQuery = nValueStripped + "+" + selectedValue +  lastPartOfQuery;	
					}
				}
			}  
		} 
		if (nValue!="N=0" || selectedAllManufacturer!=true)
		{
			document.refineForm.currentQuery.value = revisedQuery;
			passInNValuesToSearchFilters(LINK_PARAMETRICS_TO_MANUFACTURERS);
			setRequestMethod(document.refineForm);
			document.refineForm.submit();
		}
	}
}

/* Function to remove a selected manufacturer from the left panel on search page*/
function removeManuFilter(selectedValue)
{
	
	var manufacturerDimensionId = -1;
	var productGroupDimensionId = null;
	if($('manufacturerDimensionId')!= null)
	{
		manufacturerDimensionId=$('manufacturerDimensionId').value;
	}		
	if($('productGroupDimensionIdContainer'))
	{
		productGroupDimensionId=$('productGroupDimensionIdContainer').innerHTML;
	}	
	if(manufacturerDimensionId==productGroupDimensionId)    // If we are tryng to remove the selected manufacturer from product group detail page.
	{			
		var productGroupDimensionId = $('productGroupDimensionIdContainer').innerHTML;
		parametricSelectionCloseEvent(selectedValue,manufacturerDimensionId);		
	}
	else		
	if(document.refineForm && document.refineForm.currentQuery)
	{
		// remove the value from the parametrics search string
		parametrics_updateSearchStringNValue('removeValue', selectedValue);
		
		if (parametricsFormURL.length > 0)
		{
			// if we are on the parametrics page then we want to perform the same processing
			// as would happen when clicking the apply button on the parametrics panel
			parametrics_doFilteredSearch();
		}
		else
		{
			// otherwise update the form details and submit the refine form
			var revisedQuery;	
			var currentQuery = document.refineForm.currentQuery.value;
			var removedManufacturer='+'+selectedValue;
			revisedQuery=currentQuery.replace(removedManufacturer,"");
			document.refineForm.currentQuery.value = revisedQuery;
			passInNValuesToSearchFilters(LINK_PARAMETRICS_TO_MANUFACTURERS);
			setRequestMethod(document.refineForm);
			document.refineForm.submit();
		}
	}
}

/* Function to remove a selected parametric filter from the left panel on search page*/
function removeParamFilter_NonAjax(selectedValue,dimensionId)
{	
	if(document.refineForm && document.refineForm.currentQuery)
	{
		var refinementCB = $('rcb' + selectedValue);
		if (refinementCB) {
			parametrics_uncheckCheckbox(refinementCB);
		}
		// remove the value from the parametrics search string
		parametrics_updateSearchStringNValue('removeValue', selectedValue);
		
		if (parametricsFormURL.length > 0)
		{
			// if we are on the parametrics page then we need to make sure
			// the min/max drop downs are cleared for this value/dimension
			// and then perform the parametrics search
			parametrics_setMinForDimension(dimensionId,null);
			parametrics_setMaxForDimension(dimensionId,null);
			parametrics_doFilteredSearch();
		}
		else
		{
			// otherwise use the filters form
			var revisedQuery;	
			var currentQuery = document.refineForm.currentQuery.value;
			var removedFilter='+'+selectedValue;
			revisedQuery=currentQuery.replace(removedFilter,"");
			document.refineForm.currentQuery.value = revisedQuery;
			passInNValuesToSearchFilters(LINK_PARAMETRICS_TO_PARAMETRICS_CLOSE);
			document.refineForm.submit();
		}
	}
}


/* Function to control the display of manufacturer flyout and the selected manufacturer */
function showHideManufacturerMenu()
{
	if($$('#allmanu .name').length > 0)
	{
		$$('#allmanu .name')[0].observe('click', function(e) 
		{
			if($('ul_manu-list').innerHTML != '')
			{
				$$('#manuContent .manuContainer')[0].show();
			}
		});
	}
	
	/* display manufacturer */
 	if($$('.manu-list').length >0)
	{
		$$('.manu-list')[0].observe('click', function(e)
		{
			$$('#manuContent .manuContainer')[0].hide();
			var element = Event.element(e);
			var selectedManuID = element.rel;
			if(selectedManuID != null)
			{
				setTimeout("manuFilter("+selectedManuID+")",10);			
			}
		});
 	}
}
addOnloadEvent(showHideManufacturerMenu);


var LINK_PARAMETRICS_TO_SEARCH_FILTERS = true;
var LINK_PARAMETRICS_TO_MANUFACTURERS = true;
var LINK_PARAMETRICS_TO_PARAMETRICS_CLOSE = true;

/*
 * Function to update any n values to be shared between the filters and the
 * parametrics section for a selected filter.
 */
function updateSharedNValuesForFilter(element, nValueToUpdate,toggle) {
	// only want to do anything if we are linking the parametrics to filters
	if (LINK_PARAMETRICS_TO_SEARCH_FILTERS) 
	{
		// update the n values
		updateSharedNValuesForCheckBox(element, nValueToUpdate,toggle);

		// if auto update perform the search
		if (typeof(PARAMETRICS_AJAX) != "undefined" && PARAMETRICS_AJAX)
		{
			// perform search
			parametrics_retrieveDimensions();
		}
	}
}

/*
 * Function to update any n values to be shared between the filters and the
 * parametrics section for a check box, i.e. if the box is checked it will
 * add the value, if it is unchecked it will remove it.
 */
function updateSharedNValuesForCheckBox(element, nValueToUpdate,toggle) {
	// if checked then add the the nValue
	var parameterName1 = 'addValue';
	var parameterName2 = 'removeValue';
	//toggle the click action for NIC filter checkbox
	if(toggle)
	{
		parameterName1 = 'removeValue';
		parameterName2 = 'addValue';
	}
	if (element.checked) 
	{
		parametrics_updateSearchStringNValue(parameterName1, nValueToUpdate);
	}
	// otherwise remove the nValue
	else 
	{
		parametrics_updateSearchStringNValue(parameterName2, nValueToUpdate);
	}
}

/*
 * Function that will pass in any n values from the parametric section that been selected
 * into the search filters form. This is only done if the flag to link the two panels is
 * set. Also sets the original query.
 */
function passInNValuesToSearchFilters(linkPanels)
{
	// only want to pass in the details if we are linking parametrics to filters
	if (linkPanels) {
		var nValues = getURLParameter(searchQueryURL, 'N');
		// we only want to do anything if we have the shared values available
		if (nValues != null) 
		{
			var searchFiltersNValues = document.getElementById('searchFiltersNValues');
			if (searchFiltersNValues != null) 
			{
				searchFiltersNValues.value = nValues;
			}
		}

		// set the removed n values if we are on the parametrics page
		if (typeof(removedNValues) != "undefined")
		{
			/*Remove all the N values in nValues variables from the removedNValues N value list
			This is to allow considering any N value selection done after clearing the filters and clicking static filter Apply button*/
			if (nValues != null && removedNValues != null)
			{
				var selectedNValues = nValues.split('+');
				var selectedNValuesLength= selectedNValues.length;
				for ( var index = 0; index < selectedNValuesLength ; index++ )
				{
					removedNValues=removedNValues.replace(selectedNValues[index],'');
				}
			}
			var searchFiltersRemovedNValues = document.getElementById('searchFiltersRemovedNValues');
			if (searchFiltersRemovedNValues != null) 
			{
				searchFiltersRemovedNValues.value = removedNValues;
			}
		}

		// set the original query if we are on the parametrics page
		if (typeof(originalQueryURL) != "undefined")
		{
			var searchFiltersOriginalQueryURL = document.getElementById('searchFiltersOriginalQueryURL');
			if (searchFiltersOriginalQueryURL != null) 
			{
				searchFiltersOriginalQueryURL.value = encodeURIComponent(originalQueryURL);
			}
		}
	}
	setRequestMethod($('refineForm'));
}

/* Function to display tariff code popup */
function displayTariffExplainPopup(linkElement){
        NotificationLightBox = Class.create(lightbox, {
		initialize: function() {},
        	showBusy: function() {
			$("busyImage").show();
        	},
        	hidePopup: function() {
	        	$("busyImage").hide();
			this.deactivate();
        	},
        	cancel: function() {
                	this.deactivate();
                	return false;
        	}

       });
       var lUrl = "/jsp/shoppingCart/tariffExplainPopup.jsp";
       displayLightbox(lUrl, new NotificationLightBox(linkElement));
}

/* Function to display product group based on the value selected from productGroup dropdown*/
function productGroupChange(attributeId)
{
	var currentURL = window.location.href;
	if(attributeId === "")
	{
		window.location.href =  removeURLParameter(currentURL, 'selectedPg');	
	}
	else
	{
		var showResultsURL = searchQueryURL+"&selectedPg="+attributeId+"&No=0"+"&originalQueryURL=" + encodeURIComponent(originalQueryURL);
		// POST the Product Group form when the showResultsURL length > 2000
		if (showResultsURL.length > 2000) 
		{
			var parametricsForm = $('myParametricsForm');
			if (parametricsForm) 
			{	
				$('longSearchQuery').value = showResultsURL;
				parametricsForm.action = '/jsp/search/browse.jsp';
				parametricsForm.method = 'POST';
				parametricsForm.submit();
			}
		}
		else
		{	
			currentURL = setURLParameter(currentURL, 'No', "0");
			window.location.href = setURLParameter(currentURL, 'selectedPg', attributeId);	
			window.submit;
		}			
	}	
}

function restoreProductGroupsDropdown()
{
	var selectedPg = $('selectPGId').value;
	var dropdownLength = $('productGroupSelect').options.length;
	for ( var i = 0; i < dropdownLength; i++ )
	{
        if ( $('productGroupSelect').options[i].value === selectedPg ) 
        {
        	$('productGroupSelect').options[i].selected = true;
            return;
        }
	}	
}

/* This function added as part of search browsability feature and is used to populate the manufacturer flyout based
   on the parametric refinements seletced by the user when Auto Apply is Clicked */

function loadManufacturerFlyout(refinements)
{
	try
	{
		var noManufacture = true;
		if(refinements)
		{
			var currentManufacturerRefinement = '';
			refinements.each(function (refinement) 
			{
				if(refinement.status=='AVAILABLE' )
				{
					noManufacture = false;
					currentManufacturerRefinement =  currentManufacturerRefinement + '<li>' + ' <a rel='+refinement.id+' href="javascript:void(0)" > ' +
					refinement.name + ' (' + refinement.recordCount + ') </a><br></li>'
				}
			});
			$('ul_manu-list').innerHTML = currentManufacturerRefinement;
		}
		selectManufacturersToggle(noManufacture);
	}
	catch(e){}
}
function selectManufacturersToggle(noManufacture)
{
	if(noManufacture)
	{
		$('selectmanufacturelink').hide();
		$('selectmanufacture').show();
		$('container').hide();
	}
	else
	{
		$('selectmanufacturelink').show();
		$('selectmanufacture').hide();
	}
}


countrySelector = function() {
	var ieOnWinVer = (function() {
	var a=navigator.userAgent; 
	var i=a.indexOf("MSIE");
	if (i== -1 || navigator.platform.indexOf("Win") == -1) return 0;
		return parseFloat(a.substring(i+5)); // 5 is "MSIE".length+1
	})();
	if ($$('.globalHeader .countrySelector .box').length > 0) {
		$$('.globalHeader .countrySelector .box')[0].observe('mouseover', function() {
			this.addClassName('IEhover');
			$$('.globalHeader .headerRight')[0].addClassName('tempCountryselectorHoverFix');
		});
		$$('.globalHeader .countrySelector .box')[0].observe('mouseout', function() {
			this.removeClassName('IEhover');
			$$('.globalHeader .headerRight')[0].removeClassName('tempCountryselectorHoverFix');
		});
	}
};
	
addOnloadEvent(countrySelector);

//BAU #52852 make pagination scroll to the right side of the browser window for tables wider than browser
paginationPositioning = function() {	
			var tableWidth = $$('#searchResults')[0].getWidth();
			var tableOffset = $$('#searchResults')[0].cumulativeOffset ();
			tableOffset = tableOffset[0];
			var viewportWidth = document.viewport.getWidth();
			var scrollLeft = document.viewport.getScrollOffsets();
			scrollLeft = scrollLeft[0]
			var paddingRight = (tableOffset+tableWidth)-(scrollLeft+viewportWidth)+30;
			if (paddingRight <= 0) 
			{
				paddingRight = 30;
			}
			else if (paddingRight >= (tableWidth-300))
			{
				paddingRight = (tableWidth-300);
			}
					
			$$('.mfPager > div').each(function(item) {
				item.setStyle({paddingRight:paddingRight +'px'});
			});
			//alert('tablewidth ' +tableWidth + 'tableOffset ' +tableOffset + 'viewportWidth ' +viewportWidth + 'scrollLeft ' +scrollLeft + 'paddingRight ' +paddingRight);

};

runPP = function() {
	if ($$('#searchResults').length > 0) {
		paginationPositioning();
		Event.observe(document.onresize ? document : window, "resize", function() {
			paginationPositioning();
		});
		Event.observe(document.onscroll ? document : window, "scroll", function() {
			paginationPositioning();
		});
	}
}

addOnloadEvent(runPP);

/* Function to show hidden price break in search results page for product group products. */

function showPriceBreak(productId,count)
{
	for(var i=0;i<count;i++)
	{
		$(productId+""+i).style.display = "block";
		$("moreLink"+productId).style.display = "none";
	}
}

 /* Some times the URL length may exceed the handling capacity, in that case call this method by passing form object */
function setRequestMethod(form)
{
	var str = '';
	if(form != null)
	{
		var strLen = form.length;
	}
	for(var i=0; i< strLen; i++)
	{
		str += (form.elements[i].name+ "%3D" + form.elements[i].value+"%26");
	}
	if(str.length > 2000)
	{
		form.method = 'POST';
	}
}
/* Function to be performed */
function staticFiltersParametricsApply()
{
	setSearchTermOntoSearchString();
	parametrics_doFilteredSearch();
}

/* Function to update the search string with the search term entered in the filter box.
 * This should only be used when parametrics is available
 */
function setSearchTermOntoSearchString()
{
	var searchStringElement = document.getElementById('searchwithinRange');
	if (searchStringElement != null) 
	{
		var searchString = searchStringElement.value;
		var searchwithinRangeTextElement = document.getElementById('searchwithinRangeText');
		// only do something if we have a search term and it isn't the default one.
		if (searchString.length > 0 && searchString != searchwithinRangeTextElement.value)
		{
			parametrics_updateSearchStringWithSeparator('updateValue', 'gensearch', "Ntk", "|");
			parametrics_updateSearchStringWithSeparator('updateValue', searchString, "Ntt", "|");
			parametrics_updateSearchStringWithSeparator('updateValue', 'mode+matchallpartial', "Ntx", "|");
		}
	}
}

/* Function to be used on the static filters panel when on the parametrics page so that
 * an enter key press on the search within text box causes the apply button to be
 * clicked, rather than the form be submitted.
 */
function staticFiltersNonFormEnterSubmit(event)
{
	if($('staticFilterButton'))
	{
		event = event || window.event;
		var charCode = event.which || event.keyCode;
		if (charCode == 13)
		{
			if ($('staticFilterButton'))
			{
				$('staticFilterButton').click();
				return false;				
			}
		}
	}
}

if ($('refineForm'))
{
	$('refineForm').onkeypress = staticFiltersNonFormEnterSubmit;
}