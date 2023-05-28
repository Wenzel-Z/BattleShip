package Controller;

import Model.Model;

import java.util.Scanner;

public abstract class AbstractController implements Controller {
    protected final Scanner scanner;
    protected final Model model;

    public AbstractController(Scanner scanner, Model model) {
        this.scanner = scanner;
        this.model = model;
    }
}
