package Controller;

import Model.Model;

import java.util.Scanner;

public class MainController extends AbstractController {
    private final WelcomeController welcomeController;
    private final FleetController fleetController;
    private final GameController gameController;

    public MainController() {
        super(new Scanner(System.in), new Model());

        this.welcomeController = new WelcomeController(this.scanner, this.model);
        this.fleetController = new FleetController(this.scanner, this.model);
        this.gameController = new GameController(this.scanner, this.model, true);
    }

    public void run() {
        this.welcomeController.run();
        this.fleetController.run();
        this.model.createBoards();
        this.gameController.run();
    }
}
