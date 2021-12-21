package presentacion;

import  javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VistaRec extends VistaPrincipal{

    private static JComboBox algoritme;
    private static JButton busca = new JButton("Buscar");
    private static JButton save = new JButton("Guardar recomendación");
    private static boolean nova_rec;
    private static List<Integer> id;
    private static List<Float> val;
    public  VistaRec (){}

    public static void menuRec(){
        JPanel menR = new JPanel();
        fin.add(menR);
        menR.setVisible(true);
        menR.setLayout(null);

        JLabel titolR = new JLabel("RECOMENDACIONES");
        titolR.setFont(new Font("Arial",Font.BOLD,30));
        titolR.setBounds(310,30,550,40);
        menR.add(titolR);

        JButton backRm = new JButton("Volver");
        backRm.setBounds(20,450,100,30);
        menR.add(backRm);

        JButton nuevarec = new JButton("Nueva Recomendación");
        JButton savedrec = new JButton("Cargar Recomendación");
        nuevarec.setFont(new Font("Arial", Font.BOLD, 20));
        savedrec.setFont(new Font("Arial", Font.BOLD, 20));
        nuevarec.setBounds(300, 160,350,40);
        savedrec.setBounds(300, 260,350,40);
        menR.add(nuevarec);
        menR.add(savedrec);

        ActionListener tornarRm = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: tornarR");
                panelactual = 1;
                menR.setVisible(false);
                VistaPrincipal.panelmain();
            }
        };
        backRm.addActionListener(tornarRm);

        ActionListener nova = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: nuevarec");
                panelactual = 5;
                menR.setVisible(false);
                VistaPrincipal.panelmain();
            }
        };
        nuevarec.addActionListener(nova);

        ActionListener cargar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: cargar");
                panelactual = 6;
                menR.setVisible(false);
                VistaPrincipal.panelmain();
            }
        };
        savedrec.addActionListener(cargar);


    }

    public static void recomanacio(){
        JPanel recomana = new JPanel();
        fin.add(recomana);
        recomana.setVisible(true);
        recomana.setLayout(null);

        JLabel titolR = new JLabel("RECOMENDACIONES");
        titolR.setFont(new Font("Arial",Font.BOLD,30));
        titolR.setBounds(310,30,550,40);
        recomana.add(titolR);

        JButton backR = new JButton("Volver");
        backR.setBounds(20,450,100,30);

        recomana.add(backR);

        ActionListener tornarR = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: tornarR");
                panelactual = 4;
                recomana.setVisible(false);
                panelmain();
            }
        };
        backR.addActionListener(tornarR);


        JLabel frase1 = new JLabel("Buscar Recomendación");
        frase1.setFont(new Font("Arial",Font.PLAIN,22));
        frase1.setBounds(350,110,400,20);
        recomana.add(frase1);


