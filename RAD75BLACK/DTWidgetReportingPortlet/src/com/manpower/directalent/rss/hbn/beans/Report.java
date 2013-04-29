package com.manpower.directalent.rss.hbn.beans;

import java.io.Serializable;
import java.util.Date;

public class Report implements Serializable{

	private static final long serialVersionUID = -173779908918320155L;

	private long id;

	private String reportName;

	private Date createdOn;
	
	private String reportDescription;
	
	private String siteName;
	
	private String mimeType;
	
	private String displayFlag;
	
	private long statId;
	
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getDisplayFlag() {
		return displayFlag;
	}

	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getReportDescription() {
		return reportDescription;
	}

	public void setReportDescription(String reportDescription) {
		this.reportDescription = reportDescription;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public long getStatId() {
		return statId;
	}

	public void setStatId(long statId) {
		this.statId = statId;
	}
}
