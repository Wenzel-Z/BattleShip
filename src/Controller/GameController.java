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
        // TODO add separation for player and AIs
        this.ai.setDimensions(this.getWidth(), this.getHeight());
        this.playerAI.setDimensions(this.getWidth(), this.getHeight());
        int shots = 0;
        Status statusOfGame = Status.ONGOING;
        ArrayList<Coordinate> aiShotCoordinates;
        ArrayList<Coordinate> aiPlayerShotCoordinates;
        while (statusOfGame == Status.ONGOING) {
            gameDisplay.displayOpponentBoard();
            gameDisplay.displayBoard(this.getOpponentBoard());
            gameDisplay.displayYourBoard();
            gameDisplay.displayBoard(this.getBoard());
            gameDisplay.display();
            gameDisplay.printLine();
            if (!(this.twoAI)) {
                while (shots < 8) {
                    try {
                        String shot = this.scanner.nextLine();
                        int[] coordinates = Arrays.stream(shot.split(" ")).mapToInt(Integer::parseInt).toArray();
                        this.model.addCoordinate(coordinates);

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
                // Player AI shots
                aiPlayerShotCoordinates = this.playerAI.takeRandomShots();
                this.model.takeShots(aiPlayerShotCoordinates, true);
                aiPlayerShotCoordinates.clear();
            }

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

    private State[][] getOpponentBoard() { return this.model.getOpponentSalvoBoard().getBoard(); }

    private State[][] getBoard() { return this.model.getSalvoBoard().getBoard(); }

    private State[][] getOpponentHitBoard() { return this.model.getOpponentSalvoBoard().getHitBoard(); }

    private State[][] getHitBoard() { return this.model.getSalvoBoard().getHitBoard(); }

    private int getWidth() { return this.model.getSalvoBoard().getWidth(); }

    private int getHeight() { return this.model.getSalvoBoard().getHeight(); }

    private void setTwoAI() {
        if (this.twoAI) {
            this.playerAI = new BattleSalvoAI();
        } else {
            this.playerAI = null;
        }
    }
}
