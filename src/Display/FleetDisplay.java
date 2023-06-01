package Display;

import java.util.NoSuchElementException;

/**
 * Display used to get the size of the fleet
 */
public class FleetDisplay extends AbstractDisplay {
    private int maxSize;

    /**
     * Displays message asking for the fleet
     */
    @Override
    public void display() {
        System.out.println("Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine]");
        System.out.println("The amount of ships in total may not exceed " + this.maxSize);
        this.printLine();
    }

    /**
     * Sets the maximum fleet size
     *
     * @param maxSize   maximum size of the fleet
     */
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * Handles invalid width and height
     *
     * @param ignored   used to determine message output
     */
    public void handleError(NoSuchElementException ignored) {
        System.err.println("Please enter a width and a height.");
    }

    /**
     * Handles invalid amount of integers
     *
     * @param ignored   used to determine message output
     */
    public void handleError(IllegalArgumentException ignored) {
        System.err.println("Please enter only 4 integers, keep in mind the max amount is " + this.maxSize);
    }

    /**
     * Handles invalid size of fleet
     *
     * @param ignored   used to determine message output
     */
    public void handleError(ArrayIndexOutOfBoundsException ignored) {
        System.err.println("Please enter 4 values, with a space between each");
    }
}
