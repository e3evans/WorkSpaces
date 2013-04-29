/*
 * Created on Nov 15, 2005
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

/**
 * @author Dmitry
 *  
 */
public interface UserDAO extends DAO {

	/**
	 * Find user by a given email
	 * 
	 * @param email
	 * @return User object
	 */
	public Object findByEmail(String email);

	/**
	 * Find all users
	 * 
	 * @param List
	 *            of User objects
	 */
	public List findAll();

}
