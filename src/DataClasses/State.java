package DataClasses;

/**
 * Enum to track the state of a board cell
 */
public enum State {
    HIT {
        @Override
        public String toString() {
            return "x";
        }
    },
    MISS {
        @Override
        public String toString() {
            return "o";
        }
    },
    SHIP {
        @Override
        public String toString() { return "S"; }
    },
    WATER {
        @Override
        public String toString() { return "~"; }
    }
}
