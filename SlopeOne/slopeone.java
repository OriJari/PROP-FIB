package com.company;
import java.util.*;
import Item;
import User;


public class slopeone {
    /*
    TENIM:
        private vector u_val(); //valoracions del usurari u

        private  float u_mean; //mitjana valoracions usuari u

        private vector p_u_val(); // prediccions de valoracions del usuari u

        map map [] []


    */


    Map<User,Map<Item,Float>> map_data; //mapa de dades
    Map<Item,Map<Item,Float>> map_des_mitj; //mapa de la desviacio dun item amb un altre
    Map<Item,Map<Item,Integer>> map_freq; //ni puta idea la veritat

    //pre: true
    //post: creadora
    public static void slopeone(){}


    public static void slopeOne(Map<User,Map<Item,Float>> map_data) {
        desviacio_mitjana(map_data);
        prediccio(map_data);
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
    private static void desviacio_mitjana(Map<User, Map<Item,Float>> dades){

        for (Map<Item, Float> users : dades.values()) { // first iterate through users, per tots els usuaris
            for (Map.Entry<Item, Float> u_data : users.entrySet()) { // then iterate through user data,

                if (!map_des_mitj.containsKey(u_data.getKey())) {
                    map_des_mitj.put(u_data.getKey(), new HashMap<Item, Float>());
                    map_freq.put(u_data.getKey(), new HashMap<Item, Integer>());
                }
                for (Map.Entry<Item, Float> u2_data : user.entrySet()) {
                    int cont1 = 0;
                    if (map_freq.get(u_data.getKey()).containsKey(u2_datya.getKey())) {
                        cont1 = freq.get(u_data.getKey()).get(u2_data.getKey()).intValue();
                    }
                    float desv_ini = 0.0f;
                    if (map_des_mitj.get(u_data.getKey()).containsKey(u2_data.getKey())) {
                        desv_ini = map_des_mitj.get(u_data.getKey()).get(u2_data.getKey()).doubleValue();
                    }
                    float desv_result = u_data.getValue() - u2_data.getValue();
                    map_freq.get(u_data.getKey()).put(u2_data.getKey(), cont1 + 1);
                    map_des_mitj.get(u_data.getKey()).put(u2_data.getKey(), desv_ini + desv_result);
                }
            }
        }
        for (Item j : map_des_mitj.keySet()) {
            for (Item i : map_desv_mitj(j).keySet()) {
                float desviacio = map_des_mitj.get(j).get(i).doubleValue();
                int cardinalitat = map_freq.get(j).get(i).intValue();
                map_des_mitj.get(j).put(i, desviacio / cardinalitat);
            }
        }

    }

    private static void prediccio(Map<User, Map<Item,Float>> dades) {
        Map<Item, Float> u_pred = new HashMap<Item, Float>();
        Map<Item, Integer> u_freq = new HashMap<Item, Integer>();
        for (Item j : map_des_mitj.keySet()) {
            u_freq.put(j, 0);
            u_pred.put(j, 0.0f);
        }
        for (Map.Entry<User, Map<Item, Float>> u_data : data.entrySet()) {
            for (Item j : u_data.getValue().keySet()) {
                for (Item i : map_des_mitj.keySet()) {

                    float predictedValue = map_des_mitj.get(i).get(j).floatValue() + u_data.getValue().get(j).floatValue();
                    float finalValue = predictedValue * map_freq.get(i).get(j).intValue();
                    u_predPred.put(i, u_pred.get(i) + finalValue);
                    u_freq.put(i, u_freq.get(i) + map_freq.get(i).get(j).intValue());

                }
            }
            Map<Item, Float> clean = new HashMap<Item, Float>();
            for (Item j : u_pred.keySet()) {
                if (u_freq.get(j) > 0) {
                    clean.put(j, u_pred.get(j).floatValue() / u_freq.get(j).intValue());
                }
            }
            for (Item j : InputData.items) {
                if (u_data.getValue().containsKey(j)) {
                    clean.put(j, u_data.getValue().get(j));
                } else if (!clean.containsKey(j)) {
                    clean.put(j, -1.0f);
                }
            }


        }


    }

}