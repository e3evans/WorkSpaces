package com.manpower.portal.mpnet.dao.interfaces;



import com.manpower.portal.mpnet.hbn.beans.Event;

// TODO: Auto-generated Javadoc
/**
 * The Class EventsDAO.
 */
public interface EventsDAO extends DAO
{

  /**
   * Gets the test event.
   *
   * @return the test event
   */
  public Event getTestEvent();
  
  /**
   * Save event and all the events related to this event
   *
   * @param event
   *          the event
   *
   * @return true, if successful
   */
  public boolean saveEvent(Event event);



}
