package presentation;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Botones extends JFrame{
    private JLabel etiqueta1;
    private JButton boton1;
    private ImageIcon imagen = new ImageIcon("quoridor.png");
    //private ImageIcon fondo = new ImageIcon("Fondo.jpeg");
    
    public Botones(){
        super("Objeto JButton");
        Container contenedor = getContentPane();
        contenedor.setLayout(new BorderLayout());
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 2;
        int height = screenSize.height / 2;
        setSize(width,height);

        boton1 = new JButton ("¡Jugar!");
        boton1.setPreferredSize(new Dimension(width/4, height/4));

        boton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent inicio){
                Inicio frame2 = new Inicio();
                frame2.setVisible(true);
            }
        });

        etiqueta1 = new JLabel(imagen);

        JPanel panelImagen = new JPanel();
        panelImagen.add(etiqueta1);

        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBoton.add(boton1);

        //etiqueta2 = new JLabel(fondo);

        //JPanel panelFondo = new JPanel();
        //panelFondo.setLayout(new FlowLayout(FlowLayout.CENTER));;
        //panelFondo.setPreferredSize(new Dimension(width*2, height*2)); 
        //panelFondo.add(etiqueta2);

        contenedor.add(etiqueta1, BorderLayout.CENTER);
        contenedor.add(panelBoton, BorderLayout.SOUTH);
        //contenedor.add(etiqueta2, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }
    public static void main(String args[]){
        Botones ventana = new Botones();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        
    }
}