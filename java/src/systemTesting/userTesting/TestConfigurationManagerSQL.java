package systemTesting.userTesting;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;


import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import configuration.ConfigurationManagerSQL;
import user.UserSpace;
import user.UserSpaceMaker;
import user.UserSpaceMakerImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;



public class TestConfigurationManagerSQL {
	
	private String validAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user10SQL\\config10";
	
	private File validFile = new File(validAddress);
	
	private ConfigurationManagerSQL validConfigMgr;
	
	private String invalidAddress = "bad stuff";
	
	private File invalidFile = new File(invalidAddress);
	
	private File empty;
	
	private ConfigurationManagerSQL invalidConfigMgr;
	
	private Connection validConnection;
	
	private Scanner sc;
	
	private ByteArrayInputStream auto;
	
	//private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	//private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	
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
		
	public void manualFeedSetUp(){
		sc = new Scanner(System.in);
	}
	

	//@Before
	public void setUpStreams() {
	    //System.setOut(new PrintStream(outContent));
	    //System.setErr(new PrintStream(errContent));
	}

	//@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}	
	
	
	

	
//////////////////////////////////////////////////////////////////////////////////////
	
	//These are the test for the constructor and getFile() method.
	
	/*
	 * Testing constructor with a valid file
	 */
	//@Ignore
	@Test
	public void testConstructorWithValidFile(){
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		assertEquals(validFile,validConfigMgr.getFile());
	}
	
	
	/*
	 * Testing constructor with a null file
	 */
	//@Ignore
	@Test
	public void testConstructorWithNullFile(){
		validConfigMgr=new ConfigurationManagerSQL(null);
		assertEquals(null,validConfigMgr.getFile());
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////
	
	//These are the test for the setFile(File file)
	
	/*
	 * At creation null, but then setting to a validFile
	 */
	//@Ignore
	@Test
	public void testsetFilePartI(){
		validConfigMgr=new ConfigurationManagerSQL(null);
		validConfigMgr.setFile(validFile);
		assertEquals(validFile,validConfigMgr.getFile());
	}
	
	/*
	 * At creation a validFile, but then setting it to null.
	 */
	//@Ignore
	@Test
	public void testsetFilePartII(){
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.setFile(null);
		assertNotEquals(validFile,validConfigMgr.getFile());
	}	
	
	
/////////////////////////////////////////////////////////////////////////////////////////
	
	//These are the test for loadFilesMap() and getBPAFilesMap()
	
	/*
	 * Testing loading a set of valid file maps and testing not empty
	 */
	//@Ignore
	@Test
	public void testloadFilesMapPartI(){
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		assertEquals(false,validConfigMgr.getBPAFilesMap().isEmpty());
	}
	
	/*
	 * Testing loading a set of valid file maps and for size
	 */
	//@Ignore
	@Test
	public void testloadFilesMapPartII(){
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		//validConfigMgr.setGLFile("gl.SQL");
		assertEquals(2,validConfigMgr.getBPAFilesMap().size());
	}
	
	/*
	 * Testing getting an empty BPAFilesMap
	 */
	//@Ignore
	@Test
	public void testgetPBAFilesMapEmpty(){
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		assertEquals(true,validConfigMgr.getBPAFilesMap().isEmpty());
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////
	
	//These are the tests for captureGlConnectionSettings and getGLConnectionSettings
	// and getGLFileName


	/*
	* Testing capturing a validGLConnection. Testing for return value of method as well
	* of content inside the connectionSetting data structure.
	*/
	//@Ignore
	@Test
	public void testCaptureGLConnectionSettingsValid(){
		autoFeedSetUpFile("testconfigurationManagerSQL1.txt");
	    validConfigMgr=new ConfigurationManagerSQL(validFile);
	    assertEquals(true,validConfigMgr.captureGLConnectionSettings(sc));
	    assertEquals(4,validConfigMgr.getGLConnectionSettings().size());
	    assertEquals("jdbc:mysql://LocalHost:3306/abc",validConfigMgr.getGLConnectionSettings().get(0));
	    assertEquals("root",validConfigMgr.getGLConnectionSettings().get(1));
	    assertEquals("tonto",validConfigMgr.getGLConnectionSettings().get(2));
	    assertEquals("smallgl",validConfigMgr.getGLConnectionSettings().get(3));
	    assertEquals("smallgl",validConfigMgr.getGLFileName());
	}
	
	/*
	* Testing capturing a InvalidGLConnection. Invalid table name, testing for 
	* return type of methods and ensuring that the connectionSetting data structure
	* is empty
	*/
	//@Ignore
	@Test
	public void testCaptureGLConnectionSettingsInValid(){
		autoFeedSetUpFile("testconfigurationManagerSQL2.txt");
	    validConfigMgr=new ConfigurationManagerSQL(validFile);
	    assertEquals(false,validConfigMgr.captureGLConnectionSettings(sc));
	    assertEquals(0,validConfigMgr.getGLConnectionSettings().size());
	    assertEquals(null,validConfigMgr.getGLFileName());
	}    
	
	
	/*
	* Testing capturing a InvalidValidGLConnection. Invalid table name, then corrected.
	* Testing for return value of method as well of content inside the connectionSetting
	* data structure.
	*/
	//@Ignore
	@Test
	public void testCaptureGLConnectionSettingsInValidValidTable(){
		autoFeedSetUpFile("testconfigurationManagerSQL3.txt");
	    validConfigMgr=new ConfigurationManagerSQL(validFile);
	    assertEquals(true,validConfigMgr.captureGLConnectionSettings(sc));
	    assertEquals(4,validConfigMgr.getGLConnectionSettings().size());
	    assertEquals("jdbc:mysql://LocalHost:3306/abc",validConfigMgr.getGLConnectionSettings().get(0));
	    assertEquals("root",validConfigMgr.getGLConnectionSettings().get(1));
	    assertEquals("tonto",validConfigMgr.getGLConnectionSettings().get(2));
	    assertEquals("smallgl",validConfigMgr.getGLConnectionSettings().get(3));
	    assertEquals("smallgl",validConfigMgr.getGLFileName());
	    
	}
	
	/*
	* Testing capturing a InvalidValidGLConnection. Invalid url, then corrected.
	* Testing for return value of method as well of content inside the connectionSetting
	* data structure.
	*/
	//@Ignore
	@Test
	public void testCaptureGLConnectionSettingsInValidValidURL(){
		autoFeedSetUpFile("testconfigurationManagerSQL4.txt");
	    validConfigMgr=new ConfigurationManagerSQL(validFile);
	    assertEquals(true,validConfigMgr.captureGLConnectionSettings(sc));
	    assertEquals(4,validConfigMgr.getGLConnectionSettings().size());
	    assertEquals("jdbc:mysql://LocalHost:3306/abc",validConfigMgr.getGLConnectionSettings().get(0));
	    assertEquals("root",validConfigMgr.getGLConnectionSettings().get(1));
	    assertEquals("tonto",validConfigMgr.getGLConnectionSettings().get(2));
	    assertEquals("smallgl",validConfigMgr.getGLConnectionSettings().get(3));
	    assertEquals("smallgl",validConfigMgr.getGLFileName());
	}
	
	/*
	* Testing capturing a InvalidValidGLConnection. Invalid username and table, then corrected.
	* Testing for return value of method as well of content inside the connectionSetting
	* data structure.
	*/
	//@Ignore
	@Test
	public void testCaptureGLConnectionSettingsInValidValidUsernameTable(){
		autoFeedSetUpFile("testconfigurationManagerSQL5.txt");
	    validConfigMgr=new ConfigurationManagerSQL(validFile);
	    assertEquals(true,validConfigMgr.captureGLConnectionSettings(sc));
	    assertEquals(4,validConfigMgr.getGLConnectionSettings().size());
	    assertEquals("jdbc:mysql://LocalHost:3306/abc",validConfigMgr.getGLConnectionSettings().get(0));
	    assertEquals("root",validConfigMgr.getGLConnectionSettings().get(1));
	    assertEquals("tonto",validConfigMgr.getGLConnectionSettings().get(2));
	    assertEquals("smallgl",validConfigMgr.getGLConnectionSettings().get(3));
	    assertEquals("smallgl",validConfigMgr.getGLFileName());
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////////
	
	
	//These are the tests for testConnection and testDatabase
	
	/*
	* Testing testConnnection and testTable with valid inputs
	*/
	//@Ignore
	@Test
	public void testConnectionTableValid(){
		autoFeedSetUpFile("testconfigurationManagerSQL1.txt");
	    validConfigMgr=new ConfigurationManagerSQL(validFile);
	    validConfigMgr.captureGLConnectionSettings(sc);
	    assertEquals(true,validConfigMgr.testConnection());
	    assertEquals(true,validConfigMgr.testTable("smallgl"));
	    assertEquals(false,validConfigMgr.testTable("wronggl"));
	}
	
	/*
	* Testing testConnnection and testDatabase with invalid inputs
	*/
	//@Ignore
	@Test
	public void testConnectionTableInValid(){
		autoFeedSetUpFile("testconfigurationManagerSQL2.txt");
	    validConfigMgr=new ConfigurationManagerSQL(validFile);
	    validConfigMgr.captureGLConnectionSettings(sc);
	    assertEquals(false,validConfigMgr.testConnection());
	    assertEquals(false,validConfigMgr.testTable("smallgl"));
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////////
	
	
	//These are the tests for getConnection

	/*
	* Testing getConnection with valid inputs. Testing if the connection returned is valid.
	*/
	//@Ignore
	@Test
	public void testgetGLConnectionValid(){
		autoFeedSetUpFile("testconfigurationManagerSQL1.txt");
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.captureGLConnectionSettings(sc);
		try {
			assertEquals(true,validConfigMgr.getGLConnection().isValid(5));
		} catch (SQLException ex){
			ex.printStackTrace();
		}
	}

	/*
	* Testing getConnection with invalid inputs. Testing if the connection returned is invalid.
	*/
	//@Ignore
	@Test (expected = NullPointerException.class)
	public void testgetGLConnectionInValid(){
		autoFeedSetUpFile("testconfigurationManagerSQL2.txt");
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.captureGLConnectionSettings(sc);
		try {
			assertEquals(false,validConfigMgr.getGLConnection().isValid(5));
		} catch (SQLException ex){
			ex.printStackTrace();
		}
	}
	
		
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//These are the test for grabFilesAttributes(File file, Map map) and getBpaFilesAttributesMap()
	
	/*
	 * Testing a valid grabBPAFilesAttributes
	 */
	//@Ignore
	@Test
	public void testgrabFilesAttributesvalid() {
		File temp = new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user10\\config10\\phones.csv");
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFileAttributes(temp, validConfigMgr.getBpaFilesAttributesMap());
		assertEquals(false,validConfigMgr.getBpaFilesAttributesMap().isEmpty());
	}
	
	
	/*
	 * Testing a valid grabBPAFilesAttributes passing an empty file
	 */
	//@Ignore
	@Test (expected = NullPointerException.class)
	public void grabFilesAttributesvalidEmptyFile() {
		File temp = null;
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFileAttributes(null, validConfigMgr.getBpaFilesAttributesMap());
	}
	
	/*
	 * Testing a valid grabBPAFilesAttributes passing an empty file
	 */
	//@Ignore
	@Test (expected = NullPointerException.class)
	public void grabFilesAttributesvalidEmptyMap() {
		validConfigMgr.grabFileAttributes(null, null);
	}
	
	/*
	 * Testing that when not loaded you get an empty file back
	 */
	//@Ignore
	@Test
	public void getBpaFilesAttributesMapEmpty() {
		File temp = new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user3\\config3\\phones.csv");
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		assertEquals(true,validConfigMgr.getBpaFilesAttributesMap().isEmpty());
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	//Tests for grabFilesAttributes()
	
	/*
	 * Testing that is grabs BPA file
	 */
	//@Ignore
	@Test
	public void testgrabFilesAttributesValidBPA() {
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFilesAttributes();
		assertEquals(false,validConfigMgr.getBpaFilesAttributesMap().isEmpty());
	}
	
	/*
	 * Testing that is grabs BPA file
	 */
	//@Ignore
	@Test
	public void testgrabFilesAttributesValidGL() {
		autoFeedSetUpFile("testconfigurationManagerSQL1.txt");
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.captureGLConnectionSettings(sc);
		validConfigMgr.grabFilesAttributes();
		assertEquals(false,validConfigMgr.getGlFilesAttributesMap().isEmpty());
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	//Tests for getGlFilesAttributesMap();

	/*
	 * Testing that missing GL returns an empty file.
	 */
	//@Ignore
	@Test
	public void testgetGlFilesAttributesMapEmpty() {
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFilesAttributes();
		assertEquals(true,validConfigMgr.getGlFilesAttributesMap().isEmpty());
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Tests for readEntry(String input, String message,List<String> accumulator)
	
	/*
	 * Testing that missing GL returns an empty file.
	 */
	//@Ignore
	@Test
	public void testReadEntryValidSelection() {
		autoFeedSetUp("pclient");
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFilesAttributes();
		assertEquals(true,validConfigMgr.readEntry("phones.csv","Select one of the below options", accum,validConfigMgr.getBpaFilesAttributesMap(),1,sc));
	}
	
	/*
	 * Testing readEntry and asking to make a wrong selection
	 */
	//@Ignore
	@Test
	public void testReadEntryInValidSelection() {
		autoFeedSetUp("invalid");
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFilesAttributes();
		assertEquals(false,validConfigMgr.readEntry("phones.csv","Type something else", accum,validConfigMgr.getBpaFilesAttributesMap(),1,sc));
	}
	
	/*
	 * Testing readEntry with a null input file.
	 */
	//@Ignore
	@Test
	public void testReadEntryNullfile() {
		autoFeedSetUp("something irrelevant");
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFilesAttributes();
		assertEquals(false,validConfigMgr.readEntry(null,"type something",accum,validConfigMgr.getBpaFilesAttributesMap(),1,sc));
	}
	
	/*
	 * Testing readEntry with a null accumulator List
	 */
	//@Ignore
	@Test 
	public void testReadEntryNullAccumulator() {
		autoFeedSetUp("pclient");
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFilesAttributes();
		assertEquals(false,validConfigMgr.readEntry("phones.csv","type one of the below",null,validConfigMgr.getBpaFilesAttributesMap(),1,sc));
	}
	
	/*
	 * Testing readEntry with a null map
	 */
	//@Ignore
	@Test(expected = NullPointerException.class)
	public void testReadEntryNullMap() {
		autoFeedSetUp("pclient");
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFilesAttributes();
		assertEquals(false,validConfigMgr.readEntry("phones.csv","ignore this sentence", accum,null,1,sc));
	}
	
	/*
	 * Testing readEntry and asking user to enter 2 entries instead of one.
	 */
	//@Ignore
	@Test
	public void testReadEntryCounter() {
		autoFeedSetUp("pclient pcalls");
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFilesAttributes();
		assertEquals(false,validConfigMgr.readEntry("phones.csv","select two valid entries", accum,validConfigMgr.getBpaFilesAttributesMap(),1,sc));
	}
	
	
	/*
	 * Testing readEntry and setting maxEntry to 0
	 */
	//@Ignore
	@Test
	public void testReadEntryZeroCounter() {
		autoFeedSetUp("pcalls");
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFilesAttributes();
		assertEquals(false,validConfigMgr.readEntry("phones.csv","select a valid entry", accum,validConfigMgr.getBpaFilesAttributesMap(),0,sc));
	}
	
	/*
	 * Testing readEntry and setting maxEntry to 0
	 */
	//@Ignore
	@Test
	public void testReadEntryValidMultipleArguments() {
		autoFeedSetUp("pclient pcalls");
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFilesAttributes();
		assertEquals(true,validConfigMgr.readEntry("phones.csv","select two valid entries", accum,validConfigMgr.getBpaFilesAttributesMap(),2,sc));
	}
	
	
	/*
	 * Testing readEntry and requesting to enter one invalid and one valid entry.
	 */
	//@Ignore
	@Test
	public void testReadEntryValidMultipleArgumentsOneValidOneInvalid() {
		autoFeedSetUp("biruta pclient");
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFilesAttributes();
		assertEquals(false,validConfigMgr.readEntry("phones.ale","select one invalid and one valid", accum,validConfigMgr.getBpaFilesAttributesMap(),2,sc));
	}
	
	/*
	 * Testing readEntry and requesting to enter one invalid and one valid entry.
	 */
	//@Ignore
	@Test
	public void testReadEntryValidMultipleArgumentsOneInValidOneValid() {
		autoFeedSetUp("pclient biruta");
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFilesAttributes();
		assertEquals(false,validConfigMgr.readEntry("phones.csv","select one valid and one invalid", accum,validConfigMgr.getBpaFilesAttributesMap(),2,sc));
	}
	
	/*
	 * Testing readEntry and requesting to input two invalid entries.
	 */
	//@Ignore
	@Test
	public void testReadEntryValidMultipleArgumentsTwoInValid() {
		autoFeedSetUp("ale biruta");
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFilesAttributes();
		assertEquals(false,validConfigMgr.readEntry("phones.csv","select two invalid", accum,validConfigMgr.getBpaFilesAttributesMap(),2,sc));
	}
	
	
	


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Tests for loadBpafilesMainAttributes() and getBpaMainFilesAttributesMap()

	/*
 	* Testing if it returns an empty map.
	*/
	//@Ignore
	@Test
	public void testgetBpaMainFilesAttributesMapEmpty() {	
		autoFeedSetUp("");
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFilesAttributes();
		assertEquals(true,validConfigMgr.getBpaMainFilesAttributesMap().isEmpty());
	}
	
	/*
 	* Testing that it returns a populated map
	*/
	//@Ignore
	@Test
	public void testLoadBpafilesMainAttributesValid() {	
		autoFeedSetUpFile("testconfigmgr1.txt");
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFilesAttributes();
		validConfigMgr.loadBpaFilesMainAttributes(sc);
		assertEquals(false,validConfigMgr.getBpaMainFilesAttributesMap().isEmpty());
	}
		
	
	/*
 	* Testing that it returns a populated map and test its size.
	*/
	//@Ignore
	@Test
	public void testLoadBpafilesMainAttributesValidSize () {		
		autoFeedSetUpFile("testconfigmgr1.txt");
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFilesAttributes();
		validConfigMgr.loadBpaFilesMainAttributes(sc);
		assertEquals(2,validConfigMgr.getBpaMainFilesAttributesMap().size());
	}
	
	/*
 	* Testing that it returns a populated map and test the size of one element.
	*/
	//@Ignore
	@Test
	public void testLoadBpafilesMainAttributesValidElementSize () {	
		autoFeedSetUpFile("testconfigmgr1.txt");
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFilesAttributes();
		validConfigMgr.loadBpaFilesMainAttributes(sc);
		assertEquals(2,validConfigMgr.getBpaMainFilesAttributesMap().get("phones.csv").size());
	}	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Tests for loadGlfilesMainAttributes() and getGlMainFilesAttributesMap()
	
	
	/*
 	* Testing that it returns a populated map and test the size.
	*/
	//@Ignore
	@Test
	public void testLoadGlfilesMainAttributesValidElementSize () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		autoFeedSetUpFile("testconfigurationManagerSQL1.txt");
		validConfigMgr.captureGLConnectionSettings(sc);
		autoFeedSetUpFile("testconfigmgr2.txt");
		validConfigMgr.grabFilesAttributes();
		System.out.println("Please select exactly 3 attributes in total ");
		validConfigMgr.loadGlFilesMainAttributes(sc);	
		assertEquals(4,validConfigMgr.getGlMainFilesAttributesMap().get("smallgl").size());
	}
	
	/*
 	* Testing that if not loaded, it will return a null value.
	*/
	//@Ignore
	@Test
	public void testGlMainFilesAttributesMapNullFile () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFilesAttributes();	
		assertEquals(null,validConfigMgr.getGlMainFilesAttributesMap().get("smallgl"));
	}	
	

	
//////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Tests for grabSQLTableAttributes
	
	
	/*
 	* Testing with a valid set of inputs. Testing for method return and content
 	* of mapped passed as an argument.
	*/
	//@Ignore
	@Test
	public void testgrabSQLTableAttributesValid () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		Map<String,Set<String>> temp = new HashMap<>();
		validConfigMgr.capture("config10");
		assertEquals(true,validConfigMgr.grabSQLTableAttributes("smallgl",temp));
		assertEquals(4,temp.get("smallgl").size());
		assertEquals(true,temp.get("smallgl").contains("Account"));
		assertEquals(true,temp.get("smallgl").contains("Department"));
		assertEquals(true,temp.get("smallgl").contains("Amount"));
		assertEquals(true,temp.get("smallgl").contains("Legal_Entity"));
	}
	
	/*
 	* Testing with an in valid set of inputs. Testing for method return
	*/
	//@Ignore
	@Test (expected = NullPointerException.class)
	public void testgrabSQLTableAttributesInValid () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		Map<String,Set<String>> temp = new HashMap<>();
		validConfigMgr.capture("config11");
		validConfigMgr.grabSQLTableAttributes("smallgl",temp);
	}
	
	/*
 	* Testing with an in valid set of inputs. Testing for method return and content
 	* of mapped passed as an argument.
	*/
	//@Ignore
	@Test 
	public void testgrabSQLTableAttributesInValidTestingTemp () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		Map<String,Set<String>> temp = new HashMap<>();
		validConfigMgr.capture("config11");
		try {
			validConfigMgr.grabSQLTableAttributes("smallgl",temp);
		} catch (NullPointerException ex){
		    System.out.println("Null Pointer exception caught");	
		}
		assertEquals(true,temp.isEmpty());
	}
	
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Tests for grabGL()


	/*
	* Testing with a valid set of inputs. Testing that the GL attributes map content.
	*/
	//@Ignore
	@Test
	public void testgrabglValidInput () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		autoFeedSetUpFile("testConfigurationManagerSQL1.txt");
		validConfigMgr.captureGLConnectionSettings(sc);
		assertEquals(true,validConfigMgr.grabGL());
		assertEquals(4,validConfigMgr.getGlFilesAttributesMap().get("smallgl").size());
		assertEquals(true,validConfigMgr.getGlFilesAttributesMap().get("smallgl").contains("Account"));
		assertEquals(true,validConfigMgr.getGlFilesAttributesMap().get("smallgl").contains("Department"));
		assertEquals(true,validConfigMgr.getGlFilesAttributesMap().get("smallgl").contains("Amount"));
		assertEquals(true,validConfigMgr.getGlFilesAttributesMap().get("smallgl").contains("Legal_Entity"));
	}	
	
	
	/*
	* Testing with an invalid set of inputs. Testing that the GL attributes map content.
	*/
	////@Ignore
	@Test
	public void testgrabglInValidInput () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		autoFeedSetUpFile("testConfigurationManagerSQL2.txt");
		validConfigMgr.captureGLConnectionSettings(sc);
		assertEquals(false,validConfigMgr.grabGL());
		assertEquals(true,validConfigMgr.getGlFilesAttributesMap().isEmpty());
	}	
	

//////////////////////////////////////////////////////////////////////////////////////////////////
	
