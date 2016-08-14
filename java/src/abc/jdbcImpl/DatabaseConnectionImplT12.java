package abc.jdbcImpl;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class DatabaseConnectionImplT12 {
	
	private Connection connection;
	
	public DatabaseConnectionImplT12(){
		try {
			connection = DriverManager.getConnection("jdbc:mysql://LocalHost:3306/jdbctutorial","root","tonto");
			System.out.println("Connection Succesfull");
				
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void test(){
		//List<String> list = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery("SELECT * FROM `salespeople`");
			while(rs.next()){
				List<String> list = new ArrayList<>();
				list.add(rs.getInt("id")+" "+rs.getString("s_name")+" "+rs.getString("s_city")+" "+rs.getFloat("comm"));
				//System.out.println(rs.getString(1));
				//System.out.println(rs.getString(2));
				//System.out.println(rs.getString(3));
				//System.out.println(rs.getFloat(4));
				System.out.println(list);
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
		
		
	}
	
	//These sections contains tests outside the tutorial scope
	private List<String> getLabels(){
		List<String> results = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			String SQLM ="salespeople";
			rs = st.executeQuery("SELECT * FROM `"+SQLM+"`");
			results = new LinkedList<>();
			for (int i=1;i<=rs.getMetaData().getColumnCount();i++){
				//rs.getMetaData().getColumnTypeName(column)
				//System.out.println(rs.getMetaData().getColumnClassName(i));
				//System.out.println(rs.getMetaData().get);
				//System.out.println((rs.getMetaData().getColumnType(i)));
				results.add(rs.getMetaData().getColumnTypeName(i));
				//results.add(rs.getMetaData().getColumnName(i));
			}			
		} catch (Exception ex){
			ex.printStackTrace();
		}
		return results;
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
	
	private void update(int rowID){
		Statement st=null;
		try {
			st = connection.createStatement();
			st.executeUpdate("UPDATE salespeople SET s_name='Paulino',s_city='Berlin',comm=0.18 WHERE id="+rowID);
			System.out.println("Updated Successfully");
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	private void delete(){
		Statement st=null;
		try {
			st = connection.createStatement();
			st.executeUpdate("DELETE FROM salespeople WHERE s_name='Paulino'");
			System.out.println("Row Deleted Successfully");
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void usingStatement(){
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs  = st.executeQuery("SELECT * FROM salespeople WHERE s_name='Gary' AND s_city='New York'");
			
			while(rs.next()){
				System.out.println(rs.getInt("id")+" "+rs.getString("s_name")+" "+rs.getString("s_city")+" "+rs.getFloat("comm"));
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	private void usingPrepareStatement(String name,String city){
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql="SELECT * FROM salespeople WHERE s_name=? AND s_city=?";
			pst = connection.prepareStatement(sql);
			pst.setString(1,name);
			pst.setString(2,city);
			rs = pst.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt("id")+" "+rs.getString("s_name")+" "+rs.getString("s_city")+" "+rs.getFloat("comm"));
			}	
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void insertData(String name, String city, Double comm){
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement("INSERT INTO salespeople (s_name,s_city,comm) VALUES(?,?,?)");
			pst.setString(1, name);
			pst.setString(2, city);
			pst.setDouble(3, comm);
			pst.executeUpdate();
			System.out.println("Inserted succesfully");
			
		} catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	
	private void update(int id,String name, String city, Double comm){
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement("UPDATE salespeople SET s_name=?, s_city=?, comm=? WHERE id=?");
			pst.setString(1, name);
			pst.setString(2, city);
			pst.setDouble(3, comm);
			pst.setInt(4, id);
			pst.executeUpdate();
			System.out.println("Updated succesfully");
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
		
	}
	
	
	public static void main(String args[]){
		
		DatabaseConnectionImplT12 dbc = new DatabaseConnectionImplT12();
		dbc.test();
		
		//dbc.getLabels().forEach(System.out::println);
		//dbc.getData().forEach(System.out::println);
		//dbc.update(9,"Trilly","Perugia",.30);
		//dbc.insertData("Bibbio","Perugia",.20);
		//dbc.usingStatement();
		//System.out.println();
		//dbc.usingPrepareStatement("Gary","New York");
		//dbc.selectRowsID(1);
		//dbc.selectRowsID(3);
		//dbc.selectRowsCity("London");
		//dbc.inserData();
		//System.out.println(db.getData());
		//dbc.update(5);
		//dbc.delete();
		
		
	}
	


	
}
