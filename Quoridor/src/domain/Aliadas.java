package domain;

public class Aliadas extends Barrera{

    public Aliadas(int filaInicio, int columnaInicio,Jugador propietario) {
        super(filaInicio, columnaInicio, propietario);
    }

    public boolean bloqueaCamino(int fila, int columna,Jugador jugadorActual) {
        return false;
    }
}
