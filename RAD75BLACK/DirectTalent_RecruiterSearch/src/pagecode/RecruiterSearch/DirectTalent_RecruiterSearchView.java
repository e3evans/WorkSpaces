/**
 * 
 */
package pagecode.RecruiterSearch;

import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;

import pagecode.PageCodeBase;

import com.ibm.faces.component.UIColumnEx;
import com.ibm.faces.component.html.HtmlBehavior;
import com.ibm.faces.component.html.HtmlCommandExButton;
import com.ibm.faces.component.html.HtmlDataTableEx;
import com.ibm.faces.component.html.HtmlInputHelperTypeahead;
import com.ibm.faces.component.html.HtmlScriptCollector;
import javax.faces.component.html.HtmlInputText;

/**
 * @author Eric Evans
 *
 */
public class DirectTalent_RecruiterSearchView extends PageCodeBase {

	protected HtmlBehavior behavior2;
	protected HtmlCommandExButton button1;
	protected UIColumnEx columnEx1;
	protected HtmlOutputText text1;
	protected HtmlOutputText text2;
	protected HtmlOutputText text3;
	protected HtmlOutputText text4;
	protected HtmlOutputText text5;
	protected HtmlOutputText text6;
	protected HtmlOutputText text7;
	protected HtmlOutputText text8;
	protected HtmlDataTableEx tableEx1;
	protected HtmlOutputText textFirstname1;
	protected UIColumnEx columnEx2;
	protected HtmlOutputText textMiddlename1;
	protected UIColumnEx columnEx3;
	protected HtmlOutputText textLastname1;
	protected UIColumnEx columnEx4;
	protected HtmlOutputText textStatus1;
	protected UIColumnEx columnEx5;
	protected HtmlOutputText textLast_login_date1;
	protected UIColumnEx columnEx6;
	protected HtmlOutputText textCount_of_jobs_applied_for1;
	protected UIColumnEx columnEx7;
	protected HtmlOutputText textPrefered_location1;
	protected UIColumnEx columnEx8;
	protected HtmlOutputText textBranchname1;
	protected HtmlInputHelperTypeahead typeahead1;
	protected HtmlForm keywordSearchForm;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlInputText keywords;
	protected HtmlBehavior getBehavior2() {
		if (behavior2 == null) {
			behavior2 = (HtmlBehavior) findComponentInRoot("behavior2");
		}
		return behavior2;
	}

	protected HtmlCommandExButton getButton1() {
		if (button1 == null) {
			button1 = (HtmlCommandExButton) findComponentInRoot("button1");
		}
		return button1;
	}

	protected UIColumnEx getColumnEx1() {
		if (columnEx1 == null) {
			columnEx1 = (UIColumnEx) findComponentInRoot("columnEx1");
		}
		return columnEx1;
	}

	protected HtmlOutputText getText1() {
		if (text1 == null) {
			text1 = (HtmlOutputText) findComponentInRoot("text1");
		}
		return text1;
	}

