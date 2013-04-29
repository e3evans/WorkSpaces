addEvent(window,"load",addButtonEndings);
addEvent(window,"load", hideCountryPages);



function addEvent(o,e,f){
	if (o.addEventListener){ o.addEventListener(e,f,true); return true; }
	else if (o.attachEvent){ return o.attachEvent("on"+e,f); }
	else { return false; }
}

function changeClass(Elem, myClass) {
			
	var elem;
	if(document.getElementById) {
		var elem = document.getElementById(Elem.id);
	} else if (document.all){
		var elem = document.all[Elem.id];
	}
	elem.className = myClass;
}

function getSelected(Selector){
	var selects = new Array();
	var j=0;
	for (var i=0; i<Selector.options.length;i++){
		if (Selector.options[i].selected) {
             selects[j++] = Selector.options[i].text;
        }	
	}
	return selects;
}

/* http://www.dustindiaz.com/getelementsbyclass/ */
function getElementsByClass(searchClass,node,tag) {
	var classElements = new Array();
	if ( node == null )
		node = document;
	if ( tag == null )
		tag = '*';
	var els = node.getElementsByTagName(tag);
	var elsLen = els.length;
	var pattern = new RegExp("(^|\\s)"+searchClass+"(\\s|$)");
	for (i = 0, j = 0; i < elsLen; i++) {
		if ( pattern.test(els[i].className) ) {
			classElements[j] = els[i];
			j++;
		}
	}
	return classElements;
}

function insertAfter(newElement, targetElement) {
	var parent = targetElement.parentNode;
	if (parent.lastChild == targetElement) {
		parent.appendChild(newElement);
	}
	else {
		parent.insertBefore(newElement, targetElement.nextSibling);
	}
}

function addButtonEndings() {
	if (!document.getElementsByTagName) {
		return false;
	}
	
	var buttons = getElementsByClass("myManpowerCSS_orange24");
	/* loop through all buttons and attach a child div */
	for (i=0; i < buttons.length; i++) {
		var div = document.createElement("div");
		div.className = "myManpowerCSS_orange24end";
		insertAfter(div, buttons[i]);
	}
	
	var blueButtons = getElementsByClass("myManpowerCSS_blue24");
	/* loop through all buttons and attach a child div */
	for (i=0; i < blueButtons.length; i++) {
		var div = document.createElement("div");
		div.className = "myManpowerCSS_blue24end";
		insertAfter(div, blueButtons[i]);
	}
	
	var buttons = getElementsByClass("myManpowerCSS_blue24_disable");
	/* loop through all buttons and attach a child div */
	for (i=0; i < buttons.length; i++) {
		var div = document.createElement("div");
		div.className = "myManpowerCSS_blue24end";
		insertAfter(div, buttons[i]);
	}	
	
}

function showElement(formId,elementId)
{
	document.getElementById(portletNamespace+":"+formId+":"+elementId).style.display = '';	
}

function hideElement(formId, elementId)
{
	document.getElementById(portletNamespace+":"+formId+":"+elementId).style.display = 'none';	
}

function hideCountryPages() {

	hideElement('candidate_form','usaSubview:usaCandidateData_subview:us_data_pg');
	hideElement('candidate_form','canadaSubview:canadaCandidateData_subview:us_data_pg');
}

function clickHome() {
	var el = document.getElementById('homeLink');
	if(el)
	{
		el.styleClass="active";
	}
}

function setTime()
{

	var usaExpDate = document.getElementById(portletNamespace + ":candidate_form:usaSubview:usaCandidateData_subview:datum11").value;
	alert("usaExpDate = " + usaExpDate);
	var usaBirthDate = document.getElementById(portletNamespace + ":candidate_form:usaSubview:usaCandidateData_subview:datum21").value;

	alert("usaBirthDate = " + usaBirthDate);
	
	if(usaExpDate != null && usaExpDate != "")
	{
		alert("set usa visa exp date");
		document.getElementById("datum1").value = usaExpDate;
		
	}
	alert("check");
	if(usaBirthDate != null && usaBirthDate != "")
	{
		alert("set usa birth date");
		document.getElementById("datum2").value = usaBirthDate;
	}
	alert("exit usa");
	
	
	var canadaExpDate = document.getElementById(portletNamespace + ":candidate_form:canadaSubview:canadaCandidateData_subview:datum31").value;
	alert("canadaExpDate = " + canadaExpDate);
	var canadaBirthDate = document.getElementById(portletNamespace + ":candidate_form:canadaSubview:canadaCandidateData_subview:datum41").value;
	
	alert("canadaBirthDate = " + canadaBirthDate);
	if(canadaExpDate != null && canadaExpDate != "")
	{
		alert("set canada visa exp date");
		document.getElementById("datum3").value = canadaExpDate;
	}
	alert("check");
	if(canadaBirthDate != null && canadaBirthDate != "")
	{	
		alert("set canada birth date");
		document.getElementById("datum4").value = canadaBirthDate;
	}
	alert("exit canada");
	
	
}

