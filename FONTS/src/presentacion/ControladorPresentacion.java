package presentacion;

//import dominio.controladores.ControladorDominio;

import java.util.List;
import java.util.*;

public class ControladorPresentacion {
    //privete ControladorDominio CD;
    private VistaPrincipal vistaPrincipal = null;
    int ID_actual;

    public ControladorPresentacion(){
        //CD = new ControladorDominio();
        vistaPrincipal = new VistaPrincipal(this);
    }

    public void inicialitzarPresentacion(){
        //CD.inicialitzarControladorDominio;
        vistaPrincipal.hacerVisible();
    }

    /*public boolean cambiarUsername(String new_username) {
        //CD.cambiarUsername(new_username);
    }

    public boolean cambiarPassword(String new_password) {
       // CD.cambiarPassword(new_password);
    }

    public boolean cambiarMail(String new_mail) {
       // CD.cambiarMail(new_mail);
    }

    public boolean cerrarSesion(){}

    public boolean login(String nick, String password){
        ID_actual = CD.login(nick, password);
        return ID_actual != -1;
    }

    public boolean signin(String nick, String password, String mail){
        ID_actual = CD.signin(nick, password, mail);
        return ID_actual != -1;
    }

    public boolean addItem(int ID, List<String> tags){
        CD.addItem(ID, tags);
    }

    public boolean addRating(int IDitem, float valor){
        CD.addRating(ID_actual, IDitem, valor);
    }

    public boolean addTag(int IDitem, String tag){
        CD.addTag(IDitem, tag);
    }

    public boolean delTag(int IDitem, String tag){
        CD.delTag(IDitem, tag);
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
