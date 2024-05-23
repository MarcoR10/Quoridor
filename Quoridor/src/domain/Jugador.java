package domain;

import java.awt.*;

public abstract class Jugador {

    protected Ficha ficha;
    protected String nombre ,color;
    protected int barreras , casillas;

    public Jugador(String nombre,String color) {
        this.nombre = nombre;
        this.color = color;
        ficha = new Ficha(nombre.charAt(0),new Point(0,0));
        barreras = 10;
    }

    public abstract void jugar(int x, int y);

    public String getNombre(){
        return nombre.substring(0, 1).toUpperCase();
    }

    public int getBarreras() {
        return barreras;
    }

    public void restaBarrreras(){
        barreras = barreras - 1;
    }

    public Ficha getFicha() {
        return ficha;
    }
}
