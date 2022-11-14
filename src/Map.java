import java.util.ArrayList;

import javax.tools.DocumentationTool.Location;

public class Map {
	private static int mapLength = 100;
	private ArrayList<Location> mapView;
	private int trapCount;
	private int currentCount;
	private int shipyardCount;
	private Location locations1;
	private String[] trapMessages;
	private String[] currentMessages;
	private String[] shipyardMessages;
	private String[] miscellaneousMessages;

	public Map() {
		trapCount = 15;
		currentCount = 15;
		shipyardCount = 5;
		setBlankMap();
		locations1 = new Location(int trapCount, int currentCount, int shipyardCount);
		addLocations(int trapCount, int currentCount, int shipyardCount, int[] locations1.getLocationPositions());
	}

	public Map(int trapCount, int currentCount, int shipyardCount) {
		this.trapCount = trapCount;
		this.currentCount = currentCount;
		this.shipyardCount = shipyardCount;
		setBlankMap();
		locations1 = new Location(int trapCount, int currentCount, int shipyardCount);
		addLocations(int trapCount, int currentCount, int shipyardCount, int[] locations1.getLocationPositions());
	}

	private void setBlankMap() {
		mapView.add(new Start());
		for (int a = 0; a < 100; a++) {
			mapView.add(new Water());
		}
		mapView.add(new End());
	}

	private void addLocations(int trapCount, int currentCount, int shipyardCount, int[] location1.getLocationPositions()) {
		for (int i = 0; i < trapCount; i++) {
			mapView.set((location1.getLocationPositions())[i], new Trap());
		}
		for (int j = trapCount; j < trapCount + currentCount; j++) {
			mapView.set((location1.getLocationPositions())[j],  new Current());
		}
		for (int k = trapCount + currentCount; k < trapCount + currentCount + shipyardCount; k++) {
			mapView.set((location1.getLocationPositions())[k],  new Shipyard());
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
	
	public ArrayList<Location> getMapView() {
		return mapView;
	}
	
	public void setMapView(ArrayList<Location> mapView, Player player) {
		
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
		return String.format("", ); // display mapView spaced out
	}
}
