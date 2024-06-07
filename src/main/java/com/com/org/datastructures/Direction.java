package com.com.org.datastructures;

public enum Direction {
    UP(1),
    DOWN(-1),
    IDLE(0);


    public final int value;

    Direction(int value) {
        this.value = value;
    }

}
