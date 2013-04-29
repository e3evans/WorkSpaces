package com.manpower.directtalentrecruitersearch.dao;

import java.io.Serializable;

public interface ResumeDAO {
	public Object findByID(Serializable id);
	public Object findByIDAndType(Serializable id,String candType);
}
