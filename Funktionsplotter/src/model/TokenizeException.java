package model;

/**
 * Wrapper Klasse für Exceptions die in der Funktionsberechnungsklasse entstehen
 * @author smodlich
 *
 */
public class TokenizeException extends RuntimeException {
	
	public TokenizeException(String message) {
        super(message);
    }

    public TokenizeException(String message, Exception ex) {
        super(message, ex);
    }

}
