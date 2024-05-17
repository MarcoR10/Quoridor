package domain;

public abstract class  Casilla {

    protected int fila,columna;
    protected String type,lugar;

    public Casilla(int fila,int columna,String type){
        this.fila = fila;
        this.columna = columna;
        this.type = type;
        lugar = "_";
    }

    public char getTipo(){
        char tipo = Character.toUpperCase(type.charAt(0));
        return tipo;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getLugar() {
        return lugar;
    }

}
