/**
 * Imports
 */
import java.util.*;
import java.io.*;
/**
 * This class is designed to check whether or not a certain string is a subsequence of another string
 * @author claireroeder
 * October 1st, 2019
 *
 */
public class Application {
	/**
	 * Defines the scanner so that a user can input information
	 */
	private static Scanner scan;

	/**
	 * Main method
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		/**
		 * Instance variables
		 */
		scan = new Scanner(System.in);
		String firstSeq = "";
		String secondSeq = "";
		String would;
		String fileProcessed;
		
		/**
		 * Counters for loops so that they stop at the correct number of runs
		 */
		int countLastOriginal = 0;
		int c = 0;
		
		/**
		 * Linked Stacks used to compare sequences
		 */
		StackInterface<Character> original = new LinkedStack<>();
		StackInterface<Character> stringOne = new LinkedStack<>();
		StackInterface<Character> stringTwo = new LinkedStack<>();
		StackInterface<Character> third = new LinkedStack<>();
		StackInterface<Character> last = new LinkedStack<>();
		
		/**
		 * Asks the user whether or not they want to enter sequences themselves or through a file
		 */
		System.out.println("Would you like to enter sequences manually or from a file? ");
		String answer = scan.nextLine();
		

/**
 * If statement for the answer that the user chooses, either M or F
 */
	if(answer.equalsIgnoreCase("M")) {
		
		/**
		 * If the user chooses M, this do while loop asks the user for the first and second sequences, then
		 * pushes each character into a respective LinkedList and compares the entries
		 */
				do{
				System.out.println("Enter the first sequence: ");
				firstSeq = scan.next();
				System.out.println("Enter the second sequence: ");
				secondSeq = scan.next();
				
				/**
				 * These ints hold the number of characters in each sequence so that the following loops
				 * run the correct number of times
				 */
				int length1 = firstSeq.length();
				int length2 = secondSeq.length();
				
				/**
				 * This sets up a counter i and pushes each character at that index into
				 * a stringOne LinkedList and a LinkList original that is used as a constant
				 */
				int i= 0;
					do {
						stringOne.push(firstSeq.charAt(i));
						original.push(firstSeq.charAt(i));
						i++;
					}while(i<length1);
				
				
				/**
				 * This sets up a counter j and pushes each character at that index into the stringTwo LinkedList until
				 * there are no more entries left
				 */
				int j = 0;
					do {
						stringTwo.push(secondSeq.charAt(j));
						j++;
					}while(j<length2);
				
				/**
				 * This loop checks to see if the top two entries in the stringOne and stringTwo Linked Lists
				 * are equivalent. If they are, that character from stringTwo is pushed into a third Linked List to save it while checking the others.
				 * The top entry in stringOne is removed.
				 * If they are not equal, the top entry of  stringTwo is removed.
				 * There is also a counter to keep track of how many equivalent characters are found
				 */
				for(int k = 0; k < length2; k++) {
					if(stringTwo.peek() == stringOne.peek()) {
						third.push(stringTwo.pop());
						stringOne.pop();
						c++;
					}//end if
					else {
						stringTwo.pop();
					}//end else
				
				}//end for
				
				/**
				 * This if statement uses the counter c from the previous loop and checks to see if it equal to
				 * the number of characters in the first sequence.
				 * If it is equal, then all of the entries in the third Linked List are pushed into the "last" one
				 * so that they can be in the same order as the "original" linkedlist that was created
				 */
				if(c == length1) {
					for(int f = 0; f < length1; f++) {
						last.push(third.pop());
					}//end for
					/**
					 * This checks to make sure that the top of entry of the last and the orignal are equivalent, popping
					 * each entry off if they are. It uses the counter countLastOriginal to keep track of how many times it is checked
					 */
					for(int x = 0; x < length1; x++) {
					if(last.peek() == original.peek()) {
						last.pop();
						original.pop();
						countLastOriginal++;
					}//end if
				}//end if
				
				}//end if
				
				/**
				 * If countLastOriginal from the last for loop has the same number as the number of
				 * characters in the first sequence, then it is a subsequence. If not, it is not
				 */
				if(countLastOriginal == length1) {
					System.out.println(firstSeq + " is a subsequence of " + secondSeq);
				}//end if
				else {
					System.out.println(firstSeq + " is NOT a subsequence of " + secondSeq);
				}
				/**
				 * These reset the the linkedlists so that they can be used again for other sequences and set counters to 0.
				 */
				stringOne.clear();
				stringTwo.clear();
				third.clear();
				last.clear();
				c = 0;
				countLastOriginal = 0;
				
				/**
				 * Asks the user if they want to try more sequences
				 */
				System.out.println("Would you like to enter more sequences? Y/N : ");
				would = scan.next();
				
				/**
				 * The expression for the do while loop, it keeps running if the user chooses Y
				 */
				}while(would.equalsIgnoreCase("Y"));
				
				/**
				 * If the user does not choose Y, this prints out
				 */
				System.out.println("<END RUN>");
		
	}//end if
	
