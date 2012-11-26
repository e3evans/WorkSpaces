<%@page session="false" contentType="text/html" import="ca.standardlife.wcm.Constants"%>
<%
	String taxonomyName = request.getParameter("taxonomyName");
	if (taxonomyName == null){%>
	
	<font color="red">Error during building the Category Picker; taxonomy name is unknown !</font>
	
<%}else{%>

<script type="text/javascript">
var ccbCategories=''; // variable holds the comma-delimited categories WCM UUID for the AT
var jsTaxonomyName='<%=taxonomyName%>';
 
dojo.addOnLoad(function() {
	populateRootCategory(jsTaxonomyName);
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
	   		var combo = dojo.byId("comboLOB");
	   		combo.options.length = 0;
	   		combo.options[combo.options.length] = new Option("<%=Constants.CATEGORY_PICKER_DEFAULT_OPTION_LABEL_ROOT%>", "0");
	   		for (i in data.catPickerItems) {
	   			combo.options[combo.options.length] = new Option(data.catPickerItems[i].title, data.catPickerItems[i].uuid);
	   		}
	   		if (combo.disabled) combo.disabled = false;
        },
        error: function(error){
    		printPickerMessage(error, "error");
  		}
	}); 
}

function setPicker(senderComboId, selectedVal, targetComboId) {
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
				
				// only 'Mutual Fund' and 'Segregated' Fund have series
				// so somehow we'll have to hide the series combo if those aren't selected
				if (targetComboId == "comboSeries,comboFamily") {
					if (jsTaxonomyName == "<%=Constants.PRODUCT_TAXONOMY_ROOT%>") {
						if ((data.catPickerName == "Mutual Fund") || (data.catPickerName == "Segregated Fund")) {
							dojo.byId("seriesWrapper").style.display = "table-row";
							targetComboId = "comboSeries";
						} else {
							dojo.byId("seriesWrapper").style.display = "none";
							targetComboId = "comboFamily";
						}				
					} else { 
						targetComboId = "comboCategory";
					}
				}
				
				resetOptions(senderComboId, targetComboId);
							
		   		var combo = dojo.byId(targetComboId);
		   		combo.options.length = 0;
		   		combo.options[combo.options.length] = new Option("<%=Constants.CATEGORY_PICKER_DEFAULT_OPTION_LABEL%>", "0");
		   		for (i in data.catPickerItems) {
		   			combo.options[combo.options.length] = new Option(data.catPickerItems[i].title, data.catPickerItems[i].uuid);
		   		}
		   		combo.disabled = false;
	        },
	        error: function(error){
	    		printPickerMessage(error, "error");
	  		}
		}); 
	}
}

function resetOptions(senderComboId, targetComboId) {
	var combos = new Array();
	if (senderComboId == "comboLOB") {
		combos[0] = "comboSeries";
		combos[1] = "comboFamily";
		combos[2] = "comboCategory";
	} else if ( (senderComboId == "comboProductType") && (targetComboId == "comboSeries") ) {
		combos[0] = "comboFamily";
		combos[1]= "comboCategory";
	} else if ( (senderComboId == "comboProductType") && (targetComboId == "comboFamily") ) {
		combos[0] = "comboSeries";
		combos[1] = "comboCategory";
	} else if (senderComboId == "comboSeries")  {
		combos[0] = "comboCategory";
	}	
		
	for (var i=0; i < combos.length; i++) {
		var c = dojo.byId(combos[i]);				
		if (c && c.options.length > 1) {
			c.options.length = 0;
			c.options[c.options.length] = new Option("<%=Constants.CATEGORY_PICKER_DEFAULT_OPTION_LABEL%>", "0");
		}
	}
}

// Sets the categories of the Authoring Template
function setATCategories() {
	
	var atCategory = dojo.byId('dialog_Inputprofile_control_multiSelectCategory');
	if (!atCategory) {
		printPickerMessage("Could not assign categories, authoring template has no profiling enabled.", "error");
		return;
	} else {
		catUUID = dojo.byId('comboCategory').options[dojo.byId('comboCategory').selectedIndex].value;
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
			
		printPickerMessage("Category ["+dojo.byId('comboCategory').options[dojo.byId('comboCategory').selectedIndex].text+"] added.", "success");
	}
}

function resetCategoryPicker() {
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

<form id="slCategoryPickerForm">
	<table cellspacing="0" cellpadding="0">
	<tr>
		<td>LOB:</td>
		<td>&nbsp;</td>
		<td>
		<select id="comboLOB" class="catPicker" onChange="setPicker(this.id, dojo.byId('comboLOB').options[dojo.byId('comboLOB').selectedIndex].value, 'comboProductType');">
			<option value="0" selected="selected"><%=Constants.CATEGORY_PICKER_DEFAULT_OPTION_LABEL_ROOT%></option> 
		</select>
		</td>
	</tr>
	<tr>	
		<td>Product Type:</td>
		<td>&nbsp;</td>
		<td>
		<select id="comboProductType" class="catPicker" disabled="disabled" onChange="setPicker(this.id, dojo.byId('comboProductType').options[dojo.byId('comboProductType').selectedIndex].value, 'comboSeries,comboFamily');">
			<option value="0" selected="selected"><%=Constants.CATEGORY_PICKER_DEFAULT_OPTION_LABEL%></option>
		</select>
		</td>
	</tr>
	
	<%if (taxonomyName.equals(Constants.PRODUCT_TAXONOMY_ROOT)){%>	
	<tr id="seriesWrapper" style="display:none;">	
		<td>Series:</td>
		<td>&nbsp;</td>
		<td>
		<select id="comboSeries" class="catPicker" disabled="disabled" onChange="setPicker(this.id, dojo.byId('comboSeries').options[dojo.byId('comboSeries').selectedIndex].value, 'comboFamily');">
			<option value="0" selected="selected"><%=Constants.CATEGORY_PICKER_DEFAULT_OPTION_LABEL%></option>
		</select>
		</td>
	</tr>
	<tr>	
		<td>Family:</td>
		<td>&nbsp;</td>
		<td>
		<select id="comboFamily" class="catPicker" disabled="disabled" onChange="setPicker(this.id, dojo.byId('comboFamily').options[dojo.byId('comboFamily').selectedIndex].value, 'comboCategory');">
			<option value="0" selected="selected"><%=Constants.CATEGORY_PICKER_DEFAULT_OPTION_LABEL%></option>
		</select>
		</td>
	</tr>
	<%}%>
	<tr>	
		<td><%if (taxonomyName.equals(Constants.PRODUCT_TAXONOMY_ROOT)){%>Product:<%}else{%>Solution:<%}%></td>
		<td>&nbsp;</td>
		<td>
		<select id="comboCategory" class="catPicker" disabled="disabled">
			<option value="0" selected="selected"><%=Constants.CATEGORY_PICKER_DEFAULT_OPTION_LABEL%></option>
		</select>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>
		<input type="button" value="Save" onClick="setATCategories();">
		&nbsp;
		<input type="button" value="Reset" onClick="resetCategoryPicker();">
		</td>
	</tr>
	</table>
</form>
<%}//if(taxonomyName == null)%>