/*Samuel Verkruyse
 * 1/28/2016
 * Lab Section 8
 * Project 1
 */

import java.util.Scanner; //Import Scanner for user input

class Converter{ //Class that facilitates the conversion from infix to postfix, and the computation of postfix statements
	
	private static String output = ""; //Creates a placeholder string for output
	private static MyStack<String> test = new MyStack<String>(); //Creates a placeholder stack for data manipulation
	
	public static String infixToPostfix (String expression){ //Takes in an infix expression and turns it into a postfix expression
		output = ""; //Makes the placeholder string empty, in case anything is left in it
		String[] tokenizer = expression.split(" ");//Breaks down the passed expression into tokens
		for(int i=0; i < tokenizer.length; i++){//Goes through every spot in the token array by splitting at spaces in the passed expression
			if(tokenizer[i].equals("")); //If there were multiple spaces, ignore the blank spaces
			else if(tokenizer[i].equals(")")){ //If there is an end parenthesis, this is the highest priority operand
				while(!test.topElement().equals("(")){//Until the top of the stack is open parenthesis
					output += test.pop() + " "; //Add whatever is on the top of the stack to the output string
				}
				if(test.topElement().equals("("))//Once the top of the stack is open parenthesis
					test.pop();//Discard of it without adding to output
			}
			else if(tokenizer[i].equals("(")){//If the token is open parenthesis
				test.push(tokenizer[i]); //Add it to the stack
			}

			else if(tokenizer[i].matches("[/*]")){ //If the next token is a multiplication/division operator
				if(test.isEmpty() || test.topElement().equals("(") || test.topElement().matches("[+-]")) //If the top of the stack is empty, (,+ or -
					test.push(tokenizer[i]);//Put the operator token on top of it
				else//Otherwise
					output += tokenizer[i] + " ";//Put it in the output string
			}
			else if(tokenizer[i].matches("[+-]")){ //If the next token is an addition/subtraction operator
				if(test.isEmpty() || test.topElement().equals("(")) //If the stack is empty or the top is an open parenthesis
					test.push(tokenizer[i]);//Push the operator to the stack
				else if(test.topElement().matches("[/*]")){//If the top of the stack is division or multiplication
					output += test.pop() + " ";//Put the top of the stack on the output string
					if(!test.isEmpty() && test.topElement().matches("[+-]")){//Then if the stack is not empty, and the top is + or -
						output += test.pop() + " ";//Put the operator in the output string
						test.push(tokenizer[i]);//Then put the new operator on top
					}
					else//Otherwise
						test.push(tokenizer[i]);//Just put it on top of the stack
				}
				else if(test.topElement().matches("[-+]")){//If the next element is - or +
					output += test.pop() + " ";//Put the top into the output string
					test.push(tokenizer[i]);//Make the new operator the new top
				}

				else//Otherwise
					output += tokenizer[i] + " ";//Just add the operator to the output string
			}
			
			else//If it's not an operator
				output += tokenizer[i] + " ";//Add whatever it is to the output string
		}
		while(!test.isEmpty())//At the end, loop through the stack until it is empty
			output += test.pop() + " ";//Then add whatever is left to the output string
		return output;//Return the output string
	}
	
	public static double postfixValue (String expression){//Takes a postfix expression and returns the numerical value of the result
		output = "";//Clears the output string
		String[] tokenizer = expression.split(" ");//Makes an array split at every space in the expression
		for(int i=0; i < tokenizer.length; i++){//Goes through every spot in the array
			if(tokenizer[i].equals(""));//If there was an extra space, nullify it
			if(!tokenizer[i].matches("[+-/*]")){//If it is not an operator
				test.push(tokenizer[i]);//Put it on the stack
			}
			else if(tokenizer[i].equals("*")){//If the operator is multiplication
				 test.push(String.valueOf(Double.parseDouble(test.pop()) * Double.parseDouble(test.pop()))); //Convert the strings to doubles, multiply, then store as string
			}
			else if(tokenizer[i].equals("/")){//If the operator is division
				 Double holder = Double.parseDouble(test.pop());
				 test.push(String.valueOf(Double.parseDouble(test.pop()) / holder)); //Convert the strings to doubles, divide, then store as string
			}
			else if(tokenizer[i].equals("+")){//If the operator is addition
				 test.push(String.valueOf(Double.parseDouble(test.pop()) + Double.parseDouble(test.pop()))); //Convert the strings to doubles, add, then store as string
			}
			else if(tokenizer[i].equals("-")){//If the operator is subtraction
				 Double holdertwo = Double.parseDouble(test.pop());
				 test.push(String.valueOf(Double.parseDouble(test.pop()) - holdertwo)); //Convert the strings to doubles, subtract, then store as string
			}
		}
		return Double.parseDouble(test.pop());//Return the remaining string converted to a double

	}
}
class ConverterTest{//Runner class for both of the Converter methods in the Converter class
	
	public static void main(String[] args){//Where the magic happens!
		Scanner in = new Scanner(System.in);//Creates a new scanner for user input
		System.out.println("Choose one of the following operations:");//Prompts the user to make a choice
		System.out.println("-Infix to postfix conversion (enter the letter i)");
		System.out.println("-Postfix expression evaluation (enter the letter p)");
		System.out.println("-Arithmetic expression evaluation (enter the letter a)");
		System.out.println("-Quit the program (enter the letter q)");
		char userInput = in.nextLine().charAt(0);//Takes the first character inputed
		while(userInput != 'q'){//As long as the character does not choose q
			switch (userInput) {//Switch statement dependent on the chosen character
				case 'i': System.out.println("Enter the Infix expression to convert:");//Prompts for an infix expression
						System.out.println("the postfix expression is: " + Converter.infixToPostfix(in.nextLine()));//Calls the infixToPostfix method to convert the infix expression and prints the result
						System.out.println("Make a choice from the menu");//Prompts for another choice to be made
						userInput = in.nextLine().charAt(0);
						break;
				case 'p': System.out.println("Enter the Postfix expression to evaluate");//Prompts for a postfix expression
						System.out.println("the value of the postfix expression is: " + Converter.postfixValue(in.nextLine()));//Calls the postfixValue method and returns the numerical result
						System.out.println("Make a choice from the menu");//Prompts for another choice to be made
						userInput = in.nextLine().charAt(0);
						break;
				case 'a':System.out.println("Enter the Arithmetic expression to evaluate");//Prompts for an arithmetic expression (assumed to be infix)	
						System.out.println("the value of the arithmetic expression is: " + Converter.postfixValue(Converter.infixToPostfix(in.nextLine())));//Converts infix to postfix, then calculates and returns numerical result of postfix
						System.out.println("Make a choice from the menu");//Prompts for another choice to be made
						userInput = in.nextLine().charAt(0);
						break;

				case 'q': System.out.println("Farewell!"); //Says goodbye per request of instructions upon choosing to quit
						break;
				default: System.out.println("Invalid choice"); //If the user is dense and doesn't type a valid character
						System.out.println("Make a choice from the menu");//Prompts for another choice to be made
						userInput = in.nextLine().charAt(0);
						break;
			}
		}

	}
}
