package com.assignment.main;

import com.assignment.object.Dice;
import com.assignment.object.Player;
import com.assignment.object.River;

import java.util.Scanner;

public class Main {
    //Attributes
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

    public static void main(String[] args) {

        userInput = "0";
        while (userInput.equals("0")) {
            System.out.println("Pls choose the option below: \n1. Start a new game\n2. Enter Sandbox Mode");
            userInput = input.nextLine();
            if(userInput.equals("1")) {
                create();
            } else if(userInput.equals("2")) {
                createSandBox();
            } else {
                userInput = "0";
            }
        }



        //Play game
        while (player1.getPosition() < 99 && player2.getPosition() < 99) {
            System.out.println("Click enter to roll a dice or 'quit' to end the game!");
            userInput = input.nextLine();
            if (userInput.toLowerCase().equals("quit")) {
                break;
            } else {
                play((count % 2) + 1);
            }
        }

        //TODO Record Score
        //use: { (int) count/2 } to get score
        //because 2 count = 1 round
    }

    public static void create() {

        //TODO: Read txt file and print out top 5 player score
        //print the lowest count score because he use the lowest count to get win

        //Ask 2 player name
        System.out.print("\nName for player 1: ");
        player1Name = input.nextLine();
        System.out.print("\nName for player 2: ");
        player2Name = input.nextLine();


        //Create two new player and the River Map
        player1 = new Player(1, player1Name);
        player2 = new Player(2, player2Name);
        river = new River(player1, player2);

        //Show Map
        System.out.println(river.draw());
    }

    public static void createSandBox() {

        //TODO: Read txt file and print out top 5 player score

        //Ask 2 player name
        System.out.print("\nName for player 1: ");
        player1Name = input.nextLine();
        System.out.print("\nName for player 2: ");
        player2Name = input.nextLine();

        currentAmount = -1;
        trapAmount = -1;
        shipyardAmount = -1;

        while (currentAmount < 0 || currentAmount > 20) {
            System.out.print("\nAmount for Current(0~20): ");
            currentAmount = input.nextInt();
        }
        while (trapAmount < 0 || trapAmount > 20) {
            System.out.print("\nAmount for Trap(0~20): ");
            trapAmount = input.nextInt();
        }
        while (shipyardAmount < 0 || shipyardAmount > 20) {
            System.out.print("\nAmount for Shipyard(0~20): ");
            shipyardAmount = input.nextInt();
        }

        //Create two new player and the River Map
        player1 = new Player(1, player1Name);
        player2 = new Player(2, player2Name);

        river = new River(player1, player2, currentAmount, trapAmount, shipyardAmount);

        //Show Map
        System.out.println(river.draw());
    }

    public static void play(int turn) {
        //Roll the dice
        movement = Dice.roll();
        count += 1;

        if (turn == 1) {
            System.out.println(player1.getName() + "'s turn: \nDice attempt: " + movement);
            player1.move(movement);
            if (player1.getPosition() >= 99) {
                player1.setPosition(99);
                System.out.println(river.draw());
                System.out.println("Player 1 Won");
            } else {
                river.check(player1);

                if (player1.getHp() > 0) {
                    System.out.println(river.draw());
                } else {
                    player2.setPosition(99);       //if player 1 negative hp let player 2 win
                }
            }
        } else if (turn == 2) {
            System.out.println(player2.getName() + "'s turn: \nDice attempt: " + movement);
            player2.move(movement);
            if (player2.getPosition() >= 99) {
                player2.setPosition(99);
                System.out.println(river.draw());
                System.out.println("Player 2 Won");
            } else {
                river.check(player2);
                if (player2.getHp() > 0) {
                    System.out.println(river.draw());
                } else {
                    player1.setPosition(99);      //if player 2 negative hp let player 1 win
                }
            }
        }
    }
}
