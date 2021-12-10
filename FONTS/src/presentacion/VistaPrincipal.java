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

        this.setResizable(false);//no es pot fer més gran l'aplicacio
        // fins q no sapiga fer resize i fixar les coses

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
        Graphics2D g2=(Graphics2D) g;
        Font mifont=new Font("Comic Sans MS",Font.BOLD,26);
        g2.setFont(mifont);
        Toolkit mipantalla = Toolkit.getDefaultToolkit();
        Dimension tamPantalla = mipantalla.getScreenSize();
        int altura = tamPantalla.height;
        int amplada = tamPantalla.width;
        g.drawString("RECOMENDATION", amplada-(amplada - 350),altura - (altura - 100) );


    }
    public void button(){
        JButton b=new JButton("Click Here");
        b.setBounds(50,100,95,30);
        b.add(b);
        b.setSize(400,400);
        b.setLayout(null);
        b.setVisible(true);
    }

}
