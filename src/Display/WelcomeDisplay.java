package Display;

import java.util.NoSuchElementException;

/**
 * Display for welcoming the user and obtaining the board dimensions;
 */
public class WelcomeDisplay extends AbstractDisplay {

    /**
     * Prints the welcome message
     */
    @Override
    public void display() {
        System.out.println("BattleSalvo");
        System.out.println("Please enter a valid height and width below:");
        this.printLine();
    }

    /**
     * Handles generic error
     */
    public void handleError() { System.err.println("Oh no."); }

    /**
     * Handles when given input is empty
     *
     * @param ignored   used to determine message output
     */
    public void handleError(NoSuchElementException ignored) {
        System.err.println("Please enter a width and a height.");
    }

    /**
     * Handles if dimensions are invalid
     *
     * @param ignored   used to determine message output
     */
    public void handleError(IllegalArgumentException ignored) {
        System.err.println("Unable to set dimensions, please enter a valid with and height");
    }
}
