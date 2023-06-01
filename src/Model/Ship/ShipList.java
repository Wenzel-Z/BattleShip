package Model.Ship;

import DataClasses.Coordinate;

/**
 * Class for the list of ships on a board
 */
public class ShipList {
    Ship[] ships;

    /**
     * Constructor for ship list
     *
     * @param ships     Ships on the board
     */
    public ShipList(Ship[] ships) {
        this.ships = ships;
    }

    /**
     * Getter for the amount of ships on the board
     *
     * @return  amount of ships on the board
     */
    public int getSize() {
        return ships.length;
    }

    /**
     * Getter for the ships on the board
     *
     * @return  ships on the board
     */
    public Ship[] getShips() {
        return this.ships;
    }

    /**
     * Gets the amount of ships currently alive on the board
     *
     * @return      amount of live ships
     */
    public int getLiveShips() {
        int liveShips = 0;
        for (Ship ship : this.ships) {
            if(ship.isAlive()) {
                liveShips++;
            }
        }

        return liveShips;
    }

    /**
     * Gets the shoot at board cell given the coordinate
     *
     * @param coordinate    coordinate of possible ship location
     * @return              ship at the coordinate, or null
     */
    public Ship getShipAt(Coordinate coordinate) {
        for (Ship ship: this.ships) {
            if (ship.getLocation().contains(coordinate)) {
                return ship;
            }
        }

        return null;
    }

    /**
     * Checks if any of the ships are alive
     *
     * @return      true if at least one ship is alive, otherwise false
     */
    public boolean checkStatus() {
        for (Ship ship: this.ships) {
            if (ship.isAlive()) {
                return true;
            }
        }
        return false;
    }
}
