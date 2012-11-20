package com.asponte.rest.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.json.JSONObject;
import org.json.XML;

import com.asponte.vmm.VMMManager;

@Path("search")
public class SearchService {
	@GET
	@Produces("text/xml")
	public String searchVMM(
			@QueryParam("userName")String userName,
			@QueryParam("password")String password,
			@QueryParam("searchBase")String searchBase,
			@QueryParam("searchTerm")String searchTerm){
		
		try {
			return VMMManager.searchVMM(userName,password,searchBase,searchTerm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "THERE HAS BEEN AN ERROR CHECK THE LOGS!";
	}
	@POST
	@Produces("application/xml")
	public String searchVMMPostXML(
			@FormParam("userName")String userName,
			@FormParam("password")String password,
			@FormParam("searchBase")String searchBase,
			@FormParam("searchTerm")String searchTerm){

//		System.out.println("HERE!!!!"+searchTerm);
		try {
//			return VMMManager.searchVMM("waslocal","waslocal","o=defaultWIMFileBasedRealm","w");
			System.out.println("SEARCH BASE:  "+searchBase);
			System.out.println("SEARCH TERM:  "+searchTerm);
			return VMMManager.searchVMM(userName,password,searchBase,searchTerm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "THERE HAS BEEN AN ERROR CHECK THE LOGS!";
	}
	
	@POST
	@Path("/json")
	@Produces("application/xml")
	public String searchVMMPostJSON(
			@FormParam("userName")String userName,
			@FormParam("password")String password,
			@FormParam("searchBase")String searchBase,
			@FormParam("searchTerm")String searchTerm){
		try {
			String xml = VMMManager.searchVMM(userName,password,searchBase,searchTerm).trim();
			JSONObject xmlJSONObj = XML.toJSONObject(xml);
			return xmlJSONObj.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "THERE HAS BEEN AN ERROR CHECK THE LOGS!";
	}
	@GET
	@Path("/testDN")
	@Produces("text/xml")
	public String test(@QueryParam("userName")String user, 
			@QueryParam("password")String password, @QueryParam("base")String base,
			@QueryParam("queryAttrib")String queryAttrib,@QueryParam("userType")String userType){
		
		try {
			String test = VMMManager.searchTest(user, password, base,queryAttrib,userType);
			return test;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return"CHECK THE LOGS";
	}
}
