package DataClasses;

/**
 * Enum for possible types of ships
 */
public enum ShipType {
    BATTLESHIP (5) {
        @Override
        public String toString() { return "B"; }
    },
    CARRIER (6) {
        @Override
        public String toString() { return "C"; }
    },
    DESTROYER (4) {
        @Override
        public String toString() { return "D"; }
    },
    SUBMARINE (3) {
        @Override
        public String toString() { return "S"; }
    };

    private final int size;

    /**
     * Sets size of ship type
     *
     * @param size  size of ship
     */
    ShipType(int size) {
        this.size = size;
    }

    /**
     * Getter for size of ship type
     *
     * @return  size of ship
     */
    public int getSize() {
        return this.size;
    }
}
