package com.assignment.object;

import java.util.Random;

public class Dice {
    private static int randomNum;

    //Generate and Return a random number btw (1 ~ 6)
    public static int roll() {
        randomNum = new Random().nextInt(6) + 1;
        return randomNum;
    }

}
