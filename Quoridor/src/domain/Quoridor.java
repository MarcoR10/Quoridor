package domain;

import java.awt.*;
import java.io.Serializable;
import java.util.Scanner;

public class Quoridor implements Serializable {

    private Jugador jugador1,jugador2,jugadorActual;
    private boolean casillas,barreras;
    private String modoJuego;
    private Tablero tablero;
    private Scanner scanner = new Scanner(System.in);

    public Quoridor(int filas,int columnas,Jugador jugador1,Jugador jugador2,boolean casillas,boolean barreras,String modoJuego){
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.jugadorActual = jugador1;
        this.casillas = casillas;
        this.barreras = barreras;
        this.modoJuego = modoJuego;
        tablero = new Tablero(filas,columnas,jugador1,jugador2,casillas,barreras);
    }

    public void jugar() {
        while(!Ganador()){
            System.out.println("Es el turno de : " + jugadorActual.nombre);
            System.out.println("¿Qué desea hacer?\n1. Mover una ficha\n2. Colocar un objeto\nIngrese el número correspondiente a su elección:");
            int accion = scanner.nextInt();
            tablero.imprimirTablero();

            switch (accion){
                case 1:

                    System.out.println("Ingrese las coordenadas (x y):");
                    scanner.nextLine();
                    String entrada = scanner.nextLine();
                    String[] partes = entrada.split(" ");
                    int fila = Integer.parseInt(partes[0]);
                    int columna = Integer.parseInt(partes[1]);
                    tablero.moverFicha(jugadorActual,fila,columna);

                    // Implementacion Clase Doble //
                    Casilla casillaNueva = tablero.getCasilla(fila, columna);
                    if (casillaNueva instanceof Doble && ((Doble) casillaNueva).otorgaTurnoAdicional()) {
                        System.out.println("¡Has obtenido un turno adicional!");
                        tablero.imprimirTablero();
                        continue; // No cambiar de turno
                    }
                    //--------------------------------//
                    break;

                case 2:
                    if(jugadorActual.getBarreras() != 0){

                        System.out.println("Ingrse las coordenadas de inicio (x y):");
                        scanner.nextLine();
                        String entrada2 = scanner.nextLine();
                        String[] partes2 = entrada2.split(" ");

                        int fila2 = Integer.parseInt(partes2[0]);
                        int columna2 = Integer.parseInt(partes2[1]);

                        System.out.println("Ingrese las coordenadas final (x y):");
                        String entrada3 = scanner.nextLine();
                        String[] partes3 = entrada3.split(" ");
                        int fila3 = Integer.parseInt(partes3[0]);
                        int columna3 = Integer.parseInt(partes3[1]);
                        tablero.colocarPared(fila2,columna2,fila3,columna3,jugadorActual);

                        // Resta la cantidad de veces que se utiliza las barreras //
                        jugadorActual.restaBarrreras();

                    }
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione 1 o 2.");
            }

            cambiarTurno();
            tablero.imprimirTablero();

        }
    }

    public boolean Ganador() {
        // Verifica si los jugadores estan en pocision de ganar //
        if (jugador1.getFicha().estaEn(tablero.getFilas() - 1, jugador1.getFicha().getCoordenadas().y)) {
            jugadorActual = jugador1;
            return true;

        }
        if (jugador2.getFicha().estaEn(0, jugador2.getFicha().getCoordenadas().y)) {
            jugadorActual = jugador2;
            return true;
        }
        return false;
    }

    public void cambiarTurno() {
        jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;
    }

    public static void main(String[] args) {
        Humano jugador1 = new Humano("Zen","Rojo");
        Humano jugador2 = new Humano("Bigotes","Azul");
        Quoridor game = new Quoridor(10, 10,jugador1,jugador2,true,false,"Normal");
        game.jugar();
    }

}
