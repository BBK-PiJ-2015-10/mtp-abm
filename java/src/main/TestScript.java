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
		
		//System.out.println(config.isDirectory());
		//System.out.println(config.exists());
		//String[] arrays = config.list();
		//Arrays.asList(arrays).forEach(n->System.out.println(n));
		
		//File[] files = config.listFiles();
		//Arrays.asList(files);
		//System.out.println(files[0].getPath());
		//System.out.println(files[0].getName());
		
		//System.out.println(config.list());
		
		ConfigurationManager test = new ConfigurationManager(config);
		//System.out.println(test.getFile().getAbsolutePath());
		//System.out.println(test.capture("config3"));
		test.scanConfig();
		//System.out.println(test.capture("config3"));
		test.setGLFile("gl.csv");
		test.save();
		System.out.println(test.capture("config3"));
		
		ConfigurationManager test2 = new ConfigurationManager(config);
		test2.capture("config3");
		test2.getFileNames().forEach(System.out::println);
		
		System.out.println("it is over");
		
		
		//test.
		
		//System.out.println(test.getFile().getName());
		//System.out.println(test.getFileNames());
		//test.scanConfig();
		//System.out.println(test.getFileNames());
		//test.setGLFile("gl.csv");
		//System.out.println(test.getFileNames());
		//System.out.println(test.getFileNames());
		
		
	}

}
