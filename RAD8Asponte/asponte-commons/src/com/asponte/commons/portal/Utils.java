package com.asponte.commons.portal;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Utils {
	private Utils(){}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static final Map<String,Object> fileparams(HttpServletRequest request) throws FileUploadException {
		Map<String,Object> requestParams=new HashMap<String,Object>(request.getParameterMap());
		if(ServletFileUpload.isMultipartContent(request)){
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload sfu=new ServletFileUpload(factory);
			List<FileItem> fileItems=sfu.parseRequest(request);
			for(FileItem fi:fileItems){
				if(fi.isFormField()){
					requestParams.put(fi.getFieldName(),fi.getString());
				}else{
					requestParams.put(fi.getFieldName(), fi);
				}
			}
		}
		return requestParams;
	}
	public static final Object param(Map<String,Object>params,String param,Object alt){
		Object o=params.get(param);
		if(o!=null){
			if(o instanceof String){
				o=((String)o).trim();
			}else if(o instanceof String[]){
				o=((String[])o)[0].trim();
			}
		}else{
			o=alt;
		}
		return o;
	}
	public static final Object param(Map<String,Object>params,String param){
		return param(params,param,null);
	}
	
	public static String param(HttpServletRequest request,String name){
		String s=request.getParameter(name);
		if(s!=null){
			s=s.trim();
		}
		return s;
	}
	
	public static String param(PortletRequest request,String name){
		String s=request.getParameter(name);
		if(s!=null){
			s=s.trim();
		}
		return s;
	}
	
	
	public static boolean empty(String s){
		return(s==null||s.length()==0);
	}

	public static String formatString(String string, Object[] objects) {
		if(objects!=null&&objects.length>0){
			return MessageFormat.format(string, objects);
		}else{
			return string;
		}
	}
	
	public static String join(String []ary,String delim){
		StringBuffer sb=new StringBuffer();
		for(int ii=0;ii<ary.length;ii++){
			if(ii>0){sb.append(delim);}
			sb.append(ary[ii]);
		}
		return sb.toString();
	}
}