      //Tests for capture(String configurationname)
	
	/*
 	* Testing that method return true if input is a file that exists.
	*/
	//@Ignore
	@Test
	public void testcaptureValidInput () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		assertEquals(true,validConfigMgr.capture("config10"));
	}
	
	/*
 	* Testing that method return false if name provided doesn't really exist in file.
	*/
	//@Ignore
	@Test
	public void testcaptureInValidInputName () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		assertEquals(false,validConfigMgr.capture("config33"));
	}
	
	/*
 	* Testing that method return false if the file doesn't really exist.
	*/
	//@Ignore
	@Test
	public void testcaptureInValidInputNonExistentDocument () {	
		invalidConfigMgr=new ConfigurationManagerSQL(invalidFile);
		assertEquals(false,invalidConfigMgr.capture("bad stuff"));
	}	
	
	
	/*
 	* Testing that file returned element name is config3
	*/
	//@Ignore
	@Test
	public void testcaptureValidFile() {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		assertEquals("config10",validConfigMgr.getFile().getName());
	}
	
	/*
 	* Testing that glfile returned element name is gl.SQL
	*/
	//@Ignore
	@Test
	public void testcaptureValidGLFile () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		assertEquals("smallgl",validConfigMgr.getGLFileName());
	}

	/*
 	* Testing that bpaFilesMap returned element is of size 2
	*/
	//@Ignore
	@Test
	public void testcaptureValidbpaFilesMap () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		assertEquals(2,validConfigMgr.getBPAFilesMap().size());
	}	
	
	
	/*
 	* Testing that bpaFilesAttributesMap returned element is of size 4
 	*/ 
	//@Ignore
	@Test
	public void testcaptureValidbpaFilesAttributesMap () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		assertEquals(4,validConfigMgr.getBpaFilesAttributesMap().get("phones.csv").size());
	}
	
	/*
 	* Testing that bpaFilesAttributesMap returned element is of size 4
	*/
	//@Ignore
	@Test
	public void testcaptureValidbpaFilesMainAttributesMap () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		assertEquals(2,validConfigMgr.getBpaMainFilesAttributesMap().get("phones.csv").size());
	}
	
	/*
 	* Testing that GlFilesAttributesMap returned element is of size 4
	*/
	//@Ignore
	@Test
	public void testcaptureValidGlFilesAttributesMap () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		assertEquals(4,validConfigMgr.getGlFilesAttributesMap().get("smallgl").size());
	}
	
	/*
 	* Testing that GlFilesAttributesMap returned element is of size 4
	*/
	//@Ignore
	@Test
	public void testcaptureValidGlMainFilesAttributesMap () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		assertEquals(3,validConfigMgr.getGlMainFilesAttributesMap().get("smallgl").size());
	}
	
	/*
 	* Testing that glConnectionSettings size returned element is of size 4
	*/
	//@Ignore
	@Test
	public void testcaptureValidGLConnectionSettings () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		assertEquals(4,validConfigMgr.getGLConnectionSettings().size());
	}
	
	/*
 	* Testing that the contents on the glConnectionSettings captured are correct
	*/
	//@Ignore
	@Test
	public void testcaptureValidGLConnectionSettingsContent () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		assertEquals(4,validConfigMgr.getGLConnectionSettings().size());
		assertEquals("jdbc:mysql://LocalHost:3306/abc",validConfigMgr.getGLConnectionSettings().get(0));
		assertEquals("root",validConfigMgr.getGLConnectionSettings().get(1));
		assertEquals("tonto",validConfigMgr.getGLConnectionSettings().get(2));
		assertEquals("smallgl",validConfigMgr.getGLConnectionSettings().get(3));
		
	}
	
	

