package Model;

import DataClasses.Coordinate;
import DataClasses.State;
import DataClasses.ShipType;
import Exceptions.GenerationException;
import Model.Ship.Ship;
import Model.Ship.ShipList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class BattleSalvoBoard {
    private final State[][] board;
    private final State[][] hitBoard;
    private final ShipList ships;
    private final HashSet<Coordinate> shipLocations;
    private final int height;
    private final int width;

    public BattleSalvoBoard(int height, int width, int[] amounts) {
        this.height = height;
        this.width = width;

        this.ships = this.createShipList(amounts);
        this.shipLocations = new HashSet<>();

        this.board = this.createBoard();
        State[][] hitBoard = new State[width][height];
        this.hitBoard = this.fillBoard(hitBoard);
    }

    public State[][] getBoard() {
        return this.board;
    }

    public State[][] getHitBoard() {
        return this.hitBoard;
    }

    public ShipList getShips() {
        return this.ships;
    }

    public int getHeight() { return height; }

    public int getWidth() { return width; }

    public void takeShot(Coordinate coordinate) {
        int x = coordinate.x();
        int y = coordinate.y();
        if (this.shipLocations.contains(coordinate)) {
            this.shipLocations.remove(coordinate);
            this.ships.getShipAt(coordinate).handleHit();

            this.board[x][y] = State.HIT;
            this.hitBoard[x][y] = State.HIT;

        } else {
            this.board[x][y] = State.MISS;
            this.hitBoard[x][y] = State.MISS;
        }
    }



    private State[][] createBoard() {
        Random random = new Random();
        State[][] board = new State[this.width][this.height];
        Ship[] ships = this.ships.getShips();
        int amountOfShips = this.ships.getSize();

        int shipsPlaced = 0;
        while (shipsPlaced < amountOfShips) {
            Ship ship = ships[shipsPlaced];
            int size = ship.getRep().getSize();
            try {
                int x = random.nextInt(this.width);
                int y = random.nextInt(this.height);
                boolean vertical = random.nextBoolean();

                this.placeShip(board, ship, size, x, y, vertical);
                shipsPlaced++;
            } catch (GenerationException ignored) {
            }
        }

        return fillBoard(board);
    }

    private void placeShip(State[][] board, Ship ship, int size, int x, int y, boolean vertical)
            throws GenerationException {

        // Not the biggest fan of this function, definitely a way to not have so much repeat code
        if (vertical) {
            while (y + size > this.height) {
                y -= 1;
            }
        } else {
            while (x + size> this.width) {
                x -= 1;
            }
        }

        if (vertical) {
            for (int m = y; m < (y + size); m++) {
                if (board[x][m] == State.SHIP) {
                    throw new GenerationException();
                }
            }
        } else {
            for (int n = x; n < (x + size); n++) {
                if (board[n][y] == State.SHIP) {
                    throw new GenerationException();
                }
            }
        }

        for (int j = 0; j < size; j++) {
            board[x][y] = State.SHIP;
            Coordinate coordinate = new Coordinate(x, y);
            ship.addCoordinates(coordinate);
            this.shipLocations.add(coordinate);
            if (vertical) {
                y++;
            } else {
                x++;
            }
        }
    }

    private State[][] fillBoard(State[][] board) {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (!(board[j][i] == State.SHIP)) {
                    board[j][i] = State.WATER;
                }
            }
        }
        return board;
    }

    private ShipList createShipList(int[] amounts) {
        ShipType[] representatives = new ShipType[] {ShipType.CARRIER, ShipType.BATTLESHIP,
                                                    ShipType.DESTROYER, ShipType.SUBMARINE};
        int index = 0;
        int numShips = Arrays.stream(amounts).sum();
        Ship[] ships = new Ship[numShips];
        int i = 0;
        while (i < numShips){
            int j = 0;
            while(j < amounts[index]) {
                ships[i] = new Ship(representatives[index]);
                i++;
                j++;
            }
            index++;
        }

        return new ShipList(ships);
    }
}
