dojo.require('dojo.parser');
dojo.require('dijit.Dialog');
dojo.require('dojox.widget.Standby');
dojo.declare("asponte.aww.Wizard",[dijit.Dialog],{
	namespace:'',
	showOnStartup:true,
	_fixSizes:false,
	_standby:null,
	_saveButton:null,
	_cancelButton:null,
	_inputValid:false,
	_tabber:null,
	postCreate: function(){
		this.inherited(arguments);
		this._standby = new dojox.widget.Standby({
            target: this.namespace+'AwwWizardDialog',
            id: this.namespace+'AwwWizardStandby'
        });		
        document.body.appendChild(this._standby.domNode);
	},
	startup: function(){
		this.inherited(arguments);
        this._standby.startup();
        this._saveButton=dijit.byId(this.namespace+"AwwWizardSave");
        this._cancelButton=dijit.byId(this.namespace+"AwwWizardCancel");
        this._tabber=dijit.byId(this.namespace+'AwwWizardTabber');
        dojo.connect(this._tabber,'selectChild',this,this._onTabChange);
        // immediately show the dialog
		if(this.showOnStartup){this.show();}
	},
	show: function(){
		this.inherited(arguments);
		this._updateButtons();
		// close the portlet palette if it is open
    	if(window.wptheme_InlinePalettesContainer){
            dojo.cookie('portalFlyoutIsOpen','0');
    		if(window.wptheme_InlinePalettesContainer.containerStatus){
    			window.wptheme_InlinePalettesContainer.toggle();
    		}
    	}        
	},
	finish: function(){
		this._standby.show();
		var tabber=this._tabber;
		var childWidget=tabber.selectedChildWidget;
		var formName=childWidget.formName;
		var extensionId=childWidget.extensionId;
		var templateName=childWidget.templateName;
		if(formName&&extensionId&&templateName){
			var form=dojo.byId(formName);
			form.extensionId.value=extensionId;
			form.templateName.value=templateName;
			form.submit();
		}		
	},
	cancel: function(){
		this.hide();
	},
	setInputValid: function(b){
		this._inputValid=b;
		this._updateButtons();
	},
	_updateButtons: function(){
		this._saveButton.attr('disabled',!this._inputValid);
	},
	_onTabChange:function(child){
		var formName=child.formName;
		var form=dijit.byId(formName);
		this.setInputValid(form.isValid());		
	}
});