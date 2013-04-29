/*
 * Created on Jun 14, 2007
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import com.manpower.portal.mpnet.hbn.beans.FlexField;

/**
 * @author Dimitar Bakardzhiev
 *  
 */
public interface FlexFieldDAO extends DAO {
	public FlexField findByCandidate(long candidateId);
}
