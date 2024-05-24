package domain;

import java.awt.*;
import java.io.Serializable;

public class Regreso extends Casilla implements Serializable {


    public Regreso(int fila, int columna, String type) {
        super(fila, columna, type);
    }

    public void retrocederFicha(Jugador jugador, Tablero tablero) {
        Point coor = jugador.getFicha().getCoordenadas();
        if(jugador.getColor().equals("Rojo")){
            int filaRetroceder = coor.x - 2; // Retroceder dos filas
            if (filaRetroceder >= 0 && tablero.getCasilla(filaRetroceder, coor.y).getJugador() == null) {
                tablero.actualizarCasilla(coor.x, coor.y, filaRetroceder, coor.y, jugador);
                jugador.getFicha().setCoordenadas(new Point(filaRetroceder, coor.y));
            }
        }
        if (jugador.getColor().equals("Azul")){
            int filaRetroceder = coor.x + 2; // Retroceder dos filas
            if (filaRetroceder >= 0 && tablero.getCasilla(filaRetroceder, coor.y).getJugador() == null) {
                tablero.actualizarCasilla(coor.x, coor.y, filaRetroceder, coor.y, jugador);
                jugador.getFicha().setCoordenadas(new Point(filaRetroceder, coor.y));
            }
        }

    }

    public void realizarAccion(Jugador jugador, Tablero tablero) {
        retrocederFicha(jugador, tablero);
    }


}
