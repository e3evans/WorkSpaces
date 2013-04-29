/*
 * Created on Jan 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.gemt.hbn.object.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author Eric Evans
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface GemtNotesDAO {

	public List findAll();
	public Object findByID(Serializable id);
	public Object makePersistent(Object obj);
	public Object delete(Object obj);
}
 