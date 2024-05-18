package presentation;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;


public class Reglas extends JFrame implements ChangeListener, ActionListener{
    private JLabel label1, label2;
    private JCheckBox check1;
    private JButton botoncito1;
    private JScrollPane scrollPane1;
    private JTextArea area1;

    public Reglas(){
        setTitle("Reglas");
        setIconImage(new ImageIcon(getClass().getResource("Quoridor1.jpg")).getImage()); //Agregar imagen NO OLVIDAR

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 2;
        int height = screenSize.height / 2;
        setSize(width,height);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        label1 = new JLabel("REGLAS");
        label1.setFont(new Font("Andale Mono", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0); // Añadimos un espacio en la parte superior
        panelPrincipal.add(label1, gbc);

        area1 = new JTextArea();
        area1.setEditable(false);
        area1.setFont(new Font("Andale Mono", Font.PLAIN, 9));
        area1.setText("\n\n REGLAS" + 
        " \n\nREGLAS PARA 2 JUGADORES" +
        " \n\n Antes de empezar, colocar las barreras en cada zona del tablero previstas para" + 
        " \n\n almacenarlas. (10 para cada jugador)." + 
        " \n\n Cada jugador coloca su peón en el centro de su línea de salida (fig. 1)." + 
        " \n\n Sortear para saber quien empieza." + 
        " \n\n Desarrollo de una partida" + 
        " \n\n Cuando sea su turno, el jugador puede elegir desplazar su peón o colocar una barrera." + 
        " \n\n Si ya ha desplazado todas sus barreras, el jugador solamente podrá desplazar su peón." + 
        " \n\n Movimiento de los peones:" + 
        " \n\n Los peones pueden desplazarse de una casilla y solo de una en una, en sentido" + 
        " \n\n horizontal o vertical, avanzando o retrocediendo, (fig. 2). Hay que evitar las barreras" + 
        " \n\n Disposición de las barreras:" + 
        " \n\n Las barreras se disponen exactamente entre 2 bloques de 2 casillas. " + 
        " \n\n Las barreras se utilizarán para que el adversario pueda avanzar menos, no obstante esta" + 
        " \n\n prohibido impedirle completamente el acceso hacia su línea de llegada: Siempre hay" + 
        " \n\n que dejar un acceso libre " + 
        " \n\n Frente a frente: " + 
        " \n\n Cuando los 2 peones se encuentran cara a cara sobre 2 casillas vecinas sin que una " + 
        " \n\n barrera los separe, el jugador que le toque jugar puede saltar y colocarse delante del" + 
        " \n\n peón del jugador adversario." + 
        " \n\n FIN DE LA PARTIDA" + 
        " \n\n El primer jugador que llegue sobre la novena casilla, enfrente de su línea de salida," + 
        " \n\n gana la partida." + 
        " \n\n DURACIÓN DE UNA PARTIDA" + 
        " \n\n De 10 a 20 minutos." + 
        " \n\n Si se organizara un torneo, se puede asignar a cada jugador un tiempo limitado." );
        scrollPane1 = new JScrollPane(area1);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 10, 10); // Añadimos un espacio en los lados y abajo
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panelPrincipal.add(scrollPane1, gbc);

        check1 = new JCheckBox("Aceptar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 10); // Añadimos un espacio en los lados y abajo
        gbc.weightx = 0.5;
        gbc.weighty = 0.0;
        panelPrincipal.add(check1, gbc);

        botoncito1 = new JButton("Continuar");
        botoncito1.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 10, 10); // Añadimos un espacio en la parte inferior y derecha
        panelPrincipal.add(botoncito1, gbc);

        add(panelPrincipal);

        pack();

        ImageIcon imagen = new ImageIcon("Captura.png");// agregar imagen
        label2 = new JLabel(imagen);
        label2.setBounds(315, 135, width/3, height/3);
        add(label2);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        

        
    }

    public void stateChanged(ChangeEvent e){

    }

    public void actionPerformed(ActionEvent e){

    }
}