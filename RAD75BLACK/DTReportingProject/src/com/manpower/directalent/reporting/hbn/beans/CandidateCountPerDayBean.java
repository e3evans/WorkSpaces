package com.manpower.directalent.reporting.hbn.beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;



public class CandidateCountPerDayBean extends BaseBean{

	private String id;
	private long site_id;
	private long count;
	private Date countdate;
	private String sitecode;
	

	@SuppressWarnings({ "unchecked" })
	private HashMap<Object, Object> fieldNames = new HashMap();
	
	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
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
	
	public Date getCountdate() {
		return countdate;
	}

	public String getCountDateString(){
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		return formatter.format(getCountdate());
		
	}
	public void setCountdate(Date countdate) {
		this.countdate = countdate;
	}

	public HashMap<Object,Object> getFields(){
		
		fieldNames.put("id", getId());
		fieldNames.put("site_id",getSite_id());
		fieldNames.put("count", getCount());
		fieldNames.put("flagpath", getFlagpath());
		fieldNames.put("sitecode", getSitecode());
		fieldNames.put("countdate", getCountDateString());
		return fieldNames;
		
	}
}
