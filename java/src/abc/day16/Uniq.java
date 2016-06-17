package abc.day16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Uniq {

	public static void main(String[] args) {
		
		if (args.length != 1){
			System.out.println("Java Uniq filename" );
			System.exit(-1);
		}
		
		Uniq qs = new Uniq();
		qs.launcher(args[0]);
	}
	
	private void launcher(String fileName){
		File file = new File(fileName);
		try (BufferedReader in = new BufferedReader(new FileReader(file)))
		{
			String line;
			LinkedHashSet<String> lineSet = new LinkedHashSet<>();
			while ((line = in.readLine()) != null){
				lineSet.add(line);
				System.out.println(line);
			}
			printUnique(lineSet);
		} catch (FileNotFoundException ex){
			System.out.println("File " +file + " does not exist");
		} catch (IOException ex){
			ex.printStackTrace();
		}
		
	}
	
	private void printUnique(LinkedHashSet lineSet){
		System.out.println();
		Iterator itr = lineSet.iterator();
		while (itr.hasNext()){
			System.out.println(itr.next());
		}
		
	}

}
