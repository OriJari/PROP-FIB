package presentacion;

import  javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class VistaPrincipal {

    //controlador presentacio
    private ControladorPresentacion CP;

    //components gui
    private JFrame fin = new JFrame("Recomendation System");


    //recomendacions
    private JButton busca = new JButton("Buscar");
    private JButton save = new JButton("Guardar recomendación");
    private boolean nova_rec;
    private java.util.List<Integer> id;
    private List<Float> val;
    private int nitems;
    private JCheckBox eval = new JCheckBox("Evaluación");
    private boolean checkbox = false;

    //user
    private  JTextField tUserId = new JTextField();
    private  JButton addB = new JButton("Añadir");
    private  JButton addBR = new JButton("Añadir/Modificar");
    private  JButton deleteB = new JButton("Eliminar") ;
    private  JButton deleteBR = new JButton("Eliminar") ;
    private  JButton gRate = new JButton("Gestionar Ratings");
    private  JComboBox cItemId;
    private  JComboBox cUserid;
    private  JTextField tRate = new JTextField();
    private  int id_actual_user;


    //item
    private  JButton gesTag = new JButton("Gestionar Tags");
    private  JComboBox TagsItems = new JComboBox();
    private  JComboBox citemid = new JComboBox();
    private  JTextField tagmod = new JTextField();
    private  int id_actual;

    //start
    private JPanel start = new JPanel();
    private JLabel titolStart = new JLabel("SISTEMA RECOMANADOR");
    private JButton csvs = new JButton("Browser CSV");
    private JTextField csvchoosen = new JTextField();
    private JFileChooser fc;
    private JButton startB = new JButton("Start");

    //menu
    private JPanel menu = new JPanel();
    private JLabel titolMenu = new JLabel("SISTEMA RECOMANADOR");
    private JButton backM = new JButton("Volver");
    private  JButton gestuser = new JButton("Gestión de Usuario");
    private  JButton gestitem = new JButton("Gestión de Item");
    private  JButton recomanacio = new JButton("Recomendaciones");

    //menuRecomanacio
    private JPanel menR = new JPanel();
    private JLabel titolMR = new JLabel("RECOMENDACIONES");
    private JButton backRm = new JButton("Volver");
    private JButton nuevarec = new JButton("Nueva Recomendación");
    private JButton savedrec = new JButton("Cargar Recomendación");

    //recomanacio
    private JPanel recomana = new JPanel();
    private JLabel titolRecomanacio = new JLabel("RECOMENDACIONES");
    private JButton backR = new JButton("Volver");
    private JLabel frase1Recomanacio = new JLabel("Buscar Recomendación");
    private JComboBox CuserID = new JComboBox(/*(Vector) CP.list_user()*/);
    private JLabel frase2Recomanacio = new JLabel("Para el usuario:");
    private JLabel frase3Recomanacio = new JLabel("Con el algoritmo:");
    private JLabel frase4Recomanacio = new JLabel("Numero de items recomendados:");
    private  JComboBox algoritme = new JComboBox();
    private JComboBox nit = new JComboBox();

    //carega recomanacio
    private JPanel cargarR = new JPanel();
    private JLabel titolCargaR = new JLabel("RECOMENDACIONES");
    private JButton back_carRm = new JButton("Volver");
    private JLabel frase1CR = new JLabel("Escoja la recomendacion guardada");
    private JComboBox combo_rec;
    private JButton openB = new JButton("Open");

    //items recomanats
    private JPanel item_rec = new JPanel();


    //atributs
    private  int panelactual = 5;
    private  String path_csv;



    public VistaPrincipal(ControladorPresentacion cp) {
        CP = cp;
        inicializaComponentes();
    }

    private void inicializaComponentes() {
        hacerVisible();


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

    public  void panelmain(){
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

                menuRec();
                break;
            case 5:
                System.out.println("Case: " + panelactual  + " buscar recomanacio");

                recomanacio();
                break;

            case 6:
                System.out.println("Case: " + panelactual  + " cargar recomanacio");
                cargar_rec();
                break;
            case 7:
                System.out.println("Case: " + panelactual  + " items recomanacio");

                rec_items();
                break;

            case 8:
                System.out.println("Case: " + panelactual  + " gestion ratings");
                gestion_ratings();
                break;

            case 9:
                System.out.println("Case: " + panelactual  + " gestion tags");
                gestion_tag();
                break;

            default:
                break;
        }
    }

    public  void start(){

        fin.add(start);
        start.setVisible(true);
        start.setLayout(null);

        titolStart.setFont(new Font("Arial",Font.BOLD,30));
        titolStart.setBounds(290,30,550,40);
        start.add(titolStart);

        JLabel frase1 = new JLabel("Escoja el CSV con el que quiere trabajar");
        frase1.setFont(new Font("Arial", Font.PLAIN,18));
        frase1.setBounds(310,130,400,20);
        start.add(frase1);

        csvs.setBounds(390,250,150,25);
        start.add(csvs);


        csvchoosen.setEnabled(false);
        csvchoosen.setBounds(270,190,400,25);
        start.add(csvchoosen);

        File file = new File("DATA");
        fc = new JFileChooser(file);

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

    public  void menu(){

        fin.add(menu);
        menu.setVisible(true);
        menu.setLayout(null);


        titolMenu.setFont(new Font("Arial",Font.BOLD,30));
        titolMenu.setBounds(285,30,550,40);
        menu.add(titolMenu);

        gestuser.setBounds(300, 150,350,40);
        gestuser.setFont(new Font("Arial", Font.BOLD, 20));


        gestitem.setBounds(300, 230,350,40);
        gestitem.setFont(new Font("Arial", Font.BOLD, 20));


        recomanacio.setBounds(300, 310,350,40);
        recomanacio.setFont(new Font("Arial", Font.BOLD, 20));



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

    public  void menuRec(){

        fin.add(menR);
        menR.setVisible(true);
        menR.setLayout(null);


        titolMR.setFont(new Font("Arial",Font.BOLD,30));
        titolMR.setBounds(310,30,550,40);
        menR.add(titolMR);


        backRm.setBounds(20,450,100,30);
        menR.add(backRm);


        nuevarec.setFont(new Font("Arial", Font.BOLD, 20));
        savedrec.setFont(new Font("Arial", Font.BOLD, 20));
        nuevarec.setBounds(300, 160,350,40);
        savedrec.setBounds(300, 300,350,40);
        menR.add(nuevarec);
        menR.add(savedrec);

        eval.setFont(new Font("Arial",Font.PLAIN,18));
        eval.setBounds(410,220,250,25);
        eval.setSelected(checkbox);
        menR.add(eval);

        ActionListener tornarRm = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: tornarR");
                panelactual = 1;
                menR.setVisible(false);
                panelmain();
            }
        };
        backRm.addActionListener(tornarRm);

        ActionListener nova = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(eval.isSelected()) checkbox = true;
                else checkbox = false;
                System.out.println("boton pulsado: nuevarec");
                panelactual = 5;
                menR.setVisible(false);
                panelmain();
            }
        };
        nuevarec.addActionListener(nova);

        ActionListener cargar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: cargar");
                panelactual = 6;
                menR.setVisible(false);
                panelmain();
            }
        };
        savedrec.addActionListener(cargar);


    }

    public  void recomanacio(){

        fin.add(recomana);
        recomana.setVisible(true);
        recomana.setLayout(null);


        titolRecomanacio.setFont(new Font("Arial",Font.BOLD,30));
        titolRecomanacio.setBounds(310,30,550,40);
        recomana.add(titolRecomanacio);


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



        frase1Recomanacio.setFont(new Font("Arial",Font.PLAIN,22));
        frase1Recomanacio.setBounds(350,110,400,20);
        recomana.add(frase1Recomanacio);





        frase2Recomanacio.setFont(new Font("Arial",Font.PLAIN,18));
        frase2Recomanacio.setBounds(281,180,150,25);
        recomana.add(frase2Recomanacio);




        CuserID.setBounds(470,180,250,25);
        CuserID.addItem("2848721");
        CuserID.addItem("8466126");

        recomana.add(CuserID);


        frase3Recomanacio.setFont(new Font("Arial",Font.PLAIN,18));
        frase3Recomanacio.setBounds(273,240,150,25);
        recomana.add(frase3Recomanacio);



        algoritme.setBounds(470,240,250,25);
        algoritme.addItem("Collaborative filtering");
        algoritme.addItem("Content based filtering");
        algoritme.addItem("Hybrid algorithm");

        recomana.add(algoritme);

        //resize tontorron pq aparegui el combobox
        fin.setSize(960,541);
        fin.setSize(960,540);


        frase4Recomanacio.setFont(new Font("Arial",Font.PLAIN,18));
        frase4Recomanacio.setBounds(145,300,300,25);
        recomana.add(frase4Recomanacio);


        nit.setBounds(470,300,250,25);
        nit.addItem("1");
        nit.addItem("2");
        nit.addItem("3");
        nit.addItem("4");
        nit.addItem("5");
        nit.addItem("6");
        nit.addItem("7");
        nit.addItem("8");
        nit.addItem("9");
        nit.addItem("10");
        recomana.add(nit);

        busca.setFont(new Font("Arial",Font.BOLD,20));
        busca.setBounds(310,380,300,40);
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
                nitems = Integer.valueOf((String) nit.getSelectedItem());

                System.out.println(nitems);

                //if(algorithm == "Collaborative filtering") CP.recommendCF(nitems,uid,checkbox);
                //else if(algorithm == "Content based filtering") CP.recommendCBF(nitems,uid,checkbox);
                //else CP.recommendH(nitems,uid,checkbox);
                //id = CP.list_itemREC();
                //val = CP.list_valREC();
                System.out.println("boton pulsado: buscar");
                panelactual = 7;
                recomana.setVisible(false);
                panelmain();



            }
        };
        busca.addActionListener(search);



    }

    public  void cargar_rec(){

        fin.add(cargarR);
        cargarR.setVisible(true);
        cargarR.setLayout(null);


        titolCargaR.setFont(new Font("Arial",Font.BOLD,30));
        titolCargaR.setBounds(310,30,550,40);
        cargarR.add(titolCargaR);


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



        frase1CR.setFont(new Font("Arial", Font.PLAIN,22));
        frase1CR.setBounds(300,130,400,25);
        cargarR.add(frase1CR);




        File file = new File(path_csv);

        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isFile();
            }
        });
        System.out.println(Arrays.toString(directories));

        combo_rec = new JComboBox(directories);

        combo_rec.setBounds(265,190,400,25);

        cargarR.add(combo_rec);

        //resize tontorron pq aparegui el combobox
        fin.setSize(960,541);
        fin.setSize(960,540);




        openB.setBounds(285,380,350,40);
        openB.setFont(new Font("Arial", Font.BOLD, 20));
        cargarR.add(openB);

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
        openB.addActionListener(comencem);
    }

    public  void rec_items(){

        fin.add(item_rec);
        item_rec.setVisible(true);
        item_rec.setLayout(null);

        JLabel titolIR = new JLabel("RECOMENDACIONES");
        titolIR.setFont(new Font("Arial",Font.BOLD,30));
        titolIR.setBounds(310,30,550,40);
        item_rec.add(titolIR);


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

        List<JLabel> icon = new ArrayList<>();
        List<JLabel> itemid = new ArrayList<>();
        List<JLabel> val = new ArrayList<>();
        for (int i = 0; 5*i < nitems;++i) {
            for(int j = 0; j < 5 && 5*i+j < nitems; ++j) {
                icon.add(new JLabel(new ImageIcon("FONTS/src/presentacion/item.png")));
                icon.get(5*i+j).setBounds(150 +150*j,150+150*i,48,48);
                item_rec.add(icon.get(5*i+j));
                itemid.add(new JLabel("ItemId: "));
                itemid.get(5*i+j).setBounds(150 +150*j,200+150*i,48,20);
                item_rec.add(itemid.get(5*i+j));
                val.add(new JLabel("Val: "));
                val.get(5*i+j).setBounds(150 +150*j,220+150*i,48,20);
                item_rec.add(val.get(5*i+j));


            }
        }
        /*
        if (nitems > 0) {
            JLabel icona1 = new JLabel(new ImageIcon("FONTS/src/presentacion/item.png"));
            icona1.setBounds(150,150,48,48); item_rec.add(icona1);
            JLabel itemid1 = new JLabel("ItemId: " ); itemid1.setBounds(150,200,48,20);
            item_rec.add(itemid1); JLabel val1 = new JLabel("Val: " + "a");
            val1.setBounds(150,220,48,20); item_rec.add(val1);
        }
        if(nitems > 1){
            JLabel icona2 = new JLabel(new ImageIcon("FONTS/src/presentacion/item.png"));
            icona2.setBounds(300,150,48,48);
            item_rec.add(icona2); JLabel itemid2 = new JLabel("ItemId: ");
            itemid2.setBounds(300,200,48,20); item_rec.add(itemid2);
            JLabel val2 = new JLabel("Val: "); val2.setBounds(300,220,48,20); item_rec.add(val2);
        }
        if(nitems > 2){
            JLabel icona3 = new JLabel(new ImageIcon("FONTS/src/presentacion/item.png"));
            icona3.setBounds(450,150,48,48); item_rec.add(icona3);
            JLabel itemid3 = new JLabel("ItemId: "); itemid3.setBounds(450,200,48,20);
            item_rec.add(itemid3);
        }
        if(nitems > 3){
            JLabel icona4 = new JLabel(new ImageIcon("FONTS/src/presentacion/item.png"));
            icona4.setBounds(600,150,48,48); item_rec.add(icona4);
            JLabel itemid4 = new JLabel("ItemId: "); itemid4.setBounds(600,200,48,20);
            item_rec.add(itemid4);
        }
        if(nitems > 4){
            JLabel icona5 = new JLabel(new ImageIcon("FONTS/src/presentacion/item.png"));
            icona5.setBounds(750,150,48,48); item_rec.add(icona5);
            JLabel itemid5 = new JLabel("ItemId: "); itemid5.setBounds(750,200,48,20);
            item_rec.add(itemid5);
        }
        if(nitems > 5){
            JLabel icona6 = new JLabel(new ImageIcon("FONTS/src/presentacion/item.png"));
            icona6.setBounds(150,300,48,48); item_rec.add(icona6);
            JLabel itemid6 = new JLabel("ItemId: "); itemid6.setBounds(150,350,48,20);
            item_rec.add(itemid6);
        }
        if(nitems > 6){
            JLabel icona7 = new JLabel(new ImageIcon("FONTS/src/presentacion/item.png"));
            icona7.setBounds(300,300,48,48); item_rec.add(icona7);
            JLabel itemid7 = new JLabel("ItemId: ");  itemid7.setBounds(300,350,48,20);
            item_rec.add(itemid7);
        }
        if(nitems > 7){
            JLabel icona8 = new JLabel(new ImageIcon("FONTS/src/presentacion/item.png"));
            icona8.setBounds(450,300,48,48); item_rec.add(icona8);
            JLabel itemid8 = new JLabel("ItemId: "); itemid8.setBounds(450,350,48,20);
            item_rec.add(itemid8);
        }
        if(nitems > 8){
            JLabel icona9 = new JLabel(new ImageIcon("FONTS/src/presentacion/item.png"));
            icona9.setBounds(600,300,48,48); item_rec.add(icona9);
            JLabel itemid9 = new JLabel("ItemId: "); itemid9.setBounds(600,350,48,20);
            item_rec.add(itemid9);
        }
        if(nitems > 9){
            JLabel icona10 = new JLabel(new ImageIcon("FONTS/src/presentacion/item.png"));
            icona10.setBounds(750,300,48,48); item_rec.add(icona10);
            JLabel itemid10 = new JLabel("ItemId: "); itemid10.setBounds(750,350,48,20);
            item_rec.add(itemid10);
        }
         JLabel val3 = new JLabel("Val: ");
        JLabel val4 = new JLabel("Val: ");
        JLabel val5 = new JLabel("Val: ");
        JLabel val6 = new JLabel("Val: ");
        JLabel val7 = new JLabel("Val: ");
        JLabel val8 = new JLabel("Val: ");
        JLabel val9 = new JLabel("Val: ");
        JLabel val10 = new JLabel("Val: ");


        val3.setBounds(450,220,48,20);
        val4.setBounds(600,220,48,20);
        val5.setBounds(750,220,48,20);
        val6.setBounds(150,370,48,20);
        val7.setBounds(300,370,48,20);
        val8.setBounds(450,370,48,20);
        val9.setBounds(600,370,48,20);
        val10.setBounds(750,370,48,20);


        item_rec.add(val3);
        item_rec.add(val4);
        item_rec.add(val5);
        item_rec.add(val6);
        item_rec.add(val7);
        item_rec.add(val8);
        item_rec.add(val9);
        item_rec.add(val10);
*/
    }

    public  void gestion_user(){
        JPanel gestUser = new JPanel();
        fin.add(gestUser);
        gestUser.setVisible(true);
        gestUser.setLayout(null);

        JLabel titolU = new JLabel("GESTION DE USUARIO");
        titolU.setFont(new Font("Arial",Font.BOLD,30));
        titolU.setBounds(310,30,550,40);
        gestUser.add(titolU);

        JButton backGU = new JButton("Volver");
        backGU.setBounds(20,450,100,30);

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

        JLabel frase1 = new JLabel("Escriba el ID del usuario que quiera añadir");
        frase1.setFont(new Font("Arial", Font.PLAIN,18));
        frase1.setBounds(310,110,400,20);
        gestUser.add(frase1);


        tUserId.setBounds(285,150,150,30);
        gestUser.add(tUserId);

        addB.setFont(new Font("Arial",Font.BOLD,18));
        addB.setBounds(515,150,150,30);
        gestUser.add(addB);




        cUserid = new JComboBox((Vector) CP.list_user());

        cUserid.setBounds(285,250,150,30);

        gestUser.add(cUserid);

        //resize tontorron pq aparegui el combobox
        fin.setSize(960,541);
        fin.setSize(960,540);

        JLabel frase2 = new JLabel("Escoje el ID del usuario que quiera eliminar/modificar las ratings");
        frase2.setFont(new Font("Arial", Font.PLAIN,18));
        frase2.setBounds(220,200,600,20);
        gestUser.add(frase2);

        deleteB.setFont(new Font("Arial",Font.BOLD,18));
        deleteB.setBounds(515,250,150,30);
        gestUser.add(deleteB);

        gRate.setFont(new Font("Arial",Font.BOLD,18));
        gRate.setBounds(360,325,220,40);
        gestUser.add(gRate);

        ActionListener afegirU = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: añadir");

                if(isNumericI(tUserId.getText()) && CP.addUser(Integer.valueOf(tUserId.getText()))){
                    JOptionPane.showMessageDialog(gestUser,"Añadido correctamente");
                    System.out.println(tUserId.getText());
                }
                else if(isNumericI(tUserId.getText())) JOptionPane.showMessageDialog(gestUser,"Usuario ya registrado","Error",0);
                else JOptionPane.showMessageDialog(gestUser,"No se ha podido añadir correctamente","Error",0);
            }
        };
        addB.addActionListener(afegirU);

        ActionListener eliminarU = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: eliminar");

                if(CP.deleteUser((Integer)cUserid.getSelectedItem())){
                    JOptionPane.showMessageDialog(gestUser,"Eliminado correctamente");
                    System.out.println(tUserId.getText());
                }
                else JOptionPane.showMessageDialog(gestUser,"No se ha podido eliminar correctamente","Error",0);
            }
        };
        deleteB.addActionListener(eliminarU);

        ActionListener ratings = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: gestionar ratings");
                id_actual_user = (Integer)cUserid.getSelectedItem();
                panelactual = 8;
                gestUser.setVisible(false);
                panelmain();

            }
        };
        gRate.addActionListener(ratings);


    }

    public  void gestion_ratings(){
        JPanel gestRate = new JPanel();
        fin.add(gestRate);
        gestRate.setVisible(true);
        gestRate.setLayout(null);

        JLabel titolU = new JLabel("GESTION DE RATINGS");
        titolU.setFont(new Font("Arial",Font.BOLD,30));
        titolU.setBounds(310,30,550,40);
        gestRate.add(titolU);

        JButton backGR = new JButton("Volver");
        backGR.setBounds(20,450,100,30);
        gestRate.add(backGR);

        ActionListener tornarGR = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: tornarGR");
                panelactual = 2;
                gestRate.setVisible(false);
                panelmain();
            }
        };
        backGR.addActionListener(tornarGR);

        JLabel frase1 = new JLabel("Selecciona el ID del item que quiera gestionar");
        frase1.setFont(new Font("Arial", Font.PLAIN,18));
        frase1.setBounds(300,110,400,20);
        gestRate.add(frase1);

        JLabel frase2 = new JLabel("Nuevo valor de la rate");
        frase2.setFont(new Font("Arial", Font.PLAIN,16));
        frase2.setBounds(285,250,200,20);
        gestRate.add(frase2);

        tRate.setBounds(285,280,150,30);
        gestRate.add(tRate);

        addBR.setFont(new Font("Arial",Font.BOLD,18));
        addBR.setBounds(515,280,200,30);
        gestRate.add(addBR);




        cItemId = new JComboBox((Vector) CP.list_item());
        cItemId.setBounds(330,160,300,30);

        gestRate.add(cItemId);

        //resize tontorron pq aparegui el combobox
        fin.setSize(960,541);
        fin.setSize(960,540);

        deleteBR.setFont(new Font("Arial",Font.BOLD,18));
        deleteBR.setBounds(405,350,150,30);
        gestRate.add(deleteBR);


        ActionListener mod = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: añadir/modificar");

                if(isNumericF(tRate.getText()) && CP.addRating(id_actual_user,Integer.valueOf((Integer) cUserid.getSelectedItem()),Float.valueOf(tRate.getText()))){
                    JOptionPane.showMessageDialog(gestRate,"Añadido/modificado correctamente");
                    System.out.println(tRate.getText());
                }
                else JOptionPane.showMessageDialog(gestRate,"No se ha podido añadir/modificar correctamente","Error",0);
            }
        };
        addBR.addActionListener(mod);

        ActionListener eli = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: eliminar");

                if(CP.deleteRating(id_actual_user,(Integer)cItemId.getSelectedItem())){
                    JOptionPane.showMessageDialog(gestRate,"Eliminado correctamente");
                    System.out.println("eliminado");
                }
                else JOptionPane.showMessageDialog(gestRate,"No se ha podido eliminar correctamente","Error",0);
            }
        };
        deleteBR.addActionListener(eli);


    }

    public  void gestion_item(){
        JPanel gestItem = new JPanel();
        fin.add(gestItem);
        gestItem.setVisible(true);
        gestItem.setLayout(null);

        JLabel titolI = new JLabel("GESTION DE ITEM");
        titolI.setFont(new Font("Arial",Font.BOLD,30));
        titolI.setBounds(340,30,550,40);
        gestItem.add(titolI);

        JButton backGI = new JButton("Volver");
        backGI.setBounds(20,450,100,30);

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

        JLabel frase1 = new JLabel("Escriba el ID del item que quiera añadir");
        frase1.setFont(new Font("Arial", Font.PLAIN,18));
        frase1.setBounds(310,110,400,20);
        gestItem.add(frase1);

        JTextField titemId = new JTextField();
        titemId.setBounds(285,150,150,30);
        gestItem.add(titemId);

        JButton addI = new JButton("Añadir");
        addI.setFont(new Font("Arial",Font.BOLD,18));
        addI.setBounds(515,150,150,30);
        gestItem.add(addI);


        citemid = new JComboBox((Vector)CP.list_item());
        citemid.setBounds(285,250,150,30);
        gestItem.add(citemid);

        //resize tontorron pq aparegui el combobox
        fin.setSize(960,541);
        fin.setSize(960,540);

        JLabel frase2 = new JLabel("Escoje el ID del item que quiera eliminar/modificar");
        frase2.setFont(new Font("Arial", Font.PLAIN,18));
        frase2.setBounds(280,200,600,20);
        gestItem.add(frase2);

        JButton deleteI = new JButton("Eliminar");
        deleteI.setFont(new Font("Arial",Font.BOLD,18));
        deleteI.setBounds(515,250,150,30);
        gestItem.add(deleteI);

        gesTag.setFont(new Font("Arial",Font.BOLD,18));
        gesTag.setBounds(360,325,220,40);
        gestItem.add(gesTag);

        ActionListener addIt = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: añadir");

                if(isNumericI(titemId.getText()) && !CP.exists(Integer.valueOf(titemId.getText()))){
                    ArrayList<String> tags = new ArrayList<>();
                    //for(int i = 0; i < 5; ++i) {

                    tags.add(JOptionPane.showInputDialog("Introducir Tag"));
                    System.out.println(JOptionPane.showInputDialog("Introducir Tag"));
                    //}
                    CP.addItem(Integer.valueOf(titemId.getText()),tags);


                    System.out.println(titemId.getText());
                }

                else JOptionPane.showMessageDialog(gestItem,"No se ha podido añadir correctamente","Error",0);
            }
        };
        addI.addActionListener(addIt);

        ActionListener elimI = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: eliminar");

                if(CP.deleteItem((Integer)citemid.getSelectedItem())){
                    JOptionPane.showMessageDialog(gestItem,"Eliminado correctamente");
                    System.out.println(citemid.getSelectedItem());
                }
                //else if(!CP.valdiItem((Integer)citemid.getSelectedItem())) JOptionPane.showMessageDialog(gestUser,"Item no registrado","Error",0);
                else JOptionPane.showMessageDialog(gestItem,"No se ha podido eliminar correctamente","Error",0);
            }
        };
        deleteI.addActionListener(elimI);

        ActionListener gt = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: gestionar tags");
                id_actual = (Integer)citemid.getSelectedItem();
                panelactual = 9;
                gestItem.setVisible(false);
                panelmain();
            }
        };
        gesTag.addActionListener(gt);


    }

    public  void gestion_tag(){
        JPanel gestTag = new JPanel();
        fin.add(gestTag);
        gestTag.setVisible(true);
        gestTag.setLayout(null);

        JLabel titolU = new JLabel("GESTION DE TAGS");
        titolU.setFont(new Font("Arial",Font.BOLD,30));
        titolU.setBounds(335,30,550,40);
        gestTag.add(titolU);

        JButton backGT = new JButton("Volver");
        backGT.setBounds(20,450,100,30);
        gestTag.add(backGT);

        ActionListener tornarGT = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: tornarGT");
                panelactual = 3;
                gestTag.setVisible(false);
                panelmain();
            }
        };
        backGT.addActionListener(tornarGT);

        JLabel frase1 = new JLabel("Selecciona el TAG quieras modificar");
        frase1.setFont(new Font("Arial", Font.PLAIN,18));
        frase1.setBounds(330,110,400,20);
        gestTag.add(frase1);

        JLabel frase2 = new JLabel("Nuevo valor de la TAG");
        frase2.setFont(new Font("Arial", Font.PLAIN,16));
        frase2.setBounds(285,250,200,20);
        gestTag.add(frase2);

        tagmod.setBounds(285,280,150,30);
        gestTag.add(tagmod);

        JButton modify = new JButton("Modificar");
        modify.setFont(new Font("Arial",Font.BOLD,18));
        modify.setBounds(515,280,150,30);
        gestTag.add(modify);


        TagsItems = new JComboBox((Vector)CP.tag_list());
        TagsItems.setBounds(330,160,300,30);

        gestTag.add(TagsItems);

        //resize tontorron pq aparegui el combobox
        fin.setSize(960,541);
        fin.setSize(960,540);

        JButton deleteT = new JButton("Eliminar");
        deleteT.setFont(new Font("Arial",Font.BOLD,18));
        deleteT.setBounds(405,350,150,30);
        gestTag.add(deleteT);


        ActionListener modT = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: modificar");

                if(CP.modifyTag(id_actual, (String) TagsItems.getSelectedItem(), tagmod.getText())){
                    JOptionPane.showMessageDialog(gestTag,"Modificado correctamente");
                    System.out.println(tagmod.getText());
                }
                else JOptionPane.showMessageDialog(gestTag,"No se ha podido modificar correctamente","Error",0);
            }
        };
        modify.addActionListener(modT);

        ActionListener eliminaT = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: eliminar");

                if(CP.delTag(id_actual, (String) TagsItems.getSelectedItem())){
                    JOptionPane.showMessageDialog(gestTag,"Eliminado correctamente");
                    System.out.println("eliminado");
                }
                else JOptionPane.showMessageDialog(gestTag,"No se ha podido eliminar correctamente","Error",0);
            }
        };
        deleteT.addActionListener(eliminaT);

    }


    private  boolean isNumericI(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private  boolean isNumericF(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }


}