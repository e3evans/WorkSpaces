<%@page session="false" contentType="text/html" import="ca.standardlife.wcm.Constants"%>

<style type="text/css">
    #associationWrapper {
        width: 700px;
        position: relative;
        clear: both;
    }
	#productAssociation {
        width: 50%;
        position: relative;
        float: left;
		border: 1px solid black;
		margin-top: 10px;
    }
    #associationRulesWrapper {
		display: none;
        width: 49%;
        position: relative;
        background: #FFFF00;
        float: right;
		border: 1px solid black;
		margin-top: 10px;
   }
   #associationRules { margin: 5px; }
   #documenStructureWrapper {
		clear: both;
		margin-top: 20px;
   }
   .ruleEntry { margin-top: 5px; }
   .ruleLabel {}
</style>
	
<script type="text/javascript">
dojo.require("dijit.form.Form");
dojo.require("dijit.form.RadioButton");
dojo.require("dijit.form.Select");
dojo.require("dijit.form.CheckBox");
dojo.require("dijit.form.TextBox");
dojo.require("dijit.form.Button");
dojo.require("dojox.widget.Standby");
dojo.require("dojo.parser");

var standby;
var ruleIndex=0;

dojo.addOnLoad(function(){
	dojo.parser.parse("slDocPickerWrapper");
	
	standby = new dojox.widget.Standby({target: "slDocPickerWrapper"});
	document.body.appendChild(standby.domNode);
	standby.startup();	
	
	// Taxonomy related to products
	retrieveTaxonomyChildren("<%=Constants.PRODUCT_TAXONOMY_ROOT%>", "comboLOB");
	
	// Taxonomy related to documents
	retrieveTaxonomyChildren("<%=Constants.DOCUMENT_TYPES_TAXONOMY_ROOT%>", "comboDocumentCategory");
	retrieveTaxonomyChildren("<%=Constants.DOCUMENT_TAGS_TAXONOMY_ROOT%>", null);
});

dojo.ready(function(){   
	var lblRuleCategory = dojo.byId("labelRuleCategory");
	var ruleParentContainer = dojo.byId("associationRulesWrapper");
	var ruleContainer = dojo.byId("associationRules");
   
	// Hook into the events on the widgets
	
	// Events related to "Document Related To" radios
	dojo.connect(dijit.byId("radioProduct"), "onClick", function(isChecked){
		//console.debug("Selected RADIO is:"+dijit.byId("radioProduct").value);		
		if(isChecked){
			console.debug("radioProduct Is checked");
			lblRuleCategory.innerHTML = "Product";
		}
	});
	dojo.connect(dijit.byId("radioSolution"), "onClick", function(isChecked){		
		if(isChecked){
			console.debug("radioSolution Is checked");
			lblRuleCategory.innerHTML = "Solution";			
		}
	});
	dojo.connect(dijit.byId("radioVAP"), "onClick", function(isChecked){		
		if(isChecked){
			console.debug("radioVAP Is checked");
			lblRuleCategory.innerHTML = "VAP";			
		}
	});
	
	// Events related to product association
	dojo.connect(dijit.byId("comboLOB"), "onChange", function(value){
		retrieveCategoryChildren(value, "comboProductType");
	});		
	dojo.connect(dijit.byId("comboProductType"), "onChange", function(value){
		retrieveCategoryChildren(value, "comboSeries,comboFamily");
	});		
	dojo.connect(dijit.byId("comboSeries"), "onChange", function(value){
		retrieveCategoryChildren(value, "comboFamily");
	});	
	dojo.connect(dijit.byId("comboFamily"), "onChange", function(value){
		retrieveCategoryChildren(value, "comboRuleCategory");
	});		
	
	// Events related to solution association
	
	// Events related to VAP association
	
	// Events related to document structure
	dojo.connect(dijit.byId("comboDocumentCategory"), "onChange", function(value){
		//console.log("Value:"+value);
		retrieveCategoryChildren(value, "comboDocumentType");
	});		
	
	// All button events
	
	// Event to connect the Add Association Rule-button
	dojo.connect(dijit.byId("btnAddRule"), "onClick", function(){		
		// Check if any association widget is selected			
		var selectedWidget;
		var associationPath="";
		var count=0;
		dojo.forEach(["comboLOB","comboProductType","comboSeries","comboFamily","comboRuleCategory"], 
			function(w, i) { 
				var wdgt = dijit.byId(w);
				if (wdgt) {
					//console.debug(wdgt.value); 					
					if (wdgt.value.length > 0) {
						selectedWidget = wdgt;	
						if (i>0 && i >= count) associationPath += " --> ";	
						associationPath += selectedWidget.attr('displayedValue');
						count++;											
					} else
						count--;
				} 	
			}
		);		
		if (selectedWidget) {
			if (ruleParentContainer.style.display == "none" || ruleParentContainer.style.display == "")
				ruleParentContainer.style.display = "block";
			try {
				var checkBox = new dijit.form.CheckBox({
							id: "checkBoxRule_"+selectedWidget.value,
							value: selectedWidget.value,			
							checked: false});				
				var ruleRow = dojo.create("div", {id: "rule_"+selectedWidget.value, className:"ruleEntry"}, ruleContainer);			
				ruleRow.appendChild(checkBox.domNode);			
				dojo.create("span",{innerHTML: "<span></span>"+associationPath, className:"ruleLabel"},ruleRow);		  
				ruleIndex++;	
				//console.debug("New rule index:"+ruleIndex);
			} catch(err) {
				printPickerMessage("This association rule already exists!", "error");
			}
		} else 
			printPickerMessage("Please select an association to add!", "error");
	});		
	// Event to connect the Delete Association Rule-button
	dojo.connect(dijit.byId("btnDeleteRule"), "onClick", function(){
		var checkedFound = false;
		dojo.query("input[type=checkbox]", ruleContainer).forEach(function(chkbx){ 
			//console.debug("checkbox id: " +chkbx.id);
			//console.debug("checkbox value: " +chkbx.value);
			//console.debug("checkbox checked: "+chkbx.checked);
			if (chkbx.checked) {
				checkedFound = true;
				dijit.byId(chkbx.id).destroy();
				ruleContainer.removeChild(dojo.byId("rule_"+chkbx.value));
				ruleIndex--;
			}			
		});	
		if (!checkedFound)
			printPickerMessage("Select an association rule to delete!", "error");
		else if (ruleIndex == 0) 
			ruleParentContainer.style.display = "none";
	});	
	// Event to connect the Reset button
	dojo.connect(dijit.byId("btnReset"), "onClick", function(){
		console.debug("btnReset clicked!");
		resetDocumentCategoryPicker();
	});	
	// Event to connect the Save button
	dojo.connect(dijit.byId("btnSave"), "onClick", function(){
		console.debug("btnSave clicked!");
		setDocumentCategories();
	});	
	
});

