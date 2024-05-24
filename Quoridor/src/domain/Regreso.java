package domain;

import java.awt.*;
import java.io.Serializable;

public class Regreso extends Casilla implements Serializable {
    public Regreso(int fila, int columna, String type) {
        super(fila, columna, type);
    }

    public void retrocederFicha(Jugador jugador, Tablero tablero) {
        Point coor = jugador.getFicha().getCoordenadas();
        int filaRetroceder = coor.x - 2;
        // Retroceder dos filas
        // Verificar si la nueva posición es válida
        if (filaRetroceder >= 0) {
            tablero.actualizarCasilla(coor.x, coor.y, filaRetroceder, coor.y, jugador);
            jugador.jugar(filaRetroceder, coor.y);
            tablero.imprimirTablero();
        }
    }

}
