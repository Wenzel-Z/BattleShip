package Model;

import DataClasses.Coordinate;
import DataClasses.ShipType;

import java.util.ArrayList;
import java.util.HashSet;

public class Ship {
    private final ShipType representation;
    private final int size;
    private final HashSet<Coordinate> location;

    private int hitsTaken;

    private boolean alive;

    public Ship(ShipType representation, int size) {
        this.representation = representation;
        this.size = size;
        this.location = new HashSet<>();
        this.hitsTaken = 0;
        this.alive = true;
    }

    public ShipType getRep() {
        return this.representation;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isAlive() {
        return alive;
    }

    public HashSet<Coordinate> getLocation() {
        return location;
    }

    public void handleHit() {
        this.hitsTaken++;
        if (this.hitsTaken == this.size) {
            this.alive = false;
        }
    }

    public void addCoordinates(Coordinate coordinate) {
        this.location.add(coordinate);
    }

    public void addCoordinates(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y);
        this.location.add(coordinate);
    }
}
