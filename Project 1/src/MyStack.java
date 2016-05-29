/*Samuel Verkruyse
 * 1/28/2016
 * Lab Section 8
 * Project 1
 */

import java.util.*; //Import util, specifically for the scanner

public class MyStack<T> {//Class that implements a version of the Stack ADT
	
	private Node<T> firstNode; //Placeholder for the front of the stack
	
	public void push(T add){//Adds to the top of the stack
		Node<T> pointerNode = firstNode;//Points to the front of the stack
		if(pointerNode == null){//If the front is empty
			firstNode = new Node<T>();//Makes a new firstNode
			firstNode.data = add;//Store whatever is passed in the new frontNode
			return;//Prevent further progression within the method
		}
		pointerNode = new Node<T>(); //Otherwise, make a new node anyway
		pointerNode.data = add;//Store the passed data in the new node
		pointerNode.next = firstNode; //Make the new node point to the frontNode
		firstNode = pointerNode;//Now make the newnode the front
	}
	
	public T pop(){//Allows for the removal of the top of the stack
        try { //Catches errors if the pop method would not end up working
            if(firstNode == null){//If the front is null
                  throw new EmptyStackException(); //Let the user know that the stack is empty
            }
        }
    	catch (Exception e) {//If the exception does occur
              return null; //Return null (useful later)
        }
		Node<T> returner = firstNode; //Otherwise, make a node named returner point to firstNode
		firstNode = firstNode.next;//Change the firstNode to the one following it
		return returner.data;//Return what was in the frontNode
	}
	
	public T topElement(){//Allows for the top element to be disclosed without removal
		try {//Catches errors if there is not a top to the stack
            if(firstNode == null){//If the front is null
                  throw new EmptyStackException();  //Let the user know that the stack is empty
            }
        }
    	catch (Exception e) { //If the exception does occur
              return null;//Return null (useful later)
        }
		return firstNode.data; //Return what is in the frontNode
	}
	
	public boolean isEmpty(){//Tells if the stack is empty
		return firstNode == null;//If the firstNode is null return true, otherwise, returns false
	}
	
	private static class Node<T>{//Constructs nodes
		public T data;//Holds the node data
		public Node<T> next;//Points to what node is next
	}
}

class StackTest{
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);//Creates a scanner for user input
		MyStack<String> test = new MyStack<String>();//Creates an empty stack for user operations
		System.out.println("Choose one of the following operations:");//Prompts user to choose an option
		System.out.println("-push/add (enter the letter a)");
		System.out.println("-pop/delete (enter the letter d)");
		System.out.println("-topElement (enter the letter p)");
		System.out.println("-check if the list is empty (enter the letter e)");
		System.out.println("-Quit (enter the letter q)");
		char userInput = in.nextLine().charAt(0);//Notes the user's choice
		do{
			switch (userInput) {//Switch statement utilizing user input
				case 'a': System.out.println("Enter the String you want to push:");//Prompts user to push a string
						String pushee = in.nextLine();
						test.push(pushee);//Pushes whatever they chose to push
						System.out.println(pushee + " pushed in");//Lets the user know that their data was pushed
						System.out.println("Make a choice from the menu");//Prompts another choice to be made
						userInput = in.nextLine().charAt(0);
						break;
				case 'd':if(test.topElement() == null)//If the top of the stack is null
							System.out.println("Invalid operation on an empty stack");//Let them know that you cannot pop an empty stack
						else//Otherwise
							System.out.println(test.pop() + " popped out");//Let them know what was popped
						System.out.println("Make a choice from the menu");//Prompts another choice to be made
						userInput = in.nextLine().charAt(0);
						break;
				case 'p':if(test.topElement() == null)//If the top of the stack is null
							System.out.println("Invalid operation on an empty stack");//Let them know you cannot look at the topElement of an empty stack
						else//Otherwise
							System.out.println(test.topElement() + " on the top");//Let them know what is on top
						System.out.println("Make a choice from the menu");//Prompts another choice to be made
						userInput = in.nextLine().charAt(0);
						break;
				case 'e': if(test.isEmpty()){//If the stack is empty
							System.out.println("Empty");//Let them know the stack is empty
							
						}
						else{//Otherwise
							System.out.println("Not Empty");//Let them know the stack is not empty
						}
						System.out.println("Make a choice from the menu");//Prompts another choice to be made
						userInput = in.nextLine().charAt(0);
						break;
				case 'q': System.out.println("quitting"); //Quit if the user hits the quit key
						break;
				default: System.out.println("Invalid choice"); //If they choose a different character, let them know that's not allowed, strictly speaking
						System.out.println("Make a choice from the menu");//Prompts another choice to be made
						userInput = in.nextLine().charAt(0);
						break;
			}
		}while(userInput != 'q');////As long as the user does not choose q

	}
}
