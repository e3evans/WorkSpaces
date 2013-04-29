package pagecode.Candidate_Data;

import javax.faces.event.ValueChangeEvent;

import pagecode.PageCodeBase;
import utils.SSSConstants;

public class Consent extends PageCodeBase{
	
	private Boolean confirmTrue;

	public boolean isConfirmTrue() {
		Boolean temp = (Boolean)sessionScope.get(SSSConstants.CONFIRM_TRUE_SESSION_KEY);
		
		if(temp != null)
		{
			this.confirmTrue = temp;
		}
		else
		{
			setConfirmTrue(false);
		}
		return confirmTrue.booleanValue();
	}

	public void setConfirmTrue(boolean confirmTrue) {
		
		sessionScope.put(SSSConstants.CONFIRM_TRUE_SESSION_KEY, new Boolean(confirmTrue));
		this.confirmTrue = new Boolean(confirmTrue);
	}
	
	public void changeConsent(ValueChangeEvent event)
	{
		Boolean newValue = (Boolean) event.getNewValue();
		setConfirmTrue(newValue.booleanValue());
	}
}
