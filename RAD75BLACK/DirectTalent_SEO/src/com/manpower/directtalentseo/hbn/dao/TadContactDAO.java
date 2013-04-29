package com.manpower.directtalentseo.hbn.dao;

import java.io.Serializable;
import java.util.List;

public interface TadContactDAO {
	public List findAll();
	public Object findByID(Serializable id);
}
