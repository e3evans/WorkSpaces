	var textArea;
	var dispList;
	var inputText;
	var dispArea;
	
	function initBulletVariables(textAreaId){
		//currentNamespace is set at the .jsp level
		textArea = document.getElementById('view'+currentNamespace+':'+textAreaId);
		dispList = document.getElementById('\"'+textAreaId+'_list\"');
		dispArea = document.getElementById(textAreaId+'_disp');
		//alert(textAreaId);alert(dispArea); 
		inputText = document.getElementById(textAreaId+'_input');
	}
	
	function clearEdit(){
		var li;
		if (dispList!=null){
			for (i=0;i<dispList.childNodes.length;i++){
				li=dispList.childNodes[i];
				elementnum = 0;
				if (li.hasChildNodes){
					var children = li.childNodes;
					var saveChild = li.childNodes[0];
					while (li.firstChild){
						li.removeChild(li.firstChild);
					}
					li.appendChild(saveChild);
				}
				
			}
		}
	}
	function editItem(textAreaId,edit){
		initBulletVariables(textAreaId);
		var editWhat = edit.substring(edit.length-1,edit.length);
		var temp = dispList.childNodes[editWhat];
		editingBullet=editWhat;
		showPostPanel(cleanItem(temp).innerHTML,startPos);	
		clearEdit();
		setEditMode(textAreaId);
	}
	
	
	function addItem(){
		//Current Text Area ID is set by the function that opens the edit box at the .jsp level
		textAreaId = currentTextAreaId;
		initBulletVariables(textAreaId);
		var errmsg='';
		//IF NO LIST EXISTS CREATE IT.
		if (dispList==null){
			dispList = document.createElement('ul');
			dispList.className = '\"GEMTbullets\"';
			dispList.id = '\"'+textAreaId+'_list\"';
			dispArea.appendChild(dispList);
		}else if(dispList.childNodes.length>5){
			//Verify the number of bullets
			if (editingBullet==null){
				errmsg+='-Maximum of 6 bullets allowed.\n';
			}
		}
		//CREATE AN LI ELEMENT TO APPEND TO THE LIST
		var li;
		var revertString='';
		if (editingBullet==null){
			//CREATE A BULLET IF WE'RE NOT EDITING AN EXISTING BULLET
			li = document.createElement('li');
		}else{	
			//GET THE EXISTING BULLET BASED ON "editingBullet"(Integer)
			li=dispList.childNodes[editingBullet];
			revertString=li.innerHTML;		
		}
		
		
		li.innerHTML = getBulletText();
		
		//CHECK THE BROWSER IF IT'S FIREFOX WE HAVE TO GET THE INNER TEXT VIA "textContent"
		if (navigator.appName.toLowerCase().indexOf('microsoft')==-1){
			//MSIE
			testText = trimAll(li.textContent);
		}else{
			//FIREFOX or NETSCAPE BASED
			testText = trimAll(li.innerText);
		}
		//SPLIT THE TRIMMED TEXT OUT INTO AN ARRAY OF WORDS.
		var temp = testText.split(' ');
		//CHECK THE LENGTH MAXIMUM # OF WORDS IS 25.
		if (temp.length>25) errmsg+='-Bullets are limited to 25 words or less.';	
		
		//IF WE'VE FAILED ANY OF OUR ERROR TESTS REVERT THE LI TO THE VALUE IT STARTED WITH AND
		//FIRE AN ALERT MESSAGE BEFORE REDIRECTING THEM BACK TO THE EDIT BOX.
		if (errmsg!=''){
			li.innerHTML=revertString;
			alert(errmsg);
			return;
		}
		
		//EITHER APPENDS A NEW BULLET OR WIPES OUT THE GLOBAL VARIABLE "editingBullet"
		if (editingBullet==null){
			dispList.appendChild(li);
		}else{
			editingBullet=null;
		}
		
		clearEdit();
		//SET THE TEXT AREA VALUE FOR SUBMISSION
		
		textArea.value=dispArea.innerHTML;
		
		textArea.innerText = dispArea.innerHTML;
		setEditMode(textAreaId);
		closePostPanel();
		
	}
	function removeItem(textAreaId,remove){
		initBulletVariables(textAreaId);
		var removeWhat = remove.substring(remove.length-1,remove.length);
		var temp = dispList.childNodes[removeWhat];
		dispList.removeChild(temp);
		/**ERIC ERIC**/
		clearEdit();
		textArea.value=dispArea.innerHTML;
		textArea.innerText = dispArea.innerHTML;
		
		setEditMode(textAreaId);
		//alert(textArea.innerText);
		
	}
	
	function moveItem(textAreaId,upDown){
		//alert(textAreaId);
		initBulletVariables(textAreaId);
		var moveWhat = upDown.substring(upDown.length-1,upDown.length);
		var temp = dispList.childNodes[moveWhat];
		var move = -1;
		
		if (upDown.indexOf('_up')>-1){
			move = 1;
		}
		
		move = moveWhat - move;
		
		dispList.removeChild(temp);
		if (dispList.childNodes.length==move){
			dispList.appendChild(temp);
		}else{
			dispList.insertBefore(temp,dispList.childNodes[move]);
		}
		clearEdit();
		setEditMode(textAreaId);
		
	}
	
	
	/**
		Created function to cleanup the firefox DOM model
	**/
	function cleanList(textAreaId){
		var li;
		for (i=0;i<dispList.childNodes.length;i++){
			li=dispList.childNodes[i];
			if (typeof(li.value)=='undefined'){
				dispList.removeChild(li);
			}
		}
		
	}
	function cleanItem(item){
		var saveChild = item.childNodes[0];
		var saveSpan = item.childNodes[1];
		while(item.firstChild){
			item.removeChild(item.firstChild)
		}
		item.appendChild(saveChild);
		item.appendChild(saveSpan);
		return item;
	}
	function createItem(textAreaId,i){
		var li=dispList.childNodes[i];
		var up;
		var down;
		var remove;
		var edit;
		if (i!=0){
			up=document.createElement('a');
			up.id=textAreaId+'_up'+i;
			up.className='mpThemeCommandLink';
			up.href="javascript:moveItem('"+textAreaId+"','"+up.id+"')";
			up.appendChild(document.createTextNode('Move Up'));
			li.appendChild(document.createTextNode('   '));
			li.appendChild(up);
		}
		if (i!=dispList.childNodes.length-1){
			down=document.createElement('a');
			down.id=textAreaId+'_down'+i;
			down.className='mpThemeCommandLink';
			down.href="javascript:moveItem('"+textAreaId+"','"+down.id+"')";
			down.appendChild(document.createTextNode('Move Down'));
			
			if (i==0){
				li.appendChild(document.createTextNode('   '));
			}else{
				li.appendChild(document.createTextNode(' | '));
			}
			li.appendChild(down);
		}
		li.appendChild(document.createTextNode(' | '));
		edit=document.createElement('a');
		edit.id=textAreaId+'_edit'+i;
		edit.className='mpThemeCommandLink';
		edit.href="javascript:editItem('"+textAreaId+"','"+edit.id+"')";
		edit.appendChild(document.createTextNode('Edit'));
		li.appendChild(edit);
		
		li.appendChild(document.createTextNode(' | '));
		remove=document.createElement('a');
		remove.id=textAreaId+'_remove'+i;
		remove.className='mpThemeCommandLink';
		remove.href="javascript:removeItem('"+textAreaId+"','"+remove.id+"')";
		remove.appendChild(document.createTextNode('Remove'));
		li.appendChild(remove);
	
	}
	
	function setEditMode(textAreaId){
		initBulletVariables(textAreaId);
		cleanList(textAreaId);
		
		if (dispList!=null){
			
			for (i=0;i<dispList.childNodes.length;i++){
				if(dispList.childNodes[i].childNodes.length==2 || dispList.childNodes[i].childNodes.length==1 ){
					createItem(textAreaId,i)
				}
			}
		}
	}
	function toggleEdit(textAreaId){
		initBulletVariables(textAreaId);
		cleanList(textAreaId);
		
		if (dispList!=null){
			
			for (i=0;i<dispList.childNodes.length;i++){

				if(dispList.childNodes[i].childNodes.length==2 || dispList.childNodes[i].childNodes.length==1 ){
					createItem(textAreaId,i)
				}else{
					clearEdit();
					break;
				}
				
			}
		}
	
	}
	
		function getBulletText(){
		var editor = dojo.widget.byId('bullets_richText');
		return editor.getEditorContent();
	}
	
	
	function showPostPanel(content,position)
	{
		var parentNode = dojo.byId('masterBullets');
		//create a new div node that the editor will exist in. This will be created and deleted	
		//each time the editor is shown/hidden
		
		if (parentNode.childNodes.length>0)parentNode.removeChild(parentNode.firstChild);
		
		var widgetNode = document.createElement('div');
		
		parentNode.appendChild(widgetNode);
		
		widgetNode.innerHTML = content ? content : '<FONT style=\"font-family:Arial;font-size:xx-small;\">&nbsp;</FONT>';
		var editorArgs = {items: ["bold", "italic", "underline", "strikethrough","forecolor", "hilitecolor"],id:'bullets_richText',htmlEditing:false};
		var widget = dojo.widget.createWidget('Editor2', editorArgs,widgetNode);
		
		
		var enclosingNode = document.getElementById("enclosingNode");
		//var enclosingNode = dojo.byId('d_newPost');
		
		enclosingNode.style.left = position[0];
		enclosingNode.style.top = position[1];
		enclosingNode.focus();
	}
	function closePostPanel(){
		var enclosingNode = document.getElementById("enclosingNode");
		var parentNode = dojo.byId('masterBullets');
		while(parentNode.firstChild){
			parentNode.removeChild(parentNode.firstChild);
		}
		enclosingNode.style.left = '-1000px';
		enclosingNode.style.top = '-1000px';
		//setEditMode(textAreaId);
	
	}
	
	function findButtonPos(obj) {
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
	
	//TRIM FUNCTIONS
	
	function leftTrim(sString){
		while (sString.substring(0,1) == ' '){
			sString = sString.substring(1, sString.length);
		}
		return sString;
	}
	
	
	function rightTrim(sString){
		while (sString.substring(sString.length-1, sString.length) == ' '){
			sString = sString.substring(0,sString.length-1);
		}
		return sString;
	}



	function trimAll(sString){
		while (sString.substring(0,1) == ' '){
			sString = sString.substring(1, sString.length);
		}
		while (sString.substring(sString.length-1, sString.length) == ' ')
		{
			sString = sString.substring(0,sString.length-1);
		}
		return sString;
	}
	