package com.manpower.xml;

import com.manpower.bgs.services.BgsAdvertmatcher;

public class TestService {
	public TestService(){}
	
	 public static void main(String args[]){
		 
		 System.out.println("STARTING....");
		BgsAdvertmatcher.createMatchFiles("C:/xxLensXML/kioskdata_en.xml");
		 //BgsAdvertmatcher.getRecruiterIds("C:/xxLensXML/kioskdata_en.xml");
		 System.out.println("FINISHING....");
	 }
	
}
