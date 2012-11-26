package com.asponte.portal.designer.builders;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.asponte.commons.Result;
import com.asponte.portal.designer.DesignerException;
import com.asponte.portal.designer.PortletResult;
import com.asponte.portal.designer.wcm.Constants;
import com.asponte.portal.designer.wcm.Results;
import com.asponte.portal.designer.wcm.WidgetContributor;
import com.asponte.portal.designer.wcm.WidgetFactory;
import com.ibm.workplace.wcm.api.ChildPosition;
import com.ibm.workplace.wcm.api.Content;
import com.ibm.workplace.wcm.api.Document;
import com.ibm.workplace.wcm.api.DocumentId;
import com.ibm.workplace.wcm.api.DocumentIdIterator;
import com.ibm.workplace.wcm.api.DocumentLibrary;
import com.ibm.workplace.wcm.api.DocumentTypes;
import com.ibm.workplace.wcm.api.LibraryComponent;
import com.ibm.workplace.wcm.api.ReferenceComponent;
import com.ibm.workplace.wcm.api.Workspace;
import com.ibm.workplace.wcm.api.exceptions.AuthorizationException;
import com.ibm.workplace.wcm.api.exceptions.ComponentNotFoundException;
import com.ibm.workplace.wcm.api.exceptions.DocumentCreationException;
import com.ibm.workplace.wcm.api.exceptions.DocumentRetrievalException;
import com.ibm.workplace.wcm.api.exceptions.IllegalDocumentTypeException;
import com.ibm.workplace.wcm.api.exceptions.IllegalTypeChangeException;
import com.ibm.workplace.wcm.api.exceptions.OperationFailedException;
import com.ibm.workplace.wcm.api.exceptions.PropertyRetrievalException;

public class FeaturedNewsContributor implements WidgetContributor {

	private static final String CLASS_NAME=WcmLrpBuilder.class.getName();
	private static final Logger LOGGER=Logger.getLogger(CLASS_NAME);
	
	public FeaturedNewsContributor() {
	}

	public void contentCreated(WidgetFactory f,
							   Properties config, 
							   String templateId,
							   Workspace workspace,
							   DocumentLibrary awwDesignLib, 
							   DocumentLibrary awwContentLib,
							   Properties args,
							   List<Document> docs) throws DesignerException {
		final String METHOD_NAME="createWidget";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME);}
		String atName="Widget";
		String cmpntName="Headlines 1C Min All HTML";		
		Content widget=(Content)docs.get(docs.size()-1);
		String title=widget.getTitle();
		DocumentId pid=widget.getDirectParent();
		DocumentId atId;
		DocumentId cmpntId;
		DocumentId wfId=null;
		workspace.setCurrentDocumentLibrary(awwDesignLib);
		if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Locating the authoring template '"+atName+"'...");}
		DocumentIdIterator itr=workspace.findByName(DocumentTypes.AuthoringTemplate,atName);
		if(itr.hasNext()){
			atId=itr.nextId();
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Locating the library component '"+cmpntName+"'...");}
			itr=workspace.findComponentByName(cmpntName);
			if(itr.hasNext()){
				cmpntId=itr.nextId();
				try {
					Content content;
					content=workspace.createContent(atId,pid,null,ChildPosition.END);
					content.setName(widget.getName()+"_ALL");
					content.setTitle(widget.getTitle()+"_ALL");
					wfId=widget.getWorkflowId();
					if(wfId!=null){
						if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Setting content workflow to '"+wfId.getName()+"'...");}
						content.setWorkflowId(wfId);
						content.setEffectiveDate(new Date());
					}
					if(content.hasComponent(Constants.WRAPPER_CMPNT_NAME)){
						try {
							if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Setting component reference to '"+cmpntId+"'...");}
							ReferenceComponent ref=(ReferenceComponent)content.getComponent(Constants.WRAPPER_CMPNT_NAME);
							LibraryComponent cmpnt=(LibraryComponent)workspace.getById(cmpntId,true);
							ref.setComponentRef(cmpnt);
							content.setComponent(Constants.WRAPPER_CMPNT_NAME, ref);
						}catch(DocumentRetrievalException e){
							if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not access the component with id '"+cmpntId+"'!",e);}
							throw new DesignerException(new PortletResult(Result.ERROR,Results.FEATURED_NEWS_ALL_WRAPPER_CMPNT_ACCESS_EXCEPTION,new Object[] {cmpntId,title},e));
						}catch(AuthorizationException e){
							if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": User is not authorized to access the component with id '"+cmpntId+"'!",e);}
							throw new DesignerException(new PortletResult(Result.ERROR,Results.FEATURED_NEWS_ALL_WRAPPER_CMPNT_AUTH_EXCEPTION,new Object[] {cmpntId,title},e));
						}catch(ComponentNotFoundException ignore){
							// We are using hasComponent to check first so this should never happen
							assert false;
						}catch(IllegalTypeChangeException ignore){
							// We are setting the same component we get so this should never happen
							assert false;
						}					
					}
					docs.add(content);
				}catch(DocumentCreationException e){
					if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not create the content item for the 'See All' view for the featured news with the title '"+title+"'!",e);}
					throw new DesignerException(new PortletResult(Result.ERROR,Results.FEATURED_NEWS_ALL_DOCUMENT_CREATE_EXCEPTION,new Object[] {title},e));
				}catch(AuthorizationException e){
					if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": User is not authorized to create the content item for the 'See All' view for the featured news with the title '"+title+"'!",e);}
					throw new DesignerException(new PortletResult(Result.ERROR,Results.FEATURED_NEWS_ALL_DOCUMENT_CREATE_ACCESS_EXCEPTION,new Object[] {title},e));
				}catch (PropertyRetrievalException e){
					if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not access the workflow property for the featured news with the title '"+title+"'!",e);}
					throw new DesignerException(new PortletResult(Result.ERROR,Results.FEATURED_NEWS_ALL_DOCUMENT_PARENT_DEFAULT_EXCEPTION,new Object[] {title},e));
				}catch (OperationFailedException e) {
					if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": The workflow could not be set on the content item for the 'See All' link for the featured news with the title '"+title+"'!",e);}
					throw new DesignerException(new PortletResult(Result.ERROR,Results.FEATURED_NEWS_ALL_DOCUMENT_SET_WORKFLOW_EXCEPTION,new Object[] {wfId.getName(),title},e));
				}catch(IllegalDocumentTypeException ignore){
					assert false;	
				}	
			}else{
				if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Could not find the library component "+cmpntName+"!");}
				throw new DesignerException(new PortletResult(Result.ERROR,Results.FEATURED_NEWS_ALL_CMPNT_NOT_FOUND_EXCEPTION,new Object[] {cmpntName,title,awwDesignLib.getName()}));
			}
		}else{
			if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Could not find the authoring template "+atName+"!");}
			throw new DesignerException(new PortletResult(Result.ERROR,Results.FEATURED_NEWS_ALL_AT_NOT_FOUND_EXCEPTION,new Object[] {atName,title,awwDesignLib.getName()}));
		}			
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME);}
	}
	
	public void contentSaved(WidgetFactory f,
			   				   Properties config, 
			   				   String templateId,
			   				   Workspace workspace,
			   				   DocumentLibrary awwDesignLib, 
			   				   DocumentLibrary awwContentLib,
			   				   Properties args,
			   				   List<Document> docs) throws DesignerException {	
		
	}
}

