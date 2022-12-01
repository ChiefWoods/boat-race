import java.util.Random;

public class Trap extends Entity{
	private int movement;
	private String level;
	
	//constructor
		public Trap(int position) {
			super(position);
			
			if (new Random().nextBoolean()) {
				level = "Strong";
				movement = -4;
			}
			else {
				level = "Weak";
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
			return String.format("#");
		}
}
