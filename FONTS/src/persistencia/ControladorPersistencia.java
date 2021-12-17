package persistencia;

import java.util.ArrayList;
import java.util.List;

public class ControladorPersistencia {

    public ControladorPersistencia(){};

    public boolean inicializar(String path){
        //Crea les classes que necessitis
        return true;
    }

    public boolean addItem(int ID, List<String> tags){
        return true;
    }
    public boolean delItem(int ID) {
        return true;
    }
    public boolean modTag(int IDitem, String atribute, String newtag){
        return true;
    }
    public boolean delTag(int IDitem, String atribute){
        return true;
    }
    public boolean addUser(int ID) {
        return true;
    }
    public boolean delUser(int ID) {
        return true;
    }
    public boolean addRating(int IDuser, int IDitem, float valor){
        return true;
    }
    public boolean modRating(int IDuser, int IDitem, float new_rate) {
        return true;
    }
    public boolean delRating(int IDuser, int IDitem) {
        return true;
    }

    public List<List<String>> getMapRate(int a){
        List<List<String>> result = new ArrayList<>();
        return result;
    }

    public List<List<String>> getMapItem(){
        List<List<String>> result = new ArrayList<>();
        return result;
    }
}