package main;

import java.util.Scanner;

public class ABCStarter {

	public static void main(String[] args) {
		
		ABCSystem application = new ABCSystemImpl(new Scanner(System.in));
		application.run();
		
	}

}
