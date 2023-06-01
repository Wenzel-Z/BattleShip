package Controller;

import DataClasses.Status;
import Display.GameDisplay;
import DataClasses.State;
import Model.BattleSalvoAI;
import DataClasses.Coordinate;
import Model.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * Controller that handles the running of the game
 */
public class GameController extends AbstractController {
    private final GameDisplay gameDisplay;
    private BattleSalvoAI playerAI;
    private final BattleSalvoAI ai;
    private final boolean twoAI;

    /**
     * Constructor for game controller, takes in the scanner and model for the superclass
     * Creates game display for output and ai for playing the game
     *
     * @param scanner   scanner of system input stream, passed to every controller
     * @param model     model used to handle the logic obtained from the user
     * @param twoAI     boolean for whether player vs ai or ai vs ai
     */
    public GameController(Scanner scanner, Model model, boolean twoAI) {
        super(scanner, model);

        this.gameDisplay = new GameDisplay();
        this.ai = new BattleSalvoAI();
        this.twoAI = twoAI;

        this.setTwoAI();
    }

    /**
     * Runs the game and continues running till one side loses
     */
    @Override
    public void run() {
        // TODO add separation for player and AIs -> clean
        this.ai.setDimensions(this.getWidth(), this.getHeight());
        this.playerAI.setDimensions(this.getWidth(), this.getHeight());
        int shots = 0;
        Status statusOfGame = Status.ONGOING;
        ArrayList<Coordinate> aiShotCoordinates;
        ArrayList<Coordinate> aiPlayerShotCoordinates = new ArrayList<>();
        while (statusOfGame == Status.ONGOING) {
            this.printMessage();
            if (this.twoAI) {
                this.scanner.nextLine();
            }
            if (!(this.twoAI)) {
                while (shots < this.getLiveShips(true)) {
                    try {
                        String shot = this.scanner.nextLine();
                        int[] coordinates = Arrays.stream(shot.split(" ")).mapToInt(Integer::parseInt).toArray();
                        Coordinate coordinate = new Coordinate(coordinates[0], coordinates[1]);
                        aiPlayerShotCoordinates.add(coordinate);

                        shots++;
                    } catch (NoSuchElementException e) {
                        this.gameDisplay.handleError(e);
                    } catch (IllegalArgumentException e) {
                        this.gameDisplay.handleError(e);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        this.gameDisplay.handleError(e);
                    }
                }
            } else {
                // Model.Player AI shots
                aiPlayerShotCoordinates = this.playerAI.takeRandomShots(this.getLiveShips(true));
            }

            // Model.Player/AI shots
            this.model.takeShots(aiPlayerShotCoordinates, true);
            aiPlayerShotCoordinates.clear();

            // AI shots
            aiShotCoordinates = this.ai.takeRandomShots(this.getLiveShips(false));
            this.model.takeShots(aiShotCoordinates, false);
            aiShotCoordinates.clear();

            statusOfGame = this.model.checkStatusOfGame();
        }
    }

    /**
     * Gets the live ships currently alive for the player
     *
     * @param player       boolean of whether it is the player's or opponent's side
     * @return             the amount of live ships
     */
    private int getLiveShips(boolean player) {
        if (player) {
            return this.model.getSalvoBoard().getShips().getLiveShips();
        } else {
            return this.model.getOpponentSalvoBoard().getShips().getLiveShips();
        }
    }

    /**
     * Gets the players board
     *
     * @return      the player's board
     */
    private State[][] getBoard() { return this.model.getSalvoBoard().getBoard(); }

    /**
     * Gets the opponents board
     *
     * @return      the opponent's board
     */
    private State[][] getOpponentBoard() { return this.model.getOpponentSalvoBoard().getBoard(); }

    /**
     * Gets the player's hidden board
     *
     * @return      the player's hidden board
     */
    private State[][] getHitBoard() { return this.model.getSalvoBoard().getHitBoard(); }

    /**
     * Gets the opponent's hidden board
     *
     * @return      the opponent's hidden board
     */
    private State[][] getOpponentHitBoard() { return this.model.getOpponentSalvoBoard().getHitBoard(); }

    /**
     * Gets the width of the boards
     *
     * @return      the width of the boards
     */
    private int getWidth() { return this.model.getWidth(); }

    /**
     * Gets the height of the boards
     *
     * @return      the height of the boards
     */
    private int getHeight() { return this.model.getHeight(); }

    /**
     * Helper method, uses game display to print the next screen
     *
     */
    private void printMessage() {
        this.gameDisplay.displayBoard(this.getOpponentHitBoard(), false);
        this.gameDisplay.displayBoard(this.getBoard(), true);
        this.gameDisplay.display();
        this.gameDisplay.printLine();
    }

    /**
     * Sets an ai if player is null - bad method because of lack of "player class"s
     *
     */
    private void setTwoAI() {
        if (this.twoAI) {
            this.playerAI = new BattleSalvoAI();
        } else {
            this.playerAI = null;
        }
    }
}
