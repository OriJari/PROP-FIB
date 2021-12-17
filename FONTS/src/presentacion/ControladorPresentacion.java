package presentacion;

//import dominio.controladores.ControladorDominio;

import dominio.controladores.ControladorDominio;

import java.util.List;
import java.util.*;

public class ControladorPresentacion {
    private ControladorDominio CD;
    private static VistaPrincipal v = new VistaPrincipal();



    public static void main(String[] args){
        v.hacerVisible();

    }

    public ControladorPresentacion(){
        CD = new ControladorDominio();
    }

    public void inicializar(String path){
        CD.inicializar(path);
    }

    public boolean addUser(int ID){
        return CD.addUser(ID);
    }

    /*

    public boolean deleteUser(int ID){
        CD.deleteUser(ID);
    }

    public boolean addItem(int ID, List<String> tags){
        CD.addItem(ID, tags);
    }

     public boolean deleteItem(int ID, List<String> tags){
        CD.deleteItem(ID, tags);
    }

    public boolean addRating(int IDitem, float valor){
        CD.addRating(ID_actual, IDitem, valor);
    }

    public boolean deleteRating(int IDitem, float valor){
        CD.deleteRating(ID_actual, IDitem, valor);
    }

    public boolean modifyTag(int IDitem, String tag){
        CD.modifyTag(IDitem, tag);
    }

    public boolean delTag(int IDitem, String tag){
        CD.delTag(IDitem, tag);
    }

    public boolean saveRecomendation(boolean g){
        CD.saveRecomendation(g);
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
