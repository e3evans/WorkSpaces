package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.MobileAgentDAO;
import com.manpower.portal.mpnet.hbn.beans.MobileAgent;

public class MobileAgentDAOHibernate extends GenericHibernateDAO implements MobileAgentDAO{

	private static final Logger log=Logger.getLogger(MobileAgentDAOHibernate.class);
	
	public MobileAgentDAOHibernate(Session session) {
		super(MobileAgentDAOHibernate.class, session);
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findByID(Serializable id) {
		Object agent = HibernateUtil.getSessionFactory()
		.getCurrentSession().get(MobileAgent.class, id);
					HibernateUtil.getCurrentSession().evict(agent);
					return agent;
	}
	
	public List<MobileAgent> getJobAgents(String deviceID) {
		Session session = HibernateUtil.getCurrentSession();
		List<MobileAgent> agents = session.createCriteria(MobileAgent.class).createCriteria("device").add(Restrictions.eq("deviceId", deviceID)).list();
		return agents;
	}

	
	public List<MobileAgent> findByName(String agentName, String deviceID) {
		Session session = HibernateUtil.getCurrentSession();
		List<MobileAgent> agents = session.createCriteria(MobileAgent.class).add(Restrictions.eq("name", agentName)).createCriteria("device").add(Restrictions.eq("deviceId", deviceID)).list();
		return agents;
	}
}
