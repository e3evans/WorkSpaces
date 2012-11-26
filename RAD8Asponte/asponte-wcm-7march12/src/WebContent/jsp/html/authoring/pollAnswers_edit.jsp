<%@ page session="true" contentType="text/html"
    import="java.util.*,com.ibm.workplace.wcm.api.*"%><%@ taglib
    uri="/WEB-INF/tld/wcm.tld" prefix="wcm"
    %><%@page import="com.ibm.workplace.wcm.api.authoring.CustomItemBean"%><%

    CustomItemBean customItem =(CustomItemBean) request.getAttribute("CustomItemBean");
    customItem.setSubmitFunctionName("wcmpoll_save");
    String fvalue = (String)customItem.getFieldValue();
    String []values=null;
    System.out.println(fvalue);
    if(fvalue!=null&&(fvalue=fvalue.trim()).length()>0){
        values=fvalue.split(",");
    }
%>
<div id="pollAnswers">
<style type="text/css">
.wcmPollAnswersList{
  margin:0px;
  padding:4px 10px 16px 4px;
}
.wcmPollAnswersList li{
  list-style-position:inside;
  list-style-type:none;
}
.wcmPollAnswersList li a{
  margin:0px 10px;
  text-decoration:none;
}
</style>
<span class="wcmRequiredField">*</span>&nbsp;<span class="lbl1"><span style="display: none;">Required Field</span><span class="fieldLabel">Answers</span></span><br />
<span class="wcmHelpText">Use the controls provided to enter the valid answers for this poll.</span>
<ul id="<%=customItem.getFieldName()%>_answer_list" class="wcmPollAnswersList">
<%
if(values!=null&&values.length>0){
for(int iii=0;iii<values.length;iii++){
    String value=values[iii];
 %>
<li><input type="radio" name="<%=customItem.getFieldName()%>_answer" value="<%=value%>" id="<%=customItem.getFieldName()%>_answer_<%=iii%>" /><label for="<%=customItem.getFieldName()%>_answer_<%=iii%>"><%=value%></label><a href="javascript:void(0);" onclick="wcmpoll_delans(this);">delete</a></li>
<%
}
}
%>
</ul>
<button dojoType="dijit.form.Button" type="button" id="<%=customItem.getFieldName()%>_add">Add answer</button>
<div dojoType="dijit.Dialog" title="Add Answer" id="<%=customItem.getFieldName()%>_dialog">
  <div>
    <label for="<%=customItem.getFieldName()%>_dialog_text">Enter the answer text:</label>
    <input type="text" id="<%=customItem.getFieldName()%>_dialog_text" name="<%=customItem.getFieldName()%>_dialog_text" size="40" />
  </div>
  <div>
    <button dojoType="dijit.form.Button" type="button" id="<%=customItem.getFieldName()%>_dialog_ok">OK</button>
    <button dojoType="dijit.form.Button" type="button" id="<%=customItem.getFieldName()%>_dialog_cancel">Cancel</button>
  </div>
</div>
</div>
<script language='Javascript'>
function wcmpoll_save() {
    var s='';
    var e;
    var q=0;
	var rc=false;
    dojo.query('input','pollAnswers').forEach(
        function(item,idx,ary){
            if(q++>0){s+=',';}
            s+=item.value;
        }
    );
	if(q<2){
		alert("You must specify at least 2 answers!");
	}else if(q>10){
		alert("You must not specify more than 10 answers!");
	}else{
    	document.getElementById("<%=customItem.getFieldName()%>").value = s;
		rc=true;
	}
	return rc;
}
function wcmpoll_addans(evt){
    var e=dojo.byId('<%=customItem.getFieldName()%>_dialog_text');
    if(e&&e.value){
        var val=dojo.trim(e.value);
        if(val.length>0){
            var f=dojo.byId('<%=customItem.getFieldName()%>_answer_list');
              if(f){
				var idx=dojo.query('li',f).length;
                var li=document.createElement('li');
                f.appendChild(li);
                li.innerHTML='<input type="radio" name="<%=customItem.getFieldName()%>_answer_'+idx+'" value="'+val+'" id="<%=customItem.getFieldName()%>_answer_'+idx+'" /><label for="<%=customItem.getFieldName()%>_answer_'+idx+'">'+val+'</label><a href="javascript:void(0)" onclick="wcmpoll_delans(this);">delete</a>';
            }
        }else{
            // $$NON-NLS$$
            alert('Please enter a valid value!');
        }
    }
    dijit.byId('<%=customItem.getFieldName()%>_dialog').hide();
}
function wcmpoll_delans(anchor){
	var e=dojo.byId('<%=customItem.getFieldName()%>_answer_list');
    var f=anchor.parentNode;
	e.removeChild(f);
}
function wcmpoll_init() {
    dojo.addClass(dojo.body(),'tundra');
	dojo.parser.parse('pollAnswers');
    var e=dojo.byId('<%=customItem.getFieldName()%>_add');
    var g=dojo.byId('<%=customItem.getFieldName()%>_dialog_ok');
    var h=dojo.byId('<%=customItem.getFieldName()%>_dialog_cancel');
    if(e&&g&&h){
        dojo.connect(e,'onclick',e,function(){dijit.byId('<%=customItem.getFieldName()%>_dialog').show();});
        dojo.connect(g,'onclick',null,wcmpoll_addans);
        dojo.connect(h,'onclick',null,function(){dijit.byId('<%=customItem.getFieldName()%>_dialog').hide();});
    }
}
dojo.require('dijit.Dialog');
dojo.require('dijit.form.Button');
dojo.addOnLoad(wcmpoll_init);
</script>
