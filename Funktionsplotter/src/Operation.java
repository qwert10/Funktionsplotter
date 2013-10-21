
public enum Operation implements Token {

	PLUS("+", 1) {
        public int berechne(int a, int b) {
            return a+b;
        }
    },

    MINUS("-", 1) {
        public int berechne(int a, int b) {
            return a-b;
        }
    },

    MAL("*", 2) {
        public int berechne(int a, int b) {
            return a*b;
        }
    },

    DURCH("/", 2) {
        public int berechne(int a, int b) {
            return a/b;
        }
    };
    
    private final int prioritaet;
    private final String name;

    Operation(String name, int prio) {
        this.prioritaet = prio;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return prioritaet;
    }
}
