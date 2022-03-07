package jdbc;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import person.Person;

public class Commands {

	Scanner in = new Scanner(System.in);

	// CREATES A NEW ENTRY IN THE MYSQL DATABASE
	public void create() {

		String url = "jdbc:mysql://localhost:3306/testdb";
		String dbUsername = "root";
		String dbPassword = "password123!";

		System.out.print("First name: ");
		String first_name = in.nextLine();

		System.out.print("Last name:");
		String last_name = in.nextLine();

		System.out.print("Age: ");
		int age = in.nextInt();

		Person p = new Person(first_name, last_name, age);

		String sql = p.addPerson();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
			Statement statement = connection.createStatement();
			int num = statement.executeUpdate(sql);
			System.out.println(num + " ROWS UPDATED.");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	// DELETES A CURRENT ENTRY IN MYSQL DATABASE BY FINDING ID NUMBER
	public void delete() {

		String url = "jdbc:mysql://localhost:3306/testdb";
		String dbUsername = "root";
		String dbPassword = "password123!";

		System.out.print("ID of who you would you like deleted: ");
		int num = in.nextInt();

		String sql = "DELETE FROM person WHERE person_ID = " + num + ";";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	// UPDATES A CURRENT ENTRY IN MYSQL DATABASE BY FINDING IT BY ID NUMBER
	public void update() {

		String url = "jdbc:mysql://localhost:3306/testdb";
		String dbUsername = "root";
		String dbPassword = "password123!";

		System.out.print("What ID do you want to update: ");
		int id = in.nextInt();

		System.out.print("New first name: ");
		String first_name = in.nextLine();

		System.out.print("New last name: ");
		String last_name = in.nextLine();

		System.out.print("New age: ");
		int age = in.nextInt();

		String sql = "UPDATE person SET First_Name = '" + first_name + "', Last_Name = '" + last_name + "', age = '"
				+ age + "' WHERE person_ID = " + id + ";";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	// GETS A SINGLE ENTRY FROM MYSQL DATABASE BASED OFF ID NUMBER
	public void get() throws IOException {

		List<String> data = new ArrayList<String>();
		ResultSet resultSet;
		String url = "jdbc:mysql://localhost:3306/testdb";
		String dbUsername = "root";
		String dbPassword = "password123!";

		System.out.print("What ID would you like to get: ");
		int id = in.nextInt();

		String sql = "SELECT * FROM person WHERE person_ID = " + id + ";";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while ((resultSet).next()) {

				String first_Name = resultSet.getString("First_Name");
				String last_Name = resultSet.getString("Last_Name");
				int age = resultSet.getInt("Age");

				data.add(first_Name + " " + last_Name + " " + age);
			}

			System.out.println(data);
			writeToFile(data, "Person.txt");
			resultSet.close();
			statement.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	// GETS A LIST OF ALL ENTRIES IN MYSQL DATABASE
	public void getAll() throws IOException {

		List<String> data = new ArrayList<String>();
		ResultSet resultSet;
		String url = "jdbc:mysql://localhost:3306/testdb";
		String dbUsername = "root";
		String dbPassword = "password123!";

		String sql = "SELECT * FROM person;";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while ((resultSet).next()) {

				int id = resultSet.getInt("person_ID");
				String first_Name = resultSet.getString("First_Name");
				String last_Name = resultSet.getString("Last_Name");
				int age = resultSet.getInt("Age");

				data.add("ID: " + id + " Name: " + first_Name + " " + last_Name + " Age: " + age + "\n\n");
			}

			System.out.println(data);
			writeToFile(data, "Person.txt");
			resultSet.close();
			statement.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void writeToFile(List<String> list, String fileName) throws IOException {

		FileWriter writer = new FileWriter(fileName);

		for (String person : list) {
			writer.write(person);
		}
		writer.close();

	}

}
