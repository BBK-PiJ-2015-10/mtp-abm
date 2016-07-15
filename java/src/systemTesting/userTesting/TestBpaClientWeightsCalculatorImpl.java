package systemTesting.userTesting;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import bpa.BpaCostsMakerImpl;
import bpa.BpaClientWeightsCalculatorImpl;
import bpa.BpaCostsMaker;
import period.PeriodMaker;
import period.PeriodMakerImpl;

public class TestBpaClientWeightsCalculatorImpl {
	
	private String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user15\\period15";
	
	private File file = new File(address);
    
	private PeriodMaker period = new PeriodMakerImpl(file);
	
	private BpaCostsMaker bpaCostsMaker;
	
	private BpaClientWeightsCalculatorImpl bpaClientWeightsCalculator;
	
	private static final double DELTA = 1e-11;
	
	
	@Before
	public void setUp() {
		period.capture("period15");
	}
	

	
///////////////////////////////////////////////////////////////////////////////////////////
	
    //Below are the tests for the getClientsWeights

	/*
	* The below tests the contents of a valid getClientsWeights results. Testing for the keys in the master
	* map and also for the keys on the sub maps.
	*/
	//@Ignore
	@Test
	public void testgetClientsWeightsValidInputsI(){
		bpaCostsMaker = new BpaCostsMakerImpl(period);
		bpaClientWeightsCalculator = new BpaClientWeightsCalculatorImpl(bpaCostsMaker);
		assertEquals(2,bpaClientWeightsCalculator.getClientsWeights().keySet().size());
		assertEquals(3,bpaClientWeightsCalculator.getClientsWeights().get("phones.csv").keySet().size());
		assertEquals(3,bpaClientWeightsCalculator.getClientsWeights().get("implementation.csv").keySet().size());
	}
	
	/*
	* The below tests the contents of a valid getClientsWeights results. Testing for the weights of
	* the values in the submaps.
	*/
	//@Ignore
	@Test
	public void testgetClientsWeightsValidInputsII(){
		bpaCostsMaker = new BpaCostsMakerImpl(period);
		bpaClientWeightsCalculator = new BpaClientWeightsCalculatorImpl(bpaCostsMaker);
		double result = bpaClientWeightsCalculator.getClientsWeights().get("phones.csv").get("American");
		assertEquals(0.06878306878,result,DELTA);
		result = bpaClientWeightsCalculator.getClientsWeights().get("phones.csv").get("AirFrance");
		assertEquals(0.91269841270,result,DELTA);
		result = bpaClientWeightsCalculator.getClientsWeights().get("phones.csv").get("BA");
		assertEquals(0.01851851852,result,DELTA);
		result = bpaClientWeightsCalculator.getClientsWeights().get("implementation.csv").get("American");
		assertEquals(0.25,result,DELTA);
		result = bpaClientWeightsCalculator.getClientsWeights().get("implementation.csv").get("AirFrance");
		assertEquals(0.625,result,DELTA);
		result = bpaClientWeightsCalculator.getClientsWeights().get("implementation.csv").get("BA");
		assertEquals(0.125,result,DELTA);	
	}
		
	/*
	* The below tests that calling a getClientsWeights on a calculator with a null Cost Maker returns null
	*/
	//@Ignore
	@Test
	public void testgetClientsWeightsInValidInputsNullPeriodMaker(){
		bpaCostsMaker = new BpaCostsMakerImpl(period);
		bpaClientWeightsCalculator = new BpaClientWeightsCalculatorImpl(null);
		assertEquals(null,bpaClientWeightsCalculator.getClientsWeights());
	}
	
	/*
	* The below tests that calling a getClientsWeights on a calculator with a null period returns null
	*/
	//@Ignore
	@Test
	public void testgetClientsWeightsInValidInputsNullPeriodMakerII(){
		bpaCostsMaker = new BpaCostsMakerImpl(null);
		bpaClientWeightsCalculator = new BpaClientWeightsCalculatorImpl(bpaCostsMaker);
		assertEquals(null,bpaClientWeightsCalculator.getClientsWeights());
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////
	
    //Below are the tests for popMap();

	/*
	* The below test that a popMap on a valid set of input returns a true
	*/
	//@Ignore
	@Test
	public void testPopMapValidInput(){
		bpaCostsMaker = new BpaCostsMakerImpl(period);
		bpaClientWeightsCalculator = new BpaClientWeightsCalculatorImpl(bpaCostsMaker);
		assertEquals(true,bpaClientWeightsCalculator.popmap());
	}
	
	/*
	* The below test that a popMap on an invalid set of input returns false
	*/
	//@Ignore
	@Test
	public void testPopMapInValidInputI(){
		bpaCostsMaker = new BpaCostsMakerImpl(period);
		bpaClientWeightsCalculator = new BpaClientWeightsCalculatorImpl(null);
		assertEquals(false,bpaClientWeightsCalculator.popmap());
	}
	
	/*
	* The below test that a popMap on an invalid set of input returns false
	*/
	//@Ignore
	@Test
	public void testPopMapInValidInputII(){
		bpaCostsMaker = new BpaCostsMakerImpl(null);
		bpaClientWeightsCalculator = new BpaClientWeightsCalculatorImpl(bpaCostsMaker);
		assertEquals(false,bpaClientWeightsCalculator.popmap());
	}
	
	
	
	
	
	

}
