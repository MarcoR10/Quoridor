package presentation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Configuracion extends JFrame {

    private String NombreJ1, NombreJ2,tipoJ1,tipoJ2,modoJuego,casillas,barreras;
    private boolean casillasI,barrerasI;
    private JTextField nombreJugador1, nombreJugador2;
    private JLabel Nombre1, Nombre2, Color1, Color2, CasillasE, barrerasE, TipoJugador1, TipoJugador2, Modo, TamañoT;
    private JComboBox<String> comboBox1, comboBox2, comboBox3, comboBox4, comboBox5, comboBox6;
    private JButton colorJugador1, colorJugador2, confirm;
    private String[] opciones, opciones2, opciones3, opciones4, opciones5;
    private Color ColorP1, ColorP2;
    private Dimension Pantalla;

    public Configuracion() {
        prepareElements();
        prepareActions();
    }

    private void prepareElements() {
        //-------------------------//
        setTitle("Configuracion");
        Pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Quoridor1.jpg")).getImage());
        setSize(Pantalla.width, Pantalla.height);
        setLocationRelativeTo(null);
        setLayout(null);
        //-------------------------//
        // Crear imagen de fondo y BackgroundPanel
        ImageIcon fondoIcon = new ImageIcon(getClass().getResource("/Imagenes/Fondo2.png"));
        Image fondoImage = fondoIcon.getImage().getScaledInstance(Pantalla.width, Pantalla.height, Image.SCALE_DEFAULT);
        BackgroundPanel PanelConfiguracion = new BackgroundPanel(fondoImage);
        PanelConfiguracion.setLayout(null);
        PanelConfiguracion.setBounds(0, 0, Pantalla.width, Pantalla.height);
        //-------------------------//
        // Añadir componentes al BackgroundPanel
        Nombre1 = new JLabel("Introduce tu nombre Jugador 1 :");
        Nombre1.setFont(new Font("Bodoni MT Cursiva", Font.BOLD, 15));
        Nombre1.setForeground(Color.WHITE);
        Nombre1.setBounds(20, 50, 250, 100);
        PanelConfiguracion.add(Nombre1);
        //-------------------------//
        nombreJugador1 = new JTextField();
        nombreJugador1.setBounds(290, 90, 100, 20);
        PanelConfiguracion.add(nombreJugador1);
        //-------------------------//
        Nombre2 = new JLabel("Introduce tu nombre Jugador 2 :");
        Nombre2.setFont(new Font("Bodoni MT Cursiva", Font.BOLD, 15));
        Nombre2.setForeground(Color.WHITE);
        Nombre2.setBounds(700, 50, 250, 100);
        PanelConfiguracion.add(Nombre2);
        //-------------------------//
        nombreJugador2 = new JTextField();
        nombreJugador2.setBounds(950, 90, 100, 20);
        PanelConfiguracion.add(nombreJugador2);
        //-------------------------//
        Color1 = new JLabel("Introduce tu Color Jugador 1 :");
        Color1.setFont(new Font("Bodoni MT Cursiva", Font.BOLD, 15));
        Color1.setForeground(Color.WHITE);
        Color1.setBounds(20, 95, 250, 100);
        PanelConfiguracion.add(Color1);
        //-------------------------//
        colorJugador1 = new JButton("Seleccion");
        colorJugador1.setBounds(290, 135, 100, 20);
        PanelConfiguracion.add(colorJugador1);
        //-------------------------//
        Color2 = new JLabel("Introduce tu Color Jugador 2 :");
        Color2.setFont(new Font("Bodoni MT Cursiva", Font.BOLD, 15));
        Color2.setForeground(Color.WHITE);
        Color2.setBounds(700, 95, 250, 100);
        PanelConfiguracion.add(Color2);
        //-------------------------//
        colorJugador2 = new JButton("Seleccion");
        colorJugador2.setBounds(950, 135, 100, 20);
        PanelConfiguracion.add(colorJugador2);
        //-------------------------//
        TipoJugador1 = new JLabel("Introduce el tipo de Jugador 1 :");
        TipoJugador1.setFont(new Font("Bodoni MT Cursiva", Font.BOLD, 15));
        TipoJugador1.setForeground(Color.WHITE);
        TipoJugador1.setBounds(20, 150, 250, 100);
        PanelConfiguracion.add(TipoJugador1);
        //-------------------------//
        opciones = new String[]{"Humano", "Maquina"};
        comboBox1 = new JComboBox<>(opciones);
        comboBox1.setBounds(290, 185, 150, 30);
        PanelConfiguracion.add(comboBox1);
        //-------------------------//
        TipoJugador2 = new JLabel("Introduce el tipo de Jugador 2 :");
        TipoJugador2.setFont(new Font("Bodoni MT Cursiva", Font.BOLD, 15));
        TipoJugador2.setForeground(Color.WHITE);
        TipoJugador2.setBounds(700, 150, 250, 100);
        PanelConfiguracion.add(TipoJugador2);
        //-------------------------//
        opciones2 = new String[]{"Humano", "Maquina"};
        comboBox2 = new JComboBox<>(opciones2);
        comboBox2.setBounds(950, 185, 150, 30);
        PanelConfiguracion.add(comboBox2);
        //-------------------------//
        Modo = new JLabel("Modo de Juego:");
        Modo.setFont(new Font("Bodoni MT Cursiva", Font.BOLD, 15));
        Modo.setForeground(Color.WHITE);
        Modo.setBounds(500, 265, 250, 100);
        PanelConfiguracion.add(Modo);
        //-------------------------//
        opciones3 = new String[]{"Normal", "Contrarreloj", "Cronometrado"};
        comboBox3 = new JComboBox<>(opciones3);
        comboBox3.setBounds(650, 300, 150, 30);
        PanelConfiguracion.add(comboBox3);
        //-------------------------//
        CasillasE = new JLabel("Casillas Especiales:");
        CasillasE.setFont(new Font("Bodoni MT Cursiva", Font.BOLD, 15));
        CasillasE.setForeground(Color.WHITE);
        CasillasE.setBounds(500, 315, 250, 100);
        PanelConfiguracion.add(CasillasE);
        //-------------------------//
        opciones4 = new String[]{"false", "true"};
        comboBox4 = new JComboBox<>(opciones4);
        comboBox4.setBounds(650, 350, 150, 30);
        PanelConfiguracion.add(comboBox4);
        //-------------------------//
        barrerasE = new JLabel("Barreras Especiales :");
        barrerasE.setFont(new Font("Bodoni MT Cursiva", Font.BOLD, 15));
        barrerasE.setForeground(Color.WHITE);
        barrerasE.setBounds(500, 365, 250, 100);
        PanelConfiguracion.add(barrerasE);
        //-------------------------//
        opciones5 = new String[]{"false", "true"};
        comboBox5 = new JComboBox<>(opciones5);
        comboBox5.setBounds(650, 400, 150, 30);
        PanelConfiguracion.add(comboBox5);
        //-------------------------//
        confirm = new JButton("Confirmar");
        confirm.setFont(new Font("Bodoni MT Cursiva", Font.BOLD, 20));
        confirm.setBounds(600, 650, 150, 50);
        PanelConfiguracion.add(confirm);
        //-------------------------//
        // Añadir BackgroundPanel al JFrame
        add(PanelConfiguracion);
    }

    private void prepareActions() {
        colorJugador1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color colorSeleccionado = JColorChooser.showDialog(null, "Selecciona un color", Color.WHITE);
                ColorP1 = colorSeleccionado;
                if (colorSeleccionado != null) {
                    colorJugador1.setBackground(colorSeleccionado);
                    String rgb = String.format("#%02x%02x%02x", colorSeleccionado.getRed(), colorSeleccionado.getGreen(), colorSeleccionado.getBlue());
                    colorJugador1.setText(rgb);
                }
            }
        });

        colorJugador2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color colorSeleccionado = JColorChooser.showDialog(null, "Selecciona un color", Color.WHITE);
                ColorP2 = colorSeleccionado;
                if (colorSeleccionado != null) {
                    colorJugador2.setBackground(colorSeleccionado);
                    String rgb = String.format("#%02x%02x%02x", colorSeleccionado.getRed(), colorSeleccionado.getGreen(), colorSeleccionado.getBlue());
                    colorJugador2.setText(rgb);
                }
            }
        });

        comboBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOption = comboBox1.getSelectedItem().toString();
                System.out.println("Seleccionado: " + selectedOption);
            }
        });

        comboBox2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOption = comboBox2.getSelectedItem().toString();
                System.out.println("Seleccionado: " + selectedOption);
            }
        });

        confirm.addActionListener(e -> {
            NombreJ1 = nombreJugador1.getText();
            NombreJ2 = nombreJugador2.getText();
            tipoJ1  = (String) comboBox1.getSelectedItem();
            tipoJ2  = (String) comboBox1.getSelectedItem();
            modoJuego = comboBox3.getSelectedItem().toString();
            barreras = (String) comboBox4.getSelectedItem();
            barrerasI = Boolean.parseBoolean(barreras);
            casillas = (String) comboBox5.getSelectedItem();
            casillasI = Boolean.parseBoolean(casillas);
            abrirJuego(NombreJ1,NombreJ2,tipoJ1,tipoJ2,modoJuego,barrerasI,casillasI,ColorP1,ColorP2);
        });
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                Salida();
            }
        });
    }

    private void abrirJuego(String nombreJ1, String nombreJ2, String tipoJ1, String tipoJ2, String modoJuego, boolean barreras, boolean casillas,Color color1 ,Color color2) {
        Juego juego = new Juego(nombreJ1,nombreJ2,tipoJ1,tipoJ2,modoJuego,barreras,casillas,color1,color2);
        juego.setVisible(true);
        this.dispose();
    }

    private void Salida() {
        if (JOptionPane.showConfirmDialog(rootPane, "¿Seguro que quiere salir?", "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

}

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}


