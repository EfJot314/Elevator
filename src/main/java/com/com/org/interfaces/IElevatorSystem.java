package com.com.org.interfaces;

import com.com.org.datastructures.Direction;
import com.com.org.datastructures.ElevatorState;

import java.util.List;

public interface IElevatorSystem {
    void pickup(int elevatorFloor, Direction direction);
    void update(int elevatorId, int currentFloor, int destinationFloor);
    void step();
    List<ElevatorState> status();

}
