package com.com.org.elevator;

import com.com.org.datastructures.Direction;
import com.com.org.datastructures.ElevatorState;
import com.com.org.datastructures.Request;

import java.util.*;

import static java.lang.Math.abs;


class RequestComparator implements Comparator<Request> {

    private final Direction direction;
    private final int floor;

    public RequestComparator(Direction direction, int floor) {
        this.direction = direction;
        this.floor = floor;
    }



    //return "+" if r2 > r1, "-" if r1 > r2, "0" if r1 == r2
    @Override
    public int compare(Request r1, Request r2) {
        int delta1 = r1.floor - floor;
        int delta2 = r2.floor - floor;
        //if IDLE, then best is thath, which is the closest
        if(direction == Direction.IDLE) {
            return abs(delta1) - abs(delta2);
        }
        //otherwise, best is that, which has the same direction and is the closest in direction
        //UP
        if(direction == Direction.UP) {
            if(r1.direction == Direction.UP) {
                if(r2.direction == Direction.UP) {
                    if(delta1 * delta2 < 0) {
//                        if(delta1 > 0) {
//                            return -1;
//                        }
//                        else{
//                            return 1;
//                        }
                        return delta2;
                    }
                    //same sign -> better closer
                    return abs(delta1) - abs(delta2);
                }
                else{
                    return -1;
                }
            }
            else if(r2.direction == Direction.UP) {
                return 1;
            }
        }
        //DOWN
        if(direction == Direction.DOWN) {
            if(r1.direction == Direction.DOWN) {
                if(r2.direction == Direction.DOWN) {
                    if(delta1 * delta2 < 0) {
//                        if(delta1 > 0){
//                            return 1;
//                        }
//                        else{
//                            return -1;
//                        }
                        return delta1;
                    }
                    //same sign -> better closer
                    return abs(delta1) - abs(delta2);
                }
                else{
                    return -1;
                }
            }
            else if(r2.direction == Direction.DOWN) {
                return 1;
            }
        }
        //if equal
        return 0;
    }
}



public class Elevator {

    public final int id;
    private boolean enabled;
    private int currentFloor;
    private int destinationFloor;
    private Direction direction;
    private boolean doorOpen;
    private final List<Request> requests;
    private Request nextRequest;

    public Elevator(int id) {
        this.id = id;
        currentFloor = 0;
        destinationFloor = 0;
        direction = Direction.IDLE;
        doorOpen = false;
        requests = new ArrayList<>();
        nextRequest = null;
    }

    public void update(){
        if(enabled){
            doorOpen = false;
            currentFloor += direction.value;
            if(nextRequest.floor == currentFloor){
                doorOpen = true;
                elevatorLogic();
            }
        }
    }

    public void addRequest(Request request){
        if(!requests.contains(request)){
            requests.add(request);
        }
        elevatorLogic();
    }

    public void removeRequest(Request request){
        requests.remove(request);
    }

    public List<Request> getRequests() {
        return new ArrayList<>(requests);
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


    private void findNextRequest(){
        Comparator<Request> comparator = new RequestComparator(direction, currentFloor);
        nextRequest = Collections.max(requests, comparator);
    }

    private void findDirection(){
        direction = nextRequest.direction;
    }

    private void elevatorLogic(){
        findNextRequest();
        findDirection();
    }


}
