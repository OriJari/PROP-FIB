package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;



public class VistaUser extends VistaPrincipal {

    private static JTextField tUserId = new JTextField();
    private static JButton addB = new JButton("Añadir");
    private static JButton addBR = new JButton("Añadir/Modificar");
    private static JButton deleteB = new JButton("Eliminar") ;
    private static JButton deleteBR = new JButton("Eliminar") ;
    private static JButton gRate = new JButton("Gestionar Ratings");
    private static JComboBox cItemId;
    private static JComboBox cUserid;
    private static JTextField tRate = new JTextField();

    public VistaUser(){}

    public static void gestion_user(){
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


        File file = new File("DATA");

        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        System.out.println(Arrays.toString(directories));

        cUserid = new JComboBox(directories);

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

                if(isNumericI(tUserId.getText())/* && CP.addUser(Integer.valueOf(tUserId.getText()))*/){
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

                if(true/*CP.valdiUser((Integer)cUserid.getSelectedItem()) && CP.deleteUser((Integer)cUserid.getSelectedItem())*/){
                    JOptionPane.showMessageDialog(gestUser,"Eliminado correctamente");
                    System.out.println(tUserId.getText());
                }
                //else if(!CP.valdiUser((Integer)cUserid.getSelectedItem())) JOptionPane.showMessageDialog(gestUser,"Usuario no registrado","Error",0);
                else JOptionPane.showMessageDialog(gestUser,"No se ha podido eliminar correctamente","Error",0);
            }
        };
        deleteB.addActionListener(eliminarU);

        ActionListener ratings = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton pulsado: gestionar ratings");
                if(true /*CP.valdiUser((Integer)cUserid.getSelectedItem())*/){

                    panelactual = 8;
                    gestUser.setVisible(false);
                    panelmain();
                }
                //else if(!CP.valdiUser((Integer)cUserid.getSelectedItem())) JOptionPane.showMessageDialog(gestUser,"Usuario no registrado","Error",0);

            }
        };
        gRate.addActionListener(ratings);


    }

    public static void gestion_ratings(){
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


        File file = new File("DATA");

        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        System.out.println(Arrays.toString(directories));

        cItemId = new JComboBox(directories);
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

                if(isNumericF(tRate.getText())/* && CP.addRating(Integer.valueOf(cUserid.getSelectedItem()),Float.valueOf(tRate.getText()))*/){
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

                if(true/*CP.valdiItem((Integer)cItemId.getSelectedItem()) && CP.deleteRating((Integer)cItemId.getSelectedItem())*/){
                    JOptionPane.showMessageDialog(gestRate,"Eliminado correctamente");
                    System.out.println("eliminado");
                }
                else JOptionPane.showMessageDialog(gestRate,"No se ha podido eliminar correctamente","Error",0);
            }
        };
        deleteBR.addActionListener(eli);


    }

    private static boolean isNumericI(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private static boolean isNumericF(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}

