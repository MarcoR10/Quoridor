package domain;

import java.io.Serializable;

/**
 * La clase Barrera es una clase abstracta que representa una barrera en un juego.
 * Las clases que heredan de Barrera deben implementar el método abstracto bloqueaCamino.
 * Esta clase implementa la interfaz Serializable.
 *
 * @see Serializable
 */
public abstract class Barrera implements Serializable {

    protected int fila,columna,longitud;
    protected Jugador propietario;

    /**
     * Constructor para inicializar una nueva instancia de Barrera.
     *
     * @param filaIn la fila inicial donde se coloca la barrera.
     * @param columnaIn la columna inicial donde se coloca la barrera.
     * @param jugadorActual el jugador que coloca la barrera y es propietario de la misma.
     */
    public Barrera(int filaIn,int columnaIn,Jugador jugadorActual){
        this.fila = fila;
        this.columna = columna;
        this.propietario = jugadorActual;
    }


    /**
     * Obtiene la columna donde se encuentra la barrera.
     *
     * @return la columna de la barrera.
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Obtiene la fila donde se encuentra la barrera.
     *
     * @return la fila de la barrera.
     */
    public int getFila() {
        return fila;
    }

    /**
     * Verifica si esta barrera está adyacente a otra barrera.
     *
     * @param otraBarrera la otra barrera a verificar.
     * @return true si las barreras están adyacentes en la misma fila o columna, false en caso contrario.
     */

    public boolean estanJuntas(Barrera otraBarrera) {
        // Verificar si comparten la misma fila o columna adyacente
        return (this.fila == otraBarrera.fila && Math.abs(this.columna - otraBarrera.columna) == 0) ||
                (this.columna == otraBarrera.columna && Math.abs(this.fila - otraBarrera.fila) == 0);
    }

    /**
     * Método abstracto que determina si la barrera bloquea el camino a un jugador específico.
     * Este método debe ser implementado por las clases que heredan de Barrera.
     *
     * @param fila la fila que se está evaluando.
     * @param columna la columna que se está evaluando.
     * @param jugadorActual el jugador que se está moviendo y se verifica si está bloqueado.
     * @return true si la barrera bloquea el camino, false en caso contrario.
     */
    public abstract boolean bloqueaCamino(int fila, int columna, Jugador jugadorActual);

}
