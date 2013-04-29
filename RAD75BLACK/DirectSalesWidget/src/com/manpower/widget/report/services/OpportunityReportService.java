package com.manpower.widget.report.services;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.manpower.hbn.shared.dao.DAOFactory;
import com.manpower.widget.hbn.beans.DSW306090;
import com.manpower.widget.hbn.beans.EstRevReport;
import com.manpower.widget.hbn.beans.OpportunityReport;
import com.manpower.widget.hbn.beans.TopTen;

public class OpportunityReportService {
	
	public static double WIN_PLAN = 0;
	public static double WIN_ENGAGE = 0;
	public static double WIN_QUALIFY = .10;
	public static double WIN_DEVELOP = .25;
	public static double WIN_SOLUTION = .50;
	public static double WIN_PROOF = .75;
	public static double WIN_CLOSE = .9;
	public static double WIN_DEPLOY = 1;
	
	private static double getWinNumber(String stage,long value){
		double temp = Double.parseDouble(Long.toString(value));
		
		if (stage.equals("DEPLOY")){
			return WIN_DEPLOY*temp;
		}else if (stage.equals("CLOSE")){
			return WIN_CLOSE*temp;
		}else if (stage.equals("PROOF")){
			return WIN_PROOF*temp;
		}else if (stage.equals("SOLUTION")){
			return WIN_SOLUTION*temp;
		}else if (stage.equals("DEVELOP")){
			return WIN_DEVELOP*temp;
		}else if (stage.equals("QUALIFY")){
			return WIN_QUALIFY*temp;
		}else if (stage.equals("ENGAGE")){
			return WIN_ENGAGE*temp;
		}else if (stage.equals("PLAN")){
			return WIN_PLAN*temp;
		}
		
		return temp;
	}
	
