package Patience;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 * 	Class that takes in the user input and parses it and checks if it's a valid command
* 	@version 2.0
* 	@author Diarmuid Mac Siúrdáin
*/
public class UserCommand{
	private String cmd;
	private char[] command;
	private Scanner input = new Scanner(System.in);
	public UserCommand() {
		cmd = "";
		setCommand(new char[4]);
	}
	/*
	 * Parses user inputted string into a char array
	 */
	public void parseString() {
		command=cmd.toCharArray();
	}
	/*
	 * Requests String input from user, calls parseString and returns a boolean after checking the input command
	 */
	public boolean requestInput() {
		System.out.println("Enter Command: ");
		cmd=input.nextLine();
		parseString();
		return checkCommand();
	}
	public char[] getCommand() {
		return command;
	}
	public void setCommand(char[] piles) {
		this.command = piles;
	}
	/*
	 * Confirms legal input of a command for card movement commands using Regex
	 */
	public boolean checkCardMoves() {
		Pattern firstInputPatterns = Pattern.compile("[^pPsdchSDCH1234567]");
		Matcher matchFirstInputPatterns = firstInputPatterns.matcher(""+command[0]);
		Pattern secondInputPatterns = Pattern.compile("[^sdchSDCH1234567]");
		Matcher matchSecondInputPatterns = secondInputPatterns.matcher(""+command[1]);
		boolean error = false;
		if (matchFirstInputPatterns.find() || matchSecondInputPatterns.find()) {
			error=true;
		}
		return error;
	}
	/*
	 * Confirms legal input of a command for quitting and drawing from deck commands using Regex
	 */
	public boolean checkSingleLetterInput() {
		Pattern lanesCheck = Pattern.compile("[^dqDQ]");
		Matcher matchLanes = lanesCheck.matcher(cmd);
		boolean error = false;
		if (matchLanes.find()) {
			error=true;
		}
		return error;
	}
	/*
	 * Confirms legal input of a command for MultiCard card movement commands using Regex
	 */
	public boolean checkMultipleCardMoves() {
		Pattern firstInputPatterns = Pattern.compile("[^1-7]");
		Matcher matchFirstInputPatterns = firstInputPatterns.matcher(""+command[0]);
		Pattern secondInputPatterns = Pattern.compile("[^1-7]");
		Matcher matchSecondInputPatterns = secondInputPatterns.matcher(""+command[1]);
		Pattern thirdInputPatterns = Pattern.compile("[^2-9]");
		Matcher matchThirdInputPatterns = thirdInputPatterns.matcher(""+command[2]);
		boolean error = false;
		/*
		 * If the amount of cards is greater than 9 we must process a third and fourth char differently to when only 3 char input
		 */
		if (command.length>3) {
			Pattern thirdDoubleDigInputPatterns = Pattern.compile("[^1]");
			Matcher matchThirdDoubleDigInputPatterns = thirdDoubleDigInputPatterns.matcher(""+command[2]);
			Pattern fourthDoubleDigInputPatterns = Pattern.compile("[^0-2]");
			Matcher matchFourhDoubleDigInputPatterns = fourthDoubleDigInputPatterns.matcher(""+command[3]);
			if (matchFirstInputPatterns.find() || matchSecondInputPatterns.find() ||  matchThirdDoubleDigInputPatterns.find() ||matchFourhDoubleDigInputPatterns.find()) {
				error=true;
			}
		}else {
			if (matchFirstInputPatterns.find() || matchSecondInputPatterns.find() ||  matchThirdInputPatterns.find()) {
				error=true;
			}
		}
		return error;
	}
	/*
	 * Checks command using length of cmd string to indicate which check on the input command must be made
	 */
	public boolean checkCommand() {
		boolean error = false;
		if (cmd.length()==1) {
			if (checkSingleLetterInput()) {
				error = true;
			}
		}else if(cmd.length()==2) {
			if (checkCardMoves()) {
				error = true;
			}
		}else if (cmd.length()>=3) {
			if (checkMultipleCardMoves()) {
				error = true;
			}
		}else {
			command="E".toCharArray();
			error = true;
		}
		return error;
	}
}