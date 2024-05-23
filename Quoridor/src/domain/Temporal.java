package domain;

public class Temporal extends Barrera {
    private int turnosRestantes;

    public Temporal(int filaInicio, int columnaInicio, Jugador propietario) {
        super(filaInicio, columnaInicio, propietario);
        this.turnosRestantes = 4;
    }

    public void reducirTurno() {
        turnosRestantes--;
    }

    public boolean esActiva() {
        return turnosRestantes > 0;
    }

    public boolean bloqueaCamino(int fila, int columna,Jugador jugadorActual) {
        return false;
    }
}
