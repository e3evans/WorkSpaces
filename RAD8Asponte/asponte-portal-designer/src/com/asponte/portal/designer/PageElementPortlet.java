package com.asponte.portal.designer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.eclipse.core.runtime.CoreException;

import com.asponte.commons.Result;
import com.asponte.commons.portal.Utils;
import com.ibm.portal.Locator;
import com.ibm.portal.ModelException;
import com.ibm.portal.Modifiable;
import com.ibm.portal.ObjectID;
import com.ibm.portal.content.ContentModel;
import com.ibm.portal.content.ContentModelController;
import com.ibm.portal.content.ContentNode;
import com.ibm.portal.content.ContentNodeType;
import com.ibm.portal.content.ContentPage;
import com.ibm.portal.content.LayoutControl;
import com.ibm.portal.content.LayoutControlCreationContext;
import com.ibm.portal.content.LayoutModelController;
import com.ibm.portal.content.LayoutNode;
import com.ibm.portal.model.controller.ContentModelControllerHome;
import com.ibm.portal.model.controller.CreationContextBuilderFactory;
import com.ibm.portal.model.controller.PortletModelControllerHome;
import com.ibm.portal.navigation.NavigationNode;
import com.ibm.portal.navigation.NavigationSelectionModel;
import com.ibm.portal.portlet.ModifiablePortletEntity;
import com.ibm.portal.portlet.ModifiablePortletPreferences;
import com.ibm.portal.portlet.ModifiablePortletPreferencesProvider;
import com.ibm.portal.portlet.PortletDefinitionCloningContext;
import com.ibm.portal.portlet.PortletEntityCreationContext;
import com.ibm.portal.portlet.PortletModelController;
import com.ibm.portal.portlet.service.PortletServiceHome;
import com.ibm.portal.portlet.service.PortletServiceUnavailableException;
import com.ibm.portal.portlet.service.model.ContentModelProvider;
import com.ibm.portal.portlet.service.model.NavigationSelectionModelProvider;
import com.ibm.portal.portlet.service.model.PortletModelProvider;
import com.ibm.portal.portletmodel.PortletDefinition;
import com.ibm.portal.portletmodel.PortletEntity;
import com.ibm.portal.portletmodel.PortletModel;
import com.ibm.portal.portletmodel.PortletWindow;
import com.ibm.portal.portletmodel.admin.AdminPortletModel;
import com.ibm.portal.portletmodel.admin.PortletDefinitionList;

/**
 * A sample portlet based on GenericPortlet
 */
