package abc.day16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TempAv {

	public static void main(String[] args) {
		
		if (args.length != 1){
			System.out.println("java TempAv fileName");
			System.exit(-1);
		}
		
		File file = new File(args[0]);
		
		try (BufferedReader in = new BufferedReader(new FileReader(file)))
		{
			String line;
			int fileTotal =0;
			int fileCount =0;
			while ((line = in.readLine()) != null){
				String[] strArray = line.split(", ");
				System.out.println(line);
				int lineTotal =0;
				for (int i=0; i<strArray.length;i++){
					lineTotal = lineTotal + Integer.parseInt(strArray[i]);
				}
				System.out.println("Line Average: " +lineTotal / strArray.length);
				fileTotal = fileTotal + lineTotal;
				fileCount = fileCount + strArray.length;
			}
			System.out.println("================");
			System.out.println("File average: " + fileTotal / fileCount);
			System.out.println("================");
	    } catch (FileNotFoundException ex){
	    	System.out.println("File " + file + " does not exist");
	    } catch (IOException ex){
	    	ex.printStackTrace();
	    }
		
    }

}
