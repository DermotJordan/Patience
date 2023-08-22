package Patience;
public class Patience {
	/**
	 * A Patience game, uses the package patience which contains (Card.java, CarPile.java, Board.java, Deck.java, SuitPile.Java, LanePile.java, UserCommand.java)
	 * Explanation of game Rules are given upon the running of the game.
	 * 	@param args
	 * 	@version 2.0
	 * 	@author Diarmuid Mac Siúrdáin
	 */

	public static void main(String[] args) {
		UserCommand userCmd= new UserCommand();
		Deck deck= new Deck();
		Deck waste= new Deck();
		deck.generateDeck();
		deck.shuffleDeck();
		Board board= new Board();
		board.dealCards(deck);
		System.out.println("Welcome to Patience!\nThe goal of the game is to place all cards in order on the foundations piles (Eg Spades, Diamonds, Clubs and Diamonds)");
		System.out.println("You may only move cards in descending order onto the lanes labeled 1-7, the next card on each pile must be of a different colour (Eg Red Black Red or Black Red Black");
		System.out.println("There is a deck that you may draw from and then you may move that card to either the foundation or lane piles.");
		System.out.println("The cards on the foundation pile must be added in ascending order and must be of the same suit.");
		System.out.println("This game is run on the command line and the following commands are allowed:");
		System.out.println("    Q: Quits Game.\n    D: Draws new card from pile P.\n    [Label1][Label2]: Moves single card from Label1 to Label2 (Available labels are; 1-7, P/p, S/s, D/d, C/c, H/h).");
		System.out.println("    [Label1][Label2][number]: Multi-card move from Label1 and Label2 given a number no greater then 12 (Available labels are; 1-7, P/p, S/s, D/d, C/c, H/h).");
		do{
			board.printBoard(deck, waste);
			if(!userCmd.requestInput()){
				board.commandToMove(userCmd.getCommand(), deck, waste);
			}else {
				System.out.println("INVALID COMMAND PLEASE TRY AGAIN");
			}
			if (board.isWin()) {
				break;
			}
		} while (userCmd.getCommand()[0] != 'Q' && userCmd.getCommand()[0] != 'q');
		if (board.isWin()) {
			board.printBoard(deck, waste);
			System.out.println("CONGRATULATIONS! YOU WON!\n YOUR SCORE: "+board.getScore()+"     TOTAL MOVES: "+board.getNumMoves());
		}else {
			System.out.println("Exiting game, Thanks for playing! Please try again!");
		}
	}
}