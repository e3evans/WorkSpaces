package com.manpower.portal.portlet.directoffice_sssportlet.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.manpower.directoffice.pojos.Address;

public abstract class JSONUtils {
	
	public static String formatDate(Date date){
		 String DATE_FORMAT = "yyyy-MM-dd";
//		 System.out.println("BEFORE FORMATTER: "+date);
		 SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
//		 System.out.println("FORMATTED DATE: "+sdf.format(date));
		 return sdf.format(date);
		
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject getJSONArray(String arrayName,List<Object>resultsList) throws JSONException{
		JSONObject jobj = new JSONObject();
		JSONArray jarray = new JSONArray();
		if (resultsList !=null){
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
		}
		jobj.put(arrayName, jarray);
		return jobj;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject getJSONArray(String arrayName,Object result) throws JSONException{
		JSONObject jobj = new JSONObject();
		JSONArray jarray = new JSONArray();
		
			try {
				Method dataMethod = result.getClass().getMethod("getFields");
//				System.out.println("CALLING METHOD  -- > "+)
				HashMap<Object,Object> tempFields = (HashMap<Object,Object>)dataMethod.invoke(result);
				
				jarray.put(getNestedJSONObject(tempFields));
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
			
	
		jobj.put(arrayName, jarray);
		return jobj;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject getComboBoxArray(String arrayName,Hashtable items,String dataname,String label) throws JSONException{
		JSONObject jobj = new JSONObject();
		JSONObject labelObj;
		JSONArray jarray = new JSONArray();
		
		for (Enumeration e = items.keys();e.hasMoreElements();){
			labelObj = new JSONObject();
			String temp = (String)e.nextElement();
			labelObj.put(dataname, temp);
			labelObj.put(label, (String)items.get(temp));
			jarray.put(labelObj);
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
	
	private static JSONObject getNestedJSONObject(HashMap<Object,Object>fields) throws JSONException{
		JSONObject jobj = new JSONObject();
		Iterator<Object>i=fields.keySet().iterator();
		while(i.hasNext()){
			String key = (String) i.next();
			if (fields.get(key)!=null){
				if (fields.get(key).getClass().isArray()){
					String [] temp = (String[]) fields.get(key);
					JSONArray jarray = new JSONArray();
					for (int x=0;x<temp.length;x++){
						jarray.put(temp[x]);
					}
					jobj.put(key, jarray);
				}else if (fields.get(key).getClass().toString().indexOf("com.manpower.directoffice.pojos.Address")>-1){
					Address candidateAddress = (Address)fields.get(key);
					JSONObject tempObj = new JSONObject();
					tempObj.put("addresstype",candidateAddress.getAddress_type());
					tempObj.put("addressline1",candidateAddress.getAddressLine1());
					tempObj.put("addressline2",candidateAddress.getAddressLine2());
					tempObj.put("addresscountry",candidateAddress.getCountry());
					tempObj.put("addressmunicipality",candidateAddress.getMunicipality());
					tempObj.put("addresspostalcode",candidateAddress.getPostal_code());
					tempObj.put("addressprovince",candidateAddress.getState_province());
					jobj.put(key, tempObj);
				}else{
					jobj.put(key, fields.get(key));
				}
			}else{
				jobj.put(key, "");
			}
			//DEBUG ONLY
//			else{
//				System.out.println(key +"---> is null");
//			}
			//END DEBUG
		}
		return jobj;
	}
	
	
	
	

}
