package presentacion;

//import dominio.controladores.ControladorDominio;

import dominio.controladores.ControladorDominio;

import java.io.IOException;
import java.util.List;
import java.util.*;

public class ControladorPresentacion {
    private ControladorDominio CD;
    private static VistaPrincipal v;




    public void run(){

        crearVista();
        v.hacerVisible();

    }

    public ControladorPresentacion(){
        CD = new ControladorDominio();
    }

    public  void crearVista(){
        v = new VistaPrincipal(this);
    }
    public void inicializar(String path){
        CD.inicializar(path);
    }


    public List<Integer> list_user(){
        return CD.list_user();
        //retorna una llista amb tots els id users
    }

    public List<Integer> list_item(){
        return CD.list_item();
        //retorna una llista amb tots els id items
    }


    public boolean addUser(int ID){
        return CD.addUser(ID);
    }


    public boolean deleteUser(int ID){
        return CD.delUser(ID);
    }


/*
     public boolean validItem(int ID){
        CD.validItem(ID);
        //retorna true si l'item exsiteix

    }



    public void addItem(int ID, List<String> tags){
        CD.addItem(ID, tags);
    }

     public boolean deleteItem(int ID){
        CD.deleteItem(ID);
    }

*/
    public boolean addRating(int ID_User,int IDitem, float valor){
        return CD.addRating(ID_User, IDitem, valor);
    }

    public boolean deleteRating(int ID_actual,int  IDitem){
        return CD.delRating(ID_actual, IDitem);
    }
/*
    public boolean modifyTag(int IDitem, String tag){
        CD.modifyTag(IDitem, tag);
    }

    public boolean delTag(int IDitem, String tag){
        CD.delTag(IDitem, tag);
    }

    public boolean saveRecomendation(){
        CD.saveRecomendation();
    }

    public boolean evaluateRecomendation(boolean e){
        CD.evaluateRecomendation(e);
    }

    public void CSVescollit(String s){
        CD.CSVescollit(s);
    }

    public void recommendCF(boolean valoration, int k){
        CD.recommendCF(valoration, k);
    }

    public void recommendCBF(boolean valoration, int k){
        CD.recommendCBF(valoration, k);
    }

    public void recommendH(boolean valoration, int k){
        CD.recommendH(valoration, k);
    }


    */
}
