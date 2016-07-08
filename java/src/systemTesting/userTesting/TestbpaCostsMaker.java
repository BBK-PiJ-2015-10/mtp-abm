package systemTesting.userTesting;

import java.io.File;

import org.junit.Before;

import bpa.BpaCostsMaker;
import period.PeriodMaker;
import period.PeriodMakerImpl;

public class TestbpaCostsMaker {
	
	private String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user14\\period14";
	
	private File file = new File(address);
    
	private PeriodMaker period = new PeriodMakerImpl(file);
	
	private BpaCostsMaker bpaCostsMaker;
	
	@Before
	public void setUp() {
		period.capture("period14");
	}
	
	
	

}
