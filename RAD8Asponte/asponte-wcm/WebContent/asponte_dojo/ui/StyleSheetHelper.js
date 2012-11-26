dojo.provide("asponte.ui.StyleSheetHelper");

dojo.declare("asponte.ui.StyleSheetHelper",null,
	{
	styleSheets: {},
	scripts: {},
	registerStyleSheet: function(id,url){
		// This isn't really thread-safe but there is no "thread" support in JS
		// and dojo does this in other places too.
		if(!this.styleSheets[id]){
			this.styleSheets[id]=true;
			if(document.createStyleSheet) {
				document.createStyleSheet(url);
			}else{
				var styles = "@import url('"+url+"');";
				var newSS=document.createElement('style');
				newSS.type='text/css';
				var newText=document.createTextNode(styles);
				newSS.appendChild(newText);
				document.getElementsByTagName("head")[0].appendChild(newSS);
			}
		}
	},
	registerScript: function(id,url){
		// This isn't really thread-safe but there is no "thread" support in JS
		// and dojo does this in other places too.
		// SECURITY NOTE: This has the potential to open us up to XSS if we source something we don't trust.
		// We use it for jquery support which lives in our AWW EAR which we more or less trust or have locked down.
		// The alternative is simple - customers put it in your theme so this won't have to be invoked.
		if(!this.scripts[id]){
			this.scripts[id]=true;
			var newSS=document.createElement('script');
			newSS.type='text/javascript';
			newSS.src=url;
			document.getElementsByTagName("head")[0].appendChild(newSS);
		}
	}	
});

asponte.ui.registerStyleSheet=function(id,url){
	if(!asponte.ui._styleSheetHelper){
		asponte.ui._styleSheetHelper=new asponte.ui.StyleSheetHelper();
	}
	asponte.ui._styleSheetHelper.registerStyleSheet(id,url);
}
asponte.ui.registerScript=function(id,url){
	if(!asponte.ui._styleSheetHelper){
		asponte.ui._styleSheetHelper=new asponte.ui.StyleSheetHelper();
	}
	asponte.ui._styleSheetHelper.registerScript(id,url);
}
