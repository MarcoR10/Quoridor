package domain;

import java.awt.*;
import java.util.Random;

public class Tablero {

    private Casilla[][] casillas;
    private int filas,columnas;
    private boolean casillasE;

    public Tablero(int filas,int columnas,Jugador jugador1 , Jugador jugador2,boolean casillaE,boolean barreraE){
        this.filas = filas;
        this.columnas = columnas;
        this.casillasE = casillaE;
        casillas = new Casilla[this.filas][this.columnas];
        Inicio(this.filas,this.columnas,jugador1,jugador2,casillaE);
    }

    public void Inicio(int fila,int columna,Jugador jugador1 , Jugador jugador2,boolean casillasE){
        // Especificacion si existen casillas especiales //
        if(casillasE){
            Random random = new Random();
            for (int i = 0; i < fila; i++) {
                for (int j = 0; j < columna; j++) {
                    int tipoCasilla = random.nextInt(100);
                    if (tipoCasilla < 10) {  // 10% probabilidad de ser Teletransportador
                        casillas[i][j] = new Teletransportador(i, j, "teletransportador");
                    } else if (tipoCasilla < 20) {  // 10% probabilidad de ser Regresar
                        casillas[i][j] = new Regreso(i, j, "regresar");
                    } else if (tipoCasilla < 30) {  // 10% probabilidad de ser Turno Doble
                        casillas[i][j] = new Doble(i, j, "doble");
                    } else {
                        casillas[i][j] = new Normal(i, j, "normal");
                    }
                }
            }
        }else {
            for (int i = 0; i < fila; i++) {
                for (int j = 0; j < columna; j++) {
                    casillas[i][j] =  new Normal(i, j, "normal");
                }
            }
        }
        // Colocacion Jugadores //
        casillas[0][(columna/2)-1].setJugador(jugador1);
        jugador1.getFicha().setCoordenadas(new Point(0,(columna/2)-1));
        casillas[fila-1][(columna/2)-1].setJugador(jugador2);
        jugador2.getFicha().setCoordenadas(new Point(fila-1,(columna/2)-1));
    }

    public void moverFicha(Jugador jugadoractual,int fila,int columna) {
        Point coor = jugadoractual.getFicha().getCoordenadas();
        Casilla casillaActual = casillas[coor.x][coor.y];

        if (casillaActual instanceof Teletransportador) {
            if (((Teletransportador) casillaActual).permiteMovimiento(fila, columna, this)) {
                actualizarCasilla(coor.x, coor.y, fila, columna, jugadoractual);
                jugadoractual.getFicha().setCoordenadas(new Point(fila, columna));
            }
        }else if (casillaActual instanceof Regreso){
            if (movimientoValido(fila, columna, coor.x, coor.y)) {
                actualizarCasilla(coor.x, coor.y, fila, columna, jugadoractual);
                jugadoractual.getFicha().setCoordenadas(new Point(fila, columna));
                ((Regreso) casillaActual).realizarAccion(jugadoractual, this);
            }
        }else if (casillaActual instanceof Doble){
            if (movimientoValido(fila, columna, coor.x, coor.y)) {
                actualizarCasilla(coor.x, coor.y, fila, columna, jugadoractual);
                jugadoractual.jugar(fila, columna);
                System.out.println("¡Has obtenido un turno adicional!" + jugadoractual);
            }
        }else {
            if (movimientoValido(fila, columna, coor.x, coor.y)) {
                actualizarCasilla(coor.x, coor.y, fila, columna, jugadoractual);
                jugadoractual.jugar(fila, columna);
            }
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

    public Casilla getCasilla(int fila, int columna) {
        return casillas[fila][columna];
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

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Casilla casilla = casillas[i][j];
                if (casilla.getJugador() != null) {
                    System.out.print(casilla.getJugador().getFicha().getNombre() + "\t");
                } else if (casilla.getBarrera() != null) {
                    System.out.print("X\t");
                } else if (casilla instanceof Teletransportador) {
                    System.out.print("T\t");
                } else if (casilla instanceof Regreso) {
                    System.out.print("R\t");
                } else if (casilla instanceof Doble) {
                    System.out.print("D\t");
                } else {
                    System.out.print("N\t");
                }
            }
            System.out.println();
        }

    }
}
