import java.util.Random;

public class Trap extends Entity {
	private int movement;
	private String level;

	public Trap(int position) {
		super(position);

		if (new Random().nextBoolean()) {
			level = "strong";
			movement = -4;
		} else {
			level = "weak";
			movement = -2;
		}
	}

	public int getMovement() {
		return movement;
	}

	public void setMovement(int movement) {
		this.movement = movement;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String toString() {
		return String.format(" # ");
	}
}
