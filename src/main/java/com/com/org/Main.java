package com.com.org;

import com.com.org.datastructures.ElevatorState;
import com.com.org.interfaces.IElevatorSystem;
import com.com.org.system.ElevatorSystem;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        IElevatorSystem elevatorSystem = new ElevatorSystem();

        elevatorSystem.addElevator(1);
        elevatorSystem.addElevator(2);
        elevatorSystem.addElevator(3);
        elevatorSystem.addElevator(4);
        elevatorSystem.addElevator(5);

        List<ElevatorState> systemState = elevatorSystem.status();
        System.out.println(systemState);

    }
}