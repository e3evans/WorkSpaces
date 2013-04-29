/*
 * Created on Jul 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

import com.manpower.portal.mpnet.hbn.beans.AdvertisementContact;

/**
 * @author mstoffel
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface RecruiterCommentsDAO extends DAO {
	public List findAllCommentsByRecruiter(AdvertisementContact adContact);
}
