package com.asponte.portal.designer;

import java.util.ArrayList;
import java.util.List;

import com.asponte.commons.Result;

public class DesignerException extends Exception {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6482062037710677524L;

	private List<Result> results=new ArrayList<Result>();

	public DesignerException(){
		super();
	}
	public DesignerException(Result result) {
		super();
		this.results.add(result);
	}
	
	public DesignerException(Throwable cause) {
		super(cause);
	}

	public DesignerException(Result result, Throwable cause) {
		super(cause);
		this.results.add(result);
	}
	
	public void addResult(Result result){
		this.results.add(result);
	}
	
	public Result [] getResults(){
		Result []r=new Result[this.results.size()];
		return (Result[])this.results.toArray(r);
	}	
}
