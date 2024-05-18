package domain;

public class BarreraNormal extends Barrera {

    public BarreraNormal(int filaInicio, int columnaInicio, int filaFin, int columnaFin, Jugador propietario) {
        super(filaInicio, columnaInicio, filaFin, columnaFin, propietario);
    }

    public boolean bloqueaCamino(int fila, int columna, int nuevaFila, int nuevaColumna, Jugador jugadorActual) {
        // Lógica específica para barrera normal de dos casillas
        if (filaIn == filaFin && columnaIn < columnaFin) {
            return (fila == filaIn && ((columna >= columnaIn && columna < columnaFin) || (nuevaColumna >= columnaIn && nuevaColumna < columnaFin)));
        }
        if (columnaIn == columnaFin && filaIn < filaFin) {
            return (columna == columnaIn && ((fila >= filaIn && fila < filaFin) || (nuevaFila >= filaIn && nuevaFila < filaFin)));
        }
        return false;
    }

}
