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
        this.random = new Random();;
        this.hitSquares = new HashSet<>();
    }

    public void setDimensions(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.area = boardWidth * boardHeight;
    }

    public ArrayList<Coordinate> takeRandomShots() {
        int shotsTaken = 0;
        int difference = this.area - this.hitSquares.size();
        if (difference < 8) {
            shotsTaken = difference;
        }

        ArrayList<Coordinate> coordinates = new ArrayList<>();
        while (shotsTaken < 8) {
            Coordinate coordinate = new Coordinate(random.nextInt(this.boardWidth), random.nextInt(this.boardHeight));
            // TODO fix random generation
            if (!(this.hitSquares.contains(coordinate))) {
                coordinates.add(coordinate);
                this.hitSquares.add(coordinate);

                shotsTaken++;
            }
        }

        return coordinates;
    }
}
