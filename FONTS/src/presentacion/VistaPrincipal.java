package presentacion;

import  javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;


/**
 *
 * @author Oriol Martí Jariod
 */

/** @class VistaPrincipal
 * @brief Implements the views of the application
 */

public class VistaPrincipal {

    //controlador presentacio
    private  ControladorPresentacion CP;

    //components gui
    private  JFrame fin = new JFrame("Recomendation System");


    //recomendacions
    private  JButton busca = new JButton("Buscar");
    private  JButton save = new JButton("Guardar recomendación");
    private  boolean nova_rec;
    private List<Integer> id;
    private List<Float> val;
    private  int nitems;
    private  JCheckBox eval = new JCheckBox("Evaluación");
    private  boolean checkbox = false;

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
    private JComboBox CuserID;
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
    private  JPanel item_rec;
    private JLabel titolIR = new JLabel("RECOMENDACIONES");
    private JButton back_IR = new JButton("Volver");
    private JLabel frase1IR = new JLabel("Lo que te puede gustar...");

    //gestion user
    private JPanel gestUser = new JPanel();
    private JLabel titolU = new JLabel("GESTION DE USUARIO");
    private JButton backGU = new JButton("Volver");
    private JLabel frase1GU = new JLabel("Escriba el ID del usuario que quiera añadir");
    private JLabel frase2GU = new JLabel("Escoje el ID del usuario que quiera eliminar/modificar las ratings");

    //gestion ratings
    private JPanel gestRate = new JPanel();
    private JLabel titolGR = new JLabel("GESTION DE RATINGS");
    private JButton backGR = new JButton("Volver");
    private  JLabel frase1GR = new JLabel("Selecciona el ID del item que quiera gestionar");
    private JLabel frase2GR = new JLabel("Nuevo valor de la rate");

    //gestion item
    private JPanel gestItem = new JPanel();
    private JLabel titolGI = new JLabel("GESTION DE ITEM");
    private JButton backGI = new JButton("Volver");
    private JLabel frase1GI = new JLabel("Escriba el ID del item que quiera añadir");
    private JTextField titemId = new JTextField();
    private JButton addI = new JButton("Añadir");
    private JLabel frase2GI = new JLabel("Escoje el ID del item que quiera eliminar/modificar");
    private JButton deleteI = new JButton("Eliminar");

    //gestion tag
    private JPanel gestTag = new JPanel();
    private JLabel titolGT = new JLabel("GESTION DE TAGS");
    private JButton backGT = new JButton("Volver");
    private JLabel frase1GT = new JLabel("Selecciona el TAG quieras modificar");
    private JLabel frase2GT = new JLabel("Nuevo valor de la TAG");
    private JButton modify = new JButton("Modificar");
    private JButton deleteT = new JButton("Eliminar");


    //atributs
    private  int panelactual = 0;
    private  String path_csv;
    private List<String> rec_sv;

    /** @brief Default builder.
     *
     * \pre <em>true</em>
     * \post It creates a <em>VistaPrincipal</em> object with its attribute <em>ControladorPresentacion</em>.
     *
     */
    public VistaPrincipal(ControladorPresentacion cp) {
        CP = cp;
        inicializaComponentes();
    }

    /**
     * @brief Initialize components
     *
     * \pre true
     * \post Initialize variables and calls other functions
     */
    private void inicializaComponentes() {
        hacerVisible();
        asignar_listenersComponentes();

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

        algoritme.addItem("Collaborative filtering");
        algoritme.addItem("Content based filtering");
        algoritme.addItem("Hybrid algorithm");

    }

    /**
     * @brief Make visible teh frame
     *
     * \pre true
     * \post Sets the frame
     */
    public void hacerVisible() {
        fin.pack();
        fin.setVisible(true);
        fin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        basic_frame();
    }

    /**
     * @brief Basic info of the frame
     *
     * \pre true
     * \post Sets basic parameters of the frame
     */
    public void basic_frame(){
        fin.setTitle("Recomendation System");
        fin.setResizable(false);

        fin.setSize(960,540);
        fin.setLocationRelativeTo(null);
        panelmain();
    }

