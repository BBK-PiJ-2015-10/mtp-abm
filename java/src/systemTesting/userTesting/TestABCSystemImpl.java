package systemTesting.userTesting;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.ABCSystemImpl;

import org.junit.Before;
import org.junit.After;
import org.junit.Ignore;

import java.io.File;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class TestABCSystemImpl {
	
	private ABCSystemImpl system1;
	
	private ByteArrayInputStream auto;
	
	private Scanner sc;
	
	private String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\";
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}	
	
	
	public void manualFeedSetUp(){
		sc = new Scanner(System.in);
		system1 = new ABCSystemImpl(sc);
	}
	
	public void autoFeedSetUp(String message){
		auto = new ByteArrayInputStream(message.getBytes());
		System.setIn(auto);
		sc = new Scanner(System.in);
		system1 = new ABCSystemImpl(sc);
	}
	
	
	/* 
	* Testing creating a validUserSpace
	*/
	//@Ignore
	@Test
	public void testrunMakeNewUserSpaceAuto() {	
		autoFeedSetUp("user10");
		system1.runMakeNewUserSpace();
		String name = "user10";
		File temp = new File(address+name);
		assertEquals(true,temp.isDirectory());
		new File(address+name+"\\"+name+".dat").delete();
		temp.delete();
	}
	
	

}
