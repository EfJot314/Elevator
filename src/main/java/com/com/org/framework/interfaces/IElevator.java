package com.com.org.framework.interfaces;

import com.com.org.framework.datastructures.Direction;
import com.com.org.framework.datastructures.ElevatorState;
import com.com.org.framework.datastructures.Request;

import java.util.List;

public interface IElevator {

    int getId();
    void update();
    void addRequest(Request request);
    void goTo(int floor);
    void removeRequest(Request request);
    List<Request> getRequests();
    void setCurrentFloor(int currentFloor);
    void setDestinationFloor(int targetFloor);
    void setDirection(Direction direction);
    int getCurrentFloor();
    int getDestinationFloor();
    Direction getDirection();
    boolean isDoorOpen();
    boolean isEnabled();
    void setEnability(boolean enability);
    ElevatorState getElevatorState();


}
