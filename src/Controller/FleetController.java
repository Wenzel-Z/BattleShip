package Controller;

import Display.FleetDisplay;
import Model.Model;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Controller for getting the size of the fleet
 */
public class FleetController extends AbstractController{
    private final FleetDisplay fleetDisplay;

     /**
     * Constructor that takes in the scanner and model for the super class
     * Creates fleet display for message display while running
     *
     * @param scanner   scanner of system input stream, passed to every controller
     * @param model     model used to handle the logic obtained from the user
     */
    public FleetController(Scanner scanner, Model model) {
        super(scanner, model);
        fleetDisplay = new FleetDisplay();
    }

    /**
     * Generic run method, obtains input and passes the values to the model for verification and initialization
     */
    @Override
    public void run() {
        this.fleetDisplay.setMaxSize(this.model.getMaxSize());
        boolean obtainedInput = false;
        while (!(obtainedInput)) {
            this.fleetDisplay.display();
            try {
                String line = this.scanner.nextLine();
                int[] shipSizes = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                this.model.setAmountOfShips(shipSizes);
                obtainedInput = true;
            } catch (NoSuchElementException e) {
                this.fleetDisplay.handleError(e);
            } catch (IllegalArgumentException e) {
                this.fleetDisplay.handleError(e);
            } catch (ArrayIndexOutOfBoundsException e) {
                this.fleetDisplay.handleError(e);
            }
        }
    }
}
