/*
 * Created on Oct 11, 2007
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.util.Date;

/**
 * @author ssprout1
 *
 * Persistent Class for table APPLICANTRESPONSEDETAIL
 */
public class ApplicantResponseDetail extends Base  {

	
	/*
 table APPLICANTRESPONSEDETAIL
(
  ID                  NUMBER not null,
  APPLICANTRESPONSEID NUMBER not null,
  NAME                VARCHAR2(100) not null,
  VALUE               VARCHAR2(500),
  CREATED_ON          DATE,
  CREATED_BY          VARCHAR2(200),
  CHANGED_ON          DATE,
  CHANGED_BY          VARCHAR2(200)
)
	 */
	private static final long serialVersionUID =8890609168076355639L;
	private long id;
	private long applicantResponseId;
	private String name;
	private String value;
	private Date created_On;
	private Date changed_On;
	
	
	public long getApplicantResponseId() {
		return applicantResponseId;
	}
	public void setApplicantResponseId(long applicantResponseId) {
		this.applicantResponseId = applicantResponseId;
	}
	public Date getChanged_On() {
		return changed_On;
	}
	public void setChanged_On(Date changed_On) {
		this.changed_On = changed_On;
	}
	public Date getCreated_On() {
		return created_On;
	}
	public void setCreated_On(Date created_On) {
		this.created_On = created_On;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
