package Patience;
/*
 * This is an extension of the Cardpile(super pile). This class describes the lanes of the board
* 	@version 2.0
* 	@author Diarmuid Mac Siúrdáin
*/
public class LanePile extends CardPile {
	public LanePile() {
		super();
	}
	/*
	 * Moves Multiple Cards from current pile to the destination pile passed with also the number of cards specified also to the method. (This must be a LanePile)
	 * It returns a boolean true or false to indicate a successful transfer of cards between piles
	 */
	public boolean multiCardMove(int numberCardsToBeMoved, LanePile destPile) {
		Card cardToBeMoved = getCardAtInd(getNumCards()-numberCardsToBeMoved);
		Card destCard = destPile.topCard();
		boolean successFlag=true;
		if (checkMoveToLanes(cardToBeMoved, destCard)) {
			System.out.println("INVALID MOVE PLEASE MAKE A VALID MOVE!");
			successFlag = false;
		}else {
			Card[] cardsToBeMoved = new Card[numberCardsToBeMoved];
			for(int i=0;i<numberCardsToBeMoved;i++) {
				cardsToBeMoved[i]=removeCard();
			}
			for(int i=numberCardsToBeMoved-1;i>=0;i--) {
				destPile.addCard(cardsToBeMoved[i]);
			}
		}
		if (!isCardStackEmpty() && successFlag) {
			makeVisible();
		}
		return successFlag;
	}
	/*
	 * Moves Card from current pile to the destination pile passed to the method. (This must be a LanePile or SuitPile)
	 * It returns a boolean true or false to indicate a successful transfer of cards between piles
	 */
	public boolean checkAndMoveCard(CardPile destPile) {
		Card destCard;
		boolean suitFlag=destPile.getClass().toString().contains("SuitPile");
		if (destPile.isCardStackEmpty() && suitFlag ) {
			SuitPile pile2= (SuitPile)destPile;
			destCard = new Card(pile2.getSuit(),0,pile2.getColour());
		}else {
			destCard = destPile.topCard();
		}
		boolean successFlag=true;
		if (checkMoveToLanes(topCard(), destCard) && !suitFlag) {
			System.out.println("INVALID MOVE PLEASE MAKE A VALID MOVE!");
			successFlag = false;
		}else if(suitFlag && checkMovesToSuitPiles(topCard(), destCard)){
			System.out.println("INVALID MOVE PLEASE MAKE A VALID MOVE!");
			successFlag = false;
		}else {
			Card temp = removeCard();
			destPile.addCard(temp);			
		}
		if (!isCardStackEmpty()) {
			makeVisible();
		}
		return successFlag;
	}

}