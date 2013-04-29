package com.manpower.portal.portlet.directtalent_recruitersearch.utility;

import org.json.me.JSONException;
import org.json.me.JSONObject;

import com.manpower.hbn.beans.ParameterMapKey;

public class JsonUtil {
	
	public static String getSearchString(String[] params) {
		JSONObject jObj = new JSONObject();
		try {
			jObj.put(String.valueOf(ParameterMapKey.keywords_str), params[ParameterMapKey.keywords]);
			jObj.put(String.valueOf(ParameterMapKey.prefBranch_str), params[ParameterMapKey.prefBranch]);
			jObj.put(String.valueOf(ParameterMapKey.prefLocation_str), params[ParameterMapKey.prefLocation]);
			jObj.put(String.valueOf(ParameterMapKey.inclCand_str), params[ParameterMapKey.inclCand]);
			jObj.put(String.valueOf(ParameterMapKey.inclApp_str), params[ParameterMapKey.inclApp]);
			jObj.put(String.valueOf(ParameterMapKey.inclgt30_str), params[ParameterMapKey.inclgt30]);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jObj.toString();
	}
	
	public static String getValue(String jsonString, String value) {
		String result = "";
		try {
			JSONObject obj = new JSONObject(jsonString);
			result = obj.getString(value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}
}
