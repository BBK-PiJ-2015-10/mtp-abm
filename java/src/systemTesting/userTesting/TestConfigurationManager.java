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

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import java.util.Set;

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
	
	private String validAddress = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user10\\config10";
	
	private File validFile = new File(validAddress);
	
	private ConfigurationManager validConfigMgr;
	
	private String invalidAddress = "bad stuff";
	
	private File invalidFile = new File(invalidAddress);
	
	private File empty;
	
	private ConfigurationManager invalidConfigMgr;
	
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
	
	
	

	
//////////////////////////////////////////////////////////////////////////////////////
	
	//These are the test for the constructor and getFile() method.
	
	/*
	 * Testing constructor with a valid file
	 */
	//@Ignore
	@Test
	public void testConstructorWithValidFile(){
		validConfigMgr=new ConfigurationManager(validFile);
		assertEquals(validFile,validConfigMgr.getFile());
	}
	
	
	/*
	 * Testing constructor with a null file
	 */
	//@Ignore
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
	//@Ignore
	@Test
	public void testsetFilePartI(){
		validConfigMgr=new ConfigurationManager(null);
		validConfigMgr.setFile(validFile);
		assertEquals(validFile,validConfigMgr.getFile());
	}
	
	/*
	 * At creation a validFile, but then setting it to null.
	 */
	//@Ignore
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
	//@Ignore
	@Test
	public void testloadFilesMapPartI(){
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		assertEquals(false,validConfigMgr.getBPAFilesMap().isEmpty());
	}
	
	/*
	 * Testing loading a set of valid file maps and for size
	 */
	//@Ignore
	@Test
	public void testloadFilesMapPartII(){
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		assertEquals(2,validConfigMgr.getBPAFilesMap().size());
	}
	
	/*
	 * Testing getting an empty BPAFilesMap
	 */
	//@Ignore
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
	//@Ignore
	@Test(expected = NullPointerException.class)
	public void testgetGLFileEmpty(){
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		assertEquals(true,validConfigMgr.getGLFile().exists());
	}
	
	/*
	 * Testing getting a valid BPAFilesMap
	 */
	//@Ignore
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
	//@Ignore
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
	//@Ignore
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
	//@Ignore
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
	//@Ignore
	@Test
	public void grabFilesAttributesvalid() {
		File temp = new File("C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user10\\config10\\phones.csv");
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.grabFileAttributes(temp, validConfigMgr.getBpaFilesAttributesMap());
		assertEquals(false,validConfigMgr.getBpaFilesAttributesMap().isEmpty());
	}
	
	
	/*
	 * Testing a valid grabBPAFilesAttributes passing an empty file
	 */
	//@Ignore
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
	//@Ignore
	@Test (expected = NullPointerException.class)
	public void grabFilesAttributesvalidEmptyMap() {
		validConfigMgr.grabFileAttributes(null, null);
	}
	
	/*
	 * Testing that when not loaded you get an empty file back
	 */
	//@Ignore
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
	//@Ignore
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
	//@Ignore
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
	//@Ignore
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
	//@Ignore
	@Test
	public void testReadEntryValidSelection() {
		autoFeedSetUp("pclient");
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		assertEquals(true,validConfigMgr.readEntry("phones.csv","Select one of the below options", accum,validConfigMgr.getBpaFilesAttributesMap(),1,sc));
	}
	
	/*
	 * Testing readEntry and asking to make a wrong selection
	 */
	//@Ignore
	@Test
	public void testReadEntryInValidSelection() {
		autoFeedSetUp("invalid");
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		assertEquals(false,validConfigMgr.readEntry("phones.csv","Type something else", accum,validConfigMgr.getBpaFilesAttributesMap(),1,sc));
	}
	
	/*
	 * Testing readEntry with a null input file.
	 */
	//@Ignore
	@Test (expected = NullPointerException.class)
	public void testReadEntryNullfile() {
		autoFeedSetUp("something irrelevant");
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		assertEquals(true,validConfigMgr.readEntry(null,"type something",accum,validConfigMgr.getBpaFilesAttributesMap(),1,sc));
	}
	
	/*
	 * Testing readEntry with a null accumulator List
	 */
	//@Ignore
	@Test (expected = NullPointerException.class)
	public void testReadEntryNullAccumulator() {
		autoFeedSetUp("pclient");
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		assertEquals(true,validConfigMgr.readEntry("phones.csv","type one of the below",null,validConfigMgr.getBpaFilesAttributesMap(),1,sc));
	}
	
	/*
	 * Testing readEntry with a null map
	 */
	//@Ignore
	@Test(expected = NullPointerException.class)
	public void testReadEntryNullMap() {
		autoFeedSetUp("pclient");
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		assertEquals(false,validConfigMgr.readEntry("phones.csv","ignore this sentence", accum,null,1,sc));
	}
	
	/*
	 * Testing readEntry and asking user to enter 2 entries instead of one.
	 */
	//@Ignore
	@Test
	public void testReadEntryCounter() {
		autoFeedSetUp("pclient pcalls");
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		assertEquals(false,validConfigMgr.readEntry("phones.csv","select two valid entries", accum,validConfigMgr.getBpaFilesAttributesMap(),1,sc));
	}
	
	
	/*
	 * Testing readEntry and setting maxEntry to 0
	 */
	//@Ignore
	@Test
	public void testReadEntryZeroCounter() {
		autoFeedSetUp("pcalls");
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		assertEquals(false,validConfigMgr.readEntry("phones.csv","select a valid entry", accum,validConfigMgr.getBpaFilesAttributesMap(),0,sc));
	}
	
	/*
	 * Testing readEntry and setting maxEntry to 0
	 */
	//@Ignore
	@Test
	public void testReadEntryValidMultipleArguments() {
		autoFeedSetUp("pclient pcalls");
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		assertEquals(true,validConfigMgr.readEntry("phones.csv","select two valid entries", accum,validConfigMgr.getBpaFilesAttributesMap(),2,sc));
	}
	
	
	/*
	 * Testing readEntry and requesting to enter one invalid and one valid entry.
	 */
	//@Ignore
	@Test
	public void testReadEntryValidMultipleArgumentsOneValidOneInvalid() {
		autoFeedSetUp("biruta pclient");
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		assertEquals(false,validConfigMgr.readEntry("phones.csv","select one invalid and one valid", accum,validConfigMgr.getBpaFilesAttributesMap(),2,sc));
	}
	
	/*
	 * Testing readEntry and requesting to enter one invalid and one valid entry.
	 */
	//@Ignore
	@Test
	public void testReadEntryValidMultipleArgumentsOneInValidOneValid() {
		autoFeedSetUp("pclient biruta");
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		assertEquals(false,validConfigMgr.readEntry("phones.csv","select one valid and one invalid", accum,validConfigMgr.getBpaFilesAttributesMap(),2,sc));
	}
	
	/*
	 * Testing readEntry and requesting to input two invalid entries.
	 */
	//@Ignore
	@Test
	public void testReadEntryValidMultipleArgumentsTwoInValid() {
		autoFeedSetUp("ale biruta");
		List<String> accum= new LinkedList<>();
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		assertEquals(false,validConfigMgr.readEntry("phones.csv","select two invalid", accum,validConfigMgr.getBpaFilesAttributesMap(),2,sc));
	}
	
	
	


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Tests for loadBpafilesMainAttributes() and getBpaMainFilesAttributesMap()

	/*
 	* Testing if it returns an empty map.
	*/
	//@Ignore
	@Test
	public void testgetBpaMainFilesAttributesMapEmpty() {	
		autoFeedSetUp("");
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		assertEquals(true,validConfigMgr.getBpaMainFilesAttributesMap().isEmpty());
	}
	
	/*
 	* Testing that it returns a populated map
	*/
	//@Ignore
	@Test
	public void testLoadBpafilesMainAttributesValid() {	
		autoFeedSetUpFile("testconfigmgr1.txt");
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		validConfigMgr.loadBpaFilesMainAttributes(sc);
		assertEquals(false,validConfigMgr.getBpaMainFilesAttributesMap().isEmpty());
	}
		
	
	/*
 	* Testing that it returns a populated map and test its size.
	*/
	//@Ignore
	@Test
	public void testLoadBpafilesMainAttributesValidSize () {		
		autoFeedSetUpFile("testconfigmgr1.txt");
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		validConfigMgr.loadBpaFilesMainAttributes(sc);
		assertEquals(2,validConfigMgr.getBpaMainFilesAttributesMap().size());
	}
	
	/*
 	* Testing that it returns a populated map and test the size of one element.
	*/
	//@Ignore
	@Test
	public void testLoadBpafilesMainAttributesValidElementSize () {	
		autoFeedSetUpFile("testconfigmgr1.txt");
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		validConfigMgr.loadBpaFilesMainAttributes(sc);
		assertEquals(2,validConfigMgr.getBpaMainFilesAttributesMap().get("phones.csv").size());
	}	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Tests for loadGlfilesMainAttributes() and getGlMainFilesAttributesMap()
	
	
	/*
 	* Testing that it returns a populated map and test the size.
	*/
	//@Ignore
	@Test
	public void testLoadGlfilesMainAttributesValidElementSize () {	
		autoFeedSetUpFile("testconfigmgr2.txt");
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();
		System.out.println("Please select exactly 3 attributes in total ");
		validConfigMgr.loadGlFilesMainAttributes(sc);	
		assertEquals(4,validConfigMgr.getGlMainFilesAttributesMap().get("gl.csv").size());
	}
	
	/*
 	* Testing that if not loaded, it will return a null value.
	*/
	//@Ignore
	@Test
	public void testGlMainFilesAttributesMapNullFile () {	
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.loadFilesMap();
		validConfigMgr.setGLFile("gl.csv");
		validConfigMgr.grabFilesAttributes();	
		assertEquals(null,validConfigMgr.getGlMainFilesAttributesMap().get("gl.csv"));
	}	
	

	
