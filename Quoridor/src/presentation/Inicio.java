package presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Inicio extends JFrame{
    private JButton boton3, boton4, boton5;

    public Inicio(){
        super("Objeto inicio");
        Container contenedor = getContentPane();
        contenedor.setLayout(new FlowLayout());

        boton3 = new JButton("Jugar con PC");
        boton4 = new JButton("Dos jugadores");
        boton5 = new JButton("Reglas del juego");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 2;
        int height = screenSize.height / 2;
        setSize(width,height);

        boton3.setPreferredSize(new Dimension(width/3, height/3));
        boton4.setPreferredSize(new Dimension(width/3, height/3));
        boton5.setPreferredSize(new Dimension(width/3, height/3));

        boton5.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent reglas){
                Reglas frame3 = new Reglas();
                frame3.setVisible(true);
            }
        });

        boton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent juego) {
                // Crear una nueva instancia de Juego y hacerla visible
        
                String filasStr = JOptionPane.showInputDialog(null, "Ingrese el número de filas:");
                String columnasStr = JOptionPane.showInputDialog(null, "Ingrese el número de columnas:");
                
                // Verificar si las cadenas son nulas o están vacías
                if (filasStr != null && !filasStr.isEmpty() && columnasStr != null && !columnasStr.isEmpty()) {
                    // Convertir las cadenas a enteros
                    int filas = Integer.parseInt(filasStr);
                    int columnas = Integer.parseInt(columnasStr);
        
                    Juego frame4 = new Juego(filas, columnas); // O cualquier otro tamaño que desees
                    frame4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo la ventana de Juego
                    frame4.setVisible(true);
                } else {
                    // Manejar el caso en que las cadenas son nulas o están vacías
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese números válidos para filas y columnas.");
                }
            }
        });
        

        contenedor.add(boton3);
        contenedor.add(boton4);
        contenedor.add(boton5);

        setVisible(true);

        setLocationRelativeTo(null);

    }

}
