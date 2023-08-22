package Patience;
/*
 * This is an extension of the Cardpile(super pile). This class describes the deck of the board
* 	@version 2.0
* 	@author Diarmuid Mac Siúrdáin
*/
public class Deck extends CardPile {
	private static char[] SUITS = {'S','D','C','H'};
	private char suitCol;
	public Deck(){
		super();
	}
	/*
	 * Generates the 52 Card objects of the deck, and assigns the color of the suit, the suit, and the card number to the card
	 */
	public void generateDeck() {
		for (char S : SUITS) {
			for (int i = 1;i <=13;i++) {
				if (S == 'D' || S == 'H') {
					 suitCol='R';
				}else {
					suitCol='B';
				}
				addCard(new Card(S,i,suitCol));
			}
		}
	}
	/*
	 * Randomizes the CardStack by calling the method randomizeDeck from CardPile
	 */
	public void shuffleDeck() {
		randomiseDeck();
	}
	/*
	 * Turns over next card in the Deck Pile.
	 * Returns waste pile to Deck pile if deck pile is empty. 
	 */
	public void nextCard(Deck waste) {
		if (!isCardStackEmpty()) {
			if (!topCard().isVis()) {
				makeVisible();
			}else {
				Card temp = removeCard();
				waste.addCard(temp);
				if (!isCardStackEmpty()) {
					makeVisible();
				}
			}
		}else {
			for(int i=waste.getNumCards()-1;i>=0;i--) {
				Card temp=waste.getCardAtInd(i);
				addCard(temp);
				makeInVisible();
			}
			waste.clearPile();
		}

	}
	/*
	 * Makes a move without error checking for the movement of cards between Deck and Waste pile
	 */
	public void noErrorCheckingMoves(CardPile destPile) {
		Card temp = removeCard();
		destPile.addCard(temp);	
		if(!isCardStackEmpty()) {
			makeVisible();
		}
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
		return successFlag;
	}
}