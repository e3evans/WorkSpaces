

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

