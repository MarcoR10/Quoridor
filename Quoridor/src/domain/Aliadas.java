package domain;

import java.io.Serializable;

public class Aliadas extends Barrera implements Serializable {

    public Aliadas(int filaInicio, int columnaInicio,Jugador propietario) {
        super(filaInicio, columnaInicio, propietario);
    }

    public boolean bloqueaCamino(int fila, int columna,Jugador jugadorActual) {
        return false;
    }
}
