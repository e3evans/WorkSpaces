/*
 * Created on Jan 23, 2006
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.manpower.portal.mpnet.hbn.beans.CandidateJobAgents;

/**
 * @author jsingh
 *  
 */
public interface ActiveAdvertDAO extends DAO {

	public static final String SKILL_CRITERIA = "(contains({alias}.CANDIDATESKILLS, ?) > 0 or"
		+ " contains({alias}.JOBDESCRIPTION, ?) > 0 or"
		+ " contains({alias}.CANDIDATEPROFILE, ?) > 0 or"
		+ " contains({alias}.JOBTITLE, ?) > 0)";

	public static final String SKILL_AND_JOBTITLE_CRITERIA = "((contains({alias}.CANDIDATESKILLS, ?) > 0 or"
		+ " contains({alias}.JOBDESCRIPTION, ?) > 0 or"
		+ " contains({alias}.CANDIDATEPROFILE, ?) > 0) and"
		+ " contains({alias}.JOBTITLE, ?) > 0)";

	public static final Type[] QUADRUPLE_STRING_TYPES = new Type[] {
		Hibernate.STRING, Hibernate.STRING, Hibernate.STRING,
		Hibernate.STRING };
	
	public List getAdvertisementIDs(CandidateJobAgents jobAgent,String advertId, String orderByProperty,boolean descending, int startIndex, int endIndex);

	public int getCount(CandidateJobAgents jobAgent, String advertID);
}
