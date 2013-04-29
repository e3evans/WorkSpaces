/**
 * 
 */
package pagecode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;

import com.ibm.faces.component.html.HtmlScriptCollector;

import com.ibm.websphere.models.config.process.Component;
import com.ibm.workplace.wcm.api.Content;
import com.ibm.workplace.wcm.api.ContentComponent;
import com.ibm.workplace.wcm.api.DocumentId;
import com.ibm.workplace.wcm.api.DocumentIdIterator;
import com.ibm.workplace.wcm.api.DocumentLibrary;
import com.ibm.workplace.wcm.api.DocumentLibraryACL;
import com.ibm.workplace.wcm.api.DocumentTypes;
import com.ibm.workplace.wcm.api.LibraryResourceType;
import com.ibm.workplace.wcm.api.LibraryResourceTypes;
import com.ibm.workplace.wcm.api.Repository;
import com.ibm.workplace.wcm.api.RichTextComponent;
import com.ibm.workplace.wcm.api.WCM_API;
import com.ibm.workplace.wcm.api.Workspace;
import com.ibm.workplace.wcm.api.exceptions.AuthorizationException;
import com.ibm.workplace.wcm.api.exceptions.ComponentNotFoundException;
import com.ibm.workplace.wcm.api.exceptions.DocumentRetrievalException;
import com.ibm.workplace.wcm.api.exceptions.OperationFailedException;
import com.ibm.workplace.wcm.api.exceptions.ServiceNotAvailableException;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.model.SelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlInputText;
import com.ibm.faces.component.html.HtmlCommandExButton;
import javax.faces.component.UISelectItem;
import javax.faces.component.html.HtmlOutputText;



/**
 * @author Eric Evans
 *
 */
public class WcmCrawlerView extends PageCodeBase {
	
	private List libraryItems = null;
	
	protected Repository repository;
	
	protected HtmlScriptCollector scriptCollector1;

	protected HtmlForm wcmCrawlerSelector;

	protected HtmlSelectOneMenu LibrarySelector;

	protected UISelectItems selectItems1;

	protected HtmlInputText searchtext;

	protected HtmlCommandExButton search;

	protected UISelectItem selectItem1;

	protected HtmlOutputText fi;

	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}

	@SuppressWarnings("unchecked")
	public void onPageLoadBegin(FacesContext facescontext) {

		Workspace theWorkspace =null;
		PortletRequest pr = (PortletRequest)facescontext.getExternalContext().getRequest();

		try {
			theWorkspace = WCM_API.getRepository().getWorkspace(pr.getUserPrincipal());
			if (theWorkspace!=null){

				if (getSessionScope().get("libItems")==null){
					Iterator<DocumentLibrary> ws = theWorkspace.getDocumentLibraries();
					List<SelectItem> libraryList = new ArrayList<SelectItem>();
					while (ws.hasNext()){
						DocumentLibrary dl = ws.next();
						SelectItem si = new SelectItem();
						si.setValue(dl.getName());
						si.setLabel(dl.getTitle());
						si.setDescription(dl.getName());
						libraryList.add(si);
						theWorkspace.setCurrentDocumentLibrary(dl);
					}
					getSessionScope().put("libItems", libraryList);
					
				}
			}
		} catch (ServiceNotAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OperationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	protected HtmlForm getWcmCrawlerSelector() {
		if (wcmCrawlerSelector == null) {
			wcmCrawlerSelector = (HtmlForm) findComponentInRoot("wcmCrawlerSelector");
		}
		return wcmCrawlerSelector;
	}

	protected HtmlSelectOneMenu getLibrarySelector() {
		if (LibrarySelector == null) {
			LibrarySelector = (HtmlSelectOneMenu) findComponentInRoot("LibrarySelector");
		}
		return LibrarySelector;
	}

	protected UISelectItems getSelectItems1() {
		if (selectItems1 == null) {
			selectItems1 = (UISelectItems) findComponentInRoot("selectItems1");
		}
		return selectItems1;
	}

	public void setLibraryItems(List libraryItems) {
		this.libraryItems = libraryItems;
	}

	public List getLibraryItems() {
		
		return libraryItems;
	}

	protected HtmlInputText getSearchtext() {
		if (searchtext == null) {
			searchtext = (HtmlInputText) findComponentInRoot("searchtext");
		}
		return searchtext;
	}

	protected HtmlCommandExButton getSearch() {
		if (search == null) {
			search = (HtmlCommandExButton) findComponentInRoot("search");
		}
		return search;
	}

	public String doSearchAction() {
		// Type Java code that runs when the component is clicked
		System.out.println("BEGIN SEARCH!!");
		Workspace theWorkspace =null;
		PortletRequest pr = (PortletRequest)getFacesContext().getExternalContext().getRequest();
		StringBuffer sb = new StringBuffer();
		try {
			theWorkspace = WCM_API.getRepository().getWorkspace(pr.getUserPrincipal());
			if (theWorkspace!=null){
				DocumentLibrary dl = theWorkspace.getDocumentLibrary(getLibrarySelector().getValue().toString());
				
				boolean proceed = false;
				if (dl!=null) proceed=true;
				if (proceed){
					System.out.println("PROCEEDING....");
					theWorkspace.setCurrentDocumentLibrary(dl);
					DocumentIdIterator dii = theWorkspace.findByType(DocumentTypes.Content);
					
					while (dii.hasNext()){
						DocumentId did = (DocumentId)dii.next();
						Content content = (Content) theWorkspace.getById(did);
//						System.out.println("REVIEWING DOC..."+content.getName());
						String []components = content.getComponentNames();
						for (int i=0;i<components.length;i++){
							ContentComponent component = (ContentComponent)content.getComponentByReference(components[i]);
							if (component.getClass().toString().indexOf("RichTextComponent")>0){
								RichTextComponent rtComp = (RichTextComponent)component;
								String temp = rtComp.getRichText();
								if (temp.indexOf(getSearchtext().getValue().toString())>0){
									sb.append("-->\""+getSearchtext().getValue().toString()+"\" exists in:  "+content.getTitle()+".<br>");
								}else{
									System.out.println("TITLE:  "+content.getTitle()+"...NO RESULT.");
								}
	//							System.out.println(rtComp.getRichText());
							}
						}
					}
				}
			}
		} catch (ServiceNotAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OperationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentRetrievalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AuthorizationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ComponentNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getSessionScope().put("foundissues", sb.toString());
		return "";
	}

	protected UISelectItem getSelectItem1() {
		if (selectItem1 == null) {
			selectItem1 = (UISelectItem) findComponentInRoot("selectItem1");
		}
		return selectItem1;
	}

	protected HtmlOutputText getFi() {
		if (fi == null) {
			fi = (HtmlOutputText) findComponentInRoot("fi");
		}
		return fi;
	}
	
	
}