
public class Player {
	
	private String name;
	private String colorsign;
	
	public Player(String name, String colorsign) {
		
		this.setName(name);
		this.setColorsign(colorsign);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColorsign() {
		return colorsign;
	}

	public void setColorsign(String colorsign) {
		this.colorsign = colorsign;
	}

}