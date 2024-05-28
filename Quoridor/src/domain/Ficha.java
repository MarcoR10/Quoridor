package domain;

import java.awt.*;
import java.io.Serializable;


/**
 * La clase Ficha representa una ficha en un juego de tablero.
 * Cada ficha tiene un nombre único y coordenadas que indican su posición en el tablero.
 * Esta clase implementa la interfaz Serializable.
 *
 * @see Serializable
 */
public class Ficha implements Serializable {

    private char nombre;
    private Point coordenadas;

    /**
     * Constructor para crear una instancia de la ficha.
     *
     * @param nombre el nombre único de la ficha.
     * @param coordenadas las coordenadas que indican la posición inicial de la ficha en el tablero.
     */
    public Ficha(char nombre, Point coordenadas){
        this.nombre = nombre;
        this.coordenadas = coordenadas;
    }

    /**
     * Establece las coordenadas de la ficha.
     * @param coordenadas las nuevas coordenadas de la ficha.
     */
    public void setCoordenadas(Point coordenadas) {
        this.coordenadas = coordenadas;
    }

    /**
     * Mueve la ficha a las nuevas coordenadas especificadas.
     *
     * @param x la coordenada x de destino.
     * @param y la coordenada y de destino.
     */
    public void mover(int x, int y) {
        coordenadas.setLocation(x, y);
    }

    /**
     * Verifica si la ficha está en las coordenadas especificadas.
     *
     * @param x la coordenada x a verificar.
     * @param y la coordenada y a verificar.
     * @return true si la ficha está en las coordenadas especificadas, false en caso contrario.
     */
    public boolean estaEn(int x, int y) {
        return coordenadas.getX() == x && coordenadas.getY() == y;
    }

    /**
     * Obtiene el nombre de la ficha.
     * @return el nombre de la ficha.
     */
    public char getNombre() {
        return nombre;
    }

    /**
     * Obtiene las coordenadas actuales de la ficha.
     * @return las coordenadas actuales de la ficha.
     */
    public Point getCoordenadas() {
        return coordenadas;
    }
}
