package systemTesting.userTesting;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import configuration.ConfigurationManager;
import configuration.MapCreator;
import configuration.MapCreatorImpl;

public class TestMapCreatorImpl {
	
	private MapCreator mapCreator;
	
	private ConfigurationManager configurationManager;
	
	private Scanner sc;
	
	private ByteArrayInputStream auto;
	
	private String mapName = "testmap.csv";
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	
	public void manualFeedSetUp(){
		sc = new Scanner(System.in);
	}
	
	public void autoFeedSetUp(String message){
		auto = new ByteArrayInputStream(message.getBytes());
		System.setIn(auto);
		sc = new Scanner(System.in);
	}
	
	public void autoFeedSetUpFile(String filename){
		try {
			sc = new Scanner(new FileReader(filename));
		} catch (FileNotFoundException ex){
			System.out.println("File " + filename + " does not exist");
		} catch (IOException ex){
			ex.printStackTrace();
		}	
	}
	
	
	@Before
	public void setUp() {
		configurationManager = new ConfigurationManager
				(new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user11\\config11"));
		configurationManager.capture("config11");
		//setUpStreams();	
	}
	
	
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
//These are the test for createMap()

	/*
	* Testing createMap with valid inputs
	*/
	//@Ignore
	@Test
	public void testcreateMapValidInputs() {
		 autoFeedSetUpFile("testmapcreatorimpl1.txt");
		 mapCreator = new MapCreatorImpl();
		 assertEquals(true,mapCreator.createMap(configurationManager, sc,mapName));
		 String tempString = configurationManager.getFile().getAbsolutePath()+"\\"+mapName;
		 File tempFile = new File(tempString);
		 assertEquals(true,tempFile.exists());
		 tempFile.delete();
	}
	
	/*
	* Testing createMap with invalid inputs Null configuration manager
	*/
	//@Ignore
	@Test
	public void testcreateMapInValidInputNullConfiguration() {
		 autoFeedSetUpFile("testmapcreatorimpl1.txt");
		 mapCreator = new MapCreatorImpl();
		 assertEquals(false,mapCreator.createMap(null, sc,mapName));
	}
	
	/*
	* Testing createMap with invalid inputs Null name
	*/
	//@Ignore
	@Test
	public void testcreateMapInValidInputNullName() {
		 String tempname = null;
		 autoFeedSetUpFile("testmapcreatorimpl1.txt");
		 mapCreator = new MapCreatorImpl();
		 assertEquals(false,mapCreator.createMap(configurationManager,sc,tempname));
	}
	
	
	
	/*
	* Testing createMap with invalid inputs Null scanner
	*/
	//@Ignore
	@Test
	public void testcreateMapInValidInputNullScanner() {
		autoFeedSetUpFile("testmapcreatorimpl1.txt");
		 mapCreator = new MapCreatorImpl();
		 assertEquals(false,mapCreator.createMap(configurationManager, null,mapName));	 
	}
	
	/*
	* Testing createMap with valid inputs
	*/
	//@Ignore
	@Test
	public void testcreateMapInValidInputsAnswers() {
		 autoFeedSetUpFile("testmapcreatorimpl2.txt");
		 mapCreator = new MapCreatorImpl();
		 assertEquals(false,mapCreator.createMap(configurationManager, sc,mapName));
		 
	}
	
	
}
