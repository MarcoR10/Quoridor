package domain;

import java.io.Serializable;


/**
 * La clase Maquina representa un jugador controlado por la máquina en un juego.
 * Extiende de la clase Jugador e implementa la interfaz Serializable.
 *
 * @see Jugador
 * @see Serializable
 */
public class Maquina extends Jugador implements Serializable {

    /**
     * Constructor para crear una instancia de un jugador controlado por la máquina.
     *
     * @param nombre el nombre del jugador controlado por la máquina.
     * @param color el color asociado al jugador controlado por la máquina.
     */
    public Maquina(String nombre ,String color){
        super(nombre,color);
    }

    /**
     * Método que define la acción de jugar para un jugador controlado por la máquina.
     *
     * @param x la coordenada x del movimiento.
     * @param y la coordenada y del movimiento.
     */
    public void jugar(int x,int y){

    }
}