function retrieveTaxonomyChildren(taxonomyName, targetWidgetId) {
	printPickerMessage("", "reset");
	standby.show();
	dojo.xhrGet({
		url:"/standard-life-wcm/SLCategoryPickerJson?taxonomyName="+taxonomyName,
		handleAs:"json",
		preventCache: true,
		load: function(data){		
			if (targetWidgetId) {
				populateWidget(targetWidgetId, data);
			} else {				
				for (i in data.catPickerItems) {
					var catName = data.catPickerItems[i].name;
					var catUUID = data.catPickerItems[i].uuid;					
					if (catName == "Usage") {
						retrieveCategoryChildren(catUUID, "comboDocumentUsage");
					} else if (catName == "Jurisdiction") {
						retrieveCategoryChildren(catUUID, "comboJurisdiction");
					} else if (catName == "Audience") {
						retrieveCategoryChildren(catUUID, "checkBoxGroupAudienceWrapper");
					}
				}
			}	   		
        },
        error: function(error, ioargs){
			if (ioargs.xhr.status == 500) {
				printPickerMessage("Couldn't fetch data from SLCategoryPickerJson: "+error, "error");
			} else { printPickerMessage(error, "error");} 
  		}
	}); 
	standby.hide();	
}

function retrieveCategoryChildren(selectedVal, targetWidgetId) {

	printPickerMessage("", "reset");	
	if (selectedVal != "0") {	
		standby.show();		
		dojo.xhrGet({
			url:"/standard-life-wcm/SLCategoryPickerJson?catUUID="+selectedVal, // val is UUID of the item, category names are not unique in the taxonomy model
			handleAs:"json",
			preventCache: true,
			load: function(data){	
				if (targetWidgetId == "comboSeries,comboFamily") {
					if (dijit.byId("radioProduct").value == "Product") {
						if ((data.catPickerName == "Mutual Fund") || (data.catPickerName == "Segregated Fund")) {
							dojo.byId("seriesWrapper").style.display = "table-row";
							targetWidgetId = "comboSeries";
						} else {
							dojo.byId("seriesWrapper").style.display = "none";
							targetWidgetId = "comboFamily";
						}		
					} else {
						targetWidgetId = "comboRuleCategory";
					}
				}
				
				alert("TODO: Implement method resetWidget()"); //resetWidget(senderComboId, targetComboId);
				
				populateWidget(targetWidgetId, data);
	        },
	        error: function(error, ioargs){
				if (ioargs.xhr.status == 500) {
					printPickerMessage("Couldn't fetch data from SLCategoryPickerJson: "+error, "error");
				} else { printPickerMessage(error, "error");} 
			}
		}); 
		standby.hide();
	}
}

