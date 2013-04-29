package manning.chapterThree.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * This class represents a user of the ArtistPortfolio system.  This is one of 
 * our applications main domain objects.
 */

public class User {

	private String username;
	private String password;
	private String portfolioName;
	private Double age;
	private Date birthday;
	private String firstName;
	private String lastName;
	private String email;
	private boolean receiveJunkMail;
	private Map portfolios = new HashMap();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getReceiveJunkMail() {
		return receiveJunkMail;
	}

	public void setReceiveJunkMail(boolean receiveJunkMail) {
		this.receiveJunkMail = receiveJunkMail;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getAge() {
		return age;
	}

	public void setAge(Double age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Map getPortfolios() {
		return portfolios;
	}

	public void setPortfolios(Map portfolios) {
		this.portfolios = portfolios;
	}

}
