package dominio.controladores;

import dominio.clases.algorithm.collaborativefiltering.CollaborativeFiltering;
import dominio.clases.algorithm.contentbasedflitering.K_NN;
import dominio.clases.algorithm.hybrid.Hybrid;
import dominio.clases.evaluation.Evaluation;
import dominio.clases.recommendation.Recommendation;
import persistencia.ControladorPersistencia;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ControladorDominio {
    private ControladorPersistencia CP;
    private CollaborativeFiltering CFNotEval;
    private CollaborativeFiltering CFEval;
    private K_NN KNNnotEval;
    private K_NN KNNEval;
    private Evaluation E;
    private Recommendation rec;
    private Hybrid H;

    public ControladorDominio(){
        //Cridar a Persistencia a que porti tots els mapes (En format List<String> i transformar-ho a maps)
        CP = new ControladorPersistencia();
    }

    public void computeK(Map<Integer, Map<Integer, Float>> mapRateKnown, Map<Integer, Map<Integer, Float>> mapRateUnknown, int maxItems){
        CollaborativeFiltering CFAux;
        int maxK = mapRateKnown.size()/50;
        float maxDCG = 0.0f;
        E = new Evaluation(mapRateUnknown);
        for(int i = maxK; i < mapRateKnown.size()/3; ++i){
            float DCG = 0.0f;
            CFAux = new CollaborativeFiltering(mapRateKnown, mapRateUnknown, i);
            for(Map.Entry<Integer, Map<Integer, Float>> entry: mapRateKnown.entrySet()){
                DCG += E.DCG(CFAux.recommend(entry.getKey(), maxItems, true));
            }
            if(DCG > maxDCG){
                maxK = i;
                maxDCG = DCG;
            }
        }

        CFNotEval = new CollaborativeFiltering(mapRateKnown, mapRateUnknown, maxK);
    }

    public Map<Integer, Map<Integer, Float>> constructMap(List<Integer> mapRateIDusers, List<List<Integer>> mapRateIDitems, List<List<Float>> mapRateVal){
        Map<Integer, Map<Integer, Float>> result = new TreeMap<>();
        for(int i = 0; i < mapRateIDusers.size(); ++i){
            Map<Integer, Float> interiorMap = new TreeMap<>();
            for(int j = 0; j < mapRateIDitems.get(i).size(); ++j){
                interiorMap.put(mapRateIDitems.get(i).get(j), mapRateVal.get(i).get(j));
            }
            result.put(mapRateIDusers.get(i), interiorMap);
        }
        return result;
    }

    public void inicializar(String path){
        boolean result = CP.inicializar(path);
        List<Integer> mapRateIDusersRatings = CP.getMapRateIDusers(0);
        List<List<Integer>> mapRateIDitemsRatings = CP.getMapRateIDitems(0);
        List<List<Float>> mapRateValRatings = CP.getMapRateVal(0);

        List<Integer> mapRateIDusersKnown = CP.getMapRateIDusers(1);
        List<List<Integer>> mapRateIDitemsKnown = CP.getMapRateIDitems(1);
        List<List<Float>> mapRateValKnown = CP.getMapRateVal(1);

        List<Integer> mapRateIDusersUnknown = CP.getMapRateIDusers(2);
        List<List<Integer>> mapRateIDitemsUnknown = CP.getMapRateIDitems(2);
        List<List<Float>> mapRateValUnknown = CP.getMapRateVal(2);

        Map<Integer, Map<Integer, Float>> mapRateRatings = constructMap(mapRateIDusersRatings, mapRateIDitemsRatings, mapRateValRatings);
        Map<Integer, Map<Integer, Float>> mapRateKnown = constructMap(mapRateIDusersKnown, mapRateIDitemsKnown, mapRateValKnown);
        Map<Integer, Map<Integer, Float>> mapRateUnknown = constructMap(mapRateIDusersUnknown, mapRateIDitemsUnknown, mapRateValUnknown);

        //CFNotEval = CollaborativeFiltering();


    }

    public List<Integer> list_user(){
        return CP.list_user();
        //retorna una llista amb tots els id users
    }

    public List<Integer> list_item(){
        return CP.list_item();
        //retorna una llista amb tots els id items
    }

    public boolean addItem(int ID, List<String> tags){
        return CP.addItem(ID, tags);
    }
    public boolean exists(int idItem){
        return CP.exists(idItem);
    }
    public boolean delItem(int ID) {
        return CP.delItem(ID);
    }
    public boolean modTag(int IDitem, String atribute, String newtag){
        return CP.modTag(IDitem, atribute, newtag);
    }
    public boolean delTag(int IDitem, String atribute){
        return CP.delTag(IDitem, atribute);
    }
    public boolean addUser(int ID) {
        return CP.addUser(ID);
    }
    public boolean delUser(int ID) {
        return CP.delUser(ID);
    }
    public boolean addRating(int IDuser, int IDitem, float valor){
        return CP.addRating(IDuser, IDitem, valor);
    }
    public boolean modRating(int IDuser, int IDitem, float new_rate) {
        return CP.modRating(IDuser, IDitem, new_rate);
    }
    public boolean delRating(int IDuser, int IDitem) {
        return CP.delRating(IDuser, IDitem);
    }

    /*public List<List<String>> getMapRate(int a){ // 0 si es ratings, 1 si es known, 2 si es unknown
        return CP.getMapRate(a);
    }*/

    public List<List<String>> getMapItemTags(){
        return CP.getMapItemTags();
    }

    public void recommendCF(int k, int userID, boolean eval){
        if(eval){
            rec = CFEval.recommend(userID, k, eval);
        }
        else{
            rec = CFNotEval.recommend(userID, k, eval);
        }

    }

    public void recommendCBF(int k, int userID, boolean eval){
        if (eval){
            rec = KNNEval.recommend(userID, k, eval);
        }
        else{
            rec = KNNnotEval.recommend(userID, k, eval);
        }
    }

    public void recommendH(int k, int userID, boolean eval){
        Recommendation r1, r2;
        if (eval) {
            r1 = CFEval.recommend(userID, k, eval);
            r2 = KNNEval.recommend(userID, k, eval);
        }
        else{
            r1 = CFNotEval.recommend(userID, k, eval);
            r2 = KNNnotEval.recommend(userID, k, eval);
        }

        rec = H.recommend(r1,r2,k);
    }

    public List<String> tag_list() {
        return CP.tag_list();
    }

    public boolean saveRecomendation() {
        return true;
    }

    public void evaluateRecomendation(boolean e) {
    }

    public List<Integer> list_itemREC() {
        return rec.getItemIDs();
        //retorna la llista de items de la recomanacio
    }

    public List<Float> list_valREC() {
        return rec.getItemRates();
        //retorna la llista de valors de la recomanacio
    }

    public List<Float> list_valSavedREC(String path_rec) {
        return CP.list_valSavedREC(path_rec);
    }

    public List<Integer> list_itemSavedREC(String path_rec) {
        return CP.list_itemSavedREC(path_rec);
    }

    public List<Integer> get_item_rec(){
        return CP.get_item_rec();
    }

    public List<Integer> get_alg_rec(){
        return CP.get_alg_rec();
    }

    public List<String> get_dates_rec(){
        return CP.get_dates_rec();
    }
}
