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
	
	public void moveSecondPiece(Integer n, String sign) {		
		Integer i = 30;
		boolean finished = false;
		while(!finished) {
			Integer pos = 9 + n;
			// If opponent's piece is on the spot, swap them
			if(!squares.get(pos.toString()).equals(sign) && !squares.get(pos.toString()).equals(".")) {
				squares.put(pos.toString(), sign);
				if(sign.equals("x")) {
					squares.put("9", "o");
				} else {
					squares.put("9", "x");
				}
				finished = true;
			} else if (squares.get(pos.toString()).equals(".")) {
				squares.put(pos.toString(), sign);
				squares.put("9", ".");
				finished = true;
			}
			i--;
		}
		print();
	}
	
	public void setPieces() {
		
		for(Integer i = 1; i < 11; i+=2) {
			squares.put(i.toString(), "o");
			Integer x = i+1;
			if(x == 10) {
				squares.put("11", "x");
			} else {
				squares.put(x.toString(), "x");
			}
		}
		
	}

}
