package com.manpower.directaltent.reporting.services;

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

import com.manpower.directaltent.reporting.utils.UUIDFunctions;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			 out.print(UUIDFunctions.createUUID());
			 out.close();
			 System.out.println("TESTING!!!");

			 
		} catch (NamingException e) {
		    e.printStackTrace();
		    out.print("loginFailure");
		    out.close();
		}
	}
		
		



	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
/*	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		username = username + "@CORP";
		String password = request.getParameter("password");
	
		System.out.println("USER: " +username);
		System.out.println("PASS: "+password);
		
		Hashtable<String, String> env = new Hashtable<String, String>(11);
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://gsd1w104c.CORP.ROOT.GLOBAL:389");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL,  username );
		env.put(Context.SECURITY_CREDENTIALS, password);
		
		try {
			 DirContext ctx = new InitialDirContext(env);
			 ctx.close();
			 out.print(UUIDFunctions.createUUID());
			 out.close();
			 System.out.println("TESTING!!!");
			 
		} catch (NamingException e) {
		    e.printStackTrace();
		    out.print("loginFailure");
		    out.close();
		}
	}*/

}
