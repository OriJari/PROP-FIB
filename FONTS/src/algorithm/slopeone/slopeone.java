package algorithm.slopeone;


import java.util.Map;
import java.util.TreeMap;

import preprocessat.*;


public class slopeone {



    Map<Integer,Map<Integer,Float>> map_data; //mapa de dades rating <userid<itemid,rate>>
    TreeMap<Integer, TreeMap<Integer, Float>> map_des; //mapa de la desviacio dun item amb un altre <userid<itemid,rate>>
    TreeMap<Integer, TreeMap<Integer, Integer>> map_freq; //mapa dels cops que hem computat la desviacio rating per un parell d items <userid<item1,item2>>
    Map<Integer,Float> map_pred; //mapa de prediccio <itemid,predict_rate>



    public void SlopeOne() {
        map_data = CSVparser.getMapRate();
        desviacio_mitjana();
        prediccio();

        Map<Integer,Float> user_pred = new TreeMap<Integer,Float>();
        map_pred = prediccio(user_pred);
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
    public void desviacio_mitjana(){
        map_des = new TreeMap<Integer,TreeMap<Integer,Float>>();
        map_freq = new TreeMap<Integer,TreeMap<Integer,Integer>>();
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
                float desviacio = (float) map_des.get(j).get(i);
                int cardinalitat = map_freq.get(j).get(i);
                map_des.get(j).put(i, desviacio / cardinalitat);
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
    public  void prediccio() {
        map_pred = new TreeMap<Integer,Float>();
        TreeMap<Integer,Integer> freq = new TreeMap<Integer,Integer>();
        for (int j : map_des.keySet()) {
            freq.put(j, 0);
            map_pred.put(j, 0.0f);
        }

        for (Map.Entry<Integer, Map<Integer, Float>> u_data : map_data.entrySet()) {
            for (int j : u_data.getValue().keySet()) {
                for (int i : map_des.keySet()) {

                    float predictedValue = map_des.get(i).get(j) + u_data.getValue().get(j);
                    float finalValue = predictedValue * map_freq.get(i).get(j);
                    map_pred.put(i, map_pred.get(i) + finalValue);
                    freq.put(i, freq.get(i) + map_freq.get(i).get(j));

                }
            }

            for (Integer j : map_pred.keySet()) {
                if (freq.get(j) > 0) {
                   map_pred.put(j, map_pred.get(j) / freq.get(j));
                }
            }

        }

    }
    /*
    prediccio passant map per param

     */

    public Map<Integer,Float> prediccio(Map<Integer,Float> user_pred) {
        Map<Integer,Float> predict = new TreeMap<Integer,Float>(); //item id, rate
        Map<Integer,Integer> frequen = new TreeMap<Integer,Integer>();//item id1, item id 2
        for (int j : map_des.keySet()) {
            predict.put(j,0.0f);
            frequen.put(j,0);
        }
        for (Integer j : user_pred.keySet()) {
            for (Integer i : map_des.keySet()) {
                float newval = ( map_des.get(i).get(j) + user_pred.get(j));
                predict.put(i, predict.get(i)+newval);
            }
        }
        predict.replaceAll((j, v) -> v / user_pred.size());
        for (Integer j : user_pred.keySet()) {
            predict.put(j,user_pred.get(j));
        }
        return predict;
    }



}