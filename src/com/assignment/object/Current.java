package com.assignment.object;

import java.util.Random;

public class Current extends Entity {
    private int movement;
    private String level;

    public Current(int position) {
        super(position);
        if (new Random().nextBoolean()) {
            level = "Strong";
            movement = 5;
        } else {
            level = "Weak";
            movement = 3;
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
        return "[C]";
    }

}
