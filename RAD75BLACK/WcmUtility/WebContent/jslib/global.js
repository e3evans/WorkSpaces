function changeClass(Elem, myClass) {
			
	var elem;
	if(document.getElementById) {
		var elem = document.getElementById(Elem.id);
	} else if (document.all){
		var elem = document.all[Elem.id];
	}
	elem.className = myClass;
}

function splitJsfId(sourceObject){
	return sourceObject.id.split(':');
}

function setFieldValue(sourceObject,fieldId){
	alert(sourceObject.id);
	alert(fieldId.id);
		var splitId = splitJsfId(sourceObject);
		var popField = '';
		for (i=0;i<splitId.length-1;i++){
		
			popField += splitId[i]+':';
		}
		popField+=fieldId;
		document.getElementById(popField).value=sourceObject.value;
		alert(document.getElementById(popField).id);
//		alert(document.getElementById(popField).id);
}
function setSavedPrefField(sourceObject,fieldId,value){
	
	var splitId = splitJsfId(sourceObject);
		var popField = '';
		for (i=0;i<splitId.length-1;i++){
			
			popField += splitId[i]+':';
		}
		popField+=fieldId;
		document.getElementById(popField).value=value;
		
}

function callRemoteAction(sourceObject,remoteForm,remoteButtonId){
	var splitId = splitJsfId(sourceObject);
	alert(splitId);
	var viewId = splitId[0];
	alert(viewId);
	var remoteActionId = viewId+":"+remoteForm+":"+remoteButtonId;
	alert(remoteActionId);
	document.getElementById(remoteActionId).click();

}


function callRemoteAction1(sourceObject,remoteForm,remoteButtonId){
	alert(document.getElementById(remoteForm.remoteButtonId));

}



function clickFieldValue(sourceObject,fieldId){
		var splitId = splitJsfId(sourceObject);
		var popField = '';
		for (i=0;i<splitId.length-1;i++){
			popField += splitId[i]+':';
		}
		popField+=fieldId;
		
		alert(document.getElementById(popField).id());
		//document.getElementById(popField).value=sourceObject.value;
}