	/**
	 * This else if is used if the user chooses F and wants to compare sequences from a file
	 */
	else if(answer.equalsIgnoreCase("F")) {
		
		/**
		 * Prompts the user to enter the name of the file and sets their input equal to fileProcessed
		 */
		System.out.println("Enter the name of the file to be processed: ");
		fileProcessed = scan.next();
		
		/**
		 * This creates a file that the program can read, equal to fileProcessed
		 */
		File file = new File(fileProcessed);
		/**
		 * BufferedReader that reads the file 
		 */
		BufferedReader fileRead = new BufferedReader(new FileReader(file));
		
		String line = null;
		/**
		 * Try/Finally sections to handle exceptions
		 */
		try {
			StringBuffer sb = new StringBuffer("");
			
			
			/**
			 * This do while loops adds all of the elements in the file into one single line
			 */
			while((line = fileRead.readLine()) != null ) {
				sb.append(line + ", ");
			}
			/**
			 * array created that splits each string using the comma and places its elements into an array
			 */
			String[] array = (sb.toString()).split(", ");
			int arraySize = (array.length - 1);
			String[] array2 = new String[arraySize];
			for(int a = 1; a <  arraySize; a++ ) {
				array2[a - 1] = array[a]; 
			}
	
			
			for(int z = 0; z < arraySize ; z = z + 1) {
			
			int length1 = firstSeq.length();
			int length2 = secondSeq.length();
			
			/**
			 * The same loops used for the Manual Entry of sequences
			 */
			int i= 0;
			do {
				stringOne.push(firstSeq.charAt(i));
				original.push(firstSeq.charAt(i));
				i++;
			}while(i<length1);
			
			int j = 0;
			do {
				stringTwo.push(secondSeq.charAt(j));
				j++;
			}while(j<length2);
			
			for(int k = 0; k < length2; k++) {
				if(stringTwo.peek() == stringOne.peek()) {
					third.push(stringTwo.pop());
					stringOne.pop();
					c++;
				}//end if
				else {
					stringTwo.pop();
				}//end else
			
			}//end for
			
			
			if(c == length1) {
				for(int f = 0; f < length1; f++) {
					last.push(third.pop());
				}//end for
				
				for(int x = 0; x < length1; x++) {
				if(last.peek() == original.peek()) {
					last.pop();
					original.pop();
					countLastOriginal++;
				}//end if
			}//end if
			
			}
			if(countLastOriginal == length1) {
				System.out.println(firstSeq + " is a subsequence of " + secondSeq);
			}//end if
			else {
				System.out.println(firstSeq + " is NOT a subsequence of " + secondSeq);
			}
			stringOne.clear();
			stringTwo.clear();
			third.clear();
			last.clear();
			c = 0;
			
		}//end try
		}
		finally{
			
		}
		 
	}
	}//end main


}//end class
