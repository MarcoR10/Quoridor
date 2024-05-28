package domain;

import java.io.Serializable;


/**
 * La clase Aliadas representa una barrera específica que puede ser colocada en un juego
 * por un jugador. Esta barrera puede bloquear el camino a otros jugadores.
 * La clase extiende de Barrera e implementa la interfaz Serializable.
 *
 * <p>La barrera solo bloquea el camino a jugadores que no sean el propietario de la barrera.</p>
 *
 * @see Barrera
 * @see Serializable
 */

public class Aliadas extends Barrera implements Serializable {


    /**
     * Constructor para crear una instancia de la barrera Aliadas.
     *
     * @param filaInicio la fila inicial donde se coloca la barrera.
     * @param columnaInicio la columna inicial donde se coloca la barrera.
     * @param propietario el jugador que coloca la barrera y es propietario de la misma.
     */

    public Aliadas(int filaInicio, int columnaInicio,Jugador propietario) {
        super(filaInicio, columnaInicio, propietario);
    }


    /**
     * Método que determina si la barrera bloquea el camino a un jugador específico.
     *
     * @param fila la fila que se está evaluando.
     * @param columna la columna que se está evaluando.
     * @param jugadorActual el jugador que se está moviendo y se verifica si está bloqueado.
     * @return true si el jugador actual no es el propietario de la barrera, indicando que
     *         la barrera bloquea su camino; false si el jugador actual es el propietario,
     *         indicando que la barrera no bloquea su camino.
     */
    @Override
    public boolean bloqueaCamino(int fila, int columna, Jugador jugadorActual) {
        return !propietario.equals(jugadorActual);
    }

}
