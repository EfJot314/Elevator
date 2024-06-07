package com.com.org.system;

import com.com.org.datastructures.Direction;
import com.com.org.datastructures.ElevatorState;
import com.com.org.elevator.Elevator;
import com.com.org.interfaces.IElevatorSystem;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem implements IElevatorSystem {

    private final List<Elevator> elevators;

    public ElevatorSystem() {
        elevators = new ArrayList<>();
    }

    public void addElevator(int id) {
        Elevator elevator = getElevatorById(id);
        if(elevator == null) {
            this.elevators.add(new Elevator(id));
        }
    }

    public void removeElevator(int id) {
        Elevator elevator = getElevatorById(id);
        if(elevator != null) {
            elevators.remove(elevator);
        }
    }

    @Override
    public void disableElevator(int id) {
        Elevator elevator = getElevatorById(id);
        if(elevator != null) {
            elevator.setEnability(false);
        }
    }

    @Override
    public void enableElevator(int id) {
        Elevator elevator = getElevatorById(id);
        if(elevator != null) {
            elevator.setEnability(true);
        }
    }

    @Override
    public void pickup(int elevatorFloor, Direction direction) {

    }

    @Override
    public void update(int id, int currentFloor, int destinationFloor) {
        Elevator elevator = getElevatorById(id);
        if(elevator != null) {
            elevator.setCurrentFloor(currentFloor);
            elevator.setDestinationFloor(destinationFloor);
        }
    }

    @Override
    public void step() {
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

    private Elevator getElevatorById(int id){
        for(Elevator elevator : elevators) {
            if(elevator.id == id){
                return elevator;
            }
        }
        return null;
    }
}
