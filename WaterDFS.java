import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class WaterDFS {
	public static ArrayList<String> visited = new ArrayList<String>();
	public static int nodesTraversed = 0;
	public static Stack<Node> stack = new Stack<Node>();
	public static jug jugA, jugB, jugC;
	public static int initA, initB, initC;
	
	// Node to store the volume of each jug as a tuple
	static class Node {
        int a , b , c;
    
        public Node(int a, int b , int c){
            this.a = a;
            this.b = b;
            this.c = c;
    
        }
        //To String function for displaying the the volumes in each state
        public String toString() {
        	return("["+this.a+", "+this.b+", "+this.c+"]");
        }
    }
	//Jug class which has parameters capacity and current volume, it also has methods empty and fill
	static class jug {
		
		public int capacity;
		public int volume;
		
		public jug(int cap) {
			this.capacity = cap;
			this.volume = 0;
		}
		//function to set volume of jug to zero
		public void empty() {
			this.volume = 0;
			Node newNode = new Node(jugA.volume, jugB.volume, jugC.volume);
			addState(newNode);
		}
		//function to set volume of jug to it's capacity
		public void fill() {
			this.volume = capacity;
			Node newNode = new Node(jugA.volume, jugB.volume, jugC.volume);
			addState(newNode);
		}
		
	}
	//function to transfer from one jug (sender) to another jug (receiver)
	public static void transfer(jug a, jug b) {
		//calculate free space in receiving jug
		int difference = b.capacity - b.volume;
		//If the volume in sending jug is greater than or equal to free space then fill receiving jug
		//and reduce volume in sending jug by the free space that is now filled in receiving jug
		if (a.volume >= difference) {
			b.volume = b.capacity;
			a.volume -= difference;
			Node newNode = new Node(jugA.volume, jugB.volume, jugC.volume);
			addState(newNode);
		//Otherwise add volume in sending jug to volume in receiving jug and set sending jug to empty
		} else {
			b.volume += a.volume;
			a.volume = 0;
			Node newNode = new Node(jugA.volume, jugB.volume, jugC.volume);
			addState(newNode);
		}
	}
	//function to check if state has already been visited, and if not then add to the stack
	public static void addState(Node node) {
		String formatted = node.toString();
		if (!visited.contains(formatted)) {
			stack.push(node);
		}
		//Reset volumes to initial volumes for that state so each function can be carried out for the state
		jugA.volume = initA;
		jugB.volume = initB;
		jugC.volume = initC;
		
	}

	public static void main(String[] args) {
		//Get current time to see runtime when program is complete
		long startTime = System.currentTimeMillis();
		
		//Request and read user input for the capacities for each jug
		Scanner sc = new Scanner(System.in);
        System.out.println("Enter the positive capacities of the 3 jugs, press enter after each capacity: ");
        // read the maximum capacity of jugs
        int capA = sc.nextInt();
        int capB = sc.nextInt();
        int capC = sc.nextInt();
        
        sc.close();
        //Error catching to ensure that capacities are all positive
        if (capA < 0 || capB < 0 || capC < 0) {
        	System.out.println("Please only enter positive capacities...");
        	main(args);       	
        }
        //Base case, add first state to stack where volume of each jug is zero
        Node n = new Node(0, 0, 0);
        stack.push(n);
        //Initialise three jug objects
        jugA = new jug(capA);
        jugB = new jug(capB);
        jugC = new jug(capC);
        
        //Iterate through all possible states using DFS and stop when stack is empty
        while (!stack.isEmpty()) {
        	//Pop top item from stack and convert into readable string
        	Node node = stack.pop();
        	String formatted = node.toString();
        	//If state has not been visited, then add to visited list and generate all possible child states
        	if (!visited.contains(formatted)) {
        		System.out.println(formatted);
	        	visited.add(formatted);
	        	nodesTraversed++;
	        	//Set volume of each jug to value of volume in state we are exploring
	        	jugA.volume = node.a;
	        	jugB.volume = node.b;
	        	jugC.volume = node.c;
	
	        	//Set initial values of volume to reset to after each function is called
	        	initA = jugA.volume;
	        	initB = jugB.volume;
	        	initC = jugC.volume;
	        	
	        	//Fill each jug
	        	jugA.fill();
	        	jugB.fill();
	        	jugC.fill();
	        	//Empty each jug
	        	jugA.empty();
	        	jugB.empty();
	        	jugC.empty();       	
	        	//Transfer between all possible jugs
	        	transfer(jugA, jugB);
	        	transfer(jugA, jugC);
	        	transfer(jugB, jugA);
	        	transfer(jugB, jugC);
	        	transfer(jugC, jugA);
	        	transfer(jugC, jugB);
        }
        }
        System.out.println("Number of nodes traversed: "+nodesTraversed);
        //Calculate and print runtime of program
        long endTime = System.currentTimeMillis();
		System.out.println("Took "+(endTime - startTime) + " ms"); 
	}
}
