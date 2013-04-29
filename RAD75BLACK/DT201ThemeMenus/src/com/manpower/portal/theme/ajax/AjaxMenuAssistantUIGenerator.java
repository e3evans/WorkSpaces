package com.manpower.portal.theme.ajax;

import java.util.Iterator;
import java.util.Stack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.portal.navigation.NavigationNode;
import com.ibm.wps.model.LocaleHelper;

public class AjaxMenuAssistantUIGenerator {
	
	public static String getMenuCell(HttpServletRequest req, String uniqueId,String hideword){
		NavigationNode temp = AjaxMenuAssistant.getInstance(req).getRootNode(uniqueId);
		StringBuffer sb = new StringBuffer();
		if (temp!=null){

			sb.append("<div class=\"footerList\">");
			sb.append("<h6>");
			sb.append("<a href=\""+AjaxMenuAssistant.getInstance(req).getSelectionChangeURL(temp)+"\" class=\"menuPortLinkHead\">");
			sb.append(LocaleHelper.getTitle(temp, req));
			sb.append("</a>");
			sb.append("</h6>");


			
			if (AjaxMenuAssistant.getInstance(req).hasChildren(temp)){
				NavigationNode child = null;
				Stack<?> childStack = AjaxMenuAssistant.getInstance(req).getChildren(req, temp);
				Iterator<?> children = childStack.iterator();
				while (children.hasNext()){
					child = (NavigationNode)children.next();
					boolean showme = true;
					if (child.getObjectID().getUniqueName()!=null){
						if (child.getObjectID().getUniqueName().indexOf(hideword)>-1)showme=false;
						if (LocaleHelper.getTitle(child, req).indexOf(hideword)>-1)showme=false;
					}
					if (showme){
						sb.append("<a href=\""+AjaxMenuAssistant.getInstance(req).getSelectionChangeURL(child)+"\" class=\"menuPortLink\">");
						sb.append(LocaleHelper.getTitle(child, req));
						sb.append("</a>");
						if (children.hasNext())sb.append("<br/>");
					}
				}
				
			}
			sb.append("</div>");
		}
		return sb.toString();
		
	}
	public static String getMenuNode(HttpServletRequest req,HttpServletResponse resp, String uniqueID, String hideword){
		NavigationNode temp = AjaxMenuAssistant.getInstance(req,resp).getRootNode(uniqueID);
		StringBuffer sb = new StringBuffer();
		if (temp!=null){
			if (AjaxMenuAssistant.getInstance(req).hasChildren(temp)){
				sb.append("<ul>");
				NavigationNode child = null;
				Stack<?> childStack = AjaxMenuAssistant.getInstance(req,resp).getChildren(req, temp);
				Iterator<?> children = childStack.iterator();
				while (children.hasNext()){
					child = (NavigationNode)children.next();
					boolean showme = true;
					if (child.getObjectID().getUniqueName()!=null){
						if (child.getObjectID().getUniqueName().indexOf(hideword)>-1)showme=false;
						if (LocaleHelper.getTitle(child, req).indexOf(hideword)>-1)showme=false;
					}
					if (showme){
						sb.append("<li>");
						sb.append("<a href=\""+AjaxMenuAssistant.getInstance(req,resp).getSelectionChangeURL(child)+"\">");
						sb.append( LocaleHelper.getTitle(child, req));
						sb.append("</a>");
						sb.append(getMenuSubNodes(req,resp, child, hideword));
						sb.append("</li>");

					}
				}
				sb.append("</ul>");
			}
		}
		
		return sb.toString();
		
	}

	public static String getMenuNode(HttpServletRequest req, String uniqueID, String hideword){
		NavigationNode temp = AjaxMenuAssistant.getInstance(req).getRootNode(uniqueID);
		StringBuffer sb = new StringBuffer();
		if (temp!=null){
			if (AjaxMenuAssistant.getInstance(req).hasChildren(temp)){
				sb.append("<ul>");
				NavigationNode child = null;
				Stack<?> childStack = AjaxMenuAssistant.getInstance(req).getChildren(req, temp);
				Iterator<?> children = childStack.iterator();
				while (children.hasNext()){
					child = (NavigationNode)children.next();
					boolean showme = true;
					if (child.getObjectID().getUniqueName()!=null){
						if (child.getObjectID().getUniqueName().indexOf(hideword)>-1)showme=false;
						if (LocaleHelper.getTitle(child, req).indexOf(hideword)>-1)showme=false;
					}
					if (showme){
						sb.append("<li>");
						sb.append("<a href=\""+AjaxMenuAssistant.getInstance(req).getSelectionChangeURL(child)+"\">");
						sb.append( LocaleHelper.getTitle(child, req));
						sb.append("</a>");
						sb.append(getMenuSubNodes(req, child, hideword));
						sb.append("</li>");

					}
				}
				sb.append("</ul>");
			}
		}
		
		return sb.toString();
		
	}
	public static String getMenuSubNodes(HttpServletRequest req, NavigationNode node,String hideword){
		StringBuffer sb = new StringBuffer();
		
		Stack<?> childStack = AjaxMenuAssistant.getInstance(req).getChildren(req, node);
		Iterator<?> children = childStack.iterator();
		NavigationNode child = null;
		while (children.hasNext()){
			child = (NavigationNode)children.next();
			boolean showme = true;
			if (child.getObjectID().getUniqueName()!=null){
				if (child.getObjectID().getUniqueName().indexOf(hideword)>-1)showme=false;
				if (LocaleHelper.getTitle(child, req).indexOf(hideword)>-1)showme=false;
			}
			if (showme){
				sb.append("<li>");
				sb.append("<a href=\""+AjaxMenuAssistant.getInstance(req).getSelectionChangeURL(child)+"\">");
				sb.append( LocaleHelper.getTitle(child, req));
				sb.append("</a></li>");
			}
		}
		if (sb.length()>0){
			sb.append("</ul>");
			sb.insert(0,"<ul>");
		}
		return sb.toString();
	}
	
	public static String getMenuSubNodes(HttpServletRequest req,HttpServletResponse resp, NavigationNode node,String hideword){
		StringBuffer sb = new StringBuffer();
		
		Stack<?> childStack = AjaxMenuAssistant.getInstance(req,resp).getChildren(req, node);
		Iterator<?> children = childStack.iterator();
		NavigationNode child = null;
		while (children.hasNext()){
			child = (NavigationNode)children.next();
			boolean showme = true;
			if (child.getObjectID().getUniqueName()!=null){
				if (child.getObjectID().getUniqueName().indexOf(hideword)>-1)showme=false;
				if (LocaleHelper.getTitle(child, req).indexOf(hideword)>-1)showme=false;
			}
			if (showme){
				sb.append("<li>");
				sb.append("<a href=\""+AjaxMenuAssistant.getInstance(req).getSelectionChangeURL(child)+"\">");
				sb.append( LocaleHelper.getTitle(child, req));
				sb.append("</a></li>");
			}
		}
		if (sb.length()>0){
			sb.append("</ul>");
			sb.insert(0,"<ul>");
		}
		return sb.toString();
	}
	
	


}
