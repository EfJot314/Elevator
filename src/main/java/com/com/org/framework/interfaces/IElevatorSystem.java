package com.com.org.framework.interfaces;

import com.com.org.framework.datastructures.Direction;
import com.com.org.framework.datastructures.ElevatorState;

import java.util.List;

public interface IElevatorSystem {
    void addElevator(int id);
    int getNumberOfElevators();
    IElevator getElevator(int id);
    void removeElevator(int id);
    void disableElevator(int id);
    void enableElevator(int id);
    void pickup(int elevatorFloor, Direction direction);
    void update(int id, int currentFloor, int destinationFloor);
    void step();
    List<ElevatorState> status();

}