    /**
     * @brief Actions performer events
     *
     * \pre true
     * \post Sets the actions of every ActionEvent
     */

    public void actionPerformed_csvs(ActionEvent e) {
        //browser ficheros/dyrectory
        File file = new File("DATA");

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
    public void actionPerformed_startB(ActionEvent e) {
        path_csv =  (fc.getSelectedFile().getPath() + "\\");
        System.out.println("CSV selecionado: " + path_csv);
        CP.inicializar(path_csv);

        System.out.println("boton pulsado: start");
        panelactual = 1;
        start.setVisible(false);
        panelmain();
    }
    public void actionPerformed_backM(ActionEvent e) {
        System.out.println("boton pulsado: volver");
        panelactual = 0;
        menu.setVisible(false);
        panelmain();
    }
    public void actionPerformed_gestuser(ActionEvent e) {
        System.out.println("boton pulsado: gestion user");
        panelactual = 2;
        menu.setVisible(false);
        panelmain();
    }
    public void actionPerformed_gestitem(ActionEvent e) {
        System.out.println("boton pulsado: gestion item");
        panelactual = 3;
        menu.setVisible(false);
        panelmain();
    }
    public void actionPerformed_recomanacio(ActionEvent e) {
        System.out.println("boton pulsado: recomanacio");
        panelactual = 4;
        menu.setVisible(false);
        panelmain();
    }
    public void actionPerformed_backRm(ActionEvent e) {
        System.out.println("boton pulsado: tornarR");
        panelactual = 1;
        menR.setVisible(false);
        panelmain();
    }
    public void actionPerformed_nuevarec(ActionEvent e) {
        if(eval.isSelected()) checkbox = true;
        else checkbox = false;
        System.out.println("boton pulsado: nuevarec");
        panelactual = 5;
        menR.setVisible(false);
        panelmain();
    }
    public void actionPerformed_savedrec(ActionEvent e) {
        System.out.println("boton pulsado: cargar");

        List<Integer> ids = CP.get_IDuser_rec();
        List<Integer> alg = CP.get_alg_rec();
        List<String> dat = CP.get_dates_rec();
        rec_sv = new ArrayList<>(ids.size());
        for(int i = 0; i < ids.size(); i++) {
            rec_sv.set(i,"Id User: " + ids.get(i) + " Algoritme: ");
            if(alg.get(i) == 0) rec_sv.set(i,rec_sv.get(i) + "CF " + dat.get(i));
            else if(alg.get(i) == 1) rec_sv.set(i,rec_sv.get(i) + "CBF " + dat.get(i));
            else rec_sv.set(i,rec_sv.get(i) + "H " + dat.get(i));

        }

        panelactual = 6;
        menR.setVisible(false);
        panelmain();
    }
    public void actionPerformed_backR(ActionEvent e) {
        System.out.println("boton pulsado: tornarR");
        panelactual = 4;
        recomana.setVisible(false);
        panelmain();
    }
    public void actionPerformed_busca(ActionEvent e) {
        String userid = (String) CuserID.getSelectedItem();
        int uid = Integer.valueOf(userid);
        System.out.println("user selecionado: " + userid);
        String algorithm = (String) algoritme.getSelectedItem();
        System.out.println("algoritmo selecionado: " + algorithm);

        nova_rec = true;
        nitems = Integer.valueOf((String) nit.getSelectedItem());

        System.out.println(nitems);

        if(algorithm == "Collaborative filtering") CP.recommendCF(nitems,uid,checkbox);
        //else if(algorithm == "Content based filtering") CP.recommendCBF(nitems,uid,checkbox);
        //else CP.recommendH(nitems,uid,checkbox);
        //id = CP.list_itemREC();
        //val = CP.list_valREC();
        System.out.println("boton pulsado: buscar");
        panelactual = 7;
        recomana.setVisible(false);
        panelmain();
    }
    public void actionPerformed_back_carRm(ActionEvent e) {
        System.out.println("boton pulsado: tornar");
        panelactual = 4;
        cargarR.setVisible(false);
        panelmain();
    }
    public void actionPerformed_save(ActionEvent e) {
        System.out.println("boton pulsado: guardar");
        if(CP.saveRecomendation()){
            JOptionPane.showMessageDialog(item_rec,"Guardado correctamente");
        }
    }
    public void actionPerformed_backGU(ActionEvent e) {
        System.out.println("boton pulsado: tornarGU");
        panelactual = 1;
        gestUser.setVisible(false);
        panelmain();
    }
    public void actionPerformed_addB(ActionEvent e) {
        System.out.println("boton pulsado: añadir");

        if(isNumericI(tUserId.getText()) && CP.addUser(Integer.valueOf(tUserId.getText()))){
            JOptionPane.showMessageDialog(gestUser,"Añadido correctamente");
            System.out.println(tUserId.getText());
        }
        else if(isNumericI(tUserId.getText())) JOptionPane.showMessageDialog(gestUser,"Usuario ya registrado","Error",0);
        else JOptionPane.showMessageDialog(gestUser,"No se ha podido añadir correctamente","Error",0);
    }
    public void actionPerformed_deleteB(ActionEvent e) {
        System.out.println("boton pulsado: eliminar");

        if(CP.deleteUser((Integer)cUserid.getSelectedItem())){
            JOptionPane.showMessageDialog(gestUser,"Eliminado correctamente");
            System.out.println(tUserId.getText());
        }
        else JOptionPane.showMessageDialog(gestUser,"No se ha podido eliminar correctamente","Error",0);
    }
    public void actionPerformed_gRate(ActionEvent e) {
            System.out.println("boton pulsado: gestionar ratings");
            id_actual_user = (Integer)cUserid.getSelectedItem();
            panelactual = 8;
            gestUser.setVisible(false);
            panelmain();

    }
    public void actionPerformed_backGR(ActionEvent e) {
        System.out.println("boton pulsado: tornarGR");
        panelactual = 2;
        gestRate.setVisible(false);
        panelmain();
    }
    public void actionPerformed_addBR(ActionEvent e) {
        System.out.println("boton pulsado: añadir/modificar");

        if(isNumericF(tRate.getText()) && CP.addRating(id_actual_user,Integer.valueOf((Integer) cUserid.getSelectedItem()),Float.valueOf(tRate.getText()))){
            JOptionPane.showMessageDialog(gestRate,"Añadido/modificado correctamente");
            System.out.println(tRate.getText());
        }
        else JOptionPane.showMessageDialog(gestRate,"No se ha podido añadir/modificar correctamente","Error",0);
    }
    public void actionPerformed_deleteBR(ActionEvent e) {
        System.out.println("boton pulsado: eliminar");

        if(CP.deleteRating(id_actual_user,(Integer)cItemId.getSelectedItem())){
            JOptionPane.showMessageDialog(gestRate,"Eliminado correctamente");
            System.out.println("eliminado");
        }
        else JOptionPane.showMessageDialog(gestRate,"No se ha podido eliminar correctamente","Error",0);
    }
    public void actionPerformed_backGI(ActionEvent e) {
        System.out.println("boton pulsado: tornarGI");
        panelactual = 1;
        gestItem.setVisible(false);
        panelmain();
    }
    public void actionPerformed_addI(ActionEvent e) {
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
    public void actionPerformed_deleteI(ActionEvent e) {
        System.out.println("boton pulsado: eliminar");

        if(CP.deleteItem((Integer)citemid.getSelectedItem())){
            JOptionPane.showMessageDialog(gestItem,"Eliminado correctamente");
            System.out.println(citemid.getSelectedItem());
        }
        //else if(!CP.valdiItem((Integer)citemid.getSelectedItem())) JOptionPane.showMessageDialog(gestUser,"Item no registrado","Error",0);
        else JOptionPane.showMessageDialog(gestItem,"No se ha podido eliminar correctamente","Error",0);
    }
    public void actionPerformed_gesTag(ActionEvent e) {
        System.out.println("boton pulsado: gestionar tags");
        id_actual = (Integer)citemid.getSelectedItem();
        panelactual = 9;
        gestItem.setVisible(false);
        panelmain();
    }
    public void actionPerformed_backGT(ActionEvent e) {
        System.out.println("boton pulsado: tornarGT");
        panelactual = 3;
        gestTag.setVisible(false);
        panelmain();
    }
    public void actionPerformed_backIR(ActionEvent e) {
        System.out.println("boton pulsado: tornar");
        if(nova_rec) panelactual = 5;
        else panelactual = 6;
        item_rec.setVisible(false);
        panelmain();
    }
    public void actionPerformed_modify(ActionEvent e) {
        System.out.println("boton pulsado: modificar");

        if(CP.modifyTag(id_actual, (String) TagsItems.getSelectedItem(), tagmod.getText())){
            JOptionPane.showMessageDialog(gestTag,"Modificado correctamente");
            System.out.println(tagmod.getText());
        }
        else JOptionPane.showMessageDialog(gestTag,"No se ha podido modificar correctamente","Error",0);
    }
    public void actionPerformed_deleteT(ActionEvent e) {
        System.out.println("boton pulsado: eliminar");

        if(CP.delTag(id_actual, (String) TagsItems.getSelectedItem())){
        JOptionPane.showMessageDialog(gestTag,"Eliminado correctamente");
        System.out.println("eliminado");
        }
        else JOptionPane.showMessageDialog(gestTag,"No se ha podido eliminar correctamente","Error",0);
    }
    public void actionPerformed_openB(ActionEvent e) {
        String path_rec = (String) combo_rec.getSelectedItem();
        System.out.println("recomendacion guardada selecionado: " + path_rec);
        //id = CP.list_itemSavedREC(path_rec);
        //val = CP.list_valSavedREC(path_rec);
        System.out.println("boton pulsado: open");
        nova_rec = false;
        panelactual = 7;
        cargarR.setVisible(false);
        panelmain();
    }

    //listeners
    /**
     * @brief Assigns the listeners
     *
     * \pre true
     * \post Assigns every action event with his component
     */
    private void asignar_listenersComponentes() {
        //botones

        deleteI.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_deleteI(event);
                    }
                });

