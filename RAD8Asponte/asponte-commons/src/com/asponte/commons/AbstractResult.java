package com.asponte.commons;

import java.io.Serializable;

public abstract class AbstractResult implements Serializable,Result {
	private static final long serialVersionUID = -5882061114121663841L;
	private int severity;
	private String code;
	private Object []inserts;
	private Throwable throwable;
	public AbstractResult(int severity,String code){
		this(severity,code,null,null);
	}
	public AbstractResult(int severity,String code,Object []inserts){
		this(severity,code,inserts,null);
	}
	public AbstractResult(int severity,String code,Object []inserts,Throwable t){
		this.severity=severity;
		this.code=code;
		this.inserts=inserts;
		this.throwable=t;
	}
	/* (non-Javadoc)
	 * @see com.asponte.commons.Result#getSeverity()
	 */
	public int getSeverity() {
		return severity;
	}
	public void setSeverity(int severity) {
		this.severity = severity;
	}
	/* (non-Javadoc)
	 * @see com.asponte.commons.Result#getCode()
	 */
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	/* (non-Javadoc)
	 * @see com.asponte.commons.Result#getInserts()
	 */
	public Object[] getInserts() {
		return inserts;
	}
	public void setInserts(Object[] inserts) {
		this.inserts = inserts;
	}
	/* (non-Javadoc)
	 * @see com.asponte.commons.Result#getThrowable()
	 */
	public Throwable getThrowable() {
		return throwable;
	}
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
}
