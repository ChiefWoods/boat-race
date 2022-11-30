package com.assignment.object;

import java.util.ArrayList;
import java.util.Random;

public class River {
    /*
    * This is used as a Map class
    * */

    //Attribute
    private ArrayList<String> riverMap;
    private ArrayList<Integer> entityPositionList;
    private ArrayList<Entity> entityList;

    private Player player1;
    private Player player2;

    private int randomEntityP;

    Random rd = new Random();

    //Constructor
    public River(Player player1, Player player2) {
        riverMap = new ArrayList();
        entityPositionList = new ArrayList<Integer>();
        entityList = new ArrayList<Entity>();

        this.player1 = player1;
        this.player2 = player2;

        //Create a map
        for (int i = 0; i < 100; i++) {
            riverMap.add(" ~ ");
        }

        //Generate trap
        for (int j = 0; j < 15; j++) {
            entityList.add(new Trap(generateRandomPosition()));
        }

        //Generate current
        for (int k = 0; k < 15; k++) {
            entityList.add(new Current(generateRandomPosition()));
        }

        //Generate Shipyard
        for (int l = 0; l < 10; l++) {
            entityList.add(new Shipyard(generateRandomPosition()));
        }
    }
    public River(Player player1, Player player2, int currentAmount, int trapAmount, int shipyardAmount) {
        riverMap = new ArrayList();
        entityPositionList = new ArrayList<Integer>();
        entityList = new ArrayList<Entity>();

        this.player1 = player1;
        this.player2 = player2;

        //Create a map
        for (int i = 0; i < 100; i++) {
            riverMap.add(" ~ ");
        }

        //Generate trap
        for (int j = 0; j < currentAmount; j++) {
            entityList.add(new Current(generateRandomPosition()));
        }

        //Generate current
        for (int k = 0; k < trapAmount; k++) {
            entityList.add(new Trap(generateRandomPosition()));
        }

        //Generate Shipyard
        for (int l = 0; l < shipyardAmount; l++) {
            entityList.add(new Shipyard(generateRandomPosition()));
        }
    }

    public String draw() {
        //Erase and reset string for map
        String paper = "";
        riverMap.replaceAll(x -> " ~ ");    //Water(Empty)

        entityList.forEach(entity -> {
            riverMap.set(entity.getPosition(), entity.toString());
        });

        //Put the player on the map
        if (player1.getPosition() == player2.getPosition()) {
            riverMap.set(player1.getPosition(), "[P1/P2]");
        } else if (player1.getPosition() > 100 || player2.getPosition() > 100) {
            System.out.println("You Won");
        }else {
            riverMap.set(player1.getPosition(), player1.toString());
            riverMap.set(player2.getPosition(), player2.toString());
        }

        //Draw on paper
        for (int i = 0; i < riverMap.size(); i++) {
            paper += riverMap.get(i);
        }

        return paper;
    }

    public void check(Player player) {
        while (entityPositionList.contains(player.getPosition())) {

            entityList.forEach(entity -> {
                if (player.getPosition() == entity.getPosition()) {
                    if (entity instanceof Current) {
                        System.out.printf("%s hit a %s %s\n", player.getName(), ((Current) entity).getLevel(), "Current");
                        if (player.getPosition() + ((Current) entity).getMovement() < 100) {
                            player.move(((Current) entity).getMovement());
                        } else {
                            player.setPosition(100);    //set to last position to avoid out of index
                        }
                    } else if (entity instanceof Trap) {
                        System.out.printf("%s hit a %s %s\n", player.getName(), ((Trap) entity).getLevel(), "Trap");
                        player.damage();
                        System.out.printf("%s Hp: %d\n", player, player.getHp());
                        if (player.getHp() < 0) {
                            player.setPosition(0);    //To avoid the while loop keep looping
                            System.out.printf("GG, %s lose\n", player);
                        } else {
                            player.move(((Trap) entity).getMovement());
                        }
                    } else if (entity instanceof Shipyard) {
                        System.out.printf("%s hit a %s\n", player.getName(), "Shipyard");
                        player.repair();
                        System.out.printf("%s Hp: %d\n", player, player.getHp());

                        if (player.getPosition() + ((Shipyard) entity).getMovement() < 100) {
                            player.move(((Shipyard) entity).getMovement());
                        } else {
                            player.setPosition(100);    //set to last position to avoid out of index
                        }
                    }
                }
            });
        }
    }

    public int generateRandomPosition() {

        randomEntityP = rd.nextInt(riverMap.size());

        // to avoid entity overlapping and the starting point.
        while (entityPositionList.contains(randomEntityP) || randomEntityP < 10) {
            randomEntityP = rd.nextInt(riverMap.size());
        }

        //save the occurred entity position for checking purposes
        entityPositionList.add(randomEntityP);
        return randomEntityP;
    }
}