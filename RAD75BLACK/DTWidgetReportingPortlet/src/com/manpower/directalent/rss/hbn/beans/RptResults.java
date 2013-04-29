package com.manpower.directalent.rss.hbn.beans;

import java.util.Date;

public class RptResults {
		private long rpt_id; 
		private String rpt_wk_range; 
		private String rpt_proc_name; 
		private String rpt_xls_name; 
		private Date rpt_ins_date;
		public long getRpt_id() {
			return rpt_id;
		}
		public void setRpt_id(long rpt_id) {
			this.rpt_id = rpt_id;
		}
		public Date getRpt_ins_date() {
			return rpt_ins_date;
		}
		public void setRpt_ins_date(Date rpt_ins_date) {
			this.rpt_ins_date = rpt_ins_date;
		}
		public String getRpt_proc_name() {
			return rpt_proc_name;
		}
		public void setRpt_proc_name(String rpt_proc_name) {
			this.rpt_proc_name = rpt_proc_name;
		}
		public String getRpt_wk_range() {
			return rpt_wk_range;
		}
		public void setRpt_wk_range(String rpt_wk_range) {
			this.rpt_wk_range = rpt_wk_range;
		}
		public String getRpt_xls_name() {
			return rpt_xls_name;
		}
		public void setRpt_xls_name(String rpt_xls_name) {
			this.rpt_xls_name = rpt_xls_name;
		}
	

	
}
