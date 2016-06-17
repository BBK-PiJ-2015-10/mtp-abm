package abc.day16;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BinaryCP {

	public static void main(String[] args) {
		
		if (args.length != 2) {
			System.out.println("java BinaryCP file1 file2:");
			System.exit(-1);
		}
		BinaryCP bcp = new BinaryCP();
		bcp.launch(args[0], args[1]);

	}
	
	public void launch(String aName, String bName){
		
		File aFile = new File(aName);
		File bFile = new File(bName);
		
			
		try (OutputStream out = new FileOutputStream(bFile);
			InputStream in = new FileInputStream(aFile);)
		{
			int size = in.available();
			System.out.println(size + " bytes");
			byte[] data = new byte[size];
			int readBytes;
			while ( (readBytes = in.read(data)) != -1){
				out.write(data, 0, size);
			}
		} catch (FileNotFoundException ex){
			System.out.println(" File " +aFile + "does not exist");
		} catch (IOException ex){
			ex.printStackTrace();
		}
			
	}
	

}
