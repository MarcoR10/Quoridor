package domain;

import java.io.Serializable;

/**
 * La clase Larga representa una barrera larga en el juego.
 * Extiende de la clase Barrera e implementa la interfaz Serializable.
 *
 * <p>Esta barrera no bloquea ningún camino, permitiendo que los jugadores se muevan libremente.</p>
 *
 * @see Barrera
 * @see Serializable
 */
public class Larga extends Barrera implements Serializable {

    /**
     * Constructor para crear una instancia de la barrera larga.
     *
     * @param filaIn la fila inicial donde se coloca la barrera.
     * @param columnaIn la columna inicial donde se coloca la barrera.
     * @param jugadorActual el jugador que coloca la barrera y es propietario de la misma.
     */
    public Larga(int filaIn, int columnaIn, Jugador jugadorActual) {
        super(filaIn, columnaIn, jugadorActual);
    }

    /**
     * Método que determina si la barrera larga bloquea el camino a un jugador específico.
     * En el caso de la barrera larga, siempre retorna false, lo que significa que no bloquea ningún camino.
     *
     * @param fila la fila que se está evaluando.
     * @param columna la columna que se está evaluando.
     * @param jugadorActual el jugador que se está moviendo.
     * @return siempre retorna false, indicando que la barrera no bloquea ningún camino.
     */
    public boolean bloqueaCamino(int fila, int columna, Jugador jugadorActual) {
        return false;
    }

}
