package presentation;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class Panel_Inicial extends JFrame{

    private JPanel Inicio;
    private JLabel Front;
    private JButton Jugar;
    private Dimension Pantalla;
    private ImageIcon Fondo;

    public Panel_Inicial(){
        prepareElements();
        prepareActions();
    }

    public void prepareElements() {
        setTitle("QuoriPoob");
        Pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(Pantalla.width/2, Pantalla.height/2);
        setLocationRelativeTo(null);
        PanelInicial();
    }

    private void PanelInicial() {
        //--------------------------------------------//
        Inicio = new JPanel();
        Inicio.setLayout(null);
        //--------------------------------------------//
        Fondo = new ImageIcon(getClass().getResource("/Imagenes/Fondo1.jpg"));
        Image Fon = Fondo.getImage().getScaledInstance(Pantalla.width/2,Pantalla.height/2 ,Image.SCALE_DEFAULT);
        ImageIcon Fone = new ImageIcon(Fon);
        Front = new JLabel(Fone);
        Front.setBounds(0, 0, Fone.getIconWidth(), Fone.getIconHeight());
        //--------------------------------------------//
        Jugar = new JButton("Play");
        Jugar.setBackground(Color.WHITE);
        Jugar.setBounds((Pantalla.width / 5)+20,(Pantalla.height / 5)+100,100,40);
        //--------------------------------------------//
        Inicio.add(Jugar);
        Inicio.add(Front);
        add(Inicio);
    }

    public void prepareActions() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                Salida();
            }
        });
        Jugar.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(Reglas::new);
        });
    }

    private void Salida() {
        if (JOptionPane.showConfirmDialog(rootPane, "¿Seguro que quiere salir?", "Salir del sistema", JOptionPane.YES_NO_OPTION
        ) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String args[]){
        Panel_Inicial ventana = new Panel_Inicial();
        ventana.setVisible(true);
    }
}