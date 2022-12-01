package com.assignment.object;

public class Entity {
	private int position;
	
	// Constructor
	public Entity(int position) {
		this.position = position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public int getPosition() {
		return position;
	}
	
	public String toString() {
		return String.format(" ~ ");
	}
}
