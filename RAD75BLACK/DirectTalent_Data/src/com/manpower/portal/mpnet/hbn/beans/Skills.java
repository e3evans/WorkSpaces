/*
 * Created on Jan 23, 2006
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author jsingh
 *
 * Persistent classs for table SKILLS
 */
public class Skills implements Serializable {
	private static final long serialVersionUID =    918476085850069751L;
	
	 private long id;
	 private long siteId;	 
	 private String skillname;
	 private String skilldescription;
	 private String active;
	 private long parentSkill;
	 private Date updatedOn;
	 private String updatedBy;
	 private String language;

	 private Skills parent;
	 
	 private Set 	children;
	 
	 public Skills() {
	 	super();
	 }
	 
	/**
	 * @return Returns the active.
	 */
	public String getActive() {
		return active;
	}
	/**
	 * @param active The active to set.
	 */
	public void setActive(String active) {
		this.active = active;
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
	 * @return Returns the parent_Id.
	 */
	public long getParentSkill() {
		return parentSkill;
	}
	/**
	 * @param parent_Id The parent_Id to set.
	 */
	public void setParentSkill(long parent_Id) {
		parentSkill = parent_Id;
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
	 * @return Returns the skilldescription.
	 */
	public String getSkilldescription() {
		return skilldescription;
	}
	/**
	 * @param skilldescription The skilldescription to set.
	 */
	public void setSkilldescription(String skilldescription) {
		this.skilldescription = skilldescription;
	}
	/**
	 * @return Returns the skillname.
	 */
	public String getSkillname() {
		return skillname;
	}
	/**
	 * @param skillname The skillname to set.
	 */
	public void setSkillname(String skillname) {
		this.skillname = skillname;
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
	 * @return Returns the children.
	 */
	public Set getChildren() {
		return children;
	}
	/**
	 * @return Returns the parent.
	 */
	public Skills getParent() {
		return parent;
	}
	/**
	 * @param children The children to set.
	 */
	public void setChildren(Set children) {
		this.children = children;
	}
	/**
	 * @param parent The parent to set.
	 */
	public void setParent(Skills parent) {
		this.parent = parent;
	}
	/**
	 * @return Returns the language.
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language The language to set.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Skills == false) {
			return false;
		} if (obj == this) {
			return true;
		}
		Skills someSkills = (Skills)obj;
		return new EqualsBuilder().
			appendSuper(super.equals(obj)).
			append(this.id, someSkills.getId()).
			append(this.siteId, someSkills.getSiteId()).
			append(this.skillname, someSkills.getSkillname()).
			append(this.skilldescription, someSkills.getSkilldescription()).
			append(this.active, someSkills.getActive()).
			append(this.parentSkill, someSkills.getParentSkill()).
			append(this.language, someSkills.getLanguage()).
			append(this.parent, someSkills.getParent()).
			append(this.children, someSkills.getChildren()).
			append(this.updatedBy, someSkills.getUpdatedBy()).
			append(this.updatedOn, someSkills.getUpdatedOn()).isEquals();
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
	    return new HashCodeBuilder(14, 33).
			append(this.id).
			append(this.siteId).
			append(this.skillname).
			append(this.skilldescription).
			append(this.active).
			append(this.parentSkill).
			append(this.language).
			append(this.parent).
			append(this.children).
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
			append("skillname", this.skillname).
			append("skilldescription", this.skilldescription).
			append("active", this.active).
			append("parentSkill", this.parentSkill).
			append("language", this.language).
			append("parent", this.parent).
			append("children", this.children).
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
