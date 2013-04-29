/*
 * Created on Jan 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.gemt.hbn.object.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

/**
 * @author Eric Evans
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface GemtSummaryReportDAO {

	public List findAll();
	public Object findByID(Serializable id);
	public Object makePersistent(Object obj);
	public Object delete(Object obj);
	public List getAllbyUserEmail(String email, String orderBy, String ascDsc);
	public List getAllByIdArray(Object[]ids,String orderBy,String ascDsc);
	public List findAllOrderBy(String orderBy, String ascDsc);
	public List findByCustomQuery(String query, Properties params);
	public List findAllByPeriod(String email,Serializable period, String mode);
	public Object update(Object obj);
	public List findParentByID(long id);
	public List findAllFinalized(String email, String mode);
	public List findAllActiveReportsByPeriod(String email,Serializable period, String mode);
}