function populateWidget(widgetId, data) {	
	if (data.catPickerItems.length == 0) {
		printPickerMessage("Item '"+data.catPickerName+"' has no children! Please select another item.", "error");
		return;
	}	
	var w = dijit.byId(widgetId);
	if (w) {			
		if (w instanceof dijit.form.Select) {		
			if (w.options) w.options = [];
			w.addOption({ label:"<%=Constants.CATEGORY_PICKER_DEFAULT_OPTION_LABEL_PLEASE_SELECT%>", value:0 });				
			for (i in data.catPickerItems) 
				w.addOption({ label: data.catPickerItems[i].title, value: data.catPickerItems[i].uuid });
			if (w.disabled) w.disabled = false;
		} 
	} else { // create the Audience checkboxes
		for (i in data.catPickerItems)  {
			var checkBox = new dijit.form.CheckBox({
					id: "checkBoxAudience_"+i,
					value: data.catPickerItems[i].uuid,			
					checked: false});			
					
			dojo.byId(widgetId).appendChild(checkBox.domNode);			
			dojo.create("label", {innerHTML: data.catPickerItems[i].title, "for":"checkBoxAudience_"+i}, dojo.byId(widgetId));
		}		
	}
}

// Sets the categories of the Authoring Template
function setDocumentCategories() {	
	
	alert ("CHECK LOGIC: setDocumentCategories"); 
	/*
	var atCategory = dojo.byId('dialog_Inputprofile_control_multiSelectCategory');
	if (!atCategory) {
		printPickerMessage("Could not assign categories, authoring template has no profiling enabled.", "error");
		return;
	} else {

		catUUID = dijit.byId('comboAudience').attr('displayedValue');
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
			
		printPickerMessage("Category ["+dijit.byId('comboAudience').attr('displayedValue')+"] added.", "success");
	}
	*/
}

function resetDocumentCategoryPicker() {

	alert ("CHECK LOGIC: resetDocumentCategoryPicker"); 
	/*
	
	dojo.query(".catPicker").forEach(function(c){
		c.options.length = 0;
		c.options[c.options.length] = new Option("<%=Constants.CATEGORY_PICKER_DEFAULT_OPTION_LABEL%>", "0");
		c.disabled = true;
	});
	printPickerMessage("", "reset");
	if (jsTaxonomyName=='<%=Constants.PRODUCT_TAXONOMY_ROOT%>') 
		dojo.byId("seriesWrapper").style.display = "none";
		
	retrieveTaxonomyChildren(jsTaxonomyName);
	*/
}

function printPickerMessage(msg, status) {	
	var msgContainer = dojo.byId("slCategoryPickerMessage");
	if (status == "reset") msgContainer.innerHTML = "";
	else {
		var msgText ="<p>";
		if (status == "error") msgText = "<font color='red'>";
		if (status == "success") msgText = "<font color='green'>";
		msgText += msg;
		msgText += "</font></p>";
		
		msgContainer.innerHTML = msgText;
	}
}
</script>

<span id="slCategoryPickerMessage"></span>

<div id="slDocPickerWrapper">

