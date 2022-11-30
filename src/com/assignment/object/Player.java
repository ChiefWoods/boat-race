package com.assignment.object;

public class Player {
    private int id;
    private String name;
    private int score;
    private int position;
    private int hp;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.score = 0;
        this.hp = 100;
    }

    public void move(int step) {
        this.position += step;
    }

    public void damage() {
        this.hp -= 15;
    }

    public void repair() {
        this.hp += 20;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getHp() {
        return hp;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format("[P%d]", this.id);
    }
}
