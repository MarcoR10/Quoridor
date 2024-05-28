package domain;

import java.io.Serializable;


/**
 * La clase Doble representa una casilla especial en un tablero de juego que otorga
 * un turno adicional al jugador que la ocupa. Extiende de la clase Casilla e implementa
 * la interfaz Serializable.
 *
 * @see Casilla
 * @see Serializable
 */
public class Doble extends Casilla implements Serializable {

    /**
     * Constructor para crear una instancia de la casilla Doble.
     * @param fila la fila en la que se encuentra la casilla.
     * @param columna la columna en la que se encuentra la casilla.
     * @param type el tipo de casilla representado como una cadena de texto.
     */
    public Doble(int fila, int columna, String type) {
        super(fila, columna, type);
    }


    /**
     * Método que indica si la casilla otorga un turno adicional al jugador que la ocupa.
     * @return true si la casilla otorga un turno adicional, false en caso contrario.
     */
    public boolean otorgaTurnoAdicional() {
        return true;
    }

}
