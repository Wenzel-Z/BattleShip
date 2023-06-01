package Display;

import DataClasses.State;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Display for during the exchange of shots
 */
public class GameDisplay extends AbstractDisplay {

    /**
     * Default message for getting the amount of ships
     */
    @Override
    public void display() {
        System.out.println("Please enter your shots");
    }

    /**
     * Displays board given and message depending on whether it is the player's or opponent's board
     */
    public void displayBoard(State[][] board, Boolean player) {
        String message;
        if (player) {
            message = "Your Board";
        } else {
            message = "OpponentBoard";
        }

        // Print message and board
        System.out.println(message);
        Arrays.stream(board)
                .forEach(col -> System.out.println(Arrays.toString(col).replace(",", "")));
    }

    /**
     * Handles when more than 8 values are entered
     *
     * @param ignored   used to determine message output
     */
    public void handleError(NoSuchElementException ignored) {
        System.err.println("Please enter 8 values");
    }

    /**
     * Handles when the output given is not formatted correctly
     *
     * @param ignored   used to determine message output
     */
    public void handleError(IllegalArgumentException ignored) {
        System.err.println("Arguments not formatted correctly, please include a space between each pair of coordinates");
    }

    /**
     * Handles when a number given is larger or smaller than the given board dimensions
     *
     * @param ignored   used to determine message output
     */
    public void handleError(ArrayIndexOutOfBoundsException ignored) {
        System.err.println("The indices given are not valid as they are out of bounds");
    }
}
