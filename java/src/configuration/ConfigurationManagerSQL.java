package configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;

import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class ConfigurationManagerSQL extends ConfigurationManagerAbstract implements Serializable {

	private List<String> glConnectionSettings = new LinkedList<>();
		
	public ConfigurationManagerSQL(File file){
		super(file);
	}
		
	public boolean captureGLConnectionSettings(Scanner sc){
		boolean validEntry = false;
		String selection=null;
		while (!validEntry){
			try {
				System.out.println("Please enter the url that you wish to connect to");
				selection=sc.nextLine();
				glConnectionSettings.add(selection);
				System.out.println("Please enter the Username");
				selection=sc.nextLine();
				glConnectionSettings.add(selection);
				System.out.println("Please enter the Password");
				selection=sc.nextLine();
				glConnectionSettings.add(selection);
				System.out.println("Please enter the Database name");
				selection=sc.nextLine();
				glConnectionSettings.add(selection);
				validEntry = testConnection() && testTable(selection);
				if (!validEntry){
					System.out.println("Can't connect to the SQL database");
					glConnectionSettings.clear();
				}
				
			} catch (NoSuchElementException | IllegalStateException ex) {
				glConnectionSettings.clear();
				return false;	
			}
		}
		return true;
	}
	
	public boolean testConnection(){
		try {
			DriverManager.getConnection(glConnectionSettings.get(0),
			                            glConnectionSettings.get(1),glConnectionSettings.get(2));
			
		} catch (SQLException | IndexOutOfBoundsException ex) {
			return false;
		}
		return true;
	}
	
	
	public boolean testTable(String SQLTableName){
		try {
			Statement st = getGLConnection().createStatement();
			st.executeQuery("SELECT * FROM `"+SQLTableName+"`");
			return true;
		} catch (SQLException | IndexOutOfBoundsException ex){
			return false;
		}
		
	}
	
	
	
	public Connection getGLConnection(){
		Connection glConnection;
		try {
			glConnection = DriverManager.getConnection(glConnectionSettings.get(0),
					       glConnectionSettings.get(1),glConnectionSettings.get(2));
			
		} catch (SQLException |IndexOutOfBoundsException ex) {
			return null;
		}
		return glConnection;
	}
	
	
	//This is a new one
	public List<String> getGLConnectionSettings(){
		return this.glConnectionSettings;
	}
	
		
	public String getGLFileName(){
		try {
			return glConnectionSettings.get(glConnectionSettings.size()-1);
		} catch (IndexOutOfBoundsException ex){
			return null;
		}
	}
		
	public boolean grabSQLTableAttributes(String SQLTableName, Map map){
		try {
			Statement st = getGLConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM `"+SQLTableName+"`");
			Set<String> fieldsSet = new HashSet<>();
			for (int i=1;i<=rs.getMetaData().getColumnCount();i++){
				fieldsSet.add(rs.getMetaData().getColumnName(i));
			}
			map.put(SQLTableName, fieldsSet);
		} catch (SQLException ex){
			return false;
		}
		return true;
	}
	
	
	public void grabGL(){
		grabSQLTableAttributes(glConnectionSettings.get(glConnectionSettings.size()-1),glFilesAttributesMap);
	}
	
			
	public void save(){
		try (ObjectOutputStream encode = new ObjectOutputStream
				(new FileOutputStream(file.getAbsolutePath()+"\\"+file.getName()+".dat"));)
		{
			encode.writeObject(file);
			encode.writeObject(bpaFilesMap);
			encode.writeObject(glConnectionSettings);
			encode.writeObject(bpaFilesAttributesMap);
			encode.writeObject(glFilesAttributesMap);
			encode.writeObject(glFilesMainAttributesMap);
			encode.writeObject(bpaFilesMainAttributesMap);
			encode.writeObject(glbpamapFile);
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
	}
	
	public void save(String newname){
		try (ObjectOutputStream encode = new ObjectOutputStream
				(new FileOutputStream(file.getAbsolutePath()+"\\"+newname+".dat"));)
		{
			encode.writeObject(file);
			encode.writeObject(bpaFilesMap);
			encode.writeObject(glConnectionSettings);
			encode.writeObject(bpaFilesAttributesMap);
			encode.writeObject(glFilesAttributesMap);
			encode.writeObject(glFilesMainAttributesMap);
			encode.writeObject(bpaFilesMainAttributesMap);
			encode.writeObject(glbpamapFile);
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
	}
	
	public boolean capture(String configurationname){
		boolean isPresent;
		if (this.file.exists()){
			try (ObjectInputStream incode = new ObjectInputStream
					(new FileInputStream(file.getAbsolutePath()+"\\"+configurationname+".dat"));)
			{
				file = (File)incode.readObject();
				bpaFilesMap=(Map<String,File>)incode.readObject();
				glConnectionSettings=(List<String>)incode.readObject();
				bpaFilesAttributesMap=(Map<String,Set<String>>)incode.readObject();
				glFilesAttributesMap=(Map<String,Set<String>>)incode.readObject();
				glFilesMainAttributesMap=(Map<String,List<String>>)incode.readObject();
				bpaFilesMainAttributesMap=(Map<String,List<String>>)incode.readObject();
				glbpamapFile=(File)incode.readObject();
				isPresent = true;
			} 
			  catch (ClassNotFoundException | IOException | NullPointerException ex){
				isPresent = false;	
			  }	
		}
		else {
			System.out.println("File doesn't exists");
			isPresent=false;
		}
		return isPresent;
	}
	
		
}
