# FriendshipGraphs

Implementing graphs to model friendships. This assignment was created for CS 112 -- Data Structures at Rutgers University (Spring 2015). 



Graphs are used for a variety of applications, including social networking. In the following project each node represents a person and the school they attend. Friends groups are determined by students from the same school, as well as connections to people from different schools

The API is as follow:

      public class Friends{
      public Friends()                                                                              //Creates new blank graph and Scanner
      public void buildGraph(String inputFile)                                                      //Builds graph using input file and scanner
      public String shortestPath(String personA, String personB)                                    //Finds the shortest path between two people in a graph
      public String getClique(String connection, String school, Person student, ArrayList visited)  //Finds people from same school
      public String connectors()                                                                    //Finds the people who share common links between schools
      public void printData()                                                                       //Prints the data of all the nodes
      public char getOption()                                                                       //Allows user to choose option
      public void runProgram                                                                        //Driver to run class
      }


This project was completed on May 5th, 2015
