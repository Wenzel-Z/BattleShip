package Controller;

import Model.Model;

import java.util.Scanner;

/**
 * Handles information between each controller
 */
public class MainController extends AbstractController {
    private final WelcomeController welcomeController;
    private final FleetController fleetController;
    private final GameController gameController;

    /**
     * Creates controllers needed
     * Creates scanner and model, passes them to each class
     */
    public MainController() {
        super(new Scanner(System.in), new Model());

        this.welcomeController = new WelcomeController(this.scanner, this.model);
        this.fleetController = new FleetController(this.scanner, this.model);
        this.gameController = new GameController(this.scanner, this.model, true);
    }

    /**
     * Runs the controllers in order, creates the boards when values are obtained
     */
    public void run() {
        this.welcomeController.run();
        this.fleetController.run();
        this.model.createBoards();
        this.gameController.run();
    }
}