public class PageElementPortlet extends GenericPortlet {
	private static final String CLASS_NAME=PageElementPortlet.class.getName();
	private static final Logger LOGGER=Logger.getLogger(CLASS_NAME);
	private static final String JSP_FOLDER    = "/jsp/";    // JSP folder name
	private static final String VIEW_JSP      = "PageElementPortletView";         // JSP file name to be rendered on the view mode
	private static GenericPortlet INSTANCE=null;
	private PortletServiceHome cmHome;
	private PortletServiceHome nsmHome;
	private PortletServiceHome pmHome;
	private ContentModelControllerHome cmcHome;
	private PortletModelControllerHome pmcHome;
	/**
	 * @see javax.portlet.Portlet#init()
	 */
	public void init() throws PortletException{
		super.init();
		final String METHOD_NAME="init";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME);}
		INSTANCE=this;
		try{
			javax.naming.Context ctx = new javax.naming.InitialContext();
			this.cmHome = (PortletServiceHome) ctx.lookup("portletservice/com.ibm.portal.portlet.service.model.ContentModelProvider");
			this.nsmHome = (PortletServiceHome) ctx.lookup("portletservice/com.ibm.portal.portlet.service.model.NavigationSelectionModelProvider");
			this.pmHome = (PortletServiceHome) ctx.lookup("portletservice/com.ibm.portal.portlet.service.model.PortletModelProvider");
			this.cmcHome = (ContentModelControllerHome) ctx.lookup(ContentModelControllerHome.CONTENT_MODEL_CONTROLLER_JNDI_NAME);
		    this.pmcHome = (PortletModelControllerHome) ctx.lookup(PortletModelControllerHome.PORTLET_MODEL_CONTROLLER_JNDI_NAME);
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Looking up extension registry...");}
			org.eclipse.core.runtime.IExtensionRegistry extensionRegistry = (org.eclipse.core.runtime.IExtensionRegistry)ctx.lookup(Constants.EXTENSION_REGISTRY_JNDI_NAME);
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Found extension registry.");}
			getPortletContext().setAttribute(Constants.EXTENSION_REGISTRY, extensionRegistry);
		}catch(NamingException e){
			// If this happens, there is something else majorly wrong
			if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": JNDI lookups failed!",e);}
			throw new PortletException(e);
		}finally{
			if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME);}
		}
	}

	/**
	 * Serve up the <code>view</code> mode.
	 * 
	 * @see javax.portlet.GenericPortlet#doView(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		final String METHOD_NAME="doView";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME,new Object[]{request,response});}
		
		// Set the MIME type for the render response
		if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Settings response type...");}
		response.setContentType(request.getResponseContentType());

		// See if there are action results
		if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Checking for action results...");}
		PortletSession session=request.getPortletSession();
		List<Result> results=(List<Result>)session.getAttribute(Constants.ACTION_RESULTS);
		if(results!=null){
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Forwarding action results...");}
			session.removeAttribute(Constants.ACTION_RESULTS);
			request.setAttribute(Constants.ACTION_RESULTS, results);
		}else{
			try {
				checkFirstRun(request,response);
			} catch (DesignerException ignore) {
				// if first run check fails, not fatal, just log and ignore
			}
		}
		
		// Invoke the JSP to render
		if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Invoking view jsp...");}
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, VIEW_JSP));
		rd.include(request,response);

		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME);}
	}

	/**
	 * Process an action request.
	 * 
	 * @see javax.portlet.Portlet#processAction(javax.portlet.ActionRequest, javax.portlet.ActionResponse)
	 */
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException, java.io.IOException {
		final String METHOD_NAME="processAction";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME,new Object[]{request,response});}			
		List<Result> results=new ArrayList<Result>();
		// Validate params
		String extensionId=Utils.param(request,Constants.EXTENSION_ID);
		if(Utils.empty(extensionId)){
			if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Missing parameter "+Constants.EXTENSION_ID+"!");}
			results.add(new PortletResult(Result.ERROR,Results.MISSING_EXTENSION_ID_PARAM));
		}
		String templateName=Utils.param(request,Constants.TEMPLATE_NAME);
		if(Utils.empty(extensionId)){
			if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Missing parameter "+Constants.EXTENSION_ID+"!");}
			results.add(new PortletResult(Result.ERROR,Results.MISSING_TEMPLATE_NAME_PARAM));
		}
		// Execute action
		if(results.size()==0) {
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": extensionId="+extensionId+", templateName="+templateName+"...");}
		    org.eclipse.core.runtime.IExtensionRegistry extensionRegistry = (org.eclipse.core.runtime.IExtensionRegistry)getPortletContext().getAttribute(Constants.EXTENSION_REGISTRY);
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Looking up extension "+extensionId+"...");}
			org.eclipse.core.runtime.IExtension extension=extensionRegistry.getExtension(extensionId);
			if(extension!=null){
				org.eclipse.core.runtime.IConfigurationElement []configElements=extension.getConfigurationElements();
				org.eclipse.core.runtime.IConfigurationElement config=null;
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Searching for template name "+templateName+"...");}
				for(org.eclipse.core.runtime.IConfigurationElement e:configElements){
					String name=e.getAttribute("name");
					if(name!=null&&name.trim().equalsIgnoreCase(templateName)){
						config=e;
						break;
					}
				}
				if(config!=null){
					Object obj;
					try {
						if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Creating new executable extension for class "+config.getAttribute("class")+"...");}
						obj = config.createExecutableExtension("class");
						if(obj instanceof PageElementProvider){
							PageElementProvider provider=(PageElementProvider)obj;
							String portletId=provider.getPortletId();
							if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Page element portlet ID is "+portletId+"...");}
							Map<String,Object> administratorPrefs=new HashMap<String,Object>();
							Map<String,Object> sharedPrefs=new HashMap<String,Object>();
							if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Building configuration for portlet ID "+portletId+"...");}
							provider.buildPortletConfiguration(request,response,administratorPrefs,sharedPrefs);
							if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Creating page element for portlet ID "+portletId+"...");}
							createPageElement(request,response,portletId,administratorPrefs,sharedPrefs);
						}else{
							if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Invalid page element provider "+obj.getClass().getName()+"!");}
							results.add(new PortletResult(Result.ERROR,Results.INVALID_PAGE_ELEMENT_PROVIDER,new Object[]{obj.getClass().getName(),PageElementProvider.class.getName()}));
						}
					} catch (CoreException e) {
						if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not create the page element provider "+config.getAttribute("class")+"!",e);}
						results.add(new PortletResult(Result.ERROR,Results.CREATE_PAGE_ELEMENT_PROVIDER_FAILED,new Object[]{config.getAttribute("class")}));
					} catch (DesignerException e) {
						Result []rs=e.getResults();
						for(Result r:rs){results.add(r);}
					}
				}
			}else{
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not find the page element extension with id "+extensionId+"!");}
				results.add(new PortletResult(Result.ERROR,Results.PAGE_ELEMENT_EXTENSION_NOT_FOUND,new Object[]{extensionId}));
			}
		}
		// Process result
		if(results.size()>0){
			PortletSession session=request.getPortletSession();
			session.setAttribute(Constants.ACTION_RESULTS, results);
		}
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME);}
	}

	
	private void createPageElement(ActionRequest request,ActionResponse response, 
								   String portletId,
								   Map<String, Object> administratorPrefs,
								   Map<String, Object> sharedPrefs ) throws DesignerException {
		final String METHOD_NAME="createPageElement";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME,new Object[]{request,response,portletId,administratorPrefs,sharedPrefs});}			
		try{
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Loading navigation selection model...");}
			NavigationSelectionModelProvider nsmp = (NavigationSelectionModelProvider) this.nsmHome.getPortletService(NavigationSelectionModelProvider.class);
			NavigationSelectionModel nsm = nsmp.getNavigationSelectionModel(request, response);
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Loading content model and controller...");}
			ContentModelProvider cmp = (ContentModelProvider) this.cmHome.getPortletService(ContentModelProvider.class);
			ContentModel<ContentNode> cm = cmp.getContentModel(request, response);
			ContentModelController cmc = this.cmcHome.getContentModelControllerProvider().createContentModelController(cm);
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Loading portlet model and controller...");}
			PortletModelProvider pmp = (PortletModelProvider) this.pmHome.getPortletService(PortletModelProvider.class);
			PortletModel pm=pmp.getPortletModel(request, response);
			PortletModelController pmc=this.pmcHome.getPortletModelControllerProvider().createPortletModelController(pm);
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Loading admin portlet model...");}
			AdminPortletModel apm = pmp.getAdminPortletModel(request, response);
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Accessing current portlet window...");}
			PortletWindow currPortletWindow=pmp.getCurrentPortletWindow(request);
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Current portlet window ID is "+currPortletWindow+"...");}
			NavigationNode node=(NavigationNode)nsm.getSelectedNode();
			ContentNode cNode=node.getContentNode();
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Selected content node is "+cNode+"...");}
			if(cNode.getContentNodeType()==ContentNodeType.PAGE||cNode.getContentNodeType()==ContentNodeType.STATICPAGE){
				// Get the layout model controller
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Loading layout model controller...");}
				LayoutModelController lmc=cmc.getLayoutModelController((ContentPage)cNode);
				
				// Locate the layout control for our window id
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Locating current window layout node...");}
				Locator locator = lmc.getLocator();
				ObjectID objectId=currPortletWindow.getObjectID();
				LayoutNode lNode=(LayoutNode)locator.findByID(objectId);
				LayoutNode pNode=(LayoutNode)lmc.getParent(lNode);
				Iterator iNodes=lmc.getChildren(pNode);
				LayoutNode nextNode=null;
				while(nextNode==null&&iNodes.hasNext()){
					LayoutNode _node=(LayoutNode)iNodes.next();
					if(_node.getObjectID().equals(lNode.getObjectID())&&iNodes.hasNext()){
						nextNode=(LayoutNode)iNodes.next();
					}
				}
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Current window layout node "+lNode+", parent node "+pNode+", next node "+nextNode+"...");}
				
				// Locate the portlet we want to add
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Loading global portlet definition list...");}
				PortletDefinitionList pdl=apm.getPortletDefinitionList();
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Searching global portlet definition list for portlet ID "+portletId+"...");}
				PortletDefinition pdef=(PortletDefinition)pdl.getLocator().findByUniqueName(portletId);
				
				if(lNode!=null&&pdef!=null){
					CreationContextBuilderFactory builder = CreationContextBuilderFactory.getInstance();
					// If we have new config mode prefs, we are going to clone first
					if(administratorPrefs.size()>0){
						if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Admin prefs set, need to clone and set config mode prefs...");}
						PortletDefinitionCloningContext cloningContext=builder.newPortletDefinitionCloningContext(pdef.getObjectID(), null);
						PortletDefinition portletDefinition=(PortletDefinition)pmc.create(PortletDefinition.class, cloningContext);
						if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Setting admin preferences...");}
						ModifiablePortletPreferences prefs=((ModifiablePortletPreferencesProvider)portletDefinition).getModifiablePortletPreferencesLayer();
						for(String key:administratorPrefs.keySet()){
							Object value=administratorPrefs.get(key);
							if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Setting admin preference "+key+"="+value+"...");}
							if(value instanceof String){
								prefs.setStringValue(key, (String)value);
							}else if(value instanceof String[]){
								prefs.setStringValues(key, (String[])value);
							}
						}
						if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Committing changes...");}
						pmc.commit();
						// use the new portlet definition
						pdef=portletDefinition;
					}
					// Now, create a new portlet entity from the portlet def (the old or new one)
					if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Creating new portlet entity for portlet definition "+pdef+"...");}
					PortletEntityCreationContext portletEntityContext=builder.newPortletEntityCreationContext(pdef.getObjectID(), null);
					ModifiablePortletEntity portletEntity=(ModifiablePortletEntity)pmc.create(PortletEntity.class,portletEntityContext);
					ModifiablePortletPreferences prefs=((ModifiablePortletPreferencesProvider)portletEntity).getModifiablePortletPreferencesLayer();
					if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Setting shared preferences...");}
					for(String key:sharedPrefs.keySet()){
						Object value=sharedPrefs.get(key);
						if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Setting shared preference "+key+"="+value+"...");}
						if(value instanceof String){
							prefs.setStringValue(key, (String)value);
						}else if(value instanceof String[]){
							prefs.setStringValues(key, (String[])value);
						}
					}
					if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Committing changes...");}
					pmc.commit();
					// Now, create the new layout control
					if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Creating new layout node for portlet entity "+portletEntity+"...");}
					LayoutControlCreationContext creationContext = builder.newLayoutControlCreationContext(pdef, portletEntity);
					Modifiable control = lmc.create(LayoutControl.class, creationContext);
					if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Deleting placeholder node...");}
					lmc.delete(lNode);
					if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Inserting actual node...");}
					lmc.insert(control, pNode, nextNode);
					if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Committing changes...");}
					cmc.commit();
				}else{
					// TODO: error couldn't find portlet by unique name
				}
			}
		} catch (ModelException e) {
			if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": A model error occurred while creating the page element for portlet window ID "+request.getWindowID()+" using portlet ID "+portletId+"!",e);}
			throw new DesignerException(new PortletResult(Result.ERROR,Results.CREATE_PAGE_ELEMENT_FAILED_0,new Object[]{request.getWindowID(),portletId}),e);
		} catch (PortletServiceUnavailableException e) {
			if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not create page element for portlet window ID "+request.getWindowID()+" using portlet ID "+portletId+" because one or more of the required model portlet services id unavailable!",e);}
			throw new DesignerException(new PortletResult(Result.ERROR,Results.CREATE_PAGE_ELEMENT_FAILED_1,new Object[]{request.getWindowID(),portletId}),e);
		} finally{
			if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME);}
		}
	}

	private void checkFirstRun(PortletRequest request,PortletResponse response) throws DesignerException{
		final String METHOD_NAME="checkFirstRun";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME,new Object[]{request,response});}			
		try{
			PortletModelProvider pmp = (PortletModelProvider) this.pmHome.getPortletService(PortletModelProvider.class);
			PortletModel pm=pmp.getPortletModel(request, response);
			PortletModelController pmc=this.pmcHome.getPortletModelControllerProvider().createPortletModelController(pm);
			PortletWindow currPortletWindow=pmp.getCurrentPortletWindow(request);
			PortletEntity pe=pm.getPortletEntity(currPortletWindow);
			ModifiablePortletEntity mpe=(ModifiablePortletEntity)pmc.getModifiableNode(pe);
			ModifiablePortletPreferences prefs=mpe.getModifiablePortletPreferencesLayer();
			String firstRun=prefs.getValue(Constants.FIRST_RUN);
			if(firstRun!=null){
				request.setAttribute(Constants.FIRST_RUN,Boolean.FALSE);
			}else{
				prefs.setStringValue(Constants.FIRST_RUN, "false");
				pmc.commit();
				request.setAttribute(Constants.FIRST_RUN,Boolean.TRUE);
			}
		} catch (ModelException e) {
			if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": A model error occurred while check for first run for portlet window ID "+request.getWindowID()+"!",e);}
			throw new DesignerException(new PortletResult(Result.ERROR,Results.CHECK_FIRST_RUN_FAILED_0,new Object[]{request.getWindowID()}),e);
		} catch (PortletServiceUnavailableException e) {
			if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not check for first run for portlet window ID "+request.getWindowID()+" because one or more of the required model portlet services id unavailable!",e);}
			throw new DesignerException(new PortletResult(Result.ERROR,Results.CHECK_FIRST_RUN_FAILED_1,new Object[]{request.getWindowID()}),e);
		} finally{
			if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME);}
		}
	}

	/**
	 * Returns JSP file path.
	 * 
	 * @param request Render request
	 * @param jspFile JSP file name
	 * @return JSP file path
	 */
	private static String getJspFilePath(RenderRequest request, String jspFile) {
		String markup = request.getProperty("wps.markup");
		if( markup == null )
			markup = getMarkup(request.getResponseContentType());
		return JSP_FOLDER + markup + "/" + jspFile + "." + getJspExtension(markup);
	}

	/**
	 * Convert MIME type to markup name.
	 * 
	 * @param contentType MIME type
	 * @return Markup name
	 */
	private static String getMarkup(String contentType) {
		if( "text/vnd.wap.wml".equals(contentType) )
			return "wml";
        else
            return "html";
	}

	/**
	 * Returns the file extension for the JSP file
	 * 
	 * @param markupName Markup name
	 * @return JSP extension
	 */
	private static String getJspExtension(String markupName) {
		return "jsp";
	}
	
	protected static GenericPortlet getInstance(){return INSTANCE;}
}
