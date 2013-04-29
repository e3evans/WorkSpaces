package com.manpower.hbn.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.manpower.hbn.dao.SQLQueryDAO;
import com.manpower.hbn.shared.HibernateUtilities;
import com.manpower.hbn.shared.dao.GenericHibernateDAO;
import com.manpower.widget.hbn.beans.DSW306090;
import com.manpower.widget.hbn.beans.EstRevReport;
import com.manpower.widget.hbn.beans.OpportunityReport;
import com.manpower.widget.hbn.beans.TopTen;

public class SQLQueryDAOImpl extends GenericHibernateDAO implements SQLQueryDAO {

	public SQLQueryDAOImpl(Session session) {
		super(DSW306090.class, session);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Object> get306090() {
		StringBuffer sb = new StringBuffer();
		sb.append("select 1 opp_id,30 \"closing\",NVL(sum(a.est_revenue),0)as est_revenue,NVL(sum(a.gp),0) as GP from (select b.est_revenue*r.rate_mult est_revenue,  b.gp*r.rate_mult gp from sysadm.ps_rsf_opportunity t,  sysadm.ps_rt_rate_tbl r,  (select opportunity_id,sum(m_mpg_share_estrev) est_revenue,sum(m_est_gp) gp from sysadm.ps_m_rsf_opp_prod group by opportunity_id) b,  (select from_cur,max(effdt) dttm from sysadm.ps_rt_rate_tbl group by from_cur) c  where t.opportunity_status in ('0','5')  and t.sales_stage!=' '  and t.opportunity_id = b.opportunity_id(+)  and r.from_cur=t.currency_cd  and r.to_cur='USD'  and r.effdt = c.dttm  and c.from_cur = t.currency_cd  and t.est_close_dt between trunc(sysdate) and trunc(sysdate)+30) a union select 2 opp_id,60 \"closing\",NVL(sum(a.est_revenue),0)as est_revenue,NVL(sum(a.gp),0)  as GP from (select b.est_revenue*r.rate_mult est_revenue,  b.gp*r.rate_mult gp from sysadm.ps_rsf_opportunity t,  sysadm.ps_rt_rate_tbl r,  (select opportunity_id,sum(m_mpg_share_estrev) est_revenue,sum(m_est_gp) gp from sysadm.ps_m_rsf_opp_prod group by opportunity_id) b,  (select from_cur,max(effdt) dttm from sysadm.ps_rt_rate_tbl group by from_cur) c  where t.opportunity_status in ('0','5')  and t.sales_stage!=' '  and t.opportunity_id = b.opportunity_id(+)  and r.from_cur=t.currency_cd  and r.to_cur='USD'  and r.effdt = c.dttm  and c.from_cur = t.currency_cd  and t.est_close_dt between trunc(sysdate) and trunc(sysdate)+60) a union select 3 opp_id,90 \"closing\",NVL(sum(a.est_revenue),0)as est_revenue,NVL(sum(a.gp),0)  as GP from (select b.est_revenue*r.rate_mult est_revenue,  b.gp*r.rate_mult gp from sysadm.ps_rsf_opportunity t,  sysadm.ps_rt_rate_tbl r,  (select opportunity_id,sum(m_mpg_share_estrev) est_revenue,sum(m_est_gp) gp from sysadm.ps_m_rsf_opp_prod group by opportunity_id) b,  (select from_cur,max(effdt) dttm from sysadm.ps_rt_rate_tbl group by from_cur) c  where t.opportunity_status in ('0','5')  and t.sales_stage!=' '  and t.opportunity_id = b.opportunity_id(+)  and r.from_cur=t.currency_cd  and r.to_cur='USD'  and r.effdt = c.dttm  and c.from_cur = t.currency_cd  and t.est_close_dt between trunc(sysdate) and trunc(sysdate)+90) a  ");
		SQLQuery query = HibernateUtilities.currentSession().createSQLQuery(sb.toString()).addEntity(DSW306090.class);
		List tempList = query.list();
		HibernateUtilities.currentSession().close();
		return tempList;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getOpportunityCount() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("select rownum as opp_id,b.sales_stage,b.est_revenue from " +
				"(select a.sales_stage as sales_stage,count(*)as est_revenue from " +
				"(select decode(upper(t.sales_stage),' ','NO STAGE',upper(t.sales_stage)) " +
				"as sales_stage,t.est_revenue " +
				"as est_revenue from sysadm.ps_rsf_opportunity t where t.opportunity_status in ('0','5')and t.sales_stage!=' ') a group by a.sales_stage order by a.Sales_Stage asc) b");
		
		SQLQuery query = HibernateUtilities.currentSession().createSQLQuery(sb.toString()).addEntity(OpportunityReport.class);
		List tempList = query.list();
		HibernateUtilities.currentSession().close();
		return tempList;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getTopTenOpportunites() {
		StringBuffer sb = new StringBuffer(); 
		sb.append("select rownum opp_id,a.OPPORTUNITY_NAME,a.name1,NVL(a.est_revenue,0) as est_revenue,NVL(a.gp,0)as gp,a.sales_stage, a.sales_user_name,a.act_close_dt as est_close_dt from (select t.OPPORTUNITY_NAME,t.name1,NVL(b.est_revenue,0) est_revenue,NVL(b.gp,0) gp, upper(t.sales_stage)as sales_stage,t.SALES_USER_NAME,t.ACT_CLOSE_DT  from sysadm.ps_rsf_opportunity t, sysadm.ps_rt_rate_tbl r, (select opportunity_id,sum(m_mpg_share_estrev) est_revenue,sum(m_est_gp) gp  from sysadm.ps_m_rsf_opp_prod group by opportunity_id) b, (select from_cur,max(effdt) dttm from sysadm.ps_rt_rate_tbl group by from_cur) c where t.opportunity_status in ('0','5') and t.sales_stage!=' ' and t.opportunity_id = b.opportunity_id(+) and r.from_cur=t.currency_cd and r.to_cur='USD' and r.effdt = c.dttm and c.from_cur = t.currency_cd order by 3 desc) a  where rownum < 11 ");
		SQLQuery query = HibernateUtilities.currentSession().createSQLQuery(sb.toString()).addEntity(TopTen.class);
		List<Object> tempList = query.list();
		HibernateUtilities.currentSession().close();
		return tempList;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getTotalEstimatedRevenue() {
		StringBuffer sb = new StringBuffer(); 
		sb.append("select rownum as opp_id,d.sales_stage,NVL(d.usd_revenue,0) as usd_revenue,NVL(e.eur_revenue,0) as euro_revenue  from (select a.sales_stage as sales_stage,sum(a.usd_revenue)as usd_revenue  from (select decode(upper(t.sales_stage),' ','NO STAGE',  upper(t.sales_stage)) as sales_stage,  b.est_revenue*r.rate_mult as usd_revenue  from sysadm.ps_rsf_opportunity t,  sysadm.ps_rt_rate_tbl r,  (select opportunity_id,sum(m_mpg_share_estrev) est_revenue  from sysadm.ps_m_rsf_opp_prod group by opportunity_id) b,  (select from_cur,max(effdt) dttm from sysadm.ps_rt_rate_tbl group by from_cur) c  where t.opportunity_status in ('0','5')  and t.sales_stage!=' '  and t.opportunity_id = b.opportunity_id(+)  and r.from_cur=t.currency_cd  and r.to_cur='USD'  and r.effdt = c.dttm  and c.from_cur = t.currency_cd) a  group by a.sales_stage order by a.Sales_Stage asc) d,  (select a.sales_stage as sales_stage,sum(a.eur_revenue)as eur_revenue  from (select decode(upper(t.sales_stage),' ','NO STAGE',  upper(t.sales_stage)) as sales_stage,  b.est_revenue*r.rate_mult as eur_revenue  from sysadm.ps_rsf_opportunity t,  sysadm.ps_rt_rate_tbl r,  (select opportunity_id,sum(m_mpg_share_estrev) est_revenue  from sysadm.ps_m_rsf_opp_prod group by opportunity_id) b,  (select from_cur,max(effdt) dttm from sysadm.ps_rt_rate_tbl group by from_cur) c  where t.opportunity_status in ('0','5')  and t.sales_stage!=' '  and t.opportunity_id = b.opportunity_id(+)  and r.from_cur=t.currency_cd  and r.to_cur='EUR'  and r.effdt = c.dttm  and c.from_cur = t.currency_cd) a  group by a.sales_stage order by a.Sales_Stage asc) e  where d.sales_stage=e.sales_stage");
		SQLQuery query = HibernateUtilities.currentSession().createSQLQuery(sb.toString()).addEntity(EstRevReport.class);
		List<Object> tempList = query.list();
		HibernateUtilities.currentSession().close();
		return tempList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getTotalEstimatedRevenueNewBusiness() {
		StringBuffer sb = new StringBuffer(); 
		sb.append("select rownum as opp_id,d.sales_stage,NVL(d.usd_revenue,0) as usd_revenue,NVL(e.eur_revenue,0)  as euro_revenue from (select a.sales_stage as sales_stage,sum(a.usd_revenue)as usd_revenue from (select decode(upper(t.sales_stage),' ','NO STAGE',  upper(t.sales_stage))  as sales_stage,  b.new_business*r.rate_mult as usd_revenue from sysadm.ps_rsf_opportunity t,  sysadm.ps_rt_rate_tbl r, (select opportunity_id,sum(m_est_new) new_business from sysadm.ps_m_rsf_opp_prod group by opportunity_id) b, (select from_cur,max(effdt) dttm  from sysadm.ps_rt_rate_tbl group by from_cur) c where t.opportunity_status in ('0','5')  and t.sales_stage!=' ' and t.opportunity_id = b.opportunity_id(+)  and r.from_cur=t.currency_cd and r.to_cur='USD'  and r.effdt = c.dttm  and c.from_cur = t.currency_cd) a group by a.sales_stage order by a.Sales_Stage asc) d, (select a.sales_stage as sales_stage,sum(a.eur_revenue)as eur_revenue from (select decode(upper(t.sales_stage),' ','NO STAGE',  upper(t.sales_stage))  as sales_stage,  b.new_business*r.rate_mult as eur_revenue from sysadm.ps_rsf_opportunity t,  sysadm.ps_rt_rate_tbl r,  (select opportunity_id,sum(m_est_new) new_business from sysadm.ps_m_rsf_opp_prod group by opportunity_id) b, (select from_cur,max(effdt) dttm from sysadm.ps_rt_rate_tbl group by from_cur) c where t.opportunity_status in ('0','5')  and t.sales_stage!=' ' and t.opportunity_id = b.opportunity_id(+) and r.from_cur=t.currency_cd  and r.to_cur='EUR' and r.effdt = c.dttm  and c.from_cur = t.currency_cd) a group by a.sales_stage order by a.Sales_Stage asc) e  where d.sales_stage=e.sales_stage");
		SQLQuery query = HibernateUtilities.currentSession().createSQLQuery(sb.toString()).addEntity(EstRevReport.class);
		List<Object> tempList = query.list();
		HibernateUtilities.currentSession().close();
		return tempList;
	}
	

	@SuppressWarnings("unchecked")
	public List<Object> getTotalWeightedEstimatedGP() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("select rownum as opp_id,d.sales_stage,NVL(d.usd_revenue,0) as usd_revenue,NVL(e.eur_revenue,0) as euro_revenue from (select a.sales_stage as sales_stage,sum(a.usd_revenue)as usd_revenue from (select decode(upper(t.sales_stage),' ','NO STAGE',  upper(t.sales_stage)) as sales_stage,  b.est_revenue*r.rate_mult as usd_revenue from sysadm.ps_rsf_opportunity t,  sysadm.ps_rt_rate_tbl r,  (select opportunity_id,sum(m_est_gp) est_revenue from sysadm.ps_m_rsf_opp_prod group by opportunity_id) b,  (select from_cur,max(effdt) dttm from sysadm.ps_rt_rate_tbl group by from_cur) c  where t.opportunity_status in ('0','5')  and t.sales_stage!=' '  and t.opportunity_id = b.opportunity_id(+)  and r.from_cur=t.currency_cd  and r.to_cur='USD'  and r.effdt = c.dttm  and c.from_cur = t.currency_cd) a  group by a.sales_stage order by a.Sales_Stage asc) d, (select a.sales_stage as sales_stage,sum(a.eur_revenue)as eur_revenue from (select decode(upper(t.sales_stage),' ','NO STAGE',  upper(t.sales_stage)) as sales_stage,  b.est_revenue*r.rate_mult as eur_revenue from sysadm.ps_rsf_opportunity t,  sysadm.ps_rt_rate_tbl r,  (select opportunity_id,sum(m_est_gp) est_revenue from sysadm.ps_m_rsf_opp_prod group by opportunity_id) b,  (select from_cur,max(effdt) dttm from sysadm.ps_rt_rate_tbl group by from_cur) c  where t.opportunity_status in ('0','5')  and t.sales_stage!=' '  and t.opportunity_id = b.opportunity_id(+)  and r.from_cur=t.currency_cd  and r.to_cur='EUR'  and r.effdt = c.dttm  and c.from_cur = t.currency_cd) a  group by a.sales_stage order by a.Sales_Stage asc) e where d.sales_stage=e.sales_stage ");
		SQLQuery query = HibernateUtilities.currentSession().createSQLQuery(sb.toString()).addEntity(EstRevReport.class);
		List<Object> tempList = query.list();
		HibernateUtilities.currentSession().close();
		return tempList;
	}

	@Override
	public List<Object> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

}
