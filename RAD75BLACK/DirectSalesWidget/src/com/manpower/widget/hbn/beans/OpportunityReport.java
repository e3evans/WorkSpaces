package com.manpower.widget.hbn.beans;

public class OpportunityReport {
	public long opp_id;
	private String sales_stage;
	private long est_revenue;
	
	
	public String getSales_stage() {
		return sales_stage;
	}
	
	public long getOpp_id() {
		return opp_id;
	}
	public void setOpp_id(long opp_id) {
		this.opp_id = opp_id;
	}
	public void setSales_stage(String sales_stage) {
		this.sales_stage = sales_stage;
	}
	public long getEst_revenue() {
		return est_revenue;
	}
	public void setEst_revenue(long est_revenue) {
		this.est_revenue = est_revenue;
	}
	
}
