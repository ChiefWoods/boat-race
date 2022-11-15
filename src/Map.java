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
	private String[] trapMessages; ///
	private String[] currentMessages;
	private String[] shipyardMessages;
	private String[] miscellaneousMessages;

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
		addEntities(int trapCount, int currentCount, int shipyardCount, int[] entityPositions); ///
	}

	public Map(int trapCount, int currentCount, int shipyardCount) {
		this.trapCount = trapCount;
		this.currentCount = currentCount;
		this.shipyardCount = shipyardCount;
		setBlankMap();
		entity1 = new Entity(int trapCount, int currentCount, int shipyardCount);
		addEntities(int trapCount, int currentCount, int shipyardCount, int[] entityPositions); ///
	}

	private void setBlankMap() { // populates mapList with objects
		mapView.add(new Start());
		mapView.add(new Everyone());
		for (int a = 0; a < 99; a++) {
			mapView.add(new Water());
		}
		mapView.add(new End());
	}

	private void addEntities(int trapCount, int currentCount, int shipyardCount, int[] entity1.getEntityPositions()) { // places traps, currents and shipyards randomly onto 
		for (int i = 0; i < trapCount; i++) {
			mapView.set((entity1.getEntityPositions())[i], new Trap());
		}
		for (int j = trapCount; j < trapCount + currentCount; j++) {
			mapView.set((entity1.getEntityPositions())[j],  new Current());
		}
		for (int k = trapCount + currentCount; k < trapCount + currentCount + shipyardCount; k++) {
			mapView.set((entity1.getEntityPositions())[k],  new Shipyard());
		}
		// Alternative
//		private int lowerLimit = 0;
//		private int upperLimit = trapCount;
//		for (int i = lowerLimit; i < upperLimit; i++) {
//			mapView.set(i, new Trap());
//		}
//		lowerLimit += trapCount;
//		upperLimit += currentCount;
//		for (int j = lowerLimit; j < upperLimit; j++) {
//			mapView.set(j,  new Current());
//		}
//		lowerLimit += currentCount;
//		upperLimit += shipyardCount;
//		for (int k = lowerLimit; k < upperLimitt; k++) {
//			mapView.set(k,  new Shipyard());
//		}
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
