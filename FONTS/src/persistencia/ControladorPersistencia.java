package persistencia;

import dominio.clases.content.Content;
import persistencia.preprocessat.CSVparserItem;
import persistencia.preprocessat.CSVparserRate;
import persistencia.preprocessat.RecommendationSave;
import persistencia.preprocessat.UserList;

import java.util.*;

public class ControladorPersistencia {

    CSVparserItem CSVItem;
    CSVparserRate CSVRate;
    CSVparserRate CSVKnown;
    CSVparserRate CSVUnknown;
    RecommendationSave Recomm;
    static UserList UserList;

    public ControladorPersistencia(){}

    public boolean inicializar(String path){
        CSVItem = new CSVparserItem(path + "items.csv");
        CSVItem.readLoadItem();
        CSVItem.MapItemData(CSVItem.getContent());
        CSVRate = new CSVparserRate(path + "ratings.db.csv");
        CSVRate.readLoadRate();
        CSVRate.LoadRate(CSVRate.getContent());
        CSVKnown = new CSVparserRate(path + "ratings.test.known.csv");
        CSVKnown.readLoadRate();
        CSVKnown.LoadRate(CSVKnown.getContent());
        CSVUnknown = new CSVparserRate(path + "ratings.test.unknown.csv");
        CSVUnknown.readLoadRate();
        CSVUnknown.LoadRate(CSVUnknown.getContent());
        //Crea les classes que necessitis
        UserList = new UserList();
        return true;
    }

    public  boolean addItem(int ID, List<String> tags){
       return CSVItem.addItemCSV(ID, tags);
    }

    public  boolean delItem(int ID) {
       return CSVItem.delItemCSV(ID);
    }

    public  boolean modTag(int IDitem, String atribute, String newtag){
        return CSVItem.modTagCSV(IDitem, atribute, newtag);
    }

    public  boolean delTag(int IDitem, String atribute){
        return CSVItem.delTagCSV(IDitem, atribute);
    }

    public static boolean addUser(int ID) {
        return UserList.addUserList(ID);
    }

    public  boolean delUser(int ID) {
        return CSVKnown.delUserCSV(ID);
    }

    public  boolean addRating(int IDuser, int IDitem, float valor){
        return  CSVKnown.addRatingCSV(IDuser, IDitem, valor);
    }

    public  boolean modRating(int IDuser, int IDitem, float new_rate) {
       return CSVKnown.modRatingCSV(IDuser, IDitem, new_rate);
    }

    public  boolean delRating(int IDuser, int IDitem) {
       return CSVKnown.delRatingCSV(IDuser, IDitem);
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