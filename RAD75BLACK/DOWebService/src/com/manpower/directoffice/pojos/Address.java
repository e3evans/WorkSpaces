package com.manpower.directoffice.pojos;



public class Address {

	static public final String PRIMARY_ADDRESS = "primary";
	static public final String PAYROLL_ADDRESS = "payroll";

	
	String addressLine1;	// extracted
	String addressLine2;	// extracted
	String municipality;	// extracted
	String state_province;	// extracted
	String postal_code;		// extracted
	String address_type;	// extracted (primary)
	String country;			// extracted
	

	public String getCountry() {
		return country;

	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAddressLine1() {
		
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	public String getState_province() {
//		Hashtable list = CodeListHandler.getStateList(); 
//		String state = (String) list.get(this.state_province);
//		if (state == null)return "";
		return state_province;
	}
	public void setState_province(String state_province) {
		this.state_province = state_province;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public String getAddress_type() {
		return address_type;
	}
	public void setAddress_type(String address_type) {
		this.address_type = address_type;
	}

	public static Address getTestMainAddressData(){
		Address mainAddress = new Address();
		mainAddress.setAddress_type(Address.PRIMARY_ADDRESS);
		mainAddress.setAddressLine1("123 Testing Lane");
		mainAddress.setAddressLine2("Apt 1234");
		mainAddress.setMunicipality("Milwaukee");
		mainAddress.setPostal_code("12345");
		mainAddress.setState_province("Wisconsin");
		mainAddress.setCountry("United States");
		return mainAddress;
	}
	
	public void printMe() {
		System.out.println("type->" + this.getAddress_type());
		System.out.println("line1 ->" + this.getAddressLine1());
		System.out.println("line2 ->" + this.getAddressLine2());
		System.out.println("country ->" + this.getCountry());
		System.out.println("municp ->" + this.getMunicipality());
		System.out.println("postcode ->" + this.getPostal_code());
		System.out.println("state ->" + this.getState_province());

	}
}
