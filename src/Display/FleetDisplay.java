package Display;

import java.util.NoSuchElementException;

public class FleetDisplay implements Display {
    private int maxSize;


    @Override
    public void display() {
        System.out.println("Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine]");
        System.out.println("The amount of ships in total may not exceed " + this.maxSize);
        this.printLine();
    }
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public void printLine() {
        System.out.println("---------------------------------------------");
    }

    public void handleError(NoSuchElementException ignored) {
        System.err.println("Please enter a width and a height.");
    }

    public void handleError(IllegalArgumentException ignored) {
        System.err.println("Please enter only 4 integers, keep in mind the max amount is " + this.maxSize);
    }

    public void handleError(ArrayIndexOutOfBoundsException ignored) {
        System.err.println("Please enter 4 values, with a space between each");
    }
}
