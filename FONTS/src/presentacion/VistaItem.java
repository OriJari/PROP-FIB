package presentacion;

;

import  javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class VistaItem extends VistaPrincipal {

    private static JButton gesTag = new JButton("Gestionar Tags");
    private static JComboBox TagsItems = new JComboBox();
    private static JComboBox citemid = new JComboBox();
    private static JTextField tagmod = new JTextField();
    private static int id_actual;
    public VistaItem(){}

    public static void gestion_item(){
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

    public static void gestion_tag(){
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

    private static boolean isNumericI(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }


}
