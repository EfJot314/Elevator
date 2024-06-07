package com.com.org.datastructures;

public class ElevatorState {
    public final int id;
    public final int currentFloor;
    public final int destinationFloor;
    public final Direction direction;

    public ElevatorState(int id, int currentFloor, int destinationFloor, Direction direction) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "{id=" + id + ", currentFloor=" + currentFloor + ", destinationFloor=" + destinationFloor + ", direction=" + direction + "}";
    }

}
