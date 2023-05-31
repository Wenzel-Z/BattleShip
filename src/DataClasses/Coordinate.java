package DataClasses;

import java.util.Objects;

public record Coordinate(int x, int y) {

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordinate coordinate)) {
            return false;
        }

        return coordinate.x == this.x && coordinate.y == this.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
