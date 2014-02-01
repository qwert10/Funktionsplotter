package model;

/**
 * Enum Klasse, die die Rechenoperationen der Funktion bereithält, die durch ein Rechenzeichen ausgedrückt werden können.
 * Außerdem wird die Priorität erfasst um zum Beispiel Multiplikation von Addition zu berrechnen. Für die Berrechnung der Potenzen
 * muss außerdem die Assoziativität beachtet werden. Diese wird durch einen einfachen Zahlenwert (2 ist rechtsassoziativ) angegeben. 
 * @author smodlich
 *
 */
public enum Operation implements Token {
	
/**
 * Addition
 */
	PLUS("+", 1,1) {
        public double berechne(double a, double b) {
            return a+b;
        }
    },
    /**
     * Subtraktion
     */

    MINUS("-", 1,1) {
        public double berechne(double a,double b) {
            return a-b;
        }
    },
    /**
     * Multiplikation
     */

    MAL("*", 2,1) {
        public double berechne(double a,double b) {
            return a*b;
        }
    },
    /**
     * Division
     */

    DURCH("/", 2,1) {
        public double berechne(double a, double b) {
            return a/b;
        }
    },
    /**
     * Potenzierung
     */
    
    HOCH("^", 3,2) {
        public double berechne(double a, double b) {
            return Math.pow(a, b);
        }
    };
    
    
    /**
     * Abstrakte Berechnungsmethode. Muss von jedem Enum individuell überschrieben werden mit der konkreten Berechnungsmethode.
     * @param a 1. Zahl
     * @param b 2. Zahl
     * @return Berechnungswert
     */
    public abstract double berechne(double a ,double b);
    
    /**
     * Priorität der Operation
     */
    private final int prioritaet;
    /**
     * Zeichen der Operation
     */
    private final String name;
    /**
     * Assoziativität der Operation 1=linksassoziativ 2=rechtsassoziativ 
     */
    private final int asso;

    /**
     * Konstruktor für die Operationen
     * @param name Zeichen
     * @param prio Priorität
     * @param az Assoziativität
     */
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
