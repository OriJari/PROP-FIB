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


    public static Map<Integer,Map<Integer,Float>> map_data;
    /** @brief matrix data <userid<itemid,rate>>
     * */
    public static Map<Integer, Map<Integer, Float>> map_des;
    /** @brief matrix with differential between two items <itemid_1<itemid_2,diff>>
     * */
    public static Map<Integer, Map<Integer, Integer>> map_freq;
    /** @brief matrix with number of times we’ve computed a differential rating for each pair of items <itemid_1<itemid_2<times>>
     * */
    public static Map<Integer,Float> map_pred;
    /** @brief map with rates' predictions of the items for one user <itemid,predict_rate>
     * */

    //Builders

    /** @brief Default builder.
     *
     * \pre <em>true</em>
     * \post It creates a <em>SlopeOne</em> object with its attribute <em>map_data</em>, <em>map_des</em>, <em>map_freq</em> and <em>map_pred</em> empty.
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
     * @param map_freq Matrix that represents times we've computed a diff rating for each pair
     * @param map_pred Map that represents the ratings that users have given about some items
     *
     * \post It creates a <em>K_Means</em> object with the parameter opinions as its attribute <em>opinions</em>.
     */
    public SlopeOne(Map<Integer,Map<Integer,Float>> map_data, Map<Integer, Map<Integer, Float>> map_des,
                    Map<Integer, Map<Integer, Integer>> map_freq , Map<Integer,Float> map_pred){
        SlopeOne.map_data = map_data;
        SlopeOne.map_des = map_des;
        SlopeOne.map_freq = map_freq;
        SlopeOne.map_pred = map_pred;
    }



    public static Map<Integer,Float> slopeone(Map<Integer, Map<Integer, Float>> data, Map<Integer,Float> user) {
        map_data = data;
        desviacio_mitjana();

        prediccio(user);
        for(Map.Entry<Integer,Float> entry : user.entrySet()){
            map_pred.remove(entry.getKey());

        }
        return map_pred;
    }


    /*

    //pre:
  //  Based on the available data, calculate the relationships between the
    // items and number of occurence
     //existing user data and their items' ratings
    //post:  The preprocessing phase, in which is calculated the difference between all item-item preference values

    /*
  1.  for every item i
2.  for every other item j
3.   for every user u expressing preference for both i and j
4.     add the difference in u’s preference for i and j to an average
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


    /*
    1. for every item i the user u expresses no preference for
    2.  for every item j that user u expresses a preference for
    3.   find the average preference difference between j and i
    4.   add this diff to u’s preference value for j
    5.   add this to a running average
    6. return the top items, ranked by these averages
     */
    public static void prediccio(Map<Integer, Float> u_data) {
        map_pred = new TreeMap<Integer,Float>();
        Map<Integer,Integer> freq = new TreeMap<Integer,Integer>();
        for (int j : map_des.keySet()) {
            freq.put(j, 0);
            map_pred.put(j, 0.0f);
        }


        for (int j : u_data.keySet()) {
            for (int i : map_des.keySet()) {
                if(map_freq.containsKey(i) && map_freq.get(i).containsKey(j)) {
                    float mitjana = 0.0f;
                    int k = 0;
                    for(Map.Entry<Integer,Float> entry : u_data.entrySet()){
                        ++k;
                        mitjana += entry.getValue();
                    }
                    mitjana /= (float)k;

                    float predictedValue = map_des.get(i).get(j) + mitjana;
                    float finalValue = predictedValue * map_freq.get(i).get(j);//aqui pq multipliques per la frequencia????
                    map_pred.put(i, map_pred.get(i) + finalValue);//aqui pq ho sumes?? has de fer la suma de les desviacions i despres sumarho a la mitjana
                    freq.put(i, freq.get(i) + map_freq.get(i).get(j));
                }
            }
        }

        for (Integer j : map_pred.keySet()) {
            if (freq.get(j) > 0) {
               map_pred.put(j, map_pred.get(j) / freq.get(j));
            }
        }
    }

}