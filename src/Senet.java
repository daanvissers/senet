import java.util.ArrayList;

public class Senet {
	
	private Board board;
	private ArrayList<Player> players;
	private Dice dice;
	private int turn;
	
	public Senet() {
		
		players = new ArrayList<>();
		dice = new Dice();
		board = new Board();
		
	}
	
	public void play() {
		
		// Ask which game mode should be played
		System.out.println("Welcome to Senet! \n"
				+ "Would you like to start \n"
				+ "a normal game (0) or a test position (1-3)?");
		String mode = Main.sc.nextLine();
		
		String p1name = null, p2name = null;
		if(mode.equals("0")) {
			// Ask for player names
			System.out.println("Enter the name of the first player:");
			p1name = Main.sc.nextLine();
			
			System.out.println("Enter the name of the second player:");
			p2name = Main.sc.nextLine();
		}

		createGame(mode, p1name, p2name);
		
		playGame(mode);
		
	}

	private void createGame(String mode, String p1name, String p2name) {
		switch(mode) {
			case "0": this.players.add(new Player(p1name));
					  this.players.add(new Player(p2name));
					  break;
			default:  this.players.add(new Player("black"));
					  this.players.add(new Player("white"));
					
		}
		setColorSigns();
	}
	
	private void playGame(String mode) {
		
		// Set the pieces on the board
		board.setPieces(mode);
		
		if(mode.equals("0")) {
			// First determine who starts first
			determineStarter();
			
			// Do the second move
			System.out.println(players.get(turn).getName() 
					+ " (" + players.get(turn).getColorSign() + "), press <ENTER> to throw the dice" );
			Main.sc.nextLine();
			Integer n = (Main.CHEAT) ? Integer.parseInt(Main.sc.nextLine()) : dice.throwSticks();
			System.out.println(players.get(turn).getName() 
					+ " (" + players.get(turn).getColorSign() + "), you have thrown " + n);

			board.moveSecondPiece(n, players.get(turn).getColorSign());
		}
		
		switchTurn();
		
		// Repeat the process of the game
		boolean gameFinished = false;
		while(!gameFinished) {
			
			// Repeat turns if 1,4,6 has been thrown
			boolean turnFinished = false;
			while(!turnFinished) {
				
				// Throw dice
				System.out.println(players.get(turn).getName() 
						+ " (" + players.get(turn).getColorSign() + "), press <ENTER> to throw the dice" );
				Main.sc.nextLine();
				Integer n = (Main.CHEAT) ? Integer.parseInt(Main.sc.nextLine()) : dice.throwSticks();
				
				System.out.println(players.get(turn).getName() 
						+ " (" + players.get(turn).getColorSign() + "), you have thrown " + n);
				System.out.println(players.get(turn).getName() 
						+ " (" + players.get(turn).getColorSign() + "), which piece do you want to move?" );
				System.out.print("-> ");
				Integer piece = Integer.parseInt(Main.sc.nextLine());
				
				board.movePiece(n, piece, players.get(turn).getColorSign());
				
				turnFinished = (n == 1 || n == 4 || n == 6) ? false : true;
			}
			switchTurn();
		}
		
	}
	
	private void determineStarter() {
		
		boolean decided = false;
		int n = 0;
		while(!decided) {
			switchTurn();
			n = dice.throwSticks();
			System.out.println(players.get(turn).getName() + " has thrown " + n);
			decided = (n == 1) ? true : false;
		}
		System.out.println(players.get(turn).getName() + " starts the game");
		switchTurn();
		board.print();
	}
	
	private void setColorSigns() {
		players.get(turn).setColorSign("x");
		switchTurn();
		players.get(turn).setColorSign("o");
	}
	
	private void switchTurn() {
		turn = (turn == 0) ? 1 : 0;
	}

}
