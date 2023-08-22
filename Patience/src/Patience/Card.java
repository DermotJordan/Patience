package Patience;
/*
* 	@version 2.0
* 	@author Diarmuid Mac Siúrdáin
*/
public class Card {
	private char suit;
	private int cardNumber;
	private char colour;
	private boolean visibility;
	
	public Card(char s, int num, char col) {
		suit=s;
		cardNumber=num;
		colour=col;
		visibility=false;
	}
	
	/* 
	 * Returns card string for printing the card to the command line
	 */
	public String returnCard() {
		String cardVal;
		switch (cardNumber) {
		case 1:
			cardVal="A";
			break;
		case 11:
			cardVal="J";
			break;
		case 12:
			cardVal="Q";
			break;
		case 13:
			cardVal="K";
			break;
		case 0:
			cardVal=" ";
			break;
		default:
			cardVal=Integer.toString(cardNumber);
		}
		String readCard = cardVal+Character.toString(suit);
		String outputString;
		if (readCard.contains("10")){
			outputString = "["+readCard+" ]";
		}else {
			outputString = "[ "+readCard+" ]";
		}
		return outputString;
		
	}
	public char whichColour() {
		return colour;
	}
	public boolean isVis() {
		return visibility;
	}
	public void changeVis(boolean vis) {
			visibility=vis;
	}
	public int getCardNumber() {
		return cardNumber;
	}
	public char getSuit() {
		return suit;
	}
}