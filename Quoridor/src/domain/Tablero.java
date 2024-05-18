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
        // Asignacion Jugadores
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

    public void colocarPared(int filaInicio, int columnaInicio, int filaFin, int columnaFin) {
        // Verificar si la pared es horizontal o vertical
        boolean esHorizontal = filaInicio == filaFin && Math.abs(columnaInicio - columnaFin) == 1;
        boolean esVertical = columnaInicio == columnaFin && Math.abs(filaInicio - filaFin) == 1;

        // Verificar si las coordenadas están dentro del rango del tablero
        if (filaInicio < 0 || filaInicio >= filas || columnaInicio < 0 || columnaInicio >= columnas ||
                filaFin < 0 || filaFin >= filas || columnaFin < 0 || columnaFin >= columnas) {
            System.out.println("Coordenadas fuera del rango del tablero.");
            return;
        }

        // Verificar si la orientación es válida
        if (!(esHorizontal || esVertical)) {
            System.out.println("Las coordenadas no forman una pared válida.");
            return;
        }

        // Verificar si las casillas para la pared están disponibles
        if (esHorizontal) {
            if (!casillas[filaInicio][columnaInicio].getLugar().equals("_") ||
                    !casillas[filaFin][columnaFin].getLugar().equals("_")) {
                System.out.println("Una o ambas casillas ya están ocupadas.");
                return;
            }
        } else if (esVertical) {
            if (!casillas[filaInicio][columnaInicio].getLugar().equals("_") ||
                    !casillas[filaFin][columnaFin].getLugar().equals("_")) {
                System.out.println("Una o ambas casillas ya están ocupadas.");
                return;
            }
        }

        // Colocar la pared en las casillas correspondientes
        if (esHorizontal) {
            casillas[filaInicio][columnaInicio].setLugar("X");
            casillas[filaFin][columnaFin].setLugar("X");
        } else if (esVertical) {
            casillas[filaInicio][columnaInicio].setLugar("|");
            casillas[filaFin][columnaFin].setLugar("|");
        }

        // Verificar que no se bloquee completamente el camino para cualquier jugador
        if (!caminoDisponible()) {
            // Revertir la colocación de la pared si se bloquea el camino
            casillas[filaInicio][columnaInicio].setLugar("_");
            casillas[filaFin][columnaFin].setLugar("_");
            System.out.println("La pared bloquea completamente el camino. Acción revertida.");
            return;
        }
    }

    private boolean caminoDisponible() {
        // Implementar la lógica para verificar que al menos un camino está disponible para cada jugador
        // Esto generalmente implica realizar una búsqueda de caminos desde la posición actual de cada jugador
        // hasta su lado objetivo del tablero.
        return true; // Placeholder, necesita implementación completa.
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

        // Verificar si hay barreras en el camino
        if (movimientoHorizontal) {
            int columnaIntermedia = (columnaActual + columnaNueva) / 2;
            if (!casillas[filaActual][columnaIntermedia].getLugar().equals("_")) {
                return false;
            }
        } else if (movimientoVertical) {
            int filaIntermedia = (filaActual + filaNueva) / 2;
            if (!casillas[filaIntermedia][columnaActual].getLugar().equals("_")) {
                return false;
            }
        }

        // Si todas las verificaciones pasan, el movimiento es válido
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

    public int getFilas() {
        return filas;
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
