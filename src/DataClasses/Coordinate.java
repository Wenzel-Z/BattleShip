package DataClasses;

import java.util.Objects;

/**
 * Record for saving coordinates on the boards
 *
 * @param x     index of x
 * @param y     index of y
 */
public record Coordinate(int x, int y) {

    /**
     * Checks if x and y value are the same as the object passed in
     *
     * @param o     the reference object with which to compare
     * @return      true if the values are equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordinate coordinate)) {
            return false;
        }

        return coordinate.x == this.x && coordinate.y == this.y;
    }

    /**
     * Hashes the values
     *
     * @return  hash of the coordinate
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
