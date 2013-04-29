package com.manpower.portal.theme.ajax;

import java.util.Iterator;
import java.util.Stack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.portal.ModelException;
import com.ibm.portal.ObjectNotFoundException;
import com.ibm.portal.navigation.NavigationModel;
import com.ibm.portal.navigation.NavigationNode;
import com.ibm.wps.model.ModelUtil;
import com.ibm.wps.model.NavigationModelUtil;
import com.ibm.wps.model.factory.IsolationMode;


public class AjaxMenuAssistant {

	private static AjaxMenuAssistant menuAssistant;
	private ModelUtil modelUtil;
	private NavigationModel<NavigationNode> navModel;
	private NavigationModelUtil navUtil;
	
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public AjaxMenuAssistant(HttpServletRequest request) throws ModelException {
		modelUtil = ModelUtil.from(request);
		modelUtil.setIsolationMode(IsolationMode.LIVE);
        navModel = modelUtil.getNavigationModel();
        navUtil = NavigationModelUtil.from(request);
	}

	@SuppressWarnings("unchecked")
	public AjaxMenuAssistant(HttpServletRequest request,HttpServletResponse response)throws ModelException{
		modelUtil = ModelUtil.from(request, response);
		modelUtil.setIsolationMode(IsolationMode.LIVE);
        navModel = modelUtil.getNavigationModel();
        navUtil = NavigationModelUtil.from(request);
		
	}
	
	public static AjaxMenuAssistant getInstance(HttpServletRequest request){
		//if (menuAssistant==null){
			try{
				menuAssistant=new AjaxMenuAssistant(request);
			}catch (ModelException me){
				me.printStackTrace();
			}
		//}
		return menuAssistant;
	}
	public static AjaxMenuAssistant getInstance(HttpServletRequest request,HttpServletResponse response){
		//if (menuAssistant==null){
			try{
				menuAssistant=new AjaxMenuAssistant(request,response);
			}catch (ModelException me){
				me.printStackTrace();
			}
		//}
		return menuAssistant;
	}
	
	public NavigationNode getRootNode(String uniqueId){
		
		NavigationNode nnode = (NavigationNode)navModel.getLocator().findByUniqueName(uniqueId);
		return nnode;
		
	}
	
		
	public NavigationNode getRootNode(){
		NavigationNode node=null;
		try {
			node = (NavigationNode)navModel.getRoot();
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return node;
	}
	
	public Stack<NavigationNode> getChildren(HttpServletRequest request,NavigationNode node){
		Stack<NavigationNode> nodeStack = new Stack<NavigationNode>();
		try {
			for (Iterator<NavigationNode> children = navModel.getChildren(node);children.hasNext();){
				NavigationNode nnode = (NavigationNode)children.next();
				nodeStack.push(nnode);
			}
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nodeStack;
	}
	
	public boolean hasChildren(NavigationNode node){
		boolean hasChildren = false;
		try {
			hasChildren = navModel.hasChildren(node);
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hasChildren;
	}

	public String getSelectionChangeURL(NavigationNode node){
		return navUtil.createSelectionChangeURL(node);
	}

	
	
}
