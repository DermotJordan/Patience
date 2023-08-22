package Patience;
import java.util.Collections;
/*
 * Base class CardPile, which contains the most basic and utility methods and the common
 * private variable cardStack which is needed in all piles.
 * 	@version 2.0
 * 	@author Diarmuid Mac Siúrdáin
 */ 
 
import java.util.Stack;
public class CardPile {
	private Stack<Card> cardStack;

	public CardPile() {
		cardStack = new Stack<Card>();
	}
	/*
	 * Shuffle CardStack
	 */
	public void randomiseDeck() {
		Collections.shuffle(cardStack);
	}
	/*
	 * Add Card to CardStack
	 */
	public void addCard(Card card) {
		cardStack.push(card);
	}
	/*
	 * Remove Top card from CardStack
	 */
	public Card removeCard() {
		 return cardStack.pop();
	}
	/*
	 * Change visibility of card to false
	 */
	public void makeInVisible() {
		Card curCard=cardStack.pop();
		curCard.changeVis(false);
		cardStack.push(curCard);
	}
	/*
	 * Change visibility of card to true
	 */
	public void makeVisible() {
		Card curCard=cardStack.pop();
		curCard.changeVis(true);
		cardStack.push(curCard);
	}
	/*
	 * Search for card at indicated index
	 * Return blank card if visibility variable is false
	 */
	public Card getCardAtInd(int index) {
		Card outputForCard;
		if(index<getNumCards() && index >= 0) {
			if (cardStack.elementAt(index).isVis()) {
				outputForCard = cardStack.elementAt(index);
			}else {
				outputForCard=new Card('N',0,'N');
			}
		}else {
			outputForCard=new Card('N',0,'N');
		}
		return outputForCard;
	}
	/*
	 * Returns top card in the stack as a Card class, but does not remove the card from the stack
	 */
	public Card topCard() {
		if (cardStack.isEmpty()==false) {
			if (cardStack.peek().isVis()) {
				return cardStack.peek();
			}
		}
		return new Card(' ', 0, ' ');
	}
	/*
	 * Returns card in the stack as a string, but does not remove the card from the stack
	 */
	public String returnCardInPile(int index) {
		String outputForCard = "      ";
		if(index<getNumCards()) {
			if (cardStack.elementAt(index).isVis()) {
				outputForCard = cardStack.elementAt(index).returnCard();
			}else {
				outputForCard = "[    ]";
			}
		}
		return outputForCard;
	}
	public boolean isCardStackEmpty() {
		return cardStack.isEmpty();
	}
	public int getNumCards() {
		return cardStack.size();
	}
	public void clearPile() {
		cardStack.clear();
	}
	/*
	 * Checks if the source card is eligible to move to the foundation piles indicated in the SuitPile Class
	 */
	public boolean checkMovesToSuitPiles(Card sourceCard, Card destCard) {
		boolean error=false;
		if ((sourceCard.getCardNumber() != destCard.getCardNumber()+1) || (sourceCard.getCardNumber() == 13 && sourceCard.getCardNumber() == 0)) {
			error=true;
		}else if(sourceCard.getSuit() != destCard.getSuit()) {
			error=true;
		}
		return error;
	}
	/*
	 * Checks if the source card is eligible to move to the Lanes
	 */
	public boolean checkMoveToLanes(Card sourceCard, Card destCard) {
		boolean error=false;
		if (((sourceCard.getCardNumber() == destCard.getCardNumber()-1 && sourceCard.whichColour() != destCard.whichColour()) || (sourceCard.getCardNumber() == 13 && destCard.getCardNumber() == 0)) && (sourceCard.getCardNumber() != 0)) {
			error=false;
		}else {
			error=true;
		}
		return error;
	}
}

