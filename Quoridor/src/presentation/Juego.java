package presentation;

import domain.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * La clase Juego extiende JFrame y representa la ventana principal del juego de Quoridor.
 * Contiene los elementos gráficos y la lógica necesaria para interactuar con el tablero y los jugadores.
 */
public class Juego extends JFrame{

    private Quoridor Game;
    private JPanel Juego,Player1,Player2,Acciones;
    private JButton movimiento,muro;
    private BotonFicha[][] buttons;
    private JMenu archivo;
    private JMenuBar menuBar;
    private JMenuItem load, save, start, quit, terminar;
    private Jugador jugador1,jugador2;
    private Color colorj1 ,colorj2,colorActual;
    private int Tablero = 3;
    private boolean mov,pare,barreras,casillas;
    private int[] casillaSeleccionada,casillaDestino;
    private String modoJuego;


    /**
     * Constructor de la clase Juego.
     * @param nombreJ1 Nombre del Jugador 1
     * @param nombreJ2 Nombre del Jugador 2
     * @param tipoJ1 Tipo del Jugador 1 (Humano/Máquina)
     * @param tipoJ2 Tipo del Jugador 2 (Humano/Máquina)
     * @param modoJuego Modo de juego seleccionado
     * @param barreras Indica si se usan barreras especiales
     * @param casillas Indica si se usan casillas especiales
     * @param colorj1 Color del Jugador 1
     * @param colorj2 Color del Jugador 2
     */
    public Juego(String nombreJ1, String nombreJ2, String tipoJ1, String tipoJ2, String modoJuego, boolean barreras, boolean casillas,Color colorj1 ,Color colorj2){
        this.colorj1 = colorj1;
        this.colorj2 = colorj2;
        this.colorActual = colorj1;
        this.barreras = barreras;
        this.casillas = casillas;
        this.modoJuego = modoJuego;
        prepareElements(nombreJ1 , nombreJ2 , tipoJ1 , tipoJ2 , modoJuego , barreras , casillas);
        prepareActions();
    }


    /**
     * Prepara los elementos gráficos del juego.
     */
    private void prepareElements(String nombreJ1, String nombreJ2, String tipoJ1, String tipoJ2, String modoJuego, boolean barreras, boolean casillas) {
        prepareElementsBoard(nombreJ1 , nombreJ2 , tipoJ1 , tipoJ2 , modoJuego , barreras , casillas);
        prepareElementsMenu();
    }

