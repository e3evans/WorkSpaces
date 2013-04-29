
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.ForwardOnlyResultSetTableFactory;
import org.dbunit.ext.oracle.OracleConnection;

/*
 * Created on Oct 2, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author David Cook
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class AbstractData {

	//private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DB_URL = "jdbc:oracle:thin:@tsdmzaix02:1521:itdv1";

	private static final String DB_SCHEMA = "MPNETADMIN23";

	private static final String DB_USER = "mpnetadmin23";

	private static final String DB_PWD = "a31204";

	private static final String DB_DRIVER = "oracle.jdbc.OracleDriver";

	protected static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class driverClass = Class.forName(DB_DRIVER);
		Connection aConnection = DriverManager.getConnection(DB_URL, DB_USER,
				DB_PWD);
		return aConnection;
	}

	public static OracleConnection getOracleConnection()
			throws ClassNotFoundException, SQLException {
		OracleConnection aConnection = new OracleConnection(getConnection(),
				DB_SCHEMA);
		DatabaseConfig config = aConnection.getConfig();
		config.setProperty(DatabaseConfig.PROPERTY_RESULTSET_TABLE_FACTORY,
				new ForwardOnlyResultSetTableFactory());
		return aConnection;
	}
	
	public static void purgeOracleRecycleBin() throws ClassNotFoundException, SQLException {
	    getConnection().createStatement().execute("purge recyclebin");
	}

}