//////////////////////////////////////////////////////////////////////////////////////////////////
	
      //Tests for capture(String configurationname)
	
	/*
 	* Testing that method return true if input is a file that exists.
	*/
	//@Ignore
	@Test
	public void testcaptureValidInput () {	
		validConfigMgr=new ConfigurationManager(validFile);
		assertEquals(true,validConfigMgr.capture("config10"));
	}
	
	/*
 	* Testing that method return false if name provided doesn't really exist in file.
	*/
	//@Ignore
	@Test
	public void testcaptureInValidInputName () {	
		validConfigMgr=new ConfigurationManager(validFile);
		assertEquals(false,validConfigMgr.capture("config33"));
	}
	
	/*
 	* Testing that method return false if the file doesn't really exist.
	*/
	//@Ignore
	@Test
	public void testcaptureInValidInputNonExistentDocument () {	
		invalidConfigMgr=new ConfigurationManager(invalidFile);
		assertEquals(false,invalidConfigMgr.capture("bad stuff"));
	}	
	
	
	/*
 	* Testing that file returned element name is config3
	*/
	//@Ignore
	@Test
	public void testcaptureValidFile() {	
		validConfigMgr=new ConfigurationManager(validFile);
		assertEquals("config10",validConfigMgr.getFile().getName());
	}
	
	/*
 	* Testing that glfile returned element name is gl.csv
	*/
	//@Ignore
	@Test
	public void testcaptureValidGLFile () {	
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.capture("config10");
		assertEquals("gl.csv",validConfigMgr.getGLFile().getName());
	}

	/*
 	* Testing that bpaFilesMap returned element is of size 2
	*/
	//@Ignore
	@Test
	public void testcaptureValidbpaFilesMap () {	
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.capture("config10");
		assertEquals(2,validConfigMgr.getBPAFilesMap().size());
	}	
	
	
	/*
 	* Testing that bpaFilesAttributesMap returned element is of size 4
	*/
	//@Ignore
	@Test
	public void testcaptureValidbpaFilesAttributesMap () {	
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.capture("config10");
		assertEquals(4,validConfigMgr.getBpaFilesAttributesMap().get("phones.csv").size());
	}
	
	/*
 	* Testing that bpaFilesAttributesMap returned element is of size 4
	*/
	//@Ignore
	@Test
	public void testcaptureValidbpaFilesMainAttributesMap () {	
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.capture("config10");
		assertEquals(2,validConfigMgr.getBpaMainFilesAttributesMap().get("phones.csv").size());
	}
	
	/*
 	* Testing that GlFilesAttributesMap returned element is of size 4
	*/
	//@Ignore
	@Test
	public void testcaptureValidGlFilesAttributesMap () {	
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.capture("config10");
		assertEquals(4,validConfigMgr.getGlFilesAttributesMap().get("gl.csv").size());
	}
	
	/*
 	* Testing that GlFilesAttributesMap returned element is of size 4
	*/
	//@Ignore
	@Test
	public void testcaptureValidGlMainFilesAttributesMap () {	
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.capture("config10");
		assertEquals(3,validConfigMgr.getGlMainFilesAttributesMap().get("gl.csv").size());
	}	
	

