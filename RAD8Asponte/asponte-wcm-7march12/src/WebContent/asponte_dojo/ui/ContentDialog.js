dojo.provide("asponte.ui.ContentDialog");

dojo.require('dojo.io.iframe');
dojo.require("dijit.Dialog");
dojo.require('dojox.fx._base');

asponte.ui._ifrLoadCb=
	function(iframe){
		if(typeof(dojo)!="undefined"&&typeof(dijit)!="undefined"){
			if(iframe&&iframe._widgetId){
				var widget=dijit.byId(iframe._widgetId);
				widget._ifrLoad();
			}
		}	
	};

asponte.ui._createMasterIframeDialog=function(){
	asponte.ui._masterIframeDialog=new asponte.ui.IframeDialog({minWidth:330,minHeight:230,maxHeight:605,maxWidth:900,showClose:true});
	asponte.ui._masterIframeDialog.startup();
	return asponte.ui._masterIframeDialog;
};

asponte.ui.showIframeDialog=function(args){
	if(!asponte.ui._masterIframeDialog){asponte.ui._createMasterIframeDialog();}
	if(!asponte.ui._masterIframeDialog.open){
		/*asponte.ui._masterIframeDialog.opener=inst;*/
		asponte.ui._masterIframeDialog.show(args);
	}
};

asponte.ui.hideIframeDialog=function(){
    if(!asponte.ui._masterIframeDialog){asponte.ui._createMasterIframeDialog();}
	if(asponte.ui._masterIframeDialog.open){
    		asponte.ui._masterIframeDialog.hide();
	}
};

asponte.ui._createMasterContentDialog=function(){
	asponte.ui._masterContentDialog=new asponte.ui.ContentDialog({minWidth:330,minHeight:230,maxHeight:605,maxWidth:900,showClose:true});
	asponte.ui._masterContentDialog.startup();
	return asponte.ui._masterContentDialog;
};

asponte.ui.showContentDialog=function(args){
	if(!asponte.ui._masterContentDialog){asponte.ui._createMasterContentDialog();}
	if(!asponte.ui._masterContentDialog.open){
		asponte.ui._masterContentDialog.show(args);
	}
};

asponte.ui.hideContentDialog=function(){
    if(!asponte.ui._masterContentDialog){asponte.ui._createMasterContentDialog();}
	if(asponte.ui._masterContentDialog.open){
    		asponte.ui._masterContentDialog.hide();
	}
};

asponte.ui.showDialog=function(args){
	if(args.standalone){asponte.ui.showIframeDialog(args);}
	else{asponte.ui.showContentDialog(args);}
};

asponte.ui.hideDialog=function(standalone){
	if(standalone){asponte.ui.hideIframeDialog();}
	else{asponte.ui.hideContentDialog();}
};

dojo.declare("asponte.ui._ContentDialog",dijit.Dialog,
{
	// title: string
	title: '',

	// minimum width of iframe
	minWidth:400,

	// minimum height of iframe
	minHeight:400,

	// maximum width of iframe
	maxWidth: 800,

	// maximum height of iframe
	maxHeight: 900,

	// show close flag
	showClose: true,

	// _args: the current arguments (when open)
	_args: null,

	postCreate: function(){
		this.inherited(arguments);
		if(!this.showClose){
			dojo.style(this.closeButtonNode,'visibility','hidden');
		}
	},
	show: function(args){
		this._args=args;
		this.title=this._args.title;
		this.titleNode.innerHTML='Loading...';
		this._load();
		var w,h;
		w=this._args.width||this.minWidth;
		h=this._args.height||this.minHeight;			
		w=(w<this.minWidth?this.minWidth:Math.min(w,this.maxWidth));
		h=(h<this.minHeight?this.minHeight:Math.min(h,this.maxHeight));
		this._setSize(w,h);
		if(!this.open){
			this.inherited(arguments);
		}
		if (this._args.showClose != undefined && !this._args.showClose) {
			dojo.style(this.closeButtonNode,'visibility', 'hidden');
		} else {
			dojo.style(this.closeButtonNode,'visibility', 'visible');
			dojo.style(this.closeButtonNode,'display', 'inline');
		}
	},
	hide: function(){
		this._args=null;
		if(this.open){
			this.inherited(arguments);
		}
	},
	_setSize: function(w,h){
		this._mysize={w:w,h:h};
	}
});

dojo.declare("asponte.ui.ContentDialog",asponte.ui._ContentDialog,
{
	// TODO: Add animation support
	_load:function(){
		// load the content pane
		this.setHref(this._args.url);
	},
	onLoad:function(){
		this.titleNode.innerHTML=this.title;
		dojo.style(this.domNode,{'width':this._mysize.w+'px','height':this._mysize.h+'px'});	
		this._position();
	}
});

dojo.declare("asponte.ui.IframeDialog",asponte.ui._ContentDialog,
{
	// _ifr: the iframe
	_ifr: null,

	// _ifrp: the frame container (parent) element
	_ifrp: null,

	// _ifrLoading: loading flag
	_ifrLoading: false,

	// _ifrLoaded: loaded flag
	_ifrLoaded: false,

	postCreate: function(){
		this.inherited(arguments);
		/*dojo.addClass(this.domNode,'asponteIframeDialog');*/
		var html='<div class="asponteIframeDialogSplash" id="'+this.id+'_ifrp"></div>';
		html+='<div><iframe class="asponteIframeDialogIframe" frameborder="0" name="'+this.id+'_ifr" src="" id="'+this.id+'_ifr" scrolling="auto" ';
		if(dojo.isIE){
			html+='onload="asponte.ui._ifrLoadCb(this);" ';
		}
		html+='></iframe></div>';
		this.setContent(html);
	},

	_checkInit:function(){
		if(!this._ifr){
			this._ifr=dojo.byId(this.id+'_ifr');
			this._ifr._widgetId=this.id;
			if(!dojo.isIE){
				dojo.connect(this._ifr,'onload',this,this._ifrLoad);
			}
			this._ifrp=dojo.byId(this.id+'_ifrp');
		}
	},

	_load: function(args){
		if(this._ifrLoading){return;}
		this._checkInit();	
		// load the iframe
		dojo.style(this._ifrp,{'display':'block','visibility':'visible'});
		this._ifr.style.visibility="hidden";
		this._ifrLoading=true;
		this._ifr.src=this._args.url;
		this._ifrLoaded=false;
	},
	resize:function () {
	        dojo.style(this.domNode, {
	            'width': this._mysize.w+ 'px',
	            'height': this._mysize.h + 'px'
	        });
	        /*dojo.style(this.containerNode, {
	            'width': this._mysize.w + 'px',
	            'height': this._mysize.h + 'px',
	        });*/
	},
	_ifrLoad: function(){
		if(!this._ifr||!this._ifrLoading){return;}
		dojo.style(this._ifrp,{'display':'none','visibility':'hidden'});
		this._showIfr();
		this.resize();
		this._position();
	},
	_showIfr: function(){
		var mb=dojo.marginBox(this.titleBar);
		// these calculations are tundra specific and allow the iframe to fit just within the boundary box
		var w=this._mysize.w-20;
		var h=this._mysize.h-(20+mb.h+1);
		this.titleNode.innerHTML=this.title;
		dojo.style(this._ifr,{'width':w+'px',
				      'height':h+'px',
				      'visibility':''});
		this._ifrLoading=false;
		this._ifrLoaded=true;
	}
});