
public class Player {
	
	//Attributes
	private int id;
	private String name;
	private int position;
	private int score;
	private int hp;
	
	//Constructor
	
	public Player(int i, String nm) {
		id = i;
		name = nm;
	}
	
	//Setter & Getter
	
	public void setID (int i) {
		id =i;
	}
	
	public int getID() {
		return id;
	}
	
	public void setName (String nm) {
		name = nm;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPosition (int p) {
		position = p;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setScore (int s) {
		score = s;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setHP (int h) {
		hp = h;
	}
	
	public int getHP() {
		return hp;
	}
	
	//Methods
	public void move (int step) {
		position += step;
	}
	
	public void damage() {
		hp -= 15;
	}
	
	public void repair( ) {
		hp += 20;
	}
	
	//ToString()
	@Override
	public String toString() {
		return String.format("P %d", id);
	}

}
