package domain;

import java.io.Serializable;

public abstract class  Casilla implements Serializable {

    protected Jugador jugador;
    protected Barrera barrera;
    protected int fila,columna;
    protected String type,lugar;

    public Casilla(int fila,int columna,String type){
        this.fila = fila;
        this.columna = columna;
        this.type = type;
        this.jugador = null;
        this.barrera = null;
    }

    public char getTipo(){
        char tipo = Character.toUpperCase(type.charAt(0));
        return tipo;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setJugadorNull() {
        this.jugador = null;
    }

    public Barrera getBarrera() {
        return barrera;
    }

    public void setBarrera(Barrera barrera) {
        this.barrera = barrera;
    }

}
