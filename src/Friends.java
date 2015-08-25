package fga;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

// Akhil Velagapudi
// Vineet Shenoy

public class Friends {
	
	public HashMap<Person, Node> data;
	public HashMap<String, Person> nameToPerson;
	
	public int shortest = Integer.MAX_VALUE;
	public String shortestPath;
	public ArrayList<ArrayList<Person>> paths;
	public Scanner stdin;
	
	public Friends() throws FileNotFoundException{
		data = new HashMap<Person, Node>();
		nameToPerson = new HashMap<String, Person>();
		stdin = new Scanner(System.in);
	}
	
	public void buildGraph(String inputFile) throws FileNotFoundException{
		Scanner fileReader = new Scanner(new File(inputFile));
		int numNodes = Integer.parseInt(fileReader.nextLine());
		for (int i = 0; i < numNodes; i ++){
			String person = fileReader.nextLine();
			String[] temp = person.split("\\|");
			Person vertex;
			if (temp.length < 3){
				vertex = new Person(temp[0], null);
			}
			else{
				vertex = new Person(temp[0], temp[2]);
			}
			data.put(vertex, null);
			nameToPerson.put(temp[0], vertex);
		}
		while (fileReader.hasNext()){
			String edge = fileReader.nextLine();
			String[] temp = edge.split("\\|");
			
			Person a = nameToPerson.get(temp[0]);
			Person b = nameToPerson.get(temp[1]);
			
			Node headA = new Node(a);
			headA.next = data.get(b);
			data.put(b, headA);
			
			Node headB = new Node(b);
			headB.next = data.get(a);
			data.put(a, headB);
		}
		fileReader.close();
	}
	
	public String shortestPath(String personA, String personB){
		paths = new ArrayList<ArrayList<Person>>();
		shortest = Integer.MAX_VALUE;
		ArrayList<Person> nodesVisited = new ArrayList<Person>();
		nodesVisited.add(nameToPerson.get(personA));
		shortestPath(nameToPerson.get(personA), nameToPerson.get(personB), nodesVisited);
		for (ArrayList<Person> a: paths)
			a.remove(0);
		if (shortestPath == null)
			return personA + " and " + personB + " are not connected"; 
		return shortestPath + personB;
	}
	
	public void shortestPath(Person personA, Person personB, ArrayList<Person> nodesVisited){
		for (Node a = data.get(personA); a != null; a = a.next){
			if (a.data.equals(personB)){
				paths.add((ArrayList<Person>) nodesVisited.clone());
				if (nodesVisited.size() < shortest){
					shortest = nodesVisited.size();
					shortestPath = "";
					for (Person b: nodesVisited)
						shortestPath += b.name + "-";
				}
				continue;
			}
			if (nodesVisited.contains(a.data)){
				continue;
			}
			nodesVisited.add(a.data);
			shortestPath(a.data, personB, nodesVisited);
			nodesVisited.remove(a.data);
		}
	}
	
	public void printCliques(String schoolName){
		int numCliques = 1;
		ArrayList<Person> students = new ArrayList<Person>();
		ArrayList<Person> used = new ArrayList<Person>();
		for (Person a: data.keySet())
			if (a.school != null && a.school.equalsIgnoreCase(schoolName))
				students.add(a);
		for (Person a: students){
			if (used.contains(a))
				continue;
			ArrayList<Person> visited = new ArrayList<Person>();
			System.out.println("Clique " + numCliques + ":\n");
			String connections = getClique("", schoolName, a, visited);
			System.out.println(connections);
			System.out.println();
			used.addAll(visited);
			numCliques ++;
		}
	}
	
	public String getClique(String connections, String schoolName, Person student, ArrayList<Person> visited){
		visited.add(student);
		connections = student.toString() + "\n" + connections;
		for (Node a = data.get(student); a != null; a = a.next){
			Person b = a.data;
			if (visited.contains(b) || b.school == null || !b.school.equalsIgnoreCase(schoolName))
				continue;
			connections += "\n" + student.name + "|" + b.name;
			connections = getClique(connections, schoolName, b, visited);
		}
		return connections;
	}
	
	public void printData(){
		for (Person a: data.keySet())
			System.out.println(a + "\t\t" + data.get(a));
	}
	
	public String connectors(){
		ArrayList<Person> allPeople= new ArrayList<Person>(data.keySet());
		ArrayList<Person> connectors = new ArrayList<Person>();
		for(int i=0;i<allPeople.size()-1;i++){
			Person a=allPeople.get(i);
			for(int j=i+1;j<allPeople.size();j++){
				Person b=allPeople.get(j);
				shortestPath(a.name,b.name);
				if (paths.isEmpty())
					continue;
				for (Person c: paths.get(0)){
					boolean connector = true;
					for (int k = 1; k < paths.size(); k ++)
						if (!paths.get(k).contains(c))
							connector = false;
					if (connector && !connectors.contains(c))
						connectors.add(c);
				}
			}
		}
		if (connectors.isEmpty())
			return "There are no connectors";
		String output = "";
		for (int i = 0; i < connectors.size() - 1; i ++)
			output += connectors.get(i).name + ", ";
		output += connectors.get(connectors.size() - 1).name;
		return output;
	}
	
	public char getOption() {
		System.out.print("\tChoose action: ");
		System.out.print("Shortest path intros (s), ");
		System.out.print("Cliques (c), ");
		System.out.print("Connectors (n), ");
		System.out.print("Quit? (q) => ");
		String in = stdin.next().toLowerCase();
		char response = in.charAt(0);
		while (response != 's' && response != 'c' && response != 'n' && response != 'q') {
			System.out.print("\tYou must enter one of s, c, n, or q => ");
			response = stdin.next().toLowerCase().charAt(0);
		}
		return response;
	}
	
	public void runProgram() throws FileNotFoundException{
		System.out.print("Enter graph file name => ");
		String graphFile = stdin.next();
		buildGraph(graphFile);
		
		char option;
		while ((option = getOption()) != 'q') {
			if (option == 's') {
				System.out.print("\tEnter name of first person => ");
				String friend1 = stdin.next();
				System.out.print("\tEnter name of second person => ");
				String friend2 = stdin.next();
				System.out.println(shortestPath(friend1,friend2));
			} else if (option == 'c') {
				System.out.print("\tEnter School name => ");
				String schoolName=stdin.next();
				printCliques(schoolName);
			} else if (option == 'n') {
				System.out.println(connectors());
			} 
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Friends tc = new Friends();
		tc.runProgram();
	}
	
}
