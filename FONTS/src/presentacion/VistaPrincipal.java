package presentacion;

import  javax.swing.*;
import javax.tools.*;
import java.awt.*;

public class VistaPrincipal {
    public static void main(String[] args){
        finestra fin = new finestra();
        fin.setVisible(true);
        fin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class finestra extends JFrame{
    public finestra(){


       // setExtendedState(Frame.MAXIMIZED_BOTH);
        //pantalla completa al obrir
        setTitle("Recomendation System");

        Toolkit mipantalla = Toolkit.getDefaultToolkit();
        Dimension tamPantalla = mipantalla.getScreenSize();
        int altura = tamPantalla.height;
        int amplada = tamPantalla.width;
        setSize(amplada/2,altura/2);
        setLocation(amplada/4,altura/4);

        Lamina milamina = new Lamina();
        add(milamina);
    }
}

class Lamina extends JPanel{
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawString("prova 1 ", 100 , 100);

    }
}