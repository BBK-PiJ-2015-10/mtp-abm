package user;

import java.util.Scanner;
import java.io.File;

public class UserSpaceMakerImpl implements UserSpaceMaker {
	
	@Override
	public void createUserSpace(UserSpace userSpace) {
				
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter the name of the directory userspace you wish to create");
			String dirname = sc.next();
			String address = "C:\\Users\\YasserAlejandro\\mp\\mtp-abm\\";
		
			userSpace.FileSetUserSpaceFile(new File(address+dirname));
			userSpace.getUserSpaceFile().mkdir();
			userSpace.save();
			
	}
	
	
	
	
	
	

}
