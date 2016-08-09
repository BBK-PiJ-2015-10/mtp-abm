package sqlimpl;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.File;

public class SQLDataLoader {
	
	private Connection connection;
	
	private Statement statement;
	
	private PreparedStatement preparedStatement;
	
	private ResultSet resultSet;
	
	private List<String> labels = new LinkedList<>();
	
	private File sourceFile;
	
	
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
	
	public void inserData(String TableName){
		try {
			statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO tester(Legal_Entity,Department,Account,Amount) VALUES('NA','IS','200FL',1.26)");
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void inserDataPrepared(String tableName,List<String> parameters,List<String> values){
		try {
			String sqlm=null;
			for (int i=0;i<parameters.size();i++){
				if (sqlm==null){
					sqlm="("+parameters.get(i)+",";
				}
				else {
					if (i==parameters.size()-1){
						sqlm=sqlm+=parameters.get(i)+")";
					}
					else {
						sqlm=sqlm+=parameters.get(i)+",";
					}
				}	
			}
			String sql="INSERT INTO "+tableName+sqlm+" VALUES(?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			for (int k=0;k<values.size();k++){
				Integer posn=k+1;
				String param=values.get(k);	
				if (k==values.size()-1){
						preparedStatement.setDouble(posn,Double.parseDouble(param));
					}
					else {
						preparedStatement.setString(posn,param);
					}	
			}
			
			preparedStatement.executeUpdate();;
		} 
		catch (SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public boolean readFile(String address){
		String fileAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\config16\\gl.csv";
		sourceFile = new File(fileAddress);
		return sourceFile.exists();
	}
	

	
	

	public static void main(String[] args) {
		
		SQLDataLoader sqlDataLoader = new SQLDataLoader("abc");
		
		/*
		List<String> plist = new LinkedList<>();
		plist.add("Legal_Entity");
		plist.add("Department");
		plist.add("Account");
		plist.add("Amount");
		List<String> vlist = new LinkedList<>();
		vlist.add("AUS");
		vlist.add("ST");
		vlist.add("600MPS");
		vlist.add("5.99");
		sqlDataLoader.inserDataPrepared("tester",plist,vlist);
		*/
		System.out.println(sqlDataLoader.readFile("anything"));
		//labels.forEach(a->System.out.println(a));
		
		// TODO Auto-generated method stub

	}

}
