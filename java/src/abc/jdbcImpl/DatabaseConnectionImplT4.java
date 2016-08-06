package abc.jdbcImpl;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

public class DatabaseConnectionImplT4 {
	
	private Connection connection;
	
	private List<String> getData(){
		List<String> list = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.createStatement();
			rs = st.executeQuery("SELECT * FROM `salespeople`");
			while(rs.next()){
				list.add(rs.getInt("id")+" "+rs.getString("s_name")+" "+rs.getString("s_city")+" "+rs.getFloat("comm"));
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
		return list;
		
	}
	
	public DatabaseConnectionImplT4(){
		
		try {
			//Get Connection
			connection = DriverManager.getConnection("jdbc:mysql://LocalHost:3306/jdbctutorial","root","tonto");
			System.out.println("Connection Succesfull");
				
		} catch (Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	
	public static void main(String args[]){
		
		DatabaseConnectionImplT4 db = new DatabaseConnectionImplT4();
		
		System.out.println(db.getData());
		
	}
	


	
}
