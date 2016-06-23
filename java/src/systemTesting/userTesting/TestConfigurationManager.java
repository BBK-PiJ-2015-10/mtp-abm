package systemTesting.userTesting;

import java.io.File;
import java.util.List;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import configuration.ConfigurationManager;
import user.UserSpace;
import user.UserSpaceMaker;
import user.UserSpaceMakerImpl;

public class TestConfigurationManager {
	
	private String validAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user3\\config3";
	
	private File validFile = new File(validAddress);
	
	private ConfigurationManager validConfigMgr;
	
	private String invalidAddress = "bad stuff";
	
	private File invalidFile = new File(invalidAddress);
	
	private File empty;
	
	private ConfigurationManager invalidConfigMgr;
	
	@Ignore
	@Before
	public void initialize(){
		validConfigMgr = null;
	}
	
//////////////////////////////////////////////////////////////////////////////////////
	
	//These are the test for the constructor and getFile() method.
	
	/*
	 * Testing constructor with a valid file
	 */
	@Ignore
	@Test
	public void testConstructorWithValidFile(){
		validConfigMgr=new ConfigurationManager(validFile);
		assertEquals(validFile,validConfigMgr.getFile());
	}
	
	
	/*
	 * Testing constructor with a null file
	 */
	@Ignore
	@Test
	public void testConstructorWithNullFile(){
		validConfigMgr=new ConfigurationManager(null);
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
		validConfigMgr=new ConfigurationManager(null);
		validConfigMgr.setFile(validFile);
		assertEquals(validFile,validConfigMgr.getFile());
	}
	
	/*
	 * At creation a validFile, but then setting it to null.
	 */
	@Ignore
	@Test
	public void testsetFilePartII(){
		validConfigMgr=new ConfigurationManager(validFile);
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
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		assertEquals(false,validConfigMgr.getBPAFilesMap().isEmpty());
	}
	
	/*
	 * Testing loading a set of valid file maps and for size
	 */
	@Ignore
	@Test
	public void testloadFilesMapPartII(){
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		assertEquals(3,validConfigMgr.getBPAFilesMap().size());
	}
	
	/*
	 * Testing getting an empty BPAFilesMap
	 */
	@Ignore
	@Test
	public void testgetPBAFilesMapEmpty(){
		validConfigMgr=new ConfigurationManager(validFile);
		assertEquals(true,validConfigMgr.getBPAFilesMap().isEmpty());
	}
			
/////////////////////////////////////////////////////////////////////////////////////////
	
	//These are the tests for setGLFile and getGLFile
	
	
	/*
	 * Testing getting an empty BPAFilesMap
	 */
	@Ignore
	@Test(expected = NullPointerException.class)
	public void testgetGLFileEmpty(){
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		assertEquals(true,validConfigMgr.getGLFile().exists());
	}
	
	/*
	 * Testing getting a valid BPAFilesMap
	 */
	@Ignore
	@Test
	public void testgetGLFileValid(){
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		assertEquals(true,validConfigMgr.getGLFile().exists());
	}
	
	/*
	 * Testing getting an invalid file
	 */
	@Ignore
	@Test (expected = NullPointerException.class)
	public void testgetGLFileInValidName(){
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gll.csv");
		assertEquals(true,validConfigMgr.getGLFile().exists());
	}	
	
	
	/*
	 * Testing setting a null file
	 */
	@Ignore
	@Test (expected = NullPointerException.class)
	public void testsetGLFileNull(){
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile(null);
		assertEquals(true,validConfigMgr.getGLFile().exists());
	}
	
	
	/*
	 * Testing setting a non existent file
	 */
	@Ignore
	@Test (expected = NullPointerException.class)
	public void testsetGLFileNonExistent(){
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("non-existent");
		assertEquals(true,validConfigMgr.getGLFile().exists());
	}	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//These are the test for grabFilesAttributes(File file, Map map) and getBpaFilesAttributesMap()
	
