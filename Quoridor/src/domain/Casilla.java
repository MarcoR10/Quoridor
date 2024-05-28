package domain;

import java.io.Serializable;


/**
 * La clase Casilla representa una casilla en un tablero de juego. Es una clase abstracta
 * que debe ser extendida por clases concretas que definan tipos específicos de casillas.
 * Esta clase implementa la interfaz Serializable.
 * @see Serializable
 */
public abstract class Casilla implements Serializable {

    protected Jugador jugador;
    protected Barrera barrera;
    protected int fila,columna;
    protected String type,lugar;


    /**
     * Constructor para inicializar una nueva instancia de Casilla.
     * @param fila la fila en la que se encuentra la casilla.
     * @param columna la columna en la que se encuentra la casilla.
     * @param type el tipo de casilla representado como una cadena de texto.
     */
    public Casilla(int fila,int columna,String type){
        this.fila = fila;
        this.columna = columna;
        this.type = type;
        this.jugador = null;
        this.barrera = null;
    }

    /**
     * Obtiene el tipo de la casilla como un carácter.
     * El carácter es la primera letra del tipo en mayúscula.
     * @return el carácter que representa el tipo de la casilla.
     */
    public char getTipo(){
        char tipo = Character.toUpperCase(type.charAt(0));
        return tipo;
    }

    /**
     * Obtiene el tipo de la casilla como un carácter.
     * El carácter es la primera letra del tipo en mayúscula.
     * @return el carácter que representa el tipo de la casilla.
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * Establece el jugador que ocupa la casilla.
     * @param jugador el jugador que ocupará la casilla.
     */
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    /**
     * Libera la casilla de cualquier jugador, estableciendo el jugador a null.
     */
    public void setJugadorNull() {
        this.jugador = null;
    }

    /**
     * Obtiene la barrera que ocupa la casilla.
     * @return la barrera que ocupa la casilla, o null si no hay barrera.
     */
    public Barrera getBarrera() {
        return barrera;
    }

    /**
     * Establece la barrera que ocupa la casilla.
     * @param barrera la barrera que ocupará la casilla.
     */
    public void setBarrera(Barrera barrera) {
        this.barrera = barrera;
    }

    /**
     * Establece si hay un jugador en la casilla.
     */
    public boolean hayJugador(){
        if (jugador != null){
            return true;
        }else {
            return false;
        }
    }

    /**
     * Establece si hay una barrera en la casilla.
     */
    public boolean hayPared(){
        if (barrera != null){
            return true;
        }else {
            return false;
        }
    }

}
