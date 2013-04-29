package com.manpower.directaltent.reporting.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class JSONUtils {
	
	@SuppressWarnings("unchecked")
	public static JSONObject getJSONArray(String arrayName,List<Object>resultsList) throws JSONException{
		JSONObject jobj = new JSONObject();
		JSONArray jarray = new JSONArray();
		
		for (int i=0;i<resultsList.size();i++){
			Object dataBean = resultsList.get(i);
			try {
				Method dataMethod = dataBean.getClass().getMethod("getFields");
				HashMap<Object,Object> tempFields = (HashMap<Object,Object>)dataMethod.invoke(dataBean);
				jarray.put(getJSONObject(tempFields));
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		jobj.put(arrayName, jarray);
		return jobj;
	}
	
	private static JSONObject getJSONObject(HashMap<Object,Object>fields) throws JSONException{
		JSONObject jobj = new JSONObject();
		Iterator<Object>i=fields.keySet().iterator();
		while(i.hasNext()){
			String key = (String) i.next();
			jobj.put(key, fields.get(key));
		}
		return jobj;
		
	}
	

}
