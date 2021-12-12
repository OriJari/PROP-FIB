package presentacion;

import  javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaPrincipal {
    private JFrame fin = new JFrame("Recomendation System");
    private JPanel laminapor = new JPanel();
    private JPanel laminalog = new JPanel();
    public VistaPrincipal(ControladorPresentacion controladorPresentacion) {
    }

    public void hacerVisible() {

        fin.setVisible(true);
        fin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


    class finestra extends JFrame {
        Lamina_portada laminapor = new Lamina_portada();
        Lamina_login laminalog = new Lamina_login();

        public finestra() {//constructora
        }

        public void finestra_main() {


            // setExtendedState(Frame.MAXIMIZED_BOTH);
            //pantalla completa al obrir
            setTitle("Recomendation System");

            this.setResizable(false);//no es pot fer més gran l'aplicacio
            // fins q no sapiga fer resize i fixar les coses

            Toolkit mipantalla = Toolkit.getDefaultToolkit();
            Dimension tamPantalla = mipantalla.getScreenSize();
            int altura = tamPantalla.height;
            int amplada = tamPantalla.width;
            setSize(amplada / 2, altura / 2);
            setLocation(amplada / 4, altura / 4);

            add(laminapor);
        }

        public void view_log() {

            add(laminalog);
        }


    }

    class Lamina_portada extends JPanel implements ActionListener {


        JButton button_login = new JButton("Log In");
        JButton button_singup = new JButton("Sing Up");

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            Font mifont = new Font("Arial Bold", Font.BOLD, 26);
            g2.setFont(mifont);
            Toolkit mipantalla = Toolkit.getDefaultToolkit();
            Dimension tamPantalla = mipantalla.getScreenSize();
            int altura = tamPantalla.height;
            int amplada = tamPantalla.width;
            g2.drawString("RECOMENDATION", amplada - (amplada - 350), altura - (altura - 100));
            button();


        }

        public void button() {
            add(button_login);
            add(button_singup);
            button_login.addActionListener(this);
            button_singup.addActionListener(this);

        }

        public void actionPerformed(ActionEvent e) {
            Object botonPulsado = e.getSource();
            if (botonPulsado == button_login) {

            } else {

            }

        }
    }

    class Lamina_login extends JPanel implements ActionListener {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            Font mifont = new Font("Arial Bold", Font.BOLD, 26);
            g2.setFont(mifont);
            Toolkit mipantalla = Toolkit.getDefaultToolkit();
            Dimension tamPantalla = mipantalla.getScreenSize();
            int altura = tamPantalla.height;
            int amplada = tamPantalla.width;
            g2.drawString("LOG IN", amplada - (amplada - 350), altura - (altura - 100));
            button();


        }

        public void button() {
            JButton button_login = new JButton("Log In");

            add(button_login);
            button_login.addActionListener((this));

        }

        public void actionPerformed(ActionEvent e) {

        }
    }

}