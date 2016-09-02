package sqlimpl;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class SQLDataLoader {
	
	private Connection connection;
	
	private Statement statement;
	
	private PreparedStatement preparedStatement;
	
	private ResultSet resultSet;
	
	private List<String> labels = new LinkedList<>();
	
	private File srcFile;
		
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
			String sql="INSERT INTO "+tableName+sqlm+" VALUES(?,?,?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			for (int k=0;k<values.size();k++){
				Integer posn=k+1;
				String param=values.get(k);	
				if (k==values.size()-2){
						preparedStatement.setDouble(posn,Double.parseDouble(param));
					}
				else if (k==values.size()-4){
					preparedStatement.setInt(posn,Integer.parseInt(param));
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
	
	public boolean readWriteFile(String address, String tableName){
		srcFile = new File(address);
		getLabels(tableName);
		try (BufferedReader in = new BufferedReader(new FileReader(srcFile));){
			String line;
			line=in.readLine();
			String[]sentence;
			List<String> feeder;
			while ((line = in.readLine()) != null){
			sentence=line.split(",");
				feeder = new LinkedList();
				feeder = Arrays.asList(sentence);
				inserDataPrepared(tableName,labels,feeder);
			}
			return true;
		} catch (IOException ex){
		  return false;
		}
	}
	

	
	public static void main(String[] args) {
		
		SQLDataLoader sqlDataLoader = new SQLDataLoader("abc");
		String srcFileAddress= "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user17\\config17\\gl.csv";
		sqlDataLoader.readWriteFile(srcFileAddress,"mediumgl");
	

	}

}
