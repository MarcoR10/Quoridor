package domain;

import java.awt.*;
import java.io.Serializable;

/**
 * La clase Jugador representa un jugador en un juego.
 * Es una clase abstracta que debe ser extendida por clases concretas que definan tipos específicos de jugadores.
 * Esta clase implementa la interfaz Serializable.
 *
 * @see Serializable
 */
public abstract class Jugador implements Serializable {

    protected Ficha ficha;
    protected String nombre ,color;
    protected int barreras , casillas;


    /**
     * Constructor para crear una instancia de un jugador.
     *
     * @param nombre el nombre del jugador.
     * @param color el color asociado al jugador.
     */
    public Jugador(String nombre,String color) {
        this.nombre = nombre;
        this.color = color;
        ficha = new Ficha(nombre.charAt(0),new Point(0,0));
        barreras = 10;
    }

    /**
     * Método abstracto que define la acción de jugar para un jugador.
     *
     * @param x la coordenada x del movimiento.
     * @param y la coordenada y del movimiento.
     */
    public abstract void jugar(int x, int y);

    /**
     * Obtiene el nombre del jugador.
     *
     * @return el nombre del jugador.
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Obtiene la cantidad de barreras restantes que el jugador puede colocar.
     *
     * @return la cantidad de barreras restantes del jugador.
     */
    public int getBarreras() {
        return barreras;
    }

    /**
     * Resta una barrera al contador de barreras del jugador.
     */
    public void restaBarrreras(){
        barreras = barreras - 1;
    }

    /**
     * Obtiene la ficha asociada al jugador.
     *
     * @return la ficha asociada al jugador.
     */
    public Ficha getFicha() {
        return ficha;
    }

    /**
     * Restablece el estado inicial del jugador.
     */
    public void reset() {
        this.barreras = 10; // Número inicial de barreras
    }

    /**
     * Obtiene el color asociado al jugador.
     *
     * @return el color asociado al jugador.
     */
    public String getColor() {
        return color;
    }
}
