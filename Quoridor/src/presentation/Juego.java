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
    private JPanel Juego,Player1,Player2,Acciones;
    private JButton movimiento,muro;
    private ficha[][] buttons;
    private JMenu archivo;
    private JMenuBar menuBar;
    private JMenuItem load, save, start, quit, terminar;
    private Jugador jugador1,jugador2;
    private Color colorj1 ,colorj2,colorActual;
    private int Tablero = 3;
    private boolean mov,pare;
    private int[] casillaSeleccionada,casillaDestino;

    public Juego(String nombreJ1, String nombreJ2, String tipoJ1, String tipoJ2, String modoJuego, boolean barreras, boolean casillas,Color colorj1 ,Color colorj2){
        this.colorj1 = colorj1;
        this.colorj2 = colorj2;
        this.colorActual = colorj1;
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
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Quoridor1.jpg")).getImage());
        //--------------------------------------------//
        Jugadores(nombreJ1,nombreJ2,tipoJ1,tipoJ2);
        Game = new Quoridor(Tablero,Tablero,jugador1,jugador2,casillas,barreras,modoJuego);
        //--------------------------------------------//
        Juego = new JPanel(new GridBagLayout());
        Juego.setLayout(new GridLayout(Tablero, Tablero));
        Juego.setBorder(new CompoundBorder(new EmptyBorder(2, 2, 2, 2), new TitledBorder("Tablero")));
        Juego.setBorder(new LineBorder(new Color(42, 96, 177), 3));
        setLocationRelativeTo(null);
        //--------------------------------------------//
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        buttons = new ficha[Tablero][Tablero];
        for (int row = 0; row < Tablero; row++) {
            for (int col = 0; col < Tablero; col++) {
                buttons[row][col] = new ficha();
                buttons[row][col].setBackground(new Color(117, 79, 43));
                buttons[row][col].setEnabled(false);
                gbc.gridx = col;
                gbc.gridy = row;
                gbc.weightx = 1.0 / 10;
                gbc.weighty = 1.0 / 10;
                gbc.ipadx = 150 / 10;
                gbc.ipady = 350 / 10;
                Juego.add(buttons[row][col], gbc);
            }
        }
        // Colocacion Jugadores //
        buttons[0][(Tablero/2)].Peon(colorj1,true);
        buttons[Tablero-1][(Tablero/2)].Peon(colorj2,true);
        repaint();
        //----------------------//
        Player1 = createPlayerPanel("Jugador 1", colorj1, nombreJ1,jugador1.getBarreras());
        Player2 = createPlayerPanel("Jugador 2", colorj2, nombreJ2,jugador2.getBarreras());
        getContentPane().add(Juego, BorderLayout.CENTER);
        add(Player1, BorderLayout.WEST);
        add(Player2, BorderLayout.EAST);
        //----------------------//
        Acciones = new JPanel(new GridLayout(1, 2, 10, 10));
        Acciones.setBorder(new EmptyBorder(5, 5, 5, 5));
        movimiento = new JButton("Mover jugador");
        muro = new JButton("Poner pared");
        Acciones.add(movimiento, BorderLayout.WEST);
        Acciones.add(muro, BorderLayout.EAST);
        getContentPane().add(Acciones, BorderLayout.SOUTH);
    }

    private JPanel createPlayerPanel(String title, Color color, String nombre ,int barrerasRestantes) {
        //----------------------//
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setBorder(BorderFactory.createTitledBorder(title));
        //----------------------//
        JLabel nombreLabel = new JLabel("Nombre:");
        JLabel colorLabel = new JLabel("Color:");
        JLabel barrerasLabel = new JLabel("Barreras disponibles:");
        JLabel tiempoLabel = new JLabel("Tiempo restante:");
        JLabel puntajeLabel = new JLabel("Tiempo de jugada restante:");
        //----------------------//
        JLabel nombreValue = new JLabel(nombre);
        nombreValue.setForeground(Color.BLACK);
        JPanel colorPanel = new JPanel();
        colorPanel.setBackground(color);
        //----------------------//
        JLabel BarrerasValue = new JLabel(String.valueOf(barrerasRestantes));
        JLabel tiempoValue = new JLabel("2 horas");
        JLabel SegmentoValue = new JLabel("30 segundos");
        //----------------------//
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
        playerPanel.add(SegmentoValue);
        //----------------------//
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
        for (int row = 0; row < Tablero; row++) {
            for (int col = 0; col < Tablero; col++) {
                buttons[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton selectedButton = (JButton) e.getSource();
                        for (int row = 0; row < buttons.length; row++) {
                            for (int col = 0; col < buttons[row].length; col++) {
                                if (buttons[row][col] == selectedButton) {
                                    if (casillaSeleccionada == null) {
                                        casillaSeleccionada = new int[]{row, col};
                                        buttons[row][col].setEnabled(false); // Deshabilita la casilla seleccionada
                                    } else {
                                        // Si ya hay una casilla seleccionada, asigna la casilla actual como la segunda seleccionada
                                        casillaDestino = new int[]{row, col};
                                        // Procesa el movimiento usando las casillas seleccionadas (casillaSeleccionada y casillaDestino)
                                        procesarMovimiento();
                                        // Reinicia las casillas seleccionadas
                                        casillaSeleccionada = null;
                                        casillaDestino = null;
                                    }
                                    return;
                                }
                            }
                        }
                    }
                });
            }
        }
        movimiento.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                moverJugador();
            }
        });
        muro.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ponerPared();
            }
        });
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
                terminarJuego();
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

    private void moverJugador(){
        JOptionPane.showMessageDialog(null, "Escoge en donde esta el jugador y a donde lo quieres mover en ese orden", "Información", JOptionPane.INFORMATION_MESSAGE);
        activarCasillas();
        mov = true;
    }

    private void ponerPared(){
        JOptionPane.showMessageDialog(null, "Escoge en donde colocar la pared inicial y la final", "Información", JOptionPane.INFORMATION_MESSAGE);
        activarCasillas();
        pare = true;
    }

    private void procesarMovimiento(){
        if (casillaSeleccionada != null && casillaDestino != null) {
            // Aquí puedes procesar el movimiento usando las casillas seleccionadas (casillaSeleccionada y casillaDestino)
            int[] casilla1 = casillaSeleccionada;
            int[] casilla2 = casillaDestino;
            // Llama a un método para procesar el movimiento, pasando las coordenadas de las casillas seleccionadas
            if (mov == true) {
                if ((Game.getTablero()).movimientoValido(casilla2[0], casilla2[1], casilla1[0], casilla1[1])) {
                    (Game.getTablero()).moverFicha(Game.getJugadorActual(), casilla2[0], casilla2[1]);
                    buttons[casilla1[0]][casilla1[1]].Peon(colorActual, false);
                    buttons[casilla2[0]][casilla2[1]].Peon(colorActual, true);
                }
            }
            if (pare == true) {
                if (!Game.getBarerrasE()) {
                    (Game.getTablero()).colocarPared(casilla2[0], casilla2[1], casilla1[0], casilla1[1], Game.getJugadorActual(), Game.getBarerrasE());
                    buttons[casilla1[0]][casilla1[1]].Wall(colorActual, true);
                    buttons[casilla2[0]][casilla2[1]].Wall(colorActual, true);
                } else {
                    (Game.getTablero()).colocarPared(casilla2[0], casilla2[1], casilla1[0], casilla1[1], Game.getJugadorActual(), Game.getBarerrasE());
                    buttons[casilla1[0]][casilla1[1]].Wall(colorActual, true);
                    buttons[casilla2[0]][casilla2[1]].Wall(colorActual, true);
                }
            }
            if (Game.Ganador()) {
                terminarJuego();
                mostrarGanador(Game.getJugadorActual());
            }
            //---------------------------------------------//
            desactivarCasillas();
            cambiarTurno();
            (Game.getTablero()).imprimirTablero();
            // Luego, reinicia las casillas seleccionadas
            mov = false;
            pare = false;
        } else {
            JOptionPane.showMessageDialog(null, "Primero debes seleccionar las dos casillas.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void desactivarCasillas(){
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                buttons[row][col].setEnabled(false);
            }
        }
    }

    private void activarCasillas(){
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                buttons[row][col].setEnabled(true);
            }
        }
    }

    public void cambiarTurno() {
        colorActual = (colorActual == colorj1) ? colorj2 : colorj1;
        Game.cambiarTurno();
    }

    private void mostrarGanador(Jugador jugador) {
        String mensaje = "¡" + jugador.getNombre() + " ha ganado!";
        JOptionPane.showMessageDialog(null, mensaje, "Juego Terminado", JOptionPane.INFORMATION_MESSAGE);
    }

    private void terminarJuego() {
        desactivarCasillas();
        movimiento.setEnabled(false);
        muro.setEnabled(false);
        String mensaje = "El juego ha terminado.";
        JOptionPane.showMessageDialog(null, mensaje, "Juego Terminado", JOptionPane.INFORMATION_MESSAGE);
    }

}


