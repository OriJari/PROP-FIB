/*package dominio.controladores;

import dominio.clases.algorithm.collaborativefiltering.CollaborativeFiltering;
import dominio.clases.algorithm.contentbasedflitering.K_NN;
import persistencia.ControladorPersistencia;

import java.util.List;
import java.util.Map;

public class ControladorDominio {
    ControladorPersistencia CP;
    CollaborativeFiltering CF;
    K_NN KNN;

    private Map<Integer, Map<Integer, Float>> transformerMapRate(List<String> l){

    }

    public ControladorDominio(){
        //Cridar a Persistencia a que porti tots els mapes (En format List<String> i transformar-ho a maps)
        CP = new ControladorPersistencia();

        List<String> mapaS = CP.getMapRate();
        Map<Integer, Map<Integer, Float>> mapRate= tranformerMapRate(mapaS);

        CF = new CollaborativeFiltering(mapRate);
        KNN = new K_NN(mapRate);
    };

    public void cambiarUsername(String new_username){
        CP.cambiarUsername(new_username);
    }

    public void cambiarPassword(String new_password) {
        CP.cambiarPassword(new_password);
    }

    public void cambiarMail(String new_mail) {
        CP.cambiarMail(new_mail);
    }

    public int login(String nick, String password){
        return CP.login(nick, password);
    }

    public int signin(String nick, String password, String mail){
        return CP.signin(nick, password, mail);
    }

    public void addItem(int ID, List<String> tags){
        CP.addItem(ID, tags);
    }

    public void addRating(int IDuser, int IDitem, float valor){
        CP.addRating(IDuser, IDitem, valor);
    }

    public void addTag(int IDitem, String tag){
        CP.addTag(IDitem, tag);
    }

    public void delTag(int IDitem, String tag){
        CP.delTag(IDitem, tag);
    }

    public void recommendCF(boolean valoration, int k){

    }

    public void recommendCBF(boolean valoration, int k){

    }

    public void recommendH(boolean valoration, int k){

    }
}*/
