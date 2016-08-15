package systemTesting.userTesting;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import configuration.ConfigurationManagerCSV;
import configuration.ConfigurationManagerSQL;
import configuration.ConfigurationMapper;
import configuration.ConfigurationMapperImplCSV;

import old.*;

import sqlimpl.*;
import user.UserSpace;
import user.UserSpaceMaker;
import user.UserSpaceMakerImpl;

public class TestConfigurationManagerSQL {
	
	private String validAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user10\\config10";
	
	private File validFile = new File(validAddress);
	
	private ConfigurationManagerSQL validConfigMgr;
	
	private String invalidAddress = "bad stuff";
	
	private File invalidFile = new File(invalidAddress);
	
	private File empty;
	
	private ConfigurationManagerSQL invalidConfigMgr;
	
	private Scanner sc;
	
	private ByteArrayInputStream auto;
	
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
		
	public void manualFeedSetUp(){
		sc = new Scanner(System.in);
	}
	

	//@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
    //@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}	
	
	
	

	
//////////////////////////////////////////////////////////////////////////////////////
	
	//These are the test for the constructor and getFile() method.
	
	/*
	 * Testing constructor with a valid file
	 */
	@Ignore
	@Test
	public void testConstructorWithValidFile(){
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		assertEquals(validFile,validConfigMgr.getFile());
	}
	
	
	/*
	 * Testing constructor with a null file
	 */
	@Ignore
	@Test
	public void testConstructorWithNullFile(){
		//validConfigMgr=new ConfigurationManager(null);
		assertEquals(null,validConfigMgr.getFile());
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////
	
	//These are the test for the setFile(File file)
	
	/*
	 * At creation null, but then setting to a validFile
	 */
	@Ignore
	@Test
	public void testsetFilePartI(){
		//validConfigMgr=new ConfigurationManager(null);
		validConfigMgr.setFile(validFile);
		assertEquals(validFile,validConfigMgr.getFile());
	}
	
	/*
	 * At creation a validFile, but then setting it to null.
	 */
	@Ignore
	@Test
	public void testsetFilePartII(){
		//validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.setFile(null);
		assertNotEquals(validFile,validConfigMgr.getFile());
	}	
	
	
/////////////////////////////////////////////////////////////////////////////////////////
	
	//These are the test for loadFilesMap() and getBPAFilesMap()
	
	/*
	 * Testing loading a set of valid file maps and testing not empty
	 */
	@Ignore
	@Test
	public void testloadFilesMapPartI(){
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		assertEquals(false,validConfigMgr.getBPAFilesMap().isEmpty());
	}
	
	/*
	 * Testing getting an empty BPAFilesMap
	 */
	@Ignore
	@Test
	public void testgetPBAFilesMapEmpty(){
		//validConfigMgr=new ConfigurationManager(validFile);
		assertEquals(true,validConfigMgr.getBPAFilesMap().isEmpty());
	}
			
/////////////////////////////////////////////////////////////////////////////////////////
	
	//These are the tests for setGLFile and getGLFile
	
	

	

	
	


	

	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//These are the test for grabFilesAttributes(File file, Map map) and getBpaFilesAttributesMap()
	
	/*
	 * Testing a valid grabBPAFilesAttributes
	 */
	@Ignore
	@Test
	public void testgrabFilesAttributesvalid() {
		File temp = new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user10\\config10\\phones.csv");
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFileAttributes(temp, validConfigMgr.getBpaFilesAttributesMap());
		assertEquals(false,validConfigMgr.getBpaFilesAttributesMap().isEmpty());
	}
	
	
	/*
	 * Testing a valid grabBPAFilesAttributes passing an empty file
	 */
	@Ignore
	@Test (expected = NullPointerException.class)
	public void grabFilesAttributesvalidEmptyFile() {
		File temp = null;
		//validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFileAttributes(null, validConfigMgr.getBpaFilesAttributesMap());
	}
	
	/*
	 * Testing a valid grabBPAFilesAttributes passing an empty file
	 */
	@Ignore
	@Test (expected = NullPointerException.class)
	public void grabFilesAttributesvalidEmptyMap() {
		validConfigMgr.grabFileAttributes(null, null);
	}
	
	/*
	 * Testing that when not loaded you get an empty file back
	 */
	@Ignore
	@Test
	public void getBpaFilesAttributesMapEmpty() {
		File temp = new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user3\\config3\\phones.csv");
		//validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		assertEquals(true,validConfigMgr.getBpaFilesAttributesMap().isEmpty());
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	//Tests for grabFilesAttributes()

	

	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	//Tests for getGlFilesAttributesMap();

	/*
	 * Testing that missing GL returns an empty file.
	 */
	@Ignore
	@Test
	public void testgetGlFilesAttributesMapEmpty() {
		//validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFilesAttributes();
		assertEquals(true,validConfigMgr.getGlFilesAttributesMap().isEmpty());
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Tests for readEntry(String input, String message,List<String> accumulator)
	

	

	

	

	
	




	/*
 	* Testing that bpaFilesMap returned element is of size 2
	*/
	@Ignore
	@Test
	public void testcaptureValidbpaFilesMap () {	
		//validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.capture("config10");
		assertEquals(2,validConfigMgr.getBPAFilesMap().size());
	}	
	
	
	/*
 	* Testing that bpaFilesAttributesMap returned element is of size 4
	*/
	@Ignore
	@Test
	public void testcaptureValidbpaFilesAttributesMap () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		assertEquals(4,validConfigMgr.getBpaFilesAttributesMap().get("phones.csv").size());
	}
	
	/*
 	* Testing that bpaFilesAttributesMap returned element is of size 4
	*/
	@Ignore
	@Test
	public void testcaptureValidbpaFilesMainAttributesMap () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		assertEquals(2,validConfigMgr.getBpaMainFilesAttributesMap().get("phones.csv").size());
	}
	
	/*
 	* Testing that GlFilesAttributesMap returned element is of size 4
	*/
	@Ignore
	@Test
	public void testcaptureValidGlFilesAttributesMap () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		assertEquals(4,validConfigMgr.getGlFilesAttributesMap().get("gl.csv").size());
	}
	
