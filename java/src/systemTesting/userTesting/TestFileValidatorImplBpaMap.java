package systemTesting.userTesting;

import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import configuration.ConfigurationManagerAbstract;
import configuration.ConfigurationManagerCSV;
import configuration.ConfigurationManagerSQL;
import validator.FileValidatorImplBpaMap;
import validator.FileValidator;


public class TestFileValidatorImplBpaMap {
	
	private String validConfigFileCSVAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user15\\config15";
	
	private String validConfigFileSQLAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user15\\config15sql";
	
	private String validglbpaFileAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user15\\config15\\glbpamap.csv";
	
	private File validglbpaFile = new File(validglbpaFileAddress);
	
	private String invalidglbpaFileAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user15\\config15\\invalidglbpamap.csv";
	
	private File invalidglbpaFile = new File(invalidglbpaFileAddress);
	
	private String missingglbpaFileAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user15\\config15\\missingglbpamap.csv";
	
	private File missingglbpaFile = new File(missingglbpaFileAddress);
	
	private ConfigurationManagerAbstract validConfigCSV;
	
	private ConfigurationManagerAbstract validConfigSQL;
	
	private FileValidator fileValidator = new FileValidatorImplBpaMap();
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	
	
	public void startConfigMgrCSV(){
		validConfigCSV = new ConfigurationManagerCSV(new File(validConfigFileCSVAddress));
	}
	
	public void startConfigMgrSQL(){
		validConfigSQL = new ConfigurationManagerSQL(new File(validConfigFileSQLAddress));
	}
	
	
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	@Before
	public void initializeFactory(){
		setUpStreams();
	}
	
	
	
///////////////////////////////////////////////////////////////////////////////////////////	
	
	//Below are the tests for the validateInput method with ConfigurationManager type CSV

	/*
	* The below test if provided a valid set of inputs the method return true. In this 
	* case testing for the parameter BPA. Testing with a CSV configMgr.
	*/
	//@Ignore
	@Test
	public void testValidateInputValidICSV() {	
		startConfigMgrCSV();
		validConfigCSV.capture("config15");
		assertEquals(true,fileValidator.validateInput(validglbpaFile,"BPA",validConfigCSV));
	}
		
	
	/*
	* The below tests if passed an incorrect String argument the method returns false and
	* provides a message on screen
	*/
	//@Ignore
	@Test
	public void testValidateInputInvalidICSV() {	
		String test1 = "Sales is not a valid selection";
		startConfigMgrCSV();
		validConfigCSV.capture("config15");
		assertEquals(false,fileValidator.validateInput(validglbpaFile,"Department",validConfigCSV));
		assertEquals(test1,outContent.toString().trim());
	}
	
	/*
	* The below tests if passed a File with an invalid tuple the method returns false and
	* provides a message on screen
	*/
	//@Ignore
	@Test
	public void testValidateInputInvalidIICSV() {	
		String test1 = "invalid3 is not a valid selection";
		startConfigMgrCSV();
		validConfigCSV.capture("config15");
		assertEquals(false,fileValidator.validateInput(invalidglbpaFile,"BPA",validConfigCSV));
		assertEquals(test1,outContent.toString().trim());
	}
	
	/*
	* The below tests if passed a File with an missing tuple the method returns false and
	* provides a message on screen
	*/
	//@Ignore
	@Test
	public void testValidateInputInvalidIIICSV() {	
		String test1 = "The file contains missing data input";
		startConfigMgrCSV();
		validConfigCSV.capture("config15");
		assertEquals(false,fileValidator.validateInput(missingglbpaFile,"BPA",validConfigCSV));
		assertEquals(test1,outContent.toString().trim());
	}
	
	/*
	* The below tests if passed an invalid argument the method returns false and
	* provides a message on screen
	*/
	//@Ignore
	@Test
	public void testValidateInputInvalidIVCSV() {	
		String test1 = "The file contains missing data input";
		startConfigMgrCSV();
		validConfigCSV.capture("config15");
		assertEquals(false,fileValidator.validateInput(validglbpaFile,"anything",validConfigCSV));
		assertEquals(test1,outContent.toString().trim());
	}
	
	/*
	* The below tests if passed an invalidConfigCSV (not populated) the method returns false and
	* provides a message on screen
	*/
	//@Ignore
	@Test
	public void testValidateInputInvalidVCSV() {	
		String test1 = "implementation.csv is not a valid selection";
		startConfigMgrCSV();
		assertEquals(false,fileValidator.validateInput(validglbpaFile,"BPA",validConfigCSV));
		assertEquals(test1,outContent.toString().trim());
	}
	
	/*
	* The below tests if passed an invalidConfigCSV (not initialized) the method returns false
	*/
	//@Ignore
	@Test
	public void testValidateInputInvalidVICSV() {	
		String test1 = "The file contains missing data input";
		assertEquals(false,fileValidator.validateInput(validglbpaFile,"BPA",validConfigCSV));
		assertEquals(test1,outContent.toString().trim());
	}
	
