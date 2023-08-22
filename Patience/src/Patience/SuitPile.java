package Patience;
/*
 * This is an extension of the Cardpile(super pile). This class describes the Foundation SuitPiles of the board
* 	@version 2.0
* 	@author Diarmuid Mac Siúrdáin
*/
public class SuitPile extends CardPile {
	private char suit;
	private char colour;
	public SuitPile(char suit) {
		super();
		this.suit=suit;
		if (getSuit()=='S' || getSuit()=='C') {
			colour='B';
		}else {
			colour='R';
		}
	}
	public char getColour() {
		return colour;
	}
	public char getSuit() {
		return suit;
	}
	/*
	 * Searches for LanePile in the string returned from the getClass method.
	 */
	public boolean checkForLanePileClass(CardPile destPile) {
		return destPile.getClass().toString().contains("LanePile");
	}
	/*
	 * Moves Card from current pile to the destination pile passed to the method. (This must be a LanePile)
	 * It returns a boolean true or false to indicate a successful transfer of cards between piles
	 */
	public boolean  moveCard(CardPile destPile) {
		boolean successFlag=false;
		if (checkForLanePileClass(destPile)) {
			if (!checkMoveToLanes(topCard(),destPile.topCard())){
					Card temp = removeCard();
					destPile.addCard(temp);
					successFlag=true;
			}
		}
		return successFlag;
	}
}