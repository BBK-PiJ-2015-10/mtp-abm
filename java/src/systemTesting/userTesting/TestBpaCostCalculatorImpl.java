package systemTesting.userTesting;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;

import bpa.BpaCostCalculatorImpl;
import bpa.BpaCostsMaker;
import period.PeriodMaker;
import period.PeriodMakerImpl;

public class TestBpaCostCalculatorImpl {
	
	private String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user15\\period15";
	
	private File file = new File(address);
    
	private PeriodMaker period = new PeriodMakerImpl(file);
	
	private BpaCostCalculatorImpl bpaCostCalculator;
	
	
	@Before
	public void setUp() {
		period.capture("period15");
	}
	

	
///////////////////////////////////////////////////////////////////////////////////////////
	
//Below are the tests for the getActivityCost(String activityName)

	/*
	* The below test the Activity Costs of two valid activities
	*/
	//@Ignore
	@Test
	public void testgetActivityCostsValidInputs(){
		bpaCostCalculator = new BpaCostCalculatorImpl(period.getBpaCosts());
		int num=bpaCostCalculator.getActivityCost("implementation.csv");
		assertEquals(78750,num);
		num=bpaCostCalculator.getActivityCost("phones.csv");
		assertEquals(90000,num);
	}
	
	/*
	* The below test the Activity Costs for a non existent activity
	*/
	//@Ignore
	@Test
	public void testgetActivityCostsNonExistentActivity(){
		bpaCostCalculator = new BpaCostCalculatorImpl(period.getBpaCosts());
		int num=bpaCostCalculator.getActivityCost("anything.csv");
		assertEquals(0,num);
	}
	
	/*
	* The below test the Activity Costs with a null file
	*/
	//@Ignore
	@Test (expected = NullPointerException.class)
	public void testgetActivityCostsNullFile(){
		bpaCostCalculator = new BpaCostCalculatorImpl(null);
		int num=bpaCostCalculator.getActivityCost("anything.csv");
		assertEquals(0,num);
	}
	
	/*
	* The below tests that a map is populated after a valid Activity is costed.
	*/
	@Ignore
	@Test
	public void testgetActivityCostsValidInputsTestingMap(){
		bpaCostCalculator = new BpaCostCalculatorImpl(period.getBpaCosts());
		assertEquals(true,bpaCostCalculator.getBpaCostsMap().isEmpty());
		bpaCostCalculator.getActivityCost("implementation.csv");
		assertEquals(false,bpaCostCalculator.getBpaCostsMap().isEmpty());
	}
	
	/*
	* The below tests that an Activity cost calculation is saved on a map
	*/
	//@Ignore
	@Test
	public void testgetActivityCostsValidInputsTestingMapContent(){
		bpaCostCalculator = new BpaCostCalculatorImpl(period.getBpaCosts());
		int num1=bpaCostCalculator.getActivityCost("implementation.csv");
		int num1m=bpaCostCalculator.getBpaCostsMap().get("implementation.csv");
		assertEquals(num1,num1m);
		int num2=bpaCostCalculator.getActivityCost("phones.csv");
		int num2m=bpaCostCalculator.getBpaCostsMap().get("phones.csv");
		assertEquals(num2,num2m);
	}
	
	
	
	
	/*
	* The below tests that a map is populated after a valid Activity is costed.
	*/
	//@Ignore
	@Test
	public void testgetActivityCostsInValidInputsTestingMap(){
		bpaCostCalculator = new BpaCostCalculatorImpl(period.getBpaCosts());
		assertEquals(true,bpaCostCalculator.getBpaCostsMap().isEmpty());
		bpaCostCalculator.getActivityCost("anything.csv");
		assertEquals(false,bpaCostCalculator.getBpaCostsMap().isEmpty());
	}
	

	
	

}
