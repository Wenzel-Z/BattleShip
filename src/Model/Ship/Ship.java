package Model.Ship;

import DataClasses.Coordinate;
import DataClasses.ShipType;

import java.util.HashSet;

public class Ship {
    private final ShipType representation;

    private final HashSet<Coordinate> location;

    private int hitsTaken;

    private boolean alive;

    public Ship(ShipType representation) {
        this.representation = representation;
        this.location = new HashSet<>();
        this.hitsTaken = 0;
        this.alive = true;
    }

    public ShipType getRep() {
        return this.representation;
    }

    public boolean isAlive() {
        return alive;
    }

    public HashSet<Coordinate> getLocation() {
        return location;
    }

    public void handleHit() {
        this.hitsTaken++;
        if (this.hitsTaken == this.representation.getSize()) {
            this.alive = false;
        }
    }

    public void addCoordinates(Coordinate coordinate) {
        this.location.add(coordinate);
    }
}
