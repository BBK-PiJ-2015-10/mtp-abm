package abc.jdbcImpl;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseConnectionImplT3 {
	
	public static void main(String args[]){
		
	
		try {
			
			//Get Connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://LocalHost:3306/jdbctutorial","root","tonto");
			System.out.println("Connection Succesfull");
			
			//Create Statement
			Statement st = connection.createStatement();
			
			//ExecuteQuery
			
			//ResultSet
			ResultSet rs = st.executeQuery("SELECT * FROM `salespeople`");
			
			while(rs.next()){
				System.out.println(rs.getInt("id")+" "+rs.getString("s_name")+" "+rs.getString("s_city")+" "+rs.getFloat("comm"));
			}
			
			
		} catch (Exception ex){
			ex.printStackTrace();
		}
			
		
		
	}
	

}
