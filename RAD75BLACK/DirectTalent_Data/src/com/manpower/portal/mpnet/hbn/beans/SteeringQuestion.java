/*
 * Created on 2006-2-21
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author alexander.todorov
 *
 * Persistent class for table STEERING_QUESTIONS
 */
public class SteeringQuestion implements Serializable{
	private static final long serialVersionUID =     -4445893974478435735L;
	private long 	id, 
					siteId,
					questionId;
	
	private String	question,
					yesAnswer,
					noAnswer,
					yesDecision,
					noDecision,
					language,
					updatedBy,
					questionType;
	
	private Date	updatedOn;
	
	
	
					
	
	/**
	 * @return Returns the id.
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return Returns the language.
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @return Returns the noAnswer.
	 */
	public String getNoAnswer() {
		return noAnswer;
	}
	/**
	 * @return Returns the noDecision.
	 */
	public String getNoDecision() {
		return noDecision;
	}
	/**
	 * @return Returns the question.
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @return Returns the siteId.
	 */
	public long getSiteId() {
		return siteId;
	}
	/**
	 * @return Returns the updatedBy.
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @return Returns the updatedOn.
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}
	/**
	 * @return Returns the yesAnswer.
	 */
	public String getYesAnswer() {
		return yesAnswer;
	}
	/**
	 * @return Returns the yesDecision.
	 */
	public String getYesDecision() {
		return yesDecision;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @param language The language to set.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @param noAnswer The noAnswer to set.
	 */
	public void setNoAnswer(String noAnswer) {
		this.noAnswer = noAnswer;
	}
	/**
	 * @param noDecision The noDecision to set.
	 */
	public void setNoDecision(String noDecision) {
		this.noDecision = noDecision;
	}
	/**
	 * @param question The question to set.
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * @param siteId The siteId to set.
	 */
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}
	/**
	 * @param updatedBy The updatedBy to set.
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @param updatedOn The updatedOn to set.
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	/**
	 * @param yesAnswer The yesAnswer to set.
	 */
	public void setYesAnswer(String yesAnswer) {
		this.yesAnswer = yesAnswer;
	}
	/**
	 * @param yesDecision The yesDecision to set.
	 */
	public void setYesDecision(String yesDecision) {
		this.yesDecision = yesDecision;
	}
	/**
	 * @return Returns the questionId.
	 */
	public long getQuestionId() {
		return questionId;
	}
	/**
	 * @param questionId The questionId to set.
	 */
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	/**
	 * @return Returns the questionType.
	 */
	public String getQuestionType() {
		return questionType;
	}
	/**
	 * @param questionType The questionType to set.
	 */
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if(obj instanceof SteeringQuestion == false) {
			return false;
		} if (obj == this) {
			return true;
		}
		SteeringQuestion aSteeringQuestion = (SteeringQuestion)obj;
		return new EqualsBuilder().
			appendSuper(super.equals(obj)).
			append(this.id, aSteeringQuestion.getId()).
			append(this.siteId, aSteeringQuestion.getSiteId()).
			append(this.questionId, aSteeringQuestion.getQuestionId()).
			append(this.question, aSteeringQuestion.getQuestion()).
			append(this.yesAnswer, aSteeringQuestion.getYesAnswer()).
			append(this.noAnswer, aSteeringQuestion.getNoAnswer()).
			append(this.yesDecision, aSteeringQuestion.getYesDecision()).
			append(this.noDecision, aSteeringQuestion.getNoDecision()).
			append(this.language, aSteeringQuestion.getLanguage()).
			append(this.questionType, aSteeringQuestion.getQuestionType()).
			append(this.updatedBy, aSteeringQuestion.getUpdatedBy()).
			append(this.updatedOn, aSteeringQuestion.getUpdatedOn()).isEquals();
	}
			
	
	/* This version is slower than the hard-coded version and will 
	 * fail under a security manager unless appropriate permissions are set.
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	//public boolean equals(Object obj) {
	//	return EqualsBuilder.reflectionEquals(this, obj);
	//}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// you pick a hard-coded, randomly chosen, non-zero, odd number
	    // ideally different for each class
	    return new HashCodeBuilder(14, 23).
			append(this.id).
			append(this.siteId).
			append(this.questionId).
			append(this.question).
			append(this.yesAnswer).
			append(this.noAnswer).
			append(this.yesDecision).
			append(this.noDecision).
			append(this.language).
			append(this.questionType).
			append(this.updatedBy).
			append(this.updatedOn).toHashCode();
	}
		
	/* This version is slower than the hard-coded version and will 
	 * fail under a security manager unless appropriate permissions are set.
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	//public int hashCode() {
	//   return HashCodeBuilder.reflectionHashCode(this);
	//}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).
			append("id", this.id).
			append("siteId", this.siteId).
			
			append("questionId", this.questionId).
			append("question", this.question).
			append("yesAnswer", this.yesAnswer).
			append("noAnswer", this.noAnswer).
			append("yesDecision", this.yesDecision).
			append("noDecision", this.noDecision).
			append("language", this.language).
			append("questionType", this.questionType).
			append("updatedBy", this.updatedBy).
			append("updatedOn", this.updatedOn)
				.toString();
	}
	/* This version is slower than the hard-coded version and will 
	 * fail under a security manager unless appropriate permissions are set.
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */	
	//public String toString() {
	//   return ToStringBuilder.reflectionToString(this);
	//}
}
