package ModelConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractConnection {
	protected static Connection con;
	
	protected  AbstractConnection(String connectionString)
	{
		
		try {
			//đăng ký driver
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			//Driver driver = new net.sourceforge.jtds.jdbc.Driver();
			//DriverManager.registerDriver(driver);
			
			
			//tạo kết nối đến CSDL
			String conString ="jdbc:jtds:sqlserver://localhost:1433/" + connectionString + ";integratedSecurity=true";
			con = DriverManager.getConnection(conString);
			System.out.println("Connected!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}