/**
 * 
 */
package pagecode.AjaxMenus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlSelectManyListbox;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.portlet.ActionRequest;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.ValidatorException;
import javax.servlet.http.HttpServletRequest;

import pagecode.PageCodeBase;

import com.ibm.faces.component.UIColumnEx;
import com.ibm.faces.component.html.HtmlCommandExButton;
import com.ibm.faces.component.html.HtmlDataTableEx;
import com.ibm.faces.component.html.HtmlRequestLink;
import com.ibm.faces.component.html.HtmlScriptCollector;
import com.ibm.portal.navigation.NavigationNode;
import com.ibm.portal.portlet.ajaxmenus.AjaxMenusPortlet;
import com.ibm.wps.model.LocaleHelper;
import com.manpower.portal.theme.ajax.AjaxMenuAssistant;
import com.manpower.portal.theme.ajax.AjaxMenuAssistantUIGenerator;
import com.manpower.portal.theme.ajax.AjaxMenuBean;

/**
 * @author Eric Evans
 *
 */
public class AjaxMenusEdit extends PageCodeBase {
		
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected HtmlOutputText text2;
	protected HtmlSelectManyListbox lev1List;
	protected HtmlDataTableEx tableEx1;
	protected UIColumnEx columnEx1;
	protected HtmlOutputText text3;
	protected HtmlOutputText text4;
	protected HtmlOutputText text5;
	protected HtmlOutputText textSequence1;
	protected UIColumnEx columnEx2;
	protected HtmlOutputText textDisplayName1;
	protected UIColumnEx columnEx3;
	protected HtmlOutputText textUniqueid1;
	protected HtmlOutputText text6;
	protected UIColumnEx columnEx4;
	protected HtmlCommandExButton moveUp;
	protected HtmlCommandExButton moveDown;
	protected HtmlCommandExButton deleteRow;
	protected HtmlOutputText text1;
	protected HtmlRequestLink link1;
	protected HtmlInputText hideword;
	protected HtmlCommandExButton button1;
	protected HtmlCommandExButton updatePreferences;
	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}

	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}

	public void onPageLoadBegin(FacesContext facescontext) {
		/*
		 * Set the Breadcrumbtrail
		 */
		if (getSessionScope().get("breadcrumbs")==null){
			getSessionScope().put("breadcrumbs", "root");
		}
		/*
		 * Get the navlist based on either the root entry or the last item clicked.
		 */
		HttpServletRequest req = (HttpServletRequest)facescontext.getExternalContext().getRequest();
		RenderRequest rRequest = (RenderRequest)facescontext.getExternalContext().getRequest();
		
		String [] storedPrefs = rRequest.getPreferences().getValues(AjaxMenusPortlet.PREF_DISPLAY_ITEMS, null);
		String hideword = rRequest.getPreferences().getValue(AjaxMenusPortlet.PREF_HIDE_WORD, null);
		String showdropdown = rRequest.getPreferences().getValue(AjaxMenusPortlet.PREF_INCLUDE_DROPDOWN, "false");
		getSessionScope().put("hideword",hideword);
		getSessionScope().put("showdropdown", showdropdown);
				
		if (storedPrefs!=null){
			List myStoredPrefs = new ArrayList();
			StringBuffer previewArea = new StringBuffer();
			for (int i = 0;i<storedPrefs.length;i++){
				
				AjaxMenuBean amb = new AjaxMenuBean();
				amb.setUniqueid(storedPrefs[i]);
				amb.setSequence(i);
				NavigationNode nn = (NavigationNode)AjaxMenuAssistant.getInstance(req).getRootNode(amb.getUniqueid());
				amb.setDisplayName(LocaleHelper.getTitle(nn, req));
				myStoredPrefs.add(amb);
				previewArea.append(AjaxMenuAssistantUIGenerator.getMenuCell(req, amb.getUniqueid(),hideword));
			}
			getSessionScope().put("previewArea",previewArea.toString());
			getSessionScope().put("storedPrefs", myStoredPrefs);
			getSessionScope().put("lastentry",Integer.toString(storedPrefs.length-1));
			
			
		}
		
		
		NavigationNode node = null;
		if (getSessionScope().get("level1Value")!=null){
			node = AjaxMenuAssistant.getInstance(req).getRootNode(getSessionScope().get("level1Value").toString());
			String breadCrumbs = getSessionScope().get("breadcrumbs").toString();
			breadCrumbs+="-->"+(LocaleHelper.getTitle(node, req));
			getSessionScope().put("breadcrumbs",breadCrumbs);
		}else{
			node = AjaxMenuAssistant.getInstance(req).getRootNode();
		}
		Stack baseStack = AjaxMenuAssistant.getInstance(req).getChildren(req, node);
		Iterator bs = baseStack.iterator();

		List uiList = new ArrayList();
		while (bs.hasNext()){
			SelectItem si = new SelectItem();
			node = (NavigationNode)bs.next();
			if (node.getObjectID().getUniqueName()!=null){
				si.setLabel((LocaleHelper.getTitle(node, req)));
				si.setValue(node.getObjectID().getUniqueName());
				si.setDescription(Boolean.toString(AjaxMenuAssistant.getInstance(req).hasChildren(node)));
				uiList.add(si);
			}
			

		}
		getSessionScope().put("topLevelList",uiList);
		
		ResourceBundle rb = ResourceBundle.getBundle("com.ibm.portal.portlet.ajaxmenus.nl.CountryWebsites");
		int count = 0;
		for (Enumeration e = rb.getKeys();e.hasMoreElements();){
			e.nextElement();
			count++;
		}
		count++;
		List websites = new ArrayList();
		for (int i=1;i<count;i++){
			SelectItem si = new SelectItem();
			String [] siteInfo = rb.getString("site"+Integer.toString(i)).split(",");
			si.setLabel(siteInfo[0]+"-->"+siteInfo[1]);
			si.setValue(siteInfo[2]);
			websites.add(si);
		}
		getSessionScope().put("websites",websites);
		
	}

	public String doButton1Action() {
		
		if (getLev1List().getSelectedValues().length>0){
			getSessionScope().put("level1Value", getLev1List().getSelectedValues()[0].toString());	
		}

		return "";
	}

	protected HtmlOutputText getText2() {
		if (text2 == null) {
			text2 = (HtmlOutputText) findComponentInRoot("text2");
		}
		return text2;
	}

	public String doLink1Action() {
		// Type Java code that runs when the component is clicked
		getSessionScope().remove("level1Value");
		getSessionScope().remove("breadcrumbs");
		// TODO: Return outcome that corresponds to a navigation rule
		return "";
	}

	
	protected HtmlSelectManyListbox getLev1List() {
		if (lev1List == null) {
			lev1List = (HtmlSelectManyListbox) findComponentInRoot("lev1List");
		}
		return lev1List;
	}

	protected HtmlDataTableEx getTableEx1() {
		if (tableEx1 == null) {
			tableEx1 = (HtmlDataTableEx) findComponentInRoot("tableEx1");
		}
		return tableEx1;
	}

	protected UIColumnEx getColumnEx1() {
		if (columnEx1 == null) {
			columnEx1 = (UIColumnEx) findComponentInRoot("columnEx1");
		}
		return columnEx1;
	}

	protected HtmlOutputText getText3() {
		if (text3 == null) {
			text3 = (HtmlOutputText) findComponentInRoot("text3");
		}
		return text3;
	}

	protected HtmlOutputText getText4() {
		if (text4 == null) {
			text4 = (HtmlOutputText) findComponentInRoot("text4");
		}
		return text4;
	}

	protected HtmlOutputText getText5() {
		if (text5 == null) {
			text5 = (HtmlOutputText) findComponentInRoot("text5");
		}
		return text5;
	}

	protected HtmlOutputText getTextSequence1() {
		if (textSequence1 == null) {
			textSequence1 = (HtmlOutputText) findComponentInRoot("textSequence1");
		}
		return textSequence1;
	}

	protected UIColumnEx getColumnEx2() {
		if (columnEx2 == null) {
			columnEx2 = (UIColumnEx) findComponentInRoot("columnEx2");
		}
		return columnEx2;
	}

	protected HtmlOutputText getTextDisplayName1() {
		if (textDisplayName1 == null) {
			textDisplayName1 = (HtmlOutputText) findComponentInRoot("textDisplayName1");
		}
		return textDisplayName1;
	}

	protected UIColumnEx getColumnEx3() {
		if (columnEx3 == null) {
			columnEx3 = (UIColumnEx) findComponentInRoot("columnEx3");
		}
		return columnEx3;
	}

	protected HtmlOutputText getTextUniqueid1() {
		if (textUniqueid1 == null) {
			textUniqueid1 = (HtmlOutputText) findComponentInRoot("textUniqueid1");
		}
		return textUniqueid1;
	}

	protected HtmlOutputText getText6() {
		if (text6 == null) {
			text6 = (HtmlOutputText) findComponentInRoot("text6");
		}
		return text6;
	}

	protected UIColumnEx getColumnEx4() {
		if (columnEx4 == null) {
			columnEx4 = (UIColumnEx) findComponentInRoot("columnEx4");
		}
		return columnEx4;
	}

	protected HtmlCommandExButton getMoveUp() {
		if (moveUp == null) {
			moveUp = (HtmlCommandExButton) findComponentInRoot("moveUp");
		}
		return moveUp;
	}

	protected HtmlCommandExButton getMoveDown() {
		if (moveDown == null) {
			moveDown = (HtmlCommandExButton) findComponentInRoot("moveDown");
		}
		return moveDown;
	}

	private String[] remove(int removeItem, String[] arrayIn)
    {	
		String[] b = new String[arrayIn.length-1];
		System.arraycopy( arrayIn, 0, b, 0, removeItem );
		System.arraycopy( arrayIn, removeItem+1, b, removeItem, b.length-removeItem );
		return b;
    }
	
	private String[] move(int moveItem,String updown,String[] arrayIn){
		int increment = 1;
		if (updown.equals("up")){
			increment = -1;
		}
		String temp = "";
		
		temp = arrayIn[moveItem + increment];
		arrayIn[moveItem+increment]=arrayIn[moveItem];
		arrayIn[moveItem]=temp;
		
	
		
		
		return arrayIn;
	}
	
	public String doDeleteRowAction() {
		ActionRequest aRequest = (ActionRequest)getFacesContext().getExternalContext().getRequest();
		PortletPreferences prefs = aRequest.getPreferences();
		String[] storedPrefs = prefs.getValues(AjaxMenusPortlet.PREF_DISPLAY_ITEMS, null);
		String deleteWhat = getTextSequence1().getValue().toString();
		int delete = Integer.parseInt(deleteWhat);
		
		storedPrefs = remove(delete,storedPrefs);
		
		try {
			prefs.setValues(AjaxMenusPortlet.PREF_DISPLAY_ITEMS,storedPrefs);
			prefs.store();
		} catch (ReadOnlyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}

	public String doButton2Action() {
		// Type Java code that runs when the component is clicked
		ActionRequest ar = (ActionRequest)getFacesContext().getExternalContext().getRequest();
		PortletPreferences prefs = ar.getPreferences();
		
		try {
			prefs.reset(AjaxMenusPortlet.PREF_DISPLAY_ITEMS);
			prefs.store();
		} catch (ReadOnlyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO: Return outcome that corresponds to a navigation rule
		return "";
	}

	protected HtmlCommandExButton getDeleteRow() {
		if (deleteRow == null) {
			deleteRow = (HtmlCommandExButton) findComponentInRoot("deleteRow");
		}
		return deleteRow;
	}

	public String doMoveUpAction() {
		
		ActionRequest ar = (ActionRequest)getFacesContext().getExternalContext().getRequest();
		PortletPreferences prefs = ar.getPreferences();
		String [] temp = move(Integer.parseInt(getTextSequence1().getValue().toString()), "up", prefs.getValues(AjaxMenusPortlet.PREF_DISPLAY_ITEMS, null));
		
		
		try {
			prefs.setValues(AjaxMenusPortlet.PREF_DISPLAY_ITEMS,temp);
			prefs.store();
		} catch (ReadOnlyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}

	public String doMoveDownAction() {
		
		ActionRequest ar = (ActionRequest)getFacesContext().getExternalContext().getRequest();
		PortletPreferences prefs = ar.getPreferences();
		String [] temp = move(Integer.parseInt(getTextSequence1().getValue().toString()), "down", prefs.getValues(AjaxMenusPortlet.PREF_DISPLAY_ITEMS, null));
		
		
		try {
			prefs.setValues(AjaxMenusPortlet.PREF_DISPLAY_ITEMS,temp);
			prefs.store();
		} catch (ReadOnlyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}

	protected HtmlOutputText getText1() {
		if (text1 == null) {
			text1 = (HtmlOutputText) findComponentInRoot("text1");
		}
		return text1;
	}

	protected HtmlRequestLink getLink1() {
		if (link1 == null) {
			link1 = (HtmlRequestLink) findComponentInRoot("link1");
		}
		return link1;
	}

	protected HtmlInputText getHideword() {
		if (hideword == null) {
			hideword = (HtmlInputText) findComponentInRoot("hideword");
		}
		return hideword;
	}

	protected HtmlCommandExButton getButton1() {
		if (button1 == null) {
			button1 = (HtmlCommandExButton) findComponentInRoot("button1");
		}
		return button1;
	}

	protected HtmlCommandExButton getUpdatePreferences() {
		if (updatePreferences == null) {
			updatePreferences = (HtmlCommandExButton) findComponentInRoot("updatePreferences");
		}
		return updatePreferences;
	}

	public String doUpdatePreferencesAction() {
		ActionRequest aRequest = (ActionRequest)getFacesContext().getExternalContext().getRequest();
		String[] storeValues = (String[])getLev1List().getSelectedValues();
		String hideword = (String)getHideword().getValue().toString();
//		String hidedropdown = getHidedropdown().getValue().toString();
		try {
			String[] prefValues = aRequest.getPreferences().getValues(AjaxMenusPortlet.PREF_DISPLAY_ITEMS, new String[1]);
			List tempList = new ArrayList();
			for (int i=0;i<prefValues.length;i++){
				if (prefValues[i]!=null)tempList.add(prefValues[i]);
				
			}
			for (int i=0;i<storeValues.length;i++){
				if (!storeValues[i].equals(""))tempList.add(storeValues[i]);
			}
			storeValues = new String[tempList.size()];
			for (int i=0;i<tempList.size();i++){
				storeValues[i]=(String)tempList.get(i);
			}
			aRequest.getPreferences().setValues(AjaxMenusPortlet.PREF_DISPLAY_ITEMS, storeValues);
			aRequest.getPreferences().setValue(AjaxMenusPortlet.PREF_HIDE_WORD, hideword);
//			aRequest.getPreferences().setValue(AjaxMenusPortlet.PREF_INCLUDE_DROPDOWN, hidedropdown);
			aRequest.getPreferences().store();
		} catch (ReadOnlyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			getSessionScope().remove("level1Value");
			getSessionScope().remove("breadcrumbs");
		}
		return "";
	}

}