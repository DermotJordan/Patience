package Patience;
/*
 * Controls the board of the patience game. It is called by Patience.java and part of the package Patience.
 * 	@version 2.0
 *  @author Diarmuid Mac Siúrdáin
 */
public class Board{
	private LanePile[] lanes = new LanePile[7];
	private SuitPile[] suits = new SuitPile[4];
	private int numMoves;
	private int score;
	private static char[] ALLSUITS = {'S','D', 'C', 'H'};
	private int maxCardsInPile;
	public Board() {
		for (int i=0;i<=6;i++) {
			lanes[i]=new LanePile();
			if (i<4) {
				suits[i]=new SuitPile(ALLSUITS[i]);
			}
		}
		numMoves=0;
		score=0;
		maxCardsInPile=0;
	}
	/*
	 * Deals cards from the deck to the lanes.
	 */
	public void dealCards(Deck deck) {
		int startIndex=0;
		for(int i = 1;i<=7;i++) {
			for(int j=startIndex;j<=6;j++) {
				if (i == j+1){
					Card card=deck.removeCard();
					card.changeVis(true);
					lanes[j].addCard(card);
				}else {
					lanes[j].addCard(deck.removeCard());
				}
			}
			startIndex +=1;
		}
			
	}
	/*
	 * Returns the number of most cards in the lanes
	 */
	public void findMaxCardsInLanes() {
		int numCards=0;
		for (int i=0;i<=6;i++) {
			if (numCards<lanes[i].getNumCards()) {
				numCards=lanes[i].getNumCards();
			}
		}
		maxCardsInPile= numCards;
	}
	/*
	 * Processes the input commands on the board
	 */
	public void commandToMove(char[] cmd,Deck deck,Deck waste) {
		int pile;
		int pile2;
		boolean suitFlag = false;
		boolean deckFlag = false;
		boolean suitFlag2 = false;
		boolean successFlag = false;
		int points = 0;
		if (cmd.length<= 1) {
			if (cmd[0] == 'D' || cmd[0] == 'd') {
				deck.nextCard(waste);
				successFlag = true;
			}
		}else {
			switch (Character.toUpperCase(cmd[0])) {
			case 'S':
				pile=0;
				suitFlag=true;
				break;
			case 'D':
				pile=1;
				suitFlag=true;
				break;
			case 'C':
				pile=2;
				suitFlag=true;
				break;
			case 'H':
				pile=3;
				suitFlag=true;
				break;
			case 'P':
				pile=-1;
				deckFlag=true;
				break;
			default:
				pile=Integer.parseInt(String.valueOf(cmd[0]))-1;
			}
			switch (Character.toUpperCase(cmd[1])) {
			case 'S':
				pile2=0;
				suitFlag2=true;
				break;
			case 'D':
				pile2=1;
				suitFlag2=true;
				break;
			case 'C':
				pile2=2;
				suitFlag2=true;
				break;
			case 'H':
				pile2=3;
				suitFlag2=true;
				break;
			case 'P':
				pile2=-2;
				deckFlag=true;
				break;
			default:
				pile2=Integer.parseInt(String.valueOf(cmd[1]))-1;
			}
			if(deckFlag==true && pile==-1) {
				if (suitFlag2) {
					successFlag = deck.checkAndMoveCard(suits[pile2]);
					if(successFlag && !waste.isCardStackEmpty()) {
						waste.noErrorCheckingMoves(deck);
						points=10;
					}
				}else {
					successFlag = deck.checkAndMoveCard(lanes[pile2]);
					if(successFlag && !waste.isCardStackEmpty()) {
						waste.noErrorCheckingMoves(deck);
					}
				}
			}else if (suitFlag && !deckFlag){
				successFlag = suits[pile].moveCard(lanes[pile2]);
					if (successFlag) {
						points = -20;
					}
			}else if (pile2 != -2){
				if (suitFlag2) {
					successFlag = lanes[pile].checkAndMoveCard(suits[pile2]);
					if (successFlag) {
						points = 20;
					}
				}else if(cmd.length >= 3) {
					int numCardsToBeMoved;
					if (cmd.length>3) {
						numCardsToBeMoved=Integer.parseInt(""+cmd[2]+cmd[3]);
					}else {
						numCardsToBeMoved=Integer.parseInt(""+cmd[2]);
					}
					successFlag = lanes[pile].multiCardMove(numCardsToBeMoved,lanes[pile2]);
					if (successFlag) {
						points=5*numCardsToBeMoved;
					}
				}else {
					successFlag = lanes[pile].checkAndMoveCard(lanes[pile2]);
					if (successFlag) {	
						points=5;
					}
				}
			}
		}
		if (successFlag) {
			addMove();
			addScore(points);
		}
	}
	public void addScore(int points) {
		score+=points;
	}
	public void addMove() {
		numMoves+=1;
	}
	public int getScore() {
		return score;
	}
	public int getNumMoves() {
		return numMoves;
	}
	public boolean isWin() {
		boolean winFlag=false;
		if (suits[0].getNumCards() == 13 && suits[1].getNumCards() == 13 && suits[2].getNumCards() == 13 && suits[3].getNumCards() == 13) {
			winFlag = true;
		}
		return winFlag;
	}
	/*
	 * Prints the board to the standard output
	 */
	public void printBoard(Deck deck, Deck waste) {
		findMaxCardsInLanes();
		System.out.println("\nPoints: "+getScore()+"     Moves: "+getNumMoves());
		System.out.println("   P      W             S      D      C      H");
		String nextRow = deck.topCard().returnCard()+" "+waste.topCard().returnCard()+"        "+suits[0].topCard().returnCard()+" "+suits[1].topCard().returnCard()+" "+suits[2].topCard().returnCard()+" "+suits[3].topCard().returnCard();
		System.out.println(nextRow);
		System.out.println("  1      2      3      4      5      6      7");
		for (int i=0; i<maxCardsInPile; i++) {
			nextRow=lanes[0].returnCardInPile(i)+" "+lanes[1].returnCardInPile(i)+" "+lanes[2].returnCardInPile(i)+" "+lanes[3].returnCardInPile(i)+" "+lanes[4].returnCardInPile(i)+" "+lanes[5].returnCardInPile(i)+" "+lanes[6].returnCardInPile(i);
			System.out.println(nextRow);
		}
	}
}