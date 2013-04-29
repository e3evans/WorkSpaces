package com.manpower.portal.mpnet.hbn.beans;

import java.util.Date;

public class Event extends Base
{
  private static final long serialVersionUID = -174983552348583583L;
  private long id;
  private long siteId;
  private long candidateId;
  private String notificationType;
  private String eventType;
  private Date eventTimestamp;
  private String status;
  private int retry;
  private Date messageOnTimestamp;
  private String createdBy;
  private String changedBy;
  private Long cvJobAppId = null;
//  private List relatedEvents = new ArrayList();
//  private Set notificationTypes = new HashSet();
//  private Set eventTypes = new HashSet();
  
  
  public long getId()
  {
    return id;
  }
  public void setId(long id)
  {
    this.id = id;
  }
  public long getSiteId()
  {
    return siteId;
  }
  public void setSiteId(long siteId)
  {
    this.siteId = siteId;
  }
  public long getCandidateId()
  {
    return candidateId;
  }
  public void setCandidateId(long candidateId)
  {
    this.candidateId = candidateId;
  }
  public String getNotificationType()
  {
    return notificationType;
  }
  public void setNotificationType(String notificationType)
  {
    this.notificationType = notificationType;
  }
  public String getEventType()
  {
    return eventType;
  }
  public void setEventType(String eventType)
  {
    this.eventType = eventType;
  }
  public Date getEventTimestamp()
  {
    return eventTimestamp;
  }
  public void setEventTimestamp(Date eventTimestamp)
  {
    this.eventTimestamp = eventTimestamp;
  }
  public String getStatus()
  {
    return status;
  }
  public void setStatus(String status)
  {
    this.status = status;
  }
  public int getRetry()
  {
    return retry;
  }
  public void setRetry(int retry)
  {
    this.retry = retry;
  }
  public Date getMessageOnTimestamp()
  {
    return messageOnTimestamp;
  }
  public void setMessageOnTimestamp(Date messageOnTimestamp)
  {
    this.messageOnTimestamp = messageOnTimestamp;
  }
  public String getCreatedBy()
  {
    return createdBy;
  }
  public void setCreatedBy(String createdBy)
  {
    this.createdBy = createdBy;
  }
  public String getChangedBy()
  {
    return changedBy;
  }
  public void setChangedBy(String changedBy)
  {
    this.changedBy = changedBy;
  }

//  public List getRelatedEvents()
//  {
//    return relatedEvents;
//  }
//  public void setRelatedEvents(List relatedEvents)
//  {
//    this.relatedEvents = relatedEvents;
//  }

  public boolean eventEquals(Event other)
  {
    if (this == other)
      return true;
    if (candidateId != other.candidateId)
      return false;
    if (siteId != other.siteId)
      return false;
    if(!notificationType.equals(other.notificationType))
      return false;
    return true;
  }
  
//  public Set getNotificationTypes()
//  {
//    return notificationTypes;
//  }
//  public void setNotificationTypes(Set notificationTypes)
//  {
//    this.notificationTypes = notificationTypes;
//  }
//  public Set getEventTypes()
//  {
//    return eventTypes;
//  }
//  public void setEventTypes(Set eventTypes)
//  {
//    this.eventTypes = eventTypes;
//  }
  
  public Long getCvJobAppId()
  {
    return cvJobAppId;
  }
  public void setCvJobAppId(Long cvJobAppId)
  {
    this.cvJobAppId = cvJobAppId;
  }
  /**
   * Constructs a <code>String</code> with all attributes
   * in name = value format.
   *
   * @return a <code>String</code> representation 
   * of this object.
   */
  public String toString()
  {
      final String TAB = "  ";
      
      StringBuffer retValue = new StringBuffer();
      
      retValue.append("Event ( ")
          .append(super.toString()).append(TAB)
          .append("id = ").append(this.id).append(TAB)
          .append("siteId = ").append(this.siteId).append(TAB)
          .append("candidateId = ").append(this.candidateId).append(TAB)
          .append("notificationType = ").append(this.notificationType).append(TAB)
          .append("eventType = ").append(this.eventType).append(TAB)
          .append("eventTimestamp = ").append(this.eventTimestamp).append(TAB)
          .append("status = ").append(this.status).append(TAB)
          .append("retry = ").append(this.retry).append(TAB)
          .append("messageOnTimestamp = ").append(this.messageOnTimestamp).append(TAB)
          .append("createdBy = ").append(this.createdBy).append(TAB)
          .append("changedBy = ").append(this.changedBy).append(TAB)
          .append("cvJobAppId = ").append(this.cvJobAppId).append(TAB)
          .append(" )");
      
      return retValue.toString();
  }

  
}
