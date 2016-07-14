package systemTesting.userTesting;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bpa.BpaCostsMakerImpl;
import bpa.BpaClientWeightsCalculator;
import bpa.BpaCostsMaker;
import period.PeriodMaker;
import period.PeriodMakerImpl;

public class TestBpaClientWeightsCalculatorImpl {
	
	private String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user14\\period14";
	
	private File file = new File(address);
    
	private PeriodMaker period = new PeriodMakerImpl(file);
	
	private BpaCostsMaker bpaCostsMaker;
	
	private BpaClientWeightsCalculator bpaClientWeightsCalculator;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	
	@Before
	public void setUp() {
		period.capture("period14");
		setUpStreams();
	}
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////
	
//Below are the tests for the constructor and the getPeriod method.

	/*
	* The below test compares the period entered vs the value provided by getPeriod.
	*/
	//@Ignore
	@Test
	public void testConstructorValid(){
		//bpaCostsMakerImpl = new BpaCostsMakerImpl(period);
	}
	

}
