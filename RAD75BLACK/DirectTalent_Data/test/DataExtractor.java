
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.QueryDataSet;
import org.dbunit.database.search.TablesDependencyHelper;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.filter.ITableFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.oracle.OracleConnection;
import org.dbunit.util.search.SearchException;

public class DataExtractor {
    
	private static final int partial = 0;

	private static final int dependent = 2;

	private static final int full = 4;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws SearchException {
		try {
		    int export_type = partial;
		    
		    switch (export_type) {
			case partial:
				//partial database export
				//used only to read
				QueryDataSet partialDataSet = new QueryDataSet(AbstractData.getOracleConnection());
				partialDataSet.addTable("SITES");
				partialDataSet.addTable("ADDRESSES");
				partialDataSet.addTable("BRANCHES");
				partialDataSet.addTable("ADVERTISEMENTCONTACTS");
				partialDataSet.addTable("ADVERTISEMENTS");
				partialDataSet.addTable("CANDIDATES");
				partialDataSet.addTable("CANDIDATECOVERLETTERS");
				partialDataSet.addTable("CANDIDATEEDUCATIONS");
				partialDataSet.addTable("CANDIDATEJOBHISTORIES");
				partialDataSet.addTable("CANDIDATEPREFERENCES");
				partialDataSet.addTable("CANDIDATEREFERENCES");
				partialDataSet.addTable("CANDIDATESAVEJOBS");
				partialDataSet.addTable("SKILLS");
				partialDataSet.addTable("CANDIDATESKILLS");
				partialDataSet.addTable("CAREERHARMONY");
				//partialDataSet.addTable("CONFIGURATION");
				//partialDataSet.addTable("CONSENT");
				//partialDataSet.addTable("COUNTRIES");
				//partialDataSet.addTable("COUNTRYLOCATIONS");
				//partialDataSet.addTable("EDUCATIONDEGREES");
				//partialDataSet.addTable("GEOLOCATION");
				//partialDataSet.addTable("LOCATIONNAMES");
				//partialDataSet.addTable("LOOKUP_VALUES");
				partialDataSet.addTable("LOSTCANDIDATES");
				//partialDataSet.addTable("MANPOWER_REG_DATA");
				//partialDataSet.addTable("NATIONALITY_MAPPING");
				partialDataSet.addTable("OTHER_CANDIDATE_DETAILS");
				partialDataSet.addTable("PHONES");
				//partialDataSet.addTable("PLAN_TABLE");
				//partialDataSet.addTable("POSTAL_CODE_CAMPUS");
				//partialDataSet.addTable("REGIONS");
				//partialDataSet.addTable("REQUIREDSKILLS");
				//partialDataSet.addTable("STEERING_QUESTIONS");
				//partialDataSet.addTable("USERLOGINHISTORIES");

				FlatXmlDataSet.write(partialDataSet, new FileOutputStream(
						"export/partial.xml"));
				break;
			case dependent:
				// dependent tables database export: export table X and all
				// tables that have a PK which is a FK on X, in the right 
			    // order for insertion
			    OracleConnection oconn = AbstractData.getOracleConnection();
				String[] depTableNames = 
				    TablesDependencyHelper.getAllDependentTables(oconn, "CANDIDATES" );
				IDataSet depDataset = oconn.createDataSet( depTableNames );
				ITableFilter filter = new DatabaseSequenceFilter( oconn );
				FlatXmlDataSet.write(depDataset, new FileOutputStream(
					"export/dependents.xml"));
				break;
			case full:
				// full database export
				IDataSet fullDataSet = AbstractData.getOracleConnection().createDataSet();
				FlatXmlDataSet.write(fullDataSet, new FileOutputStream(
					"export/full.xml"));
				break;
		    }
			
		} catch (DataSetException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}