dojo.provide("asponte.ui.ContentLightbox");

dojo.require('dijit._Widget');
dojo.require('dijit.Dialog');
dojo.require('dijit.layout.ContentPane');
dojo.require('dojox.fx');

dojo.declare("asponte.ui.ContentLightbox",
	[dijit._Widget],{
	// group: String
	//		Grouping images in a page with similar tags will provide a 'slideshow' like grouping of images
	group: "",

	// title: String 
	//		A string of text to be shown in the Lightbox beneath the image (empty if using a store)
	title: "",

	// href; String
	//		Link to image to use for this Lightbox node (empty if using a store).
	href: "",

	// duration: Integer
	//		Generic time in MS to adjust the feel of widget. could possibly add various 
	//		durations for the various actions (dialog fadein, sizeing, img fadein ...) 
	duration: 500,

	useIframe: false,

	// _allowPassthru: Boolean
	//		Privately set this to disable/enable natural link of anchor tags
	_allowPassthru: false,
	
	// _attachedDialg: dojox.image._LightboxDialog
	//		The pointer to the global lightbox dialog for this widget
	_attachedDialog: null, // try to share a single underlay per page?

    // width for this link
    width:-1,

    // height for this link
    height:-1,
   
	// hideOnShow
	hideOnShow:false,

	// scrollable:
	scrollable: true,
 
    startup: function(){
		this.inherited(arguments);
        // setup an attachment to the masterDialog (or create the masterDialog)
        var tmp = dijit.byId('contentLightboxDialog');
        if(tmp){
            this._attachedDialog = tmp;
        }else{
            // this is the first instance to start, so we make the masterDialog
            this._attachedDialog = new asponte.ui.ContentLightboxDialog({ id: "contentLightboxDialog" });
            this._attachedDialog.startup();
        }
        this._addSelf();
        this.connect(this.domNode, "onclick", "_handleClick");
    },

    _addSelf: function(){
        this._attachedDialog.addHref({
            href: this.href,
            title: this.title,
            width: this.width,
            height: this.height,
            scrollable: this.scrollable,
	    useIframe: this.useIframe
        },this.group||null);
    },

	_handleClick: function(/* Event */e){
		// summary: Handle the click on the link 
		if(!this._allowPassthru){ e.preventDefault(); }
		else{ return; }
		this.show();
	},

	show: function(){
		// summary: Show the Lightbox with this instance as the starting point
		this._attachedDialog.show(this);
	},

	disable: function(){
		// summary: Disables event clobbering and dialog, and follows natural link
		this._allowPassthru = true;
	},

	enable: function(){
		// summary: Enables the dialog (prevents default link)
		this._allowPassthru = false; 
	}

 });

