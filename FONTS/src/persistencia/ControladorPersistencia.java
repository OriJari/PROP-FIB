package persistencia;

import persistencia.preprocessat.CSVparserItem;
import persistencia.preprocessat.CSVparserRate;
import persistencia.preprocessat.RecommendationSave;
import persistencia.preprocessat.UserList;

import java.util.*;
/**
 *
 * @author Miguel Gutierrez Jariod
 */
public class ControladorPersistencia {

    CSVparserItem CSVItem;
    CSVparserRate CSVRate;
    CSVparserRate CSVKnown;
    CSVparserRate CSVUnknown;
    RecommendationSave Recomm;
    static UserList UserList;

    public ControladorPersistencia(){}

    public void inicializar(String path){
        CSVItem = new CSVparserItem(path + "items.csv");
        CSVItem.readLoadItem();
        CSVItem.MapItemData(CSVItem.getContent());
        CSVItem.listatipos();
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
        UserList.initializeUsers(CSVKnown.getMapRate());
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

    public List<Integer> getMapRateIDusers(int a){
        if (a == 0){
            return CSVRate.obtenlistausers();
        }
        else if (a == 1){
            return CSVKnown.obtenlistausers();
        }
        else  return CSVUnknown.obtenlistausers();
    }

    public List<List<Integer>> getMapRateIDitems(int a){
        if (a == 0){
            return CSVRate.obtenlistaitems();
        }
        else if (a == 1){
            return CSVKnown.obtenlistaitems();
        }
        else  return CSVUnknown.obtenlistaitems();
    }

    public List<List<Float>> getMapRateVal(int a){
        if (a == 0){
            return CSVRate.obtenlistavalues();
        }
        else if (a == 1){
            return CSVKnown.obtenlistavalues();
        }
        else  return CSVUnknown.obtenlistavalues();
    }


    public List<Integer> getMapItemIDs(){
        return CSVItem.getId_Items();
    }

    public List<List<String>> getMapTipusTags(){
        return CSVItem.obtentipus();
    }

    public List<List<Integer>> getMapIntsTags(){
        return CSVItem.obtenints();
    }
    public List<List<Double>> getMapDoublesTags(){
        return CSVItem.obtendoubles();
    }

    public List<List<List<String>>> getMapCategoricsTags(){
        return CSVItem.obtencategorics();
    }

    public List<Integer> list_user(){
        List result = (List) UserList.getUsers();
        return result;
        //retorna una llista amb tots els id users
    }

    public List<Integer> list_item(){
        return CSVItem.getId_Items();
        //retorna una llista amb tots els id items
    }

    public boolean exists(int idItem){
        return CSVItem.exsistitemID(idItem);
    }

    public List<String> tag_list() {
        return CSVItem.getHeader();
        //retrona llistat de tags per litem donat
    }

    public List<Float> list_valSavedREC(String path_rec) {
        List<Float> result = new ArrayList<>();
        return result;
        //retorna la llista
    }

    public List<Integer> list_itemSavedREC(String path_rec) {
        List<Integer> result = new ArrayList<>();
        return result;
    }

    public List<Integer> get_item_rec(){
        return Recomm.getId_user();
    }

    public List<Integer> get_alg_rec(){
        return Recomm.getAlgorithm();
    }

    public List<String> get_dates_rec(){
        return Recomm.getDates();
    }

    /*public List<Integer> list_tipusheader(){
        return CSVItem.getTipus();
    }*/
}