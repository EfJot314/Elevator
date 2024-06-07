package com.com.org.system;

import com.com.org.datastructures.Direction;
import com.com.org.datastructures.ElevatorState;
import com.com.org.interfaces.IElevatorSystem;

import java.util.List;

public class ElevatorSystem implements IElevatorSystem {

    @Override
    public void pickup(int elevatorFloor, Direction direction) {

    }

    @Override
    public void update(int elevatorId, int currentFloor, int destinationFloor) {

    }

    @Override
    public void step() {

    }

    @Override
    public List<ElevatorState> status() {
        return List.of();
    }
}
