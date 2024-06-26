package system.elevator.org.framework.elevator;

import system.elevator.org.framework.datastructures.Direction;
import system.elevator.org.framework.datastructures.ElevatorState;
import system.elevator.org.framework.datastructures.Request;
import system.elevator.org.framework.interfaces.IElevator;
import system.elevator.org.framework.system.ElevatorSystem;

import java.util.*;

import static java.lang.Math.abs;


class RequestComparator implements Comparator<Request> {

    private final Direction direction;
    private final int floor;

    public RequestComparator(Direction direction, int floor) {
        this.direction = direction;
        this.floor = floor;
    }

    //return "+" if r2 > r1, "-" if r1 > r2, "0" if r1 == r2
    @Override
    public int compare(Request r1, Request r2) {
        int delta1 = r1.floor - floor;
        int delta2 = r2.floor - floor;
        //if IDLE, then best is thath, which is the closest
        if(direction == Direction.IDLE) {
            return abs(delta1) - abs(delta2);
        }
        //otherwise, best is that, which has the same direction and is the closest in direction
        //UP
        if(direction == Direction.UP) {
            if(r1.direction == Direction.UP) {
                if(r2.direction == Direction.UP) {
                    if(delta1 * delta2 < 0) {
//                        if(delta1 > 0) {
//                            return -1;
//                        }
//                        else{
//                            return 1;
//                        }
                        return delta2;
                    }
                    //same sign -> better closer
                    return abs(delta1) - abs(delta2);
                }
                else{
                    return -1;
                }
            }
            else if(r2.direction == Direction.UP) {
                return 1;
            }
        }
        //DOWN
        if(direction == Direction.DOWN) {
            if(r1.direction == Direction.DOWN) {
                if(r2.direction == Direction.DOWN) {
                    if(delta1 * delta2 < 0) {
//                        if(delta1 > 0){
//                            return 1;
//                        }
//                        else{
//                            return -1;
//                        }
                        return delta1;
                    }
                    //same sign -> better closer
                    return abs(delta1) - abs(delta2);
                }
                else{
                    return -1;
                }
            }
            else if(r2.direction == Direction.DOWN) {
                return 1;
            }
        }
        //if equal
        return 0;
    }
}



public class Elevator implements IElevator {

    private final ElevatorSystem elevatorSystem;
    private final int id;
    private boolean enabled;
    private int currentFloor;
    private int destinationFloor;
    private Direction direction;
    private boolean doorOpen;
    private final List<Request> requests;
    private Request nextRequest;

    public Elevator(int id, ElevatorSystem elevatorSystem) {
        this.elevatorSystem = elevatorSystem;

        this.id = id;
        currentFloor = 0;
        destinationFloor = 0;
        direction = Direction.IDLE;
        doorOpen = false;
        requests = new ArrayList<>();
        nextRequest = null;
        enabled = true;
    }


    public int getId() {
        return id;
    }

    public void update(){
        if(enabled && nextRequest != null){
            //if door open -> wait one step
            if(doorOpen){
                doorOpen = false;
                elevatorLogic();
            }
            //otherwise -> move
            else{
                //if there is still a request
                if(requests.contains(nextRequest)){
                    //if cabin is on destination floor -> open door and complete request
                    if( nextRequest.floor == currentFloor){
                        doorOpen = true;
                        direction = Direction.IDLE;
                        destinationFloor = currentFloor;
                        elevatorSystem.completeRequest(nextRequest);
                    }
                    // if request is not completed yet, then move
                    else{
                        currentFloor += direction.value;
                    }
                }
                //if there is no selected request, then find new
                else{
                    elevatorLogic();
                    if(nextRequest != null){
                        currentFloor += direction.value;
                    }
                }
            }
        }
    }

    public void addRequest(Request request){
        if(!requests.contains(request)){
            requests.add(request);
        }
        elevatorLogic();
    }

    public void goTo(int floor){
        //create request to desitnation floor
        Request request;
        if(floor > currentFloor){
            request = new Request(floor, Direction.UP);
        }
        else{
            request = new Request(floor, Direction.DOWN);
        }
        requests.add(request);

        //find new request
        elevatorLogic();
    }

    public void removeRequest(Request request){
        requests.remove(request);
    }

    public List<Request> getRequests() {
        return new ArrayList<>(requests);
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setDestinationFloor(int targetFloor) {
        this.destinationFloor = targetFloor;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isDoorOpen() {
        return doorOpen;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnability(boolean enability) {
        this.enabled = enability;
    }

    public ElevatorState getElevatorState() {
        return new ElevatorState(id, currentFloor, destinationFloor, direction);
    }


    private void findNextRequest(){
        Comparator<Request> comparator = new RequestComparator(direction, currentFloor);
        if(requests.isEmpty()){
            nextRequest = null;
        }
        else{
            //get best request
            nextRequest = Collections.min(requests, comparator);
        }
    }

    private void findDirection(){
        if(nextRequest.floor > currentFloor){
            direction = Direction.UP;
        }
        else if(nextRequest.floor < currentFloor){
            direction = Direction.DOWN;
        }
        else{
            direction = nextRequest.direction;
        }
    }

    private void elevatorLogic(){
        findNextRequest();
        if(nextRequest == null){
            direction = Direction.IDLE;
            destinationFloor = currentFloor;
        }
        else{
            findDirection();
            destinationFloor = nextRequest.floor;
        }
    }

}
