package com.assignment.object;

import java.util.Random;

public class Dice {
    private static int randomNum;

    // Generates and return a random number between 1 to 6 
    public static int roll() {
        randomNum = new Random().nextInt(6) + 1;
        return randomNum;
    }
}
