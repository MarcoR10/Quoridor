package domain;

import java.io.Serializable;

public class Teletransportador extends Casilla implements Serializable {

    public Teletransportador(int fila, int columna, String type) {
        super(fila, columna, type);
    }

    public void teleport(Jugador jugadoractual){
        jugadoractual.getFicha().mover(fila,columna);
    }

    public boolean permiteMovimiento(int filaDestino, int columnaDestino, Tablero tablero) {
        int deltaFila = Math.abs(fila - filaDestino);
        int deltaColumna = Math.abs(columna - columnaDestino);
        if (deltaFila <= 2 && deltaColumna <= 2) {
            return true;
        }
        return false;
    }


}
