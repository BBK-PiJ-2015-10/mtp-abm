package systemTesting.userTesting;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.ABCSystemImpl;

import org.junit.Before;
import org.junit.After;
import org.junit.Ignore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import user.UserSpace;
import user.UserSpaceMaker;
import user.UserSpaceMakerImpl;

import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class TestCreateUserSpaceMakerImpl {
	
	
	private UserSpace userSpace;
	
	private UserSpace cloneuserSpace;
	
	private UserSpace invaliduserSpace;
	
	private Scanner sc;
	
	private ByteArrayInputStream auto;
	
	private String validAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\";
	
	private String invalidAddress = "this is not valid";
	
	private File mfile = new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\test");
	
	private File validFile = new File(validAddress);
	
	private File invalidFile = new File(invalidAddress);
	
	private File config1 = new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\test\\config1");
	
	private File config2 = new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\test\\config2");
	
	private File config3 = new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\test\\config2");
	
	private UserSpaceMaker userSpaceMaker1;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	public void autoFeedSetUp(String message){
		auto = new ByteArrayInputStream(message.getBytes());
		System.setIn(auto);
		sc = new Scanner(System.in);
	}
	
	public void autoFeedSetUpFile(String filename){
		try {
			sc = new Scanner(new FileReader(filename));
		} catch (FileNotFoundException ex){
			System.out.println("File " + filename + " does not exist");
		} catch (IOException ex){
			ex.printStackTrace();
		}	
	}
	
	
	
	
	@Before
	public void initializeUserSpace(){
		userSpaceMaker1 = new UserSpaceMakerImpl();
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
		
	}
	
	@After
	public void shutoffUserSpace(){
		userSpaceMaker1 = null;
	    System.setOut(null);
	    System.setErr(null);
	}
	
	
	
	/* 
	* Testing creating a validUserSpace
	*/
	@Test
	public void testFileSetUserSpaceFileValidFileAuto() {
		autoFeedSetUpFile("testuserspacemaker1.txt");
		userSpaceMaker1.createUserSpace(new UserSpace(),sc);
		System.out.println("Enter the name again");
		String value = sc.nextLine();
		assertEquals(true,new File(validAddress+value).exists());
		new File(validAddress+value+"//"+value+".dat").delete();
		new File(validAddress+value).delete();
	}
	
	/* 
	* Testing passing an uninitizilized userSpace
	*/
	//@Ignore
	@Test(expected = NullPointerException.class)
	public void testFileSetUserSpaceFileInValidFileAuto() {
		userSpaceMaker1.createUserSpace(userSpace,sc);
	}

	
	

}
