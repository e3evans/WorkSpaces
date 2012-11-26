dojo.require('asponte.ui.FormDialog');
dojo.require('dojo.cache');
dojo.require('asponte.aww.dialogs.DialogFormMixin');
dojo.provide("asponte.aww.dialogs.AddDocCollectionDialogForm");
dojo.declare("asponte.aww.dialogs.AddDocCollectionDialogForm",[dijit._Widget,dijit._Templated,asponte.aww.dialogs.DialogFormMixin],{
    templateString: dojo.cache(new dojo._Url(document.baseURI,'/aww/jsp/html/forms/addDocCollectionForm.jsp')),
	postCreate: function(){
		this.inherited(arguments);
		this._init();
	},
	ok: function(formArgs){
		var form=dojo.byId('AwwAddDocCollectionForm');
		form.catalogId.value=formArgs.catalogId;
		this._doPost(formArgs,form.action,'AwwAddDocCollectionForm');
	}
});
if(!asponte.aww.dialogs.addDocCollectionDialog){
	// TODO: NON-NLS
	asponte.aww.dialogs.addDocCollectionDialog=new asponte.ui.FormDialog({title:'Add Document Collection',formType:'asponte.aww.dialogs.AddDocCollectionDialogForm'});
}
