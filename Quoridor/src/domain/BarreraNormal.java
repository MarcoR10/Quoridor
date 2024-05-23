package domain;

public class BarreraNormal extends Barrera {

    public BarreraNormal(int filaInicio, int columnaInicio, Jugador propietario) {
        super(filaInicio, columnaInicio, propietario);
    }

    public boolean bloqueaCamino(int fila, int columna, Jugador jugadorActual) {
        return true;
    }

}
