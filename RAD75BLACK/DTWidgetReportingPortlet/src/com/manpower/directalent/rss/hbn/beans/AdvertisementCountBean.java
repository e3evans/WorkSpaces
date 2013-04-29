package com.manpower.directalent.rss.hbn.beans;

public class AdvertisementCountBean {

	private long id;
	private String sitename;
	private long count;
	private long liveCount;
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
	
	
	
	
}
