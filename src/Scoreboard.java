import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Comparator;

public class Scoreboard {
	private ArrayList<Player> topScores = new ArrayList<Player>();
	private Scanner input;
	private Formatter output;
	private String filepath = "src/score.txt";

	public Scoreboard() {
		this.loadScores();
		this.sortTopScores();
	}

	public void loadScores() {
		try {
			input = new Scanner(new File(filepath));
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
			output = new Formatter(new File(filepath));
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
			if (newPlayer.getScore() < topScores.get(i).getScore()) {
				topScores.add(i, newPlayer);
				trimTopScores();
				break;
			}
		}
	}

	public void sortTopScores() {
		Collections.sort(topScores, Comparator.comparing(Player::getScore));
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
		String scoreString = "\nHigh Scores (Moves Taken)\n\n";
		int counter = 1;
		for (Player topScorer : topScores) {
			scoreString += counter + ". ----- " + topScorer.getName() + " ----- " + topScorer.getScore() + "\n";
			counter++;
		}
		return scoreString;
	}
}
