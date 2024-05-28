package domain;

import java.io.Serializable;

/**
 * La clase Teletransportador representa una casilla especial en un juego de tablero que permite teletransportar la ficha del jugador.
 * Extiende de la clase Casilla e implementa la interfaz Serializable.
 *
 * @see Casilla
 * @see Serializable
 */
public class Teletransportador extends Casilla implements Serializable {

    /**
     * Constructor para crear una instancia de la casilla Teletransportador.
     *
     * @param fila la fila en la que se encuentra la casilla.
     * @param columna la columna en la que se encuentra la casilla.
     * @param type el tipo de casilla representado como una cadena de texto.
     */
    public Teletransportador(int fila, int columna, String type) {
        super(fila, columna, type);
    }

    /**
     * Método que teletransporta la ficha del jugador a la posición de la casilla Teletransportador.
     *
     * @param jugadoractual el jugador cuya ficha se va a teletransportar.
     */
    public void teleport(Jugador jugadoractual){
        jugadoractual.getFicha().mover(fila,columna);
    }

    /**
     * Método que verifica si un movimiento desde esta casilla a una casilla destino es permitido.
     *
     * @param filaDestino la fila de la casilla destino.
     * @param columnaDestino la columna de la casilla destino.
     * @param tablero el tablero en el que se está jugando.
     * @return true si el movimiento es permitido, false en caso contrario.
     */
    public boolean permiteMovimiento(int filaDestino, int columnaDestino, Tablero tablero) {
        int deltaFila = Math.abs(fila - filaDestino);
        int deltaColumna = Math.abs(columna - columnaDestino);
        if (deltaFila <= 2 && deltaColumna <= 2) {
            return true;
        }
        return false;
    }


}
