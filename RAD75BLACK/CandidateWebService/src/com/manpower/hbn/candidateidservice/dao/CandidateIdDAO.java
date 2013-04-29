package com.manpower.hbn.candidateidservice.dao;

import java.io.Serializable;
import java.util.List;


public interface CandidateIdDAO {

	public List findAll();
	public Object findByID(Serializable id);
	public Object findByUserID(String userid);
	
}
