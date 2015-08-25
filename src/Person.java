package fga;
public class Person {
	public String name, school;
	public Person(String name, String school){
		this.name = name;
		this.school = school;
	}
	public boolean equals(Person other){
		return name.equals(other.name);
	}
	public String toString(){
		String output = name + "|";
		if (school != null)
			output += "y|" + school;
		else
			output += "n";
		return output;
	}
}
