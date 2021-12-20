package presentacion;

import  javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;

public class VistaItem extends VistaPrincipal {


    private static JButton addI = new JButton("Añadir");
    private static JButton gesTag = new JButton("Gestionar Tags");
    private static JButton deleteI = new JButton("Eliminar");
    private static JButton deleteT = new JButton("Eliminar");


    private static JComboBox TagsItems = new JComboBox();
    private static JComboBox citemid = new JComboBox();
    private static JTextField tagmod = new JTextField();
    private static JTextField titemId = new JTextField();
    private static JButton modify = new JButton("Modificar");




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


        titemId.setBounds(285,150,150,30);
        gestItem.add(titemId);

        addI.setFont(new Font("Arial",Font.BOLD,18));
        addI.setBounds(515,150,150,30);
        gestItem.add(addI);


        File file = new File("DATA");

        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        System.out.println(Arrays.toString(directories));

        citemid = new JComboBox(directories);
        citemid.setBounds(285,250,150,30);
        gestItem.add(citemid);

        //resize tontorron pq aparegui el combobox
        fin.setSize(960,541);
        fin.setSize(960,540);

        JLabel frase2 = new JLabel("Escoje el ID del item que quiera eliminar/modificar");
        frase2.setFont(new Font("Arial", Font.PLAIN,18));
        frase2.setBounds(280,200,600,20);
        gestItem.add(frase2);

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

                if(isNumericI(titemId.getText())){
                    //ArrayList<String> tags;
                    //for(int i = 0; i < 5; ++i) {

                        //tags.put(JOptionPane.showInputDialog("Introducir Tag");)
                        System.out.println(JOptionPane.showInputDialog("Introducir Tag"));
                    //}
                    /* CP.addItem(Integer.valueOf(tUserId.getText()))*/


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

                if(true/*CP.valdiItem((Integer)citemid.getSelectedItem()) && CP.deleteItem((Integer)citemid.getSelectedItem())*/){
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
                if(true /*CP.valdItem((Integer)citemid.getSelectedItem())*/){

                    panelactual = 9;
                    gestItem.setVisible(false);
                    panelmain();
                }
                //else if(!CP.valdiItem((Integer)citemid.getSelectedItem())) JOptionPane.showMessageDialog(gestUser,"Item no registrado","Error",0);

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

        modify.setFont(new Font("Arial",Font.BOLD,18));
        modify.setBounds(515,280,150,30);
        gestTag.add(modify);


        File file = new File("DATA");

        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        System.out.println(Arrays.toString(directories));

        TagsItems = new JComboBox(directories);
        TagsItems.setBounds(330,160,300,30);

        gestTag.add(TagsItems);

        //resize tontorron pq aparegui el combobox
        fin.setSize(960,541);
        fin.setSize(960,540);

        deleteT.setFont(new Font("Arial",Font.BOLD,18));
        deleteT.setBounds(405,350,150,30);
        gestTag.add(deleteT);


        ActionListener modT = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: modificar");

                if(true/*  CP.modifyTag(tagmod.getText())*/){
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

                if(true/*CP.deleteTag(TagItems.getSelectedItem())*/){
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
