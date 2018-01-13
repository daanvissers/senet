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
		
		// Top of the scroll
		System.out.println("                                                               .---.");
		System.out.println("                                                              /  .  \\");
		System.out.println("                                                             |\\_/|   |");
		System.out.println("                                                             |   |  /|");
		System.out.println("  .----------------------------------------------------------------' |");
		System.out.println(" /  .-.                     S   E   N   E   T                        |");
		System.out.println("|  /   \\                                                             |");
		
		// First row
		System.out.print("| |\\_.  |                       ");
		for(Integer i = 1; i < 11; i++) {
			System.out.print(squares.get(i.toString()));
		}
		System.out.println("                           |");
		
		// Second row
		System.out.print("|\\|  |/ |                       ");
		for(Integer i = 20; i > 10; i--) {
			System.out.print(squares.get(i.toString()));
		}
		System.out.println("                           |");
		
		// Third row
		System.out.print("| `---' |                       ");
		for(Integer i = 21; i < 31; i++) {
			System.out.print(squares.get(i.toString()));
		}
		System.out.println("                           |");
		
		// Bottom of the board
		System.out.println("|       |                                                           /"); 
		System.out.println("|       |----------------------------------------------------------'");
		System.out.println("\\       |");
		System.out.println(" \\     /");
		System.out.println("  `---'");
		
	}
	
	public int movePiece(Integer m, Integer piece, String sign) {
		
		Integer newPos = m + piece;
		
		int turnStatus = 0;

		// If you don't have a piece on the square
		if(!squares.get(piece.toString()).equals(sign)) {
			System.out.println("You don't have a piece on square "+piece);
		}
		// If all pieces are on last row, allow square 30 / RULE 8
		else if(newPos == 30) {
			boolean won = true;
			for(Integer i=1; i<=20; i++) {
				if(squares.get(i.toString()).equals(sign)) {
					won = false;
				}
			}
			if(won) {
				// Check if the last row contains any pieces
				int lastRowPieces = 0;
				for(Integer i=21; i<=30; i++) {
					if(squares.get(i.toString()).equals(sign)) lastRowPieces++;
				}
				if(lastRowPieces == 1) {
					// Last piece lands on 30, so you win
					turnStatus = 2;
				} else {
					// Remove piece from the board
					squares.put(piece.toString(), ".");
					turnStatus = 1;
				}
			} else {
				turnStatus = 0;
				System.out.println("Not all your pieces are on the final row");
			}
		} else if (newPos > 30) {
			System.out.println("Illegal destination: "+newPos);
			turnStatus = 0;
		}
		// If square is a trap / RULE 3
		else if(newPos == 27) {
			System.out.println("It's a trap!");
			boolean placed = false;
			Integer begin = 1;
			while(!placed) {
				if(squares.get(begin.toString()).equals(".")) {
					squares.put(begin.toString(), sign);
					squares.put(piece.toString(), ".");
					placed = !placed;
					turnStatus = 1;
				} else {
					begin++;
				}
			}
		}
		// If square contains opponent, swap them / RULE 2
		else if(squares.get(newPos.toString()).equals(opponentOf(sign))) {
			// If two next to each other, cancel / RULE 5
			Integer a = (newPos-1), b = (newPos+1);
			if(squares.get(a.toString()).equals(opponentOf(sign)) || squares.get(b.toString()).equals(opponentOf(sign))) {
				System.out.println("Attack on safe piece: "+ newPos);
				turnStatus = 0;
			} else {
				squares.put(newPos.toString(), sign);
				squares.put(piece.toString(), opponentOf(sign));
				turnStatus = 1;
			}
		}
		// If square contains nothing
		else if(squares.get(newPos.toString()).equals(".")) {
			// If jump over 3 opponent pieces / RULE 6
			int jumpCount = 0;
			for(Integer i = piece; i < newPos; i++) {
				if(squares.get(i.toString()).equals(opponentOf(sign))) jumpCount++;
			}			
			if(jumpCount >= 3) {
				System.out.println("Attempt to jump over blockade");
				turnStatus = 0;
			} else {
				// Place piece on empty square / RULE 1
				squares.put(newPos.toString(), sign);
				squares.put(piece.toString(), ".");
				turnStatus = 1;
			}
		}
		// If one of your own pieces occupies square
		else if (squares.get(newPos.toString()).equals(sign)) {
			System.out.println("One of your own pieces occupies square "+newPos);
			turnStatus = 0;
		}
		return turnStatus;
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
	
	// Test Positions
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
