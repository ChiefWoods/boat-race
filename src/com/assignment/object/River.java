package com.assignment.object;

import java.util.ArrayList;
import java.util.Random;

public class River {
	// Attributes
	private ArrayList<String> riverMap;
	private ArrayList<Integer> entityPositionList;
	private ArrayList<Entity> entityList;

	private Player player1;
	private Player player2;

	private int randomEntityPosition;

	Random rd = new Random();

	// Constructors
	public River(Player player1, Player player2) {
		riverMap = new ArrayList<String>();
		entityPositionList = new ArrayList<Integer>();
		entityList = new ArrayList<Entity>();

		this.player1 = player1;
		this.player2 = player2;

		// Creates map template
		for (int i = 0; i < 100; i++) {
			riverMap.add(" ~ ");
		}

		// Generates traps
		for (int j = 0; j < 10; j++) {
			entityList.add(new Trap(generateRandomPosition()));
		}

		// Generates currents
		for (int k = 0; k < 10; k++) {
			entityList.add(new Current(generateRandomPosition()));
		}

		// Generates shipyards
		for (int l = 0; l < 5; l++) {
			entityList.add(new Shipyard(generateRandomPosition()));
		}
	}

	public River(Player player1, Player player2, int currentAmount, int trapAmount, int shipyardAmount) {
		riverMap = new ArrayList<String>();
		entityPositionList = new ArrayList<Integer>();
		entityList = new ArrayList<Entity>();

		this.player1 = player1;
		this.player2 = player2;

		// Creates map template
		for (int i = 0; i < 100; i++) {
			riverMap.add(" ~ ");
		}

		// Generates traps
		for (int j = 0; j < currentAmount; j++) {
			entityList.add(new Current(generateRandomPosition()));
		}

		// Generates currents
		for (int k = 0; k < trapAmount; k++) {
			entityList.add(new Trap(generateRandomPosition()));
		}

		// Generates shipyards
		for (int l = 0; l < shipyardAmount; l++) {
			entityList.add(new Shipyard(generateRandomPosition()));
		}
	}

	public String draw() {
		// Resets paper to blank
		String paper = "";
		riverMap.replaceAll(x -> " ~ "); // Replaces all tiles in map as blank
		entityList.forEach(entity -> {
			riverMap.set(entity.getPosition(), entity.toString());
		});

		// Sets both players on the map
		if (player1.getPosition() == player2.getPosition()) {
			riverMap.set(player1.getPosition(), " P1/P2 ");
		} else if (player1.getPosition() > 100 || player2.getPosition() > 100) {
			System.out.println("A boat has crossed the last tile!");
		} else {
			riverMap.set(player1.getPosition(), player1.toString());
			riverMap.set(player2.getPosition(), player2.toString());
		}

		// Sets paper to reflect view of riverMap
		for (int i = 0; i < riverMap.size(); i++) {
			paper += riverMap.get(i);
		}
		return paper;
	}

	public void check(Player player) {
		boolean visitedShipyard = false;
		
		while (entityPositionList.contains(player.getPosition()) && !visitedShipyard) {

			for (int i = 0; i < entityList.size(); i++) {
				if (player.getPosition() == entityList.get(i).getPosition()) {
					if (entityList.get(i) instanceof Current) {
						System.out.printf("%s has landed on a %s %s!", player.getName(), ((Current)entityList.get(i)).getLevel(), "current"); // ***change this line when adding custom messages
						if (player.getPosition() + ((Current)entityList.get(i)).getMovement() < 100) {
							player.move(((Current)entityList.get(i)).getMovement());
						} else {
							player.setPosition(99); // Sets player position to last tile to avoid out of bounds
						}
					} else if (entityList.get(i) instanceof Trap) {
						System.out.printf("%s has landed on a %s %s!", player.getName(), ((Trap)entityList.get(i)).getLevel(), "trap"); // ***change this line when adding custom messages
						player.damage();
						System.out.printf("%s's boat has %d hitpoints left.", player, player.getHP());
						if (player.getHP() < 0) {
							player.setPosition(0); // Sets player position to first tile as boat has no hitpoints remaining and player has lost
							System.out.printf("%s's boat has lost all its hitpoints!", player.getName());
						} else {
							player.move(((Trap)entityList.get(i)).getMovement());
						}
					} else if (entityList.get(i) instanceof Shipyard) {
						System.out.printf("%s has landed on a %s!", player.getName(), "shipyard"); // ***change this line when adding custom messages
						player.repair();
						System.out.printf("%s's boat currently has %d hitpoints.", player, player.getHP());
						visitedShipyard = true;
					}
				}
			}
		}
	}

	public int generateRandomPosition() {
		randomEntityPosition = rd.nextInt(riverMap.size());

		// Generates a new position if position generated is already taken, or one of first 10 tiles, or one of last 10 tiles      
		while (entityPositionList.contains(randomEntityPosition) || randomEntityPosition < 10 || randomEntityPosition > riverMap.size() - 10) {
			randomEntityPosition = rd.nextInt(riverMap.size());
		}

		// Saves position generated to be referenced later
		entityPositionList.add(randomEntityPosition);
		return randomEntityPosition;
	}
}