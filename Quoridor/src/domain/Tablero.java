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
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                casillas[i][j] =  new Normal(i, j, "normal");
            }
        }
        casillas[0][(columna/2)-1].setJugador(jugador1);
        jugador1.getFicha().setCoordenadas(new Point(0,(columna/2)-1));
        casillas[fila-1][(columna/2)-1].setJugador(jugador2);
        jugador2.getFicha().setCoordenadas(new Point(fila-1,(columna/2)-1));
    }

    public void moverFicha(Jugador jugadoractual,int fila,int columna) {
        Point coor = jugadoractual.getFicha().getCoordenadas();
        if (movimientoValido(fila,columna, coor.x, coor.y)) {
            actualizarCasilla(coor.x,coor.y,fila,columna,jugadoractual);
            jugadoractual.jugar(fila, columna);
        }
    }

    public void colocarPared(int filaInicio, int columnaInicio, int filaFin, int columnaFin,Jugador jugador) {
        Barrera BarreI = new BarreraNormal(filaInicio, columnaInicio, jugador);
        Barrera BarreF = new BarreraNormal(filaInicio, columnaInicio, jugador);
        if (esColocacionValida(BarreI,BarreF)) {
            casillas[filaInicio][columnaInicio].setBarrera(BarreI);
            casillas[filaFin][columnaFin].setBarrera(BarreF);
        }
    }

    private boolean esColocacionValida(Barrera barreI, Barrera barreF) {
        if (barreI == null && barreF == null) {
            return true;
        }
        if (barreI == null || barreF == null) {
            return false;
        }
        if (!barreI.estanJuntas(barreF)){
            return false;
        }
        return !barreI.equals(barreF);
    }

    public boolean movimientoValido(int filaNueva, int columnaNueva, int filaActual, int columnaActual) {
        if (filaNueva < 0 || filaNueva >= filas || columnaNueva < 0 || columnaNueva >= columnas) {
            return false;
        }
        if (casillas[filaNueva][columnaNueva].getJugador() != null || casillas[filaNueva][columnaNueva].getBarrera() != null) {
            return false;
        }
        boolean movimientoHorizontal = filaNueva == filaActual && Math.abs(columnaNueva - columnaActual) == 1;
        boolean movimientoVertical = columnaNueva == columnaActual && Math.abs(filaNueva - filaActual) == 1;
        return movimientoHorizontal || movimientoVertical;
    }


    private boolean caminoDisponible() {
        // Implementar la lógica para verificar que al menos un camino está disponible para cada jugador
        // Esto generalmente implica realizar una búsqueda de caminos desde la posición actual de cada jugador
        // hasta su lado objetivo del tablero.
        // Aquí puedes usar la lógica de búsqueda de caminos que tengas en tu juego
        return true; // Devuelve true si hay al menos un camino disponible
    }


    public void actualizarCasilla(int filaAnterior, int columnaAnterior, int filaNueva, int columnaNueva, Jugador jugador) {
        Casilla casillaAnterior = casillas[filaAnterior][columnaAnterior];
        Casilla casillaNueva = casillas[filaNueva][columnaNueva];
        casillaAnterior.setJugadorNull();
        casillaNueva.setJugador(jugador);
    }

    public int getFilas() {
        return filas;
    }

    public void imprimirTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (casillas[i][j].getJugador() != null) {
                    System.out.print(casillas[i][j].getJugador().getFicha().getNombre() + "\t");
                } else if (casillas[i][j].getBarrera() != null) {
                    System.out.print("X\t");
                } else {
                    System.out.print("_\t");
                }
            }
            System.out.println();
        }
    }
}
