package model;

/**
 * Enum Klasse, die die Rechenoperationen der Funktion bereithält, die durch ein Rechenzeichen ausgedrückt werden können.
 * Außerdem wird die Priorität erfasst um zum Beispiel Multiplikation von Addition zu berrechnen. Für die Berrechnung der Potenzen
 * muss außerdem die Assoziativität beachtet werden. Diese wird durch einen einfachen Zahlenwert (2 ist rechtsassoziativ) angegeben. 
 * @author smodlich
 *
 */
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
    },
    
    HOCH("^", 3,2) {
        public double berechne(double a, double b) {
            return Math.pow(a, b);
        }
    };
    ;
    
    
    
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
