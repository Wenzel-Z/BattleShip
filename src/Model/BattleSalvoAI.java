package Model;

import DataClasses.Coordinate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Class to generate moves for the playing of BattleSalvo
 */
public class BattleSalvoAI {
    Random random;
    HashSet<Coordinate> hitSquares;
    int boardWidth;
    int boardHeight;
    int area;

    /**
     * Constructor for Battle Salvo AI
     */
    public BattleSalvoAI() {
        this.random = new Random();
        this.hitSquares = new HashSet<>();
    }

    /**
     * Sets the dimensions of the board for the AI
     *
     * @param boardWidth    width of board
     * @param boardHeight   height of board
     */
    public void setDimensions(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.area = boardWidth * boardHeight;
    }

    /**
     * Takes random shots depending on the amount of ships left
     *
     * @param shipsLeft     amount of ships left on the AI's side of the board
     * @return              a list of random shots
     */
    public ArrayList<Coordinate> takeRandomShots(int shipsLeft) {
        int shotsTaken = 0;
        int difference = this.area - this.hitSquares.size();
        if (difference < shipsLeft) {
            shotsTaken = difference;
        }

        ArrayList<Coordinate> coordinates = new ArrayList<>();
        while (shotsTaken < shipsLeft) {
            Coordinate coordinate = new Coordinate(random.nextInt(this.boardWidth), random.nextInt(this.boardHeight));
            if (!(this.hitSquares.contains(coordinate))) {
                coordinates.add(coordinate);
                this.hitSquares.add(coordinate);

                shotsTaken++;
            }
        }

        return coordinates;
    }
}