/*
        File file = new File(path_csv);

        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        System.out.println(Arrays.toString(directories));*/


        JLabel frase2 = new JLabel("Para el usuario:");
        frase2.setFont(new Font("Arial",Font.PLAIN,18));
        frase2.setBounds(275,180,150,25);
        recomana.add(frase2);


        JComboBox CuserID = new JComboBox(/*CP.list_user()/*directories*/);

        CuserID.setBounds(470,180,250,25);
        CuserID.addItem("2848721");
        CuserID.addItem("8466126");

        recomana.add(CuserID);

        JLabel frase3 = new JLabel("Con el algoritmo:");
        frase3.setFont(new Font("Arial",Font.PLAIN,18));
        frase3.setBounds(275,240,150,25);
        recomana.add(frase3);

        algoritme = new JComboBox();

        algoritme.setBounds(470,240,250,25);
        algoritme.addItem("Collaborative filtering");
        algoritme.addItem("Content based filtering");
        algoritme.addItem("Hybrid algorithm");

        recomana.add(algoritme);

        //resize tontorron pq aparegui el combobox
        fin.setSize(960,541);
        fin.setSize(960,540);



        busca.setFont(new Font("Arial",Font.BOLD,20));
        busca.setBounds(310,360,300,40);
        recomana.add(busca);


        ActionListener search = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userid = (String) CuserID.getSelectedItem();
                int uid = Integer.valueOf(userid);
                System.out.println("user selecionado: " + userid);
                String algorithm = (String) algoritme.getSelectedItem();
                System.out.println("algoritmo selecionado: " + algorithm);

                nova_rec = true;

                if(algorithm == "Collaborative filtering") CP.recommendCF(uid);
                else if(algorithm == "Content based filtering") CP.recommendCBF(uid);
                else CP.recommendH(uid);

                id = CP.list_itemREC();
                val = CP.list_valREC();

                System.out.println("boton pulsado: buscar");
                panelactual = 7;
                recomana.setVisible(false);
                panelmain();
            }
        };
        busca.addActionListener(search);



    }

    public static void cargar_rec(){
        JPanel cargarR = new JPanel();
        fin.add(cargarR);
        cargarR.setVisible(true);
        cargarR.setLayout(null);

        JLabel titolR = new JLabel("RECOMENDACIONES");
        titolR.setFont(new Font("Arial",Font.BOLD,30));
        titolR.setBounds(310,30,550,40);
        cargarR.add(titolR);

        JButton back_carRm = new JButton("Volver");
        back_carRm.setBounds(20,450,100,30);
        cargarR.add(back_carRm);

        ActionListener cargar_rec_back = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: tornar");
                panelactual = 4;
                cargarR.setVisible(false);
                panelmain();
            }
        };
        back_carRm.addActionListener(cargar_rec_back);


        JLabel frase1 = new JLabel("Escoja la recomendacion guardada");
        frase1.setFont(new Font("Arial", Font.PLAIN,22));
        frase1.setBounds(300,130,400,25);
        cargarR.add(frase1);




        File file = new File(path_csv);

        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isFile();
            }
        });
        System.out.println(Arrays.toString(directories));

        JComboBox combo_rec = new JComboBox(directories);

        combo_rec.setBounds(265,190,400,25);

        cargarR.add(combo_rec);

        //resize tontorron pq aparegui el combobox
        fin.setSize(960,541);
        fin.setSize(960,540);



        JButton startB = new JButton("Open");
        startB.setBounds(285,380,350,40);
        startB.setFont(new Font("Arial", Font.BOLD, 20));
        cargarR.add(startB);

        ActionListener comencem = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path_rec = (String) combo_rec.getSelectedItem();
                System.out.println("recomendacion guardada selecionado: " + path_rec);
                id = CP.list_itemSavedREC(path_rec);
                val = CP.list_valSavedREC(path_rec);
                System.out.println("boton pulsado: open");
                nova_rec = false;
                panelactual = 7;
                cargarR.setVisible(false);
                panelmain();
            }
        };
        startB.addActionListener(comencem);
    }

    public static void rec_items(){
        JPanel item_rec = new JPanel();
        fin.add(item_rec);
        item_rec.setVisible(true);
        item_rec.setLayout(null);

        JLabel titolR = new JLabel("RECOMENDACIONES");
        titolR.setFont(new Font("Arial",Font.BOLD,30));
        titolR.setBounds(310,30,550,40);
        item_rec.add(titolR);


        JButton back_carRm = new JButton("Volver");
        back_carRm.setBounds(20,450,100,30);
        item_rec.add(back_carRm);

        ActionListener cargar_rec_back = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: tornar");
                if(nova_rec) panelactual = 5;
                else panelactual = 6;
                item_rec.setVisible(false);
                panelmain();
            }
        };

        back_carRm.addActionListener(cargar_rec_back);
        if (nova_rec) {
            save.setBounds(350,430,220,35);
            save.setFont(new Font("Arial",Font.BOLD,16));
            item_rec.add(save);

            ActionListener guardar = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("boton pulsado: guardar");
                    if(CP.saveRecomendation()){
                        JOptionPane.showMessageDialog(item_rec,"Guardado correctamente");
                    }

                }
            };
            save.addActionListener(guardar);
        }


        JLabel frase1 = new JLabel("Lo que te puede gustar...");
        frase1.setFont(new Font("Arial",Font.PLAIN,22));
        frase1.setBounds(350,80,400,25);
        item_rec.add(frase1);

        int [] x = {150,200,250,300,350};
        int [] y = {200,300};

        JLabel icona1 = new JLabel(new ImageIcon("FONTS/src/presentacion/item.png"));
        JLabel icona2 = new JLabel(new ImageIcon("FONTS/src/presentacion/item.png"));
        JLabel icona3 = new JLabel(new ImageIcon("FONTS/src/presentacion/item.png"));
        JLabel icona4 = new JLabel(new ImageIcon("FONTS/src/presentacion/item.png"));
        JLabel icona5 = new JLabel(new ImageIcon("FONTS/src/presentacion/item.png"));
        JLabel icona6 = new JLabel(new ImageIcon("FONTS/src/presentacion/item.png"));
        JLabel icona7 = new JLabel(new ImageIcon("FONTS/src/presentacion/item.png"));
        JLabel icona8 = new JLabel(new ImageIcon("FONTS/src/presentacion/item.png"));
        JLabel icona9 = new JLabel(new ImageIcon("FONTS/src/presentacion/item.png"));
        JLabel icona10 = new JLabel(new ImageIcon("FONTS/src/presentacion/item.png"));

        icona1.setBounds(150,150,48,48);
        icona2.setBounds(300,150,48,48);
        icona3.setBounds(450,150,48,48);
        icona4.setBounds(600,150,48,48);
        icona5.setBounds(750,150,48,48);
        icona6.setBounds(150,300,48,48);
        icona7.setBounds(300,300,48,48);
        icona8.setBounds(450,300,48,48);
        icona9.setBounds(600,300,48,48);
        icona10.setBounds(750,300,48,48);

        item_rec.add(icona1);
        item_rec.add(icona2);
        item_rec.add(icona3);
        item_rec.add(icona4);
        item_rec.add(icona5);
        item_rec.add(icona6);
        item_rec.add(icona7);
        item_rec.add(icona8);
        item_rec.add(icona9);
        item_rec.add(icona10);

        JLabel itemid1 = new JLabel("ItemId: " + "a");
        JLabel itemid2 = new JLabel("ItemId: ");
        JLabel itemid3 = new JLabel("ItemId: ");
        JLabel itemid4 = new JLabel("ItemId: ");
        JLabel itemid5 = new JLabel("ItemId: ");
        JLabel itemid6 = new JLabel("ItemId: ");
        JLabel itemid7 = new JLabel("ItemId: ");
        JLabel itemid8 = new JLabel("ItemId: ");
        JLabel itemid9 = new JLabel("ItemId: ");
        JLabel itemid10 = new JLabel("ItemId: ");
        itemid1.setBounds(150,200,48,20);
        itemid2.setBounds(300,200,48,20);
        itemid3.setBounds(450,200,48,20);
        itemid4.setBounds(600,200,48,20);
        itemid5.setBounds(750,200,48,20);
        itemid6.setBounds(150,350,48,20);
        itemid7.setBounds(300,350,48,20);
        itemid8.setBounds(450,350,48,20);
        itemid9.setBounds(600,350,48,20);
        itemid10.setBounds(750,350,48,20);
        item_rec.add(itemid1);
        item_rec.add(itemid2);
        item_rec.add(itemid3);
        item_rec.add(itemid4);
        item_rec.add(itemid5);
        item_rec.add(itemid6);
        item_rec.add(itemid7);
        item_rec.add(itemid8);
        item_rec.add(itemid9);
        item_rec.add(itemid10);

        JLabel val1 = new JLabel("Val: " + "a");
        JLabel val2 = new JLabel("Val: ");
        JLabel val3 = new JLabel("Val: ");
        JLabel val4 = new JLabel("Val: ");
        JLabel val5 = new JLabel("Val: ");
        JLabel val6 = new JLabel("Val: ");
        JLabel val7 = new JLabel("Val: ");
        JLabel val8 = new JLabel("Val: ");
        JLabel val9 = new JLabel("Val: ");
        JLabel val10 = new JLabel("Val: ");
        val1.setBounds(150,220,48,20);
        val2.setBounds(300,220,48,20);
        val3.setBounds(450,220,48,20);
        val4.setBounds(600,220,48,20);
        val5.setBounds(750,220,48,20);
        val6.setBounds(150,370,48,20);
        val7.setBounds(300,370,48,20);
        val8.setBounds(450,370,48,20);
        val9.setBounds(600,370,48,20);
        val10.setBounds(750,370,48,20);
        item_rec.add(val1);
        item_rec.add(val2);
        item_rec.add(val3);
        item_rec.add(val4);
        item_rec.add(val5);
        item_rec.add(val6);
        item_rec.add(val7);
        item_rec.add(val8);
        item_rec.add(val9);
        item_rec.add(val10);
    }


}
