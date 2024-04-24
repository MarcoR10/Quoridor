package domain;

public class Tablero {

    private Casilla[][] casillas;

    public Tablero(int filas,int columnas){
        casillas = new Casilla[filas][columnas];
        Inicio(filas,columnas);
    }

    public void Inicio(int fila,int columna){
        for(int i = 0;i < fila;i++){
            for(int j = 0;j<columna;j++){
                Casilla casilla = new Casilla(i,j);
                casillas[i][j] = casilla;
            }
        }
    }

}
