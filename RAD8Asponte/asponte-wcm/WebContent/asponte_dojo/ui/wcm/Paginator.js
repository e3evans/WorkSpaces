dojo.provide("asponte.ui.wcm.Paginator");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.require("dojox.widget.FisheyeLite");

dojo.declare("asponte.ui.wcm.Paginator",
	[dijit._Widget,dijit._Templated],
	{
	animate:false,
	selectedIndex:-1,

	templateString:'<div><ul dojoAttachPoint="pageListNode" class="asponteWcmPaginator"></ul></div>',

	_showAnim:null,
	_components:[],
	_prevImg: dojo.moduleUrl('dojox.image','resources/images/left.png'),
	_nextImg: dojo.moduleUrl('dojox.image','resources/images/right.png'),
	_empty: true,
	
	buildRendering: function(){
		var nodes=dojo.query(".wcmPagingPlaceholder",this.srcNodeRef);
		this._components['_first_']=null;
		this._components['_first_disabled_']=null;
		this._components['_prev_']=null;
		this._components['_prev_disabled_']=null;
		this._components['_next_']=null;
		this._components['_next_disabled_']=null;
		this._components['_last_']=null;
		this._components['_last_disabled_']=null;
		this._components['_continue_']=null;
		this._components['_pages_']=[];
		var _t=this;
		if(nodes){
			nodes.forEach(
				function(node,index,nodeList){
					if(node.firstChild){
						var url=null;
						var txt;
						if(node.firstChild.nodeName.toLowerCase()=='a'){
							url=node.firstChild.href;
							txt=node.firstChild.firstChild.nodeValue;
						}else{
							txt=node.firstChild.nodeValue;
						}
						if(txt){
							if(_t._components[txt]!==undefined){
								_t._components[txt]=url;
							}else{
								_t._components['_pages_'].push({num:txt,url:url});
							}
						}
					}	
				}
			);
		}
		this._empty=!(_t._components['_pages_'].length>1);
		if(this._empty){this.templateString='<div style="height:1px;"></div>';}
		this.inherited(arguments);
		if(!this._empty){this._buildPageList();}
	},

	_buildPageList: function(){
        var html='';
        if(this._components['_first_']!=null){
        }
        if(this._components['_prev_']!=null){
            html+='<li class="asponteWcmPaginatorCtrl paginatorPrev"><a href="'+this._components['_prev_']+'"><img src="'+this._prevImg+'" border="0" alt="Previous page" title="Previous page" /></a></li>';
        }
        if(this._components['_pages_'].length>0){
            for(var i=0;i<this._components['_pages_'].length;i++){
                var obj=this._components['_pages_'][i];
                html+='<li class="asponteWcmPaginatorPage';
                if(obj.url==null){
                    html+=' asponteWcmPaginatorPageSelected';
                    this.selectedIndex=i;
                }
                html+='"><span class="fisheyeTarget">';
                if(obj.url!=null){
                    html+='<a href="'+obj.url+'" title="Go to page '+obj.num+'">';
                }
                html+=obj.num;
                if(obj.url!=null){
                    html+='</a>';
                }
                html+='</span></li>';
            }
        }
        if(this._components['_next_']!=null){
            html+='<li class="asponteWcmPaginatorCtrl paginatorNext"><a href="'+this._components['_next_']+'"><img src="'+this._nextImg+'" border="0" alt="Next page" title="Next page" /></a></li>';
        }
        if(this._components['_last_']!=null){
        }
		this.pageListNode.innerHTML=html;	
	},

	postCreate:function(){
		if(!this._empty){
			this.inherited(arguments);
			this._showAnim=dojo.fadeIn({node:this.domNode});
			dojo.connect(this._showAnim,'onEnd',this,this._connect);
			// TODO::this probably should move to startup
			if(this.animate){
				dojo.style(this.domNode,{'position':'absolute'});
				this._position();
			}
			this._showAnim.play(250);
		}	
	},

	_position:function(){
		var height=10;
		dojo.query("li",this.pageListNode).forEach(
			function(node,i,ary){
				var mb=dojo.marginBox(node);
				if(mb.h>height){height=mb.h;}	
			}
		);
		dojo.style(this.domNode,{'height':height+'px'});
	},

	_connect: function(){
		var _t=this;
		dojo.query('.asponteWcmPaginatorCtrl img',this.domNode).forEach(
			function(node,index,nodeList){
				if(_t.animate){
					new dojox.widget.FisheyeLite({
        				durationOut: 500,
						properties: {
          					height:1.75,
          					width:1.75
        				}
					},node);
				}
			},
			this);
		dojo.query('.asponteWcmPaginatorPage',this.domNode).forEach(
			function(node,index,nodeList){
				if(_t.animate){
					new dojox.widget.FisheyeLite({
        				easeOut: dojox.fx.easing.backInOut,
        				durationOut: 500
					},node);
				}
				if(index==this.selectedIndex){return;}
				dojo.connect(node,'onmouseenter',node,this._pageOver);
				dojo.connect(node,'onmouseleave',node,this._pageOut);
				dojo.connect(node,'onclick',node,this._pageClick);
			},
			this);
	},

	_pageOver: function(){
		dojo.addClass(this,'asponteWcmPaginatorPageOver');
	},

	_pageOut: function(){
		dojo.removeClass(this,'asponteWcmPaginatorPageOver');
	},

	_pageClick: function(){
		var nodeList=dojo.query('a',this);
		if(nodeList.length>0){
			document.location=nodeList[0].href;
		}
	}
});