	@SuppressWarnings("unchecked")
	public static String get306090(){
		List temp = (List)DAOFactory.getDAOFactory().getSqlQueryDAO().get306090();
		JSONArray jarray = new JSONArray();
		try{
			for (int i=0;i<temp.size();i++){
				DSW306090 tsn = (DSW306090)temp.get(i);
				JSONObject jobj = new JSONObject();
				jobj.put("CLOSING", tsn.getClosing());
				jobj.put("ESTREV", tsn.getEst_revenue());
				jobj.put("GP", tsn.getGp());
				jarray.put(jobj);
			}
		}catch (JSONException e){
			e.printStackTrace();
		}
		return jarray.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static String getTopTenReportData(){
		List temp = (List)DAOFactory.getDAOFactory().getSqlQueryDAO().getTopTenOpportunites();
		JSONArray jarray = new JSONArray();
		try{
			for (int i=0;i<temp.size();i++){
				TopTen tt = (TopTen)temp.get(i);
				JSONObject jobj = new JSONObject();
				jobj.put("ROWNUM", tt.getOpp_id());
				jobj.put("OPPORTUNITY", tt.getOpportunity_name());
				jobj.put("CLIENT", tt.getName1());
				jobj.put("ESTREVENUE", tt.getEst_revenue());
				jobj.put("ESTGP", getWinNumber(tt.getSales_stage(), tt.getGp()));
				jobj.put("SALESSTAGE", tt.getSales_stage());
				jobj.put("OWNER", tt.getSales_user_name());
				jobj.put("CLOSEDATE", tt.getEst_close_dt());
				jarray.put(jobj);
			}
		}catch (JSONException e){
			e.printStackTrace();
		}
		return jarray.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static String getOpportunityCount(){
		List temp = (List)DAOFactory.getDAOFactory().getSqlQueryDAO().getOpportunityCount();
		JSONArray jarray = new JSONArray();	
		try {
			for (int i=0;i<temp.size();i++){
				OpportunityReport or = (OpportunityReport)temp.get(i);
				JSONObject jobj = new JSONObject();
				jobj.put("STAGE",or.getSales_stage());
				jobj.put("ESTREV", or.getEst_revenue());
				jobj.put("WESTREV", 0);
				if (or.getSales_stage().equals("DEPLOY")){					
					jarray.put(0,jobj);
				}else if (or.getSales_stage().equals("CLOSE")){
					jarray.put(1,jobj);
				}else if (or.getSales_stage().equals("PROOF")){
					jarray.put(2,jobj);
				}else if (or.getSales_stage().equals("SOLUTION")){
					jarray.put(3,jobj);
				}else if (or.getSales_stage().equals("DEVELOP")){
					jarray.put(4,jobj);
				}else if (or.getSales_stage().equals("QUALIFY")){
					jarray.put(5,jobj);
				}else if (or.getSales_stage().equals("ENGAGE")){
					jarray.put(6,jobj);
				}else if (or.getSales_stage().equals("PLAN")){
					jarray.put(7,jobj);
				}
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jarray.toString();
	}
	
	
	@SuppressWarnings("unchecked")
	public static String getEstimatedRevenuData(){
		
		List temp = (List)DAOFactory.getDAOFactory().getSqlQueryDAO().getTotalEstimatedRevenue();
		JSONArray gpArray = getWeightedEstimatedGPData();
		JSONArray nbArray = getNewBusinessData();
		JSONArray jarray = new JSONArray();
//		JSONObject lineOfBus = new JSONObject();
		try {
		
			for (int i=0;i<temp.size();i++){
				EstRevReport estRevReport = (EstRevReport)temp.get(i);
				JSONObject jobj = new JSONObject();
				jobj.put("STAGE",estRevReport.getSales_stage());
				jobj.put("ESTREV", estRevReport.getUsd_revenue());
				jobj.put("ESTREV_EUR", estRevReport.getEuro_revenue());
				jobj.put("WESTREV", getWinNumber(estRevReport.getSales_stage(), estRevReport.getUsd_revenue()));
				jobj.put("WESTREV_EUR", getWinNumber(estRevReport.getSales_stage(),estRevReport.getEuro_revenue()));
				if (estRevReport.getSales_stage().equals("DEPLOY")){
					appendWeightedGP(jobj, gpArray.getJSONObject(0));
					appendNewBusiness(jobj, nbArray.getJSONObject(0));
					jarray.put(0,jobj);
				}else if (estRevReport.getSales_stage().equals("CLOSE")){
					appendWeightedGP(jobj, gpArray.getJSONObject(1));
					appendNewBusiness(jobj, nbArray.getJSONObject(1));
					jarray.put(1,jobj);
				}else if (estRevReport.getSales_stage().equals("PROOF")){
					appendWeightedGP(jobj, gpArray.getJSONObject(2));
					appendNewBusiness(jobj, nbArray.getJSONObject(2));
					jarray.put(2,jobj);
				}else if (estRevReport.getSales_stage().equals("SOLUTION")){
					appendWeightedGP(jobj, gpArray.getJSONObject(3));
					appendNewBusiness(jobj, nbArray.getJSONObject(3));
					jarray.put(3,jobj);
				}else if (estRevReport.getSales_stage().equals("DEVELOP")){
					appendWeightedGP(jobj, gpArray.getJSONObject(4));
					appendNewBusiness(jobj, nbArray.getJSONObject(4));
					jarray.put(4,jobj);
				}else if (estRevReport.getSales_stage().equals("QUALIFY")){
					appendWeightedGP(jobj, gpArray.getJSONObject(5));
					appendNewBusiness(jobj, nbArray.getJSONObject(5));
					jarray.put(5,jobj);
				}else if (estRevReport.getSales_stage().equals("ENGAGE")){
					appendWeightedGP(jobj, gpArray.getJSONObject(6));
					appendNewBusiness(jobj, nbArray.getJSONObject(6));
					jarray.put(6,jobj);
				}else if (estRevReport.getSales_stage().equals("PLAN")){
					appendWeightedGP(jobj, gpArray.getJSONObject(7));
					appendNewBusiness(jobj, nbArray.getJSONObject(7));
					jarray.put(7,jobj);
				}
				
			}
//			lineOfBus.put("ALL",jarray);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		return lineOfBus.toString();
		return jarray.toString();
	}
	
	private static JSONObject appendNewBusiness(JSONObject toObject,JSONObject fromObject)throws JSONException{
		
		toObject.put("ESTNB",fromObject.get("ESTNB"));
		toObject.put("ESTNB_EUR",fromObject.get("ESTNB_EUR"));
		
		return toObject;
	}
	
	private static JSONObject appendWeightedGP(JSONObject toObject, JSONObject fromObject) throws JSONException{
		
		toObject.put("WESTGP", fromObject.get("WESTGP"));
		toObject.put("WESTGP_EUR", fromObject.get("WESTGP_EUR"));
		
		return toObject;
	}
	
	@SuppressWarnings("unchecked")
	private static JSONArray getNewBusinessData(){
		List temp = (List)DAOFactory.getDAOFactory().getSqlQueryDAO().getTotalEstimatedRevenueNewBusiness();
		JSONArray jarray = new JSONArray();
		try {
			for (int i=0;i<temp.size();i++){
				EstRevReport estRevReport = (EstRevReport)temp.get(i);
				JSONObject jobj = new JSONObject();
				jobj.put("STAGE",estRevReport.getSales_stage());
				jobj.put("ESTNB", estRevReport.getUsd_revenue());
				jobj.put("ESTNB_EUR", estRevReport.getEuro_revenue());
				jobj.put("WESTNBGP", getWinNumber(estRevReport.getSales_stage(), estRevReport.getUsd_revenue()));
				jobj.put("WESTNBGP_EUR", getWinNumber(estRevReport.getSales_stage(),estRevReport.getEuro_revenue()));
				if (estRevReport.getSales_stage().equals("DEPLOY")){
					jarray.put(0,jobj);
				}else if (estRevReport.getSales_stage().equals("CLOSE")){
					jarray.put(1,jobj);
				}else if (estRevReport.getSales_stage().equals("PROOF")){
					jarray.put(2,jobj);
				}else if (estRevReport.getSales_stage().equals("SOLUTION")){
					jarray.put(3,jobj);
				}else if (estRevReport.getSales_stage().equals("DEVELOP")){
					jarray.put(4,jobj);
				}else if (estRevReport.getSales_stage().equals("QUALIFY")){
					jarray.put(5,jobj);
				}else if (estRevReport.getSales_stage().equals("ENGAGE")){
					jarray.put(6,jobj);
				}else if (estRevReport.getSales_stage().equals("PLAN")){
					jarray.put(7,jobj);
				}
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jarray;
	}
	
	@SuppressWarnings("unchecked")
	private static JSONArray getWeightedEstimatedGPData(){
		
		List temp = (List)DAOFactory.getDAOFactory().getSqlQueryDAO().getTotalWeightedEstimatedGP();
		JSONArray jarray = new JSONArray();
		
		try {
			for (int i=0;i<temp.size();i++){
				EstRevReport estRevReport = (EstRevReport)temp.get(i);
				JSONObject jobj = new JSONObject();
				jobj.put("STAGE",estRevReport.getSales_stage());
				jobj.put("ESTGP", estRevReport.getUsd_revenue());
				jobj.put("ESTGP_EUR", estRevReport.getEuro_revenue());
				jobj.put("WESTGP", getWinNumber(estRevReport.getSales_stage(), estRevReport.getUsd_revenue()));
				jobj.put("WESTGP_EUR", getWinNumber(estRevReport.getSales_stage(),estRevReport.getEuro_revenue()));
				if (estRevReport.getSales_stage().equals("DEPLOY")){
					jarray.put(0,jobj);
				}else if (estRevReport.getSales_stage().equals("CLOSE")){
					jarray.put(1,jobj);
				}else if (estRevReport.getSales_stage().equals("PROOF")){
					jarray.put(2,jobj);
				}else if (estRevReport.getSales_stage().equals("SOLUTION")){
					jarray.put(3,jobj);
				}else if (estRevReport.getSales_stage().equals("DEVELOP")){
					jarray.put(4,jobj);
				}else if (estRevReport.getSales_stage().equals("QUALIFY")){
					jarray.put(5,jobj);
				}else if (estRevReport.getSales_stage().equals("ENGAGE")){
					jarray.put(6,jobj);
				}else if (estRevReport.getSales_stage().equals("PLAN")){
					jarray.put(7,jobj);
				}
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jarray;
	}

}