	protected HtmlOutputText getText2() {
		if (text2 == null) {
			text2 = (HtmlOutputText) findComponentInRoot("text2");
		}
		return text2;
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

	protected HtmlOutputText getText6() {
		if (text6 == null) {
			text6 = (HtmlOutputText) findComponentInRoot("text6");
		}
		return text6;
	}

	protected HtmlOutputText getText7() {
		if (text7 == null) {
			text7 = (HtmlOutputText) findComponentInRoot("text7");
		}
		return text7;
	}

	protected HtmlOutputText getText8() {
		if (text8 == null) {
			text8 = (HtmlOutputText) findComponentInRoot("text8");
		}
		return text8;
	}

	protected HtmlDataTableEx getTableEx1() {
		if (tableEx1 == null) {
			tableEx1 = (HtmlDataTableEx) findComponentInRoot("tableEx1");
		}
		return tableEx1;
	}

	protected HtmlOutputText getTextFirstname1() {
		if (textFirstname1 == null) {
			textFirstname1 = (HtmlOutputText) findComponentInRoot("textFirstname1");
		}
		return textFirstname1;
	}

	protected UIColumnEx getColumnEx2() {
		if (columnEx2 == null) {
			columnEx2 = (UIColumnEx) findComponentInRoot("columnEx2");
		}
		return columnEx2;
	}

	protected HtmlOutputText getTextMiddlename1() {
		if (textMiddlename1 == null) {
			textMiddlename1 = (HtmlOutputText) findComponentInRoot("textMiddlename1");
		}
		return textMiddlename1;
	}

	protected UIColumnEx getColumnEx3() {
		if (columnEx3 == null) {
			columnEx3 = (UIColumnEx) findComponentInRoot("columnEx3");
		}
		return columnEx3;
	}

	protected HtmlOutputText getTextLastname1() {
		if (textLastname1 == null) {
			textLastname1 = (HtmlOutputText) findComponentInRoot("textLastname1");
		}
		return textLastname1;
	}

	protected UIColumnEx getColumnEx4() {
		if (columnEx4 == null) {
			columnEx4 = (UIColumnEx) findComponentInRoot("columnEx4");
		}
		return columnEx4;
	}

	protected HtmlOutputText getTextStatus1() {
		if (textStatus1 == null) {
			textStatus1 = (HtmlOutputText) findComponentInRoot("textStatus1");
		}
		return textStatus1;
	}

	protected UIColumnEx getColumnEx5() {
		if (columnEx5 == null) {
			columnEx5 = (UIColumnEx) findComponentInRoot("columnEx5");
		}
		return columnEx5;
	}

	protected HtmlOutputText getTextLast_login_date1() {
		if (textLast_login_date1 == null) {
			textLast_login_date1 = (HtmlOutputText) findComponentInRoot("textLast_login_date1");
		}
		return textLast_login_date1;
	}

	protected UIColumnEx getColumnEx6() {
		if (columnEx6 == null) {
			columnEx6 = (UIColumnEx) findComponentInRoot("columnEx6");
		}
		return columnEx6;
	}

	protected HtmlOutputText getTextCount_of_jobs_applied_for1() {
		if (textCount_of_jobs_applied_for1 == null) {
			textCount_of_jobs_applied_for1 = (HtmlOutputText) findComponentInRoot("textCount_of_jobs_applied_for1");
		}
		return textCount_of_jobs_applied_for1;
	}

	protected UIColumnEx getColumnEx7() {
		if (columnEx7 == null) {
			columnEx7 = (UIColumnEx) findComponentInRoot("columnEx7");
		}
		return columnEx7;
	}

	protected HtmlOutputText getTextPrefered_location1() {
		if (textPrefered_location1 == null) {
			textPrefered_location1 = (HtmlOutputText) findComponentInRoot("textPrefered_location1");
		}
		return textPrefered_location1;
	}

	protected UIColumnEx getColumnEx8() {
		if (columnEx8 == null) {
			columnEx8 = (UIColumnEx) findComponentInRoot("columnEx8");
		}
		return columnEx8;
	}

	protected HtmlOutputText getTextBranchname1() {
		if (textBranchname1 == null) {
			textBranchname1 = (HtmlOutputText) findComponentInRoot("textBranchname1");
		}
		return textBranchname1;
	}


	public void onPageLoadBegin(FacesContext facescontext) {
		// Type Java code to handle page load begin event here
//		List tempList = DAOFactoryHibernate.getDAOFactory().getPreferredLocationsDAO().findLocationsBySiteId(10402);
		
	}

	protected HtmlInputHelperTypeahead getTypeahead1() {
		if (typeahead1 == null) {
			typeahead1 = (HtmlInputHelperTypeahead) findComponentInRoot("typeahead1");
		}
		return typeahead1;
	}



	

	protected HtmlForm getKeywordSearchForm() {
		if (keywordSearchForm == null) {
			keywordSearchForm = (HtmlForm) findComponentInRoot("keywordSearchForm");
		}
		return keywordSearchForm;
	}

	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}

	protected HtmlInputText getKeywords() {
		if (keywords == null) {
			keywords = (HtmlInputText) findComponentInRoot("keywords");
		}
		return keywords;
	}
	
	


}