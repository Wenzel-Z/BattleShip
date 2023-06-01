package Model.Ship;

import DataClasses.Coordinate;
import DataClasses.ShipType;

import java.util.HashSet;

/**
 * Class for a ship object, keeps track of its location and times it has been hit
 */
public class Ship {
    private final ShipType representation;

    private final HashSet<Coordinate> location;

    private int hitsTaken;

    private boolean alive;

    /**
     * Constructor for ship
     *
     * @param representation    Type of ship the ship should be
     */
    public Ship(ShipType representation) {
        this.representation = representation;
        this.location = new HashSet<>();
        this.hitsTaken = 0;
        this.alive = true;
    }

    /**
     * Getter for the type of ship
     *
     * @return      type of ship
     */
    public ShipType getRep() {
        return this.representation;
    }

    /**
     * Getter for checking whether the ship is alive or destroyed
     *
     * @return  true if alive, false if dead
     */
    public boolean isAlive() {
        return this.alive;
    }

    /**
     * Gets the board cells the ship occupies
     *
     * @return      location of the ship
     */
    public HashSet<Coordinate> getLocation() {
        return this.location;
    }

    /**
     * Increments the amount of damage the ship has taken
     */
    public void handleHit() {
        this.hitsTaken++;
        if (this.hitsTaken == this.representation.getSize()) {
            this.alive = false;
        }
    }

    /**
     * Sets the coordinates the ship is at
     *
     * @param coordinate    coordinate of the ship
     */
    public void addCoordinates(Coordinate coordinate) {
        this.location.add(coordinate);
    }
}
