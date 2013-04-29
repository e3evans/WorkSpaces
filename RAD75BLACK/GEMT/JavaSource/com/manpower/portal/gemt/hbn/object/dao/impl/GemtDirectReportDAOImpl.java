package com.manpower.portal.gemt.hbn.object.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.portal.gemt.hbn.beans.GemtDirectReportHbnBean;
import com.manpower.portal.gemt.hbn.object.dao.GemtDirectReportDAO;
import com.manpower.portal.gemt.hbn.shared.dao.GenericHibernateDAO;

public class GemtDirectReportDAOImpl extends GenericHibernateDAO implements GemtDirectReportDAO{

	public GemtDirectReportDAOImpl(Session session) {
		super(GemtDirectReportHbnBean.class, session);
		// TODO Auto-generated constructor stub
	}

	public List findAll() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(GemtDirectReportHbnBean.class).list();
	}

	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return getSession().get(GemtDirectReportHbnBean.class, id);
	}
	
	public List findAllByManagerId(String mgrEmail,String orderBy,String ascDsc){
		Criteria criteria = getSession().createCriteria(GemtDirectReportHbnBean.class);
		criteria.add(Restrictions.eq("gemt_sum_mgremail", mgrEmail));
		criteria = addOrder(criteria, orderBy, ascDsc);
		return criteria.list();
	}
	
	public Object findByEmployeeEmail(String empEmail){
		Object obj = null;
		Criteria criteria = getSession().createCriteria(GemtDirectReportHbnBean.class);
		criteria.add(Restrictions.eq("gemt_sum_empemail", empEmail));
		List temp = criteria.list();
		if (temp.size()>0)obj=temp.get(0);
		return obj;
	}
	
	/* (non-Javadoc)
	 * @see com.manpower.portal.gemt.hbn.shared.dao.DAO#delete(java.lang.Object)
	 */
	public Object delete(Object obj) {
		// TODO Auto-generated method stub
		return super.delete(obj);
	}
	
	public Object makePersistent(Object obj) {
		// TODO Auto-generated method stub
		return super.makePersistent(obj);
	}

}
