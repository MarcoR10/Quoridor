package domain;

import java.awt.*;

public class Tablero {

    private Casilla[][] casillas;
    private int filas,columnas;

    public Tablero(int filas,int columnas,Jugador jugador1 , Jugador jugador2){
        this.filas = filas;
        this.columnas = columnas;
        casillas = new Casilla[this.filas][this.columnas];
        Inicio(this.filas,this.columnas,jugador1,jugador2);
    }

    public void Inicio(int fila,int columna,Jugador jugador1 , Jugador jugador2){
        // Creación del tablero
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                casillas[i][j] =  new Normal(i, j, "normal");
            }
        }
        casillas[0][(columna/2)-1].setLugar(jugador1.getNombre());
        jugador1.getFicha().setCoordenadas(new Point(0,(columna/2)-1));
        casillas[fila-1][(columna/2)-1].setLugar(jugador2.getNombre());
        jugador2.getFicha().setCoordenadas(new Point(fila-1,(columna/2)-1));
    }

    public void moverFicha(Jugador jugadoractual,int fila,int columna) {
        Point coor = jugadoractual.getFicha().getCoordenadas();
        if (movimientoValido(fila,columna, coor.x, coor.y)) {
            actualizarCasilla(coor.x,coor.y,fila,columna,jugadoractual);
            jugadoractual.jugar(fila, columna);
        }
    }

    public void colocarPared(int fila,int columna,int fila2,int columna2){

    }

    public boolean movimientoValido(int filaNueva, int columnaNueva, int filaActual, int columnaActual) {
        // Verificar si las coordenadas están dentro del rango del tablero
        if (filaNueva < 0 || filaNueva >= filas || columnaNueva < 0 || columnaNueva >= columnas) {
            return false;
        }

        // Verificar si la casilla destino está ocupada por otra ficha
        if (!casillas[filaNueva][columnaNueva].getLugar().equals("_")) {
            return false;
        }

        // Verificar si el movimiento es horizontal o vertical y si es de dos casillas
        boolean movimientoHorizontal = filaNueva == filaActual && Math.abs(columnaNueva - columnaActual) == 2;
        boolean movimientoVertical = columnaNueva == columnaActual && Math.abs(filaNueva - filaActual) == 2;

        // Verificar si se cumple alguna de las condiciones de movimiento válido
        if (!(movimientoHorizontal || movimientoVertical)) {
            return false;
        }

        // Verificar si hay barreras en el camino (pendiente de implementación)
        return true;
    }



    public boolean movimientoValidoPared(){
        return true;
    }

    public void actualizarCasilla(int filaAnterior, int columnaAnterior, int filaNueva, int columnaNueva, Jugador jugador) {
        // Obtenemos la casilla anterior y la nueva
        Casilla casillaAnterior = casillas[filaAnterior][columnaAnterior];
        Casilla casillaNueva = casillas[filaNueva][columnaNueva];
        // Vaciamos la casilla anterior
        casillaAnterior.setLugar("_");
        // Colocamos la ficha del jugador en la casilla nueva
        casillaNueva.setLugar(jugador.getNombre());
    }



    public void imprimirTablero() {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                System.out.print(casillas[i][j].getLugar() + "\t");
            }
            System.out.println();
        }
    }
}
