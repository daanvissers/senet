import java.util.HashMap;

public class Board {
	
	private HashMap<String, String> squares;
	
	public Board() {
		squares = new HashMap<>();
		fillBoard(squares);
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
		
		// Bottom of the scroll
		System.out.println("|       |                                                           /"); 
		System.out.println("|       |----------------------------------------------------------'");
		System.out.println("\\       |");
		System.out.println(" \\     /");
		System.out.println("  `---'");
		
	}
	
	// Moves the piece, according to the rules
	public int movePiece(Integer n, Integer piece, String sign) {
		
		Integer target = n + piece, turnStatus = 0;

		// Choose to pass
		if(piece == 0) turnStatus = 4;
		// If you don't have a piece on the square
		else if(!squares.get(piece.toString()).equals(sign)) {
			System.out.println("You don't have a piece on square "+piece);
		}
		// If all pieces are on last row, allow square 30 / RULE 8
		else if(target == 30) {
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
					squares.put(piece.toString(), ".");
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
		} else if (target > 30) {
			System.out.println("Illegal destination: "+target);
			turnStatus = 0;
		}
		// If square is a trap / RULE 3
		else if(target == 27) {
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
		else if(squares.get(target.toString()).equals(opponentOf(sign))) {
			// If two next to each other, cancel / RULE 5
			Integer a = (target-1), b = (target+1);
			if(squares.get(a.toString()).equals(opponentOf(sign)) || squares.get(b.toString()).equals(opponentOf(sign))) {
				System.out.println("Attack on safe piece: "+ target);
				turnStatus = 0;
			} else {
				// Swap them
				if(target != 26 && target != 28 && target != 29) {
					turnStatus = 1;
					squares.put(target.toString(), sign);
					squares.put(piece.toString(), opponentOf(sign));
				} else {
					turnStatus = 0;
					System.out.println("The piece on this square is invincible!");
				}
			}
		}
		// If square contains nothing
		else if(squares.get(target.toString()).equals(".")) {
			// If jump over 3 opponent pieces / RULE 6
			int jumpCount = 0;
			for(Integer i = piece; i < target; i++) {
				if(squares.get(i.toString()).equals(opponentOf(sign))) jumpCount++;
			}			
			if(jumpCount >= 3) {
				System.out.println("Attempt to jump over blockade");
				turnStatus = 0;
			} else {
				// Place piece on empty square / RULE 1
				squares.put(target.toString(), sign);
				squares.put(piece.toString(), ".");
				turnStatus = 1;
			}
		}
		// If one of your own pieces occupies square
		else if (squares.get(target.toString()).equals(sign)) {
			System.out.println("One of your own pieces occupies square "+target);
			turnStatus = 0;
		}
		return turnStatus;
	}
	
	public void moveSecondPiece(Integer n, String sign) {		
		Integer pos = 9 + n;
		if(squares.get(pos.toString()).equals(".")) {
			squares.put(pos.toString(), sign);
			squares.put("9", ".");
		} else {
			squares.put(pos.toString(), sign);
			squares.put("9", opponentOf(sign));
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
			case "4": setMode4();
				break;
			default:  setMode1();
				break;
		}
	}
	
	// Returns the opposing sign of the given sign
	private String opponentOf(String sign) {
		String opponentSign;
		opponentSign = (sign.equals("x")) ? "o" : "x";
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
		print();
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
	
	private void setMode4() {
		String[] number = {"24", "13", "18", "22", "25", "26", "28", "29" };
		String[] symbol = {"o", "x", "o", "o", "x", "x", "x", "x"};
		for(int i = 0; i < 8; i++) {
			squares.put(number[i], symbol[i]);
		}
		print();
	}
	
	// Return how many options you have
	public int countOptions(Integer n, Integer piece, String sign) {
		
		Integer target = n + piece, options = 0;
		
		if(target > 30) {
			options = 0;
		}
		// If all pieces are on last row, allow square 30 / RULE 8
		else if(target == 30) {
			boolean won = true;
			for(Integer i=1; i<=20; i++) {
				if(squares.get(i.toString()).equals(sign)) {
					won = false;
				}
			}
			if(won) options++;
		}
		// If square is a trap / RULE 3
		else if(target == 27) options++;
		// If square contains opponent, swap them / RULE 2
		else if(squares.get(target.toString()).equals(opponentOf(sign))) {
			// If two next to each other, cancel / RULE 5
			Integer a = (target-1), b = (target+1);
			if(squares.get(a.toString()).equals(opponentOf(sign)) || squares.get(b.toString()).equals(opponentOf(sign))) {
			} else {
				if(target != 26 && target != 28 && target != 29) {
					options++;
				}
			}
		}
		// If square contains nothing
		else if(squares.get(target.toString()).equals(".")) {
			// If jump over 3 opponent pieces / RULE 6
			int jumpCount = 0;
			for(Integer i = piece; i < target; i++) {
				if(squares.get(i.toString()).equals(opponentOf(sign))) jumpCount++;
			}			
			if(jumpCount < 3) options++;
		}
		return options;
	}
	
	public HashMap<String, String> getSquares() {
		return this.squares;
	}

}