    /**
     * Configura los elementos del tablero de juego.
     */
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
        buttons = new BotonFicha[Tablero][Tablero];
        for (int row = 0; row < Tablero; row++) {
            for (int col = 0; col < Tablero; col++) {
                buttons[row][col] = new BotonFicha();
                if ((row + col) % 2 == 0) {
                    buttons[row][col].setBackground(new Color(117, 79, 43));
                } else {
                    buttons[row][col].setBackground(new Color(87, 47, 12));
                }
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


    /**
     * Crea un panel para mostrar la información del jugador.
     */
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

    /**
     * Configura los jugadores del juego.
     */
    private void Jugadores(String nombreJ1, String nombreJ2, String tipoJ1, String tipoJ2) {
        jugador1 = crearJugador(nombreJ1, tipoJ1, "Rojo");
        jugador2 = crearJugador(nombreJ2, tipoJ2, "Azul");
    }

    /**
     * Crea un jugador basado en el tipo (Humano o Máquina).
     */
    private Jugador crearJugador(String nombre, String tipo, String color) {
        if (tipo.equals("Humano")) {
            return new Humano(nombre, color);
        } else {
            return new Maquina(nombre, color);
        }
    }

    /**
     * Configura los elementos del menú.
     */
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

    /**
     * Configura las acciones para los elementos gráficos.
     */
    private void prepareActions() {
        prepareActionsBoard();
        prepareActionsMenu();
    }


    /**
     * Configura las acciones para el tablero de juego.
     */
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


    /**
     * Configura las acciones para el menú del juego.
     */
    private void prepareActionsMenu() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        quit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                optionExit();
            }
        });
        start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                optionNew();
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
                guardarJuego();
            }
        });
        load.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarJuego();
            }
        });
    }

    /**
     * Mueve el jugador a la casilla destino.
     */
    private void moverJugador(){
        JOptionPane.showMessageDialog(null, "Escoge en donde esta el jugador y a donde lo quieres mover en ese orden", "Información", JOptionPane.INFORMATION_MESSAGE);
        activarCasillas();
        mov = true;
    }

    /**
     * Coloca una pared en la casilla seleccionada.
     */
    private void ponerPared(){
        JOptionPane.showMessageDialog(null, "Escoge en donde colocar la pared inicial y la final", "Información", JOptionPane.INFORMATION_MESSAGE);
        activarCasillas();
        pare = true;
    }

    /**
     * Metodo para procesar la accion del jugador
     */
    private void procesarMovimiento(){
        if (casillaSeleccionada != null && casillaDestino != null) {
            // Aquí puedes procesar el movimiento usando las casillas seleccionadas (casillaSeleccionada y casillaDestino)
            int[] casilla1 = casillaSeleccionada;
            int[] casilla2 = casillaDestino;
            // Llama a un método para procesar el movimiento, pasando las coordenadas de las casillas seleccionadas
            if (mov == true) {
                if ((Game.getTablero()).movimientoValido(casilla2[0], casilla2[1], casilla1[0], casilla1[1])) {
                    (Game.getTablero()).moverFicha(Game.getJugadorActual(), casilla2[0], casilla2[1],0,0);
                    buttons[casilla1[0]][casilla1[1]].Peon(colorActual, false);
                    buttons[casilla2[0]][casilla2[1]].Peon(colorActual, true);
                }
            }
            if (pare == true) {
                if (!Game.getBarerrasE()) {
                    (Game.getTablero()).colocarPared(casilla2[0], casilla2[1], casilla1[0], casilla1[1], Game.getJugadorActual(), Game.getBarerrasE(),0,0,0);
                    buttons[casilla1[0]][casilla1[1]].Wall(colorActual, true);
                    buttons[casilla2[0]][casilla2[1]].Wall(colorActual, true);
                } else {
                    (Game.getTablero()).colocarPared(casilla2[0], casilla2[1], casilla1[0], casilla1[1], Game.getJugadorActual(), Game.getBarerrasE(),0,0,0);
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

    /**
     * Cambia el estado de las casillas a desactivado
     */
    private void desactivarCasillas(){
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                buttons[row][col].setEnabled(false);
            }
        }
    }


    /**
     * Cambia el estado de las casillas a activo
     */
    private void activarCasillas(){
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                buttons[row][col].setEnabled(true);
            }
        }
    }

    /**
     * Cambia el turno al siguiente jugador.
     */
    public void cambiarTurno() {
        colorActual = (colorActual == colorj1) ? colorj2 : colorj1;
        Game.cambiarTurno();
    }

    /**
     * Muestra un mensaje del ganador del juego
     */
    private void mostrarGanador(Jugador jugador) {
        String mensaje = "¡" + jugador.getNombre() + " ha ganado!";
        JOptionPane.showMessageDialog(null, mensaje, "Juego Terminado", JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * Termina el juego.
     */
    private void terminarJuego() {
        desactivarCasillas();
        movimiento.setEnabled(false);
        muro.setEnabled(false);
        String mensaje = "El juego ha terminado.";
        JOptionPane.showMessageDialog(null, mensaje, "Juego Terminado", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Guarda el estado actual del juego.
     */
    public void guardarJuego() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            Game.guardarArchivo(selectedFile, Game);
        }
    }

    /**
     * Carga un juego previamente guardado.
     */
    public void cargarJuego() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            Quoridor cargado = Game.abrirArchivo(selectedFile);
            if (cargado != null) {
                Game = cargado;
                actualizarInterfaz();
                JOptionPane.showMessageDialog(null, "Juego cargado correctamente.");
            }
        }
    }

    /**
     * opcion para salir del juego
     */
    private void optionExit() {
        int valor = JOptionPane.showConfirmDialog(this, "Desea cerrar la aplicacion?", "Advertencia",
                JOptionPane.YES_NO_OPTION);
        if (valor == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * opcion para iniciar un nuevo juego
     */
    private void optionNew() {
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea abrir un nuevo jardín?", "Abriendo jardín :)", JOptionPane.YES_NO_OPTION);
        if(confirmacion == JOptionPane.YES_NO_OPTION){
            // Resetear jugadores
            jugador1.reset();
            jugador2.reset();
            // Crear un nuevo juego de Quoridor
            Game = new Quoridor(Tablero, Tablero, jugador1, jugador2, casillas, barreras, modoJuego);
            // Restablecer el tablero gráfico
            for (int row = 0; row < Tablero; row++) {
                for (int col = 0; col < Tablero; col++) {
                    buttons[row][col].Peon(null, false);
                    if ((row + col) % 2 == 0) {
                        buttons[row][col].setBackground(new Color(117, 79, 43));
                    } else {
                        buttons[row][col].setBackground(new Color(87, 47, 12));
                    }
                    buttons[row][col].setEnabled(false);
                    buttons[row][col].Wall(colorj1, false);
                    buttons[row][col].Wall(colorj2, false);
                }
            }
            // Posicionar los peones iniciales
            buttons[0][(Tablero / 2)].Peon(colorj1, true);
            buttons[Tablero - 1][(Tablero / 2)].Peon(colorj2, true);
            // Resetear la interfaz de los jugadores
            actualizarPanelJugador(Player1, jugador1, colorj1);
            actualizarPanelJugador(Player2, jugador2, colorj2);
            // Resetear el estado de las acciones
            mov = false;
            pare = false;
            // Resetear el color actual
            colorActual = colorj1;
            // Repintar la interfaz
            repaint();
        }
    }

    /**
     * Actualiza el panel de un jugador con la información inicial.
     */
    private void actualizarPanelJugador(JPanel playerPanel, Jugador jugador, Color color) {
        Component[] components = playerPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getText().startsWith("Barreras disponibles:")) {
                    label.setText("Barreras disponibles: " + jugador.getBarreras());
                }
            }
        }
    }

    /**
     * Actualiza el tablero
     */
    public void actualizarInterfaz() {
        for (int row = 0; row < Tablero; row++) {
            for (int col = 0; col < Tablero; col++) {
                if ((row + col) % 2 == 0) {
                    buttons[row][col].setBackground(new Color(117, 79, 43));
                } else {
                    buttons[row][col].setBackground(new Color(87, 47, 12));
                }
                buttons[row][col].setEnabled(false);
            }
        }
        for (int row = 0; row < Tablero; row++) {
            for (int col = 0; col < Tablero; col++) {
                if (Game.getTablero().getCasilla(row, col).hayJugador()) {
                    Jugador jugador = Game.getTablero().getCasilla(row, col).getJugador();
                    if (jugador != null) {
                        Ficha ficha = jugador.getFicha();
                        if (ficha != null) {
                            if (ficha.getNombre() == 'R') {
                                buttons[row][col].Peon(colorj1, true);
                            } else {
                                buttons[row][col].Peon(colorj2, true);
                            }
                        }
                    } else if (Game.getTablero().getCasilla(row, col).hayPared()) {

                    }
                }
            }
        }
    }

}

/**
 * La clase BotonFicha extiende JButton y representa una casilla del tablero de Quoridor.
 * Permite almacenar información adicional sobre el estado de la casilla y personalizar su apariencia.
 */
class BotonFicha extends JButton {

    private Color peonColor;
    private Color wallColor;
    private boolean drawPeon;
    private boolean drawWall;



    /**
     * Constructor de la clase BotonFicha.
     */
    public BotonFicha() {
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

    /**
     * Configura el peón en la casilla.
     * @param color Color del peón
     * @param draw Indica si hay un peón en la casilla
     */
    public void Peon(Color color, boolean draw) {
        peonColor = color;
        drawPeon = draw;
        repaint();
    }

    /**
     * Configura el muro en la casilla.
     * @param color Color del muro
     * @param draw Indica si hay un muro en la casilla
     */
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
        //Color outlineColor = Color.BLACK; // Color del contorno (en este caso, negro)
        // Dibuja el contorno del muro
        //g2.setColor(outlineColor);
        //g2.setStroke(new BasicStroke(wallWidth));
        //g2.drawRect(centerX - armLength - wallWidth / 2, centerY - armLength - wallWidth / 2, 2 * armLength + wallWidth, 2 * armLength + wallWidth);

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
