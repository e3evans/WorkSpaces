<%@page session="false" contentType="text/html" import="ca.standardlife.wcm.Constants"%>

<script type="text/javascript">
var ccbCategories=''; // variable holds the comma-delimited categories WCM UUID for the AT
 
dojo.addOnLoad(function() {
	populateRootCategory('<%=Constants.PRODUCT_TAXONOMY_ROOT%>');
});
	
function populateRootCategory(taxonomyName) {
	dojo.xhrGet({
		url:"/standard-life-wcm/SLCustomCategoryBrowserJson?tax="+taxonomyName,
		handleAs:"json",
		preventCache: true,
		load: function(data){
			if (data.ccbItems.length == 0) {
				alert("populateRootCategory(): Item '"+data.ccbId+"' has no children!");
				return;
			}
	   		var combo = dojo.byId("comboLOB");
	   		combo.options.length = 0;
	   		combo.options[combo.options.length] = new Option("<%=Constants.CATEGORY_BROWSER_DEFAULT_OPTION_LABEL_ROOT%>", "0");
	   		for (i in data.ccbItems) {
	   			combo.options[combo.options.length] = new Option(data.ccbItems[i].title, data.ccbItems[i].uuid);
	   		}
	   		if (combo.disabled) combo.disabled = false;
        },
        error: function(error){
    		alert("populateRootCategory(): Couldn't fetch data from servlet SLCustomCategoryBrowserJson: "+error);
  		}
	}); 
}

function setOptions(selectedVal, targetComboName) {
	dojo.xhrGet({
		url:"/standard-life-wcm/SLCustomCategoryBrowserJson?catUUID="+selectedVal, // val is UUID of the item, category names are not unique in the taxonomy
		handleAs:"json",
		preventCache: true,
		load: function(data){
			if (data.ccbItems.length == 0) {
				alert("setOptions(): Item '"+data.ccbId+"' has no children!");
				return;
			}
			
			// only 'Mutual Fund' and 'Segregated' Fund have series
			// so somehow we'll have to hide the series combo if those aren't selected
			if (targetComboName=="comboSeries,comboFamily") {
				if ((data.ccbId == "Mutual Fund") || (data.ccbId == "Segregated Fund")) {
					dojo.byId("seriesWrapper").style.display = "table-row";
					targetComboName = "comboSeries";
				} else {
					dojo.byId("seriesWrapper").style.display = "none";
					targetComboName = "comboFamily";
				}				
			}
			
	   		var combo = dojo.byId(targetComboName);
	   		combo.options.length = 0;
	   		combo.options[combo.options.length] = new Option("<%=Constants.CATEGORY_BROWSER_DEFAULT_OPTION_LABEL%>", "0");
	   		for (i in data.ccbItems) {
	   			combo.options[combo.options.length] = new Option(data.ccbItems[i].title, data.ccbItems[i].uuid);
	   		}
	   		combo.disabled = false;
        },
        error: function(error){
    		alert("setOptions(): Couldn't fetch data from servlet SLCustomCategoryBrowserJson: "+error);
  		}
	}); 
}

function setCCBCategories() {
	catUUID = dojo.byId('comboProduct').options[dojo.byId('comboProduct').selectedIndex].value;
	if (catUUID == 0) {
		alert("Please browse through the entire category browser !");
		return;
	}
	
	if (ccbCategories.length > 0) ccbCategories +=",";
	ccbCategories += catUUID;
	
	//console.log("Cats for the AT are:"+ccbCategories);
	
	// Add the categoriesd to the Authoring Template
	var atCategory = dojo.byId('dialog_Inputprofile_control_multiSelectCategory');
	if (atCategory) {
		if (atCategory.value.length > 0)
			atCategory.value += "," + ccbCategories;
		else
			atCategory.value = ccbCategories;
	}
}

function resetOptions() {
	dojo.query(".ccbCombo").forEach(function(c){
		c.options.length = 0;
		c.options[c.options.length] = new Option("<%=Constants.CATEGORY_BROWSER_DEFAULT_OPTION_LABEL%>", "0");
		c.disabled = true;
	});
	dojo.byId("seriesWrapper").style.display = "none";
	populateRootCategory('<%=Constants.PRODUCT_TAXONOMY_ROOT%>');
}
</script>

<form id="myform" name="myform">
	<table cellspacing="0" cellpadding="0">
	<tr>
		<td>LOB:</td>
		<td>&nbsp;</td>
		<td>
		<select id="comboLOB" class="ccbCombo" onChange="setOptions(dojo.byId('comboLOB').options[dojo.byId('comboLOB').selectedIndex].value, 'comboProductType');">
			<option value="0" selected="selected"><%=Constants.CATEGORY_BROWSER_DEFAULT_OPTION_LABEL_ROOT%></option> 
		</select>
		</td>
	</tr>
	<tr>	
		<td>Product Type:</td>
		<td>&nbsp;</td>
		<td>
		<select id="comboProductType" class="ccbCombo" disabled="disabled" onChange="setOptions(dojo.byId('comboProductType').options[dojo.byId('comboProductType').selectedIndex].value, 'comboSeries,comboFamily');">
			<option value="0" selected="selected"><%=Constants.CATEGORY_BROWSER_DEFAULT_OPTION_LABEL%></option>
		</select>
		</td>
	</tr>
	<tr id="seriesWrapper" style="display:none;">	
		<td>Series:</td>
		<td>&nbsp;</td>
		<td>
		<select id="comboSeries" class="ccbCombo" disabled="disabled" onChange="setOptions(dojo.byId('comboSeries').options[dojo.byId('comboSeries').selectedIndex].value, 'comboFamily');">
			<option value="0" selected="selected"><%=Constants.CATEGORY_BROWSER_DEFAULT_OPTION_LABEL%></option>
		</select>
		</td>
	</tr>
	<tr>	
		<td>Family:</td>
		<td>&nbsp;</td>
		<td>
		<select id="comboFamily" class="ccbCombo" disabled="disabled" onChange="setOptions(dojo.byId('comboFamily').options[dojo.byId('comboFamily').selectedIndex].value, 'comboProduct');">
			<option value="0" selected="selected"><%=Constants.CATEGORY_BROWSER_DEFAULT_OPTION_LABEL%></option>
		</select>
		</td>
	</tr>
	<tr>	
		<td>Product:</td>
		<td>&nbsp;</td>
		<td>
		<select id="comboProduct" class="ccbCombo" disabled="disabled">
			<option value="0" selected="selected"><%=Constants.CATEGORY_BROWSER_DEFAULT_OPTION_LABEL%></option>
		</select>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>
		<input type="button" value="Save" onClick="setCCBCategories();">
		&nbsp;
		<input type="button" value="Reset" onClick="resetOptions();">
		</td>
	</tr>
	</table>
</form>