package com.manpower.directalent.reporting.hbn.beans;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@SuppressWarnings("unchecked")
public class OracleReportFileBean {
	private long id;
	private String rpt_name;
	private String report;
	private String mime_type;
	private String sitename;
	private String rpt_descr;
	private Date created_on;
	
		
	private HashMap<Object, Object> fieldNames = new HashMap();
	
	public HashMap<Object,Object> getFields(){
		fieldNames.put("id", getId());
		fieldNames.put("rpt_name", getRpt_name());
		fieldNames.put("mime_type", getMime_type());
		fieldNames.put("sitename",getSitename());
		fieldNames.put("rpt_descr", getRpt_descr());
		Format formatter = new SimpleDateFormat("dd/MMMM/YYYY");    
		fieldNames.put("created_on", formatter.format(getCreated_on()));
		return fieldNames;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRpt_name() {
		return rpt_name;
	}

	public void setRpt_name(String rpt_name) {
		this.rpt_name = rpt_name;
	}

	public String getMime_type() {
		return mime_type;
	}

	public void setMime_type(String mime_type) {
		this.mime_type = mime_type;
	}

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public String getRpt_descr() {
		return rpt_descr;
	}

	public void setRpt_descr(String rpt_descr) {
		this.rpt_descr = rpt_descr;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public HashMap<Object, Object> getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(HashMap<Object, Object> fieldNames) {
		this.fieldNames = fieldNames;
	}
	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}



}
