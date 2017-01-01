package dao;

//Imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Database connection class
 * @author Dooba
 *
 */
public class BaseDAO {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mydb";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	
	/**
	 * @return
	 * Creates db conn
	 */
	public static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dbConnection;
	}
}
