package domain;

public abstract class Barrera {

    protected int fila,columna;
    protected Jugador propietario;

    public Barrera(int filaIn,int columnaIn,Jugador jugadorActual){
        this.fila = fila;
        this.columna = columna;
        this.propietario = jugadorActual;
    }

    public int getColumna() {
        return columna;
    }

    public int getFila() {
        return fila;
    }

    public boolean estanJuntas(Barrera otraBarrera) {
        // Verificar si comparten la misma fila o columna adyacente
        return (this.fila == otraBarrera.fila && Math.abs(this.columna - otraBarrera.columna) == 0) ||
                (this.columna == otraBarrera.columna && Math.abs(this.fila - otraBarrera.fila) == 0);
    }

    public abstract boolean bloqueaCamino(int fila, int columna, Jugador jugadorActual);

}
