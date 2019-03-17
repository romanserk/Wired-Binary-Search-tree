package BinarySearchTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern; 


public class Main{
	   
	public static final int firstLine = 0;
	
	public static void main(String arg[]){
		
		BinarySearchTree mainTree = new BinarySearchTree();
		
		userInterface(mainTree);
				
	}	
	
	
	@SuppressWarnings("resource")
	/**
	 * function to print on the stdout first line with instruction and redirect
	 * the input to the relevant function: read from file or strait go to parse line
	 * @param mainTree a new threaded bst
	 * 
	 */
	private static void userInterface(BinarySearchTree mainTree) {
		
		String firstChoose;
		
		System.out.println("Please enter your first command for manualy input or Enter the file name with extension to use txt file");
		Scanner input = new Scanner(System.in);
		firstChoose = input.nextLine();
		
		
		if (firstChoose.contains(".txt")) {
			System.out.println("\nCommand\t\t\tOutput\n");
			 File file = new File(firstChoose);
	            
	            try {
					input = new Scanner(file);
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				}
	            
	            readFile(input , mainTree, firstLine);	            			
		}
		
		else  {
			System.out.println("\nCommand\t\t\tOutput\n");
			parseLine(mainTree, firstChoose, input, false, firstLine);
		}
		
		
	}
	
	
	
	
	/**
	 * function to recive user input at the stdin
	 * @param mainTree the current bst
	 * @param lineNumber the number of the line that have been inserted
	 */
	public static void keyboardInput(BinarySearchTree mainTree, int lineNumber) {
		
		String chooseMethod;
				
		Scanner input = new Scanner(System.in);
		 if (input.hasNextLine()) {
	         	chooseMethod = input.nextLine();
	         	
	         	parseLine(mainTree, chooseMethod, input, false, lineNumber);	     	                        
	     }   
	}
	
	/**
	 * function to read the data from the file
	 * @param input - the input content
	 * @param mainTree - the current bst
	 * @param lineNumber - the current line number that have been read
	 */
	public static void readFile(Scanner input, BinarySearchTree mainTree, int lineNumber) {
		
         String chooseMethod;
         lineNumber++;

         while (input.hasNextLine()) {
        	 
         	chooseMethod = input.nextLine();
         	parseLine(mainTree, chooseMethod, input, true, lineNumber);     	                        
         }    
	}
	
	/**
	 * the function to parse the current line and use the BinarySearchTree class to create output
	 * @param mainTree the current bst
	 * @param chooseMethod the method to create the output
	 * @param input the current input string
	 * @param isFile if the input came from file or user manually input
	 * @param lineNumber the current read line number
	 */
	private static void parseLine(BinarySearchTree mainTree, String chooseMethod, Scanner input, Boolean isFile, int lineNumber) {
		
			int nodeValu;
							
			while (chooseMethod.trim().isEmpty()) {
				chooseMethod = input.nextLine();
			}
			System.out.print(chooseMethod + "\t\t");
			if(chooseMethod.contains("preorder")){
     			mainTree.preorder(mainTree.getRoot());
     			System.out.println();	
     		}
         	
     		else if(chooseMethod.contains("inorder")){
     			System.out.print("\t");
     			mainTree.inorder(mainTree.getRoot());
     			System.out.println();
     		}
     		else if(chooseMethod.contains("postorder")){
     			mainTree.postorder(mainTree.getRoot());
     			System.out.println();
     		}
     		else if(chooseMethod.contains("minimum")){
     			
     			System.out.println("\tThe current minimum value of the bst is: " + mainTree.minimum(mainTree.getRoot().getNumber()).getNumber());
     			
     		}
     		else if(chooseMethod.contains("maximum")){
     			
     			System.out.println("\tThe current maximum value of the bst is: " + mainTree.maximum(mainTree.getRoot().getNumber()).getNumber());
     			
     		}else if(chooseMethod.contains("median")){
     			if (mainTree.getMedian() != null) {
     				System.out.println("\tThe current median of the bst is: " + mainTree.getMedian().getNumber());
     			}else
     				System.out.println("\tThere is no median in this tree");
     		}
     		
     		else if (chooseMethod.matches(".*\\d+.*")) {
     			
     			Pattern p = Pattern.compile("-?\\d+");
    			Matcher m = p.matcher(chooseMethod);
    			
    			
    			while (m.find()) {
    				nodeValu = Integer.parseInt(m.group());
    				if (chooseMethod.contains("insert")) {
    	     			
    	     			 mainTree.insert(nodeValu);    			
    	     		}
    	     		else if(chooseMethod.contains("delete")){
    	     			
    	     			
    	     			mainTree.delete(nodeValu);
    	     			
    	     			
    	     		}
    	     		else if(chooseMethod.contains("find")){
    	     			
    	     			System.out.println(mainTree.find(nodeValu));
    	     					
    	     		}
    	     		else if(chooseMethod.contains("successor")){
    	     			Node succ;
    	     			if ((succ = mainTree.successor(nodeValu)) != null)
    	     			System.out.println(succ.getNumber());
    	     			
    	     		}
    	     		else if(chooseMethod.contains("predecessor")){
    	     			Node pred;
    	     			if ((pred = mainTree.predecessor(nodeValu)) != null)
    	     			System.out.println(pred.getNumber());
    	     			
    	     		}else {
    	     			if (isFile) {
    		     			System.out.println("Unknown command at line number: " + lineNumber);   			
    		     			readFile(input, mainTree, lineNumber);
    	     			}else {
    	     				System.out.println("Unknown command");
    	     				keyboardInput(mainTree, lineNumber);
    	     			}
    	     		}
    			}
     		}
     		else {
     			if (isFile) {
	     			System.out.println("\tUnknown command at line number: " + lineNumber);   			
     			}else {
     				System.out.println("\tUnknown command"); 
     			}
     		}
										
			if(isFile) {
				readFile(input, mainTree, lineNumber);
			}
			else {
				
				keyboardInput(mainTree, lineNumber);
			}
			
	}
	
}



