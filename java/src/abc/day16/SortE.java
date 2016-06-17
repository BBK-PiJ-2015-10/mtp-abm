package abc.day16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class SortE {

	public static void main(String[] args) {
		
		if (args.length != 1){
			System.out.println("java Sort filename");
			System.exit(-1);
		}
		
		File file = new File(args[0]);
		try (BufferedReader in = new BufferedReader(new FileReader(file)))
		{
		
			String line;
			ArrayList<String> lineArray = new ArrayList<>();
			while ((line = in.readLine()) != null){
				lineArray.add(line);
				System.out.println(line);
			}
			System.out.println();
			Collections.sort(lineArray);
			for (int i=0;i< lineArray.size();i++){
				System.out.println(i + " " +lineArray.get(i));
			}
		} catch (FileNotFoundException ex){
			System.out.println("File " + file + " does not exist");
		} catch (IOException ex){
			ex.printStackTrace();
		}
		
		

	}

}
