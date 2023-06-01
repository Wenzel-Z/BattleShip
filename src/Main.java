import Controller.MainController;

/**
 * Entry point of the program
 */
public class Main {
    /**
     * Creates main controller and runs it
     *
     * @param args  unused
     */
    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.run();
    }
}
