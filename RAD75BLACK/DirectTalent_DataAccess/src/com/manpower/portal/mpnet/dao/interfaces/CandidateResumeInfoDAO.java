/*
 * Created on Jun 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * @author Eric Evans
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface CandidateResumeInfoDAO extends DAO {

	public Object findByID(Serializable id);

	public List findAll();

	public List findAllByCandidateId(Serializable id);
}