	/*
 	* Testing that GlFilesAttributesMap returned element is of size 4
	*/
	@Ignore
	@Test
	public void testcaptureValidGlMainFilesAttributesMap () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		assertEquals(3,validConfigMgr.getGlMainFilesAttributesMap().get("gl.csv").size());
	}	
	

///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
    //Tests for save(String newname)
	
	/*
	* Saving a file with a different name, then retrieving and testing its existence
	*/
	@Ignore
	@Test
	public void testsaveValidExistance () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		validConfigMgr.save("newone");
		assertEquals(true,Arrays.asList(validConfigMgr.getFile().list()).contains("newone.dat"));
		String tempaddress = validConfigMgr.getFile().getAbsolutePath()+"\\"+"newone.dat";
		File temp = new File(tempaddress);
		temp.delete();		
	}	
	

	
	/*
	* Saving a file with a different name, then retrieving and testing its contents
	*/
	@Ignore
	@Test
	public void testsaveValidContentsII () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		validConfigMgr.save("newone");
		ConfigurationManagerSQL tempconfig=new ConfigurationManagerSQL(validFile);
		tempconfig.capture("newone");
		assertEquals(2,validConfigMgr.getBPAFilesMap().size());
		String tempaddress = validConfigMgr.getFile().getAbsolutePath()+"\\"+"newone.dat";
		File temp = new File(tempaddress);
		temp.delete();		
	}
	
	/*
	* Saving a file with a different name, then retrieving and testing its contents
	*/
	@Ignore
	@Test
	public void testsaveValidContentsIII () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		validConfigMgr.save("newone");
		ConfigurationManagerSQL tempconfig=new ConfigurationManagerSQL(validFile);
		tempconfig.capture("newone");
		assertEquals(4,validConfigMgr.getBpaFilesAttributesMap().get("phones.csv").size());
		String tempaddress = validConfigMgr.getFile().getAbsolutePath()+"\\"+"newone.dat";
		File temp = new File(tempaddress);
		temp.delete();		
	}
	
	/*
	* Saving a file with a different name, then retrieving and testing its contents
	*/
	@Ignore
	@Test
	public void testsaveValidContentsIV () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		validConfigMgr.save("newone");
		ConfigurationManagerSQL tempconfig=new ConfigurationManagerSQL(validFile);
		tempconfig.capture("newone");
		assertEquals(2,validConfigMgr.getBpaMainFilesAttributesMap().get("phones.csv").size());
		String tempaddress = validConfigMgr.getFile().getAbsolutePath()+"\\"+"newone.dat";
		File temp = new File(tempaddress);
		temp.delete();		
	}
	
	/*
	* Saving a file with a different name, then retrieving and testing its contents
	*/
	@Ignore
	@Test
	public void testsaveValidContentsV () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		validConfigMgr.save("newone");
		ConfigurationManagerSQL tempconfig=new ConfigurationManagerSQL(validFile);
		tempconfig.capture("newone");
		assertEquals(4,validConfigMgr.getGlFilesAttributesMap().get("gl.csv").size());
		String tempaddress = validConfigMgr.getFile().getAbsolutePath()+"\\"+"newone.dat";
		File temp = new File(tempaddress);
		temp.delete();		
	}
	
	/*
	* Saving a file with a different name, then retrieving and testing its contents
	*/
	@Ignore
	@Test
	public void testsaveValidContentsVI () {	
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.capture("config10");
		validConfigMgr.save("newone");
		ConfigurationManagerSQL tempconfig=new ConfigurationManagerSQL(validFile);
		tempconfig.capture("newone");
		assertEquals(3,validConfigMgr.getGlMainFilesAttributesMap().get("gl.csv").size());
		String tempaddress = validConfigMgr.getFile().getAbsolutePath()+"\\"+"newone.dat";
		File temp = new File(tempaddress);
		temp.delete();		
	}
	
	
	///These are the test for ConfigurationManagerSQL
	
	//@Ignore
	@Test
	public void tester () {
		validConfigMgr=new ConfigurationManagerSQL(validFile);
		validConfigMgr.loadFilesMap();
		manualFeedSetUp();
		validConfigMgr.captureGLConnectionSettings(sc);
		//validConfigMgr.setUpGLConnection();
		validConfigMgr.grabFilesAttributes();
		validConfigMgr.loadGlFilesMainAttributes(sc);
		validConfigMgr.loadBpaFilesMainAttributes(sc);
		validConfigMgr.save();
		
		System.out.println(validConfigMgr.getBpaFilesAttributesMap().size());
		
		ConfigurationManagerSQL testC = new ConfigurationManagerSQL(validFile);
		System.out.println("versus");
		System.out.println(testC.getBpaFilesAttributesMap().size());
		
		testC.capture("config10");
		System.out.println("versus again");
		System.out.println(testC.getBpaFilesAttributesMap().size());
		
		
		//ConfigurationMapper configMapper = new ConfigurationMapperImplSQL(validConfigMgr);
		//configMapper.mapFiles(sc);
		
		
		//Need to test running a map creator. Let's start with user10
		
		//validConfigMgr.getGlFilesAttributesMap().get("smallgl").forEach(System.out::println);
		//validConfigMgr.getGlFilesAttributesMap().keySet().forEach(System.out::println);
		//validConfigMgr.getl
		
		
		//assertEquals(true,validConfigMgr.captureGLConnectionSettings(sc));
		
		//assertEquals(3,3);
		
	}
	
	

	
}
