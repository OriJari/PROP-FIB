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
 * @class ControladorPersistencia
 * @brief Controller of the persistence
 * @author Manel
 * @author Miguel
 */

public class ControladorPersistencia {

    CSVparserItem CSVItem;
    CSVparserRate CSVRate;
    CSVparserRate CSVKnown;
    CSVparserRate CSVUnknown;
    RecommendationSave Recomm;
    UserList UserList;

    /**
     * @brief Default builder.
     */
    public ControladorPersistencia(){}


    /**
     * @brief initialize the lecture and preprocess of the documents
     * @param path where are located the csvs documents
     * \pre the path of the file needs to be existent and the document csv needs to be from type items.csv
     *
     * \post It creates a <em>CSVparserItem</em> object with its attribute <em>path</em> with the values:
     * <em>numCols</em>  initialized , <em>numRows</em>  initialized, <em>content</em> initialized, <em>header</em> initialized,
     * <em>mapRatedata</em> initialized, <em>id_Items</em> initialized and <em>tipus</em> initialized.
     *
     * It creates 3  <em>CSVparserRate</em> objects with their attributes <em>path</em> with their values:
     * <em>numCols</em>  initialized , <em>numRows</em>  initialized, <em>content</em> initialized, <em>mapRate</em> initialized and
     * <em>header</em> initialized.
     *
     * It creates a <em>RecommendationSave</em> object with its values:
     * <em>id_user</em>  initialized, <em>idItems</em>  initialized, <em>values</em> initialized, <em>algorithm</em> initialized,
     * <em>dates</em> initialized.
     *
     * It creates a <em>UserList</em> object with the value <em>users</em>  empty
     */
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

    /**
     * @brief reload the content of the documents
     * @param dir_name where are located the csvs documents
     * \pre the path of the file needs to be existent and the document csv needs to be from type items.csv
     *
     * \post It creates a <em>CSVparserItem</em> object with its attribute <em>path</em> with the values:
     * <em>numCols</em>  initialized , <em>numRows</em>  initialized, <em>content</em> initialized, <em>header</em> initialized,
     * <em>mapRatedata</em> initialized, <em>id_Items</em> initialized and <em>tipus</em> initialized.
     *
     * It creates 3  <em>CSVparserRate</em> objects with their attributes <em>path</em> with their values:
     * <em>numCols</em>  initialized , <em>numRows</em>  initialized, <em>content</em> initialized, <em>mapRate</em> initialized and
     * <em>header</em> initialized.
     *
     * It creates a <em>RecommendationSave</em> object with its values:
     * <em>id_user</em>  initialized, <em>idItems</em>  initialized, <em>values</em> initialized, <em>algorithm</em> initialized,
     * <em>dates</em> initialized.
     *
     * It creates a <em>UserList</em> object with the value <em>users</em>  empty
     */
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

    /**
     * @brief Add a new item into the csv
     * \pre needs current csv of items
     * \post added a new item to the csv
     * @param ID of the item to add
     * @param tags list of tags that compose the items
     * @return a boolean, if its true the action has been completed successfully, otherwise not completed the action
     */
    public  boolean addItem(int ID, List<String> tags){
       return CSVItem.addItemCSV(ID, tags);
    }

    /**
     * @brief Delete an item from a current csv and from the others
     * \pre needs current csv of items
     * \post deleted an item to all csv
     * @param ID of the item to delete
     * @return a boolean, if its true the action has been completed successfully, otherwise not completed the action
     */
    public  boolean delItem(int ID) {
        CSVRate.deleteitems(ID);
        CSVKnown.deleteitems(ID);
        CSVRate.deleteuser();
        CSVKnown.deleteuser();
       return CSVItem.delItemCSV(ID);

    }

    /**
     * @brief Modify the tag from an item from the csv
     * \pre needs current csv of items
     * \post modify the tag from an item of the csv
     * @param IDitem of the item to modify
     * @param atribute column where is located the tag to modify
     * @param newtag to modify from a previous one
     * @return a boolean, if its true the action has been completed successfully, otherwise not completed the action
     */
    public  boolean modTag(int IDitem, String atribute, String newtag){
        return CSVItem.modTagCSV(IDitem, atribute, newtag);
    }

