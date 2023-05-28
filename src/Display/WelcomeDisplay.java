package Display;

import java.util.NoSuchElementException;

public class WelcomeDisplay implements Display {
    @Override
    public void display() {
        System.out.println("BattleSalvo");
        System.out.println("Please enter a valid height and width below:");
        this.printLine();
    }

    public void handleError() { System.err.println("Oh no."); }

    public void handleError(NoSuchElementException e) {
        System.err.println("Please enter a width and a height.");
    }

    public void handleError(IllegalArgumentException e) {
        System.err.println("Unable to set dimensions, please enter a valid with and height");
    }

    public void printLine() { System.out.println("---------------------------------------------"); }




}
