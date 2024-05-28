package domain;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;


/**
 * La clase Tablero representa el tablero de juego.
 */
public class Tablero implements Serializable {

    private Casilla[][] casillas;
    private int filas,columnas;
    private boolean casillasE;
    //private Scanner scanner;

    /**
     * Constructor para inicializar el tablero.
     *
     * @param filas     Número de filas del tablero.
     * @param columnas  Número de columnas del tablero.
     * @param jugador1  Primer jugador.
     * @param jugador2  Segundo jugador.
     * @param casillaE  Indica si se utilizarán casillas especiales.
     * @param barreraE  Indica si se utilizarán barreras especiales.
     */

    public Tablero(int filas,int columnas,Jugador jugador1 , Jugador jugador2,boolean casillaE,boolean barreraE){
        this.filas = filas;
        this.columnas = columnas;
        this.casillasE = casillaE;
        casillas = new Casilla[this.filas][this.columnas];
        //this.scanner = new Scanner(System.in);
        Inicio(this.filas,this.columnas,jugador1,jugador2,casillaE);
    }

    /**
     * Método para inicializar el tablero y colocar los jugadores.
     *
     * @param fila      Número de filas del tablero.
     * @param columna   Número de columnas del tablero.
     * @param jugador1  Primer jugador.
     * @param jugador2  Segundo jugador.
     * @param casillasE Indica si se utilizarán casillas especiales.
     */

    public void Inicio(int fila,int columna,Jugador jugador1 , Jugador jugador2,boolean casillasE){
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
        casillas[0][(columna/2)].setJugador(jugador1);
        jugador1.getFicha().setCoordenadas(new Point(0,columna/2));
        casillas[fila-1][(columna/2)].setJugador(jugador2);
        jugador2.getFicha().setCoordenadas(new Point(fila-1,columna/2));
    }

    /**
     * Método para mover la ficha de un jugador.
     *
     * @param jugadoractual El jugador actual.
     * @param fila          Fila a la que se desea mover.
     * @param columna       Columna a la que se desea mover.
     */

