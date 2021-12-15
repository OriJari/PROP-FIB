package dominio.controladores;

import dominio.clases.algorithm.collaborativefiltering.CollaborativeFiltering;
import dominio.clases.algorithm.contentbasedflitering.K_NN;
//import persistencia.ControladorPersistencia;

import java.util.List;
import java.util.Map;

public class ControladorDominio {
 //   ControladorPersistencia CP;
    CollaborativeFiltering CF;
    K_NN KNN;

   /* private Map<Integer, Map<Integer, Float>> transformerMapRate(List<String> l){

    }

    public ControladorDominio(){
        //Cridar a Persistencia a que porti tots els mapes (En format List<String> i transformar-ho a maps)
        CP = new ControladorPersistencia();

        List<String> mapaS = CP.getMapRate();
        Map<Integer, Map<Integer, Float>> mapRate= tranformerMapRate(mapaS);

        CF = new CollaborativeFiltering(mapRate);
        KNN = new K_NN(mapRate);
    };


    public void addUser(int ID){

    }

    public void deleteUser(int ID){

    }

    public void addItem(int ID, List<String> tags){
        CP.addItem(ID, tags);
    }

    public void deleteItem(int ID, List<String> tags){
        CP.deleteItem(ID, tags);
    }

    public void addRating(int IDuser, int IDitem, float valor){
        CP.addRating(IDuser, IDitem, valor);
    }

    public void deleteRating(int IDuser, int IDitem, float valor){
        CP.deleteRating(IDuser, IDitem, valor);
    }

    public void modifyTag(int IDitem, String tag){
        CP.modifyTag(IDitem, tag);
    }

    public void delTag(int IDitem, String tag){
        CP.delTag(IDitem, tag);
    }

     public void saveRecomendation(boolean g){
        CP.saveRecomendation(g);
    }

    public void evaluateRecomendation(boolean e){
        CP.evaluateRecomendation(e);
    }

    public void recommendCF(boolean valoration, int k){

    }

    public void recommendCBF(boolean valoration, int k){

    }

    public void recommendH(boolean valoration, int k){

    }*/
}
