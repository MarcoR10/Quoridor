package domain;

import java.io.Serializable;

public class Teletransportador extends Casilla implements Serializable {

    public Teletransportador(int fila, int columna, String type) {
        super(fila, columna, type);
    }

    public boolean permiteMovimiento(int filaDestino, int columnaDestino, Tablero tablero) {
        int deltaFila = Math.abs(fila - filaDestino);
        int deltaColumna = Math.abs(columna - columnaDestino);
        if (deltaFila <= 1 && deltaColumna <= 1) {
            if (tablero.getCasilla(filaDestino, columnaDestino).getJugador() == null) {
                return true;
            }
        }
        return false;
    }

}
