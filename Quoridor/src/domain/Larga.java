package domain;

import java.io.Serializable;

public class Larga extends Barrera implements Serializable {

    public Larga(int filaIn, int columnaIn, Jugador jugadorActual) {
        super(filaIn, columnaIn, jugadorActual);
    }

    public boolean bloqueaCamino(int fila, int columna, Jugador jugadorActual) {
        return false;
    }
}
