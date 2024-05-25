package presentation;

import domain.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class Juego extends JFrame{

    private Quoridor Game;
    private JPanel Juego,Player1,Player2;
    private CirculoEnBoton[][] buttons;
    private JMenu archivo, settings;
    private JMenuBar menuBar;
    private JMenuItem load, save, start, quit, terminar;
    private int Posx,Posy,Table;
    private Jugador jugador1,jugador2;

    public Juego(String nombreJ1, String nombreJ2, String tipoJ1, String tipoJ2, String modoJuego, boolean barreras, boolean casillas){
       prepareElements(nombreJ1 , nombreJ2 , tipoJ1 , tipoJ2 , modoJuego , barreras , casillas);
       prepareActions();
    }
    
    private void prepareElements(String nombreJ1, String nombreJ2, String tipoJ1, String tipoJ2, String modoJuego, boolean barreras, boolean casillas) {
        prepareElementsBoard(nombreJ1 , nombreJ2 , tipoJ1 , tipoJ2 , modoJuego , barreras , casillas);
        prepareElementsMenu();
    }
    
    private void prepareElementsBoard(String nombreJ1, String nombreJ2, String tipoJ1, String tipoJ2, String modoJuego, boolean barreras, boolean casillas) {
        setTitle("QuoriPoob");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //--------------------------------------------//
        Jugadores(nombreJ1,nombreJ2,tipoJ1,tipoJ2);
        Game = new Quoridor(10,10,jugador1,jugador2,casillas,barreras,modoJuego);
        //--------------------------------------------//
        Juego = new JPanel(new GridBagLayout());
        Juego.setLayout(new GridLayout(10, 10));
        Juego.setBorder(new CompoundBorder(new EmptyBorder(2, 2, 2, 2), new TitledBorder("Tablero")));
        Juego.setBorder(new LineBorder(new Color(42, 96, 177), 3));
        setLocationRelativeTo(null);
        //--------------------------------------------//
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        buttons = new CirculoEnBoton[10][10];
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                buttons[row][col] = new CirculoEnBoton(); ;
                buttons[row][col].setBackground(Color.WHITE);
                gbc.gridx = col;
                gbc.gridy = row;
                gbc.weightx = 1.0 / 10;
                gbc.weighty = 1.0 / 10;
                gbc.ipadx = 150 / 10;
                gbc.ipady = 350 / 10;
                Juego.add(buttons[row][col], gbc);
            }
        }

        Player1 = createPlayerPanel("Jugador 1", Color.RED, "NombreJ1");
        Player2 = createPlayerPanel("Jugador 2", Color.BLUE, "NombreJ2");

        getContentPane().add(Juego, BorderLayout.CENTER);
        add(Player1, BorderLayout.WEST);
        add(Player2, BorderLayout.EAST);
    }

    private JPanel createPlayerPanel(String title, Color color, String nombre) {
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setBorder(BorderFactory.createTitledBorder(title));

        JLabel nombreLabel = new JLabel("Nombre:");
        JLabel colorLabel = new JLabel("Color:");
        JLabel barrerasLabel = new JLabel("Barreras disponibles:");
        JLabel tiempoLabel = new JLabel("Tiempo invertido:");
        JLabel puntajeLabel = new JLabel("Puntaje:");

        JLabel nombreValue = new JLabel(nombre);
        nombreValue.setForeground(Color.BLACK);
        JPanel colorPanel = new JPanel();
        colorPanel.setBackground(color);

        JLabel BarrerasValue = new JLabel("10");
        JLabel tiempoValue = new JLabel("2 horas 15 minutos");
        JLabel puntajeValue = new JLabel("1500 puntos");

        playerPanel.add(nombreLabel);
        playerPanel.add(nombreValue);
        playerPanel.add(Box.createVerticalStrut(10));
        playerPanel.add(colorLabel);
        playerPanel.add(colorPanel);
        playerPanel.add(Box.createVerticalStrut(5));
        playerPanel.add(barrerasLabel);
        playerPanel.add(BarrerasValue);
        playerPanel.add(Box.createVerticalStrut(10));
        playerPanel.add(tiempoLabel);
        playerPanel.add(tiempoValue);
        playerPanel.add(Box.createVerticalStrut(10));
        playerPanel.add(puntajeLabel);
        playerPanel.add(puntajeValue);

        return playerPanel;
    }

    private void Jugadores(String nombreJ1, String nombreJ2, String tipoJ1, String tipoJ2) {
        jugador1 = crearJugador(nombreJ1, tipoJ1, "Rojo");
        jugador2 = crearJugador(nombreJ2, tipoJ2, "Azul");
    }

    private Jugador crearJugador(String nombre, String tipo, String color) {
        if (tipo.equals("Humano")) {
            return new Humano(nombre, color);
        } else {
            return new Maquina(nombre, color);
        }
    }

    private void prepareElementsMenu() {
        setBackground(Color.WHITE);
        menuBar = new JMenuBar();
        archivo = new JMenu("Archivo");
        menuBar.add(archivo);
        //-------------------------------------------------------------------------//
        start = new JMenuItem("Nuevo");
        terminar = new JMenuItem("Terminar");
        save = new JMenuItem("Salvar");
        load = new JMenuItem("Abrir");
        quit = new JMenuItem("Salir");
        //-------------------------------------------------------------------------//
        archivo.add(start);
        archivo.addSeparator();
        archivo.add(terminar);
        archivo.addSeparator();
        archivo.add(load);
        archivo.addSeparator();
        archivo.add(save);
        archivo.addSeparator();
        archivo.add(quit);
        //-------------------------------------------------------------------------//
        setJMenuBar(menuBar);
    }
    
    private void prepareActions() {
        prepareActionsBoard();
        prepareActionsMenu();
    }

    private void prepareActionsBoard() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                buttons[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton selectedButton = (JButton) e.getSource();
                        for (int row = 0; row < buttons.length; row++) {
                            for (int col = 0; col < buttons[row].length; col++) {
                                if (buttons[row][col] == selectedButton) {
                                    Posx = row;
                                    Posy = col;
                                    //Jugada(Posx, Posy);
                                    System.out.println("Botón seleccionado en la posición: (" + row + ", " + col + ")");
                                    return;
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    private void prepareActionsMenu() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        quit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //Salida();
            }
        });
        start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //Nuevo();
            }
        });
        terminar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //End();
            }
        });
        save.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //Salvar();
            }
        });
        load.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //Abrir();
            }
        });
    }
}

class CirculoEnBoton extends JButton {
    private Color colorCirculo;

    public CirculoEnBoton() {
        super();
        this.colorCirculo = null;
    }

    public void setColorCirculo(Color color) {
        this.colorCirculo = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int diametro = Math.min(getWidth(), getHeight()) - 6; // Diámetro del círculo
        g.setColor(colorCirculo);
        g.fillOval((getWidth() - diametro) / 2, (getHeight() - diametro) / 2, diametro, diametro);
    }
}