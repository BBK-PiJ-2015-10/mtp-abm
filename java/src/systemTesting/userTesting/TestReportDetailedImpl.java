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
import report.ReportDetailedImpl;

public class TestReportDetailedImpl {
	
	private String bpaCostsAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\bpaCosts.csv";
	
	private String clientCostsAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\clientCosts3.csv";
	
	//private String badclientCostsAddress1 = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\clientCostsNoNumbers.csv";
	
	private File bpaCostsFile = new File(bpaCostsAddress);
	
	private File clientCostsFile = new File(clientCostsAddress);
	
	//private File badClientCostsFile;
	
	private ReportDetailedImpl bpaDetailedReport = new ReportDetailedImpl();
	
	private ReportDetailedImpl clientDetailedReport = new ReportDetailedImpl();
	
	private static final double DELTA = 1e-11;
	
	
////////////////////////////////////////////////////////////////////////////////////////////////
	
	//These are the tests for createFile(File srcFile) and getClientSummaryReportFile() methods
	
	/*
	* Testing createFile with valid inputs
	*/
	//@Ignore
	@Test
	public void testCreateFileValidInputs(){
		assertEquals(true,bpaDetailedReport.createFile(bpaCostsFile,"bpaDetailed"));
		assertNotEquals(null,bpaDetailedReport.getReportFile());
	}
	
