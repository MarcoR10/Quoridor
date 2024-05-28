package domain;

import java.io.Serializable;


/**
 * La clase BarreraNormal representa una barrera estándar en el juego.
 * Extiende de la clase abstracta Barrera e implementa la interfaz Serializable.
 *
 * <p>Esta barrera siempre bloquea el camino sin importar el jugador que intente pasar.</p>
 *
 * @see Barrera
 * @see Serializable
 */
public class BarreraNormal extends Barrera implements Serializable {


    /**
     * Constructor para crear una instancia de la barrera normal.
     *
     * @param filaInicio la fila inicial donde se coloca la barrera.
     * @param columnaInicio la columna inicial donde se coloca la barrera.
     * @param propietario el jugador que coloca la barrera y es propietario de la misma.
     */
    public BarreraNormal(int filaInicio, int columnaInicio, Jugador propietario) {
        super(filaInicio, columnaInicio, propietario);
    }

    /**
     * Método que determina si la barrera bloquea el camino a un jugador específico.
     * En el caso de BarreraNormal, siempre bloquea el camino, sin importar el jugador.
     *
     * @param fila la fila que se está evaluando.
     * @param columna la columna que se está evaluando.
     * @param jugadorActual el jugador que se está moviendo.
     * @return siempre retorna true, indicando que la barrera bloquea el camino.
     */
    public boolean bloqueaCamino(int fila, int columna, Jugador jugadorActual) {
        return true;
    }

}
