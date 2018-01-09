import java.util.ArrayList;

public class Senet {
	
	private Board board;
	private ArrayList<Player> players;
	private Dice dice;
	
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
		
		// Set the pieces, taking the starter into consideration
		board.setPieces(determineStarter());
		
		// Print the board
		board.print();
		
	}
	
	private int determineStarter() {
		
		boolean decided = false;
		int turn = 0, n = 0;
		while(!decided) {
			turn = switchTurn(turn);
			n = dice.throwSticks();
			System.out.println(players.get(turn).getName() + " has thrown " + n);
			decided = (n == 1) ? true : false;
		}
		System.out.println(players.get(turn).getName() + " starts the game");
		return turn;
		
	}
	
	public int switchTurn(int currentTurn) {
		
		int turn = (currentTurn == 0) ? 1 : 0;
		return turn;
		
	}

}
