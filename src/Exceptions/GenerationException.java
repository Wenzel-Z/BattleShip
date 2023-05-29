package Exceptions;

public class GenerationException extends Exception {
    public GenerationException() {
        super("Unable to find placement for given square");
    }
}
