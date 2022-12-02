package com.assignment.main;

import com.assignment.object.Dice;
import com.assignment.object.Player;
import com.assignment.object.River;
import com.assignment.object.Scoreboard;

import java.util.Scanner;

public class Game {
	// Attributes
	public static Scanner input = new Scanner(System.in);
	public static River river;
	public static Player player1;
	public static Player player2;
	public static String player1Name;
	public static String player2Name;

	public static int currentAmount;
	public static int trapAmount;
	public static int shipyardAmount;

	static String userInput;

	static int count = 0;
	static int movement;

	public static Scoreboard scoreboard = new Scoreboard();

	public static void main(String[] args) {
		scoreboard = new Scoreboard();
		userInput = "0";
		System.out.println("Welcome to Boat Race!");
		System.out.println("The goal of the game is to be the first boat to reach the end of the river, or be the last boat standing!");
		while (userInput.equals("0")) {
			System.out.print("\n[1] Start Classic Game\n[2] Start Sandbox Game\n[3] Display High Scores\nPlease select an option above:  ");
			userInput = input.nextLine();
			switch (userInput) {
			case "1":
				create();
				break;
			case "2":
				createSandBox();
				break;
			case "3":
				System.out.println(scoreboard.toString());
				userInput = "0";
				break;
			default:
				userInput = "0";
				break;
			}
		}

		// Runs game until a player wins or game is terminated
		while (player1.getPosition() < 99 && player2.getPosition() < 99) {
			if (((count % 2) + 1) == 1) {
				System.out.println("It's " + player1.getName() + "'s turn!");
			} else {
				System.out.println("It's " + player2.getName() + "'s turn!");
			}
			System.out.print("Enter any key to roll the dice! Type 'quit' to stop the game.\nEnter key: ");
			userInput = input.nextLine();
			if (userInput.equalsIgnoreCase("quit")) {
				System.out.println("\nTerminating game...\nThanks for playing!");
				break;
			} else {
				play((count % 2) + 1);
			}
		}

		// Records scores of player who won
		if (player1.getPosition() == 99) {
			player1.setScore(count);
			scoreboard.updateTopScores(player1);
		} else if (player2.getPosition() == 99) {
			player2.setScore(count);
			scoreboard.updateTopScores(player2);
		}
		
		System.out.printf("\nScores\n%s --- %d\n%s --- %d\n", player1.getName(), player1.getScore(), player2.getName(), player2.getScore());

		// Updates and displays high scores
		scoreboard.saveScores();
		scoreboard.sortTopScores();
		System.out.println(scoreboard.toString());
	}

	public static void create() {
		// Asks for both players' name
		System.out.print("\nEnter name for Player 1: ");
		player1Name = input.nextLine();
		System.out.print("\nEnter name for Player 2: ");
		player2Name = input.nextLine();

		// Creates Player and River objects
		player1 = new Player(1, player1Name);
		player2 = new Player(2, player2Name);
		river = new River(player1, player2);

		// Display river view
		System.out.printf("%s\n", river.draw());
	}

	public static void createSandBox() {
		// Asks for both players' name
		System.out.print("\nEnter name for Player 1: ");
		player1Name = input.nextLine();
		System.out.print("\nEnter name for Player 2: ");
		player2Name = input.nextLine();

		currentAmount = -1;
		trapAmount = -1;
		shipyardAmount = -1;

		while (currentAmount < 0 || currentAmount > 20) {
			System.out.print("\nChoose amount of Currents to be generated (0-20): ");
			currentAmount = input.nextInt();
		}
		while (trapAmount < 0 || trapAmount > 20) {
			System.out.print("\nChoose amount of Traps to be generated (0-20): ");
			trapAmount = input.nextInt();
		}
		while (shipyardAmount < 0 || shipyardAmount > 20) {
			System.out.print("\nChoose amount of Shipyards to be generated (0-20): ");
			shipyardAmount = input.nextInt();
		}

		// Creates Player and River objects
		player1 = new Player(1, player1Name);
		player2 = new Player(2, player2Name);
		river = new River(player1, player2, currentAmount, trapAmount, shipyardAmount);

		// Display river view
		System.out.println(river.draw());
	}

	public static void play(int turn) {
		// Roll the dice
		movement = Dice.roll();
		count += 1;

		if (turn == 1) {
			System.out.println("Moving boat forward by " + movement + " tile" + (movement == 1 ? "..." : "s..."));
			player1.move(movement);
			if (player1.getPosition() >= 99) {
				player1.setPosition(99);
				System.out.println(river.draw());
				System.out.println(player1.getName() + " has reached the end of the river! " + player1.getName() + " wins!\nThanks for playing!");
			} else {
				river.check(player1);
				if (player1.getHP() > 0) {
					System.out.println(river.draw());
				} else {
					player2.setPosition(99); // Declare Player 2 as winner when Player 1's boat reaches 0 or less hitpoints
					System.out.println(player2.getName() + " wins!");
				}
			}
		} else if (turn == 2) {
			System.out.println("Moving boat forward by " + movement + " tile" + (movement == 1 ? "..." : "s..."));
			player2.move(movement);
			if (player2.getPosition() >= 99) {
				player2.setPosition(99);
				System.out.println(river.draw());
				System.out.println(player2.getName() + " has reached the end of the river! " + player2.getName() + " wins!\nThanks for playing!");
			} else {
				river.check(player2);
				if (player2.getHP() > 0) {
					System.out.println(river.draw());
				} else {
					player1.setPosition(99); // Declare Player 1 as winner when Player 2's boat reaches 0 or less hitpoints
					System.out.println(player1.getName() + " wins!");
				}
			}
		}
	}
}
