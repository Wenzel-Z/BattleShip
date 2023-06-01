package Display;

/**
 * Abstract class for Display classes
 */
public abstract class AbstractDisplay implements Display {
    /**
     * Prints a line to the console
     */
    public void printLine() {
        System.out.println("---------------------------------------------");
    }
}
