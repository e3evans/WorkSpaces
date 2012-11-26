dojo.provide("asponte.ui.wcm.EventList");

dojo.require('dijit._Widget');
dojo.require('dijit._Templated');
dojo.require('dijit.layout.StackContainer');

dojo.declare('asponte.ui.wcm.EventList',[dijit._Widget,dijit._Templated],
{
   store: '',
   maxItemsPerPage: 10,
   templateString:'<div><div style="float:right;" dojoattachpoint="pager">Pages: <button dojoattachpoint="prevButton" dojoType="dijit.form.Button">&lt;</button><span dojoattachpoint="stackController"></span><button dojoattachpoint="nextButton" dojoType="dijit.form.Button">&gt;</button></div><div dojoattachpoint="stack"></div></div>',
   widgetsInTemplate: true,
   _stack:null,
   _stackController:null,
   _dataStore:null,
   _allEventDates:null,
   _NO_RESULTS:{events:[]},
   postCreate: function(){
      this.inherited(arguments);
      this._dataStore=window[this.store];
      this._buildRendering();
   },
   startup: function(){
      this._stack.startup();
	  if(this._stackController){
      	this._stackController.startup();
	  }
   },
   _buildRendering: function(){
	this._stack=new dijit.layout.StackContainer({
	            style: "",
	            id: this.id+"_stack"
	        },
	        this.stack);
    var eventDetailsCount=0;
	if(this._dataStore){
		eventDetailsCount=this._dataStore.items.length;
		if (eventDetailsCount > 0) {
		  var j = 0;
		  var start = 0;
		  var end = -1;
		  var s=''
		  for (var i=0; i < eventDetailsCount / this.maxItemsPerPage; i++) {
		    end += this.maxItemsPerPage;
		    for (j=start; j <= end && j<eventDetailsCount; j++) {
		      var item=this._dataStore.items[j];
		      s+='<div>';
			  s+='<table cellspacing="0" cellpadding="0" border="0">';
			  s+='<tr><td>';
			  s+="<img alt='Event image' border='0' width='100' height='100' src='"+item.image+"' />";
			  s+='</td><td width="50px;">&nbsp;</td><td valign="top">';
			  s+='<b>'+item.title+'</b>';
			  s+='<br>'+item.displayDate+' '+item.start+' '+item.end;
			  s+='<br><i>'+item.location+'</i>';
			  s+='<p>'+item.summary+'</p>';
			  s+='</td></tr></table>';
		      s+='</div>';        
    	 	} 
 			var cp=new dijit.layout.ContentPane({title:i+1,content:s});
    		this._stack.addChild(cp);
   		 	start += this.maxItemsPerPage;
		    s='';
		  }	
		}
	}
 	if(eventDetailsCount>0){
		if(eventDetailsCount>this.maxItemsPerPage){
			dojo.connect(this.prevButton,'onClick',this,function(){this._stack.back();});
			dojo.connect(this.nextButton,'onClick',this,function(){this._stack.forward();});
			this._stackController=new dijit.layout.StackController({
        	    containerId: this.id+"_stack"
    		},
			this.stackController);
		}else{
			dojo.style(this.pager,'visibility','hidden');
		}	
	}else{
		var cp=new dijit.layout.ContentPane({title:i+1,content:'No events'});
    	this._stack.addChild(cp);
		dojo.style(this.pager,'visibility','hidden');
	}
   }
});
