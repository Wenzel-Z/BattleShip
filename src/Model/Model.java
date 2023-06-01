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

    /**
     * Gets the max size of the fleet based on height and width
     *
     * @return      the largest of height and width
     */
    public int getMaxSize() { return Math.min(this.height, this.width); }

    /**
     * Getter for the width of the board
     *
     * @return  the width of the board
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Getter for the height of the board
     *
     * @return  the height of the board
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Getter for the player's board
     *
     * @return  the board object for the player
     */
    public BattleSalvoBoard getSalvoBoard() {
        return this.battleSalvoBoard;
    }

    /**
     * Getter for the opponent's board
     *
     * @return  the board object for the opponent
     */
    public BattleSalvoBoard getOpponentSalvoBoard() { return this.opponentBattleSalvoBoard; }

    /**
     * Sets the dimensions of the board
     *
     * @param width                         width of the board
     * @param height                        height of the board
     * @throws IllegalArgumentException     if arguments given are not valid dimensions
     */
    public void setDimensions(int width, int height) throws IllegalArgumentException {
        if (height < 16 && height > 5 && width < 16 && width > 5) {
            this.height = height;
            this.width = width;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Sets the amount of ships to be placed on each board
     *
     * @param amounts                       amount of ships of each type
     * @throws IllegalArgumentException     amounts exceed the limit or the input is not formatted correctly
     */
    public void setAmountOfShips(int[] amounts) throws IllegalArgumentException {
        int max = countAmounts(amounts);
        if (amounts.length == 4 && max < this.getMaxSize()+1) {
            this.shipAmounts = amounts;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Creates the board for the opponent and player
     * Depends on {@link #setAmountOfShips(int[]) setAmountOfShips} and {@link #setDimensions(int, int) setDimension}
     */
    public void createBoards() {
        this.battleSalvoBoard = this.createBoard();
        this.opponentBattleSalvoBoard = this.createBoard();
    }

    /**
     * Helper method for {@link #createBoards() createBoards}, creates an individual board
     *
     * @return  a board object
     */
    private BattleSalvoBoard createBoard() {
        return new BattleSalvoBoard(this.height, this.width, this.shipAmounts);
    }

    /**
     * Uses a list of coordinates to shoot the board of the opponents board or player's board
     *
     * @param coordinates   list of coordinates to be shot
     * @param player        whether this is the player's or opponent's shots
     */
    public void takeShots(ArrayList<Coordinate> coordinates, boolean player) {
        for (Coordinate shot : coordinates) {
            if (player) {
                this.opponentBattleSalvoBoard.takeShot(shot);
            } else {
                this.battleSalvoBoard.takeShot(shot);
            }
        }
    }

    /**
     * Checks the amount of live ships on both boards and returns the outcome
     *
     * @return  the status of the game
     */
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

    /**
     * Helper method for {@link #setAmountOfShips(int[]) setAmountOfShips}
     * Counts the amount of ships in the fleet
     *
     * @param array                         array of the amount of each type of ship
     * @return                              total amount of ships
     * @throws IllegalArgumentException     if one of the amounts is less than 1
     */
    private int countAmounts(int[] array) throws IllegalArgumentException {
        int result = 0;
        for (int val : array) {
            if (val < 1) {
                result += val;
            } else {
                throw new IllegalArgumentException();
            }
        }

        return result;
    }
}
