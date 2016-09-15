package user;

import java.util.Scanner;

/**
 * @author YasserAlejandro
 * Classes implementing UserSpaceMaker will create a UserSpace on a particular location.
 */
public interface UserSpaceMaker {
	
	/**
	 * @param userSpace the class that will hold information about the user (configuration
	 * period, type of configurations..)
	 * @param sc a scanner that will take input from the user.
	 */
	void createUserSpace(UserSpace userSpace, Scanner sc);
	
}
