/*
 * Created on 2007-6-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.dao.interfaces;

import com.manpower.portal.mpnet.hbn.beans.OtherCandidateDetails;

/**
 * @author Miroslav Nachev
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface OtherCandidateDetailsDAO extends DAO {
	public OtherCandidateDetails findByCandidateId(long candidateId);
}
