
public class Player {
	
	private String name;
	private String colorSign;
	
	public Player() {
		// Empty constructor, for mode 0
	}
	
	public Player(String name, String sign) {
		// Constructor for mode 1, 2, 3
		this.setName(name);
		this.setColorSign(sign);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColorSign() {
		return colorSign;
	}

	public void setColorSign(String colorSign) {
		this.colorSign = colorSign;
	}

}
