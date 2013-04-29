/**
 * 
 */
package pagecode.GEMTManager;

import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;

import pagecode.PageCodeBase;

import com.ibm.faces.component.html.HtmlCommandExButton;
import com.ibm.faces.component.html.HtmlScriptCollector;
import com.ibm.portal.puma.User;
import com.manpower.portal.gemt.ui.beans.GemtDirectReportUIBean;
import com.manpower.portal.utility.UserManager;

/**
 * @author Eric Evans
 *
 */
public class GEMTManagerAddUsers extends PageCodeBase {

	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected HtmlCommandExButton returntomain;

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

	protected HtmlCommandExButton getReturntomain() {
		if (returntomain == null) {
			returntomain = (HtmlCommandExButton) findComponentInRoot("returntomain");
		}
		return returntomain;
	}

	public void onPageLoadBegin(FacesContext facescontext) {
		// Type Java code to handle page load begin event here
		System.out.println(getSessionScope().get(GEMTManagerView.SESS_MANAGER_SELECTED));
		try {
			UserManager um = new UserManager(facescontext);
			User user = (User) um.findUserByUid(getSessionScope().get(GEMTManagerView.SESS_MANAGER_SELECTED).toString());
			if (user!=null){
//				User user = (User)list.get(0);
				GemtDirectReportUIBean gdruib = new GemtDirectReportUIBean(user);
				System.out.println(gdruib.getGemt_sum_empname());
				System.out.println(gdruib.getGemt_sum_empemail());
				System.out.println(gdruib.getGemt_sum_region());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	public String doReturntomainAction() {
		// Type Java code that runs when the component is clicked
		String returnAction ="RETURN";
		
		return returnAction;
	}

}