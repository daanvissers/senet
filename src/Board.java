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
	
	public void movePiece(Integer m, Integer piece, String sign) {
		
		Integer newPos = m + piece;
		System.out.println(sign+" Moves piecenr"+piece+" "+ m+ "steps, so to position "+newPos);
		
		boolean turnDone = false;
		
		// If square contains opponent, swap them
		if(squares.get(newPos.toString()).equals(opponentOf(sign))) {
			System.out.println("contains opponent");
			squares.put(newPos.toString(), sign);
			squares.put(piece.toString(), opponentOf(sign));
		}
		// If square contains nothing
		else if(squares.get(newPos.toString()).equals(".")) {
			squares.put(newPos.toString(), sign);
			squares.put(piece.toString(), ".");
		}
		// If one of your own pieces occupies square
		else if (squares.get(newPos.toString()).equals(sign)) {
			System.out.println(sign);
			System.out.println(squares.get(newPos.toString()));
			System.out.println("One of your own pieces occupies square "+newPos);
		}	
		print();
	}
	
	public void moveSecondPiece(Integer n, String sign) {		
		Integer i = 30;
		boolean finished = false;
		while(!finished) {
			Integer pos = 9 + n;
			// If square contains opponent, swap them
			if(!squares.get(pos.toString()).equals(sign) && !squares.get(pos.toString()).equals(".")) {
				squares.put(pos.toString(), sign);
				squares.put("9", opponentOf(sign));
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
	
	public void setPieces(String mode) {
		switch(mode) {
			case "0": setMode0();
				break;
			case "1": setMode1();
				break;
			case "2": setMode2();
				break;
			case "3": setMode3();
				break;
			default: System.out.println("Error!");
				break;
		}
	}
	
	// Returns the opposing sign of the given sign
	private String opponentOf(String sign) {
		String opponentSign;
		opponentSign = (sign == "x") ? "o" : "x";
		return opponentSign;
	}
	
	// Game Test Modes
	private void setMode0() {
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
	
	private void setMode1() {
		String[] number = {"1", "2", "4", "6", "8", "10", "12", "14", 
				"16", "17", "18", "20", "21", "23", "24", "25", "26" };
		String[] symbol = {"x", "o", "o", "o", "x", "o", "o", "o", 
				"x", "o", "o", "o", "o", "x", "o", "o", "o" };
		for(int i = 0; i < 17; i++) {
			squares.put(number[i], symbol[i]);
		}
		print();
	}
	
	private void setMode2() {
		String[] number = {"22", "23", "24", "29" };
		String[] symbol = {"o", "o", "o", "x"};
		for(int i = 0; i < 4; i++) {
			squares.put(number[i], symbol[i]);
		}
		print();
	}
	
	private void setMode3() {
		String[] number = {"6", "13", "18", "22", "25", "26", "28", "29" };
		String[] symbol = {"o", "x", "o", "o", "x", "x", "x", "x"};
		for(int i = 0; i < 8; i++) {
			squares.put(number[i], symbol[i]);
		}
		print();
	}

}
