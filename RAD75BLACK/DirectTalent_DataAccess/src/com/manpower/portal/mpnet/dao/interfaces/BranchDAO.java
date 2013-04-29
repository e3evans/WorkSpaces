/*
 * Created on 2006-2-1
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.hbn.beans.Branch;
import com.manpower.portal.mpnet.hbn.beans.BranchTable;

/**
 * @author alexander.todorov
 *  
 */
public interface BranchDAO extends DAO {

	/**
	 * Find branches by location, speciality and Site ID
	 * 
	 * @param location
	 * @param speciality
	 * @param siteId
	 * @return List of Branch objects.
	 */
	public List findBranches(String location, String speciality, long siteId);

	/**
	 * Find unique branches by parameter
	 * 
	 * @param property
	 * @return List of Branch objects.
	 */
	public List findUnique(String property);

	/**
	 * Find unique branches by parameters
	 * 
	 * @param property
	 * @param whereColumn
	 * @param whereValue
	 * @return List of Branch ibjects.
	 */
	public List findUniqueWhereEquals(String property, String whereColumn,
			String whereValue);
    
    
    /**
     * Delete Single Branch
     * 
     * @param brachToDelete
     * @return boolean
     */
    public boolean deleteSingleBranch(BranchTable branchToDelete);

	/**
	 * @param siteId
	 * @param language
	 */
    public List findBranchesBySiteOrdered(long siteId, int fromIndex, int pageSize, int orderBy, boolean descending);

	/**
	 * Finds branch by externalId
	 * @param externalId
	 * @return Branch HBN bean
	 */
	public Object findBranchByExternalId(String externalId,long siteId, String language);
	
	/**
	 * Selects the branches' count by siteId
	 * @param siteId
	 * @return
	 */
	public long getBranchesCountBySiteId(long siteId);
	
	public List findBySQLQuery(String query);
	
	public int getCountBySQLQuery(String query, String uniqueColumn);
}
