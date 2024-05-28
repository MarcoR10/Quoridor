package domain;

import java.io.Serializable;

/**
 * La clase Normal representa una casilla normal en un tablero de juego.
 * Extiende de la clase Casilla e implementa la interfaz Serializable.
 *
 * @see Casilla
 * @see Serializable
 */
public class Normal extends Casilla implements Serializable {

    /**
     * Constructor para crear una instancia de la casilla normal.
     *
     * @param fila la fila en la que se encuentra la casilla.
     * @param columna la columna en la que se encuentra la casilla.
     * @param type el tipo de casilla representado como una cadena de texto.
     */
    public Normal(int fila, int columna,String type) {
        super(fila, columna, type);
    }

}
