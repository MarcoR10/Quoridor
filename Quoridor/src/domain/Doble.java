package domain;

import java.io.Serializable;

public class Doble extends Casilla implements Serializable {

    public Doble(int fila, int columna, String type) {
        super(fila, columna, type);
    }

    public boolean otorgaTurnoAdicional() {
        return true;
    }

}
