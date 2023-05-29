package Controller;

import Display.FleetDisplay;
import Model.Model;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FleetController extends AbstractController{
    private final FleetDisplay fleetDisplay;
    public FleetController(Scanner scanner, Model model) {
        super(scanner, model);
        fleetDisplay = new FleetDisplay();
    }

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
