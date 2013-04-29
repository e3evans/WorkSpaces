package com.manpower.portal.mpnet.dao.interfaces;

/**
 * @author alexander.todorov
 *  
 */
public interface SteeringQuestionDAO extends DAO {

	/**
	 * Find all questions for a given site and language
	 * 
	 * @param siteId
	 * @param language
	 * @return List of SteeringQuestions objects
	 */
	public java.util.List findQuestions(long siteId, String language);

	/**
	 * Find all legal questions for a given site and language
	 * 
	 * @param siteId
	 * @param language
	 * @return List of SteeringQuestions objects
	 */
	public java.util.List findLegalQuestions(long siteId, String language);

}
