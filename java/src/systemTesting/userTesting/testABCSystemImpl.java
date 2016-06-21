package systemTesting.userTesting;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.ABCSystemImpl;

import org.junit.Before;
import org.junit.After;
import org.junit.Ignore;

import java.io.File;


import java.util.Scanner;



public class testABCSystemImpl {
	
	private ABCSystemImpl system1 = new ABCSystemImpl();
	
	private Scanner sc = new Scanner(System.in);
	
	String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\";
	
	@Before
	public void initialize(){
	}
	
	@After
	public void shutoff(){
	}
	
	
	
	/* 
	* Testing creating a validUserSpace
	*/
	@Test
	public void testrunMakeNewUserSpace() {	
		system1.runMakeNewUserSpace();
		System.out.println("Please enter the name again");
		String name=sc.nextLine();
		File temp = new File(address+name);
		assertEquals(true,temp.isDirectory());
		new File(address+name+"\\"+name+".dat").delete();
		temp.delete();
	}
	
	
	

}
