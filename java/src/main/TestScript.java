package main;

import java.io.File;

import java.util.Arrays;

public class TestScript {

	public static void main(String[] args) {
		
		TestScript script = new TestScript();
		script.launch();

	}
	
	public void launch(){
		
		System.out.println("This is a test");
		
		String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\user3\\config3";
		
		File config = new File(address);
		
		System.out.println(config.isDirectory());
		String[] arrays = config.list();
		Arrays.asList(arrays).forEach(n->System.out.println(n));
		
		
		//System.out.println(config.list());
		
		//ConfigurationStructure test = new ConfigurationStructure();
		
	}

}
