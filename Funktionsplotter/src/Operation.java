
public enum Operation implements Token {

	PLUS("+", 1) {
        public double berechne(double a, double b) {
            return a+b;
        }
    },

    MINUS("-", 1) {
        public double berechne(double a,double b) {
            return a-b;
        }
    },

    MAL("*", 2) {
        public double berechne(double a,double b) {
            return a*b;
        }
    },

    DURCH("/", 2) {
        public double berechne(double a, double b) {
            return a/b;
        }
    };
    
    public abstract double berechne(double a ,double b);
    
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