///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
    //Tests for save(String newname)
	
	/*
	* Saving a file with a different name, then retrieving and testing its existence
	*/
	//@Ignore
	@Test
	public void testsaveValidExistance () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		validConfigMgr.save("newone");
		assertEquals(true,Arrays.asList(validConfigMgr.getFile().list()).contains("newone.dat"));
		String tempaddress = validConfigMgr.getFile().getAbsolutePath()+"\\"+"newone.dat";
		File temp = new File(tempaddress);
		temp.delete();		
	}	
	
	/*
	* Saving a file with a different name, then retrieving and testing its contents
	*/
	//@Ignore
	@Test
	public void testsaveValidContentsI () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		validConfigMgr.save("newone");
		ConfigurationManagerSQL tempconfig=new ConfigurationManagerSQL(validFile);
		tempconfig.capture("newone");
		assertEquals("smallgl",tempconfig.getGLFileName());
		String tempaddress = validConfigMgr.getFile().getAbsolutePath()+"\\"+"newone.dat";
		File temp = new File(tempaddress);
		temp.delete();		
	}
	
	/*
	* Saving a file with a different name, then retrieving and testing its contents
	*/
	//@Ignore
	@Test
	public void testsaveValidContentsII () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		validConfigMgr.save("newone");
		ConfigurationManagerSQL tempconfig=new ConfigurationManagerSQL(validFile);
		tempconfig.capture("newone");
		assertEquals(2,validConfigMgr.getBPAFilesMap().size());
		String tempaddress = validConfigMgr.getFile().getAbsolutePath()+"\\"+"newone.dat";
		File temp = new File(tempaddress);
		temp.delete();		
	}
	
	/*
	* Saving a file with a different name, then retrieving and testing its contents
	*/
	//@Ignore
	@Test
	public void testsaveValidContentsIII () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		validConfigMgr.save("newone");
		ConfigurationManagerSQL tempconfig=new ConfigurationManagerSQL(validFile);
		tempconfig.capture("newone");
		assertEquals(4,validConfigMgr.getBpaFilesAttributesMap().get("phones.csv").size());
		String tempaddress = validConfigMgr.getFile().getAbsolutePath()+"\\"+"newone.dat";
		File temp = new File(tempaddress);
		temp.delete();		
	}
	
	/*
	* Saving a file with a different name, then retrieving and testing its contents
	*/
	//@Ignore
	@Test
	public void testsaveValidContentsIV () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		validConfigMgr.save("newone");
		ConfigurationManagerSQL tempconfig=new ConfigurationManagerSQL(validFile);
		tempconfig.capture("newone");
		assertEquals(2,validConfigMgr.getBpaMainFilesAttributesMap().get("phones.csv").size());
		String tempaddress = validConfigMgr.getFile().getAbsolutePath()+"\\"+"newone.dat";
		File temp = new File(tempaddress);
		temp.delete();		
	}
	
	/*
	* Saving a file with a different name, then retrieving and testing its contents
	*/
	//@Ignore
	@Test
	public void testsaveValidContentsV () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		validConfigMgr.save("newone");
		ConfigurationManagerSQL tempconfig=new ConfigurationManagerSQL(validFile);
		tempconfig.capture("newone");
		assertEquals(4,validConfigMgr.getGlFilesAttributesMap().get("smallgl").size());
		String tempaddress = validConfigMgr.getFile().getAbsolutePath()+"\\"+"newone.dat";
		File temp = new File(tempaddress);
		temp.delete();		
	}
	
	/*
	* Saving a file with a different name, then retrieving and testing its contents
	*/
	//@Ignore
	@Test
	public void testsaveValidContentsVI () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		validConfigMgr.save("newone");
		ConfigurationManagerSQL tempconfig=new ConfigurationManagerSQL(validFile);
		tempconfig.capture("newone");
		assertEquals(3,validConfigMgr.getGlMainFilesAttributesMap().get("smallgl").size());
		String tempaddress = validConfigMgr.getFile().getAbsolutePath()+"\\"+"newone.dat";
		File temp = new File(tempaddress);
		temp.delete();		
	}
	

	
}
