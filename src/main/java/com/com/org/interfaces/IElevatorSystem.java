package com.com.org.interfaces;

import com.com.org.datastructures.Direction;
import com.com.org.datastructures.ElevatorState;
import com.com.org.elevator.Elevator;

import java.util.List;

public interface IElevatorSystem {
    void addElevator(int id);
    void removeElevator(int id);
    void disableElevator(int id);
    void enableElevator(int id);
    void pickup(int elevatorFloor, Direction direction);
    void update(int id, int currentFloor, int destinationFloor);
    void step();
    List<ElevatorState> status();

}
