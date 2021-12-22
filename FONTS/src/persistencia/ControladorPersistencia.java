package persistencia;

import persistencia.preprocessat.CSVparserItem;
import persistencia.preprocessat.CSVparserRate;
import persistencia.preprocessat.RecommendationSave;
import persistencia.preprocessat.UserList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    UserList UserList;

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
        Recomm = new RecommendationSave();
    }

    public void iniciar_reload(String dir_name){
        CSVItem = new CSVparserItem("DATA/" + dir_name + "/items.csv");
        CSVItem.readLoadItem();
        CSVItem.reload_map_preporcess("DATA/" + dir_name + "/items.prepro.csv");
        CSVItem.listatipos();

        CSVRate = new CSVparserRate("DATA/" + dir_name + "ratings.db.csv");
        CSVRate.readLoadRate();
        CSVRate.reload_map_preporcess("DATA/" + dir_name + "ratings.db.prepro.csv");

        CSVKnown = new CSVparserRate("DATA/" + dir_name + "ratings.test.known.csv");
        CSVKnown.readLoadRate();
        CSVKnown.reload_map_preporcess("DATA/" + dir_name + "ratings.test.known.prepro.csv");

        CSVUnknown = new CSVparserRate("DATA/" + dir_name + "ratings.test.unknown.csv");
        CSVUnknown.readLoadRate();
        CSVUnknown.reload_map_preporcess("DATA/" + dir_name + "ratings.test.unknown.prepro.csv");
        //Crea les classes que necessitis
        UserList = new UserList();
        UserList.initializeUsers(CSVKnown.getMapRate());
        Recomm = new RecommendationSave();
        Recomm.reloadRecommendation("DATA/" + dir_name + "/Recommendation.csv");
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

    public  boolean addUser(int ID) {
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

    public List<Float> list_valSavedREC(int IDuser, int alg, String date) {
        List<Float> result = new ArrayList<>();
        return result;
        //retorna la llista
    }

    public List<Integer> list_itemSavedREC(int IDuser, int alg, String date) {
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

    public List<String> list_tipusheader(){
        return CSVItem.getTipus();
    }

    public boolean crear_carpeta(String nom_dir){
        File directorio = new File("DATA/"+ nom_dir );
        if(!directorio.exists()){
            if(directorio.mkdir()) return true;
            else return false;
        }
        else return false;
    }

    public void guardado(String name_dir){
        boolean b = crear_carpeta(name_dir);
        if (b){
            CSVItem.guardar_datos(name_dir);
            CSVItem.guardar_datos_prepros(name_dir);
            CSVRate.guardar_datos(name_dir, "ratings.db.csv");
            CSVRate.guardar_datos_preproces(name_dir, "ratings.db.prepro.csv");
            CSVKnown.guardar_datos(name_dir, "ratings.test.known.csv");
            CSVKnown.guardar_datos_preproces(name_dir, "rattings.known.prepro.csv");
            CSVUnknown.guardar_datos(name_dir, "ratings.test.unknown.csv");
            CSVKnown.guardar_datos_preproces(name_dir, "ratings.unknown.csv");
            UserList.saveUsers(name_dir);
            Recomm.saveRecommendation(name_dir);
        }
    }

    public void reload(String name_dir){
        File directory = new File("DATA/" + name_dir);
        if(directory.isFile()) {
            iniciar_reload(name_dir);
        }
    }
}