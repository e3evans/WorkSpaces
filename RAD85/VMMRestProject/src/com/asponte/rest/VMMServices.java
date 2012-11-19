package com.asponte.rest;


import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.asponte.rest.services.SearchService;


@ApplicationPath("/resources")
public class VMMServices extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(SearchService.class);
		return classes;
	}
	
	
}
