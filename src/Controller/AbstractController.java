package Controller;

import Model.Model;

import java.util.Scanner;

/**
 * Abstract class for Controller classes
 */
public abstract class AbstractController implements Controller {
    protected final Scanner scanner;
    protected final Model model;

    /**
     * Constructor that takes in the scanner and model, necessary in each controller
     *
     * @param scanner   scanner of system input stream, passed to every controller
     * @param model     model used to handle the logic obtained from the user
     */
    public AbstractController(Scanner scanner, Model model) {
        this.scanner = scanner;
        this.model = model;
    }
}
