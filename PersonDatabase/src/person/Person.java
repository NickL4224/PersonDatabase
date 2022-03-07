package person;

public class Person {

	private String first_name;
	private String last_name;
	private int age;

	

	public Person(String first_name, String last_name, int age) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public int getAge() {
		return age;
	}

	public String addPerson() {

		String addStatement = "INSERT INTO person( First_Name, Last_Name, age)" + " VALUES( '" + getFirst_name() + "', '"
				+ getLast_name() + "', " + getAge() + ");";

		return addStatement;
	}

}
