package systemTesting.userTesting;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import report.ReportGeneratorImpl;

import period.PeriodMaker;
import period.PeriodMakerImplCSV;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

//import old.PeriodMakerImpl;


public class TestReportGeneratorImpl {
	
	private Scanner sc;
	
	private String validPeriodAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16";
	
	private File validPeriodFile = new File(validPeriodAddress);
	
	private PeriodMaker validPeriodMaker = new PeriodMakerImplCSV(validPeriodFile);
	
	private ReportGeneratorImpl reportGeneratorImpl;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	private ByteArrayInputStream auto;
	
	
	public void autoFeedSetUp(String message){
		auto = new ByteArrayInputStream(message.getBytes());
		System.setIn(auto);
		sc = new Scanner(System.in);
	}
	
	public void manualScanner(){
		sc = new Scanner(System.in);
	}
	
	
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	@Before
	public void setUp(){
		validPeriodMaker.capture("period16");
		setUpStreams();
	}
	
///////////////////////////////////////////////////////////////////////////////////////////
	
	//Tests for presentChoiceValid()
	
	
	/*
	 * Test print output of method call with valid parameters()
	 */
	//@Ignore
	@Test
	public void testPresentChoiceValid() {
		reportGeneratorImpl = new ReportGeneratorImpl(validPeriodMaker);
		String test1 = "For: SummaryClientReport, type: 1";
		String test2 = "For: DetailedClientReport, type: 2";
		String test3 = "For: SummaryBPAReport, type: 3";
		String test4 = "For: DetailedBPAReport, type: 4";
		assertEquals(true,reportGeneratorImpl.presentChoices());
		assertEquals(test1,outContent.toString().trim().substring(0,33));
		assertEquals(test2,outContent.toString().trim().substring(35,69));
		assertEquals(test3,outContent.toString().trim().substring(71,101));
		assertEquals(test4,outContent.toString().trim().substring(103,134));
	}
	
