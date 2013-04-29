/**
 * 
 */
package pagecode.Search30;

import pagecode.PageCodeBase;

import com.ibm.faces.component.html.HtmlScriptCollector;
import com.ibm.faces.component.html.HtmlCommandExButton;
import com.manpower.directtalentsearch30.utility.IndexerUtility;

import javax.faces.component.html.HtmlForm;

/**
 * @author Eric Evans
 *
 */
public class DirectTalent_Search30View extends PageCodeBase {

	protected HtmlScriptCollector scriptCollector1;
	protected HtmlCommandExButton reindex;
	protected HtmlForm form1;


	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}


	protected HtmlCommandExButton getReindex() {
		if (reindex == null) {
			reindex = (HtmlCommandExButton) findComponentInRoot("reindex");
		}
		return reindex;
	}


	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}


	public String doReindexAction() {
		
		try{
			IndexerUtility.IndexAllAdverts();
		}catch (Exception e){
			e.printStackTrace();
		}
		return "";
	}


}