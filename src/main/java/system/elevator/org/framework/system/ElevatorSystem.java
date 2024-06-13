package system.elevator.org.framework.system;

import system.elevator.org.framework.datastructures.Direction;
import system.elevator.org.framework.datastructures.ElevatorState;
import system.elevator.org.framework.datastructures.Request;
import system.elevator.org.framework.elevator.Elevator;
import system.elevator.org.framework.interfaces.IElevator;
import system.elevator.org.framework.interfaces.IElevatorSystem;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class ElevatorSystem implements IElevatorSystem {

    private final List<IElevator> elevators;
    private final List<Request> requests;

    public ElevatorSystem() {
        elevators = new ArrayList<>();
        requests = new ArrayList<>();
    }

    public void addElevator(int id) {
        IElevator elevator = getElevator(id);
        if(elevator == null) {
            this.elevators.add(new Elevator(id, this));
        }
    }

    @Override
    public int getNumberOfElevators() {
        return this.elevators.size();
    }

    public void removeElevator(int id) {
        IElevator elevator = getElevator(id);
        if(elevator != null) {
            elevators.remove(elevator);
        }
    }

    @Override
    public void disableElevator(int id) {
        IElevator elevator = getElevator(id);
        if(elevator != null) {
            elevator.setEnability(false);
        }
    }



    @Override
    public void enableElevator(int id) {
        IElevator elevator = getElevator(id);
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
        for(IElevator elevator : elevators) {
            elevator.removeRequest(request);
        }
        requests.remove(request);
    }

    @Override
    public void update(int id, int currentFloor, int destinationFloor) {
        IElevator elevator = getElevator(id);
        if(elevator != null) {
            elevator.setCurrentFloor(currentFloor);
            elevator.setDestinationFloor(destinationFloor);
        }
    }

    private void addRequestToElevators(Request request) {
        //first iteration
        boolean foundElevator = false;
        for(IElevator elevator : elevators) {
            //if elevator is IDLE
            if(elevator.getDirection() == Direction.IDLE){
                elevator.addRequest(request);
                foundElevator = true;
            }
            //if elevator has same direction as request and has appropriate current floor
            if(request.direction == elevator.getDirection()){
                if(request.direction == Direction.DOWN && elevator.getCurrentFloor() >= elevator.getDestinationFloor()){
                    elevator.addRequest(request);
                    foundElevator = true;
                } else if(request.direction == Direction.UP && elevator.getCurrentFloor() <= elevator.getDestinationFloor()){
                    elevator.addRequest(request);
                    foundElevator = true;
                }
            }
        }
        //second iteration
        if(!foundElevator) {
            for(IElevator elevator : elevators) {
                //if elevator has same direction as request
                if(request.direction == elevator.getDirection()){
                    if(request.direction == Direction.DOWN){
                        elevator.addRequest(request);
                        foundElevator = true;
                    } else if(request.direction == Direction.UP){
                        elevator.addRequest(request);
                        foundElevator = true;
                    }
                }
            }
            //third iteration
            if(!foundElevator) {
                //find elevator with the closest destination floor
                int distance = 1000;
                IElevator chosenElevator = elevators.get(0);
                for(IElevator elevator : elevators) {
                    int d = abs(elevator.getDestinationFloor() - request.floor);
                    if(d < distance){
                        chosenElevator = elevator;
                    }
                }
                chosenElevator.addRequest(request);
            }
        }
    }

    @Override
    public void step() {
        //move
        for(IElevator elevator : elevators) {
            elevator.update();
        }
    }

    @Override
    public List<ElevatorState> status() {
        List<ElevatorState> elevatorStates = new ArrayList<>();
        for(IElevator elevator : elevators) {
            elevatorStates.add(elevator.getElevatorState());
        }
        return elevatorStates;
    }

    public IElevator getElevator(int id){
        for(IElevator elevator : elevators) {
            if(elevator.getId() == id){
                return elevator;
            }
        }
        return null;
    }
}
