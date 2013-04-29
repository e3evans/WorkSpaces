
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.stream.IDataSetProducer;
import org.dbunit.dataset.stream.StreamingDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.xml.sax.InputSource;

public class DataInjector {


	private static final int partial = 0;

	private static final int dependent = 2;

	private static final int full = 4;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			int export_type = partial;
			
			DatabaseOperation dbOp = DatabaseOperation.CLEAN_INSERT;
			
			IDataSetProducer producer = null;
			IDataSet dataSet = null;

			switch (export_type) {
			case partial:
			    AbstractData.purgeOracleRecycleBin();
				// inject a partial database export into database
				dataSet = new FlatXmlDataSet(new FileInputStream("export/partial.xml"));
				break;
			case dependent:
			    AbstractData.purgeOracleRecycleBin();
				// inject a partial database export into data
				dataSet = new FlatXmlDataSet(new FileInputStream(
					"export/dependents.xml"));
				break;

			case full:
			    AbstractData.purgeOracleRecycleBin();
				// inject a full database export into database
				producer = new FlatXmlProducer(new InputSource(
						"export/full.xml"));
				dataSet = new StreamingDataSet(producer);
				break;
			default:
				break;
			}

			dbOp.execute(AbstractData.getOracleConnection(), dataSet);

		} catch (DataSetException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DatabaseUnitException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}