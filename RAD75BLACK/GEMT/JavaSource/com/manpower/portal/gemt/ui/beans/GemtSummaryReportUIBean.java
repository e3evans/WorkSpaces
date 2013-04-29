/*
 * Created on Jan 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.gemt.ui.beans;

import java.util.Date;
import java.util.Map;

/**
 * @author Eric Evans
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GemtSummaryReportUIBean extends BaseUIBean {

	public static long TYPE_MID_YEAR_REPORT = 1;
	public static long TYPE_ANNUAL_REPORT = 2;
	public static long STATUS_ACTIVE = 1;
	public static long STATUS_ARCHIVED = 2;
	
	private long id;
	
	private String gemt_sum_empname; 	
	private String gemt_sum_emptitle;
	private String gemt_sum_managername;
	private String gemt_sum_locked;
	
	private Date gemt_sum_repdate;
	
	private long gemt_sum_managerid;
	private long gemt_sum_roleclient;
	private long gemt_sum_rolepeople;
	private long gemt_sum_rolethought;
	private long gemt_sum_roledaytoday;
	private long gemt_sum_overviewscorefinan;
	private long gemt_sum_overviewscorekpi;
	private long gemt_sum_overallperf;
	private long gemt_sum_period;
	private long gemt_sum_roleclient_expected;
	private long gemt_sum_rolepeople_expected;
	private long gemt_sum_rolethought_expected;
	private long gemt_sum_roledaytoday_expected;
	private long gemt_sum_status;
	
	private String gemt_sum_perfhighlights;
	private String gemt_sum_missedopps;
	private String gemt_sum_comments;
	private String gemt_sum_devstrengths;
	private String gemt_sum_devneeds;
	private String gemt_sum_devactions;
	private String gemt_sum_devpriorities;
	private String gemt_sum_empemail;
	private String gemt_sum_mgremail;
	private long gemt_parent_id;
	
	
	
	public long getGemt_sum_status() {
		return gemt_sum_status;
	}

	public void setGemt_sum_status(long gemt_sum_status) {
		this.gemt_sum_status = gemt_sum_status;
	}

	public String getGemt_sum_devpriorities() {
		return gemt_sum_devpriorities;
	}

	public void setGemt_sum_devpriorities(String gemt_sum_devpriorities) {
		this.gemt_sum_devpriorities = gemt_sum_devpriorities;
	}

	public long getGemt_parent_id() {
		return gemt_parent_id;
	}

	public void setGemt_parent_id(long gemt_parent_id) {
		this.gemt_parent_id = gemt_parent_id;
	}

	public long getGemt_sum_period() {
		return gemt_sum_period;
	}

	public void setGemt_sum_period(long gemt_sum_period) {
		this.gemt_sum_period = gemt_sum_period;
	}

	public String getGemt_sum_empemail() {
		return gemt_sum_empemail;
	}

	public void setGemt_sum_empemail(String gemt_sum_empemail) {
		this.gemt_sum_empemail = gemt_sum_empemail;
	}

	public String getGemt_sum_mgremail() {
		return gemt_sum_mgremail;
	}

	public void setGemt_sum_mgremail(String gemt_sum_mgremail) {
		this.gemt_sum_mgremail = gemt_sum_mgremail;
	}

	

	public GemtSummaryReportUIBean(String formBase,Map incomingFields){
		setPropreties(formBase,incomingFields,GemtSummaryReportUIBean.class);
		//System.out.println("TRYING SOMETHING:  "+this.gemt_sum_perfhighlights);
	}

	public GemtSummaryReportUIBean() {
	}

	public void convertHtmlVals(){
		this.gemt_sum_comments=getHtmlfromString(this.gemt_sum_comments);
		this.gemt_sum_perfhighlights=getHtmlfromString(this.gemt_sum_perfhighlights);
		this.gemt_sum_missedopps = getHtmlfromString(this.gemt_sum_missedopps);
		this.gemt_sum_devactions = getHtmlfromString(this.gemt_sum_devactions);
		this.gemt_sum_devneeds = getHtmlfromString(this.gemt_sum_devneeds);
		this.gemt_sum_devstrengths = getHtmlfromString(this.gemt_sum_devstrengths);
		this.gemt_sum_devpriorities=getHtmlfromString(this.gemt_sum_devpriorities);
	}

	/**
	 * @return Returns the id.
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return Returns the gemt_sum_comments.
	 */
	public String getGemt_sum_comments() {
		return gemt_sum_comments;
	}
	/**
	 * @param gemt_sum_comments The gemt_sum_comments to set.
	 */
	public void setGemt_sum_comments(String gemt_sum_comments) {
		this.gemt_sum_comments = gemt_sum_comments;
	}
	/**
	 * @return Returns the gemt_sum_devactions.
	 */
	public String getGemt_sum_devactions() {
		return gemt_sum_devactions;
	}
	/**
	 * @param gemt_sum_devactions The gemt_sum_devactions to set.
	 */
	public void setGemt_sum_devactions(String gemt_sum_devactions) {
		this.gemt_sum_devactions = gemt_sum_devactions;
	}
	/**
	 * @return Returns the gemt_sum_devneeds.
	 */
	public String getGemt_sum_devneeds() {
		return gemt_sum_devneeds;
	}
	/**
	 * @param gemt_sum_devneeds The gemt_sum_devneeds to set.
	 */
	public void setGemt_sum_devneeds(String gemt_sum_devneeds) {
		this.gemt_sum_devneeds = gemt_sum_devneeds;
	}
	/**
	 * @return Returns the gemt_sum_devstrengths.
	 */
	public String getGemt_sum_devstrengths() {
		return gemt_sum_devstrengths;
	}
	/**
	 * @param gemt_sum_devstrengths The gemt_sum_devstrengths to set.
	 */
	public void setGemt_sum_devstrengths(String gemt_sum_devstrengths) {
		this.gemt_sum_devstrengths = gemt_sum_devstrengths;
	}
	/**
	 * @return Returns the gemt_sum_empname.
	 */
	public String getGemt_sum_empname() {
		return gemt_sum_empname;
	}
	/**
	 * @param gemt_sum_empname The gemt_sum_empname to set.
	 */
	public void setGemt_sum_empname(String gemt_sum_empname) {
		this.gemt_sum_empname = gemt_sum_empname;
	}
	/**
	 * @return Returns the gemt_sum_emptitle.
	 */
	public String getGemt_sum_emptitle() {
		return gemt_sum_emptitle;
	}
	/**
	 * @param gemt_sum_emptitle The gemt_sum_emptitle to set.
	 */
	public void setGemt_sum_emptitle(String gemt_sum_emptitle) {
		this.gemt_sum_emptitle = gemt_sum_emptitle;
	}
	/**
	 * @return Returns the gemt_sum_locked.
	 */
	public String getGemt_sum_locked() {
		return gemt_sum_locked;
	}
	/**
	 * @param gemt_sum_locked The gemt_sum_locked to set.
	 */
	public void setGemt_sum_locked(String gemt_sum_locked) {
		this.gemt_sum_locked = gemt_sum_locked;
	}
	/**
	 * @return Returns the gemt_sum_managerid.
	 */
	public long getGemt_sum_managerid() {
		return gemt_sum_managerid;
	}
	/**
	 * @param gemt_sum_managerid The gemt_sum_managerid to set.
	 */
	public void setGemt_sum_managerid(long gemt_sum_managerid) {
		this.gemt_sum_managerid = gemt_sum_managerid;
	}
	/**
	 * @return Returns the gemt_sum_managername.
	 */
	public String getGemt_sum_managername() {
		return gemt_sum_managername;
	}
	/**
	 * @param gemt_sum_managername The gemt_sum_managername to set.
	 */
	public void setGemt_sum_managername(String gemt_sum_managername) {
		this.gemt_sum_managername = gemt_sum_managername;
	}
	/**
	 * @return Returns the gemt_sum_missedopps.
	 */
	public String getGemt_sum_missedopps() {
		return gemt_sum_missedopps;
	}
	/**
	 * @param gemt_sum_missedopps The gemt_sum_missedopps to set.
	 */
	public void setGemt_sum_missedopps(String gemt_sum_missedopps) {
		this.gemt_sum_missedopps = gemt_sum_missedopps;
	}
	/**
	 * @return Returns the gemt_sum_overallperf.
	 */
	public long getGemt_sum_overallperf() {
		return gemt_sum_overallperf;
	}
	/**
	 * @param gemt_sum_overallperf The gemt_sum_overallperf to set.
	 */
	public void setGemt_sum_overallperf(long gemt_sum_overallperf) {
		this.gemt_sum_overallperf = gemt_sum_overallperf;
	}
	/**
	 * @return Returns the gemt_sum_overviewscorefinan.
	 */
	public long getGemt_sum_overviewscorefinan() {
		return gemt_sum_overviewscorefinan;
	}
	/**
	 * @param gemt_sum_overviewscorefinan The gemt_sum_overviewscorefinan to set.
	 */
	public void setGemt_sum_overviewscorefinan(long gemt_sum_overviewscorefinan) {
		this.gemt_sum_overviewscorefinan = gemt_sum_overviewscorefinan;
	}
	/**
	 * @return Returns the gemt_sum_overviewscorekpi.
	 */
	public long getGemt_sum_overviewscorekpi() {
		return gemt_sum_overviewscorekpi;
	}
	/**
	 * @param gemt_sum_overviewscorekpi The gemt_sum_overviewscorekpi to set.
	 */
	public void setGemt_sum_overviewscorekpi(long gemt_sum_overviewscorekpi) {
		this.gemt_sum_overviewscorekpi = gemt_sum_overviewscorekpi;
	}
	/**
	 * @return Returns the gemt_sum_perfhighlights.
	 */
	public String getGemt_sum_perfhighlights() {
		return gemt_sum_perfhighlights;
	}
	/**
	 * @param gemt_sum_perfhighlights The gemt_sum_perfhighlights to set.
	 */
	public void setGemt_sum_perfhighlights(String gemt_sum_perfhighlights) {
		this.gemt_sum_perfhighlights = gemt_sum_perfhighlights;
	}
	/**
	 * @return Returns the gemt_sum_repdate.
	 */
	public Date getGemt_sum_repdate() {
		return gemt_sum_repdate;
	}
	/**
	 * @param gemt_sum_repdate The gemt_sum_repdate to set.
	 */
	public void setGemt_sum_repdate(Date gemt_sum_repdate) {
		this.gemt_sum_repdate = gemt_sum_repdate;
	}
	/**
	 * @return Returns the gemt_sum_roleclient.
	 */
	public long getGemt_sum_roleclient() {
		return gemt_sum_roleclient;
	}
	/**
	 * @param gemt_sum_roleclient The gemt_sum_roleclient to set.
	 */
	public void setGemt_sum_roleclient(long gemt_sum_roleclient) {
		this.gemt_sum_roleclient = gemt_sum_roleclient;
	}
	/**
	 * @return Returns the gemt_sum_roledaytoday.
	 */
	public long getGemt_sum_roledaytoday() {
		return gemt_sum_roledaytoday;
	}
	/**
	 * @param gemt_sum_roledaytoday The gemt_sum_roledaytoday to set.
	 */
	public void setGemt_sum_roledaytoday(long gemt_sum_roledaytoday) {
		this.gemt_sum_roledaytoday = gemt_sum_roledaytoday;
	}
	/**
	 * @return Returns the gemt_sum_rolepeople.
	 */
	public long getGemt_sum_rolepeople() {
		return gemt_sum_rolepeople;
	}
	/**
	 * @param gemt_sum_rolepeople The gemt_sum_rolepeople to set.
	 */
	public void setGemt_sum_rolepeople(long gemt_sum_rolepeople) {
		this.gemt_sum_rolepeople = gemt_sum_rolepeople;
	}
	/**
	 * @return Returns the gemt_sum_rolethought.
	 */
	public long getGemt_sum_rolethought() {
		return gemt_sum_rolethought;
	}
	/**
	 * @param gemt_sum_rolethought The gemt_sum_rolethought to set.
	 */
	public void setGemt_sum_rolethought(long gemt_sum_rolethought) {
		this.gemt_sum_rolethought = gemt_sum_rolethought;
	}

	public long getGemt_sum_roleclient_expected() {
		return gemt_sum_roleclient_expected;
	}

	public void setGemt_sum_roleclient_expected(long gemt_sum_roleclient_expected) {
		this.gemt_sum_roleclient_expected = gemt_sum_roleclient_expected;
	}

	public long getGemt_sum_roledaytoday_expected() {
		return gemt_sum_roledaytoday_expected;
	}

	public void setGemt_sum_roledaytoday_expected(
			long gemt_sum_roledaytoday_expected) {
		this.gemt_sum_roledaytoday_expected = gemt_sum_roledaytoday_expected;
	}

	public long getGemt_sum_rolepeople_expected() {
		return gemt_sum_rolepeople_expected;
	}

	public void setGemt_sum_rolepeople_expected(long gemt_sum_rolepeople_expected) {
		this.gemt_sum_rolepeople_expected = gemt_sum_rolepeople_expected;
	}

	public long getGemt_sum_rolethought_expected() {
		return gemt_sum_rolethought_expected;
	}

	public void setGemt_sum_rolethought_expected(long gemt_sum_rolethought_expected) {
		this.gemt_sum_rolethought_expected = gemt_sum_rolethought_expected;
	}
	
	
}
