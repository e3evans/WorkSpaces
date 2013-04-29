/*
 * Created on Jul 17, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.util.Date;

/**
 * @author vindukur
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CandidateAssessments extends Base{

	/**
	 * 
	 */
	public CandidateAssessments() {
		super();
	}
	private static final long serialVersionUID = -3189869665915076842L;
	private long   id;
    private long   siteId;
    private long recruiter;
	private Date   assessmentDate;
	private String score;
	private String assessmentDetails;
	private String resultsLink;
	private String comments;
	private Date   createdOn;
	private String createdBy;
	private Date   changedOn;
	private String changedBy;
	private Date   updatedOn;
    private String updatedBy;
    private Candidate   candidate;
    
    private AssessmentProvider assessmentProvider = new AssessmentProvider();
    private AssessmentTest assessmentTest = new AssessmentTest();
    private AssessmentDeliveryFormat assessmentDeliveryFormat = new AssessmentDeliveryFormat();
    private AssessmentRating assessmentRating = new AssessmentRating();
    
	/**
	 * @return Returns the assessmentDetails.
	 */
	public String getAssessmentDetails() {
		return assessmentDetails;
	}
	/**
	 * @param assessmentDetails The assessmentDetails to set.
	 */
	public void setAssessmentDetails(String assessmentDetails) {
		this.assessmentDetails = assessmentDetails;
	}
	/**
	 * @return Returns the resultsLink.
	 */
	public String getResultsLink() {
		return resultsLink;
	}
	/**
	 * @param resultsLink The resultsLink to set.
	 */
	public void setResultsLink(String resultsLink) {
		this.resultsLink = resultsLink;
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
	 * @return Returns the assessmentDate.
	 */
	public Date getAssessmentDate() {
		return assessmentDate;
	}
	/**
	 * @param assessmentDate The assessmentDate to set.
	 */
	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}
	
	/**
	 * @return Returns the changedBy.
	 */
	public String getChangedBy() {
		return changedBy;
	}
	/**
	 * @param changedBy The changedBy to set.
	 */
	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}
	/**
	 * @return Returns the changedOn.
	 */
	public Date getChangedOn() {
		return changedOn;
	}
	/**
	 * @param changedOn The changedOn to set.
	 */
	public void setChangedOn(Date changedOn) {
		this.changedOn = changedOn;
	}
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the createdBy.
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy The createdBy to set.
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return Returns the createdOn.
	 */
	public Date getCreatedOn() {
		return createdOn;
	}
	/**
	 * @param createdOn The createdOn to set.
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
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
	 * @return Returns the recruiter.
	 */
	public long getRecruiter() {
		return recruiter;
	}
	/**
	 * @param recruiter The recruiter to set.
	 */
	public void setRecruiter(long recruiter) {
		this.recruiter = recruiter;
	}
	/**
	 * @return Returns the score.
	 */
	public String getScore() {
		return score;
	}
	/**
	 * @param score The score to set.
	 */
	public void setScore(String score) {
		this.score = score;
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
	 * @return Returns the assessmentDeliveryFormat.
	 */
	public AssessmentDeliveryFormat getAssessmentDeliveryFormat() {
		return assessmentDeliveryFormat; 
	}
	/**
	 * @param assessmentDeliveryFormat The assessmentDeliveryFormat to set.
	 */
	public void setAssessmentDeliveryFormat(AssessmentDeliveryFormat assessmentDeliveryFormat) {
		this.assessmentDeliveryFormat = assessmentDeliveryFormat;
	}
	/**
	 * @return Returns the assessmentProvider.
	 */
	public AssessmentProvider getAssessmentProvider() {
		return assessmentProvider;
	}
	/**
	 * @param assessmentProvider The assessmentProvider to set.
	 */
	public void setAssessmentProvider(AssessmentProvider assessmentProvider) {
		this.assessmentProvider = assessmentProvider;
	}
	/**
	 * @return Returns the assessmentRating.
	 */
	public AssessmentRating getAssessmentRating() {
		return assessmentRating;
	}
	/**
	 * @param assessmentRating The assessmentRating to set.
	 */
	public void setAssessmentRating(AssessmentRating assessmentRating) {
		this.assessmentRating = assessmentRating;
	}
	/**
	 * @return Returns the assessmentTest.
	 */
	public AssessmentTest getAssessmentTest() {
		return assessmentTest;
	}
	/**
	 * @param assessmentTest The assessmentTest to set.
	 */
	public void setAssessmentTest(AssessmentTest assessmentTest) {
		this.assessmentTest = assessmentTest;
	}

	
	/**
	 * @return Returns the assessmentDeliveryFormatId.
	 */
	public String getAssessmentDeliveryFormatId() {
		if (assessmentDeliveryFormat != null) {
			return Long.toString(assessmentDeliveryFormat.getId());
		} else {
			return null;
		}
	}
	/**
	 * @param assessmentDeliveryFormatId The assessmentDeliveryFormatId to set.
	 */
	public void setAssessmentDeliveryFormatId(String assessmentDeliveryFormatId) {
		if (assessmentDeliveryFormatId == null) {
			assessmentDeliveryFormat = null;
		} else {
			if (assessmentDeliveryFormat == null) {
				assessmentDeliveryFormat = new AssessmentDeliveryFormat();
			}
			assessmentDeliveryFormat.setId(getLong(assessmentDeliveryFormatId));
		}
	}
	/**
	 * @return Returns the assessmentProviderId.
	 */
	public String getAssessmentProviderId() {
		if (assessmentProvider != null) {
			return Long.toString(assessmentProvider.getId());
		} else {
			return null;
		}
	}
	/**
	 * @param assessmentProviderId The assessmentProviderId to set.
	 */
	public void setAssessmentProviderId(String assessmentProviderId) {
		if (assessmentProviderId == null) {
			assessmentProvider = null;
		} else {
			if (assessmentProvider == null) {
				assessmentProvider = new AssessmentProvider();
			}
			assessmentProvider.setId(getLong(assessmentProviderId));
		}
	}
	/**
	 * @return Returns the assessmentRatingId.
	 */
	public String getAssessmentRatingId() {
		if (assessmentRating != null) {
			return Long.toString(assessmentRating.getId());
		} else {
			return null;
		}
	}
	/**
	 * @param assessmentRatingId The assessmentRatingId to set.
	 */
	public void setAssessmentRatingId(String assessmentRatingId) {
		if (assessmentRatingId == null) {
			assessmentRating = null;
		} else {
			if (assessmentRating == null) {
				assessmentRating = new AssessmentRating();
			}
			assessmentRating.setId(getLong(assessmentRatingId));
		}
	}
	/**
	 * @return Returns the assessmentTestId.
	 */
	public String getAssessmentTestId() {
		if (assessmentTest != null) {
			return Long.toString(assessmentTest.getId());
		} else {
			return null;
		}
	}
	/**
	 * @param assessmentTestId The assessmentTestId to set.
	 */
	public void setAssessmentTestId(String assessmentTestId) {
		if (assessmentTestId == null) {
			assessmentTest = null;
		} else {
			if (assessmentTest == null) {
				assessmentTest = new AssessmentTest();
			}
			assessmentTest.setId(getLong(assessmentTestId));
		}
	}

	/**
	 * @return Returns the assessmentDeliveryFormatName.
	 */
	public String getAssessmentDeliveryFormatName() {
		if (assessmentDeliveryFormat != null) {
			return assessmentDeliveryFormat.getName();
		} else {
			return null;
		}
	}

	/**
	 * @return Returns the assessmentProviderName.
	 */
	public String getAssessmentProviderName() {
		if (assessmentProvider != null) {
			return assessmentProvider.getName();
		} else {
			return null;
		}
	}

	/**
	 * @return Returns the assessmentRatingName.
	 */
	public String getAssessmentRatingName() {
		if (assessmentRating != null) {
			return assessmentRating.getName();
		} else {
			return null;
		}
	}

	/**
	 * @return Returns the assessmentTestName.
	 */
	public String getAssessmentTestName() {
		if (assessmentTest != null) {
			return assessmentTest.getName();
		} else {
			return null;
		}
	}

	private static long getLong(String value) {
		try {
			return Long.parseLong(value);
		} catch (Exception ex) {
			return 0;
		}
	}

}
