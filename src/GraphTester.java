package fga;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class GraphTester {

	static Scanner stdin = new Scanner(System.in);
	
	static char getOption() {
		System.out.print("\tChoose action: ");
		System.out.print("Shortest Path intros (s), ");
		System.out.print("Cliques (c), ");
		System.out.print("Connectors (l), ");
		System.out.print("Quit? (q) => ");
		char response = stdin.next().toLowerCase().charAt(0);
		while (response != 's' && response != 'c' && response != 'l' && response != 'q') {
			System.out.print("\tYou must enter one of p, r, b, d, a, or q => ");
			response = stdin.next().toLowerCase().charAt(0);
		}
		return response;
	}
	
	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub


		System.out.print("Enter graph file name => ");
		String graphFile = stdin.next();
		Friends graph=new Friends();
		graph.buildGraph(graphFile);
		//Friends graph=new Friends(graphFile);
		
		char option;
		while ((option = getOption()) != 'q') {
			if (option == 's') {
				System.out.print("\tEnter name of first person => ");
				String friend1 = stdin.next();
				System.out.print("\tEnter name of second person => ");
				String friend2 = stdin.next();
				//graph.shortestPath(friend1,friend2)
			} else if (option == 'c') {
				System.out.print("\tEnter School name => ");
				String schoolName=stdin.next();
				//graph.printCliques(schoolName);
				
			} else if (option == 'l') {
				//System.out.println(graph.connectors());
			} 
		}
	}
		
		
	}


