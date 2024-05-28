package domain;

import java.io.Serializable;

/**
 * La clase Humano representa a un jugador humano en un juego.
 * Extiende de la clase Jugador e implementa la interfaz Serializable.
 *
 * @see Jugador
 * @see Serializable
 */
public class Humano extends Jugador implements Serializable {

    /**
     * Constructor para crear una instancia de un jugador humano.
     *
     * @param nombre el nombre del jugador humano.
     * @param color el color asociado al jugador humano.
     */
    public Humano(String nombre ,String color){
        super(nombre,color);
    }

    /**
     * Método que permite al jugador humano realizar un movimiento en el tablero.
     *
     * @param x la coordenada x del movimiento.
     * @param y la coordenada y del movimiento.
     */
    public void jugar(int x, int y) {
        ficha.mover(x,y);
    }

}
