package domain;

import java.io.Serializable;

/**
 * La clase Temporal representa una barrera temporal en el juego, que se desactiva después de un cierto número de turnos.
 * Extiende de la clase Barrera e implementa la interfaz Serializable.
 *
 * @see Barrera
 * @see Serializable
 */
public class Temporal extends Barrera implements Serializable {

    private int turnosRestantes;

    /**
     * Constructor para crear una instancia de la barrera temporal.
     *
     * @param filaInicio la fila inicial donde se coloca la barrera.
     * @param columnaInicio la columna inicial donde se coloca la barrera.
     * @param propietario el jugador que coloca la barrera y es propietario de la misma.
     */
    public Temporal(int filaInicio, int columnaInicio, Jugador propietario) {
        super(filaInicio, columnaInicio, propietario);
        this.turnosRestantes = 5;
    }

    /**
     * Obtiene el número de turnos restantes antes de que la barrera se desactive.
     *
     * @return el número de turnos restantes.
     */
    public int getTurnosRestantes() {
        return turnosRestantes;
    }

    /**
     * Reduce en uno el número de turnos restantes antes de que la barrera se desactive.
     */
    public void reducirTurno() {
        turnosRestantes--;
    }

    /**
     * Verifica si la barrera temporal sigue activa.
     *
     * @return true si la barrera sigue activa (tiene turnos restantes), false en caso contrario.
     */
    public boolean esActiva() {
        return turnosRestantes > 0;
    }

    /**
     * Método que determina si la barrera temporal bloquea el camino a un jugador específico.
     * En el caso de la barrera temporal, siempre retorna false, ya que no bloquea ningún camino.
     *
     * @param fila la fila que se está evaluando.
     * @param columna la columna que se está evaluando.
     * @param jugadorActual el jugador que se está moviendo.
     * @return siempre retorna false, indicando que la barrera no bloquea ningún camino.
     */
    public boolean bloqueaCamino(int fila, int columna,Jugador jugadorActual) {
        return false;
    }

}
