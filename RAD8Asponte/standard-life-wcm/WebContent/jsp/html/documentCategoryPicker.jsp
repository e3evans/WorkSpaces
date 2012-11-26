<%@page session="false" contentType="text/html" import="ca.standardlife.wcm.Constants"%>
<%
	String taxonomyName = request.getParameter("taxonomyName");
	if (taxonomyName == null){%>
	
	<font color="red">Error during building the Document Category Picker; taxonomy name is unknown !</font>
	
<%}else{%>

<script type="text/javascript">
dojo.addOnLoad(function() {
	populateRootCategory("<%=taxonomyName%>");
	populateRootCategory("<%=Constants.DOCUMENT_TAGS_TAXONOMY_ROOT%>");		
});
	
function populateRootCategory(taxonomyName) {
	printPickerMessage("", "reset");
	dojo.xhrGet({
		url:"/standard-life-wcm/SLCategoryPickerJson?taxonomyName="+taxonomyName,
		handleAs:"json",
		preventCache: true,
		load: function(data){
			if (data.catPickerItems.length == 0) {
				printPickerMessage("Item '"+data.catPickerName+"' has no children! Please select another item.</font>", "error");
				return;
			}
			if (taxonomyName == "<%=Constants.DOCUMENT_TYPES_TAXONOMY_ROOT%>") {
				var combo = dojo.byId("comboDocumentCategory");
				combo.options.length = 0;
				combo.options[combo.options.length] = new Option("<%=Constants.DOCUMENT_CATEGORY_PICKER_DEFAULT_OPTION_LABEL_ROOT%>", "0");
				for (i in data.catPickerItems) {
					combo.options[combo.options.length] = new Option(data.catPickerItems[i].title, data.catPickerItems[i].uuid);
				}
				if (combo.disabled) combo.disabled = false;
			} else {				
				for (i in data.catPickerItems) {
					var catName = data.catPickerItems[i].name;
					var catUUID = data.catPickerItems[i].uuid;					
					if (catName == "Usage") {
						setPicker(catUUID, 'comboDocumentUsage');
					} else if (catName == "Jurisdiction") {
						setPicker(catUUID, 'comboJurisdiction');
					} else if (catName == "Audience") {
						setPicker(catUUID, 'comboAudience');
					}
				}
			}	   		
        },
        error: function(error){
    		printPickerMessage("Couldn't fetch data from SLCategoryPickerJson: "+error, "error");
  		}
	}); 
}

function setPicker(selectedVal, targetComboName) {
	printPickerMessage("", "reset");
	if (selectedVal != "0") {
		dojo.xhrGet({
			url:"/standard-life-wcm/SLCategoryPickerJson?catUUID="+selectedVal, // val is UUID of the item, category names are not unique in the taxonomy model
			handleAs:"json",
			preventCache: true,
			load: function(data){
				if (data.catPickerItems.length == 0) {
					printPickerMessage("Item '"+data.catPickerName+"' has no children! Please select another item.", "error");
					return;
				}				
				
				var combo = dojo.byId(targetComboName);
		   		combo.options.length = 0;
		   		combo.options[combo.options.length] = new Option("<%=Constants.CATEGORY_PICKER_DEFAULT_OPTION_LABEL_PLEASE_SELECT%>", "0");
		   		for (i in data.catPickerItems) {
		   			combo.options[combo.options.length] = new Option(data.catPickerItems[i].title, data.catPickerItems[i].uuid);
		   		}
		   		if (combo.disabled)
					combo.disabled = false;
	        },
	        error: function(error){
	    		printPickerMessage("Couldn't fetch data from SLCategoryPickerJson: "+error, "error");
	  		}
		}); 
	}
}

// Sets the categories of the Authoring Template
function setATCategories() {
	
	var atCategory = dojo.byId('dialog_Inputprofile_control_multiSelectCategory');
	if (!atCategory) {
		printPickerMessage("Could not assign categories, authoring template has no profiling enabled.", "error");
		return;
	} else {
		catUUID = dojo.byId('comboAudience').options[dojo.byId('comboAudience').selectedIndex].value;
		if (catUUID == 0) {
			printPickerMessage("Please browse through the entire category picker !", "error");
			return;
		}
		
		if (ccbCategories.length > 0) ccbCategories +=",";
		ccbCategories += catUUID;	
		//console.log("Cats for the AT are:"+ccbCategories);
		
		// Add the categoriesd to the Authoring Template
		if (atCategory.value.length > 0)
			atCategory.value += "," + ccbCategories;
		else
			atCategory.value = ccbCategories;
			
		printPickerMessage("Category ["+dojo.byId('comboAudience').options[dojo.byId('comboAudience').selectedIndex].text+"] added.", "success");
	}
}

function resetPicker() {
	dojo.query(".catPicker").forEach(function(c){
		c.options.length = 0;
		c.options[c.options.length] = new Option("<%=Constants.CATEGORY_PICKER_DEFAULT_OPTION_LABEL%>", "0");
		c.disabled = true;
	});
	printPickerMessage("", "reset");
	if (jsTaxonomyName=='<%=Constants.PRODUCT_TAXONOMY_ROOT%>') 
		dojo.byId("seriesWrapper").style.display = "none";
		
	populateRootCategory(jsTaxonomyName);
}

function printPickerMessage(msg, status) {
	var msgContainer = dojo.byId("slCategoryPickerMessage");
	if (status == "reset") msgContainer.innerHTML = "";
	else {
		var msgText ='<p>';
		if (status == 'error') msgText = "<font color='red'>";
		if (status == 'success') msgText = "<font color='green'>";
		msgText += msg;
		msgText += "</font></p>";
		
		msgContainer.innerHTML = msgText;
	}
}
</script>

<span id="slCategoryPickerMessage"></span>

<form id="slDocumentCategoryPickerForm">
	<table cellspacing="0" cellpadding="0">
	<tr>
		<td>Document Category:</td>
		<td>&nbsp;</td>
		<td>
		<select id="comboDocumentCategory" class="catPicker" onChange="setPicker(dojo.byId('comboDocumentCategory').options[dojo.byId('comboDocumentCategory').selectedIndex].value, 'comboDocumentType');">
			<option value="0" selected="selected"><%=Constants.DOCUMENT_CATEGORY_PICKER_DEFAULT_OPTION_LABEL_ROOT%></option> 
		</select>
		</td>
	</tr>
	<tr>	
		<td>Document Type:</td>
		<td>&nbsp;</td>
		<td>
		<select id="comboDocumentType" class="catPicker" disabled="disabled">
			<option value="0" selected="selected"><%=Constants.CATEGORY_PICKER_DEFAULT_OPTION_LABEL%></option>
		</select>
		</td>
	</tr>		
	<tr>	
		<td>Document Usage:</td>
		<td>&nbsp;</td>
		<td>
		<select id="comboDocumentUsage" class="catPicker">
			<option value="0" selected="selected"><%=Constants.CATEGORY_PICKER_DEFAULT_OPTION_LABEL_PLEASE_SELECT%></option>
		</select>
		</td>
	</tr>
	<tr>	
		<td>Jurisdiction:</td>
		<td>&nbsp;</td>
		<td>
		<select id="comboJurisdiction" class="catPicker">
			<option value="0" selected="selected"><%=Constants.CATEGORY_PICKER_DEFAULT_OPTION_LABEL_PLEASE_SELECT%></option>
		</select>
		</td>
	</tr>
	
	<tr>	
		<td>Audience:</td>
		<td>&nbsp;</td>
		<td>
		<select id="comboAudience" class="catPicker">
			<option value="0" selected="selected"><%=Constants.CATEGORY_PICKER_DEFAULT_OPTION_LABEL_PLEASE_SELECT%></option>
		</select>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>
		<input type="button" value="Save" onClick="setATCategories();">
		&nbsp;
		<input type="button" value="Reset" onClick="resetPicker();">
		</td>
	</tr>
	</table>
</form>
<%}//if(taxonomyName == null)%>