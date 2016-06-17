package abc.day16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class Cat {

	public static void main(String[] args) {
		
		Cat c = new Cat();
		//for (int i=0;i<args.length;i++){
           //c.launch(args[i]);
		//}
		//System.out.println("Is this working");
		c.launch();
		
	}
	
	public void launch(){
		
		Scanner input = new Scanner (System.in);
		System.out.println("Please enter the name you are looking for");
		String name = input.next();
		//String address = "\\Users\\YasserAlejandro\\PIJ\\Day-16\\Exer3\\"+name;
		//String address = "\\Users\\YasserAlejandro\\mtp\\mtp-abm\\java\\src\\abc\\iopractice\\"+name;
		//String address = "\\..\\"+name;
		String address = name;
		
		File file = new File(address);
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));
			String line;
			while ((line=in.readLine()) != null){
				System.out.println(line);
			}
			System.out.println("Is this working");
			in.close();
		} catch (FileNotFoundException ex){
			System.out.println("File " +file + " does not exist");
		} catch (IOException ex){
			ex.printStackTrace();
		} finally {
			closeReader(in);
		}
		
		
	}
	
	
	private void closeReader(Reader reader){
		try {
			if (reader != null){
				reader.close();
			}
		} catch (IOException ex){
			ex.printStackTrace();
		}
	}

}
