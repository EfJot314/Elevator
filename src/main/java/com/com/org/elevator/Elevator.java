package com.com.org.elevator;

public class Elevator {

    public final int id;
    private int currentFloor;
    private int targetFloor;
    private int direction;
    private boolean doorOpen;

    public Elevator(int id) {
        this.id = id;
        currentFloor = 0;
        targetFloor = 0;
        direction = 0;
        doorOpen = false;
    }

    public void update(){
        doorOpen = false;
        currentFloor += direction;
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

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public int getDirection() {
        return direction;
    }
}
