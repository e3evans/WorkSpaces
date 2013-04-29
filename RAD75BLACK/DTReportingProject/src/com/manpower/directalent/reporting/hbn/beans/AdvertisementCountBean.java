package com.manpower.directalent.reporting.hbn.beans;

import java.util.HashMap;

@SuppressWarnings("unchecked")
public class AdvertisementCountBean extends BaseBean{

	private long id;
	private String sitename;
	private String sitecode;
	private long count;
	private long liveCount;
	
	private HashMap<Object, Object> fieldNames = new HashMap();
	
	public HashMap<Object,Object> getFields(){
		fieldNames.put("id", getId());
		fieldNames.put("sitename", getSitename());
		fieldNames.put("count", getCount());
		fieldNames.put("livecount", getLiveCount());
		fieldNames.put("sitecode", getSitecode());
		fieldNames.put("flagpath", getFlagpath());
		return fieldNames;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getLiveCount() {
		return liveCount;
	}
	public void setLiveCount(long liveCount) {
		this.liveCount = liveCount;
	}
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	public String getSitecode() {
		return sitecode;
	}
	public void setSitecode(String sitecode) {
		this.sitecode = sitecode;
	}
	
	
	
	
}
