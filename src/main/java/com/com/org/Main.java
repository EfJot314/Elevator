package com.com.org;

import com.com.org.datastructures.Direction;
import com.com.org.datastructures.ElevatorState;
import com.com.org.interfaces.IElevatorSystem;
import com.com.org.system.ElevatorSystem;
import com.com.org.visualization.ElevatorSystemWindow;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        IElevatorSystem elevatorSystem = new ElevatorSystem();

        elevatorSystem.addElevator(1);
        elevatorSystem.addElevator(2);
        elevatorSystem.addElevator(3);
        elevatorSystem.addElevator(4);
        elevatorSystem.addElevator(5);

        List<ElevatorState> systemState1 = elevatorSystem.status();
        System.out.println(systemState1);

        elevatorSystem.pickup(3, Direction.DOWN);


        List<ElevatorState> systemState2 = elevatorSystem.status();
        System.out.println(systemState2);


        ElevatorSystemWindow ew = new ElevatorSystemWindow();

    }

}