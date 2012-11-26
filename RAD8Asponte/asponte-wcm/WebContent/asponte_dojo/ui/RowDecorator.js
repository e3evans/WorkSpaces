dojo.provide("asponte.ui.RowDecorator");
dojo.require("dijit._Widget");

dojo.declare("asponte.ui.RowDecorator",
	[dijit._Widget],
	{
		postCreate: function(){
	        dojo.query('tbody tr',this.domNode).forEach(
	        	function(node,idx,arr){
	        		if(idx%2>0){dojo.addClass(node,"odd");}
	        	}
	        );
		}	
	});
