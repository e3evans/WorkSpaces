package com.manpower.directtalentrecruitersearch.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.manpower.directtalentrecruitersearch.dao.BranchesDAO;
import com.manpower.directtalentrecruitersearch.hbn.shared.HibernateUtilities;
import com.manpower.directtalentrecruitersearch.hbn.shared.dao.GenericHibernateDAO;
import com.manpower.hbn.beans.Branches;
import com.manpower.hbn.beans.ParameterMapKey;

public class BranchesDAOImpl extends GenericHibernateDAO implements BranchesDAO{

	public BranchesDAOImpl(Session session) {
		super(BranchesDAOImpl.class, session);
		// TODO Auto-generated constructor stub
	}

	public List findAll() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(BranchesDAOImpl.class).list();
	}

	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return getSession().get(BranchesDAOImpl.class,id);
	}
	
	public List getBranchFilterList(String [] params, long siteId){
		String branchId = params[ParameterMapKey.prefBranch];
		String locationId = params[ParameterMapKey.prefLocation];
		String inclCand = params[ParameterMapKey.inclCand];
		String inclApp = params[ParameterMapKey.inclApp];
		StringBuffer sb = new StringBuffer();
		
		sb.append("select branch_id,branchname,count(*)as mpmatches " +
				"from bjh_resumes where contains(resume,'"+params[ParameterMapKey.keywords]+"')>0 " +
				"and site_id="+Long.toString(siteId));
		if (!locationId.equals("")){
			sb.append(" and location_id="+locationId);
		}
		if (!branchId.equals("")){
			sb.append(" and branch_id="+branchId);
		}
		if (inclCand.equals("false")){
			sb.append(" and cand_type!='C'");
		}
		if (inclApp.equals("false")){
			sb.append(" and cand_type!='A'");
		}
		sb.append(" group by branch_id,branchname order by branchname");
		
		SQLQuery query = HibernateUtilities.currentSession().createSQLQuery(sb.toString()).addEntity(Branches.class);		
		List results = query.list();
		HibernateUtilities.closeSession();
		return results;
	}

	
}
