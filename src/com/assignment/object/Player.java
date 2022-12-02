package com.assignment.object;

public class Player {

	// Attributes
	private int id;
	private String name;
	private int position;
	private int score;
	private int hp;

	// Constructor
	public Player() {
		
	}
	
	public Player(int id, String name) {
		this.id = id;
		this.name = name;
		this.score = 0;
		this.hp = 100;
		this.position = 0;
	}

	// Setter & Getter
	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPosition(int p) {
		position = p;
	}

	public int getPosition() {
		return position;
	}

	public void setScore(int s) {
		score = s;
	}

	public int getScore() {
		return score;
	}

	public void setHP(int h) {
		hp = h;
	}

	public int getHP() {
		return hp;
	}

	// Methods
	public void move(int step) {
		position += step;
	}

	public void damage() {
		hp -= 15;
	}

	public void repair() {
		hp += 20;
		if (hp > 100) {
			hp = 100;
		}
	}

	@Override
	public String toString() {
		return String.format(" P%d ", this.id);
	}

}
