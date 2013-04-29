package com.manpower.directalent.rss.hbn.beans;

import java.io.Serializable;

public class ReportContent implements Serializable{

	private static final long serialVersionUID = 468531707796196176L;

	private Long id;

	private String reportContent;

	private String mimeType;
	
	private String reportName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	
	
	
}
