package systemTesting.userTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.File;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import configuration.ConfigurationManager;
import report.ReportSummaryImpl;

public class TestReportSummaryImpl {
	
	private String clientCostsAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\clientCosts.csv";
	
	private String badclientCostsAddress1 = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\clientCostsNoNumbers.csv";
	
	private File clientCostsFile = new File(clientCostsAddress);
	
	private File badClientCostsFile;
	
	private ReportSummaryImpl clientSummaryReport = new ReportSummaryImpl();
	
	private static final double DELTA = 1e-11;
	
	
////////////////////////////////////////////////////////////////////////////////////////////////
	
	//These are the tests for createFile(File srcFile) and getClientSummaryReportFile() methods
	
	/*
	* Testing createFile with valid inputs
	*/
	//@Ignore
	@Test
	public void testCreateFileValidInputs(){
		assertEquals(true,clientSummaryReport.createFile(clientCostsFile,"clientSummary"));
		assertNotEquals(null,clientSummaryReport.getReportFile());
	}
	
	/*
	* Testing createFile with a Null File reference
	*/
	//@Ignore
	@Test
	public void testCreateFileWithNullFile(){
		assertEquals(false,clientSummaryReport.createFile(null,"clientSummary"));
		assertEquals(null,clientSummaryReport.getReportFile());
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////
	
//These are the tests for popMap(File srcFile) and getClientCosts methods

	/*
	* Testing popMap with valid inputs a Client type file
	*/
	//@Ignore
	@Test
	public void testPopMapValidInputsClientType(){
		assertEquals(true,clientSummaryReport.popMap(clientCostsFile,"client","cost"));
		assertEquals(3,clientSummaryReport.getClientsCosts().size());
		assertEquals(25877.97619047619,clientSummaryReport.getClientsCosts().get("American"),DELTA);
		assertEquals(131361.60714285716,clientSummaryReport.getClientsCosts().get("AirFrance"),DELTA);
		assertEquals(11510.416666666666,clientSummaryReport.getClientsCosts().get("BA"),DELTA);
	}
	
	
	/*
	* Testing popMap with valid inputs BPA type File
	*/
	//@Ignore
	@Test
	public void testPopMapValidInputsBPATypeFile(){
		assertEquals(true,clientSummaryReport.popMap(clientCostsFile,"BPA","cost"));
		assertEquals(2,clientSummaryReport.getClientsCosts().size());
		assertEquals(78750.0,clientSummaryReport.getClientsCosts().get("implementation"),DELTA);
		assertEquals(90000.0,clientSummaryReport.getClientsCosts().get("phones"),DELTA);
	}
	
	
	
	
	/*
	* Testing popMap with an invalid input null file Reference
	*/
	//@Ignore
	@Test
	public void testPopMapInValidInputsNullFileSource(){
		assertEquals(false,clientSummaryReport.popMap(null,"client","cost"));
		assertEquals(true,clientSummaryReport.getClientsCosts().isEmpty());
		badClientCostsFile = new File(badclientCostsAddress1);
	}
	
	/*
	* Testing popMap with a file that has an invalid format
	*/
	//@Ignore
	@Test
	public void testPopMapInValidInputsInvalidFormat(){
		badClientCostsFile = new File(badclientCostsAddress1);
		assertEquals(false,clientSummaryReport.popMap(badClientCostsFile,"client","cost"));
		assertEquals(true,clientSummaryReport.getClientsCosts().isEmpty());
	}
	
	/*
	* Testing popMap with a file that has missing partial data 
	*/
	//@Ignore
	@Test
	public void testPopMapInValidInputsMissingPartialData(){
		String badclientCostsAddress2 = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\clientCostsIncomplete.csv";
		badClientCostsFile = new File(badclientCostsAddress2);
		assertEquals(false,clientSummaryReport.popMap(badClientCostsFile,"client","cost"));
		assertEquals(true,clientSummaryReport.getClientsCosts().isEmpty());
	}
	
	/*
	* Testing popMap with a file that has missing data 
	*/
	//@Ignore
	@Test
	public void testPopMapInValidInputsMissingData(){
		String badclientCostsAddress2 = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\clientCostsEmpty.csv";
		badClientCostsFile = new File(badclientCostsAddress2);
		assertEquals(false,clientSummaryReport.popMap(badClientCostsFile,"client","cost"));
		assertEquals(true,clientSummaryReport.getClientsCosts().isEmpty());
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////
	
//These are the tests for popFile() method

	/*
	* Testing popFile with valid inputs
	*/
	//@Ignore
	@Test
	public void testPopFileValidInput(){
		clientSummaryReport.createFile(clientCostsFile,"clientSummary");
		clientSummaryReport.popMap(clientCostsFile,"client","cost");
		assertEquals(true,clientSummaryReport.popFile("client","cost"));
		clientSummaryReport.getReportFile().delete();
	}
	
	/*
	* Testing popFile with invalid inputs empty map
	*/
	//@Ignore
	@Test
	public void testPopFile(){
		clientSummaryReport.createFile(clientCostsFile,"clientSummary");
		clientSummaryReport.popMap(null,"client","cost");
		assertEquals(false,clientSummaryReport.popFile("client","cost"));
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////
	
//These are the tests for generateReport() method	
	
	/*
	* Testing generateReport with valid inputs
	*/
	//@Ignore
	@Test
	public void testGenerateReportValid(){
		assertEquals(true,clientSummaryReport.generateReport(clientCostsFile,"clientSummary","client","cost"));
		assertEquals(true,clientSummaryReport.getReportFile().exists());
		clientSummaryReport.getReportFile().delete();
	}
		
	/*
	* Testing generateReport with invalid inputs
	*/
	//@Ignore
	@Test
	public void testGenerateReportInValidNullFile(){
		assertEquals(false,clientSummaryReport.generateReport(null,"clientSummary","client","cost"));
		assertEquals(null,clientSummaryReport.getReportFile());
		assertEquals(true,clientSummaryReport.getClientsCosts().isEmpty());
	}
	
	/*
	* Testing generateReport with invalid inputs incompleteFile
	*/
	//@Ignore
	@Test
	public void testGenerateReportInValidincompleteFile(){
		String badclientCostsAddress2 = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\clientCostsIncomplete.csv";
		badClientCostsFile = new File(badclientCostsAddress2);
		assertEquals(false,clientSummaryReport.generateReport(badClientCostsFile,"clientSummary","client","cost"));
		assertEquals(null,clientSummaryReport.getReportFile());
		assertEquals(true,clientSummaryReport.getClientsCosts().isEmpty());
	}
	
	/*
	* Testing generateReport with invalid inputs emptyFile
	*/
	//@Ignore
	@Test
	public void testGenerateReportInValidEmptyFile(){
		String badclientCostsAddress2 = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\clientCostsEmpty.csv";
		badClientCostsFile = new File(badclientCostsAddress2);
		assertEquals(false,clientSummaryReport.generateReport(badClientCostsFile,"clientSummary","client","cost"));
		assertEquals(null,clientSummaryReport.getReportFile());
		assertEquals(true,clientSummaryReport.getClientsCosts().isEmpty());
	}
	

}
