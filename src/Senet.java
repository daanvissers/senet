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
		
		// Ask for player names
		System.out.println("Enter the name of the first player:");
		String p1name = Main.sc.nextLine();
		
		System.out.println("Enter the name of the second player:");
		String p2name = Main.sc.nextLine();
		
		createGame(p1name, p2name);
		
		playGame(mode);
		
	}

	private void createGame(String p1name, String p2name) {
		
		this.players.add(new Player(p1name));
		this.players.add(new Player(p2name));
		
	}
	
	private void playGame(String mode) {
		
		// First determine who starts first
		determineStarter();
		
		setColorSigns();
		
		// Set the pieces on the board
		board.setPieces();
		
		// Print the board
		board.print();
		
		// Do the second move
		System.out.println(players.get(turn).getName() 
				+ " (" + players.get(turn).getColorSign() + "), press <ENTER> to throw the dice" );
		Main.sc.nextLine();
		Integer n = dice.throwSticks();
		System.out.println(players.get(turn).getName() 
				+ " (" + players.get(turn).getColorSign() + "), you have thrown " + n);
		board.moveSecondPiece(n, players.get(turn).getColorSign());
		
		// Repeat the process of the game
		boolean gameFinished = false;
		while(!gameFinished) {
			
			// Throw dice
			// Do something on the board
			// switchTurn();
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
