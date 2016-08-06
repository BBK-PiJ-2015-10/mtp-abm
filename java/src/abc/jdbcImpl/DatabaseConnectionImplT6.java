package abc.jdbcImpl;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

public class DatabaseConnectionImplT6 {
	
	private Connection connection;
	

	
	public DatabaseConnectionImplT6(){
		
		try {
			//Get Connection
			connection = DriverManager.getConnection("jdbc:mysql://LocalHost:3306/jdbctutorial","root","tonto");
			System.out.println("Connection Succesfull");
				
		} catch (Exception ex){
			ex.printStackTrace();
		}
		
	}
	
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
	
	public void selectRowsID(int id){
		Statement st = null;
		ResultSet rs=null;
		try {
			st = connection.createStatement();
			rs=st.executeQuery("SELECT * FROM salespeople WHERE id="+id);
			
			if(rs.next()){
				System.out.println(rs.getInt("id")+" "+rs.getString("s_name")+" "+rs.getString("s_city")+" "+rs.getFloat("comm"));
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void selectRowsCity(String cityName){
		Statement st = null;
		ResultSet rs=null;
		try {
			st = connection.createStatement();
			rs=st.executeQuery("SELECT * FROM salespeople WHERE s_city='"+cityName+"'");
			
			while(rs.next()){
				System.out.println(rs.getInt("id")+" "+rs.getString("s_name")+" "+rs.getString("s_city")+" "+rs.getFloat("comm"));
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void inserData(){
		
		Statement st = null;
		
		try {
			
			st = connection.createStatement();
			st.executeUpdate("INSERT INTO salespeople(s_name,s_city,comm) VALUES('Parker','Miami',.26)");
			System.out.println("Row Inserted succesfully");
			
		} catch (Exception ex){
			ex.printStackTrace();
		}
		
		
		
	}
	
	
	
	public static void main(String args[]){
		
		DatabaseConnectionImplT6 dbc = new DatabaseConnectionImplT6();
		//dbc.selectRowsID(1);
		//dbc.selectRowsID(3);
		//dbc.selectRowsCity("London");
		dbc.inserData();
		//System.out.println(db.getData());
		
	}
	


	
}
