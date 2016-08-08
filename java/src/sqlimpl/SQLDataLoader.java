package sqlimpl;

import java.sql.DriverManager;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.omg.PortableInterceptor.NON_EXISTENT;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLDataLoader {
	
	private Connection connection;
	
	private Statement statement;
	
	private ResultSet resultSet;
	
	private List<String> labels = new LinkedList<>();
	
	//public List<String> getLabels
	
	public SQLDataLoader(String dataBase){
		try {
			connection = DriverManager.getConnection("jdbc:mysql://LocalHost:3306/"+dataBase,"root","tonto");
			System.out.println("Connection Succesfull");
				
		} catch (SQLException ex){
			ex.printStackTrace();
		}
		
	}
	
	public List<String> getLabels(String TableName){		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM `"+TableName+"`");
			for (int i=1;i<=resultSet.getMetaData().getColumnCount();i++){
				labels.add(resultSet.getMetaData().getColumnName(i));
			}			
		} catch (Exception ex){
			return null;
		}
		return labels;
	}
	

	
	

	public static void main(String[] args) {
		
		SQLDataLoader sqlDataLoader = new SQLDataLoader("abc");
		sqlDataLoader.getLabels("tester").forEach(System.out::println);
		//labels.forEach(a->System.out.println(a));
		
		// TODO Auto-generated method stub

	}

}
