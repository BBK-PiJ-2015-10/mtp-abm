package abc.day16;

import java.io.File;
import java.util.Scanner;

public class Mkdir {
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter a desired directory name");
		String dirname=sc.next();
		
		//String dirname = "C:\\Users\\YasserAlejandro\\mtp\\mtp-abm\\prueba";
		
		
		File f = new File(dirname);
		f.mkdir();
		
		System.out.println(dirname);
		
		/*
		if (args.length == 1){
			File f = new File(args[0]);
			f.mkdir();
			System.out.println("I hope this works");
		} else {
			System.out.println("Invalid number of arguments: java MKdir name");
		}
		*/
		
		
		//File dir = new File(".");
		
		
		

    }

}