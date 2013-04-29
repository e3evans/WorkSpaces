package com.manpower.widget.hbn.beans;

import java.util.Date;

public class TopTen {
	private long opp_id;
	private String opportunity_name;
	private String name1;
	private long est_revenue;
	private long gp;
	private String sales_stage;
	private String sales_user_name;
	private Date est_close_dt;
	
	public long getOpp_id() {
		return opp_id;
	}
	public void setOpp_id(long opp_id) {
		this.opp_id = opp_id;
	}
	public String getOpportunity_name() {
		return opportunity_name;
	}
	public void setOpportunity_name(String opportunity_name) {
		this.opportunity_name = opportunity_name;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public long getEst_revenue() {
		return est_revenue;
	}
	public void setEst_revenue(long est_revenue) {
		this.est_revenue = est_revenue;
	}
	public long getGp() {
		return gp;
	}
	public void setGp(long gp) {
		this.gp = gp;
	}
	public String getSales_stage() {
		return sales_stage;
	}
	public void setSales_stage(String sales_stage) {
		this.sales_stage = sales_stage;
	}
	public String getSales_user_name() {
		return sales_user_name;
	}
	public void setSales_user_name(String sales_user_name) {
		this.sales_user_name = sales_user_name;
	}
	public Date getEst_close_dt() {
		return est_close_dt;
	}
	public void setEst_close_dt(Date est_close_dt) {
		this.est_close_dt = est_close_dt;
	}
	
	

}
