package com.eblue.springpoll.controllers;

import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eblue.springpoll.services.DataBaseService;



@Controller
@RequestMapping("VIEW")
public class ViewController {
	
	@Autowired
	private DataBaseService databaseDAO;
	
	@RequestMapping
	public String defaultView(RenderRequest request,RenderResponse response){
		Map<String, Object> userAttributeMap = (Map<String, Object>) request.getAttribute(PortletRequest.USER_INFO);
		String currentLoginUser = "";
        if (userAttributeMap != null) {
            currentLoginUser = (String) userAttributeMap.get("user.name.family");
            request.setAttribute("currentUser", currentLoginUser);
        }
	        
        PortletSession session = request.getPortletSession();
        session.setAttribute("currentUser", currentLoginUser);

        System.out.println(databaseDAO.getPolls(currentLoginUser).size());
		databaseDAO.testConnection();
		return "polls";
	}

}