        deleteT.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_deleteT(event);
                    }
                });

        modify.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_modify(event);
                    }
                });

        backGT.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_backGT(event);
                    }
                });

        addI.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_addI(event);
                    }
                });

        backGI.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_backGI(event);
                    }
                });

        backGR.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_backGR(event);
                    }
                });

        backGU.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_backGU(event);
                    }
                });

        busca.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_busca(event);
                    }
                });

        save.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_save(event);
                    }
                });

        addB.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_addB(event);
                    }
                });

        addBR.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_addBR(event);
                    }
                });

        deleteB.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_deleteB(event);
                    }
                });

        deleteBR.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_deleteBR(event);
                    }
                });

        gRate.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_gRate(event);
                    }
                });

        gesTag.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_addBR(event);
                    }
                });

        csvs.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_csvs(event);
                    }
                });

        startB.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_startB(event);
                    }
                });


        backM.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_backM(event);
                    }
                });
        gestuser.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_gestuser(event);
                    }
                });
        gestitem.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_gestitem(event);
                    }
                });
        recomanacio.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_recomanacio(event);
                    }
                });

        backRm.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_backRm(event);
                    }
                });
        nuevarec.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_nuevarec(event);
                    }
                });
        savedrec.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_savedrec(event);
                    }
                });

        backR.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_backR(event);
                    }
                });

        back_carRm.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_back_carRm(event);
                    }
                });

        openB.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_openB(event);
                    }
                });

        back_IR.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_backIR(event);
                    }
                });

    }

    /**
     * @brief Main panels
     *
     * \pre true
     * \post Sets the actual panel
     */
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

    /**
     * @brief First panel
     *
     * \pre true
     * \post Sets the csv to work with
     */
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

        /*ActionListener selecciona = new ActionListener() {
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
        csvs.addActionListener(selecciona);*/





        startB.setBounds(290,380,350,40);
        startB.setFont(new Font("Arial", Font.BOLD, 20));
        start.add(startB);

        /*ActionListener comencem = new ActionListener() {
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
        startB.addActionListener(comencem);*/


    }

    /**
     * @brief Menu panel
     *
     * \pre true
     * \post true
     */
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


        /*ActionListener tornarM = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: volver");
                panelactual = 0;
                menu.setVisible(false);
                panelmain();
            }
        };
        backM.addActionListener(tornarM);*/

        /*ActionListener gestionar_user = new ActionListener() {
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
        recomanacio.addActionListener(rec);*/
    }

    /**
     * @brief Menu recommendation panel
     *
     * \pre true
     * \post true
     */
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

        /*ActionListener tornarRm = new ActionListener() {
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
        savedrec.addActionListener(cargar);*/


    }

    /**
     * @brief New recommendation panel
     *
     * \pre true
     * \post true
     */
    public  void recomanacio(){

        fin.add(recomana);
        recomana.setVisible(true);
        recomana.setLayout(null);


        titolRecomanacio.setFont(new Font("Arial",Font.BOLD,30));
        titolRecomanacio.setBounds(310,30,550,40);
        recomana.add(titolRecomanacio);


        backR.setBounds(20,450,100,30);

        recomana.add(backR);

        /*ActionListener tornarR = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: tornarR");
                panelactual = 4;
                recomana.setVisible(false);
                panelmain();
            }
        };
        backR.addActionListener(tornarR);*/



        frase1Recomanacio.setFont(new Font("Arial",Font.PLAIN,22));
        frase1Recomanacio.setBounds(350,110,400,20);
        recomana.add(frase1Recomanacio);





        frase2Recomanacio.setFont(new Font("Arial",Font.PLAIN,18));
        frase2Recomanacio.setBounds(281,180,150,25);
        recomana.add(frase2Recomanacio);



        CuserID = new JComboBox((Vector) CP.list_user());
        CuserID.setBounds(470,180,250,25);
       // CuserID.addItem("2848721");
       // CuserID.addItem("8466126");

        recomana.add(CuserID);


        frase3Recomanacio.setFont(new Font("Arial",Font.PLAIN,18));
        frase3Recomanacio.setBounds(273,240,150,25);
        recomana.add(frase3Recomanacio);



        algoritme.setBounds(470,240,250,25);
        /*
        algoritme.addItem("Collaborative filtering");
        algoritme.addItem("Content based filtering");
        algoritme.addItem("Hybrid algorithm");*/

        recomana.add(algoritme);

        //resize tontorron pq aparegui el combobox
        fin.setSize(960,541);
        fin.setSize(960,540);


        frase4Recomanacio.setFont(new Font("Arial",Font.PLAIN,18));
        frase4Recomanacio.setBounds(145,300,300,25);
        recomana.add(frase4Recomanacio);


        nit.setBounds(470,300,250,25);
        /*
        nit.addItem("1");
        nit.addItem("2");
        nit.addItem("3");
        nit.addItem("4");
        nit.addItem("5");
        nit.addItem("6");
        nit.addItem("7");
        nit.addItem("8");
        nit.addItem("9");
        nit.addItem("10");*/
        recomana.add(nit);

        busca.setFont(new Font("Arial",Font.BOLD,20));
        busca.setBounds(310,380,300,40);
        recomana.add(busca);


        /*ActionListener search = new ActionListener() {
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

                if(algorithm == "Collaborative filtering") CP.recommendCF(nitems,uid,checkbox);
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
        busca.addActionListener(search);*/



    }

    /**
     * @brief Load recommendation panel
     *
     * \pre true
     * \post true
     */
    public  void cargar_rec(){

        fin.add(cargarR);
        cargarR.setVisible(true);
        cargarR.setLayout(null);


        titolCargaR.setFont(new Font("Arial",Font.BOLD,30));
        titolCargaR.setBounds(310,30,550,40);
        cargarR.add(titolCargaR);


        back_carRm.setBounds(20,450,100,30);
        cargarR.add(back_carRm);

        /*ActionListener cargar_rec_back = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: tornar");
                panelactual = 4;
                cargarR.setVisible(false);
                panelmain();
            }
        };
        back_carRm.addActionListener(cargar_rec_back);*/



        frase1CR.setFont(new Font("Arial", Font.PLAIN,22));
        frase1CR.setBounds(300,130,400,25);
        cargarR.add(frase1CR);






        combo_rec = new JComboBox((Vector) rec_sv);

        combo_rec.setBounds(230,190,500,25);

        cargarR.add(combo_rec);

        //resize tontorron pq aparegui el combobox
        fin.setSize(960,541);
        fin.setSize(960,540);




        openB.setBounds(285,380,350,40);
        openB.setFont(new Font("Arial", Font.BOLD, 20));
        cargarR.add(openB);

        /*ActionListener comencem = new ActionListener() {
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
        openB.addActionListener(comencem);*/
    }

    /**
     * @brief Items recommended panel
     *
     * \pre true
     * \post true
     */
    public  void rec_items(){
        item_rec = new JPanel();
        fin.add(item_rec);
        item_rec.setVisible(true);
        item_rec.setLayout(null);


        titolIR.setFont(new Font("Arial",Font.BOLD,30));
        titolIR.setBounds(310,30,550,40);
        item_rec.add(titolIR);



        back_IR.setBounds(20,450,100,30);
        item_rec.add(back_IR);

        /*ActionListener cargar_rec_back = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: tornar");
                if(nova_rec) panelactual = 5;
                else panelactual = 6;
                item_rec.setVisible(false);
                panelmain();
            }
        };

        back_IR.addActionListener(cargar_rec_back);*/

        if (nova_rec) {
            save.setBounds(350,430,220,35);
            save.setFont(new Font("Arial",Font.BOLD,16));
            item_rec.add(save);

            /*ActionListener guardar = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("boton pulsado: guardar");
                    if(CP.saveRecomendation()){
                        JOptionPane.showMessageDialog(item_rec,"Guardado correctamente");
                    }

                }
            };
            save.addActionListener(guardar);*/
        }



        frase1IR.setFont(new Font("Arial",Font.PLAIN,22));
        frase1IR.setBounds(350,80,400,25);
        item_rec.add(frase1IR);

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
                itemid.add(new JLabel("ItemId: " + id.get(5*i+j)));
                itemid.get(5*i+j).setBounds(150 +150*j,200+150*i,60,20);
                item_rec.add(itemid.get(5*i+j));
                val.add(new JLabel("Val: " + val.get(5*i+j)));
                val.get(5*i+j).setBounds(150 +150*j,220+150*i,60,20);
                item_rec.add(val.get(5*i+j));


            }
        }

    }

    /**
     * @brief Gestion user panel
     *
     * \pre true
     * \post true
     */
    public  void gestion_user(){

        fin.add(gestUser);
        gestUser.setVisible(true);
        gestUser.setLayout(null);


        titolU.setFont(new Font("Arial",Font.BOLD,30));
        titolU.setBounds(310,30,550,40);
        gestUser.add(titolU);


        backGU.setBounds(20,450,100,30);

        gestUser.add(backGU);

        /*ActionListener tornarGU = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: tornarGU");
                panelactual = 1;
                gestUser.setVisible(false);
                panelmain();
            }
        };
        backGU.addActionListener(tornarGU);*/


        frase1GU.setFont(new Font("Arial", Font.PLAIN,18));
        frase1GU.setBounds(310,110,400,20);
        gestUser.add(frase1GU);


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


        frase2GU.setFont(new Font("Arial", Font.PLAIN,18));
        frase2GU.setBounds(220,200,600,20);
        gestUser.add(frase2GU);

        deleteB.setFont(new Font("Arial",Font.BOLD,18));
        deleteB.setBounds(515,250,150,30);
        gestUser.add(deleteB);

        gRate.setFont(new Font("Arial",Font.BOLD,18));
        gRate.setBounds(360,325,220,40);
        gestUser.add(gRate);

        /*ActionListener afegirU = new ActionListener() {
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
        gRate.addActionListener(ratings);*/


    }

    /**
     * @brief Gestion ratings panel
     *
     * \pre true
     * \post true
     */
    public  void gestion_ratings(){

        fin.add(gestRate);
        gestRate.setVisible(true);
        gestRate.setLayout(null);


        titolGR.setFont(new Font("Arial",Font.BOLD,30));
        titolGR.setBounds(310,30,550,40);
        gestRate.add(titolGR);


        backGR.setBounds(20,450,100,30);
        gestRate.add(backGR);
/*
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
*/

        frase1GR.setFont(new Font("Arial", Font.PLAIN,18));
        frase1GR.setBounds(300,110,400,20);
        gestRate.add(frase1GR);


        frase2GR.setFont(new Font("Arial", Font.PLAIN,16));
        frase2GR.setBounds(285,250,200,20);
        gestRate.add(frase2GR);

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

/*
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
*/

    }

    /**
     * @brief Gestion items panel
     *
     * \pre true
     * \post true
     */
    public  void gestion_item(){

        fin.add(gestItem);
        gestItem.setVisible(true);
        gestItem.setLayout(null);


        titolGI.setFont(new Font("Arial",Font.BOLD,30));
        titolGI.setBounds(340,30,550,40);
        gestItem.add(titolGI);


        backGI.setBounds(20,450,100,30);

        gestItem.add(backGI);
/*
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

*/
        frase1GI.setFont(new Font("Arial", Font.PLAIN,18));
        frase1GI.setBounds(310,110,400,20);
        gestItem.add(frase1GI);


        titemId.setBounds(285,150,150,30);
        gestItem.add(titemId);


        addI.setFont(new Font("Arial",Font.BOLD,18));
        addI.setBounds(515,150,150,30);
        gestItem.add(addI);


        citemid = new JComboBox((Vector)CP.list_item());
        citemid.setBounds(285,250,150,30);
        gestItem.add(citemid);

        //resize tontorron pq aparegui el combobox
        fin.setSize(960,541);
        fin.setSize(960,540);


        frase2GI.setFont(new Font("Arial", Font.PLAIN,18));
        frase2GI.setBounds(280,200,600,20);
        gestItem.add(frase2GI);


        deleteI.setFont(new Font("Arial",Font.BOLD,18));
        deleteI.setBounds(515,250,150,30);
        gestItem.add(deleteI);

        gesTag.setFont(new Font("Arial",Font.BOLD,18));
        gesTag.setBounds(360,325,220,40);
        gestItem.add(gesTag);

      /*  ActionListener addIt = new ActionListener() {
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

*/
    }

    /**
     * @brief Gestion tags panel
     *
     * \pre true
     * \post true
     */
    public  void gestion_tag(){

        fin.add(gestTag);
        gestTag.setVisible(true);
        gestTag.setLayout(null);


        titolGT.setFont(new Font("Arial",Font.BOLD,30));
        titolGT.setBounds(335,30,550,40);
        gestTag.add(titolGT);


        backGT.setBounds(20,450,100,30);
        gestTag.add(backGT);
/*
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

*/
        frase1GT.setFont(new Font("Arial", Font.PLAIN,18));
        frase1GT.setBounds(330,110,400,20);
        gestTag.add(frase1GT);


        frase2GT.setFont(new Font("Arial", Font.PLAIN,16));
        frase2GT.setBounds(285,250,200,20);
        gestTag.add(frase2GT);

        tagmod.setBounds(285,280,150,30);
        gestTag.add(tagmod);


        modify.setFont(new Font("Arial",Font.BOLD,18));
        modify.setBounds(515,280,150,30);
        gestTag.add(modify);


        TagsItems = new JComboBox((Vector)CP.tag_list());
        TagsItems.setBounds(330,160,300,30);

        gestTag.add(TagsItems);

        //resize tontorron pq aparegui el combobox
        fin.setSize(960,541);
        fin.setSize(960,540);


        deleteT.setFont(new Font("Arial",Font.BOLD,18));
        deleteT.setBounds(405,350,150,30);
        gestTag.add(deleteT);
/*

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
*/
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