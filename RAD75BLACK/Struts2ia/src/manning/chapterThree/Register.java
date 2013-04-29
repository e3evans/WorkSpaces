package manning.chapterThree;

import manning.chapterThree.utils.PortfolioService;
import manning.chapterThree.utils.User;

import com.opensymphony.xwork2.ActionSupport;

public class Register extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5301404533250107437L;
	
	private String username;
	private String password;
	private String portfolioName;
	
	public String execute(){
		User user = new User();
		user.setPassword(getPassword());
		user.setPortfolioName(getPortfolioName());
		user.setUsername(getUsername());
		
		getPortfolioService().createAccount(user);
		return SUCCESS;
	}
	
	@Override
	public void validate() {
		PortfolioService ps =getPortfolioService();
		
		if (getPassword().length()==0){
			addFieldError("password", getText("password.required"));
		}
		if (getUsername().length()==0){
			addFieldError("username", getText("username.required"));
		}
		if (getPortfolioName().length()==0){
			addFieldError("portfolioname", getText("portfolioName.required"));
		}
		if (ps.userExists(getUsername())){
			addFieldError("username", getText("user.exists"));
		}
	}
	
	public PortfolioService getPortfolioService(){
		
		return new PortfolioService();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPortfolioName() {
		return portfolioName;
	}
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
	
	
}
