dojo.require('dojo.parser');
dojo.require('dijit._Widget');
dojo.require('dijit._Templated');
dojo.require('dijit.form.Button');

dojo.provide("asponte.ui.DialogForm");
dojo.declare("asponte.ui.DialogForm",[dijit._Widget,dijit._Templated],{

	// formType: string
	// Dojo type of the dialog form
	formType: '',
	
	// templateString: string
	// The dojo template string
	templateString: '<div dojoAttachPoint="dialogFormContainerNode"><div dojoAttachPoint="dialogFormNode" dojoType="${formType}"></div><div style="margin:6px 0px;"><button dojoAttachPoint="okButton" dojoType="dijit.form.Button" type="button">OK</button><button dojoAttachPoint="cancelButton" dojoType="dijit.form.Button" type="button">Cancel</button></div></div>',

	widgetsInTemplate: true,

	_dialog: null,

	postCreate:function(){
		this.inherited(arguments);
		this.connect(this.okButton,"onClick",this.ok);
		this.connect(this.cancelButton,"onClick",this.cancel);
	},

	setDialog: function(dialog){
		this._dialog=dialog;
	},

	ok: function(){		
		var args=dojo.clone(this._dialog._formArgs);
		args.completionCallback=dojo.hitch(this,this._okComplete);
		this.dialogFormNode.ok(args);
	},

	cancel: function(){
		this.dialogFormNode.cancel();
		if(this._dialog){this._dialog.onCancel();}
	},
	
	_okComplete: function(obj){
		/* TODO: Don't think clone is necessary here */
		//var args=dojo.clone(this._dialog._formArgs);
		var args=this._dialog._formArgs;
		var done=true;
		if(obj.success){
			if(args.returnUrl){document.location=args.returnUrl;}
		}else if(obj.results){
			/* TODO: FIXME - display all messages in a box in the standby */
			alert(obj.results[0].msg);
			done=false;			
		}
		if(done){
			this._dialog.onExecute();
		}
	}
});
