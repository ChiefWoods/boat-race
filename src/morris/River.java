package morris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class River {
	private static int riverLength = 100;
	private ArrayList<Entity> riverView;

	private int trapCount;
	private int currentCount;
	private int shipyardCount;

//	private Entity entity1;

	private int newPosition;
	private int oldEveryonePosition;

	private String[] trapMessages = { "It's a trap! Your boat has been sent back 2 tiles!", "Blistering barnacles! Your boat took 2 tiles back...",
			"Your boat is forced to retreat 2 tiles back. Terrible day to be in the river...", "Slimy sea urchins! More traps! Your boat has to take 2 tiles back..." };
	private String[] currentMessages = { "The current has favoured us! The boat has advanced 3 tiles ahead!", "Tides are turning to our advantage! The boat has sailed 3 tiles ahead!",
			"A strong current has appeared! The boat has moved forward by 3 tiles.", "Ahoy there! The boat is pushed forward by 3 tiles." };
	private String[] shipyardMessages = { "Boat repaired! 20 health points replenished, boat moves 1 tile ahead",
			"These repairs should keep the boat in one piece. 20 health points replenished, boat advances by 1 tile.", "A shipyard! 20 health points replenished, boat moves 1 tile forward." };
	private String[] waterMessages = { "Hello there! Your boat has stopped on the same tile with another player.", "Your boat is now on the same tile with another player. This is awkward.",
			"Shiver me timbers, it's a Kraken! Oh nevermind, it's just another boat. Your boat is now sharing the same tile with another player." };

	// Test instances, delete in final version
	private Entity everyone1;
	private Player player1, player2;
	private int playerTurn = 1;

	public River() {
		trapCount = 15;
		currentCount = 15;
		shipyardCount = 5;
		setBlankRiver();
		entity1 = new Entity(int trapCount, int currentCount, int shipyardCount);
		addEntities();
	}

	public River(int trapCount, int currentCount, int shipyardCount) {
		this.trapCount = trapCount;
		this.currentCount = currentCount;
		this.shipyardCount = shipyardCount;
		setBlankRiver();
		entity1 = new Entity(int trapCount, int currentCount, int shipyardCount);
		addEntities();
	}

	private void setBlankRiver() { // populates riverList with objects
		riverView.add(new Start());
		riverView.add(everyone1);
		for (int a = 0; a < 99; a++) {
			riverView.add(new Water());
		}
		riverView.add(new End());
	}

	private void generatePosition() {
		Random rd = new Random();
		ArrayList<Integer> entityPosition = new ArrayList<Integer>();

		int sum = currentCount + trapCount + shipyardCount;
		for (int i = 0; i < sum; i++) {
			//Generate a random number
			int randomPosition = rd.nextInt(this.riverLength);

			// to avoid item overlapping.
			while (entityPosition.contains(randomPosition) || randomPosition < 10) {
				randomPosition = rd.nextInt(riverLength);
			}

			//Append random position into an ArrayList
			entityPosition.add(randomPosition);
		}
	}

	public void checkHit(PLayer player) {
		while (riverView.get(player.getPosition()) {

		}
	}


	public void check(Player player) {
		while (itemPositionList.contains(player.getPosition())) {
			trapList.forEach(trap -> {
				if (player.getPosition() == trap.getPosition()) {
					trap.moveBackward(player);
					System.out.printf("%s hit a %s trap\n", player.getName(), trap.getLevel());
				}
			});
			currentList.forEach(current -> {
				if (player.getPosition() == current.getPosition()) {
					current.moveForward(player);
					System.out.printf("%s hit a %s current\n", player.getName(), current.getLevel());
				}
			});
		}
	}


	private void addEntities() { // places traps, currents and shipyards randomly on the river
		for (int i = 0; i < trapCount; i++) {
			riverView.set(entity1.getEntityPositions()[i], new Trap());
		}
		for (int j = trapCount; j < trapCount + currentCount; j++) {
			riverView.set(entity1.getEntityPositions()[j], new Current());
		}
		for (int k = trapCount + currentCount; k < trapCount + currentCount + shipyardCount; k++) {
			riverView.set(entity1.getEntityPositions()[k], new Shipyard());
		}
	}

	public int getRiverLength() {
		return riverLength;
	}

	public void setRiverLength(int length) {
		riverLength = length;
	}

	public ArrayList<Entity> getRiverView() {
		return riverView;
	}

	public void setRiverView(ArrayList<Entity> riverView, Player player) { // changes riverList according to player positions
		newPosition = 1 + player.getPlayerPosition();
		oldEveryonePosition = riverView.indexOf(everyone1);
		if (riverView.indexOf(everyone1) != -1) { // if all boats have converged on the previous tile
			if (playerTurn == 1) {
				riverView.set(oldEveryonePosition, player2);
			} else {
				riverView.set(oldEveryonePosition, player1);
			}
			if (riverView.get(newPosition) instanceof Water) { // if new position has no other boats
				riverView.set(newPosition, player);
			} else { // if new position has another boat
				riverView.set(newPosition, everyone1);
			}
		} else { // if only one boat exists on the previous tile
			riverView.set(oldEveryonePosition, new Water());
			if (riverView.get(newPosition) instanceof Water) { // if new position has no other boats
				riverView.set(newPosition, player);
			} else { // if new position has another boat
				riverView.set(newPosition, everyone1);
			}
		}
	}

	public int getTrapCount() {
		return trapCount;
	}

	public void setTrapCount(int traps) {
		trapCount = traps;
	}

	public int getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(int currents) {
		currentCount = currents;
	}

	public int getShipyardCount() {
		return shipyardCount;
	}

	public void setShipyardCount(int shipyards) {
		shipyardCount = shipyards;
	}

	public String toString() {
		return String.format(" %s ", Arrays.toString(riverView.toArray())); // displays riverView spaced out
	}
}
