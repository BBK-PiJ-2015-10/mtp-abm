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

public class TestABCSystemImpl {
	
	private ABCSystemImpl system1;
	
	private ByteArrayInputStream auto;
	
	private Scanner sc;
	
	private String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\";
	
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
	* */
	@Ignore
	@Test
	public void testrunMakeNewUserSpace() {	
		manualFeedSetUp();
		system1.runMakeNewUserSpace();
		System.out.println("Please enter the name again");
		String name=sc.nextLine();
		File temp = new File(address+name);
		assertEquals(true,temp.isDirectory());
		new File(address+name+"\\"+name+".dat").delete();
		temp.delete();
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
