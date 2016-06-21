package systemTesting.userTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import user.UserSpace;
import java.io.File;

public class testUserSpace {
	
	private UserSpace userSpace;
	
	private UserSpace cloneuserSpace;
	
	private UserSpace invaliduserSpace;
	
	private String validAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\test";
	
	private String invalidAddress = "C:\\Users\\Bibbio\\mp\\mtp-abm\\test";
	
	private File mfile = new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\test");
	
	private File validFile = new File(validAddress);
	
	private File invalidFile = new File(invalidAddress);
	
	private File config1 = new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\test\\config1");
	
	private File config2 = new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\test\\config2");
	
	private File config3 = new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\test\\config2");
	
	
	@Before
	public void initializeUserSpace(){
		mfile.mkdir();
		userSpace = new UserSpace();
	}
	
	@After
	public void destroyUserSpace(){
		userSpace = null;
		invaliduserSpace = null;
		mfile.delete();
	}
	
	
	public void setUpUserSpace(){
		userSpace.FileSetUserSpaceFile(validFile);
		userSpace.addConfiguration("valid1", config1);
		userSpace.addConfiguration("valid2", config2);
	}
	
	public void setUpInvalidUserSpace(){
		invaliduserSpace.FileSetUserSpaceFile(invalidFile);
		invaliduserSpace.addConfiguration("valid1", config1);
		invaliduserSpace.addConfiguration("valid2", config2);
	}
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////
	
	//These are the test for the FileSetUserSpace(File file) and getUserSpace() methods.
	
	/* 
	* Testing by setting a valid file.
	*/
	@Test
	public void testFileSetUserSpaceFileValidFile() {
		userSpace.FileSetUserSpaceFile(validFile);
		assertEquals(validFile,userSpace.getUserSpaceFile());
	}
	
	/* 
	* Testing that two different files don't equal.
	*/
	@Test
	public void testFileSetUserSpaceFileInValidFile() {
		invaliduserSpace = new UserSpace();
		invaliduserSpace.FileSetUserSpaceFile(invalidFile);
		assertNotEquals(validFile,invaliduserSpace.getUserSpaceFile());
	}
	
	/* 
	* Testing that two userSpaces mapped to the same file return the same file.
	*/
	@Test
	public void testFileSetUserSpaceFileValidFileClone() {
		cloneuserSpace = new UserSpace();
		cloneuserSpace.FileSetUserSpaceFile(validFile);
		assertEquals(validFile,cloneuserSpace.getUserSpaceFile());	
	}
	

//////////////////////////////////////////////////////////////////////////////////////////////////	
	
	// Below are the tests for addConfiguration(String configname, File file) 
	// and getConfiguration(String configName)
	
	/*
	 * Testing adding a valid Configuration 
	 */
	@Test
	public void addConfigurationValid(){
		userSpace.addConfiguration("valid1", config1);
		assertEquals(config1,userSpace.getConfiguration("valid1"));
	}
	
	/*
	 * Testing to retrieve a non added configuration
	 */
	@Test
	public void addConfigurationEmpty(){
		assertEquals(null,userSpace.getConfiguration("valid1"));
	}
	
	
	/*
	 * Testing adding two valid Configurations 
	 */
	@Test
	public void addConfigurationTwoValidPartI(){
		userSpace.addConfiguration("valid1", config1);
		userSpace.addConfiguration("valid2", config2);
		assertEquals(config1,userSpace.getConfiguration("valid1"));
	}
	
	/*
	 * Testing adding two valid Configurations 
	 */
	@Test
	public void addConfigurationTwoValidPartII(){
		userSpace.addConfiguration("valid1", config1);
		userSpace.addConfiguration("valid2", config2);
		assertEquals(config2,userSpace.getConfiguration("valid2"));
	}	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Below are the tests for getConfigurationsNames()

	/*
	 * Testing retrieving two config names
	 */
	@Test
	public void getConfigurationsNamesValidSize(){
		userSpace.addConfiguration("valid1", config1);
		userSpace.addConfiguration("valid2", config2);
		assertEquals(2,userSpace.getConfigurationsNames().size());
	}	
	
	
	/*
	 * Testing retrieving config names from empty userSpace
	 */
	@Test
	public void getConfigurationsNamesEmptyUserSpace(){
		assertEquals(0,userSpace.getConfigurationsNames().size());
	}
	

	
//////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Below are the tests save() and capture(String directoryname)

	/*
	 * Testing to save a valid userSpace
	 */
	@Test
	public void saveValid(){
		setUpUserSpace();
		userSpace.save();
		assertEquals(true,new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\test\\test.dat").exists());
		new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\test\\test.dat").delete();
	}	
	
	/*
	 * Testing to save an invalid UserSpace
	 */
	@Test(expected = NullPointerException.class)
	public void saveInValid(){
		setUpInvalidUserSpace();
		invaliduserSpace.save();
	}
	
	/*
	 * Testing to capture a valid UserSpace
	 */
	@Test
	public void captureValid(){
		setUpUserSpace();
		userSpace.save();
		UserSpace temp = new UserSpace();
		temp.FileSetUserSpaceFile(validFile);
		temp.capture("test");
		assertEquals(2,temp.getConfigurationsNames().size());
		new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\test\\test.dat").delete();
	}
	
	/*
	 * Testing to capture an invalid name or non existent file
	 */
	@Test
	public void captureinValid(){
		setUpUserSpace();
		userSpace.save();
		UserSpace temp = new UserSpace();
		temp.FileSetUserSpaceFile(validFile);
		assertEquals(false,temp.capture("testfake"));
		new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\test\\test.dat").delete();
	}
	
	
	
	
	
	
	
	
	

}
