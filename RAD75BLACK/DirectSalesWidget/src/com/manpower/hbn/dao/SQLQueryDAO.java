package com.manpower.hbn.dao;

import java.util.List;

public interface SQLQueryDAO {
	public List<Object> getTopTenOpportunites();
	public List<Object> getOpportunityCount();
	public List<Object> get306090();
	public List<Object> getTotalEstimatedRevenue();
	public List<Object> getTotalWeightedEstimatedGP();
	public List<Object> getTotalEstimatedRevenueNewBusiness();
}
