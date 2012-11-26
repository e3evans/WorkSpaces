dojo.require('dojo.string');
dojo.require('dojox.data.dom');

dojo.provide("asponte.aww.Utils");

asponte.aww.Utils.faultsToObjects=
	function(u){
		var faults=[];
		if(u&&u.length>0){
			for(var ii=0;ii<u.length;ii++){
				var faultNode=u[ii];
				var fault={};
				fault.severity=faultNode.getAttribute('severity');
				fault.code=faultNode.getAttribute('code');
				fault.message=dojox.data.dom.textContent(faultNode);
				faults[faults.length]=fault;
			}
		}
		return faults;
	};
	
asponte.aww.Utils.lrpConfigToObject=
	function(node){
		var result={};
		result.id=node.getAttribute('contentId');
		result.componentId=node.getAttribute('componentId');
		result.type=node.getAttribute('type');
		result.contextType=node.getAttribute('contextType');
		result.broadcastsTo=node.getAttribute('broadcastsTo');
		result.listensTo=node.getAttribute('listensTo');
		return result;
	};
	
asponte.aww.Utils.resultToObject=
	function(node){
		result={data:node};
		var success=node.getAttribute('success');
		result.success=(success&&success=='true');	
		return result;
	};
	
asponte.aww.Utils.getString= 
	function(bundle,key,inserts){
      	var str = bundle[key];
      	return (inserts)? dojo.string.substitute(str,inserts):str;
    };