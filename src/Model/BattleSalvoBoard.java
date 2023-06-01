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

/**
 * Class for representing a board in the Battle Salvo Game
 */
public class BattleSalvoBoard {
    private final State[][] board;
    private final State[][] hitBoard;
    private final ShipList ships;
    private final HashSet<Coordinate> shipLocations;
    private final int height;
    private final int width;

    /**
     * Constructor for the board, creates a board and places the ships
     *
     * @param height    height of board
     * @param width     width of board
     * @param amounts   amount of each ship type in the order <Carrier, Battleship, Destroyer, Sub>
     */
    public BattleSalvoBoard(int height, int width, int[] amounts) {
        this.height = height;
        this.width = width;

        this.ships = this.createShipList(amounts);
        this.shipLocations = new HashSet<>();

        this.board = this.createBoard();
        State[][] hitBoard = new State[width][height];
        this.hitBoard = this.fillBoard(hitBoard);
    }

    /**
     * Getter for the board
     *
     * @return  the current state of the board
     */
    public State[][] getBoard() {
        return this.board;
    }

    /**
     * Getter for the hit board, a copy of the board without ships shown
     *
     * @return  the current hit board
     */
    public State[][] getHitBoard() {
        return this.hitBoard;
    }

    /**
     * Getter for the list of ships on the board
     *
     * @return  list of ships
     */
    public ShipList getShips() {
        return this.ships;
    }

    /**
     * Takes a single shot at the board and updates the board
     *
     * @param coordinate    coordinate of the location to be shot
     */
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

    /**
     * Constructor method to create the initial board based on the constructor parameters
     * Places ships and initializes the size
     *
     * @return  a 2d array representing the board
     */
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

    /**
     * Places a ship on the board based on a random square
     *
     * @param board                     board to be placed on
     * @param ship                      ship to be placed
     * @param size                      size of ship
     * @param x                         x coordinate
     * @param y                         y coordinate
     * @param vertical                  whether the ship should be placed vertically or horizontally
     * @throws GenerationException      thrown if no orientation is valid
     */
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

    /**
     * Fills the given board with water where there is not a ship
     *
     * @param board     board with ship placements
     * @return          board of water and ship placements
     */
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

    /**
     * Creates the list of ships based on the amounts given
     *
     * @param amounts   amount of each type of ship
     * @return          the ships as a ship list object
     */
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
