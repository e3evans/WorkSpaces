package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateExtraInfoDAO;
import com.manpower.portal.mpnet.hbn.beans.CandidateExtraInfo;


public class CandidateExtraInfoDAOHibernate
		extends GenericHibernateDAO
		implements CandidateExtraInfoDAO {

	
	public CandidateExtraInfoDAOHibernate(Session session){
		super(CandidateExtraInfo.class, session);
	}
	
	public void deleteCandidateExtraInfo(long id) {

		Session session = HibernateUtil.getCurrentSession();
		Object toDelete = session.load(CandidateExtraInfo.class, new Long(id));
		session.delete(toDelete);
	}

	public CandidateExtraInfo findByCandidateId(long candidateId) {
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(CandidateExtraInfo.class);
		query.add(Restrictions.eq("candidateId", new Long(candidateId)));
		CandidateExtraInfo result = (CandidateExtraInfo)query.uniqueResult();
		return result;
	}

	public void saveCandidateExtraInfo(CandidateExtraInfo extraInfo) {
		
		Session session = HibernateUtil.getCurrentSession();
		session.save(extraInfo);

	}

	public void updateCandidateExtraInfo(CandidateExtraInfo extraInfo) {
		Session session = HibernateUtil.getCurrentSession();
		if(extraInfo.getId() <= 0){
			session.save(extraInfo);
		}else{
			session.update(extraInfo);
		}
	}

	public Object delete(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteAll(List records) {
		// TODO Auto-generated method stub

	}

	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List findByCustomQuery(String query, Properties params) {
		// TODO Auto-generated method stub
		return null;
	}

	public List findByCustomSQLQuery(String query, Properties params,
			String name, Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	public List findByExample(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object findByID(Serializable id) {
		Session session = HibernateUtil.getCurrentSession();
		Object result = session.load(CandidateExtraInfo.class, id);
		return result;
	}

	public List findBySQLQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	public List findPageByCustomHQLQuery(String query, Properties params,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public List findPageByCustomHQLQuery(String query, Properties params,
			int pageNumber, int pageSize, boolean calculateTotalCount) {
		// TODO Auto-generated method stub
		return null;
	}

	public List findPageByCustomSQLQuery(String query, Properties params,
			int pageNumber, int pageSize, String entityAlias, Class entityName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List findResultsByCustomHQLQuery(String query, Properties params,
			int startIndex, int endIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object makePersistent(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object makeUpdate(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public long runCountQuery(String query, Properties params) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object runSQLQuery(String query, Properties params, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	public void runSQLUpdateQuery(String query, Properties params) {
		// TODO Auto-generated method stub

	}

	public Object save(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveAll(List records) {
		// TODO Auto-generated method stub

	}

	public void updateAll(List records) {
		// TODO Auto-generated method stub

	}

}
