package com.manpower.portal.mpnet.hbn.beans;

import java.util.Date;

/**
 * @author jsingh
 *
 * Persistent class for table CANDIDATEJOBAGENTS
 */
public class CandidateJobAgents extends Base{
	private static final long serialVersionUID =    -284682653608761439L;
	 private long 	id,
	 				siteId;
	 
	 private String agentName,
	 				jobIndustry,
					jobTitle,
					jobCountry,
					jobProximity,
					jobProximityUnit,
					contractType,
					contentType,
					frequency,
					useSkill,
					exactMatch,
					query,
					updatedBy,
					matchingJobs,
					sendEmail,
					jobLocation,
					jobLanguage,
					candidateSkills,
					hoursWorked,
					businessArea;

	 private Date	lastActive,
	 				updatedOn,
	 				emailSentDate;
	 
	 private Candidate candidate;
		 
	 /**
		 * @return agentName.
		 */
		public String getAgentName() {
			return agentName;
		}
		/**
		 * @param agentName.
		 */
		public void setAgentName(String agentName) {
			this.agentName = agentName;
		}
		/**
		 * @return  candidate.
		 */
		public Candidate getCandidate() {
			return candidate;
		}
		/**
		 * @param candidate.
		 */
		public void setCandidate(Candidate candidate) {
			this.candidate = candidate;
		}
		/**
		 * @return contractType.
		 */
		public String getContractType() {
			return contractType;
		}
		/**
		 * @param contractType.
		 */
		public void setContractType(String contractType) {
			this.contractType = contractType;
		}
		/**
		 * @return exactMatch.
		 */
		public String getExactMatch() {
			return exactMatch;
		}
		/**
		 * @param exactMatch.
		 */
		public void setExactMatch(String exactMatch) {
			this.exactMatch = exactMatch;
		}
		/**
		 * @return Returns the frequency.
		 */
		public String getFrequency() {
			return frequency;
		}
		/**
		 * @param frequency The frequency to set.
		 */
		public void setFrequency(String frequency) {
			this.frequency = frequency;
		}
		/**
		 * @return id.
		 */
		public long getId() {
			return id;
		}
		/**
		 * @param id.
		 */
		public void setId(long id) {
			this.id = id;
		}
		/**
		 * @return  jobCountry.
		 */
		public String getJobCountry() {
			return jobCountry;
		}
		/**
		 * @param jobCountry .
		 */
		public void setJobCountry(String jobCountry) {
			this.jobCountry = jobCountry;
		}
		/**
		 * @return jobIndustry.
		 */
		public String getJobIndustry() {
			return jobIndustry;
		}
		/**
		 * @param jobIndustry.
		 */
		public void setJobIndustry(String jobIndustry) {
			this.jobIndustry = jobIndustry;
		}
	

		/**
		 * @return jobTitle.
		 */
		public String getJobTitle() {
			return jobTitle;
		}
		/**
		 * @param jobTitle.
		 */
		public void setJobTitle(String jobTitle) {
			this.jobTitle = jobTitle;
		}
	
	/**
	 * @return Returns the contentType.
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * @param contentType.
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/**
	 * @return Returns the jobProximityUnit.
	 */
	public String getJobProximityUnit() {
		return jobProximityUnit;
	}
	/**
	 * @param jobProximityUnit.
	 */
	public void setJobProximityUnit(String jobProximityUnit) {
		this.jobProximityUnit = jobProximityUnit;
	}
	/**
	 * @return Returns the lastActive.
	 */
	public Date getLastActive() {
		return lastActive;
	}
	/**
	 * @param lastActive.
	 */
	public void setLastActive(Date lastActive) {
		this.lastActive = lastActive;
	}
		/**
		 * @return query.
		 */
		public String getQuery() {
			return query;
		}
		/**
		 * @param query.
		 */
		public void setQuery(String query) {
			this.query = query;
		}
		
		/**
		 * @return updatedBy.
		 */
		public String getUpdatedBy() {
			return updatedBy;
		}
		/**
		 * @param updatedBy.
		 */
		public void setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
		}
		/**
		 * @return updatedOn.
		 */
		public Date getUpdatedOn() {
			return updatedOn;
		}
		/**
		 * @param updatedOn.
		 */
		public void setUpdatedOn(Date updatedOn) {
			this.updatedOn = updatedOn;
		}
		/**
		 * @return useSkill.
		 */
		public String getUseSkill() {
			return useSkill;
		}
		/**
		 * @param useSkill.
		 */
		public void setUseSkill(String useSkill) {
			this.useSkill = useSkill;
		}
		
		
		
		/**
		 * @return matchingJobs.
		 */
		public String getMatchingJobs() {
			return matchingJobs;
		}
		/**
		 * @param matchingJobs.
		 */
		public void setMatchingJobs(String matchingJobs) {
			this.matchingJobs = matchingJobs;
		}

		
		
	/**
	 * @return Returns the jobProximity.
	 */
	public String getJobProximity() {
		return jobProximity;
	}
	/**
	 * @param jobProximity.
	 */
	public void setJobProximity(String jobProximity) {
		this.jobProximity = jobProximity;
	}
	/**
	 * @return Returns the sendEmail.
	 */
	public String getSendEmail() {
		return sendEmail;
	}
	/**
	 * @param sendEmail.
	 */
	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}
	
	
	/**
	 * @return Returns the siteId.
	 */
	public long getSiteId() {
		return siteId;
	}
	/**
	 * @param siteId.
	 */
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}	
	/**
	 * @return Returns the candidateSkills.
	 */
	public String getCandidateSkills() {
		return candidateSkills;
	}
	/**
	 * @param candidateSkills.
	 */
	public void setCandidateSkills(String candidateSkills) {
		this.candidateSkills = candidateSkills;
	}
	/**
	 * @return Returns the jobLanguage.
	 */
	public String getJobLanguage() {
		return jobLanguage;
	}
	/**
	 * @param jobLanguage.
	 */
	public void setJobLanguage(String jobLanguage) {
		this.jobLanguage = jobLanguage;
	}
	/**
	 * @return Returns the jobLocation.
	 */
	public String getJobLocation() {
		return jobLocation;
	}
	/**
	 * @param jobLocation.
	 */
	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}
	/**
	 * @return Returns the hoursWorked.
	 */
	public String getHoursWorked() {
		return hoursWorked;
	}
	/**
	 * @param hoursWorked The hoursWorked to set.
	 */
	public void setHoursWorked(String hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	/**
	 * @return Returns the businessArea.
	 */
	public String getBusinessArea() {
		return businessArea;
	}
	/**
	 * @param businessArea The businessArea to set.
	 */
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	/**
	 * @return Returns the emailSentDate.
	 */
	public Date getEmailSentDate() {
		return emailSentDate;
	}
	/**
	 * @param emailSentDate The emailSentDate to set.
	 */
	public void setEmailSentDate(Date emailSentDate) {
		this.emailSentDate = emailSentDate;
	}
}
