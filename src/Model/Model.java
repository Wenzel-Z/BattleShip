package Model;

import DataClasses.Coordinate;
import DataClasses.Status;

import java.util.ArrayList;

public class Model {
    private BattleSalvoBoard battleSalvoBoard;
    private BattleSalvoBoard opponentBattleSalvoBoard;

    private final ArrayList<Coordinate> shotCoordinates = new ArrayList<>();
    private int width;
    private int height;
    private int[] shipSizes;

    public int getMaxSize() { return Math.min(this.height, this.width); }

    public BattleSalvoBoard getSalvoBoard() {
        return this.battleSalvoBoard;
    }

    public BattleSalvoBoard getOpponentSalvoBoard() { return this.opponentBattleSalvoBoard; }

    public void setDimensions(int width, int height) throws IllegalArgumentException {
        if (height < 11 && height > 5 && width < 11 && width > 5) {
            this.height = height;
            this.width = width;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setShipSizes(int[] sizes) throws IllegalArgumentException {
        int max = Util.getMax(sizes);
        if (sizes.length == 4 && max < getMaxSize()) {
            this.shipSizes = sizes;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void createBoards() {
        this.battleSalvoBoard = this.createBoard();
        this.opponentBattleSalvoBoard = this.createBoard();
    }

    public void takeShots() {
        // For our board to enemy
        for (Coordinate shot : shotCoordinates) {
            this.opponentBattleSalvoBoard.takeShot(shot);
        }
        this.shotCoordinates.clear();
    }

    public void takeShots(ArrayList<Coordinate> coordinates, boolean player) {
        // For AI to hit the board
        for (Coordinate shot : coordinates) {
            if (player) {
                this.opponentBattleSalvoBoard.takeShot(shot);
            } else {
                this.battleSalvoBoard.takeShot(shot);
            }
        }
    }

    public void addCoordinate(int[] coordinates) {
        int x = coordinates[0];
        int y = coordinates[1];
        this.shotCoordinates.add(new Coordinate(x, y));
    }

    private BattleSalvoBoard createBoard() {
      return new BattleSalvoBoard(this.height, this.width, this.shipSizes);
    }

    public Status checkStatusOfGame() {
        boolean hasLiveShips = this.battleSalvoBoard.getShips().checkStatus();
        boolean hasLiveShipsOpponent = this.opponentBattleSalvoBoard.getShips().checkStatus();

        // TODO clean this up somehow? Not sure if possible
        if (hasLiveShips && hasLiveShipsOpponent) {
            return Status.ONGOING;
        } else if (hasLiveShips) {
            return Status.PLAYERWIN;
        } else if (hasLiveShipsOpponent) {
            return Status.OPPONENTWIN;
        } else {
            return Status.TIE;
        }
    }
}
