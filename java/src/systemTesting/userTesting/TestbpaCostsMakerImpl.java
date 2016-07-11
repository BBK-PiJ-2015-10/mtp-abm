package systemTesting.userTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import bpa.BpaCostsMaker;
import bpa.BpaCostsMakerImpl;
import period.PeriodMaker;
import period.PeriodMakerImpl;

public class TestbpaCostsMakerImpl {
	
	private String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user14\\period14";
	
	private File file = new File(address);
    
	private PeriodMaker period = new PeriodMakerImpl(file);
	
	private BpaCostsMakerImpl bpaCostsMakerImpl;
	
	@Before
	public void setUp() {
		period.capture("period14");
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////
	
	//Below are the tests for the constructor and the getPeriod method.
	
	/*
	 * The below test compares the period entered vs the value provided by getPeriod.
	 */
	@Ignore
	@Test
	public void testConstructorValid(){
		bpaCostsMakerImpl = new BpaCostsMakerImpl(period);
		assertNotEquals(null,bpaCostsMakerImpl.getPeriodMaker());;
	}
	
	/*
	 * The below test compares the period entered vs the value provided by getPeriod.
	 */
	@Ignore
	@Test
	public void testConstructornull(){
		bpaCostsMakerImpl = new BpaCostsMakerImpl(null);
		assertEquals(null,bpaCostsMakerImpl.getPeriodMaker());;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////	
	
	//Below are the tests for the constructor and the getPeriod method.
	
	/*
	 * Test that if a null or invalid period is encapsulated the method returns false.
	 */
	@Ignore
	@Test
	public void DisplayInputFilesInValid(){
		bpaCostsMakerImpl = new BpaCostsMakerImpl(null);
		assertEquals(false,bpaCostsMakerImpl.displayInputFilesNames());
	}	

	/*
	 * Test that if a valid period is encapsulated the method returns true. 
	 */
	@Ignore
	@Test
	public void DisplayInputFilesValid(){
		bpaCostsMakerImpl = new BpaCostsMakerImpl(period);
		assertEquals(true,bpaCostsMakerImpl.displayInputFilesNames());
	}	
	
	
/////////////////////////////////////////////////////////////////////////////////////////	
	
//Below are the tests for the putToSleep method.

	/*
	* Test that if a valid time is entered the method returns true.
	*/
	@Ignore
	@Test
	public void testPutToSleepValid(){
	bpaCostsMakerImpl = new BpaCostsMakerImpl(period);
	assertEquals(true,bpaCostsMakerImpl.putToSleep(30));
	}	
	
	/*
	* Test that if a valid time is entered the method returns true.
	*/
	@Ignore
	@Test
	public void testPutToSleepInValid(){
	bpaCostsMakerImpl = new BpaCostsMakerImpl(period);
	assertEquals(false,bpaCostsMakerImpl.putToSleep(-30));
	}
	
//////////////////////////////////////////////////////////////////////////////////////////
	
	//Below are the tests for validateInput

		/*
		* Test that if all files are present the method returns true.
		*/
		//@Ignore
		@Test
		public void testValidateInputValid(){
		bpaCostsMakerImpl = new BpaCostsMakerImpl(period);
		assertEquals(true,bpaCostsMakerImpl.validateInput());
		}	
		
		/*
		* Test that if all files are present the method returns true.
		*/
		//@Ignore
		@Test
		public void testValidateInputEmpty(){
		bpaCostsMakerImpl = new BpaCostsMakerImpl(period);
		assertEquals(true,bpaCostsMakerImpl.validateInput());
		}
		
	
	
	
	
}
