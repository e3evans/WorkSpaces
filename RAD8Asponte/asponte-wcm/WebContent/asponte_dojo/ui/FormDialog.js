dojo.require('dijit.Dialog');
dojo.require('asponte.ui.DialogForm');

dojo.provide("asponte.ui.FormDialog");
dojo.declare("asponte.ui.FormDialog",[dijit.Dialog],{
	formType:'',

	_form:null,
	_formArgs:null,

	postCreate:function(){
		this.inherited(arguments);       
		dojo.addClass(this.domNode,'asponteFormDialog');
		this.connect(this,"onLoad",this._connectForm);
    	this.setContent('<div dojoType="asponte.ui.DialogForm" formType="'+this.formType+'"></div>');
	},

	_connectForm: function(){
		// find our form and connect it up
        var childNodes = dojo.query(">", this.containerNode || this.domNode),
            childWidgets = childNodes.filter("[widgetId]");
        if(childNodes.length == 1 && childWidgets.length == 1){
			// this should be our form
			var form=dijit.byNode(childWidgets[0]);
			if(form&&form.setDialog){
				form.setDialog(this);
				this._form=form;
			}
		}
	},
	
	show: function(formArgs){
		this._formArgs=(formArgs||{});
		this.inherited(arguments);
	}
});