	/*
	 * Test print output of method call with valid parameters()
	 */
	//@Ignore
	@Test
	public void testPresentChoiceInValidNull() {
		reportGeneratorImpl = new ReportGeneratorImpl(null);
		String test1 = "For: SummaryClientReport, type: 1";
		String test2 = "For: DetailedClientReport, type: 2";
		String test3 = "For: SummaryBPAReport, type: 3";
		String test4 = "For: DetailedBPAReport, type: 4";
		assertEquals(false,reportGeneratorImpl.presentChoices());
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////
	
	//Tests for initialize() and getOptionsList() and getOptionsMap() methods
	
	
	/*
	 * Running initialize and testing that properly updates options list and
	 * options map.
	 */
	//@Ignore
	@Test
	public void testInitializeValid() {
		reportGeneratorImpl = new ReportGeneratorImpl(validPeriodMaker);
		assertEquals(true,reportGeneratorImpl.initialize());
		assertEquals(4,reportGeneratorImpl.getOptionsList().size());
		assertEquals(4,reportGeneratorImpl.getOptionsMap().size());
	}
	
	
	/*
	 * Running initialize and testing that properly updates options list and
	 * options map.
	 */
	//@Ignore
	@Test
	public void testInitializeInValidNullPeriodMaker() {
		reportGeneratorImpl = new ReportGeneratorImpl(null);
		assertEquals(false,reportGeneratorImpl.initialize());
		assertEquals(null,reportGeneratorImpl.getOptionsList());
		assertEquals(null,reportGeneratorImpl.getOptionsMap());
	}
	
///////////////////////////////////////////////////////////////////////////////////////////
	
//Tests for captureChoice(Scanner sc) and getChoice() methods 


	/*
	* Testing that captureChoice returns true with a valid input and test
	* the options captured corresponds to the input entered.
	*/
	//@Ignore
	@Test
	public void testCaptureChoicesValid() {
	    autoFeedSetUp("3");
		reportGeneratorImpl = new ReportGeneratorImpl(validPeriodMaker);
		assertEquals(true,reportGeneratorImpl.captureChoice(sc));
		assertEquals(3,reportGeneratorImpl.getChoice());
	}
	
	/*
	* Testing that captureChoice looks for a second element when an invalid
	* choice is entered.
	*/
	//@Ignore
	@Test (expected = NoSuchElementException.class)
	public void testCaptureChoicesInValidEntry() {
	    autoFeedSetUp("5");
		reportGeneratorImpl = new ReportGeneratorImpl(validPeriodMaker);
		reportGeneratorImpl.captureChoice(sc);
	}
	
	
	/*
	* Testing that captureChoice returns false when the periodMaker is null
	*/
	//@Ignore
	@Test
	public void testCaptureChoicesInValidNullPeriod() {
	    autoFeedSetUp("3");
		reportGeneratorImpl = new ReportGeneratorImpl(null);
		assertEquals(false,reportGeneratorImpl.captureChoice(sc));
	}
	
	
	
////////////////////////////////////////////////////////////////////////////////	
	
	//Below are the tests for generateReport()
	
	/*
	* Testing that if choice 4 is entered, generateReport yields a reportDetailedBPA.csv file
	*/
	//@Ignore
	@Test
	public void testGenerateReportValidReportDetailedBPA() {
	    autoFeedSetUp("4");
	    manualScanner();
		reportGeneratorImpl = new ReportGeneratorImpl(validPeriodMaker);
		reportGeneratorImpl.captureChoice(sc);
		assertEquals(true,reportGeneratorImpl.generateReport());
		String tempAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\reportDetailedBPA.csv";
		File tempF = new File(tempAddress);
		assertEquals(true,tempF.exists());
		tempF.delete();	
	}
	
	/*
	* Testing that if choice 3 is entered, generateReport yields a reportSummaryBPA.csv file
	*/
	//@Ignore
	@Test
	public void testGenerateReportValidReportSummaryBPA() {
	    autoFeedSetUp("3");
		reportGeneratorImpl = new ReportGeneratorImpl(validPeriodMaker);
		reportGeneratorImpl.captureChoice(sc);
		assertEquals(true,reportGeneratorImpl.generateReport());
		String tempAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\reportSummaryBPA.csv";
		File tempF = new File(tempAddress);
		assertEquals(true,tempF.exists());
		tempF.delete();
	}
	
	/*
	* Testing that if choice 4 is entered, generateReport yields a reportDetailedBPA.csv file
	*/
	//@Ignore
	@Test
	public void testGenerateReportValidReportDetailedClient() {
	    autoFeedSetUp("2");
		reportGeneratorImpl = new ReportGeneratorImpl(validPeriodMaker);
		reportGeneratorImpl.captureChoice(sc);
		assertEquals(true,reportGeneratorImpl.generateReport());
		String tempAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\reportDetailedClient.csv";
		File tempF = new File(tempAddress);
		assertEquals(true,tempF.exists());
		tempF.delete();	
	}
	
	/*
	* Testing that if choice 3 is entered, generateReport yields a reportSummaryBPA.csv file
	*/
	//@Ignore
	@Test
	public void testGenerateReportValidReportSummaryClient() {
	    autoFeedSetUp("1");
		reportGeneratorImpl = new ReportGeneratorImpl(validPeriodMaker);
		reportGeneratorImpl.captureChoice(sc);
		assertEquals(true,reportGeneratorImpl.generateReport());
		String tempAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user16\\period16\\reportSummaryClient.csv";
		File tempF = new File(tempAddress);
		assertEquals(true,tempF.exists());
		tempF.delete();
	}
		
	/*
	* Testing that if no report choice is specified, generateReports returns false.
	*/
	//@Ignore
	@Test
	public void testGenerateReportInValidReportNullReport() {
		reportGeneratorImpl = new ReportGeneratorImpl(validPeriodMaker);
		assertEquals(false,reportGeneratorImpl.generateReport());
	}
	
		
	
	
	
	

}
