package com.com.org.interfaces;

import java.util.List;

public interface IElevatorSystem {
    void pickup(int elevatorFloor, int direction);
    void update(int elevatorId, int currentFloor, int destinationFloor);
    void step();
    List<Integer> status();

}
