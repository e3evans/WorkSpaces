/*
 * Created on Jan 20, 2006
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.util.Date;

/**
 * @author jsingh
 *
 * Persistent class for table CANDIDATEPREFERENCES
 */
public class CandidatePreferences extends Base{
	private static final long serialVersionUID =  -7977281869918565353L;

	 private long 		id,
						siteId;

	private String 	contactMethod,
					contractType,
					jobTitle,
					positionType,
					distanceUnits,
					industrySector,
					workShifts,
					Flexhours,
					introToManpower,
					workType,
					updatedBy,
					relocateCountry,
					relocateInternational,
					location,
					proximity,
					preferedRole,
					hoursPerWeek;
	
	private Date 	updatedOn,
					startDate;
	
	private Candidate candidate;

	/**
	 * @return Returns the contractType.
	 */
	public String getContractType() {
		return contractType;
	}
	/**
	 * @param contractType The contractType to set.
	 */
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	/**
	 * @return Returns the candidate.
	 */
	public Candidate getCandidate() {
		return candidate;
	}
	/**
	 * @param candidate The candidate to set.
	 */
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	/**
	 * @return Returns the contactMethod.
	 */
	public String getContactMethod() {
		return contactMethod;
	}
	/**
	 * @param contactMethod The contactMethod to set.
	 */
	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}
	/**
	 * @return Returns the contarctType.
	 */
	public String getContarctType() {
		return contractType;
	}
	/**
	 * @param contarctType The contarctType to set.
	 */
	public void setContarctType(String contarctType) {
		contractType = contarctType;
	}
	/**
	 * @return Returns the distanceUnits.
	 */
	public String getDistanceUnits() {
		return distanceUnits;
	}
	/**
	 * @param distanceUnits The distanceUnits to set.
	 */
	public void setDistanceUnits(String distanceUnits) {
		this.distanceUnits = distanceUnits;
	}
	/**
	 * @return Returns the flexhours.
	 */
	public String getFlexhours() {
		return Flexhours;
	}
	/**
	 * @param flexhours The flexhours to set.
	 */
	public void setFlexhours(String flexhours) {
		Flexhours = flexhours;
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
	 * @return Returns the industrySector.
	 */
	public String getIndustrySector() {
		return industrySector;
	}
	/**
	 * @param industrySector The industrySector to set.
	 */
	public void setIndustrySector(String industrySector) {
		this.industrySector = industrySector;
	}
	/**
	 * @return Returns the introToManpower.
	 */
	public String getIntroToManpower() {
		return introToManpower;
	}
	/**
	 * @param introToManpower The introToManpower to set.
	 */
	public void setIntroToManpower(String introToManpower) {
		this.introToManpower = introToManpower;
	}
	/**
	 * @return Returns the jobTitle.
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	/**
	 * @param jobTitle The jobTitle to set.
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	/**
	 * @return Returns the positionType.
	 */
	public String getPositionType() {
		return positionType;
	}
	/**
	 * @param positionType The positionType to set.
	 */
	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}
	/**
	 * @return Returns the siteId.
	 */
	public long getSiteId() {
		return siteId;
	}
	/**
	 * @param siteId The siteId to set.
	 */
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}
	/**
	 * @return Returns the updatedBy.
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy The updatedBy to set.
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return Returns the updatedOn.
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}
	/**
	 * @param updatedOn The updatedOn to set.
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	/**
	 * @return Returns the workShifts.
	 */
	public String getWorkShifts() {
		return workShifts;
	}
	/**
	 * @param workShifts The workShifts to set.
	 */
	public void setWorkShifts(String workShifts) {
		this.workShifts = workShifts;
	}
	/**
	 * @return Returns the workType.
	 */
	public String getWorkType() {
		return workType;
	}
	/**
	 * @param workType The workType to set.
	 */
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	/**
	 * @return Returns the hoursPerWeek.
	 */
	public String getHoursPerWeek() {
		return hoursPerWeek;
	}
	/**
	 * @return Returns the startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param hoursPerWeek The hoursPerWeek to set.
	 */
	public void setHoursPerWeek(String hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return Returns the location.
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @return Returns the proximity.
	 */
	public String getProximity() {
		return proximity;
	}
	/**
	 * @return Returns the relocateCountry.
	 */
	public String getRelocateCountry() {
		return relocateCountry;
	}
	/**
	 * @return Returns the relocateInternational.
	 */
	public String getRelocateInternational() {
		return relocateInternational;
	}
	/**
	 * @param location The location to set.
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @param proximity The proximity to set.
	 */
	public void setProximity(String proximity) {
		this.proximity = proximity;
	}
	/**
	 * @param relocateCountry The relocateCountry to set.
	 */
	public void setRelocateCountry(String relocateCountry) {
		this.relocateCountry = relocateCountry;
	}
	/**
	 * @param relocateInternational The relocateInternational to set.
	 */
	public void setRelocateInternational(String relocateInternational) {
		this.relocateInternational = relocateInternational;
	}
	/**
	 * @return Returns the preferedRole.
	 */
	public String getPreferedRole() {
		return preferedRole;
	}
	/**
	 * @param preferedRole The preferedRole to set.
	 */
	public void setPreferedRole(String preferedRole) {
		this.preferedRole = preferedRole;
	}
}
