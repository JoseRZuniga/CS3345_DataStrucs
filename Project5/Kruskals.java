/*
  Name: Jose Rodriguez-Zuniga
  NetId: jpr160230
  Class: 3345.001
  Project 05

  Overview:

     The pseudocode for Kruskals algorithm is given in the textbook to find a minimum
     spanning tree of a graph.  Your program will find the minimum spanning tree
     among a set of cities in Texas.

  Details:

     Write a command-line program that uses Kruskal's algorithm to find a minimum spanning
     tree of a graph.  The graph will be provided as a file named assn9_data.csv.  The
     data in the file is in the form of an adjacency list.

     You must use the author's DisjSets class without modifying it.  You can either use
     one of the author's priority queue classes or you can use the PriorityQueue class
     provided in Java.

     You should output each edge of your minimum spanning tree as the names of the two
     cities and the distance between them.  You should also print the sum of all of the
     distances in the tree.

*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

	public class Kruskals{

        /* Edge Class */
        public static class Edge implements Comparable<Edge>{
            
            int distance;
            String v1, v2;

            Edge(int d, String s1, String s2){
                this.distance = d;
                this.v1 = s1;
                this.v2 = s2;
            }

            public int compareTo(Edge e){
                
                if(this.distance < e.distance){
                    return -1;
                }
                else
                    if(this.distance > e.distance){
                        return 1;
                    }
                    else{
                        return 0;
                    }
            }
        }

        public void kruskals(){
            
            ArrayList<Edge> eList = new ArrayList<>();
            ArrayList<String> vList = new ArrayList<>();

            BufferedReader fileReader = null;

            int totalVertices = 0;
            int sumOfDistances = 0;

            final String DELIMITER = ",";

            try{
                
                String line = "";

                fileReader = new BufferedReader(new FileReader("assn9_data.csv"));
   
                /* This while loop will read and seperate the csv file */
                while ((line = fileReader.readLine()) != null){
    
                    String[] tokens = line.split(DELIMITER);
    
                    vList.add(tokens[0]);
                    eList.add(new Edge(Integer.parseInt(tokens[2]), tokens[0], tokens[1]));
    
                    for (int i = 3; i < tokens.length; i++){
     
                        eList.add(new Edge(Integer.parseInt(tokens[i + 1]), tokens[0], tokens[i]));
                        i++;
                    }
                    totalVertices++;
                }
            }

            catch (Exception e){
  
                e.printStackTrace();
            }

            try{

                fileReader.close();
            }
            
            catch (IOException e){
                
                e.printStackTrace();
            }

            int edges = 0;
            DisjSets set = new DisjSets(vList.size());

            /* I will be using priority queue */
            PriorityQueue<Edge> pQueue = new PriorityQueue<>();

            for (Edge e : eList){
                pQueue.add(e);
            }

            while (edges < vList.size() - 1){
                
                /* Used poll instead of deleteMin() */
                Edge e = pQueue.poll();

                if(set.find(vList.indexOf(new String(e.v1))) != set.find(vList.indexOf(new String(e.v2)))){
                    edges++;
                
                    set.union(set.find(vList.indexOf(new String(e.v1))), set.find(vList.indexOf(new String(e.v2))));
                    sumOfDistances = sumOfDistances + e.distance;

                    System.out.println("Distance between " + e.v1 + " to " + e.v2 + " is " + e.distance);
                }
            }    

            System.out.println("Sum of all Distances is " + sumOfDistances);
        }

        public static void main(String args[]){

            Kruskals mst = new Kruskals();

            mst.kruskals();
            
        }
    }

    /* All of the code below was the author's DisjSets class which I did not modify */

    /**
    * Disjoint set class, using union by rank and path compression
    * Elements in the set are numbered starting at 0
    */
    class DisjSets{

        private int[]s;

	    public DisjSets(int numElements) {		
		    s = new int [numElements];
		    for(int i = 0; i < s.length; i++){		
			    s[i] = -1;
		    }
	    }

	    public void union(int root1, int root2) {
		    if(s[root2] < s[root1]){		
			    s[root1] = root2;		
		    }
		    else {
			    if(s[root1] == s[root2]){
				    s[root1]--;			
			    }
			    s[root2] = root1;		
		    }
	    }

	    public int find(int x) {
		    if(s[x] < 0){		
			    return x;
		    }
		    else{
               return s[x] = find(s[x]);
            }   
        }
    }

