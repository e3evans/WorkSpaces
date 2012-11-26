package com.ibm.googlemaps.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.taylor.utils.JSONUtils;
import com.tayloy.data.beans.Distributor;

@Controller
@RequestMapping("VIEW")
public class GoogleMapsViewController {
	
	public static String REQ_DISTRIBS = "com.taylor.beans.distribs";

	@RequestMapping
	public String defaultView(RenderRequest request,RenderResponse response){
		List<Object> myDistributors = new ArrayList<Object>();
		double lat = 42.444371;
	  	double lng = -89.088744;
		for (int i=0;i<10;i++){
			Distributor d = new Distributor();
			d.setAddress("111 Anywhere St.<br>Nowhere, OH USA");
			d.setName("Distributor "+i);
			d.setEmail("test@test.com");
			d.setIcon(response.encodeURL(request.getContextPath()+"/images/distributor.gif"));
			d.setLatitude(lat);
			d.setLongitude(lng);
			lng=lng+.1;
			lat=lat-.2;
			myDistributors.add(d);
		}
		
		try {
			request.setAttribute(REQ_DISTRIBS, JSONUtils.getJSONArray("distributors", myDistributors));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PortletPreferences prefs = request.getPreferences();
		System.out.println(prefs.getValue("defaultLong", "MISS"));
		return "view/DefaultView";
	}
	@ResourceMapping
	public void test(ResourceRequest request, ResourceResponse response){
		System.out.println("RESOURCE TEST!!!");
		System.out.println("TEST:  "+request.getParameter("action"));
	}
	
}
