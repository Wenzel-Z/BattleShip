package DataClasses;

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

    ShipType(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }
}
