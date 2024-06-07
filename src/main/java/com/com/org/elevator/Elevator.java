package com.com.org.elevator;

import com.com.org.datastructures.Direction;
import com.com.org.datastructures.ElevatorState;

public class Elevator {

    public final int id;
    private boolean enabled;
    private int currentFloor;
    private int destinationFloor;
    private Direction direction;
    private boolean doorOpen;

    public Elevator(int id) {
        this.id = id;
        currentFloor = 0;
        destinationFloor = 0;
        direction = Direction.IDLE;
        doorOpen = false;
    }

    public void update(){
        if(enabled){
            doorOpen = false;
            currentFloor += direction.value;
            if(currentFloor == destinationFloor){
                doorOpen = true;
            }
        }

    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setDestinationFloor(int targetFloor) {
        this.destinationFloor = targetFloor;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isDoorOpen() {
        return doorOpen;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnability(boolean enability) {
        this.enabled = enability;
    }

    public ElevatorState getElevatorState() {
        return new ElevatorState(id, currentFloor, destinationFloor, direction);
    }
}
