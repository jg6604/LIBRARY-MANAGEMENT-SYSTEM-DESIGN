import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Create {
	
	public static void create() {
	    try {
	    Connection connection=Connect.getConnection();
	    ResultSet resultSet = connection.getMetaData().getCatalogs();
	    
	        while (resultSet.next()) {
	           //Get the database name, which is at position 1
	          String databaseName = resultSet.getString(1);
	          if(databaseName.equals("BOOKRENT")) {
	              
	              Statement stmt = connection.createStatement();
	              //Drop database if it pre-exists to reset the complete database
	              String sql = "DROP DATABASE BOOKRENT";
	              stmt.executeUpdate(sql);
	          }
	        }
	          Statement stmt = connection.createStatement();
	           
	          String sql = "CREATE DATABASE BOOKRENT"; //Create Database
	          stmt.executeUpdate(sql); 
	          stmt.executeUpdate("USE BOOKRENT"); //Use Database
	          //Create Users Table
	          String sql1 = "CREATE TABLE USERS(UID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, USERNAME VARCHAR(30), PASSWORD VARCHAR(30), ADMIN BOOLEAN)";
	          stmt.executeUpdate(sql1);
	          //Insert into users table
	          stmt.executeUpdate("INSERT INTO USERS(USERNAME, PASSWORD, ADMIN) VALUES('admin','admin',TRUE)");
	          //Create Books table
	          stmt.executeUpdate("CREATE TABLE BOOKS(BID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, BNAME VARCHAR(50), GENRE VARCHAR(20), PRICE INT)");
	          //Create Issued Table
	          stmt.executeUpdate("CREATE TABLE ISSUED(IID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, UID INT, BID INT, ISSUED_DATE VARCHAR(20), RETURN_DATE VARCHAR(20), PERIOD INT, FINE INT)");
	          //Insert into books table
	          stmt.executeUpdate("INSERT INTO BOOKS(BNAME, GENRE, PRICE) VALUES ('The Boy in the Striped Pajamas', 'Historical', 10),  ('The Da Vinci Code', 'Mystery', 20), ('Tony and Susan','Mystery', 20), ('To Kill a Mockingbird', 'Southern Gothic', 25), ('The A.B.C. Murders','Mystery', 35)");
	           
	    resultSet.close();
	    }
	     catch (Exception ex) {
	         ex.printStackTrace();
	}
	}

	
}