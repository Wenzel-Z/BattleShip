package Exceptions;

/**
 * Exception if the generated location has no valid orientation
 */
public class GenerationException extends Exception {

    /**
     * Constructor for generation exception
     */
    public GenerationException() {
        super("Unable to find placement for given square");
    }
}
