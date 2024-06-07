package com.com.org.datastructures;

import java.util.Objects;

public class Request {
    public final int floor;
    public final Direction direction;


    public Request(int floor, Direction direction) {
        this.floor = floor;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "{floor=" + floor + ", direction=" + direction + "}";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return floor == request.floor && direction == request.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(floor, direction);
    }
}
