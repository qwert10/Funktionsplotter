package model;

public enum Operation implements Token {

	PLUS("+", 1,1) {
        public double berechne(double a, double b) {
            return a+b;
        }
    },

    MINUS("-", 1,1) {
        public double berechne(double a,double b) {
            return a-b;
        }
    },

    MAL("*", 2,1) {
        public double berechne(double a,double b) {
            return a*b;
        }
    },

    DURCH("/", 2,1) {
        public double berechne(double a, double b) {
            return a/b;
        }
    };
    
    public abstract double berechne(double a ,double b);
    
    private final int prioritaet;
    private final String name;
    private final int asso;

    Operation(String name, int prio,int az) {
        this.prioritaet = prio;
        this.name = name;
        this.asso=az;
    }

    public String getName() {
        return name;
    }

    public int getPrioritaet() {
        return prioritaet;
    }
    
    public int getAsso()
    {
    	return asso;
    }
}