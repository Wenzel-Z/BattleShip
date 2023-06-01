package Controller;

import Display.WelcomeDisplay;
import Model.Model;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class for getting the width and height of the board
 */
public class WelcomeController extends AbstractController {
    private final WelcomeDisplay welcomeDisplay;

    /**
     * Constructor for welcome controller, takes in the scanner and model for the superclass
     * Creates welcome display for sending messages
     *
     * @param scanner   scanner of system input stream, passed to every controller
     * @param model     model used to handle the logic obtained from the user
     */
    public WelcomeController(Scanner scanner, Model model) {
        super(scanner, model);

        this.welcomeDisplay = new WelcomeDisplay();
    }

    /**
     * Run method for welcome display, gets the height and width from the user
     * Sends height and width for verification
     */
    @Override
    public void run() {
        boolean invalidInput = true;
        while (invalidInput) {
            welcomeDisplay.display();
            try {
                String line = this.scanner.nextLine();
                int[] dimensions = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                this.model.setDimensions(dimensions[0], dimensions[1]);
                invalidInput = false;
            } catch (NoSuchElementException e) {
                this.welcomeDisplay.handleError(e);
            } catch (IllegalArgumentException e) {
                this.welcomeDisplay.handleError(e);
            } catch (ArrayIndexOutOfBoundsException e) {
                this.welcomeDisplay.handleError();
            }
        }
    }
}
