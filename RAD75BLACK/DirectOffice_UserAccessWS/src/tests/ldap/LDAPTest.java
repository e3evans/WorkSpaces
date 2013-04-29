package tests.ldap;

import com.manpower.ldap.ws.UserAccessWS;

public class LDAPTest {

	public static void main(String[] args)
	{
		LDAPTest obj = new LDAPTest();
		
		//create group
//		obj.testCreateGroup();
//		 create container
//		obj.testCreateContainer();
		// add user
//		obj.testAddUser();
		// delete 
//		obj.testDelete();
		// create OU
//		obj.testCreateOU();
		// create user
//		obj.testCreateUser();
		// add user to group
//		obj.testAddUserToGroup();
		// search
//		obj.testSearch();
//		obj.testIsUserExist();
		// remove from group
//		obj.testRemove();
	}
	/*
	private void testAddUser()
	{
		System.out.println("--- start");
		UserAccessWS service = new UserAccessWS();
		
		System.out.println("-- connect");
		service.connectTo("gsd1w124s.manpower.com", 50000, "CN=wpsbind,OU=users,OU=global,O=manpower", "wpsadmin");
		
		System.out.println("--- add user");
				
		service.createUser("123", "123", UserAccessWS.USA_COUNTRY, "123");
		
//		System.out.println("-- delete user");
//		service.deleteUser("CN=SSS,OU=users,OU=global,O=manpower", "OU=users,OU=global,O=manpower");
		
		System.out.println("- disconnect");
		service.disconnect();

		System.out.println("--- finish");
	}
	
	private void testCreateGroup()
	{
		System.out.println("--- start");
		UserAccessWS service = new UserAccessWS();
		
		System.out.println("-- connect");
		service.connectTo("gsd1w124s.manpower.com", 50000, "CN=wpsbind,OU=users,OU=global,O=manpower", "wpsadmin");
		
		System.out.println("--- add user");
		service.addGroupEntry("Canada", "OU=SecureSelfService,O=manpower");
		
		System.out.println("- disconnect");
		service.disconnect();

		System.out.println("--- finish");
	}
	
	private void testCreateContainer()
	{
		System.out.println("--- start");
		UserAccessWS service = new UserAccessWS();
		
		System.out.println("-- connect");
		service.connectTo("gsd1w124s.manpower.com", 50000, "CN=wpsbind,OU=users,OU=global,O=manpower", "wpsadmin");
		
		System.out.println("--- add container");
		service.addContainerEntry("SecureSelfService", "O=manpower");
		
		System.out.println("- disconnect");
		service.disconnect();

		System.out.println("--- finish");
		
	}
	
	private void testDelete()
	{
		System.out.println("--- start");
		UserAccessWS service = new UserAccessWS();
		
		System.out.println("-- connect");
		service.connectTo("gsd1w124s.manpower.com", 50000, "CN=wpsbind,OU=users,OU=global,O=manpower", "wpsadmin");
		
		System.out.println("--- call delete method");
		service.delete("CN=SecureSelfService,OU=groups,OU=global,O=manpower");
		
		System.out.println("- disconnect");
		service.disconnect();

		System.out.println("--- finish");
	}
	
	private void testCreateOU()
	{
		System.out.println("--- start");
		UserAccessWS service = new UserAccessWS();
		
		System.out.println("-- connect");
		service.connectTo("gsd1w124s.manpower.com", 50000, "CN=wpsbind,OU=users,OU=global,O=manpower", "wpsadmin");
		
		service.createOrganizationalUnit("SecureSelfService", "O=manpower");
		
		System.out.println("- disconnect");
		service.disconnect();

		System.out.println("--- finish");
	}
	
	private void testAddUserToGroup()
	{
		System.out.println("--- start");
		UserAccessWS service = new UserAccessWS();
		
		System.out.println("-- connect");
		service.connectTo("gsd1w124s.manpower.com", 50000, "CN=wpsbind,OU=users,OU=global,O=manpower", "wpsadmin");
		
		service.addUserToGroup("CN=36985,OU=users,OU=global,O=manpower", "CN=Canada,OU=SecureSelfService,O=manpower");
		
		System.out.println("- disconnect");
		service.disconnect();

		System.out.println("--- finish");
	}
	
	private void testCreateUser()
	{
		System.out.println("--- start");
		UserAccessWS service = new UserAccessWS();
		
		System.out.println("-- connect");
		service.connectTo("gsd1w124s.manpower.com", 50000, "CN=wpsbind,OU=users,OU=global,O=manpower", "wpsadmin");
		
		service.createUser("36985", "123", "Canada", "123");
		
		System.out.println("- disconnect");
		service.disconnect();

		System.out.println("--- finish");
	}
	
	
	
	private void testIsUserExist()
	{
		System.out.println("--- start");
		UserAccessWS service = new UserAccessWS();
		
		System.out.println("-- connect");
		service.connectTo("gsd1w124s.manpower.com", 50000, "CN=wpsbind,OU=users,OU=global,O=manpower", "wpsadmin");
		
		
		boolean value = service.isUserExist(1234);
		System.out.println("value = " + value);
		
		System.out.println("- disconnect");
		service.disconnect();

		System.out.println("--- finish");
	}
	
	private void testRemove()
	{
		System.out.println("--- start");
		UserAccessWS service = new UserAccessWS();
		
		System.out.println("-- connect");
		service.connectTo("gsd1w124s.manpower.com", 50000, "CN=wpsbind,OU=users,OU=global,O=manpower", "wpsadmin");
		
		
		boolean value = service.removeUserFromGroup("CN=123,OU=users,OU=global,O=manpower", "CN=USA,OU=SecureSelfService,O=manpower");
		System.out.println("value = " + value);
		
		System.out.println("- disconnect");
		service.disconnect();

		System.out.println("--- finish");
	} */
}
