package domain;

import java.awt.*;

public class Ficha {

    private char nombre;
    private Point coordenadas;

    public Ficha(char nombre, Point coordenadas){
        this.nombre = nombre;
        this.coordenadas = coordenadas;
    }

    public void setCoordenadas(Point coordenadas) {
        this.coordenadas = coordenadas;
    }

    public void mover(int x, int y) {
        coordenadas.setLocation(x, y);
    }

    public boolean estaEn(int x, int y) {
        return coordenadas.getX() == x && coordenadas.getY() == y;
    }

    public char getNombre() {
        return nombre;
    }

    public Point getCoordenadas() {
        return coordenadas;
    }
}
