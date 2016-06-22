package systemTesting.userTesting;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Ignore;

import java.io.File;
import user.UserSpace;
import user.UserSpaceMaker;
import user.UserSpaceMakerImpl;

import java.util.Scanner;

public class TestCreateUserSpaceMakerImpl {
	
	
	private UserSpace userSpace;
	
	private UserSpace cloneuserSpace;
	
	private UserSpace invaliduserSpace;
	
	private Scanner sc = new Scanner(System.in);
	
	private String validAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\";
	
	private String invalidAddress = "this is not valid";
	
	private File mfile = new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\test");
	
	private File validFile = new File(validAddress);
	
	private File invalidFile = new File(invalidAddress);
	
	private File config1 = new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\test\\config1");
	
	private File config2 = new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\test\\config2");
	
	private File config3 = new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\test\\config2");
	
	private UserSpaceMaker userSpaceMaker1;
	
	
	@Before
	public void initializeUserSpace(){
		userSpaceMaker1 = new UserSpaceMakerImpl();
	}
	
	@After
	public void shutoffUserSpace(){
		userSpaceMaker1 = null;
	}
	
	
	
	/* 
	* Testing creating a validUserSpace
	*/
	@Test
	public void testFileSetUserSpaceFileValidFile() {
		userSpaceMaker1.createUserSpace(new UserSpace());
		System.out.println("Enter the name again");
		String value = sc.nextLine();
		assertEquals(true,new File(validAddress+value).exists());
		new File(validAddress+value+"//"+value+".dat").delete();
		new File(validAddress+value).delete();
	}
	
	/* 
	* Testing passing an uninitizilized userSpace
	*/
	@Test(expected = NullPointerException.class)
	public void testFileSetUserSpaceFileInValidFile() {
		userSpaceMaker1.createUserSpace(userSpace);
	}	
	
	
	

	
	

}
