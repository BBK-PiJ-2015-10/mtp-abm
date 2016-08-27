package systemTesting.userTesting;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import bpa.BpaCostsMakerImplCSV;
import client.BpaClientWeightsCalculatorImpl;
import bpa.BpaCostsMaker;

import period.PeriodMaker;
import period.PeriodMakerImplCSV;

public class TestBpaClientWeightsCalculatorImpl {
	
	private String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user17\\period17";
	
	private File file = new File(address);
    
	private PeriodMaker period = new PeriodMakerImplCSV(file);
	
	private BpaCostsMaker bpaCostsMaker;
	
	private BpaClientWeightsCalculatorImpl bpaClientWeightsCalculator;
	
	private static final double DELTA = 1e-9;
	
	
	@Before
	public void setUp() {
		period.capture("period17");
	}
	

	
///////////////////////////////////////////////////////////////////////////////////////////
	
    //Below are the tests for the getClientWeights

	
	/*
	* The below tests that calling a getClientsWeights on a calculator with a null Cost Maker returns null
	*/
	//@Ignore
	@Test
	public void testgetClientsWeightsInValidInputsNullPeriodMaker(){
		bpaCostsMaker = new BpaCostsMakerImplCSV(period);
		bpaClientWeightsCalculator = new BpaClientWeightsCalculatorImpl(null);
		assertEquals(null,bpaClientWeightsCalculator.getClientsWeights());
	}
	
	/*
	* The below tests that calling a getClientsWeights on a calculator with a null period returns null
	*/
	//@Ignore
	@Test
	public void testgetClientsWeightsInValidInputsNullPeriodMakerII(){
		bpaCostsMaker = new BpaCostsMakerImplCSV(null);
		bpaClientWeightsCalculator = new BpaClientWeightsCalculatorImpl(bpaCostsMaker);
		assertEquals(null,bpaClientWeightsCalculator.getClientsWeights());
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////
	
    //Below are the tests for calcWeights();

	/*
	* The below test that a calcWeights on a valid set of input returns a true
	*/
	//@Ignore
	@Test
	public void testPopMapValidInput(){
		bpaCostsMaker = new BpaCostsMakerImplCSV(period);
		bpaClientWeightsCalculator = new BpaClientWeightsCalculatorImpl(bpaCostsMaker);
		assertEquals(true,bpaClientWeightsCalculator.calcWeights());
	}
	
	
	/*
	* The below tests the contents for completeness of the calcWeights. Testing for the keys in
	* the master map and also for the keys on the sub maps.
	*/
	@Test
	public void testCalcWeightsValidInputsII(){
		bpaCostsMaker = new BpaCostsMakerImplCSV(period);
		bpaClientWeightsCalculator = new BpaClientWeightsCalculatorImpl(bpaCostsMaker);
		assertEquals(5,bpaClientWeightsCalculator.getClientsWeights().keySet().size());
		assertEquals(11,bpaClientWeightsCalculator.getClientsWeights().get("phones.csv").keySet().size());
		assertEquals(11,bpaClientWeightsCalculator.getClientsWeights().get("implementation.csv").keySet().size());
		assertEquals(11,bpaClientWeightsCalculator.getClientsWeights().get("ITDemand.csv").keySet().size());
		assertEquals(11,bpaClientWeightsCalculator.getClientsWeights().get("payroll.csv").keySet().size());
		assertEquals(11,bpaClientWeightsCalculator.getClientsWeights().get("sales.csv").keySet().size());
	}
	
	/*
	* The below tests the contents accuracy of the calcWeights. Testing for the weights of
	* the values in the submaps.
	*/
	@Test
	public void testCalcWeightsValidInputsIII(){
		bpaCostsMaker = new BpaCostsMakerImplCSV(period);
		bpaClientWeightsCalculator = new BpaClientWeightsCalculatorImpl(bpaCostsMaker);
		double result = bpaClientWeightsCalculator.getClientsWeights().get("phones.csv").get("AirChina");
		assertEquals(0.07804703,result,DELTA);
		result = bpaClientWeightsCalculator.getClientsWeights().get("phones.csv").get("AirFrance");
		assertEquals(0.094488749,result,DELTA);
		result = bpaClientWeightsCalculator.getClientsWeights().get("phones.csv").get("AirJapan");
		assertEquals(0.09108809,result,DELTA);
		result = bpaClientWeightsCalculator.getClientsWeights().get("phones.csv").get("SwissAir");
		assertEquals(0.101317799,result,DELTA);
		result = bpaClientWeightsCalculator.getClientsWeights().get("sales.csv").get("Alitalia");
		assertEquals(0.058946825,result,DELTA);
		result = bpaClientWeightsCalculator.getClientsWeights().get("sales.csv").get("TACA");
		assertEquals(0.002382105,result,DELTA);
		result = bpaClientWeightsCalculator.getClientsWeights().get("sales.csv").get("AirJapan");
		assertEquals(0.079902613,result,DELTA);
		result = bpaClientWeightsCalculator.getClientsWeights().get("implementation.csv").get("BA");
		assertEquals(0.089247312,result,DELTA);
		result = bpaClientWeightsCalculator.getClientsWeights().get("implementation.csv").get("EmiratesAirlines");
		assertEquals(0.106451613,result,DELTA);
		result = bpaClientWeightsCalculator.getClientsWeights().get("implementation.csv").get("SwissAir");
		assertEquals(0.07311828,result,DELTA);
	}
	
	
	/*
	* The below test that a popMap on an invalid set of input returns false
	*/
	//@Ignore
	@Test
	public void testPopMapInValidInputI(){
		bpaCostsMaker = new BpaCostsMakerImplCSV(period);
		bpaClientWeightsCalculator = new BpaClientWeightsCalculatorImpl(null);
		assertEquals(false,bpaClientWeightsCalculator.calcWeights());
	}
	
	/*
	* The below test that a popMap on an invalid set of input returns false
	*/
	//@Ignore
	@Test
	public void testPopMapInValidInputII(){
		bpaCostsMaker = new BpaCostsMakerImplCSV(null);
		bpaClientWeightsCalculator = new BpaClientWeightsCalculatorImpl(bpaCostsMaker);
		assertEquals(false,bpaClientWeightsCalculator.calcWeights());
	}
	
	
	
	
	
	

}
