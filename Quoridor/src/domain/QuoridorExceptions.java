package domain;

import java.io.Serializable;


/**
 * La clase QuoridorExceptions representa una excepción personalizada para el juego Quoridor.
 * Esta clase extiende Exception e implementa Serializable para permitir la serialización.
 */
public class QuoridorExceptions extends Exception implements Serializable {

    /**
     * Constructor de la clase QuoridorExceptions.
     * @param message El mensaje de la excepción.
     */
    public QuoridorExceptions(String message){
        super(message);
    }

}