<div dojoType="dijit.form.Form" id="slDocPickerForm" jsId="slDocPickerForm">	
	<p>Document Related To: 
		<input id="radioProduct" name="radioDocumentRelation" group="radioDocumentRelationGroup" dojoType="dijit.form.RadioButton" value="Product" checked><label for="radioDocRelProduct"> Product</label>
		<input id="radioSolution" name="radioDocumentRelation" group="radioDocumentRelationGroup" dojoType="dijit.form.RadioButton" value="Solution"><label for="radioDocRelSolution"> Solution</label>
		<input id="radioVAP" name="radioDocumentRelation" group="radioDocumentRelationGroup" dojoType="dijit.form.RadioButton" value="Value Added Program"><label for="radioDocRelVAP"> Value Added Program</label> 
	</p>
	
	<!-- START PRO. ASS. -->	
	<div id="associationWrapper">		
		<div id="productAssociation">
			<p><b>Product Association</b></p>
			<table>
			<tr>
				<td><label for="comboLOB">LOB:</label></td>
				<td>&nbsp;</td>
				<td><select id="comboLOB" dojoType="dijit.form.Select"></select></td>
			</tr>
			<tr>
				<td><label for="comboProductType">Product Type:</label></td>
				<td>&nbsp;</td>
				<td><select id="comboProductType" dojoType="dijit.form.Select" disabled="disabled"></select></td>
			</tr>
			<tr id="seriesWrapper" style="display:none;">		
				<td><label for="comboSeries">Series:</label></td>
				<td>&nbsp;</td>
				<td><select id="comboSeries" dojoType="dijit.form.Select" disabled="disabled"></select></td>			
			</tr>
			<tr>	
				<td><label for="comboFamily">Family:</label></td>
				<td>&nbsp;</td>
				<td><select id="comboFamily" dojoType="dijit.form.Select" disabled="disabled"></select></td>			
			</tr>
			<tr>	
				<td><label id="labelRuleCategory" for="comboRuleCategory">Product:</label></td>
				<td>&nbsp;</td>			
				<td><select id="comboRuleCategory" dojoType="dijit.form.Select" disabled="disabled"></select></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td align="right"><button id="btnAddRule" dojoType="dijit.form.Button" type="button">Add Association Rule</button></td>
			</tr>
			</table>
		</div>
		<div id="associationRulesWrapper">
			<p><b><u>Association Rules</u></b></p>
			
			<div id="associationRules"></div>
			
			<button id="btnDeleteRule" dojoType="dijit.form.Button" type="button">Delete Association Rule</button>
		</div>	
	</div>
	
	<div id="documenStructureWrapper">
	<p><b>Document Structure</b></p>
	<table id="slDocPickerDocumentWrapper" cellspacing="0" cellpadding="0">
	<tr>
		<td><label for="comboDocumentCategory">Document Category:</label></td>
		<td>&nbsp;</td>
		<td><select id="comboDocumentCategory" dojoType="dijit.form.Select"></select></td>
	</tr>
	<tr>	
		<td><label for="comboDocumentType">Document Type:</label></td>
		<td>&nbsp;</td>
		<td><select id="comboDocumentType" dojoType="dijit.form.Select" disabled="disabled"></select></td>		
	</tr>		
	<tr>	
		<td><label for="comboDocumentUsage">Document Usage:</label></td>
		<td>&nbsp;</td>
		<td><select id="comboDocumentUsage" dojoType="dijit.form.Select"></select></td>		
	</tr>
	<tr>	
		<td><label for="comboJurisdiction">Jurisdiction:</label></td>
		<td>&nbsp;</td>
		<td><select id="comboJurisdiction" dojoType="dijit.form.Select"></select></td>
	</tr>	
	<tr>	
		<td><label for="checkBoxGroupAudience">Audience:</label></td>
		<td>&nbsp;</td>
		<td><div id="checkBoxGroupAudienceWrapper"></div></td>
	</tr>		
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>
			<button id="btnReset" dojoType="dijit.form.Button" type="button">Cancel</button>
			&nbsp;
			<button id="btnSave" dojoType="dijit.form.Button" type="button">Save</button>    
		</td>
	</tr>
	</table>
	</div>
</form>
</div>

<!-- 
http://www.sitepen.com/blog/2010/07/12/dive-into-dijit/

http://livedocs.dojotoolkit.org/dijit/form

http://dojotoolkit.org/reference-guide/1.7/dijit/form/Form.html

Include categoryPicker.jsp ?

http://livedocs.dojotoolkit.org/dijit/form 

myForm.attr('value').infoUrgent

var container = dojo.query('div')[13];
dojo.query('input', container).attr('disabled', 'disabled');

// find all widget-dom-nodes in a div, convert them to dijit reference:
var widgets = dojo.query("[widgetId]", someDiv).map(dijit.byNode);
// now iterate over that array making each disabled in dijit-land:
dojo.forEach(widgets, function(w){ w.attr("disabled", "disabled"); }


http://dojotoolkit.org/reference-guide/1.7/dijit/form/CheckBox.html#dijit-form-checkbox

var checkBox = new dijit.form.CheckBox({
    name: "checkBox",
    value: "agreed",
    checked: false,
    onChange: function(b){ alert('onChange called with parameter = ' + b + ', and widget value = ' + checkBox.get('value') ); }
  }, "checkBox");
 
 
 //// 
 		// target = 'comboDocumentType';
		//s2.options = [];

		// Logic to get the data, from retrieveCategoryChildren, do the rest here below
		
		//s2.addOption({ label: "new option1", value: 1 }

		
     /* s2.addOption([{ 
        label: "new option1", value: 1
      },
      { 
        label: "new option2", value: 2
      },
      { 
        label: "new option3", value: 3
      }]);
    }); */
	
	/*
	var s1 = new dojox.form.DropDownSelect();
var s2 = new dojox.form.DropDownSelect();
s1.onChange(function() {
  s2.addOption(new Option("text","value"));
});
	*/ 
 //
 
 http://wbex.ru/index.php/JavaScript_DHTML/Dojo_toolkit/CheckBox
 
 Standby widget:
 
	http://dojotoolkit.org/reference-guide/1.7/dojox/widget/Standby.html
 
	
NATIVE JS WAY:
	http://stackoverflow.com/questions/866239/creating-the-checkbox-dynamically-using-javascript
	
	http://bytes.com/topic/javascript/answers/701647-creating-checkboxes-dynamically-document-createelement 	
	
wpsadmin
Asp0nteWPS
	
-->