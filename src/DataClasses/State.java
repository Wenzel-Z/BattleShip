package DataClasses;

public enum State {
    HIT {
        @Override
        public String toString() {
            return "X";
        }
    },
    MISS {
        @Override
        public String toString() {
            return "O";
        }
    },
    SHIP {
        @Override
        public String toString() {
            return "S";
        }
    },
    WATER {
        @Override
        public String toString() {
            return ".";
        }
    }
}
