package domain;

import java.awt.*;
import java.io.Serializable;


/**
 * La clase Regreso representa una casilla especial en un juego de tablero que hace retroceder la ficha del jugador.
 * Extiende de la clase Casilla e implementa la interfaz Serializable.
 *
 * @see Casilla
 * @see Serializable
 */

public class Regreso extends Casilla implements Serializable {

    /**
     * Constructor para crear una instancia de la casilla Regreso.
     *
     * @param fila la fila en la que se encuentra la casilla.
     * @param columna la columna en la que se encuentra la casilla.
     * @param type el tipo de casilla representado como una cadena de texto.
     */
    public Regreso(int fila, int columna, String type) {
        super(fila, columna, type);
    }

    /**
     * Método que hace retroceder la ficha del jugador en la casilla Regreso.
     *
     * @param jugador el jugador cuya ficha se va a retroceder.
     * @param tablero el tablero en el que se está jugando.
     */
    public void retrocederFicha(Jugador jugador, Tablero tablero) {
        Point coor = jugador.getFicha().getCoordenadas();
        int filaRetroceder;
        if(jugador.getColor().equals("Rojo")){
            filaRetroceder = coor.x - 2;
        }else {
            filaRetroceder = coor.x + 2;
        }
        if (filaRetroceder >= 0 && filaRetroceder <= tablero.getFilas() && tablero.getCasilla(filaRetroceder, coor.y).getJugador() == null) {
            tablero.actualizarCasilla(coor.x, coor.y, filaRetroceder, coor.y, jugador);
            jugador.getFicha().mover(filaRetroceder, coor.y);
        }
    }

}
