/**
 * 
 */
package pagecode.GEMTNOTES;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;

import org.apache.commons.beanutils.BeanUtils;

import pagecode.PageCodeBase;

import com.ibm.faces.component.html.HtmlRequestLink;
import com.ibm.faces.component.html.HtmlScriptCollector;
import com.manpower.portal.gemt.hbn.beans.GemtNotesHbnBean;
import com.manpower.portal.gemt.hbn.shared.HibernateUtilities;
import com.manpower.portal.gemt.hbn.shared.dao.DAOFactory;
import com.manpower.portal.gemt.ui.beans.GemtNotesUIBean;
import com.manpower.portal.gemt.ui.services.GEMTServiceLocator;
import com.ibm.faces.component.html.HtmlCommandExButton;

/**
 * @author rrajaram
 * 
 */
public class GEMTNotesFormView extends PageCodeBase {

	public static String SESS_NOTESUIBEAN = "GemtNotesUIBean";

	public static String SESS_DISPLAYREPORT = "gemtnoteseditdisplay";

	protected HtmlForm form1;

	protected HtmlScriptCollector scriptCollector1;

	protected HtmlInputTextarea textarea1;

	protected HtmlCommandExButton submit;

	protected HtmlOutputText gemt_notes_user_name;

	public String doEditReportAction() {
		// Type Java code that runs when the component is clicked

		// TODO: Return outcome that corresponds to a navigation rule
		if (HtmlRequestLink.getParameter("paramId") == null)
			System.out.println("Null");
		else if ((HtmlRequestLink.getParameter("paramId").toString())
				.equals(null))
			System.out.println("Null");
		else
			sessionScope.put(SESS_DISPLAYREPORT, HtmlRequestLink.getParameter(
					"paramId").toString());
		return "VIEW_PAGE";
	}

	public GemtNotesUIBean copyValue(GemtNotesUIBean gsrb, GemtNotesUIBean gsrb1)
	{
		gsrb.setGemt_notes_user_name(gsrb1.getGemt_notes_user_name());
		gsrb.setCreated_by(gsrb1.getCreated_by());
		gsrb.setCreated_on(gsrb1.getCreated_on());
		gsrb.setGemt_notes_user_email(gsrb1.getGemt_notes_user_email());
		gsrb.setUpdated_by(gsrb1.getUpdated_by());
		gsrb.setUpdated_on(gsrb1.getUpdated_on());
		gsrb.setId(gsrb1.getId());
		return gsrb;
	}
	
	public String doSubmitAction() {		
		Map fieldMap = getFieldMap(FacesContext.getCurrentInstance());
		String formBase = (String)fieldMap.get("formBase");
		
		GemtNotesUIBean gsrb = 	new GemtNotesUIBean(formBase,fieldMap);
		GemtNotesUIBean gsrb1 = GEMTServiceLocator.getInstance()
		.getNotesService().findById(
				new Long(sessionScope.get(
						GEMTNotesView.SESS_DISPLAYREPORT)
						.toString()));
		
		gsrb= copyValue(gsrb, gsrb1);
		gsrb.convertHtmlVals();
		
		GemtNotesHbnBean gsrhb = new GemtNotesHbnBean();
		

		try {
			System.out.println("ID :  " + gsrb.getId());
			gsrhb = (GemtNotesHbnBean) DAOFactory.getDAOFactory()
					.getGemtNotesDAO().findByID(new Long(gsrb.getId()));
			BeanUtils.copyProperties(gsrhb, gsrb);
			HibernateUtilities.currentSession().beginTransaction();
			gsrhb = (GemtNotesHbnBean) DAOFactory.getDAOFactory()
					.getGemtNotesDAO().makePersistent(gsrhb);
			HibernateUtilities.currentSession().getTransaction().commit();

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "VIEW_PAGE";
	}

	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}

	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}

	
	 public String doDeleteReportAction() { // Type Java code that runs when the component is clicked
		 String id = HtmlRequestLink.getParameter(
			"paramId").toString();
		 GEMTServiceLocator.getInstance().getNotesService().delete(new Long(id)); 
	
	 return "VIEW_PAGE"; 
	 }
	 

	public void onPageLoadBegin(FacesContext facescontext) {
		if (sessionScope.get(GEMTNotesView.SESS_DISPLAYREPORT) != null) {
			GemtNotesUIBean gsrb = GEMTServiceLocator.getInstance()
					.getNotesService().findById(
							new Long(sessionScope.get(
									GEMTNotesView.SESS_DISPLAYREPORT)
									.toString()));
			//gsrb.convertHtmlVals();
			sessionScope.put(SESS_NOTESUIBEAN, gsrb);
		} else {
			// TEST CONDITIONS!!!
			GemtNotesUIBean gsrb = GEMTServiceLocator.getInstance()
					.getNotesService().findById(new Long("7"));
		//	gsrb.convertHtmlVals();
			sessionScope.put(SESS_NOTESUIBEAN, gsrb);
		}
		System.out
				.println("RECEIEVED:  "
						+ sessionScope.get(GEMTNotesView.SESS_DISPLAYREPORT)
								.toString());

	}

	protected HtmlInputTextarea getTextarea1() {
		if (textarea1 == null) {
			textarea1 = (HtmlInputTextarea) findComponentInRoot("textarea1");
		}
		return textarea1;
	}


	protected HtmlCommandExButton getSubmit() {
		if (submit == null) {
			submit = (HtmlCommandExButton) findComponentInRoot("submit");
		}
		return submit;
	}

	protected HtmlOutputText getGemt_notes_user_name() {
		if (gemt_notes_user_name == null) {
			gemt_notes_user_name = (HtmlOutputText) findComponentInRoot("gemt_notes_user_name");
		}
		return gemt_notes_user_name;
	}

}