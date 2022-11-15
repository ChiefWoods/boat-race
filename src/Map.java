import java.util.ArrayList;
import java.util.Arrays;

public class Map extends Game {
	private static int mapLength = 100;
	private ArrayList<Entity> mapView;
	private int trapCount;
	private int currentCount;
	private int shipyardCount;
	private Entity entity1;
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
	private Object player1, player2;
	private int playerTurn = 1;

	public Map() {
		trapCount = 15;
		currentCount = 15;
		shipyardCount = 5;
		setBlankMap();
		entity1 = new Entity(int trapCount, int currentCount, int shipyardCount);
		addEntities();
	}

	public Map(int trapCount, int currentCount, int shipyardCount) {
		this.trapCount = trapCount;
		this.currentCount = currentCount;
		this.shipyardCount = shipyardCount;
		setBlankMap();
		entity1 = new Entity(int trapCount, int currentCount, int shipyardCount);
		addEntities();
	}

	private void setBlankMap() { // populates mapList with objects
		mapView.add(new Start());
		mapView.add(everyone1);
		for (int a = 0; a < 99; a++) {
			mapView.add(new Water());
		}
		mapView.add(new End());
	}

	private void addEntities() { // places traps, currents and shipyards randomly on the map
		for (int i = 0; i < trapCount; i++) {
			mapView.set(entity1.getEntityPositions()[i], new Trap());
		}
		for (int j = trapCount; j < trapCount + currentCount; j++) {
			mapView.set(entity1.getEntityPositions()[j], new Current());
		}
		for (int k = trapCount + currentCount; k < trapCount + currentCount + shipyardCount; k++) {
			mapView.set(entity1.getEntityPositions()[k], new Shipyard());
		}
	}

	public int getMapLength() {
		return mapLength;
	}

	public void setMapLength(int length) {
		mapLength = length;
	}

	public ArrayList<Entity> getMapView() {
		return mapView;
	}

	public void setMapView(ArrayList<Entity> mapView, Player player) { // changes mapList according to player positions
		newPosition = 1 + player.getPlayerPosition();
		oldEveryonePosition = mapView.indexOf(everyone1);
		if (mapView.indexOf(everyone1) != -1) { // if all boats have converged on the previous tile
			if (playerTurn == 1) {
				mapView.set(oldEveryonePosition, player2);
			} else {
				mapView.set(oldEveryonePosition, player1);
			}
			if (mapView.get(newPosition) instanceof Water) { // if new position has no other boats
				mapView.set(newPosition, player);
			} else { // if new position has another boat
				mapView.set(newPosition, everyone1);
			}
		} else { // if only one boat exists on the previous tile
			mapView.set(oldEveryonePosition, new Water());
			if (mapView.get(newPosition) instanceof Water) { // if new position has no other boats
				mapView.set(newPosition, player);
			} else { // if new position has another boat
				mapView.set(newPosition, everyone1);
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
		return String.format(" %s ", Arrays.toString(mapView.toArray())); // displays mapView spaced out
	}
}
