package algorithm.slopeone;


import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Oriol Martí Jariod
 */

/** @class SlopeOne
 * @brief Implements Slope One algorithm.
 */

public class SlopeOne {


    private static Map<Integer,Map<Integer,Float>> map_data;
    /** @brief matrix data <userid<itemid,rate>>
     * */
    private static Map<Integer, Map<Integer, Float>> map_des;
    /** @brief matrix with differential between two items <itemid_1<itemid_2,diff>>
     * */
    private static Map<Integer, Map<Integer, Integer>> map_freq;
    /** @brief matrix with number of times we’ve computed a differential rating for each pair of items <itemid_1<itemid_2<times>>
     * */
    private static Map<Integer,Float> map_pred;
    /** @brief map with rates' predictions of the items for one user <itemid,predict_rate>
     * */

    //Builders

    /** @brief Default builder.
     *
     * \pre <em>true</em>
     * \post It creates a <em>SlopeOne</em> object with its attribute <em>map_data</em>,
     *       <em>map_des</em>, <em>map_freq</em> and <em>map_pred</em> empty.
     */
    public SlopeOne(){
        SlopeOne.map_data = new TreeMap<>();
        SlopeOne.map_des = new TreeMap<>();
        SlopeOne.map_freq = new TreeMap<>();
        SlopeOne.map_pred = new TreeMap<>();
    }

    /** @brief Builder with defined attributes.
     *
     * @param map_data Matrix that represents the data.
     * @param map_des  Matrix that represents the difference between two items.
     * @param map_freq Matrix that represents times we've computed a diff rating for each pair of items.
     * @param map_pred Map that represents rates' predictions of the items for on user.
     *
     * \pre <em>true</em>
     * \post It creates a <em>SlopeOne</em> object with the parameters map_data, map_des,
     *       map_freq and map_pred as its attribute <em>map_data</em>, <em>map_des</em>, <em>map_freq</em> and <em>map_pred</em>.
     */
    public SlopeOne(Map<Integer,Map<Integer,Float>> map_data, Map<Integer, Map<Integer, Float>> map_des,
                    Map<Integer, Map<Integer, Integer>> map_freq , Map<Integer,Float> map_pred){
        SlopeOne.map_data = map_data;
        SlopeOne.map_des = map_des;
        SlopeOne.map_freq = map_freq;
        SlopeOne.map_pred = map_pred;
    }

    /**
     * @brief Getter of the Map_data
     * @return Return the map_data
     *
     * \pre true
     * \post true
     */
    public static Map<Integer, Map<Integer, Float>> getMap_data() {return map_data;}

    /**
     * @brief Getter of the Map_des
     * @return Return the map_des
     *
     * \pre true
     * \post true
     */
    public static Map<Integer, Map<Integer, Float>> getMap_des() {return map_des;    }

    /**
     * @brief Getter of the Map_pred
     * @return Return the map_pred
     *
     * \pre true
     * \post true
     */
    public static Map<Integer, Float> getMap_pred() {return map_pred;}

    /**
     * @brief Getter of the Map_freq
     * @return Return the map_freq
     *
     * \pre true
     * \post true
     */
    public static Map<Integer, Map<Integer, Integer>> getMap_freq() {return map_freq;}

    /**
     * @brief Setter of the atribute map_data
     * @param map_data Matrix that represents the data.
     *
     * \pre True
     * \post Modify map_data
     */
    public static void setMap_data(Map<Integer, Map<Integer, Float>> map_data) { SlopeOne.map_data = map_data;}


    /** @brief Given the data and a user which it's the one we want to predict his ratings,
     *         calls the pertinents methods to calculate it, and clean the map_pred with the predictions that we want.
     *
     * @param data Matrix with a cluster of users, with his rates for different items.
     * @param user Map with the items from the user we want to predict his rate of the items non-rated.
     *
     * @return It returns the prediction map, which contains the predicted rates form our user.
     *
     * \pre true
     * \post Calculate a prediction for an user
     */
    public static Map<Integer,Float> slopeone(Map<Integer, Map<Integer, Float>> data, Map<Integer,Float> user) {
        map_data = data;
        desviacio_mitjana();

        prediccio(user);
        for(Map.Entry<Integer,Float> entry : user.entrySet()){
            map_pred.remove(entry.getKey());

        }
        return map_pred;
    }


    /** @brief Calculates the difference between all item-item rating values.
     *
     * \pre Ture
     * \post Fills map_des with the diff between all item-item rating values.
     */
    public static void desviacio_mitjana(){
        map_des = new TreeMap<Integer,Map<Integer,Float>>();
        map_freq = new TreeMap<Integer,Map<Integer,Integer>>();
        for (Map<Integer, Float> users : map_data.values()) { //  per tots els usuaris
            for (Map.Entry<Integer, Float> u_data : users.entrySet()) { // itera a traves de les dades dels usuaris

                if (!map_des.containsKey(u_data.getKey())) {
                    map_des.put(u_data.getKey(), new TreeMap<Integer, Float>());
                    map_freq.put(u_data.getKey(), new TreeMap<Integer, Integer>());
                }
                for (Map.Entry<Integer, Float> u2_data : users.entrySet()) {
                    int cont1 = 0;
                    if (map_freq.get(u_data.getKey()).containsKey(u2_data.getKey())) {
                        cont1 = map_freq.get(u_data.getKey()).get(u2_data.getKey());
                    }
                    float desv_ini = 0.0f;
                    if (map_des.get(u_data.getKey()).containsKey(u2_data.getKey()))
                        desv_ini = map_des.get(u_data.getKey()).get(u2_data.getKey());
                    float desv_result = u_data.getValue() - u2_data.getValue();
                    map_freq.get(u_data.getKey()).put(u2_data.getKey(), cont1 + 1);
                    map_des.get(u_data.getKey()).put(u2_data.getKey(), desv_ini + desv_result);
                }
            }
        }
        for (Integer j : map_des.keySet()) {
            for (Integer i : map_des.get(j).keySet()) {
                float rate = (float) map_des.get(j).get(i);
                int cardinalitat = map_freq.get(j).get(i);
                map_des.get(j).put(i, rate / cardinalitat);
            }
        }

    }


    /**
     * @brief Calculates the prediction for the items non-rated.
     *
     * @param u_data Map with the items from the user we want to predict his rate of the items non-rated.
     * \pre true
     * \post Fill map_pred with the data form u_data and the items not rated by the user but that we predicted.
     */
    public static void prediccio(Map<Integer, Float> u_data) {
        map_pred = new TreeMap<Integer,Float>();
        Map<Integer,Integer> freq = new TreeMap<Integer,Integer>();
        for (int j : map_des.keySet()) {
            freq.put(j, 0);
            map_pred.put(j, 0.0f);
        }

        float mitjana = 0.0f;
        int k = 0;
        for(Map.Entry<Integer,Float> entry : u_data.entrySet()){
            ++k;
            mitjana += entry.getValue();
        }
        if(k>1) mitjana /= (float)k;

        for(Map.Entry<Integer, Map<Integer, Float>> entry: map_des.entrySet()){
            float desvi = 0.0f;
            for(Map.Entry<Integer, Float> entry2: entry.getValue().entrySet()){
                  desvi += entry2.getValue();
            }
            if(entry.getValue().size() > 1){
                desvi = desvi/(entry.getValue().size()-1);
            }
            map_pred.put(entry.getKey(), desvi + mitjana);
        }
    }

}