dojo.declare("asponte.ui.ContentLightboxDialog",
	dijit.Dialog,{
	// title: String
	// 		The current title, read from object passed to show() 
	title: "",

	// FIXME: implement titleTemplate

	// inGroup: Array
	//		Array of objects. this is populated by from the JSON object _groups, and
	//		should not be populate manually. it is a placeholder for the currently 
	//		showing group of images in this master dialog
	inGroup: null,

	// hrefUrl: String
	//		The src="" attribute of our imageNode (can be null at statup)
	hrefUrl: "",
		
	// errorMessage: String
	// 		The text to display when an unreachable image is linked
	errorMessage: "Content not found.",

	// adjust: Boolean
	//		If true, ensure the image always stays within the viewport
	//		more difficult than necessary to disable, but enabled by default
	//		seems sane in most use cases.
	adjust: true,

	// an object of arrays, each array (of objects) being a unique 'group'
	_groups: { XnoGroupX: [] },

	// errorImg: Url
	//		Path to the image used when a 404 is encountered
	errorImg: dojo.moduleUrl("dojox.image","resources/images/warning.png"),		

	// widgetsInTemplate: boolean
	//		don't change this value for this widget
	widgetsInTemplate: true,

	// privates:
	_contentReady: false,
	_blankImg: dojo.moduleUrl("dojo","resources/blank.gif"),

	// animation holders:
	_loadingAnim:null, 
	_showImageAnim: null,
	_showNavAnim: null,
	_animConnects: [],

	_fixSizes: false,
	_width:500,
	_height:400,
	
	templateString:"<div class=\"asponteContentLightbox\" dojoAttachPoint=\"containerNode\">\n\t<div style=\"position:relative\">\n\t\t<div dojoAttachPoint=\"contentContainer\" class=\"asponteContentLightboxContainer\">\n\t\t\t<div dojoType='dijit.layout.ContentPane' dojoAttachPoint=\"contentNode\" class=\"asponteContentLightboxImage\"></div>\n\t\t\t<div class=\"asponteContentLightboxFooter\" dojoAttachPoint=\"titleNode\">\n\t\t\t\t<div class=\"dijitInline asponteContentLightboxClose\" dojoAttachPoint=\"closeNode\"></div>\n\t\t\t\t<div class=\"dijitInline asponteContentLightboxNext\" dojoAttachPoint=\"nextNode\"></div>\t\n\t\t\t\t<div class=\"dijitInline asponteContentLightboxPrev\" dojoAttachPoint=\"prevNode\"></div>\n\n\t\t\t\t<div class=\"asponteContentLightboxText\"><span dojoAttachPoint=\"textNode\">${title}</span><span dojoAttachPoint=\"groupCount\" class=\"asponteContentLightboxGroupText\"></span></div>\n\t\t\t</div>\n\t\t</div>\t\n\t\t\n</div></div>\n",

	startup: function(){
		// summary: Add some extra event handlers, and startup our superclass.

		this.inherited(arguments);
		this.connect(document.documentElement,"onkeypress","_handleKey");
		this.connect(window,"onresize","_position"); 
		this.connect(this.nextNode, "onclick", "_nextContent");
		this.connect(this.prevNode, "onclick", "_prevContent");
		this.connect(this.closeNode, "onclick", "hide");
		dojo.connect(this.contentNode,'onLoad',this,this._loadHref);
		this._makeAnims();
		this._vp = dijit.getViewport();
	
		with(this.contentNode){
			errorMessage=this.errorMessage;
		}	
	},

	show: function(/* Object */groupData){
		// summary: Show the Master Dialog. Starts the chain of events to show
		//		an image in the dialog, including showing the dialog if it is
		//		not already visible
		//
		// groupData: Object
		//		needs href and title attributes. the values for this image.
	
		var _t = this; // size

		dojo.addClass(_t.containerNode,'loading');
	
		// we only need to call dijit.Dialog.show() if we're not already open.
		if(!_t.open){ _t.inherited(arguments); }
        else{
            /* If we are switching groups, reset stuff */
            if(!groupData.group||groupData==="XnoGroupX"||
               (_t._gdata&&_t._gdata!=groupData.group)){
                    this.inGroup = null;
                    this._positionIndex = null;
            }
		}

		if(groupData.hideOnShow){
			dojo.style(_t.contentNode.domNode,"visibility","hidden");
		}else{
			dojo.style(_t.contentNode.domNode,"opacity","0"); 
		}
		dojo.style(_t.titleNode,"opacity","0");

		_t._contentReady = false;
		_t._url=groupData.href;
		_t._gdata=groupData;
	
		if((groupData.group && groupData !== "XnoGroupX") || _t.inGroup){ 
			if(!_t.inGroup){ 
				_t.inGroup = _t._groups[(groupData.group)];
				// determine where we were or are in the show 
				dojo.forEach(_t.inGroup,function(g,i){
					if(g.href == groupData.href){
						_t._positionIndex = i;
					}
				},_t);
			}
			if(!_t._positionIndex){
				_t._positionIndex=0;
				_t._url = _t.inGroup[_t._positionIndex].href;
			}
			// FIXME: implement titleTemplate
			_t.groupCount.innerHTML = " (" +(_t._positionIndex+1) +" of "+_t.inGroup.length+")";
			_t.prevNode.style.visibility = "visible";
			_t.nextNode.style.visibility = "visible";
		}else{
			// single images don't have buttons, or counters:
			_t.groupCount.innerHTML = "";
			_t.prevNode.style.visibility = "hidden";
			_t.nextNode.style.visibility = "hidden";
		}
		_t.textNode.innerHTML = groupData.title;
        _t._gdata=groupData;
       
		if(!_t._gdata.useIframe){this.contentNode.setHref(_t._url);}
		else{this._loadIframe(_t._url);}

		if(groupData.hideOnShow){this.contentNode.refresh();}
	},

    _loadHref: function(){
        this._contentReady = true;
        var w=this._width,h=this._height;
        // process overrides
        if(this._gdata.width>0){w=this._gdata.width;}
        if(this._gdata.height>0){h=this._gdata.height;}
	this.resizeTo({w:w,h:h});
    },

    _loadIframe: function(url){
        this._contentReady = true;
        var w=this._width,h=this._height;
        // process overrides
        if(this._gdata.width>0){w=this._gdata.width;}
        if(this._gdata.height>0){h=this._gdata.height;}
	this.contentNode.setContent('<iframe class="asponteIframeDialogIframe" frameborder="0" name="'+this.id+'_ifr" src="'+url+'" id="'+this.id+'_ifr" scrolling="auto" width="'+w+'" height="'+h+'"></iframe>');
	this.resizeTo({w:w,h:h});
    },

	_nextContent: function(){
		// summary: Load next image in group
		if(!this.inGroup){ return; }
		if(this._positionIndex+1<this.inGroup.length){
			this._positionIndex++;
		}else{
			this._positionIndex = 0;
		}
		this._loadContent();
	},

	_prevContent: function(){
		// summary: Load previous image in group
		if(this.inGroup){ 
			if(this._positionIndex == 0){
				this._positionIndex = this.inGroup.length - 1;
			}else{
				this._positionIndex--;
			}
			this._loadContent();
		}
	},

	_loadContent: function(){
		this._loadingAnim.play(1);
	},

    _prepNodes: function(){
	this._contentReady = false;
        this.show({
            href: this.inGroup[this._positionIndex].href,
            title: this.inGroup[this._positionIndex].title,
            width: this.inGroup[this._positionIndex].width,
            height: this.inGroup[this._positionIndex].height,
            scrollable: this.inGroup[this._positionIndex].scrollable,
            useIframe: this.inGroup[this._positionIndex].useIframe,
            group:this._gdata.group
        });
    },

	_showContent: function(){
		var w=this._mysize.w,h=this._mysize.h;
		var style={'width':w+'px','height':h+'px'};
		if(this._gdata.scrollable){
			style.overflowX='auto';
			style.overflowY='auto';
		}else{
			style.overflow='hidden';
		}
        dojo.style(this.contentNode.domNode,style);
		dojo.removeClass(this.containerNode,'loading');
		if(this._gdata.hideOnShow){dojo.style(this.contentNode.domNode,{'visibility':'visible'});}
		this._showImageAnim.play(1);
	},

	_showNav: function(){
		// summary: Fade in the footer, and setup our connections.
		this._showNavAnim.play(1);
	},

	hide: function(){
		// summary: Hide the Master Lightbox
		dojo.fadeOut({node:this.titleNode, duration:200,
			onEnd: dojo.hitch(this,function(){
				// refs #5112 - if you _don't_ change the .src, safari will _never_ fire onload for this image
				//this.imgNode.src = this._blankImg; 
			}) 
		}).play(5); 
		this.inherited(arguments);
		this.contentNode.setContent('');
		this.inGroup = null;
		this._positionIndex = null;
	},

	addHref: function(child, group){
		// summary: Add an image to this Master Lightbox
		//
		// child: Object
		//		The image information to add.
		//		href: String - link to image (required)
		// 		title: String - title to display
		//
		// group: String?
		//		attach to group of similar tag or null for individual image instance
		var g = group;
		if(!child.href){ return; }
		if(g){ 	
			if(!this._groups[g]){
				this._groups[g] = [];				
			}
			this._groups[g].push(child); 
		}else{ this._groups["XnoGroupX"].push(child); }
	},

	_handleKey: function(/* Event */e){
		// summary: Handle keyboard navigation internally
		if(!this.open){ return; }

		var dk = dojo.keys;
		var key = (e.charCode == dk.SPACE ? dk.SPACE : e.keyCode);
		switch(key){
			
			case dk.ESCAPE: this.hide(); break;

			case dk.DOWN_ARROW:
			case dk.RIGHT_ARROW:
			case 78: // key "n"
				this._nextImage(); break;

			case dk.UP_ARROW:
			case dk.LEFT_ARROW:
			case 80: // key "p" 
				this._prevImage(); break;
		}
	},
	
	_position: function(/* Event */e){
		// summary: we want to know the viewport size any time it changes
		this.inherited(arguments);
		this._vp = dijit.getViewport();
	},

    resizeTo: function(size){
        // summary: Resize our dialog container, and fire _showImage
		var _sizeAnim = dojox.fx.sizeTo({ 
            node: this.containerNode,
            duration: 1000,
            width: size.w, 
            height: size.h + 30 
        }); 
		this._mysize=size;    
        this.connect(_sizeAnim,"onEnd","_showContent");
        _sizeAnim.play(15);
    },
	
	_makeAnims: function(){
		// summary: make and cleanup animation and animation connections
		
		dojo.forEach(this._animConnects,dojo.disconnect);
		this._animConnects = [];
		this._showImageAnim = dojo.fadeIn({
				node: this.contentNode.domNode,
				duration: 350
			});
		this._animConnects.push(dojo.connect(this._showImageAnim, "onEnd", this, "_showNav"));
		this._loadingAnim = dojo.fx.combine([
				dojo.fadeOut({ node:this.contentNode.domNode, duration:175 }),
				dojo.fadeOut({ node:this.titleNode, duration:175 })
			]);
		this._animConnects.push(dojo.connect(this._loadingAnim, "onEnd", this, "_prepNodes"));
		this._showNavAnim = dojo.fadeIn({ node: this.titleNode, duration:225 });
	}
});
