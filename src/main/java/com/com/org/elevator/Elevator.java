package com.com.org.elevator;

import com.com.org.datastructures.Direction;
import com.com.org.datastructures.ElevatorState;

public class Elevator {

    public final int id;
    private int currentFloor;
    private int targetFloor;
    private Direction direction;
    private boolean doorOpen;

    public Elevator(int id) {
        this.id = id;
        currentFloor = 0;
        targetFloor = 0;
        direction = Direction.IDLE;
        doorOpen = false;
    }

    public void update(){
        doorOpen = false;
        currentFloor += direction.value;
        if(currentFloor == targetFloor){
            doorOpen = true;
        }
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setTargetFloor(int targetFloor) {
        this.targetFloor = targetFloor;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorState getElevatorState() {
        return new ElevatorState(id, currentFloor, targetFloor, direction);
    }
}
