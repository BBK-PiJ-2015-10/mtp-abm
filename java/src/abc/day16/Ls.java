package abc.day16;

import java.io.File;

public class Ls {

	public static void main(String[] args) {
		
		//File dir = new File(".");
		File dir = new File("C:\\Users\\YasserAlejandro\\mtp\\mtp-abm");
		
		String [] outputArray = dir.list();
		for (int i =0; i < outputArray.length;i++){
			System.out.println(">> " +outputArray[i]);
		}
		// TODO Auto-generated method stub

	}

}
