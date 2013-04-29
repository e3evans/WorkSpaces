package com.manpower.widget.report.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AuthenticationService
 */
public class AuthenticationService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticationService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Serlvet is getting hit on get");
		PrintWriter out = response.getWriter();
//		String username = request.getParameter("username");
//		username = username + "@CORP";
//		String password = request.getParameter("password");
		Hashtable env = new Hashtable(11);
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://gsd1w104c.CORP.ROOT.GLOBAL:389");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
//		env.put(Context.SECURITY_PRINCIPAL,  "skha1@CORP" );
//		env.put(Context.SECURITY_CREDENTIALS, "Manpower1");
		
		env.put(Context.SECURITY_PRINCIPAL,  "eevan1" );
		env.put(Context.SECURITY_CREDENTIALS, "Iloveliz18");
		try {
			System.out.println("TEST BEFORE");
			 DirContext ctx = new InitialDirContext(env);
			
			 ctx.close();
			System.out.println("HERE!!!");
			DirContext ctx2 = new InitialDirContext(env);
			// ctx = new InitialDirContext(bindenv);
			
			SearchControls searchCtls = new SearchControls();
			
			//Specify the search scope
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
 
			//specify the LDAP search filter
			String searchFilter = "(&(objectClass=user)(sAMAccountName=skha1))";
		
			//Specify the Base for the search
			String searchBase = "DC=CORP,DC=ROOT,DC=GLOBAL";
 
			//initialize counter to total the group members
			int totalResults = 0;
 
			//Specify the attributes to return
			String returnedAtts[]={"memberOf"};
			searchCtls.setReturningAttributes(returnedAtts);
		
			//Search for objects using the filter
			NamingEnumeration answer = ctx2.search(searchBase, searchFilter, searchCtls);
			System.out.println(answer.hasMoreElements());
			while (answer.hasMoreElements()){
				SearchResult sr = (SearchResult)answer.next();
				 
				System.out.println(">>>" + sr.getName());
				Attributes attrs = sr.getAttributes();
				if (attrs != null) {
 
					try {
						for (NamingEnumeration ae = attrs.getAll();ae.hasMore();) {
							Attribute attr = (Attribute)ae.next();
							System.out.println("Attribute: " + attr.getID());
							for (NamingEnumeration e = attr.getAll();e.hasMore();totalResults++) {
 
								System.out.println(" " +  totalResults + ". " +  e.next());
							}
 
						}
 
					}	 
					catch (NamingException e)	{
						System.err.println("Problem listing membership: " + e);
					}
				
				}
				

			}
			
			
			 ctx2.close();
			 out.print("loginSuccessful");
			 out.close();
		} catch (NamingException e) {
		    e.printStackTrace();
		    out.print("loginFailure");
		    out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Serlvet is getting hit on post");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		username = username + "@CORP";
		String password = request.getParameter("password");
		Hashtable<String, String> env = new Hashtable<String, String>(11);
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://gsd1w104c.CORP.ROOT.GLOBAL:389");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL,  username );
		env.put(Context.SECURITY_CREDENTIALS, password);
		
		try {
			 DirContext ctx = new InitialDirContext(env);
			 ctx.close();
			 out.print("loginSuccessful");
			 out.close();
		//START	GROUP CHECK CODE....
//				System.out.println("HERE!!!");
//				DirContext ctx2 = new InitialDirContext(env);
//				// ctx = new InitialDirContext(bindenv);
//				
//				SearchControls searchCtls = new SearchControls();
//				
//				//Specify the search scope
//				searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
//	 
//				//specify the LDAP search filter
//				String searchFilter = "(&(objectClass=user)(sAMAccountName=eevan1))";
//			
//				//Specify the Base for the search
//				String searchBase = "DC=CORP,DC=ROOT,DC=GLOBAL";
//	 
//				//initialize counter to total the group members
//				int totalResults = 0;
//	 
//				//Specify the attributes to return
//				String returnedAtts[]={"memberOf"};
//				searchCtls.setReturningAttributes(returnedAtts);
//				
//				//Search for objects using the filter
//				NamingEnumeration answer = ctx2.search(searchBase, searchFilter, searchCtls);
//				System.out.println("MORE ELEMENTS?:  "+answer.hasMoreElements());
//				while (answer.hasMoreElements()){
//					SearchResult sr = (SearchResult)answer.next();
//					 
//					System.out.println(">>>" + sr.getName());
//					Attributes attrs = sr.getAttributes();
//					if (attrs != null) {
//	 
//						try {
//							for (NamingEnumeration ae = attrs.getAll();ae.hasMore();) {
//								Attribute attr = (Attribute)ae.next();
//								System.out.println("Attribute: " + attr.getID());
//								for (NamingEnumeration e = attr.getAll();e.hasMore();totalResults++) {
//	 
//									System.out.println(" " +  totalResults + ". " +  e.next());
//								}
//	 
//							}
//	 
//						}	 
//						catch (NamingException e)	{
//							System.err.println("Problem listing membership: " + e);
//						}
//					
//					}
//					
//
//				}
//				
//				
//				 ctx2.close();
////				 out.print("loginSuccessful");
//				 out.close();
//			 
			 
		//FINISH 
			 
		} catch (NamingException e) {
		    e.printStackTrace();
		    out.print("loginFailure");
		    out.close();
		}
	}

}
