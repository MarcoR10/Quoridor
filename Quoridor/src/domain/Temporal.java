package domain;

import java.io.Serializable;

public class Temporal extends Barrera implements Serializable {

    private int turnosRestantes;

    public Temporal(int filaInicio, int columnaInicio, Jugador propietario) {
        super(filaInicio, columnaInicio, propietario);
        this.turnosRestantes = 5;
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
