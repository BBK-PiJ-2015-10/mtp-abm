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
import configuration.ConfigurationMapperImpl;
import configuration.MapCreator;
import configuration.MapCreatorImpl;

import java.util.NoSuchElementException;


public class TestConfigurationMapperImpl {
		
	private ConfigurationManager configurationManager;
	
	private ConfigurationMapperImpl configurationMapperImpl;
	
	private String validAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user12\\config12";
	
	private File validFile = new File(validAddress);
	
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
		configurationManager = new ConfigurationManager(validFile);
		setUpStreams();	
	}
	
	
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
//These are the test for execManager()

	/*
	* Testing execManager() with valid inputs
	*/
	//@Ignore
	@Test
	public void testexecManagerValidInput() {
		 autoFeedSetUpFile("testconfigurationmapperimpl1.txt");
		 configurationMapperImpl = new ConfigurationMapperImpl(configurationManager);
		 assertEquals(true,configurationMapperImpl.execManager(sc));
		 ConfigurationManager testCM = new ConfigurationManager(validFile);
		 assertEquals(true,testCM.capture("config12"));
		 assertEquals(2,testCM.getBpaFilesAttributesMap().keySet().size());
		 assertEquals("gl.csv",testCM.getGLFile().getName());
		 assertEquals(2,testCM.getBPAFilesMap().size());
		 assertEquals(2,testCM.getBpaMainFilesAttributesMap().size());
		 assertEquals(2,testCM.getBpaFilesAttributesMap().keySet().size());
		 String value = configurationManager.getFile().getAbsolutePath()+"\\"+"config12.dat";
		 File temp = new File(value);
		 temp.delete();
	}
	
	/*
	* Testing execManager() with invalid gl file input
	*/
	//@Ignore
	@Test
	public void testexecManagerInValidglFileInput() {
		 autoFeedSetUpFile("testconfigurationmapperimpl2.txt");
		 configurationMapperImpl = new ConfigurationMapperImpl(configurationManager);
		 assertEquals(false,configurationMapperImpl.execManager(sc));
	}
	
	
	/*
	* Testing execManager() with invalid gl accounts input
	*/
	//@Ignore
	@Test
	public void testexecManagerInValidglAccountInput() {
		 autoFeedSetUpFile("testconfigurationmapperimpl3.txt");
		 configurationMapperImpl = new ConfigurationMapperImpl(configurationManager);
		 assertEquals(false,configurationMapperImpl.execManager(sc));
	}
	
	
	/*
	* Testing execManager() with invalid gl accounts input
	*/
	//@Ignore
	@Test
	public void testexecManagerInValidbpaAccountInput() {
		 autoFeedSetUpFile("testconfigurationmapperimpl4.txt");
		 configurationMapperImpl = new ConfigurationMapperImpl(configurationManager);
		 assertEquals(false,configurationMapperImpl.execManager(sc));
	}
	
	/*
	* Testing execManager() with invalid gl file input
	*/
	//@Ignore
	@Test
	public void testexecManagerInValidInputNullConfigurationMNager() {
		 autoFeedSetUpFile("testconfigurationmapperimpl1.txt");
		 configurationMapperImpl = new ConfigurationMapperImpl(null);
		 assertEquals(false,configurationMapperImpl.execManager(sc));
	}
	
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	//Below are the tests for createMap(Scanner sc)
	
	/*
	* Testing createMap with valid input
	*/
	//@Ignore
	@Test
	public void testcreateMapValid() {
		 autoFeedSetUpFile("testconfigurationmapperimpl5.txt");
		 configurationMapperImpl = new ConfigurationMapperImpl(configurationManager);
		 configurationMapperImpl.execManager(sc);
		 assertEquals(true,configurationMapperImpl.createMap(sc));
		 String tempA = configurationManager.getFile().getAbsolutePath()+"\\"+"glbpamap.csv";
		 File tempF = new File(tempA);
		 assertEquals(true, tempF.exists());
		 tempF.delete();
		 String value = configurationManager.getFile().getAbsolutePath()+"\\"+"config12.dat";
		 File tempCF = new File(value);
		 tempCF.delete();
	}
	
	/*
	* Testing createMap with null scanner
	*/
	//@Ignore
	@Test
	public void testcreateMapNullScanner() {
		 autoFeedSetUpFile("testconfigurationmapperimpl5.txt");
		 configurationMapperImpl = new ConfigurationMapperImpl(configurationManager);
		 configurationMapperImpl.execManager(sc);
		 assertEquals(false,configurationMapperImpl.createMap(null));
		 String tempA = configurationManager.getFile().getAbsolutePath()+"\\"+"glbpamap.csv";
		 File tempF = new File(tempA);
		 assertEquals(false, tempF.exists());
		 String value = configurationManager.getFile().getAbsolutePath()+"\\"+"config12.dat";
		 File tempCF = new File(value);
		 tempCF.delete();
	}
	
	/*
	* Testing createMap with no data scanner
	*/
	//@Ignore
	@Test
	public void testcreateMapNoDataScanner() {
		 autoFeedSetUpFile("testconfigurationmapperimpl1.txt");
		 configurationMapperImpl = new ConfigurationMapperImpl(configurationManager);
		 configurationMapperImpl.execManager(sc);
		 assertEquals(false,configurationMapperImpl.createMap(sc));
		 String tempA = configurationManager.getFile().getAbsolutePath()+"\\"+"glbpamap.csv";
		 File tempF = new File(tempA);
		 assertEquals(false, tempF.exists());
	}
	
	/*
	* Testing createMap with incomplete data scanner
	*/
	//@Ignore
	@Test
	public void testcreateMapIncompleDataScanner() {
		 autoFeedSetUpFile("testconfigurationmapperimpl7.txt");
		 configurationMapperImpl = new ConfigurationMapperImpl(configurationManager);
		 configurationMapperImpl.execManager(sc);
		 assertEquals(false,configurationMapperImpl.createMap(sc));
		 String tempA = configurationManager.getFile().getAbsolutePath()+"\\"+"glbpamap.csv";
		 File tempF = new File(tempA);
		 assertEquals(false, tempF.exists());
	}
	
	/*
	* Testing createMap with incomplete data scanner line skipped
	*/
	//@Ignore
	@Test
	public void testcreateMapIncompleDataScannerLineSkipped() {
		 autoFeedSetUpFile("testconfigurationmapperimpl8.txt");
		 configurationMapperImpl = new ConfigurationMapperImpl(configurationManager);
		 configurationMapperImpl.execManager(sc);
		 assertEquals(false,configurationMapperImpl.createMap(sc));
		 String tempA = configurationManager.getFile().getAbsolutePath()+"\\"+"glbpamap.csv";
		 File tempF = new File(tempA);
		 assertEquals(false, tempF.exists());
	}
	
	/*
	* Testing createMap with null ConfigurationManager
	*/
	//@Ignore
	@Test
	public void testcreateMapWithNullConfiguratoinManager() {
		 autoFeedSetUpFile("testconfigurationmapperimpl5.txt");
		 configurationMapperImpl = new ConfigurationMapperImpl(null);
		 configurationMapperImpl.execManager(sc);
		 assertEquals(false,configurationMapperImpl.createMap(sc));
		 String tempA = configurationManager.getFile().getAbsolutePath()+"\\"+"glbpamap.csv";
		 File tempF = new File(tempA);
		 assertEquals(false, tempF.exists());
	}
	

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
//Below are the tests for mapFiles(Scanner sc)

	/*
	* Testing mapFiles with valid input
	*/
	//@Ignore
	@Test
	public void testMapFilesValid() {
		autoFeedSetUpFile("testconfigurationmapperimpl5.txt");
		configurationMapperImpl = new ConfigurationMapperImpl(configurationManager);
		assertEquals(true,configurationMapperImpl.mapFiles(sc));
		String tempA = configurationManager.getFile().getAbsolutePath()+"\\"+"glbpamap.csv";
		File tempMF = new File(tempA);
		assertEquals(true, tempMF.exists());
		String value = configurationManager.getFile().getAbsolutePath()+"\\"+"config12.dat";
		File tempCF = new File(value);
		assertEquals(true, tempCF.exists());
		tempCF.delete();
		tempMF.delete();
	}
	
	/*
	* Testing mapFiles with invalid input, null scanner
	*/
	//@Ignore
	@Test
	public void testMapFilesNullScanner() {
		configurationMapperImpl = new ConfigurationMapperImpl(configurationManager);
		assertEquals(false,configurationMapperImpl.mapFiles(null));
		String tempA = configurationManager.getFile().getAbsolutePath()+"\\"+"glbpamap.csv";
		File tempF = new File(tempA);
		assertEquals(false, tempF.exists());
	}
	
	
	/*
	* Testing mapFiles with invalid input, incomplete scanner part I
	*/
	//@Ignore
	@Test
	public void testMapFilesIncompleteScannerpartI() {
		autoFeedSetUpFile("testconfigurationmapperimpl2.txt");
		configurationMapperImpl = new ConfigurationMapperImpl(configurationManager);
		assertEquals(false,configurationMapperImpl.mapFiles(sc));
		String tempA = configurationManager.getFile().getAbsolutePath()+"\\"+"glbpamap.csv";
		File tempF = new File(tempA);
		assertEquals(false, tempF.exists());
	}
	
	
	/*
	* Testing mapFiles with invalid input, incomplete scanner part II
	*/
	//@Ignore
	@Test
	public void testMapFilesIncompleteScannerpartII() {
		autoFeedSetUpFile("testconfigurationmapperimpl3.txt");
		configurationMapperImpl = new ConfigurationMapperImpl(configurationManager);
		assertEquals(false,configurationMapperImpl.mapFiles(sc));
		String tempA = configurationManager.getFile().getAbsolutePath()+"\\"+"glbpamap.csv";
		File tempF = new File(tempA);
		assertEquals(false, tempF.exists());
	}
	
	
	/*
	* Testing mapFiles with invalid input, incomplete scanner part II
	*/
	//@Ignore
	@Test
	public void testMapFilesIncompleteScannerpartIII() {
		autoFeedSetUpFile("testconfigurationmapperimpl6.txt");
		configurationMapperImpl = new ConfigurationMapperImpl(configurationManager);
		assertEquals(false,configurationMapperImpl.mapFiles(sc));
		String tempA = configurationManager.getFile().getAbsolutePath()+"\\"+"glbpamap.csv";
		File tempF = new File(tempA);
		assertEquals(false, tempF.exists());
		String value = configurationManager.getFile().getAbsolutePath()+"\\"+"config12.dat";
		File tempCF = new File(value);
		assertEquals(true, tempCF.exists());
		tempCF.delete();
	}
	
	/*
	* Testing mapFiles with invalid input, null ConfigurationManager
	*/
	//@Ignore
	@Test
	public void testMapFilesInValidNullConfiguration() {
		autoFeedSetUpFile("testconfigurationmapperimpl5.txt");
		configurationMapperImpl = new ConfigurationMapperImpl(null);
		assertEquals(false,configurationMapperImpl.mapFiles(sc));
		String tempA = configurationManager.getFile().getAbsolutePath()+"\\"+"glbpamap.csv";
		File tempF = new File(tempA);
		assertEquals(false, tempF.exists());
	}
	

}
