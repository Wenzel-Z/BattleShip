package Model;

import DataClasses.Coordinate;

public class ShipList {
    Ship[] ships;
    private final int size;

    public ShipList(Ship[] ships) {
        this.ships = ships;
        this.size = ships.length;
    }

    public int getSize() {
        return this.size;
    }

    public Ship[] getShips() {
        return this.ships;
    }

    public Ship getShipAt(Coordinate coordinate) {
        for (Ship ship: this.ships) {
            if (ship.getLocation().contains(coordinate)) {
                return ship;
            }
        }
        return null;
    }

    public Ship getShipAt(int x, int y) {
        for (Ship ship: this.ships) {
            if (ship.getLocation().contains(new Coordinate(x, y))) {
                return ship;
            }
        }

        return null;
    }

    public boolean checkStatus() {
        int liveShips = 0;
        for (Ship ship: this.ships) {
            if (ship.isAlive()) {
                return true;
            }
        }

        return false;
    }
}
