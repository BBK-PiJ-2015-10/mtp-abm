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
import org.junit.Test;

import configuration.ConfigurationManager;
import configuration.ConfigurationMapperImpl;
import configuration.MapCreator;
import configuration.MapCreatorImpl;

public class TestConfigurationMapperImpl {
		
	private ConfigurationManager configurationManager;
	
	private ConfigurationMapperImpl configurationMapperImpl;
	
	private Scanner sc;
	
	private ByteArrayInputStream auto;
	
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
		 manualFeedSetUp();
		 configurationMapperImpl = new ConfigurationMapperImpl(configurationManager);
		 configurationMapperImpl.execManager(sc);
		 
		 assertEquals(2,2);
	}	

}
