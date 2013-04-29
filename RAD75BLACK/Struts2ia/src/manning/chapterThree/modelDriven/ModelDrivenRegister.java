package manning.chapterThree.modelDriven;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import manning.chapterThree.utils.PortfolioService;
import manning.chapterThree.utils.User;

public class ModelDrivenRegister extends ActionSupport
    implements ModelDriven<Object>
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8552799167818945124L;


	public ModelDrivenRegister()
    {
        user = new User();
    }

    public String execute()
    {
        getPortfolioService().createAccount(user);
        return "success";
    }

    public Object getModel()
    {
        return user;
    }

    public void validate()
    {
        PortfolioService ps = getPortfolioService();
        if(user.getPassword().length() == 0)
            addFieldError("password", getText("password.required"));
        if(user.getUsername().length() == 0)
            addFieldError("username", getText("username.required"));
        if(user.getPortfolioName().length() == 0)
            addFieldError("portfolioName", getText("portfolioName.required"));
        if(ps.userExists(user.getUsername()))
            addFieldError("username", getText("user.exists"));
    }

    public PortfolioService getPortfolioService()
    {
        return new PortfolioService();
    }

    private User user;
}