package pagecode.Candidate_Data;

import javax.faces.context.FacesContext;
import javax.portlet.PortletContext;

import org.apache.log4j.Logger;

import pagecode.PageCodeBase;

public class Test extends PageCodeBase{
	
	private static Logger logger = Logger.getLogger(Test.class);
	
	public void onPageLoadBegin(FacesContext facescontext)
	{
		logger.debug("Test.onPageLoadBegin - ENTRY");
		Object obj = facesContext.getExternalContext().getContext();
		if(obj instanceof PortletContext)
		{
			logger.debug("obj is instance of PortletContext");
			PortletContext portletContext = (PortletContext)obj;
			portletContext.setAttribute("country", "Canada");
		}
		else
		{
			logger.debug("obj is NOT an instance of PortletContext");
		}
		
		logger.debug("Test.onPageLoadBegin - EXIT");
	}
}
