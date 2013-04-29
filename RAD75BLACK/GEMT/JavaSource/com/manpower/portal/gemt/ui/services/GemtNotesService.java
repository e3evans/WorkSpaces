/*
 * Created on Jan 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.gemt.ui.services;

import java.io.Serializable;
import java.util.List;

import com.manpower.portal.gemt.ui.beans.GemtNotesUIBean;

/**
 * @author rrajaram
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface GemtNotesService {

	public GemtNotesUIBean findById(Serializable id);
	public List findAll();
	public GemtNotesUIBean makePersistent(GemtNotesUIBean gsrb);
	public void delete(Serializable id);
}
