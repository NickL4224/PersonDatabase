package jdbc;

import java.io.IOException;
import java.util.Scanner;

public class Program {

	Scanner in = new Scanner(System.in);
	Boolean ask;

	public void runProgram() throws IOException {
		System.out.print("Create, Delete, Update, Get, or Get All?: ");
		String response = in.nextLine();
		Commands c = new Commands();
		ask = true;

		while (ask) {

			if (response.equalsIgnoreCase("create")) {

				c.create();
				

			} else if (response.equalsIgnoreCase("delete")) {

				c.delete();
				

			} else if (response.equalsIgnoreCase("update")) {

				c.update();
				

			} else if (response.equalsIgnoreCase("get")) {

				c.get();
				

			} else if (response.equalsIgnoreCase("get all")) {

				c.getAll();
				

			} else {
				System.out.println("Unrecognized command");
				runProgram();
			}
			askAgain();

		}
	}

	public Boolean askAgain() throws IOException {

		System.out.print("Do you want to enter another command? (YES or NO): ");

		String answer = in.nextLine();

		if (answer.equalsIgnoreCase("YES")) {
			ask = true;
			runProgram();
			
		} else if (answer.equalsIgnoreCase("NO")){
			ask = false;
		} else {
			askAgain();
		}

		return ask;
	}

}
