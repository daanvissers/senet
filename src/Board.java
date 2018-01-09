import java.util.HashMap;

public class Board {
	
	private HashMap<String, String> squares;
	
	public Board() {
		
		squares = new HashMap<>();
		fillBoard(squares);
		
	}

	public HashMap<String, String> getSquares() {
		return squares;
	}

	public void setSquares(HashMap<String, String> squares) {
		this.squares = squares;
	}
	
	private void fillBoard(HashMap<String, String> squares) {
		
		for(Integer i = 1; i <= 30; i++) {
			squares.put(i.toString(), ".");
		}
		
	}
	
	public void print() {
		
		// Top of the board
		System.out.println("+----------+");
		
		// First row
		System.out.print("|");
		for(Integer i = 1; i < 11; i++) {
			System.out.print(squares.get(i.toString()));
		}
		System.out.println("|");
		
		// Second row
		System.out.print("|");
		for(Integer i = 20; i > 10; i--) {
			System.out.print(squares.get(i.toString()));
		}
		System.out.println("|");
		
		// Third row
		System.out.print("|");
		for(Integer i = 21; i < 31; i++) {
			System.out.print(squares.get(i.toString()));
		}
		System.out.println("|");
		
		// Bottom of the board
		System.out.println("+----------+");
		
	}
	
	public void setPieces(int starter) {
		
		if(starter == 0) {
			
			squares.put("1", "o");
			squares.put("3", "o");
			squares.put("5", "o");
			squares.put("7", "o");
			squares.put("9", "o");
			squares.put("2", "x");
			squares.put("4", "x");
			squares.put("6", "x");
			squares.put("8", "x");
			squares.put("11", "x");
			
		} else {
			
			squares.put("1", "x");
			squares.put("3", "x");
			squares.put("5", "x");
			squares.put("7", "x");
			squares.put("9", "x");
			squares.put("2", "o");
			squares.put("4", "o");
			squares.put("6", "o");
			squares.put("8", "o");
			squares.put("11", "o");
			
		}
		
	}

}