    public void moverFicha(Jugador jugadoractual,int fila,int columna,int filaT,int columnaT) {
        Point coor = jugadoractual.getFicha().getCoordenadas();
        Casilla casillaProxima = casillas[fila][columna];

        if (casillaProxima.getBarrera() instanceof Aliadas) {
            Aliadas barreraAliada = (Aliadas) casillaProxima.getBarrera();
            if (!barreraAliada.bloqueaCamino(fila, columna, jugadoractual)) {
                actualizarCasilla(coor.x, coor.y, fila, columna, jugadoractual);
                jugadoractual.getFicha().mover(fila, columna);
                return;
            }
        }
        if (casillaProxima instanceof Teletransportador) {
            if (movimientoValido(fila, columna, coor.x, coor.y)){
                actualizarCasilla(coor.x, coor.y, fila, columna, jugadoractual);
                jugadoractual.getFicha().mover(fila,columna);
                //-------------------------------------------------------------------------------//
                if (((Teletransportador) casillaProxima).permiteMovimiento(filaT,columnaT,this)){
                    Point coor2 = jugadoractual.getFicha().getCoordenadas();
                    actualizarCasilla(coor2.x,coor2.y,filaT,columnaT,jugadoractual);
                    ((Teletransportador) casillaProxima).teleport(jugadoractual);
                }else {
                    System.out.println(" Teletrasnportador Fallido ");
                }
                //-------------------------------------------------------------------------------//
            }
        }else if (casillaProxima instanceof Regreso){
            if (movimientoValido(fila, columna, coor.x, coor.y)) {
                actualizarCasilla(coor.x, coor.y, fila, columna, jugadoractual);
                jugadoractual.getFicha().mover(fila,columna);
                ((Regreso)casillaProxima).retrocederFicha(jugadoractual,this);
            }
        }else if (casillaProxima instanceof Doble){
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

    /**
     * Método para colocar una pared en el tablero.
     *
     * @param filaInicio    Fila de inicio de la pared.
     * @param columnaInicio Columna de inicio de la pared.
     * @param filaFin       Fila de fin de la pared.
     * @param columnaFin    Columna de fin de la pared.
     * @param jugador       Jugador que coloca la pared.
     * @param barrerasE     Indica si se utilizan barreras especiales.
     */

    public void colocarPared(int filaInicio, int columnaInicio, int filaFin, int columnaFin,Jugador jugador,boolean barrerasE,int opcionBarrera,int filaE, int columnaE) {
        if (barrerasE){
            int tipo = opcionBarrera;
            Barrera barrera1 = null;
            Barrera barrera2 = null;
            switch (tipo) {
                case 1:
                    barrera1 = new BarreraNormal(filaInicio, columnaInicio, jugador);
                    barrera2 = new BarreraNormal(filaInicio, columnaInicio, jugador);
                    //-------------------------------------------------------------------------------//
                    casillas[filaInicio][columnaInicio].setBarrera(barrera1);
                    casillas[filaFin][columnaFin].setBarrera(barrera2);
                    break;
                case 2:
                    barrera1 = new Temporal(filaInicio, columnaInicio, jugador);
                    barrera2 = new Temporal(filaInicio, columnaInicio, jugador);
                    //-------------------------------------------------------------------------------//
                    casillas[filaInicio][columnaInicio].setBarrera(barrera1);
                    casillas[filaFin][columnaFin].setBarrera(barrera2);
                    break;
                case 3:
                    barrera1 = new Larga(filaInicio, columnaInicio, jugador);
                    barrera2 = new Larga(filaInicio, columnaInicio, jugador);
                    Barrera barrera3 = new Larga(filaE, columnaE, jugador);;
                    //-------------------------------------------------------------------------------//
                    casillas[filaInicio][columnaInicio].setBarrera(barrera1);
                    casillas[filaFin][columnaFin].setBarrera(barrera2);
                    casillas[filaE][columnaE].setBarrera(barrera3);
                    break;
                case 4:
                    barrera1 = new Aliadas(filaInicio, columnaInicio, jugador);
                    barrera2 = new BarreraNormal(filaInicio, columnaInicio, jugador);
                    //-------------------------------------------------------------------------------//
                    casillas[filaInicio][columnaInicio].setBarrera(barrera1);
                    casillas[filaFin][columnaFin].setBarrera(barrera2);
                    break;
                default:
                    System.out.println("Tipo de barrera no válido.");
            }
        }else {
            Barrera BarreI = new BarreraNormal(filaInicio, columnaInicio, jugador);
            Barrera BarreF = new BarreraNormal(filaInicio, columnaInicio, jugador);
            if (esColocacionValida(BarreI,BarreF)) {
                casillas[filaInicio][columnaInicio].setBarrera(BarreI);
                casillas[filaFin][columnaFin].setBarrera(BarreF);
            }
        }
    }

    /**
     * Verifica si la colocación de las barreras es válida.
     *
     * @param barreI Barrera de inicio.
     * @param barreF Barrera de fin.
     * @return true si la colocación es válida, false en caso contrario.
     */

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


    /**
     * Verifica si un movimiento es válido.
     *
     * @param filaNueva    Fila a la que se desea mover.
     * @param columnaNueva Columna a la que se desea mover.
     * @param filaActual   Fila actual.
     * @param columnaActual Columna actual.
     * @return true si el movimiento es válido, false en caso contrario.
     */
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

    /**
     * Método que implementa la lógica para verificar si hay al menos un camino disponible para cada jugador.
     * Este método debe ser implementado con la lógica específica de búsqueda de caminos del juego.
     *
     * @return true si hay al menos un camino disponible, false en caso contrario.
     */
    private boolean caminoDisponible() {
        // Implementar la lógica para verificar que al menos un camino está disponible para cada jugador
        // Esto generalmente implica realizar una búsqueda de caminos desde la posición actual de cada jugador
        // hasta su lado objetivo del tablero.
        // Aquí puedes usar la lógica de búsqueda de caminos que tengas en tu juego
        return true; // Devuelve true si hay al menos un camino disponible
    }

    /**
     * Actualiza las casillas al mover un jugador.
     *
     * @param filaAnterior  Fila anterior.
     * @param columnaAnterior Columna anterior.
     * @param filaNueva     Nueva fila.
     * @param columnaNueva  Nueva columna.
     * @param jugador       Jugador que se mueve.
     */
    public void actualizarCasilla(int filaAnterior, int columnaAnterior, int filaNueva, int columnaNueva, Jugador jugador) {
        Casilla casillaAnterior = casillas[filaAnterior][columnaAnterior];
        Casilla casillaNueva = casillas[filaNueva][columnaNueva];
        casillaAnterior.setJugadorNull();
        casillaNueva.setJugador(jugador);
    }

    /**
     * Reduce los turnos de las barreras temporales y las elimina si ya no están activas.
     */
    public void reducirTurnosBarrerasTemporales() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Barrera barrera = casillas[i][j].getBarrera();
                if (barrera instanceof Temporal) {
                    ((Temporal) barrera).reducirTurno();
                    if (!((Temporal) barrera).esActiva()) {
                        casillas[i][j].setBarrera(null);
                    }
                }
            }
        }
    }


    /**
     * Obtiene una casilla específica del tablero.
     *
     * @param fila    Fila de la casilla.
     * @param columna Columna de la casilla.
     * @return La casilla en la posición especificada.
     */

    public Casilla getCasilla(int fila, int columna) {
        return casillas[fila][columna];
    }

    /**
     * Obtiene el número de filas del tablero.
     *
     * @return El número de filas.
     */
    public int getFilas() {
        return filas;
    }

    /**
     * Imprime el tablero mostrando la ubicación de los jugadores y las barreras.
     */
    public void imprimirTablero() {
        System.out.println("------------------------------------");
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

    /**
     * Imprime el tablero mostrando el tipo de cada casilla.
     */
    public void imprimirTipoTablero(){
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
