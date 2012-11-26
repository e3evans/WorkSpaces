package com.asponte.wcm.widgets;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.jsp.PageContext;

import com.asponte.commons.Result;
import com.ibm.workplace.wcm.api.Content;
import com.ibm.workplace.wcm.api.Document;
import com.ibm.workplace.wcm.api.DocumentId;
import com.ibm.workplace.wcm.api.DocumentIdIterator;
import com.ibm.workplace.wcm.api.DocumentLibrary;
import com.ibm.workplace.wcm.api.LibraryComponent;
import com.ibm.workplace.wcm.api.RenderingContext;
import com.ibm.workplace.wcm.api.SiteArea;
import com.ibm.workplace.wcm.api.Workspace;
import com.ibm.workplace.wcm.api.exceptions.AuthorizationException;
import com.ibm.workplace.wcm.api.exceptions.DocumentSaveException;
import com.ibm.workplace.wcm.api.exceptions.DuplicateChildException;
import com.ibm.workplace.wcm.api.exceptions.WCMException;

/**
 * 
 * TODO: This class has a bunch of duplicate code from Utils in the portal designer.  Would be nice to factor this
 * into the commons stuff at some point.  But because of the error message, I am too lazy to move it right now
 */
public class Utils {
	private static final String CLASS_NAME=Utils.class.getName();
	private static final Logger LOGGER=Logger.getLogger(CLASS_NAME);
	public static void renderCmpnt(PageContext pageContext,Workspace wkspc,DocumentLibrary lib,Content content,SiteArea parent,String cmpntName,Map params) throws WCMException,IOException{
		wkspc.setCurrentDocumentLibrary(lib);
		DocumentIdIterator itr=wkspc.findComponentByName(cmpntName);
		LibraryComponent cmpnt=null;
		if(itr.hasNext()){
			cmpnt=(LibraryComponent)wkspc.getById(itr.nextId(),true);
			RenderingContext renderingContext2=wkspc.createRenderingContext(pageContext.getRequest(),pageContext.getResponse(),params);
			renderingContext2.setRenderedContent(content,parent);
			pageContext.getOut().print(wkspc.render(renderingContext2,cmpnt));			
		}
	}
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
					// TODO: COPY this Result and message from designer to here
					faults.add(new ActionResult(Result.WARNING,Results.WCM_SAVE_ERROR,new Object[]{doc.getTitle(),doc.getName(),doc.getId(),saveErrors[ii]}));
				}
				result=false;
				break;
			}							
		}
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME, METHOD_NAME,result);}
		return result;
	}
}
