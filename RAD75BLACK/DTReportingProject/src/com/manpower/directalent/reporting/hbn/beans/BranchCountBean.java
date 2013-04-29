package com.manpower.directalent.reporting.hbn.beans;

import java.util.HashMap;

@SuppressWarnings("unchecked")
public class BranchCountBean extends BaseBean {
	private String id;
	private long site_id;
	private String sitecode;
	private long count;
	
	private HashMap<Object, Object> fieldNames = new HashMap();
	
	public HashMap<Object,Object> getFields(){
		fieldNames.put("id", getId());
		fieldNames.put("siteid", getSite_id());
		fieldNames.put("count", getCount());
		return fieldNames;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getSite_id() {
		return site_id;
	}
	public void setSite_id(long site_id) {
		this.site_id = site_id;
	}
	public String getSitecode() {
		return sitecode;
	}
	public void setSitecode(String sitecode) {
		this.sitecode = sitecode;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
	
	
}
