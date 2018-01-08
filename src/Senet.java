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
		
		// board.print();
		
	}

}