    /**
     * @brief Delete the tag from an item from the csv
     * \pre needs current csv of items
     * \post delete the tag from an item of the csv
     * @param IDitem of the item to modify
     * @param atribute column where is located the tag to delete
     * @return a boolean, if its true the action has been completed successfully, otherwise not completed the action
     */
    public  boolean delTag(int IDitem, String atribute){
        return CSVItem.delTagCSV(IDitem, atribute);
    }

    /**
     * @brief Add a user to the set of users
     * \pre needs Userlist initialized
     * \post the set user is actualized with the new id of the user
     * @param ID of the user to add
     * @return a boolean, if its true the action has been completed successfully, otherwise not completed the action
     */
    public  boolean addUser(int ID) {
        return UserList.addUserList(ID);
    }

    /**
     * @brief Delete a user from a current csv
     * \pre needs current csv of items
     * \post deleted a user to the csv
     * @param ID of the user to delete
     * @return a boolean, if its true the action has been completed successfully, otherwise not completed the action
     */
    public  boolean delUser(int ID) {
        return CSVKnown.delUserCSV(ID);
    }

    /**
     * @brief Add the rate of an item from a user of the csv
     * \pre needs current csv of items
     * \post add the rate of the item corresponding to a user
     * @param IDuser of the user to realize the action
     * @param IDitem of the item to add the corresponding rate
     * @param valor rate to add
     * @return a boolean, if its true the action has been completed successfully, otherwise not completed the action
     */
    public  boolean addRating(int IDuser, int IDitem, float valor){
        return  CSVKnown.addRatingCSV(IDuser, IDitem, valor);
    }

    /**
     * @brief Modify the rate of an item from a user of the csv
     * \pre needs current csv of items
     * \post modify the rate of the item corresponding to a user
     * @param IDuser of the user to realize the action
     * @param IDitem of the item to modify the corresponding rate
     * @param new_rate to modify from a previous one
     * @return a boolean, if its true the action has been completed successfully, otherwise not completed the action
     */
    public  boolean modRating(int IDuser, int IDitem, float new_rate) {
       return CSVKnown.modRatingCSV(IDuser, IDitem, new_rate);
    }

    /**
     * @brief Delete the rate of an item from a user of the csv
     * \pre needs current csv of items
     * \post delete the rate of an item from a user of the csv
     * @param IDuser of the user within interact
     * @param IDitem of the item to delete the rate
     * @return a boolean, if its true the action has been completed successfully, otherwise not completed the action
     */
    public  boolean delRating(int IDuser, int IDitem) {
       return CSVKnown.delRatingCSV(IDuser, IDitem);
    }


    /**
     * @brief obtains the list of the users form the csv chosen
     * \pre needs csvs initialized
     * \post obtain the user set form the csv
     * @param a number of csv to choose
     * @return list of integers corresponding to the users listed in the corresponding csv .
     */
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
        return UserList.getUsers();
        //retorna una llista amb tots els id users
    }

    public List<Integer> list_item(){
        return CSVItem.getId_Items();
        //retorna una llista amb tots els id items
    }

    public boolean exists(int idItem){
        return CSVItem.exsistitemID(idItem);
    }

    public List<String> get_header_items() {
        return CSVItem.getHeader();
    }

    public List<Float> list_valSavedREC(int IDuser, int alg, String date) {
        return Recomm.valuesrates(IDuser, alg, date);
        //retorna la llista
    }

    public List<Integer> list_itemSavedREC(int IDuser, int alg, String date) {
        return Recomm.valuesitems(IDuser, alg, date);
    }

    public List<Integer> get_IDuser_rec(){
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

    public void saveRec(int IDuser, int alg,  List<Integer> IDitems,List<Float> valors){
        Recomm.carregaAtributs(IDuser, alg, IDitems, valors);
    }
}