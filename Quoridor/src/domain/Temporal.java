package domain;

public class Temporal extends Barrera {
    private int turnosRestantes;

    public Temporal(int filaInicio, int columnaInicio, int filaFin, int columnaFin, Jugador propietario) {
        super(filaInicio, columnaInicio, filaFin, columnaFin, propietario);
        this.turnosRestantes = 4;
    }

    public void reducirTurno() {
        turnosRestantes--;
    }

    public boolean esActiva() {
        return turnosRestantes > 0;
    }

    public boolean bloqueaCamino(int fila, int columna, int nuevaFila, int nuevaColumna, Jugador jugadorActual) {
        if (!esActiva()) {
            return false;
        }
        if (filaIn == filaFin && columnaIn < columnaFin) {
            return (fila == filaIn && ((columna >= columnaIn && columna < columnaFin) || (nuevaColumna >= columnaIn && nuevaColumna < columnaFin)));
        }
        if (columnaIn == columnaFin && filaIn < filaFin) {
            return (columna == columnaIn && ((fila >= filaIn && fila < filaFin) || (nuevaFila >= filaIn && nuevaFila < filaFin)));
        }
        return false;
    }
}
