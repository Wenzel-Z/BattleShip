package Controller;

import Display.WelcomeDisplay;
import Model.Model;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class WelcomeController extends AbstractController {
    private final WelcomeDisplay welcomeDisplay;

    public WelcomeController(Scanner scanner, Model model) {
        super(scanner, model);

        this.welcomeDisplay = new WelcomeDisplay();
    }
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
