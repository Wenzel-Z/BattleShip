package Model;

import DataClasses.Coordinate;
import DataClasses.Status;

import java.util.ArrayList;

public class Model {
    private BattleSalvoBoard battleSalvoBoard;
    private BattleSalvoBoard opponentBattleSalvoBoard;

    private int width;
    private int height;
    private int[] shipAmounts;

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

    public void setAmountOfShips(int[] amounts) throws IllegalArgumentException {
        int max = countAmounts(amounts);
        if (amounts.length == 4 && max < this.getMaxSize()+1) {
            this.shipAmounts = amounts;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void createBoards() {
        this.battleSalvoBoard = this.createBoard();
        this.opponentBattleSalvoBoard = this.createBoard();
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

    private BattleSalvoBoard createBoard() {
      return new BattleSalvoBoard(this.height, this.width, this.shipAmounts);
    }

    public Status checkStatusOfGame() {
        boolean hasLiveShips = this.battleSalvoBoard.getShips().checkStatus();
        boolean hasLiveShipsOpponent = this.opponentBattleSalvoBoard.getShips().checkStatus();

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

    private int countAmounts(int[] array) throws IllegalArgumentException {
        int result = 0;
        for (int val : array) {
            if (val != 0) {
                result += val;
            } else {
                throw new IllegalArgumentException();
            }
        }

        return result;
    }
}
