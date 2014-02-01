package model;

/**
 * Wrapper Klasse für Exceptions die in der Funktionsberechnungsklasse entstehen
 * @author smodlich
 *
 */
public class TokenizeException extends RuntimeException {
	
	/**
	 * Ruft entsprechende Funktion der Oberklasse auf.
	 * @param message
	 */
	public TokenizeException(String message) {
        super(message);
    }

	/**
	 * Ruft entsprechende Funktion Oberklasse auf
	 * @param message
	 * @param ex
	 */
    public TokenizeException(String message, Exception ex) {
        super(message, ex);
    }

}
