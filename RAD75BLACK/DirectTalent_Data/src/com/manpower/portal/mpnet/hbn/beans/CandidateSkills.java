/*
 * Created on Jan 23, 2006
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.util.Date;

/**
 * @author jsingh
 *
 * Persistent class for table CANDIDATESKILLS
 */
public class CandidateSkills extends Base{
	
	private static final long serialVersionUID =    -7882893838271834995L;
	private long 	id,
					siteId,
					skillId;
	
	 private Date  	lastUsed,
	 				updatedOn;
	 
	 private String level = "",
	 				yearsOfExpirience,
					updatedBy;
	 
	 private Candidate candidate;
	
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
	 * @return Returns the lastUsed.
	 */
	public Date getLastUsed() {
		return lastUsed;
	}
	/**
	 * @param lastUsed The lastUsed to set.
	 */
	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}
	/**
	 * @return Returns the level.
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * @param level The level to set.
	 */
	public void setLevel(String level) {
		this.level = level;
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
	 * @return Returns the yearsOfExpirience.
	 */
	public String getYearsOfExpirience() {
		return yearsOfExpirience;
	}
	/**
	 * @param yearsOfExpirience The yearsOfExpirience to set.
	 */
	public void setYearsOfExpirience(String yearsOfExpirience) {
		this.yearsOfExpirience = yearsOfExpirience;
	}
	 
	/**
	 * @return Returns the skills_ID.
	 */
	public long getSkills_ID() {
		return skills_ID;
	}
	/**
	 * @param skills_ID The skills_ID to set.
	 */
	public void setSkills_ID(long skills_ID) {
		this.skills_ID = skills_ID;
	}
	 private long skills_ID;

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
	 * @return Returns the skillId.
	 */
	public long getSkillId() {
		return skillId;
	}
	/**
	 * @param skillId The skillId to set.
	 */
	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}
	
	/**
	 * Compare with CandidateSkills object
	 */
	public boolean equals(Object val){
	
		
		if(val == null){
		
			return false;
			
		}else if(val == this){
		
			return true;
			
		}else if(val instanceof CandidateSkills){
			
			CandidateSkills skill = (CandidateSkills)val;
			
			return 	skill.getCandidate().equals(candidate) &&
					skill.getSkillId() == skillId &&
					skill.getSiteId() == siteId &&
					skill.getLevel().equals(level);
			
		}else{
		
			return false;
			
		}
	
	}
	
	/**
	 * @return Returns hash code
	 */
	public int hashCode(){
	
		
		return ("" + skillId + ", " + siteId + ", " + level + ", " + candidate.hashCode()).hashCode();
	
	}
}
