package pagecode.DTWidgetReportDownload;

import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.manpower.directtalent.rss.ui.service.OracleReportServiceLocator;

import pagecode.PageCodeBase;

public class DTWidgetReportDownloadView extends PageCodeBase {

	private List reports;
	private String fileURL;

	private Logger logger = Logger.getLogger(DTWidgetReportDownloadView.class);
	
	public DTWidgetReportDownloadView() {
		reports = loadReportBeans();
		fileURL = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/ReportRetrieveServlet";
	}

	private List loadReportBeans() {
		List reportBeans = null;
		try {
			reportBeans = OracleReportServiceLocator.getInstance()
					.getReportService().findAllReports();
			logger.debug("Reports from database: " + reportBeans.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reportBeans;
	}

	public List getReports() {
		return reports;
	}
	
	public String getFileURL() {
		return fileURL;
	}

}
