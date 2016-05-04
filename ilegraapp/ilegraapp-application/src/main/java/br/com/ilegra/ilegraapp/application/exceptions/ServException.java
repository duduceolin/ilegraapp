package br.com.ilegra.ilegraapp.application.exceptions;

/**
 *
 * @author dudu
 */
public class ServException extends Exception {

    public ServException(String message) {
        super(message);
    }

    public ServException(String cod, String message) {
        super(cod + " - " + message);
    }

}
