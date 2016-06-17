package main;

import java.util.Scanner;
import java.io.File;

public class UserSpaceMakerImpl implements UserSpaceMaker {
	
	@Override
	public void createUserSpace(UserSpace userSpace) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the name of the directory userspace you wish to create");
		String dirname = sc.nextLine();
		
		//System.out.println("Please enter the location on disk where you wish to create the new userspace");
		//String address = sc.nextLine();
		String address = "C:\\Users\\YasserAlejandro\\mtp\\mtp-abm\\";
		
		userSpace.FileSetUserSpaceFile(new File(address+dirname));
		userSpace.getUserSpaceFile().mkdir();
		userSpace.save();
			
	}
	
	
	
	
	
	

}
