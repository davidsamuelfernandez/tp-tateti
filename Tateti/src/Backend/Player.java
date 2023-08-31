package Backend;

public class Player {

	String name;
	String item;
	
	public Player(String name, String item) {
		this.item = item;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	

}

