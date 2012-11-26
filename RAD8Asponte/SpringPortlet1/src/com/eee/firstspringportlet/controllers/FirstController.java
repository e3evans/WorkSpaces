package com.eee.firstspringportlet.controllers;

import javax.portlet.RenderRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;


@Controller
@RequestMapping("VIEW")
public class FirstController {

	@RequestMapping
	public String showIndex(){
		return "index";
	}
	
	@RequestMapping(params="page=page2")
	public String showSecondPage(@RequestParam("action")String myexample,RenderRequest request){
		System.out.println("myexample = " +myexample);
		System.out.println("request = "+request);
		return "secondPage";
	}
	
	@ActionMapping
	public void createAction(){
		System.out.println("ACTION WAS CALLED!!");
	}
	
}
