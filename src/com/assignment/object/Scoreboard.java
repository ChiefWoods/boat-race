package com.assignment.object;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Comparator;

//public class Scoreboard {
//	private ArrayList<Player> topScores = new ArrayList<Player>();
//	private Scanner input;
//	private Formatter output;
//	private String filepath = "src/com/assignment/score.txt";
//
//	public void loadScores() {
//		input = openInputFile(filepath);
//		readScores();
//		closeInputFile(input);
//	}
//
//	public void saveScores() {
//		output = openOutputFile(filepath);
//		writeScores();
//		closeOutputFile(output);
//	}
//
//	public Scanner openInputFile(String filename) {
//		Scanner tempinput = null;
//		try {
//			tempinput = new Scanner(new File(filename));
//		} catch (FileNotFoundException fileNotFoundException) {
//			System.err.println(fileNotFoundException);
//			System.exit(1);
//		}
//		return tempinput;
//	}
//
//	public Formatter openOutputFile(String filename) {
//		Formatter tempoutput = null;
//		try {
//			tempoutput = new Formatter(filename);
//		} catch (FileNotFoundException fileNotFoundException) {
//			System.err.println(fileNotFoundException);
//			System.exit(1);
//		}
//		return tempoutput;
//	}
//
//	public void closeInputFile(Scanner input) {
//		if (input != null)
//			input.close();
//	}
//
//	public void closeOutputFile(Formatter output) {
//		if (output != null)
//			output.close();
//	}
//
//	public void readScores() {
//		try {
//			while (input.hasNext()) {
//				Player player = new Player();
//				player.setName(input.next());
//				player.setScore(input.nextInt());
//				topScores.add(player);
//			}
//		} catch (NoSuchElementException elementException) {
//			System.err.println("File improperly formed.");
//			input.close();
//			System.exit(1);
//		} catch (IllegalStateException stateException) {
//			System.err.println("Error reading from file.");
//			System.exit(1);
//		}
//	}
//
//	public void writeScores() {
//		for (int k = 0; k < topScores.size(); k++) {
//			output.format("%s %d\n", topScores.get(k).getName(), topScores.get(k).getScore());
//		}
//	}
//
//	public void addPlayer(Player a) {
//		topScores.add(a);
//	}
//
//	public void sortTopScore() {
//		Collections.sort(topScores, new Comparator<Player>() {
//			@Override
//			public int compare(Player o1, Player o2) {
//				return Integer.valueOf(o2.getScore()).compareTo(o1.getScore());
//			}
//		});
//		Collections.reverse(topScores);
//	}
//
//	public String toString() {
//		String board = "Score Board: \n";
//		for (int i = 0; i < 5; i++) {
//			board += String.format("%d. %s %d rounds\n", i + 1, topScores.get(i).getName(), topScores.get(i).getScore());
//		}
//		return board;
//	}
//}
//	***old code, revise for deletion later***

// Chii Yuen

public class Scoreboard {
	private ArrayList<Player> topScores = new ArrayList<Player>();
	private Scanner input;
	private Formatter output;
	private String filePath = "src/com/assignment/score.txt";

	public Scoreboard() {
		this.loadScores();
		this.sortTopScores();
	}

	public void loadScores() {
		try {
			input = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			System.err.println("File not found.");
			System.exit(1);
		}
		while (input.hasNext()) {
			Player player = new Player();
			player.setName(input.next());
			player.setScore(input.nextInt());
			topScores.add(player);
		}
		closeInput(input);
	}
	
	public void saveScores() {
		try {
			output = new Formatter(new File(filePath));
		} catch (FileNotFoundException e) {
			System.err.println("File not found.");
			System.exit(1);
		} catch (NoSuchElementException elementException) {
			System.err.println("File improperly formed.");
			input.close();
			System.exit(1);
		} catch (IllegalStateException stateException) {
			System.err.println("Error reading from file.");
			System.exit(1);
		}
		for (int j = 0; j < 5; j++) {
			output.format("%s %d\n", topScores.get(j).getName(), topScores.get(j).getScore());
		}
		closeOutput(output);
	}
	
	public void updateTopScores(Player newPlayer) {
		for (int i = 0; i < topScores.size(); i++) {
			if (newPlayer.getScore() > topScores.get(i).getScore()) {
				topScores.add(i, newPlayer);
				trimTopScores();
				break;
			}
		}
	}
	
	public void sortTopScores() {
		Collections.sort(topScores, Comparator.comparing(Player::getScore));
		Collections.reverse(topScores);
		trimTopScores();
	}
	
	public void trimTopScores() {
		while (topScores.size() > 5) {
			topScores.remove(5);
		}
	}

	public void closeInput(Scanner input) {
		if (input != null)
			input.close();
	}
	
	public void closeOutput(Formatter output) {
		if (output != null) {
			output.close();
		}
	}
	
	public ArrayList<Player> getTopScores() {
		return topScores;
	}

	public String toString() {
		String scoreString = "\nHigh Scores\n\n";
		int counter = 1;
		for (Player topScorer : topScores) {
			scoreString += counter + ". ----- " + topScorer.getName() + " ----- " + topScorer.getScore() + "\n";
			counter++;
		}
		return scoreString;
	}
}
