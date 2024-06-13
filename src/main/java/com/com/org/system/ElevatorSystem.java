package com.com.org.system;

import com.com.org.datastructures.Direction;
import com.com.org.datastructures.ElevatorState;
import com.com.org.datastructures.Request;
import com.com.org.elevator.Elevator;
import com.com.org.interfaces.IElevatorSystem;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem implements IElevatorSystem {

    private final List<Elevator> elevators;
    private final List<Request> requests;

    public ElevatorSystem() {
        elevators = new ArrayList<>();
        requests = new ArrayList<>();
    }

    public void addElevator(int id) {
        Elevator elevator = getElevator(id);
        if(elevator == null) {
            this.elevators.add(new Elevator(id, this));
        }
    }

    @Override
    public int getNumberOfElevators() {
        return this.elevators.size();
    }

    public void removeElevator(int id) {
        Elevator elevator = getElevator(id);
        if(elevator != null) {
            elevators.remove(elevator);
        }
    }

    @Override
    public void disableElevator(int id) {
        Elevator elevator = getElevator(id);
        if(elevator != null) {
            elevator.setEnability(false);
        }
    }



    @Override
    public void enableElevator(int id) {
        Elevator elevator = getElevator(id);
        if(elevator != null) {
            elevator.setEnability(true);
        }
    }

    @Override
    public void pickup(int elevatorFloor, Direction direction) {
        Request request = new Request(elevatorFloor, direction);
        if(!requests.contains(request)) {
            requests.add(request);
            addRequestToElevators(request);
        }
    }

    public void completeRequest(Request request) {
        for(Elevator elevator : elevators) {
            elevator.removeRequest(request);
        }
        requests.remove(request);
    }

    @Override
    public void update(int id, int currentFloor, int destinationFloor) {
        Elevator elevator = getElevator(id);
        if(elevator != null) {
            elevator.setCurrentFloor(currentFloor);
            elevator.setDestinationFloor(destinationFloor);
        }
    }

    private void addRequestToElevators(Request request) {
        for(Elevator elevator : elevators) {
            //if elevator is IDLE
            if(elevator.getDirection() == Direction.IDLE){
                elevator.addRequest(request);
            }
            //if elevator has same direction as request and has appropriate current floor
            if(request.direction == elevator.getDirection()){
                if(request.direction == Direction.DOWN && elevator.getCurrentFloor() >= elevator.getDestinationFloor()){
                    elevator.addRequest(request);
                } else if(request.direction == Direction.UP && elevator.getCurrentFloor() <= elevator.getDestinationFloor()){
                    elevator.addRequest(request);
                }
            }
        }
    }

    @Override
    public void step() {
        //move
        for(Elevator elevator : elevators) {
            elevator.update();
        }

    }

    @Override
    public List<ElevatorState> status() {
        List<ElevatorState> elevatorStates = new ArrayList<>();
        for(Elevator elevator : elevators) {
            elevatorStates.add(elevator.getElevatorState());
        }
        return elevatorStates;
    }

    public Elevator getElevator(int id){
        for(Elevator elevator : elevators) {
            if(elevator.id == id){
                return elevator;
            }
        }
        return null;
    }
}
