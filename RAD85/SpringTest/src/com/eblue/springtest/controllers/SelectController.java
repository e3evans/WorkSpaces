package com.eblue.springtest.controllers;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.eblue.springtest.beans.Contact;
import com.eblue.springtest.services.ContactDAO;



@Controller
@RequestMapping("VIEW")
public class SelectController {
	private static Log log = LogFactory.getLog(SelectController.class);
	
	@Autowired
	private ContactDAO contactDAO;
	
	@RequestMapping
	public String showIndex(RenderRequest request){
		System.out.println("HERE!!!");
		log.debug("Entering SelectController.handleActionRequest()");
		request.setAttribute("contactList", contactDAO.getContactList());
		log.debug("Exiting SelectController.handleActionRequest()");
		return "list";
	}
	
	@RequestMapping(params="action=update")
	public String updateContact(@RequestParam("contactId")String contactId,Model model){
		if (!model.containsAttribute("contact")){
			model.addAttribute("contact",contactDAO.getContact(contactId));
		}	
		return"update";
	}
	
	@RequestMapping(params="action=insert")
	public String insertContact(Model model){
		model.addAttribute("contact",new Contact());
		return"insert";
	}
	
	@ActionMapping(params="action=delete")
	public void deleteContact(ActionRequest request,ActionResponse response,@RequestParam("contactId")String contactId){
		log.debug("Entering Delete");
		contactDAO.deleteContact(contactId);
		response.setRenderParameter("action", "list");
		log.debug("Exiting Delete");
	}
	
	
	@ActionMapping(params="action=insert")
	public void handleInsertRequest(ActionRequest request, ActionResponse response,@ModelAttribute("contact")Contact contact){
		log.debug("Entering SelectController.handleActionRequest()");
		contactDAO.insertContact(contact);
		response.setRenderParameter("action", "list");
		log.debug("Exiting SelectController.handleActionRequest()");
	}
	
	@ActionMapping(params="action=update")
	public void handleUpdateRequest(ActionRequest request, ActionResponse response,@ModelAttribute("contact")Contact contact){
		log.debug("Entering SelectController.handleActionRequest()");
		contactDAO.updateContact(contact);
		response.setRenderParameter("action", "list");
		log.debug("Exiting SelectController.handleActionRequest()");
	}
	
	@ResourceMapping
	public String testResource(){
		System.out.println("HERE!!!");
		return"blank";
	}
	
	
	
}
