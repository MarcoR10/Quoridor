package domain;

import java.awt.*;
import java.io.Serializable;

public class Regreso extends Casilla implements Serializable {
    public Regreso(int fila, int columna, String type) {
        super(fila, columna, type);
    }

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
