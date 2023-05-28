package Model;

import DataClasses.Coordinate;
import DataClasses.State;
import DataClasses.ShipType;

import java.util.HashSet;
import java.util.Random;

public class BattleSalvoBoard {
    private final State[][] board;
    private final State[][] hitBoard;
    private final ShipList ships;
    private final HashSet<Coordinate> shipLocations;
    private final int height;
    private final int width;

    public BattleSalvoBoard(int height, int width, int[] shipSizes) {
        this.height = height;
        this.width = width;
        this.ships = this.createShipList(shipSizes);
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
        return ships;
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
        //TODO make cleaner and separate logic out
        Random random = new Random();
        State[][] board = new State[this.width][this.height];
        Ship[] ships = this.ships.getShips();

        int shipsPlaced = 0;
        while (shipsPlaced < this.ships.getSize()) {
            Ship ship = ships[shipsPlaced];
            int size = ship.getSize();

            int x = random.nextInt(this.width);
            int y = random.nextInt(this.height);
            boolean vertical = random.nextBoolean();

            if (vertical) {
                while (y + size > this.height) {
                    y -= 1;
                }
            } else {
                while (x + size> this.width) {
                    x -= 1;
                }
            }

            boolean isFree = true;
            if (vertical) {
                for (int m = y; m < (y + size); m++) {
                    if (board[x][m] == State.SHIP) {
                        isFree = false;
                        break;
                    }
                }
            } else {
                for (int n = x; n < (x + size); n++) {
                    if (board[n][y] == State.SHIP) {
                        isFree = false;
                        break;
                    }
                }
            }

            if (!isFree) {
                continue;
            }

            for (int j = 0; j < size; j++) {
                board[x][y] = State.SHIP;
                Coordinate coordinate = new Coordinate(x, y);
                ship.addCoordinates(coordinate);
                shipLocations.add(coordinate);
                if (vertical) {
                    y++;
                } else {
                    x++;
                }
            }
            shipsPlaced++;
        }

        return fillBoard(board);
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

    private ShipList createShipList(int[] sizes) {
        ShipType[] representatives = new ShipType[] {ShipType.CARRIER, ShipType.BATTLESHIP,
                                                    ShipType.DESTROYER, ShipType.SUBMARINE};
        Ship[] ships = new Ship[4];
        for (int i = 0; i < sizes.length; i++) {
            ships[i] = new Ship(representatives[i], sizes[i]);
        }

        return new ShipList(ships);
    }
}
