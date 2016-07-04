package systemTesting.userTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.File;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import configuration.ConfigurationManager;
import user.UserSpace;
import period.PeriodMaker;
import period.PeriodMakerImpl;


public class TestPeriodMakerImpl {
	
	private UserSpace userSpace;
	
	private String validAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user3\\config3";
	
	private File validFile = new File(validAddress);
	
	private File mfile = new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\test");
	
	private PeriodMakerImpl periodMakerImpl; 
	
	
	@Before
	public void initializeUserSpace(){
		mfile.mkdir();
		userSpace = new UserSpace();
		userSpace.capture("config3");
		periodMakerImpl = new PeriodMakerImpl(userSpace);	
	}
	
	//@After
	//public void destroyUserSpace(){
		//userSpace = null;
		//periodMakerImpl = null;
		//mfile.delete();
	//}
	
/////////////////////////////////////////////////////////////////////////////////////
	
//These are the test for the getUserSpace() and the Constructor with UserSpace

	/*
	 * Testing getUserSpace with valid file
	 */
	@Test
	public void testgetUserSpaceValid(){
		assertEquals(userSpace,periodMakerImpl.getUserSpace());
	}
	
	/*
	 * Testing getUserSpace with invalid file
	 */
	@Test
	public void testgetUserSpaceNotValid(){
		UserSpace userSpace2 = new UserSpace();
		assertNotEquals(userSpace2,periodMakerImpl.getUserSpace());
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * Testing getUserSpace with invalid file
	 */
	@Test
	public void confg(){
		UserSpace userSpace2 = new UserSpace();
		assertNotEquals(2,3);
	}	
	
	

}
