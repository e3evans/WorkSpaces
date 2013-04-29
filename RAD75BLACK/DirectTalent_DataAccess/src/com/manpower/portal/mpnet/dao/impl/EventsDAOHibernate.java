package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.EventsDAO;
import com.manpower.portal.mpnet.hbn.beans.Event;

// TODO: Auto-generated Javadoc
/**
 * The Class EventsDAO.
 */
public class EventsDAOHibernate extends GenericHibernateDAO implements EventsDAO
{
  /**
   * Instantiates a new events dao.
   *
   */

  public EventsDAOHibernate(Session session)
  {
    super(Event.class, session);
  }

  /**
   * Gets the test event.
   *
   * @return the test event
   */
  public Event getTestEvent()
  {
    Event evnt = new Event();
    evnt.setId(1);
    evnt.setCandidateId(56797);
    evnt.setSiteId(10184);
    evnt.setNotificationType("candidate");
    evnt.setEventType("accountDetails");
    return evnt;
  }

  /**
   * Save event and all the events related to this event
   *
   * @param event
   *          the event
   *
   * @return true, if successful
   */
  public boolean saveEvent(Event event)
  {
    Session session = HibernateUtil.getCurrentSession();
    session.saveOrUpdate(event);
    return true;
  }

  public List findAll()
  {
    Criteria query = HibernateUtil.getCurrentSession().createCriteria(Event.class);
    return query.list();
  }

  public Object findByID(Serializable id)
  {
    Object event = HibernateUtil.getCurrentSession().get(Event.class, id);
    return event;
  }

}
