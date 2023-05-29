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

public class GameController extends AbstractController {
    private final GameDisplay gameDisplay;
    private BattleSalvoAI playerAI;

    private final BattleSalvoAI ai;

    private final boolean twoAI;

    public GameController(Scanner scanner, Model model, boolean twoAI) {
        super(scanner, model);

        this.gameDisplay = new GameDisplay();
        this.ai = new BattleSalvoAI();
        this.twoAI = twoAI;

        this.setTwoAI();
    }

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
            if (!(this.twoAI)) {
                while (shots < 8) {
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
                aiPlayerShotCoordinates = this.playerAI.takeRandomShots();
            }

            // Model.Player/AI shots
            this.model.takeShots(aiPlayerShotCoordinates, true);
            aiPlayerShotCoordinates.clear();

            // AI shots
            aiShotCoordinates = this.ai.takeRandomShots();
            this.model.takeShots(aiShotCoordinates, false);
            aiShotCoordinates.clear();

            statusOfGame = this.model.checkStatusOfGame();

            if (this.twoAI) {
                this.scanner.nextLine();
            }
        }
    }

    private State[][] getBoard() { return this.model.getSalvoBoard().getBoard(); }

    private State[][] getOpponentBoard() { return this.model.getOpponentSalvoBoard().getBoard(); }

    private State[][] getHitBoard() { return this.model.getSalvoBoard().getHitBoard(); }

    private State[][] getOpponentHitBoard() { return this.model.getOpponentSalvoBoard().getHitBoard(); }

    private int getWidth() { return this.model.getSalvoBoard().getWidth(); }

    private int getHeight() { return this.model.getSalvoBoard().getHeight(); }

    private void printMessage() {
        gameDisplay.displayOpponentBoard();
        gameDisplay.displayBoard(this.getOpponentHitBoard());
        gameDisplay.displayYourBoard();
        gameDisplay.displayBoard(this.getBoard());
        gameDisplay.display();
        gameDisplay.printLine();
    }

    private void setTwoAI() {
        if (this.twoAI) {
            this.playerAI = new BattleSalvoAI();
        } else {
            this.playerAI = null;
        }
    }
}
