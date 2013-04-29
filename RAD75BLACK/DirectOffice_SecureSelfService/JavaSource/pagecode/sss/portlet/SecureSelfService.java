package pagecode.sss.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;

public class SecureSelfService extends GenericPortlet{

	public void processAction(ActionRequest request, ActionResponse response)
	{
		request.getPortletSession();
	}
}
