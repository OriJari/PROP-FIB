package presentacion;

import  javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;

public class VistaPrincipal {

    //controlador presentacio
    protected static ControladorPresentacion CP;

    //components gui
    protected static JFrame fin = new JFrame("Recomendation System");


    private static JButton gestuser = new JButton("Gestión de Usuario");
    private static JButton gestitem = new JButton("Gestión de Item");
    private static JButton recomanacio = new JButton("Recomendaciones");



    //atributs
    protected static int panelactual = 5;
    protected static String path_csv;



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

    public void basic_frame(){
        fin.setTitle("Recomendation System");
        fin.setResizable(false);

        fin.setSize(960,540);
        fin.setLocationRelativeTo(null);
        panelmain();
    }

    public static void panelmain(){
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
                VistaUser.gestion_user();
                break;
            case 3:
                System.out.println("Case: " + panelactual  + " gestion_item");
                VistaItem.gestion_item();
                break;
            case 4:
                System.out.println("Case: " + panelactual  + " recomanacio");
                VistaRec.menuRec();
                break;
            case 5:
                System.out.println("Case: " + panelactual  + " buscar recomanacio");
                VistaRec.recomanacio();
                break;

            case 6:
                System.out.println("Case: " + panelactual  + " cargar recomanacio");
                VistaRec.cargar_rec();
                break;
            case 7:
                System.out.println("Case: " + panelactual  + " items recomanacio");
                int i = 0;
                VistaRec.rec_items(i);
                break;

            case 8:
                System.out.println("Case: " + panelactual  + " gestion ratings");
                VistaUser.gestion_ratings();
                break;

            case 9:
                System.out.println("Case: " + panelactual  + " gestion tags");
                VistaItem.gestion_tag();
                break;

            default:
                break;
        }
    }

    public static void start(){

        JPanel start = new JPanel();


        fin.add(start);
        start.setVisible(true);
        start.setLayout(null);


        JLabel titol = new JLabel("SISTEMA RECOMANADOR");
        titol.setFont(new Font("Arial",Font.BOLD,30));
        titol.setBounds(290,30,550,40);
        start.add(titol);

        JLabel frase1 = new JLabel("Escoja el CSV con el que quiere trabajar");
        frase1.setFont(new Font("Arial", Font.PLAIN,18));
        frase1.setBounds(310,130,400,20);
        start.add(frase1);



        JButton csvs = new JButton("Browser CSV");

        csvs.setBounds(390,250,150,25);

        start.add(csvs);

        JTextField csvchoosen = new JTextField();
        csvchoosen.setEnabled(false);
        csvchoosen.setBounds(270,190,400,25);
        start.add(csvchoosen);

        File file = new File("DATA");
        JFileChooser fc = new JFileChooser(file);

        ActionListener selecciona = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //browser ficheros/dyrectory

                fc.setDialogTitle("Escojer CSV");
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    //
                }
                File file1 = new File(fc.getSelectedFile().getPath());
                File file2 = new File(file.getAbsolutePath());

                String absolutePath1 = file1.toString();
                String absolutePath2 = file2.toString();
                String path = absolutePath1.substring(absolutePath2.length());

                System.out.println(path);
                csvchoosen.setFont(new Font("Arial", Font.BOLD,14));
                csvchoosen.setText(path);
            }
        };
        csvs.addActionListener(selecciona);




        JButton startB = new JButton("Start");
        startB.setBounds(290,380,350,40);
        startB.setFont(new Font("Arial", Font.BOLD, 20));
        start.add(startB);

        ActionListener comencem = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                path_csv =  fc.getSelectedFile().getPath();
                System.out.println("CSV selecionado: " + path_csv);
                CP.inicializar(path_csv);

                System.out.println("boton pulsado: start");
                panelactual = 1;
                start.setVisible(false);
                panelmain();
            }
        };
        startB.addActionListener(comencem);


    }

    public static void menu(){
        JPanel menu = new JPanel();
        fin.add(menu);
        menu.setVisible(true);
        menu.setLayout(null);

        JLabel titol = new JLabel("SISTEMA RECOMANADOR");
        titol.setFont(new Font("Arial",Font.BOLD,30));
        titol.setBounds(285,30,550,40);
        menu.add(titol);

        gestuser.setBounds(300, 150,350,40);
        gestuser.setFont(new Font("Arial", Font.BOLD, 20));


        gestitem.setBounds(300, 230,350,40);
        gestitem.setFont(new Font("Arial", Font.BOLD, 20));


        recomanacio.setBounds(300, 310,350,40);
        recomanacio.setFont(new Font("Arial", Font.BOLD, 20));


        JButton backM = new JButton("Volver");
        backM.setBounds(20,450,100,30);

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




}