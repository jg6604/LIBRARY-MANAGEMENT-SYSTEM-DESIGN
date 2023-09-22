
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Connect {
	
	public static Connection getConnection()
	{
	try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root","abc123##");
	        //Connected to MySQL
	        return con;
	 } 
	 catch (Exception ex) {
	        ex.printStackTrace();
	 }
	return null;
	}
	
	
}
