package Display;

import DataClasses.State;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class GameDisplay implements Display {

    @Override
    public void display() {
        System.out.println("Please enter your shots");
    }

    public void displayOpponentBoard() {
        System.out.println("Opponent Board");
    }

    public void displayYourBoard() {
        System.out.println("Your Board");
    }

    public void displayBoard(State[][] board) {
        Arrays.stream(board)
                .forEach(col -> System.out.println(Arrays.toString(col).replace(",", "")));
    }

    public void printLine() {
        System.out.println("---------------------------------------------");
    }

    public void handleError(NoSuchElementException ignored) {
        System.err.println("Please enter 8 values");
    }

    public void handleError(IllegalArgumentException ignored) {
        System.err.println("Arguments not formatted correctly, please include a space between each pair of coordinates");
    }

    public void handleError(ArrayIndexOutOfBoundsException ignored) {
        System.err.println("The indices given are not valid as they are out of bounds");
    }
}
