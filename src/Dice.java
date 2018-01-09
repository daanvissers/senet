import java.util.Random;

public class Dice {
	
	private Random rnd = new Random();
	
	public int throwSticks() {
		
		// Random ints, 0 = black, 1 = white
		int stickOne = rnd.nextInt(2);
		int stickTwo = rnd.nextInt(2);
		int stickThree = rnd.nextInt(2);
		int stickFour = rnd.nextInt(2);
		
		int n = stickOne + stickTwo + stickThree + stickFour;
		
		// If all sticks are black, make value 6
		n = (n == 0) ? 6 : n;
		
		return n;
		
	}

}
