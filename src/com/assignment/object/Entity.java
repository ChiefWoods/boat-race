package com.assignment.object;

public class Entity {
    private int position;

    public Entity(int position) {
        this.position = position;

    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "[Item]";
    }
}
