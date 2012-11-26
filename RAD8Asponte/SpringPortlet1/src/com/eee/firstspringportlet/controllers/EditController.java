package com.eee.firstspringportlet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("EDIT")
public class EditController {

	@RequestMapping
	public String showEdit(){
		return "editPage";
	}
}