	/*
	* Testing createFile with a Null File reference
	*/
	//@Ignore
	@Test
	public void testCreateFileWithNullFile(){
		assertEquals(false,bpaDetailedReport.createFile(null,"bpaDetailed"));
		assertEquals(null,bpaDetailedReport.getReportFile());
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////
	
//These are the tests for popMap(File srcFile) and getCosts methods

	/*
	* Testing popMap with valid inputs a BPA type file
	*/
	//@Ignore
	@Test
	public void testPopMapValidInputsClientType(){
		assertEquals(true,bpaDetailedReport.popMap(bpaCostsFile,"BPA","Amount"));
		assertEquals(2,bpaDetailedReport.getCosts().size());
		assertEquals(3,bpaDetailedReport.getCosts().get("implementation").size());
		assertEquals(2,bpaDetailedReport.getCosts().get("phones").size());
		assertEquals(3,bpaDetailedReport.getCosts().get("implementation").size());
		assertEquals("11250.0",bpaDetailedReport.getCosts().get("implementation").get(0).get(2));
		assertEquals("22500.0",bpaDetailedReport.getCosts().get("implementation").get(1).get(2));
		assertEquals("45000.0",bpaDetailedReport.getCosts().get("implementation").get(2).get(2));
		assertEquals(2,bpaDetailedReport.getCosts().get("phones").size());
		assertEquals("22500.0",bpaDetailedReport.getCosts().get("phones").get(0).get(2));
		assertEquals("67500.0",bpaDetailedReport.getCosts().get("phones").get(1).get(2));
	}
	
	
	/*
	* Testing popMap with valid inputs Client type File
	*/
	//@Ignore
	@Test
	public void testPopMapValidInputsClientTypeFile(){
		assertEquals(true,clientDetailedReport.popMap(clientCostsFile,"client","cost"));
		assertEquals(3,clientDetailedReport.getCosts().size());
		assertEquals(3,clientDetailedReport.getCosts().get("AirFrance").size());
		assertEquals("49218.75",clientDetailedReport.getCosts().get("AirFrance").get(0).get(2));
		assertEquals("123214.28571",clientDetailedReport.getCosts().get("AirFrance").get(1).get(2));
		assertEquals("24609.375",clientDetailedReport.getCosts().get("AirFrance").get(2).get(2));
		assertEquals(3,clientDetailedReport.getCosts().get("American").size());
		assertEquals("29531.25",clientDetailedReport.getCosts().get("American").get(0).get(2));
		assertEquals("6190.47619",clientDetailedReport.getCosts().get("American").get(1).get(2));
		assertEquals("3095.238095",clientDetailedReport.getCosts().get("American").get(2).get(2));
		assertEquals(2,clientDetailedReport.getCosts().get("BA").size());
		assertEquals("14765.625",clientDetailedReport.getCosts().get("BA").get(0).get(2));
		assertEquals("2500.0000005",clientDetailedReport.getCosts().get("BA").get(1).get(2));
	}
	
	
	
	
	/*
	* Testing popMap with an invalid input null file Reference
	*/
	//@Ignore
	@Test
	public void testPopMapInValidInputsNullFileSource(){
		assertEquals(false,bpaDetailedReport.popMap(null,"BPA","Amount"));
		assertEquals(true,bpaDetailedReport.getCosts().isEmpty());
	}
	
	/*
	* Testing popMap with a file that has an invalid format
	*/
	@Ignore
	@Test
	public void testPopMapInValidInputsInvalidFormat(){
		//badClientCostsFile = new File(badclientCostsAddress1);
		////assertEquals(false,clientSummaryReport.popMap(badClientCostsFile,"client","cost"));
		//assertEquals(true,clientSummaryReport.getClientsCosts().isEmpty());
	}
	
	/*
	* Testing popMap with a file that has missing partial data 
	*/
	@Ignore
	@Test
	public void testPopMapInValidInputsMissingPartialData(){
		String badclientCostsAddress2 = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\clientCostsIncomplete.csv";
		//badClientCostsFile = new File(badclientCostsAddress2);
		//assertEquals(false,clientSummaryReport.popMap(badClientCostsFile,"client","cost"));
		//assertEquals(true,clientSummaryReport.getClientsCosts().isEmpty());
	}
	
	/*
	* Testing popMap with a file that has missing data 
	*/
	@Ignore
	@Test
	public void testPopMapInValidInputsMissingData(){
		String badclientCostsAddress2 = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\clientCostsEmpty.csv";
		//badClientCostsFile = new File(badclientCostsAddress2);
		//assertEquals(false,clientSummaryReport.popMap(badClientCostsFile,"client","cost"));
		//assertEquals(true,clientSummaryReport.getClientsCosts().isEmpty());
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////
	
//These are the tests for popFile() method

	/*
	* Testing popFile with valid inputs
	*/
	@Ignore
	@Test
	public void testPopFileValidInput(){
		//clientSummaryReport.createFile(clientCostsFile,"clientSummary");
		//clientSummaryReport.popMap(clientCostsFile,"client","cost");
		//assertEquals(true,clientSummaryReport.popFile("client","cost"));
		//clientSummaryReport.getReportFile().delete();
	}
	
	/*
	* Testing popFile with invalid inputs empty map
	*/
	@Ignore
	@Test
	public void testPopFile(){
		//clientSummaryReport.createFile(clientCostsFile,"clientSummary");
		//clientSummaryReport.popMap(null,"client","cost");
		//assertEquals(false,clientSummaryReport.popFile("client","cost"));
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////
	
//These are the tests for generateReport() method	
	
	/*
	* Testing generateReport with valid inputs
	*/
	@Ignore
	@Test
	public void testGenerateReportValid(){
		//assertEquals(true,clientSummaryReport.generateReport(clientCostsFile,"clientSummary","client","cost"));
		//assertEquals(true,clientSummaryReport.getReportFile().exists());
		//clientSummaryReport.getReportFile().delete();
	}
		
	/*
	* Testing generateReport with invalid inputs
	*/
	@Ignore
	@Test
	public void testGenerateReportInValidNullFile(){
		//assertEquals(false,clientSummaryReport.generateReport(null,"clientSummary","client","cost"));
		//assertEquals(null,clientSummaryReport.getReportFile());
		//assertEquals(true,clientSummaryReport.getClientsCosts().isEmpty());
	}
	
	/*
	* Testing generateReport with invalid inputs incompleteFile
	*/
	@Ignore
	@Test
	public void testGenerateReportInValidincompleteFile(){
		String badclientCostsAddress2 = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\clientCostsIncomplete.csv";
		//badClientCostsFile = new File(badclientCostsAddress2);
		//assertEquals(false,clientSummaryReport.generateReport(badClientCostsFile,"clientSummary","client","cost"));
		//assertEquals(null,clientSummaryReport.getReportFile());
		//assertEquals(true,clientSummaryReport.getClientsCosts().isEmpty());
	}
	
	/*
	* Testing generateReport with invalid inputs emptyFile
	*/
	@Ignore
	@Test
	public void testGenerateReportInValidEmptyFile(){
		String badclientCostsAddress2 = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\clientCostsEmpty.csv";
		//badClientCostsFile = new File(badclientCostsAddress2);
		//assertEquals(false,clientSummaryReport.generateReport(badClientCostsFile,"clientSummary","client","cost"));
		//assertEquals(null,clientSummaryReport.getReportFile());
		//assertEquals(true,clientSummaryReport.getClientsCosts().isEmpty());
	}
	

}