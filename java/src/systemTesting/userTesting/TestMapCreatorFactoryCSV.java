package systemTesting.userTesting;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import configuration.MapCreatorFactoryAbstract;
import configuration.MapCreatorFactoryCSV;
import configuration.MapCreatorAbstract;

public class TestMapCreatorFactoryCSV {
	
	private MapCreatorFactoryAbstract factory;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	@Before
	public void initializeFactory(){
		setUpStreams();
		factory = new MapCreatorFactoryCSV();
	}
	
	
	@After
	public void shotdownFactory(){
		factory = null;	
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////	
	
	//Below are the tests for the getMapCreatorOptions and loadOptions methods

	/*
	* The below tests that a factory with no loaded options will have a MapCreatorOptions
	* data structure with no elements
	*/
	//@Ignore
	@Test
	public void testGetMapCreatorOptionsEmptyOptions() {	
		assertEquals(true,factory.getMapCreatorOptions().isEmpty());
	}
	
	/*
	* The below tests that a factory with loaded options will have a MapCreatorOptions
	* data structure that has elements
	*/
	//@Ignore
	@Test
	public void testGetMapCreatorOptionsNonEmptyOptions() {	
		factory.loadOptions();
		assertEquals(false,factory.getMapCreatorOptions().isEmpty());
	}
	
	
	/*
	* The below tests for the completeness of the loadOptions method
	* by testing the content that was loaded
	*/
	//@Ignore
	@Test
	public void testLoadOptionsContent() {	
		factory.loadOptions();
		assertEquals(2,factory.getMapCreatorOptions().size());
		assertEquals("this application",factory.getMapCreatorOptions().get(1));
		assertEquals("a manual table reference",factory.getMapCreatorOptions().get(2));
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////	
	
	//Below are the tests for the loadOptions method	
	
	/*
	* The below tests the screen output of a loadOptions call on a factory
	*/
	//@Ignore
	@Test
	public void testLoadOptionsValid() {	
		String test1 = "For mapping the GL to BPA via this application please type: 1";
		String test2 = "For mapping the GL to BPA via a manual table reference please type: 2";
		assertEquals(true,factory.presentChoices());
		assertEquals(test1,outContent.toString().trim().substring(0, test1.length()));
		String content2 = outContent.toString().trim().substring(test1.length()+2,outContent.toString().trim().length());
		assertEquals(test2,content2);
		assertEquals(test1.length()+test2.length(),outContent.toString().trim().length()-2);
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////	
	
	//Below are the tests for the getMapCreator method	

	/*
	* The below tests for the completeness of the getMapCreator by calling it with different
	* input choices
	*/
	//@Ignore
	@Test
	public void testgetMapCreatorValidCompleteness1() {	
		assertEquals("configuration.MapCreatorImplCSV",factory.getMapCreator(1).getClass().getName());
		assertEquals("configuration.MapCreatorImplCSV",factory.getMapCreator(2).getClass().getName());
		assertEquals(null,factory.getMapCreator(3));
		assertEquals(null,factory.getMapCreator(100));
	}
	
	/*
	* The below tests for the completeness of the getMapCreator by testing the contents of the
	* options data structure before and after calling the getMapCreator Method
	*/
	//@Ignore
	@Test
	public void testgetMapCreatorValidCompleteness2() {	
		assertEquals(true,factory.getMapCreatorOptions().isEmpty());
		factory.getMapCreator(1);
		assertEquals(false,factory.getMapCreatorOptions().isEmpty());
	}
	
	/*
	* The below tests for the accuracy of the getMapCreator by inspection the elements of each
	* MapCreator returned object
	* 
	*/
	//@Ignore
	@Test
	public void testgetMapCreatorInValid() {	
		assertEquals(false,((MapCreatorAbstract)factory.getMapCreator(1)).getFlag());
		assertEquals(true,((MapCreatorAbstract)factory.getMapCreator(2)).getFlag());
	}
	

	
	
	

}
