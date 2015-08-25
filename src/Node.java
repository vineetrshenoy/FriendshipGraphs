package fga;
public class Node {
	public Person data;
	public Node next;
	public Node(Person data){
		this.data = data;
	}
//	public String toString(){
//		String output = data.toString();
//		if (next != null)
//			output += " -> " + next;
//		return output;
//	}
	public String toString(){
		return data.toString();
	}
}
