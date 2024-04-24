package domain;

public class Quoridor {

    private Ficha jugador1,jugador2;
    private Tablero tablero;
    private Walls Paredes;

    public Quoridor(int filas,int columnas){
        tablero = new Tablero(filas,columnas);
    }


}