	/*
	* The below tests if passed a non-existent file the method returns false and provides
	* message on screen
	*/
	//@Ignore
	@Test
	public void testValidateInputInvalidVIICSV() {	
		String test1 = "File provided doesn't exist";
		startConfigMgrCSV();
		validConfigCSV.capture("config15");
		assertEquals(false,fileValidator.validateInput(new File("anything"),"BPA",validConfigCSV));
		assertEquals(test1,outContent.toString().trim());	
	}
	
	/*
	* The below tests if passed a null file the method throws a NullPointerException
	*/
	//@Ignore
	@Test (expected = NullPointerException.class)
	public void testValidateInputInvalidVIIICSV() {	
		String test1 = "File provided doesn't exist";
		startConfigMgrCSV();
		validConfigCSV.capture("config15");
		fileValidator.validateInput(null,"BPA",validConfigCSV);
	}
	
	
	//Below are the tests for the validateInput method with ConfigurationManager type SQL

	/*
	* The below test if provided a valid set of inputs the method return true. In this 
	* case testing for the parameter BPA. Testing with a CSV configMgr.
	*/
	//@Ignore
	@Test
	public void testValidateInputValidISQL() {	
		startConfigMgrSQL();
		validConfigSQL.capture("config15sql");
		assertEquals(true,fileValidator.validateInput(validglbpaFile,"BPA",validConfigSQL));
	}
		
	
	/*
	* The below tests if passed an incorrect String argument the method returns false and
	* provides a message on screen
	*/
	//@Ignore
	@Test
	public void testValidateInputInvalidISQL() {	
		String test1 = "Sales is not a valid selection";
		startConfigMgrSQL();
		validConfigSQL.capture("config15sql");
		assertEquals(false,fileValidator.validateInput(validglbpaFile,"Department",validConfigSQL));
		assertEquals(test1,outContent.toString().trim());
	}
	
	/*
	* The below tests if passed a File with an invalid tuple the method returns false and
	* provides a message on screen
	*/
	//@Ignore
	@Test
	public void testValidateInputInvalidIISQL() {	
		String test1 = "invalid3 is not a valid selection";
		startConfigMgrSQL();
		validConfigSQL.capture("config15sql");
		assertEquals(false,fileValidator.validateInput(invalidglbpaFile,"BPA",validConfigSQL));
		assertEquals(test1,outContent.toString().trim());
	}
	
	/*
	* The below tests if passed a File with an missing tuple the method returns false and
	* provides a message on screen
	*/
	//@Ignore
	@Test
	public void testValidateInputInvalidIIISQL() {	
		String test1 = "The file contains missing data input";
		startConfigMgrSQL();
		validConfigSQL.capture("config15sql");
		assertEquals(false,fileValidator.validateInput(missingglbpaFile,"BPA",validConfigSQL));
		assertEquals(test1,outContent.toString().trim());
	}
	
	/*
	* The below tests if passed an invalid argument the method returns false and
	* provides a message on screen
	*/
	//@Ignore
	@Test
	public void testValidateInputInvalidIVSQL() {	
		String test1 = "The file contains missing data input";
		startConfigMgrSQL();
		validConfigSQL.capture("config15sql");
		assertEquals(false,fileValidator.validateInput(validglbpaFile,"anything",validConfigSQL));
		assertEquals(test1,outContent.toString().trim());
	}
	
	/*
	* The below tests if passed an invalidConfigCSV (not populated) the method returns false and
	* provides a message on screen
	*/
	//@Ignore
	@Test
	public void testValidateInputInvalidVSQL() {	
		String test1 = "implementation.csv is not a valid selection";
		startConfigMgrSQL();
		assertEquals(false,fileValidator.validateInput(validglbpaFile,"BPA",validConfigSQL));
		assertEquals(test1,outContent.toString().trim());
	}
	
	/*
	* The below tests if passed an invalidConfigCSV (not initialized) the method returns false
	*/
	//@Ignore
	@Test
	public void testValidateInputInvalidVISQL() {	
		String test1 = "The file contains missing data input";
		assertEquals(false,fileValidator.validateInput(validglbpaFile,"BPA",validConfigSQL));
		assertEquals(test1,outContent.toString().trim());
	}
	
	/*
	* The below tests if passed a non-existent file the method returns false and provides
	* message on screen
	*/
	//@Ignore
	@Test
	public void testValidateInputInvalidVIISQL() {	
		String test1 = "File provided doesn't exist";
		startConfigMgrSQL();
		validConfigSQL.capture("config15sql");
		assertEquals(false,fileValidator.validateInput(new File("anything"),"BPA",validConfigSQL));
		assertEquals(test1,outContent.toString().trim());	
	}
	
	/*
	* The below tests if passed a null file the method throws a NullPointerException
	*/
	//@Ignore
	@Test (expected = NullPointerException.class)
	public void testValidateInputInvalidVIIISQL() {	
		String test1 = "File provided doesn't exist";
		startConfigMgrSQL();
		validConfigSQL.capture("config15sql");
		fileValidator.validateInput(null,"BPA",validConfigSQL);
	}	
	
	
	
	
	
	

}