	/*
	 * Testing a valid grabBPAFilesAttributes
	 */
	@Ignore
	@Test
	public void grabFilesAttributesvalid() {
		File temp = new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user3\\config3\\phones.csv");
		validConfigMgr=new ConfigurationManager(validFile);
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
		validConfigMgr=new ConfigurationManager(validFile);
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
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		assertEquals(true,validConfigMgr.getBpaFilesAttributesMap().isEmpty());
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	//Tests for grabFilesAttributes()
	
	/*
	 * Testing that is grabs BPA file
	 */
	@Ignore
	@Test
	public void testgrabFilesAttributesValidBPA() {
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		assertEquals(false,validConfigMgr.getBpaFilesAttributesMap().isEmpty());
	}
	
	/*
	 * Testing that is grabs BPA file
	 */
	@Ignore
	@Test
	public void testgrabFilesAttributesValidGL() {
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		assertEquals(false,validConfigMgr.getGlFilesAttributesMap().isEmpty());
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	//Tests for getGlFilesAttributesMap();

	/*
	 * Testing that missing GL returns an empty file.
	 */
	@Ignore
	@Test
	public void testgetGlFilesAttributesMapEmpty() {
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFilesAttributes();
		assertEquals(true,validConfigMgr.getGlFilesAttributesMap().isEmpty());
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Tests for readEntry(String input, String message,List<String> accumulator)
	
	/*
	 * Testing that missing GL returns an empty file.
	 */
	@Ignore
	@Test
	public void testReadEntryValidSelection() {
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		assertEquals(true,validConfigMgr.readEntry("phones.csv","Select one of the below options", accum,validConfigMgr.getBpaFilesAttributesMap()));
	}
	
	/*
	 * Testing readEntry and asking to make a wrong selection
	 */
	@Ignore
	@Test
	public void testReadEntryInValidSelection() {
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		assertEquals(false,validConfigMgr.readEntry("phones.csv","Type something else", accum,validConfigMgr.getBpaFilesAttributesMap()));
	
	}
	
	/*
	 * Testing readEntry with a null input file.
	 */
	@Ignore
	@Test (expected = NullPointerException.class)
	public void testReadEntryNullfile() {
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		assertEquals(true,validConfigMgr.readEntry(null,"type something",accum,validConfigMgr.getBpaFilesAttributesMap()));
	}
	
	/*
	 * Testing readEntry with a null accumulator List
	 */
	@Ignore
	@Test (expected = NullPointerException.class)
	public void testReadEntryNullAccumulator() {
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		assertEquals(true,validConfigMgr.readEntry("phones.csv","type one of the below",null,validConfigMgr.getBpaFilesAttributesMap()));
	}
	
	/*
	 * Testing readEntry with a null map
	 */
	//@Ignore
	@Test(expected = NullPointerException.class)
	public void testReadEntryNullMap() {
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		assertEquals(false,validConfigMgr.readEntry("phones.csv","this won't let you type", accum,null));
	}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Tests for loadBpafilesMainAttributes() and getBpaMainFilesAttributesMap()

	/*
 	* Testing if it returns an empty map.
	*/
	@Ignore
	@Test
	public void testgetBpaMainFilesAttributesMapEmpty() {	
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		assertEquals(true,validConfigMgr.getBpaMainFilesAttributesMap().isEmpty());
	}
	
	/*
 	* Testing that it returns a populated map
	*/
	@Ignore
	@Test
	public void testLoadBpafilesMainAttributesValid() {	
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		validConfigMgr.loadBpaFilesMainAttributes();
		assertEquals(false,validConfigMgr.getBpaMainFilesAttributesMap().isEmpty());
	}
	
	/*
 	* Testing that it returns a populated map and test its size.
	*/
	@Ignore
	@Test
	public void testLoadBpafilesMainAttributesValidSize () {	
		//validConfigMgr=new ConfigurationManager(validFile);
		//validConfigMgr.loadFilesMap();
		//validConfigMgr.setGLFile("gl.csv");
		//validConfigMgr.grabFilesAttributes();
		//validConfigMgr.loadBpaFilesMainAttributes();
		assertEquals(2,validConfigMgr.getBpaMainFilesAttributesMap().size());
	}
	
	/*
 	* Testing that it returns a populated map and test the size of one element.
	*/
	@Ignore
	@Test
	public void testLoadBpafilesMainAttributesValidElementSize () {	
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		validConfigMgr.loadBpaFilesMainAttributes();
		assertEquals(2,validConfigMgr.getBpaMainFilesAttributesMap().get("phones.csv").size());
	}	
	
	
	
	
	
	
}