///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
    //Tests for save(String newname)
	
	/*
	* Saving a file with a different name, then retrieving and testing its existence
	*/
	//@Ignore
	@Test
	public void testsaveValidExistance () {	
		validConfigMgr=new ConfigurationManager(validFile);
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
	//@Ignore
	@Test
	public void testsaveValidContentsI () {	
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.capture("config10");
		validConfigMgr.save("newone");
		ConfigurationManager tempconfig=new ConfigurationManager(validFile);
		tempconfig.capture("newone");
		assertEquals("gl.csv",tempconfig.getGLFile().getName());
		String tempaddress = validConfigMgr.getFile().getAbsolutePath()+"\\"+"newone.dat";
		File temp = new File(tempaddress);
		temp.delete();		
	}
	
	/*
	* Saving a file with a different name, then retrieving and testing its contents
	*/
	//@Ignore
	@Test
	public void testsaveValidContentsII () {	
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.capture("config10");
		validConfigMgr.save("newone");
		ConfigurationManager tempconfig=new ConfigurationManager(validFile);
		tempconfig.capture("newone");
		assertEquals(2,validConfigMgr.getBPAFilesMap().size());
		String tempaddress = validConfigMgr.getFile().getAbsolutePath()+"\\"+"newone.dat";
		File temp = new File(tempaddress);
		temp.delete();		
	}
	
	/*
	* Saving a file with a different name, then retrieving and testing its contents
	*/
	//@Ignore
	@Test
	public void testsaveValidContentsIII () {	
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.capture("config10");
		validConfigMgr.save("newone");
		ConfigurationManager tempconfig=new ConfigurationManager(validFile);
		tempconfig.capture("newone");
		assertEquals(4,validConfigMgr.getBpaFilesAttributesMap().get("phones.csv").size());
		String tempaddress = validConfigMgr.getFile().getAbsolutePath()+"\\"+"newone.dat";
		File temp = new File(tempaddress);
		temp.delete();		
	}
	
	/*
	* Saving a file with a different name, then retrieving and testing its contents
	*/
	//@Ignore
	@Test
	public void testsaveValidContentsIV () {	
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.capture("config10");
		validConfigMgr.save("newone");
		ConfigurationManager tempconfig=new ConfigurationManager(validFile);
		tempconfig.capture("newone");
		assertEquals(2,validConfigMgr.getBpaMainFilesAttributesMap().get("phones.csv").size());
		String tempaddress = validConfigMgr.getFile().getAbsolutePath()+"\\"+"newone.dat";
		File temp = new File(tempaddress);
		temp.delete();		
	}
	
	/*
	* Saving a file with a different name, then retrieving and testing its contents
	*/
	//@Ignore
	@Test
	public void testsaveValidContentsV () {	
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.capture("config10");
		validConfigMgr.save("newone");
		ConfigurationManager tempconfig=new ConfigurationManager(validFile);
		tempconfig.capture("newone");
		assertEquals(4,validConfigMgr.getGlFilesAttributesMap().get("gl.csv").size());
		String tempaddress = validConfigMgr.getFile().getAbsolutePath()+"\\"+"newone.dat";
		File temp = new File(tempaddress);
		temp.delete();		
	}
	
	/*
	* Saving a file with a different name, then retrieving and testing its contents
	*/
	//@Ignore
	@Test
	public void testsaveValidContentsVI () {	
		validConfigMgr=new ConfigurationManager(validFile);
		validConfigMgr.capture("config10");
		validConfigMgr.save("newone");
		ConfigurationManager tempconfig=new ConfigurationManager(validFile);
		tempconfig.capture("newone");
		assertEquals(3,validConfigMgr.getGlMainFilesAttributesMap().get("gl.csv").size());
		String tempaddress = validConfigMgr.getFile().getAbsolutePath()+"\\"+"newone.dat";
		File temp = new File(tempaddress);
		temp.delete();		
	}
	

	
}
