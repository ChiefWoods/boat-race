package com.assignment.object;

public class Shipyard extends Entity{
    private int movement;

    public Shipyard(int position) {
        super(position);
        movement = 1;
    }

    public int getMovement() {
        return movement;
    }

    @Override
    public String toString() {
        return "[S]";
    }
}
