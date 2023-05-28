package Display;

import java.util.NoSuchElementException;

public class FleetDisplay implements Display {
    private int maxSize;

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
    @Override
    public void display() {
        System.out.println("Please enter your fleet in the order [Carrier, Battleship, Destoryer, Submarine]");
        System.out.println("The size of any ship may not exceed " + this.maxSize);
        this.printLine();
    }
    public void printLine() {
        System.out.println("---------------------------------------------");
    }

    public void handleError(NoSuchElementException e) {
        System.err.println("Please enter a width and a height.");
    }

    public void handleError(IllegalArgumentException e) {
        System.err.println("Please enter only 4 integers, keep in mind the max value is " + this.maxSize);
    }

    public void handleError(ArrayIndexOutOfBoundsException e) {
        System.err.println("Please enter 4 values, with a space between each");
    }
}
