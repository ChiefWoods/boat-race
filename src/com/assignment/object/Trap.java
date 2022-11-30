package com.assignment.object;

import java.util.Random;

public class Trap extends Entity {
    private int movement;
    private String level;

    public Trap(int position) {
        super(position);
        if (new Random().nextBoolean()) {
            level = "Strong";
            movement = -4;
        } else {
            level = "Weak";
            movement = -2;
        }
    }

    public String getLevel() {
        return level;
    }

    public int getMovement() {
        return movement;
    }

    @Override
    public String toString() {
        return "[#]";
    }

}
