package com.manpower.portal.mpnet.dao.interfaces;

import java.io.Serializable;
import java.util.List;



public interface Advertisement_HistoryDAO extends DAO {
	

	
	public List findAllByCandidateId(Serializable id);
	

	
	
}
