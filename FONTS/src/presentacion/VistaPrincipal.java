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
    private JPanel start = new JPanel();

    private JButton gestuser = new JButton("Gestión de Usuario");
    private JButton gestitem = new JButton("Gestión de Item");
    private JButton recomanacio = new JButton("Recomendaciones");


    private JPanel recomana = new JPanel();
    private JPanel buscarecoana = new JPanel();
    private JComboBox cUserId;
    private JComboBox algoritme;
    private JCheckBox eval;
    private JButton busca;

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
                System.out.println("Case: " + panelactual  + " start");
                start();
                break;
            case 1:
                System.out.println("Case: " + panelactual  + " menu");
                menu();
                break;
            case 2:
                System.out.println("Case: " + panelactual  + " gestion_user");
                gestion_user();
                break;
            case 3:
                System.out.println("Case: " + panelactual  + " gestion_item");
                gestion_item();
                break;
            case 4:
                System.out.println("Case: " + panelactual  + " recomanacio");
                recomanacio();
                break;
            default:
                break;
        }
    }

    private void start(){

        fin.add(start);
        start.setVisible(true);
        start.setLayout(null);


        JLabel titol = new JLabel("SISTEMA RECOMANADOR");
        titol.setFont(new Font("Arial",Font.BOLD,30));
        titol.setBounds(280,30,550,40);
        titol.setVisible(true);
        start.add(titol);

        JLabel frase1 = new JLabel("Escoja el CSV con el que quiere trabajar.");
        JLabel frase2 = new JLabel("Si quiere usar un CSV propio, escriba la ruta.");
        frase1.setFont(new Font("Arial", Font.PLAIN,16));
        frase2.setFont(new Font("Arial", Font.PLAIN,16));
        frase1.setBounds(285,150,400,20);
        frase2.setBounds(280,180,400,20);
        //frase1.setVisible(true);
        //frase2.setVisible(true);
        start.add(frase1);
        start.add(frase2);

        JComboBox csvs = new JComboBox();
        csvs.setEditable(true);
        csvs.setBounds(265,250,400,25);

        csvs.addItem(" ");
        csvs.addItem("Movies 250");
        csvs.addItem("Movies 750");
        csvs.addItem("Series 250");
        csvs.addItem("Series 750");
        csvs.addItem("Series 2250");

        start.add(csvs);
        //csvs.setVisible(true);
        //resize tontorron pq aparegui el combobox
        fin.setSize(960,541);
        fin.setSize(960,540);


        JButton startB = new JButton("Start");
        startB.setBounds(285,380,350,40);
        startB.setFont(new Font("Arial", Font.BOLD, 20));
        startB.setVisible(true);
        start.add(startB);

        ActionListener comencem = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: start");
                panelactual = 1;
                start.setVisible(false);
                String s = (String) csvs.getSelectedItem();
                System.out.println("CSV selecionado: " + s);
                //CP.CSVescollit(s);
                panelmain();
            }
        };
        startB.addActionListener(comencem);


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
        //gestuser.setVisible(true);

        gestitem.setBounds(300, 220,350,40);
        gestitem.setFont(new Font("Arial", Font.BOLD, 20));
        //gestitem.setVisible(true);

        recomanacio.setBounds(300, 280,350,40);
        recomanacio.setFont(new Font("Arial", Font.BOLD, 20));
        //recomanacio.setVisible(true);

        JButton backM = new JButton("Volver");
        backM.setBounds(50,425,100,30);

        menu.add(backM);
        menu.add(gestuser);
        menu.add(gestitem);
        menu.add(recomanacio);


        ActionListener tornarM = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: volver");
                panelactual = 0;
                menu.setVisible(false);
                panelmain();
            }
        };
        backM.addActionListener(tornarM);

        ActionListener gestionar_user = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: gestion user");
                panelactual = 2;
                menu.setVisible(false);
                panelmain();
            }
        };
        gestuser.addActionListener(gestionar_user);

        ActionListener gestionar_item = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: gestion item");
                panelactual = 3;
                menu.setVisible(false);
                panelmain();
            }
        };
        gestitem.addActionListener(gestionar_item);

        ActionListener rec = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: recomanacio");
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
        gestUser.setLayout(null);

        JLabel titolU = new JLabel("Gestión de Usuario");
        titolU.setFont(new Font("Arial",Font.BOLD,30));
        titolU.setBounds(285,70,550,40);
        titolU.setVisible(true);
        gestUser.add(titolU);

        JButton backGU = new JButton("Volver");
        backGU.setBounds(50,425,100,30);

        gestUser.add(backGU);

        ActionListener tornarGU = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: tornarGU");
                panelactual = 1;
                gestUser.setVisible(false);
                panelmain();
            }
        };
        backGU.addActionListener(tornarGU);

    }

    private void gestion_item(){
        fin.add(gestItem);
        gestItem.setVisible(true);
        gestItem.setLayout(null);

        JLabel titolI = new JLabel("Gestión de Item");
        titolI.setFont(new Font("Arial",Font.BOLD,30));
        titolI.setBounds(285,70,550,40);
        titolI.setVisible(true);
        gestItem.add(titolI);

        JButton backGI = new JButton("Volver");
        backGI.setBounds(50,425,100,30);

        gestItem.add(backGI);

        ActionListener tornarGI = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: tornarGI");
                panelactual = 1;
                gestItem.setVisible(false);
                panelmain();
            }
        };
        backGI.addActionListener(tornarGI);

    }

    private void recomanacio(){
        fin.add(recomana);
        recomana.setVisible(true);
        recomana.setLayout(null);

        JLabel titolR = new JLabel("Recomendaciones");
        titolR.setFont(new Font("Arial",Font.BOLD,30));
        titolR.setBounds(285,70,550,40);
        titolR.setVisible(true);
        recomana.add(titolR);

        JButton backR = new JButton("Volver");
        backR.setBounds(50,425,100,30);

        recomana.add(backR);

        ActionListener tornarR = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: tornarR");
                panelactual = 1;
                recomana.setVisible(false);
                panelmain();
            }
        };
        backR.addActionListener(tornarR);


    }




}