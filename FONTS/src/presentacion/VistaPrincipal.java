package presentacion;

import  javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.*;

public class VistaPrincipal {
    //controlador presentacio
    private ControladorPresentacion CP;

    //components gui
    private JFrame fin = new JFrame("Recomendation System");
    private JPanel menu = new JPanel();

    private JButton gestuser = new JButton("Gestión de Usuario");
    private JButton gestitem = new JButton("Gestión de Item");
    private JButton recomanacio = new JButton("Recomendaciones");


    private JPanel recomana = new JPanel();
    private JPanel buscarecoana = new JPanel();
    private JComboBox cUserId;
    private JComboBox algoritme;
    private JCheckBox eval;
    private JButton busca;
    private JButton back;
    private JButton save;
    private JLabel imatgeitem;
    private JLabel itemId;
    private JLabel recValue;

    private JPanel gestUser = new JPanel();
    private JPanel gestrate;
    private JTextField tUserId;
    private JButton add;
    private JButton delete;
    private JButton gRate;
    private JComboBox cItemId;
    private JTextField tRate;


    private JPanel gestItem = new JPanel();
    private JButton additem;
    private JButton bGestTag;
    private JButton delteItem;

    private JPanel pGestTag;
    private JComboBox TagsItems;
    private JTextField tagmod;
    private JButton modify;

    private JPanel addItem;
    private JTextField tItemId;

    //atributs
    private int panelactual = 0;

    public VistaPrincipal(ControladorPresentacion cp) {
        CP = cp;
    }

    public VistaPrincipal() {

    }

    public void hacerVisible() {

        fin.setVisible(true);
        fin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fin.pack();

        basic_frame();

    }

    private void basic_frame(){
        fin.setTitle("Recomendation System");
        fin.setResizable(false);

        fin.setSize(960,540);
        fin.setLocationRelativeTo(null);
        panelmain();
    }

    private void panelmain(){
        switch (panelactual){
            case 0:
                start();
                break;
            case 1:
                menu();
                break;
            case 2:
                gestion_user();
                break;
            case 3:
                gestion_item();
                break;
            case 4:
                recomanacio();
                break;
            default:
                break;
        }
    }
   /* private void paintComponent(Graphics2D g) {

        Graphics2D g2 = (Graphics2D) g;
        Font mifont = new Font("Arial Bold", Font.BOLD, 26);
        g.setFont(mifont);
        Toolkit mipantalla = Toolkit.getDefaultToolkit();
        Dimension tamPantalla = mipantalla.getScreenSize();
        int altura = tamPantalla.height;
        int amplada = tamPantalla.width;
        if(panelactual == 0) g.drawString("RECOMENDATION", amplada - (amplada - 350), altura - (altura - 100));


    }*/
    private void start(){
        JPanel start = new JPanel();
        fin.add(start);
        start.setVisible(true);
        start.setLayout(null);


        JLabel titol = new JLabel("SISTEMA RECOMANADOR");
        titol.setFont(new Font("Arial",Font.BOLD,30));
        titol.setBounds(285,30,550,40);
        titol.setVisible(true);
        start.add(titol);

        JLabel frase1 = new JLabel("Escoja el CSV con el que quiere trabajar.");
        JLabel frase2 = new JLabel("Si quiere usar un CSV propio, especifique la ruta de este a continuación.");
        frase1.setFont(new Font("Arial", Font.PLAIN,18));
        frase2.setFont(new Font("Arial", Font.PLAIN,18));
        frase1.setBounds(285,110,400,20);
        frase2.setBounds(220,130,600,20);
        frase1.setVisible(true);
        frase2.setVisible(true);
        start.add(frase1);
        start.add(frase2);

        JComboBox csvs = new JComboBox();
        csvs.setEditable(true);
        csvs.setBounds(285,200,300,25);
        csvs.setVisible(true);
        csvs.addItem("-");
        csvs.addItem("Movies 250");
        csvs.addItem("Movies 750");
        csvs.addItem("Series 250");
        csvs.addItem("Series 750");
        csvs.addItem("Sereies 2250");
        start.add(csvs);
        //resize tontorron pq aparegui el combobox
        fin.setSize(970,541);
        fin.setSize(960,540);

    }

    private void menu(){

        fin.add(menu);
        menu.setVisible(true);
        menu.setLayout(null);

        JLabel titol = new JLabel("SISTEMA RECOMANADOR");
        titol.setFont(new Font("Arial",Font.BOLD,30));
        titol.setBounds(285,70,550,40);
        titol.setVisible(true);
        menu.add(titol);

        gestuser.setBounds(300, 160,350,40);
        gestuser.setFont(new Font("Arial", Font.BOLD, 20));
        gestuser.setVisible(true);

        gestitem.setBounds(300, 220,350,40);
        gestitem.setFont(new Font("Arial", Font.BOLD, 20));
        gestitem.setVisible(true);

        recomanacio.setBounds(300, 280,350,40);
        recomanacio.setFont(new Font("Arial", Font.BOLD, 20));
        recomanacio.setVisible(true);

        menu.add(gestuser);
        menu.add(gestitem);
        menu.add(recomanacio);

        ActionListener gestionar_user = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelactual = 2;
                menu.setVisible(false);
                panelmain();
            }
        };
        gestuser.addActionListener(gestionar_user);

        ActionListener gestionar_item = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelactual = 3;
                menu.setVisible(false);
                panelmain();
            }
        };
        gestitem.addActionListener(gestionar_item);

        ActionListener rec = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelactual = 4;
                menu.setVisible(false);
                panelmain();
            }
        };
        recomanacio.addActionListener(rec);
    }

    private void gestion_user(){
        fin.add(gestUser);
        gestUser.setVisible(true);

        JLabel titolU = new JLabel("Gestión de Usuario");
        titolU.setFont(new Font("Arial",Font.BOLD,30));
        titolU.setBounds(285,70,550,40);
        titolU.setVisible(true);
        gestUser.add(titolU);

    }

    private void gestion_item(){
        fin.add(gestItem);
        gestItem.setVisible(true);

        JLabel titolI = new JLabel("Gestión de Item");
        titolI.setFont(new Font("Arial",Font.BOLD,30));
        titolI.setBounds(285,70,550,40);
        titolI.setVisible(true);
        gestItem.add(titolI);

    }

    private void recomanacio(){
        fin.add(recomana);
        recomana.setVisible(true);

        JLabel titolR = new JLabel("Recomendaciones");
        titolR.setFont(new Font("Arial",Font.BOLD,30));
        titolR.setBounds(285,70,550,40);
        titolR.setVisible(true);
        recomana.add(titolR);

    }



/*
    class Lamina_portada extends JPanel implements ActionListener {



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
/*
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
*/
}