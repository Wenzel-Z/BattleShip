package Model;

import DataClasses.Coordinate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class BattleSalvoAI {
    Random random;
    HashSet<Coordinate> hitSquares;
    int boardWidth;
    int boardHeight;
    int area;

    public BattleSalvoAI() {
        this.random = new Random();
        this.hitSquares = new HashSet<>();
    }

    public void setDimensions(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.area = boardWidth * boardHeight;
    }

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
