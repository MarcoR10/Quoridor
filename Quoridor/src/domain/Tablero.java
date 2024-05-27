package domain;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Tablero {

    private Casilla[][] casillas;
    private int filas,columnas;
    private boolean casillasE;
    private Scanner scanner;

    public Tablero(int filas,int columnas,Jugador jugador1 , Jugador jugador2,boolean casillaE,boolean barreraE){
        this.filas = filas;
        this.columnas = columnas;
        this.casillasE = casillaE;
        casillas = new Casilla[this.filas][this.columnas];
        this.scanner = new Scanner(System.in);
        Inicio(this.filas,this.columnas,jugador1,jugador2,casillaE);
    }

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

    public void moverFicha(Jugador jugadoractual,int fila,int columna) {
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
                System.out.println("Ingrese las coordenadas alas que desea teleportarse (x y):");
                String MovT = scanner.nextLine();
                String[] telpot = MovT.split(" ");
                //-------------------------------------------------------------------------------//
                int filaT = Integer.parseInt(telpot[0]);
                int columnaT = Integer.parseInt(telpot[1]);
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

    public void colocarPared(int filaInicio, int columnaInicio, int filaFin, int columnaFin,Jugador jugador,boolean barrerasE) {
        if (barrerasE){
            System.out.println("Escoge el tipo de barrera que quieres : \n 1. Normal 2. Temporal 3.Larga 4.Aliadas ");
            int tipo = Integer.parseInt(scanner.nextLine());
            Barrera barrera1 = null;
            Barrera barrera2 = null;
            switch (tipo) {
                case 1:
                    barrera1 = new BarreraNormal(filaInicio, columnaInicio, jugador);
                    barrera2 = new BarreraNormal(filaInicio, columnaInicio, jugador);
                    casillas[filaInicio][columnaInicio].setBarrera(barrera1);
                    casillas[filaFin][columnaFin].setBarrera(barrera2);
                    break;
                case 2:
                    barrera1 = new Temporal(filaInicio, columnaInicio, jugador);
                    barrera2 = new Temporal(filaInicio, columnaInicio, jugador);
                    casillas[filaInicio][columnaInicio].setBarrera(barrera1);
                    casillas[filaFin][columnaFin].setBarrera(barrera2);
                    break;
                case 3:
                    barrera1 = new Larga(filaInicio, columnaInicio, jugador);
                    barrera2 = new Larga(filaInicio, columnaInicio, jugador);
                    //-------------------------------------------------------------------------------//
                    System.out.println("Ingrese las coordenadas extra (x y):");
                    String BarreL = scanner.nextLine();
                    String[] barreE = BarreL.split(" ");
                    //-------------------------------------------------------------------------------//
                    int filaE = Integer.parseInt(barreE[0]);
                    int columnaE = Integer.parseInt(barreE[1]);
                    Barrera barrera3 = new Larga(filaE, columnaE, jugador);;
                    //-------------------------------------------------------------------------------//
                    casillas[filaInicio][columnaInicio].setBarrera(barrera1);
                    casillas[filaFin][columnaFin].setBarrera(barrera2);
                    casillas[filaE][columnaE].setBarrera(barrera3);
                    break;
                case 4:
                    barrera1 = new Aliadas(filaInicio, columnaInicio, jugador);
                    barrera2 = new BarreraNormal(filaInicio, columnaInicio, jugador);
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
    }

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
