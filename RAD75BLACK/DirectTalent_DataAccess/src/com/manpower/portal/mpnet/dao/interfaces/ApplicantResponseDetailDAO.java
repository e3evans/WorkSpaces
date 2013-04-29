/*
 * Created on Oct 11, 2007
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

/**
 * @author ssprout1
 *  
 */
public interface ApplicantResponseDetailDAO extends DAO {

	List findByApplicantResponseID(long applicantResponseID);

}
