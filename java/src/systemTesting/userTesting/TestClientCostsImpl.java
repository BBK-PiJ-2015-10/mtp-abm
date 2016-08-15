package systemTesting.userTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import bpa.BpaClientWeightsCalculatorImpl;
import bpa.BpaCostCalculator;
import bpa.BpaCostCalculatorImpl;
import bpa.BpaCostsMaker;
import bpa.BpaCostsMakerImplCSV;
import period.PeriodMaker;
import period.PeriodMakerImpl;
import client.ClientCostsImpl;

public class TestClientCostsImpl {
	
	private String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user15\\period15";
	
	private File file = new File(address);
    
	private PeriodMaker period = new PeriodMakerImpl(file);
	
	private BpaCostsMaker bpaCostsMaker;
	
	private BpaClientWeightsCalculatorImpl bpaClientWeightsCalculator;
	
	private BpaCostCalculator bpaCostCalculator;
	
	private ClientCostsImpl clientCostsImpl;
	
	@Before
	public void setUp() {
		period.capture("period15");
		bpaCostsMaker = new BpaCostsMakerImplCSV(period);
		bpaClientWeightsCalculator = new BpaClientWeightsCalculatorImpl(bpaCostsMaker);
		bpaCostCalculator = new BpaCostCalculatorImpl(period.getBpaCosts());
	}
	
///////////////////////////////////////////////////////////////////////////////////////////
	
//Below are the tests for createClientsCostsFile()

	/*
	* The below test that with a valid set of inputs the creator returns true.
	*/
	//@Ignore
	@Test
	public void testCreateClientCostsFileValidInputs(){
		clientCostsImpl = new ClientCostsImpl(bpaCostCalculator,bpaClientWeightsCalculator);
		assertEquals(true,clientCostsImpl.createClientCostsFile());
		assertNotEquals(null,clientCostsImpl.getClientCostsFile());
		
	}
	
	/*
	* The below test that with an  invalid set of inputs the creator returns true.
	*/
	//@Ignore
	@Test
	public void testCreateClientCostsFileInvalidInputs(){
		clientCostsImpl = new ClientCostsImpl(bpaCostCalculator,null);
		assertEquals(false,clientCostsImpl.createClientCostsFile());
		assertEquals(null,clientCostsImpl.getClientCostsFile());
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////
	
	//Below are the tests for calculateClientsCosts()
	
	/*
	* The below test that with a valid set of inputs the creator returns true.
	*/
	//@Ignore
	@Test
	public void testcalculateClientCostsValidInputs(){
		clientCostsImpl = new ClientCostsImpl(bpaCostCalculator,bpaClientWeightsCalculator);
		assertEquals(true,clientCostsImpl.calculateClientCosts());
		assertEquals(true,clientCostsImpl.getClientCostsFile().exists());
		clientCostsImpl.getClientCostsFile().delete();
	}
	
	/*
	* The below test that with an invalid set of inputs the creator returns false.
	*/
	//@Ignore
	@Test
	public void testcalculateClientCostsInValidInputsI(){
		clientCostsImpl = new ClientCostsImpl(bpaCostCalculator,null);
		assertEquals(false,clientCostsImpl.calculateClientCosts());
		assertEquals(null,clientCostsImpl.getClientCostsFile());
	}
	
	/*
	* The below test that with an invalid set of inputs the creator returns false.
	*/
	//@Ignore
	@Test
	public void testcalculateClientCostsInValidInputsII(){
		clientCostsImpl = new ClientCostsImpl(null,bpaClientWeightsCalculator);
		assertEquals(false,clientCostsImpl.calculateClientCosts());
		assertEquals(null,clientCostsImpl.getClientCostsFile());
	}
	
	
	
	
	
	
	
	
	

}
