package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Juego extends JFrame{
    private JButton[][] botones;
    private int filas;
    private int columnas;

    public Juego(int filas, int columnas){
        this.filas = filas;
        this.columnas = columnas;

        setTitle("QUORIDOR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelBotones = new JPanel(new GridLayout(filas, columnas));

        botones = new JButton[filas][columnas];

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton boton = (JButton) e.getSource(); // Obtener el botón que disparó el evento
                // Aquí puedes agregar la lógica para manejar el clic en el botón
                System.out.println("Clic en el botón: " + boton.getText());
            }
        };

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                botones[i][j] = new JButton( "" +(i * columnas + j + 1)); // Nombre del botón
                botones[i][j].addActionListener(listener);
                panelBotones.add(botones[i][j]);
            }
        }

        getContentPane().add(panelBotones);

        pack();
        setLocationRelativeTo(null); 
        setVisible(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 2;
        int height = screenSize.height / 2;
        setSize(width,height);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
}
