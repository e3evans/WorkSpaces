package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.MobileDeviceDAO;
import com.manpower.portal.mpnet.hbn.beans.MobileAgent;
import com.manpower.portal.mpnet.hbn.beans.MobileDevice;

public class MobileDeviceDAOHibernate extends GenericHibernateDAO implements
		MobileDeviceDAO {

	public MobileDeviceDAOHibernate(Session session) {
		super(MobileDeviceDAOHibernate.class, session);
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findByID(Serializable id) {
		Object device = HibernateUtil.getSessionFactory()
		.getCurrentSession().get(MobileDevice.class, id);
					HibernateUtil.getCurrentSession().evict(device);
					return device;
	}

	public MobileDevice findByDeviceID(String deviceID) {
		Session session = HibernateUtil.getCurrentSession();
		MobileDevice device = (MobileDevice) session.createCriteria(MobileDevice.class).add(Restrictions.eq("deviceId", deviceID)).uniqueResult();
		return device;
	}

}
