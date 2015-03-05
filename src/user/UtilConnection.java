package user;

import java.sql.Connection;
import java.sql.DriverManager;

public class UtilConnection {
	private static final long serialVersionUID = 1L;
	
	public static Connection getCn(){
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/XSGYGLXT";
		String user = "root";
		String password = "123456";
		Connection cn = null;
		try {
			Class.forName(driver);
			cn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cn;
	}
	
}
