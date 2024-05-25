package domain;

import java.io.Serializable;

public class Humano extends Jugador implements Serializable {

    public Humano(String nombre ,String color){
        super(nombre,color);
    }

    public void jugar(int x, int y) {
        ficha.mover(x,y);
    }

}
