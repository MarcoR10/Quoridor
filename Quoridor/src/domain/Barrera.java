package domain;

public abstract class Barrera {

    protected int filaIn,columnaIn,filaFin,columnaFin;
    protected Jugador propietario;

    public Barrera(int filaIn,int columnaIn,int filaFin,int columnaFin,Jugador jugadorActual){
        this.filaIn = filaIn;
        this.columnaIn = columnaIn;
        this.filaFin = filaFin;
        this.columnaFin = columnaFin;
        this.propietario = propietario;
    }

    public abstract boolean bloqueaCamino(int fila, int columna, int nuevaFila, int nuevaColumna, Jugador jugadorActual);

}