class ficha extends JButton {

    private Color peonColor;
    private Color wallColor;
    private boolean drawPeon;
    private boolean drawWall;

    public ficha() {
        this.peonColor = null;
        this.wallColor = null;
        this.drawPeon = false;
        this.drawWall = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (drawPeon && peonColor != null) {
            drawPeon(g2);
        }
        if (drawWall && wallColor != null) {
            drawWall(g2);
        }

        g2.dispose();
    }

    public void Peon(Color color, boolean draw) {
        peonColor = color;
        drawPeon = draw;
        repaint();
    }

    public void Wall(Color color, boolean draw) {
        wallColor = color;
        drawWall = draw;
        repaint();
    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);
        if (drawWall && wallColor != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            drawWall(g2);
            g2.dispose();
        }
    }

    private void drawPeon(Graphics2D g2) {
        int centerX = getWidth() / 2; // Obtén el centro horizontal de la ventana
        int centerY = getHeight() / 2; // Obtén el centro vertical de la ventana

        // Configura los atributos de dibujo, como el color y el grosor de la línea
        g2.setStroke(new BasicStroke(1.0f));

        // Dibuja el contorno del cuerpo del peón (un círculo)
        int radio = 20; // Radio del círculo
        g2.setColor(Color.BLACK); // Color del contorno
        g2.drawOval(centerX - radio, centerY - radio, 2 * radio, 2 * radio);

        // Rellena el cuerpo del peón (un círculo)
        g2.setColor(peonColor); // Color del cuerpo
        g2.fillOval(centerX - radio, centerY - radio, 2 * radio, 2 * radio);

        // Dibuja la base del peón (un rectángulo)
        int baseWidth = 30; // Ancho de la base
        int baseHeight = 20; // Altura de la base
        g2.setColor(peonColor); // Color de la base
        g2.fillRect(centerX - baseWidth / 2, centerY + radio, baseWidth, baseHeight);

        // Dibuja el contorno de la base del peón (un rectángulo)
        g2.setColor(Color.BLACK); // Color del contorno de la base
        g2.drawRect(centerX - baseWidth / 2, centerY + radio, baseWidth, baseHeight);
    }

    private void drawWall(Graphics2D g2) {
        int centerX = getWidth() / 2; // Obtén el centro horizontal de la ventana
        int centerY = getHeight() / 2; // Obtén el centro vertical de la ventana
        int armLength = 100; // Longitud de los brazos de la cruz
        int wallWidth = 20; // Ancho del muro
        Color outlineColor = Color.BLACK; // Color del contorno (en este caso, negro)
        // Dibuja el contorno del muro
        g2.setColor(outlineColor);
        g2.setStroke(new BasicStroke(wallWidth));
        g2.drawRect(centerX - armLength - wallWidth / 2, centerY - armLength - wallWidth / 2, 2 * armLength + wallWidth, 2 * armLength + wallWidth);

        // Dibuja el brazo horizontal de la cruz
        g2.setStroke(new BasicStroke(10.0f)); // Restablece el grosor del trazo
        g2.setColor(wallColor);
        g2.drawLine(centerX - armLength, centerY, centerX + armLength, centerY);

        // Dibuja el brazo vertical de la cruz
        g2.drawLine(centerX, centerY - armLength, centerX, centerY + armLength);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }
}
