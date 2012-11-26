dojo.require('dojo.i18n');
dojo.require('asponte.aww.Utils');
dojo.require('asponte.ui.FormDialog');
dojo.requireLocalization('asponte','strings',null,"ROOT");
dojo.provide("asponte.aww.dialogs.DialogFormMixin");
dojo.declare("asponte.aww.dialogs.DialogFormMixin",[],{
	_bundle:null,
	_init:function(){
		this._bundle=dojo.i18n.getLocalization('asponte','strings');
	},
	_handleSuccess: function(response,ioArgs){
//		var result=response.success;
//		var results = response.results;
//		var faults=response.getElementsByTagName('fault');
//		var result={faults:asponte.aww.Utils.faultsToObjects(faults)};
//		var results=response.getElementsByTagName('result');
//		if(results&&results.length>0){
//			dojo.mixin(result,asponte.aww.Utils.resultToObject(results[0]));
//		}
		this._awwCallback(response);		
	},
	_handleError: function(jsExc,ioArgs){
		this._awwCallback({faults:[{severity:2,code:'DIALOG_FORM_POST_IO_ERROR',message:asponte.aww.Utils.getString(this._bundle,'dialog_form_post_io_error',{error:jsExc})}]});		
	},
	_doPost:function(formArgs,url,formid){
		this._awwCallback=formArgs.completionCallback;
		dojo.xhrPost({
			url:url,
			form:formid,
			handleAs: 'json',
			load:dojo.hitch(this,this._handleSuccess),
			error:dojo.hitch(this,this._handleError)
		});		
	},
	ok:function(formArgs){
	},
	cancel:function(){
	}
});
