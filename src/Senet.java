import java.util.ArrayList;

public class Senet {
	
	private Board board;
	private ArrayList<Player> players;
	private Dice dice;
	private int turn;
	
	public void play() {
		
		// Ask which game mode should be played
		System.out.println("Welcome to Senet! \n"
				+ "Would you like to start \n"
				+ "a normal game (0) or a test position (1-3)?");
		String mode = Main.sc.nextLine();
		
		// Ask for player names
		String p1name = null, p2name = null;
		if(mode.equals("0")) {
			System.out.println("Enter the name of the first player:");
			p1name = Main.sc.nextLine();
			
			System.out.println("Enter the name of the second player:");
			p2name = Main.sc.nextLine();
		}

		createGame(mode, p1name, p2name);
		
		playGame(mode);
		
	}

	private void createGame(String mode, String p1name, String p2name) {
		
		players = new ArrayList<>();
		dice = new Dice();
		board = new Board();
		
		switch(mode) {
			case "0": this.players.add(new Player());
					  this.players.add(new Player());
					  initialMoves(p1name, p2name);
					  break;
			default:  this.players.add(new Player("black", "x"));
					  this.players.add(new Player("white", "o"));
					  break;					
		}
	}
	
	private void initialMoves(String p1name, String p2name) {
		
		// First determine who starts first
		determineStarter(p1name, p2name);
		
		// Set pieces on the board, and print
		board.setPieces("0");
		
		// Do the second move
		System.out.println(players.get(turn).getName() 
				+ " (" + players.get(turn).getColorSign() + "), press <ENTER> to throw the dice" );
		if(!Main.CHEAT) Main.sc.nextLine();
		Integer n = (Main.CHEAT) ? n = Integer.parseInt(0 + Main.sc.nextLine()) : dice.throwSticks();
		System.out.println(players.get(turn).getName() 
				+ " (" + players.get(turn).getColorSign() + "), you have thrown " + n);
		board.moveSecondPiece(n, players.get(turn).getColorSign());
		switchTurn();
	}
	
	private void playGame(String mode) {
		
		// Set the pieces on the board
		if(!mode.equals("0")) board.setPieces(mode);
		
		// turnStatus: 0 == turn is not complete, 1 == turn is completed, 
		// 			   2 == winning turn,         4 == pass
		int turnStatus = 0;
		
		// While the game hasn't been won
		while(turnStatus != 2) {
			boolean throwAgain = true;
			while(throwAgain) {
				// Throw dice
				System.out.println(players.get(turn).getName() 
						+ " (" + players.get(turn).getColorSign() + "), press <ENTER> to throw the dice" );
				if(!Main.CHEAT) Main.sc.nextLine();
				Integer n = (Main.CHEAT) ? n = Integer.parseInt(0 + Main.sc.nextLine()) : dice.throwSticks();
				System.out.println(players.get(turn).getName() 
						+ " (" + players.get(turn).getColorSign() + "), you have thrown " + n);
				
				turnStatus = 0;
				
				// While a turn is not complete
				while(turnStatus == 0) {
					
					// Choose piece to move
					System.out.println(players.get(turn).getName() 
							+ " (" + players.get(turn).getColorSign() + "), which piece do you want to move? Press <ENTER> to pass" );
					System.out.print("-> ");
					Integer piece = Integer.parseInt(0 + Main.sc.nextLine());
					
					turnStatus = board.movePiece(n, piece, players.get(turn).getColorSign());
				}
				
				// Decide if player can make another throw
				if(turnStatus != 2 && turnStatus != 4) {
					throwAgain = (n == 1 || n == 4 || n == 6) ? true : false;
				} else {
					throwAgain = false;
				}
				
				board.print();
			}
			switchTurn();
		}
		switchTurn();
		System.out.println("Congratulations " + players.get(turn).getName() + ", you win!");
	}
	
	// Throw dice until someone throws 1
	private void determineStarter(String name1, String name2) {
		
		boolean decided = false, turnTaker = false;
		int n = 0;
		String name = "", nameOpponent = "";
		while(!decided) {
			n = dice.throwSticks();
			name = (turnTaker) ? name2 : name1;
			nameOpponent = (turnTaker) ? name1 : name2;
			System.out.println(name + " has thrown " + n);
			if(n==1) {
				decided = true;
				System.out.println(name + " starts the game");
				players.get(0).setName(name);
				players.get(0).setColorSign("x");
				players.get(1).setName(nameOpponent);
				players.get(1).setColorSign("o");
			} else {
				decided = false;
				turnTaker =! turnTaker;
			}
		}
		if(players.get(turn).getColorSign().equals("x") ) switchTurn();
	}
	
	private void switchTurn() {
		turn = (turn == 0) ? 1 : 0;
	}

}
