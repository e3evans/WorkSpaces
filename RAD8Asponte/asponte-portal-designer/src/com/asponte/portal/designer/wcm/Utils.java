package com.asponte.portal.designer.wcm;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.asponte.commons.Result;
import com.asponte.portal.designer.PageElementPortlet;
import com.asponte.portal.designer.PortletResult;
import com.ibm.workplace.wcm.api.Document;
import com.ibm.workplace.wcm.api.DocumentId;
import com.ibm.workplace.wcm.api.Workspace;
import com.ibm.workplace.wcm.api.exceptions.AuthorizationException;
import com.ibm.workplace.wcm.api.exceptions.DocumentSaveException;
import com.ibm.workplace.wcm.api.exceptions.DuplicateChildException;

public class Utils {
	private static final String CLASS_NAME=PageElementPortlet.class.getName();
	private static final Logger LOGGER=Logger.getLogger(CLASS_NAME);
	private Utils(){}
	public static final String makeName(String s){
		char []chars=s.toCharArray();
		for(int ii=0;ii<chars.length;ii++){
			char c=chars[ii];
			if( !(c>='0'&&c<='9')&&!(c>='A'&&c<='Z')&&!(c>='a'&&c<='z')&&
				c!=' '&&c!='$'&&c!='-'&&c!='_'&&c!='.'&&c!='!'&&c!='('&&c!=')'&&c!=','){
				chars[ii]='_';
			}
		}
		return new String(chars);
	}
	public static final String getIdrFromId(DocumentId contentIdr) {
		String []ary=contentIdr.toString().split("/");
		String idnum=null;
		if(ary.length>1){
			idnum=ary[2];
		}else{
			idnum=ary[0];
		}		
		return idnum;
	}
	
	public static final boolean saveDocuments(Workspace workspace,List<Document> docs,List<Result> faults) throws DocumentSaveException, AuthorizationException, DuplicateChildException{
		final String METHOD_NAME="saveDocuments";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME, METHOD_NAME);}
		String []saveErrors;
		boolean result=true;
		for(Document doc:docs){
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Saving the document with title '"+doc.getTitle()+"' ("+doc.getName()+") to the workspace...");}
			saveErrors=workspace.save(doc);
			if(saveErrors.length==0){
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Document with title '"+doc.getTitle()+"' ("+doc.getName()+") saved to the workspace with the ID '"+doc.getId()+"'...");}				
			}else{
				for(int ii=0;ii<saveErrors.length;ii++){
					if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": WCM reported the following error while attempting to save the document with the title '"+doc.getTitle()+"' ("+doc.getName()+") and id '"+doc.getId()+"': "+saveErrors[ii]);}
					faults.add(new PortletResult(Result.WARNING,Results.WCM_SAVE_ERROR,new Object[]{doc.getTitle(),doc.getName(),doc.getId(),saveErrors[ii]}));
				}
				result=false;
				break;
			}							
		}
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME, METHOD_NAME,result);}
		return result;
	}

	public static LrpConfig createLrpConfig(Document doc,DocumentId cmpntId,String contextType,String []categories,String []siteAreas,String broadcast,String listen,String title) {
		LrpConfig lrpConfig;
		lrpConfig=new LrpConfig();
		if (doc != null) {
		lrpConfig.setContentIdr(doc.getId());
		}
		lrpConfig.setContentContextType(contextType);
		if(cmpntId!=null){
			lrpConfig.setContentType("COMPONENT");
			lrpConfig.setComponentIdr(cmpntId);
		}else{
			lrpConfig.setContentType("CONTENT");
		}
		if(categories!=null&&categories.length>0){lrpConfig.setCategories(categories);}
		if(siteAreas!=null&&siteAreas.length>0){lrpConfig.setSiteAreas(siteAreas);}
		if(broadcast!=null){lrpConfig.setBroadcastsTo(broadcast);}
		if(listen!=null){lrpConfig.setListensTo(listen);}
		if(title!=null){lrpConfig.setPortletTitle(title);}
		return lrpConfig;
	}	
}
