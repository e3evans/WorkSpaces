package com.manpower.translations.hbn.shared.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 */
public abstract class GenericHibernateDAO implements DAO {

    public static String ORDER_ASC = "ASC";

    public static String ORDER_DESC = "DESC";

    private static final int BATCH_SIZE = 5;

    private Session session;

    public GenericHibernateDAO(Session session) {
	this.session = session;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(long)
     */
    public abstract Object findById(Number id);

    /*
     * (non-Javadoc)
     *
     * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
     */
    public abstract List findAll();

    /*
     * (non-Javadoc)
     *
     * @see
     * com.manpower.portal.mpnet.dao.interfaces.DAO#makePersistent(java.lang
     * .Object)
     */
    public Object makePersistent(Object obj) {
	session.beginTransaction();
	session.saveOrUpdate(obj);
	// Let the service layer manage transaction
	// session.getTransaction().commit();
	return obj;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.manpower.portal.mpnet.dao.interfaces.DAO#findByCustomQuery(java.lang
     * .String, java.util.Properties)
     */

    public List findByCustomQuery(String query, Properties params) {
	Query q = session.createQuery(query);

	Iterator iter = params.keySet().iterator();
	while (iter.hasNext()) {
	    String stKey = (String) iter.next();
	    String stVal = (String) params.get(stKey);
	    q.setString(stKey, stVal);
	}
	return q.list();
    }

    public Object runSQLQuery(String query, Properties params, String type) {

	SQLQuery q = session.createSQLQuery(query);
	Iterator iter = params.keySet().iterator();
	while (iter.hasNext()) {
	    String stKey = (String) iter.next();
	    String stVal = (String) params.get(stKey);
	    q.setString(stKey, stVal);
	}
	q.addScalar(type, Hibernate.DATE);
	Date max = (Date) q.uniqueResult();

	return max;
    }

    public Object delete(Object obj) {
	session.delete(obj);
	return obj;

    }

    public Object makeUpdate(Object obj) {
	session.beginTransaction();

	session.update(obj);
	// Let the service layer manage transaction
	// session.getTransaction().commit();
	return obj;
    }

    /*
     * Alexander Todorov. The application needs of methods for batch update,
     * insert and delete.
     */

    public void updateAll(List records) {

	if (records != null && !records.isEmpty()) {

	    int recSize = records.size();

	    Object forUpdate;

	    for (int i = 0; i < recSize; i++) {

		forUpdate = records.get(i);
		session.update(forUpdate);

		if (i % BATCH_SIZE == 0) {
		    session.flush();
		    session.clear();
		}

	    }

	}
    }

    protected Criteria addOrder(Criteria criteria, String orderBy, String ascDsc) {
	if (ascDsc.equals(ORDER_ASC)) {
	    criteria.addOrder(Order.asc(orderBy));
	} else {
	    criteria.addOrder(Order.desc(orderBy));
	}
	return criteria;
    }

    protected Session getSession() {
	return session;
    }
}
