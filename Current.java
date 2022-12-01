import java.util.Random;

public class Current extends Entity {
	private int movement;
	private String level;
	
	//constructor
		public Current(int position) {
			super(position);
			
			if (new Random().nextBoolean()) {
				level = "Strong";
				movement = 5;
			}
			else {
				level = "Weak";
				movement = 3;
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
			return String.format("C");
		}
}
