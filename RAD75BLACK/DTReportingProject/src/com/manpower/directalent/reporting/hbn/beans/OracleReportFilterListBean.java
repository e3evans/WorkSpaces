package com.manpower.directalent.reporting.hbn.beans;

import java.util.HashMap;

@SuppressWarnings("unchecked")
public class OracleReportFilterListBean {
	private long id;
	private String sitename;

	
		
	private HashMap<Object, Object> fieldNames = new HashMap();
	
	public HashMap<Object,Object> getFields(){
		fieldNames.put("id", getId());
		fieldNames.put("sitename",getSitename());
		return fieldNames;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public HashMap<Object, Object> getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(HashMap<Object, Object> fieldNames) {
		this.fieldNames = fieldNames;
	}



}
