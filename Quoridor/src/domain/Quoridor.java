package domain;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;


/**
 * La clase Quoridor representa el juego Quoridor.
 * Implementa Serializable para permitir guardar y cargar el estado del juego.
 */

public class Quoridor implements Serializable {

    private Jugador jugador1,jugador2,jugadorActual;
    private boolean casillas,barreras;
    private String modoJuego;
    private Tablero tablero;
    //private Scanner scanner = new Scanner(System.in);

    /**
     * Constructor para la clase Quoridor.
     *
     * @param filas       Número de filas del tablero.
     * @param columnas    Número de columnas del tablero.
     * @param jugador1    Primer jugador.
     * @param jugador2    Segundo jugador.
     * @param casillas    Indica si hay casillas especiales.
     * @param barreras    Indica si hay barreras especiales.
     * @param modoJuego   Modo de juego.
     */

    public Quoridor(int filas,int columnas,Jugador jugador1,Jugador jugador2,boolean casillas,boolean barreras,String modoJuego){
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.jugadorActual = jugador1;
        this.casillas = casillas;
        this.barreras = barreras;
        this.modoJuego = modoJuego;
        tablero = new Tablero(filas,columnas,jugador1,jugador2,casillas,barreras);
    }

//    /**
//     * * Método principal para jugar una partida de Quoridor.
//     * Solicita al jugador actual realizar una acción y actualiza el estado del juego.
//     */
//    public void jugar() {
//        tablero.imprimirTipoTablero();
//        while(!Ganador()){
//            System.out.println("Es el turno de : " + jugadorActual.nombre);
//            System.out.println("¿Qué desea hacer?\n1. Mover una ficha\n2. Colocar un objeto\nIngrese el número correspondiente a su elección:");
//            tablero.imprimirTablero();
//            int accion = scanner.nextInt();
//            switch (accion){
//                case 1:
//                    System.out.println("Ingrese las coordenadas (x y):");
//                    scanner.nextLine();
//                    String entrada = scanner.nextLine();
//                    String[] partes = entrada.split(" ");
//                    int fila = Integer.parseInt(partes[0]);
//                    int columna = Integer.parseInt(partes[1]);
//                    tablero.moverFicha(jugadorActual,fila,columna);
//                    //-------------------//
//                    Casilla casillaNueva = tablero.getCasilla(fila, columna);
//                    if (casillaNueva instanceof Doble && ((Doble) casillaNueva).otorgaTurnoAdicional()) {
//                        tablero.imprimirTablero();
//                        continue;
//                    }
//                    //-------------------//
//                    break;
//                case 2:
//                    if(jugadorActual.getBarreras() != 0){
//                        System.out.println("Ingrese las coordenadas de inicio (x y):");
//                        scanner.nextLine();
//                        String entrada2 = scanner.nextLine();
//                        String[] partes2 = entrada2.split(" ");
//                        int fila2 = Integer.parseInt(partes2[0]);
//                        int columna2 = Integer.parseInt(partes2[1]);
//                        System.out.println("Ingrese las coordenadas final (x y):");
//                        String entrada3 = scanner.nextLine();
//                        String[] partes3 = entrada3.split(" ");
//                        int fila3 = Integer.parseInt(partes3[0]);
//                        int columna3 = Integer.parseInt(partes3[1]);
//                        tablero.colocarPared(fila2,columna2,fila3,columna3,jugadorActual,barreras);
//                        jugadorActual.restaBarrreras();
//                    }
//                    break;
//                default:
//                    System.out.println("Opción no válida. Por favor, seleccione 1 o 2.");
//            }
//            cambiarTurno();
//            tablero.reducirTurnosBarrerasTemporales();
//        }
//    }

    /**
     * Verifica si algún jugador ha ganado el juego.
     *
     * @return true si hay un ganador, false en caso contrario.
     */

    public boolean Ganador() {
        if (jugador1.getFicha().estaEn(tablero.getFilas() - 1, jugador1.getFicha().getCoordenadas().y)) {
            jugadorActual = jugador1;
            System.out.println("!!Felicidades el jugador " + jugadorActual.nombre + " ha ganado!!");
            return true;
        }
        if (jugador2.getFicha().estaEn(0, jugador2.getFicha().getCoordenadas().y)) {
            jugadorActual = jugador2;
            System.out.println("!!Felicidades el jugador " + jugadorActual.nombre + " ha ganado!!");
            return true;
        }
        return false;
    }

    /**
     * Cambia el turno del jugador actual al otro jugador.
     */

    public void cambiarTurno() {
        jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;
    }

    /**
     * Devuelve el jugador actual.
     *
     * @return Jugador actual.
     */

    public Jugador getJugadorActual(){
        return jugadorActual;
    }

    /**
     * Devuelve el tablero del juego.
     *
     * @return Tablero del juego.
     */

    public Tablero getTablero() {
        return tablero;
    }

    /**
     * Devuelve si las barreras están habilitadas.
     *
     * @return true si las barreras están habilitadas, false en caso contrario.
     */

    public boolean getBarerrasE(){
        return barreras;
    }

    /**
     * Guarda el estado actual del juego en un archivo.
     *
     * @param archivo  Archivo donde se guardará el estado del juego.
     * @param quoridor Estado del juego a guardar.
     */

    public void guardarArchivo(File archivo, Quoridor quoridor){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(quoridor);
            JOptionPane.showMessageDialog(null, "Juego guardado correctamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el juego: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Carga el estado del juego desde un archivo.
     *
     * @param archivo Archivo desde donde se cargará el estado del juego.
     * @return Estado del juego cargado.
     */

    public Quoridor abrirArchivo(File archivo){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (Quoridor) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el juego: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return null;
        }
    }

//    public static void main(String[] args) {
//        Humano jugador1 = new Humano("Zen","Rojo");
//        Humano jugador2 = new Humano("Bigotes","Azul");
//        Quoridor game = new Quoridor(9, 9,jugador1,jugador2,false,false,"Normal");
//        game.jugar();
//    }

}
