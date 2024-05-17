package domain;

import java.util.Scanner;

public class Quoridor {

    private Jugador jugador1,jugador2,jugadorActual;
    private Tablero tablero;
    private Walls Paredes;
    private Scanner scanner = new Scanner(System.in);

    public Quoridor(int filas,int columnas,Jugador jugador1,Jugador jugador2){
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.jugadorActual = jugador1;
        tablero = new Tablero(filas,columnas,jugador1,jugador2);
    }

    public void jugar() {
        while(!Ganador()){
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
                        tablero.colocarPared(fila2,columna2,fila3,columna3);

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

    public boolean Ganador(){
        return false;
    }

    public void cambiarTurno() {
        jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;
    }

    public static void main(String[] args) {
        Humano jugador1 = new Humano("carlos","rojo");
        Humano jugador2 = new Humano("jennifer","azul");
        Quoridor game = new Quoridor(20, 20,jugador1,jugador2);
        game.jugar();
    }

}
