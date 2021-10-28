package com.company;
import java.util.*;
import java.io.*;
import Item;
import User;
import java.util.Map;

public class SlopeOne {
    /*
    TENIM:
        private vector u_val(); //valoracions del usurari u

        private  float u_mean; //mitjana valoracions usuari u

        private vector p_u_val(); // prediccions de valoracions del usuari u

        map map [] []


    */


    Map<User,Map<Item,Float>> map_data;
    Map<Item,Map<Item,Float>> map_des_mitj;
    Map<Item,Map<Item,Integer>> map_freq;

    //pre: true
    //post: creadora
    public static void SlopeOne(){}

    /* dudable metode
    //pre: cardinal del Map<Item, Map<Item, Integer>>, el lengh
    //post: retorna la desviació mitjana de la valoració de dos items
    private double desviacio_mitjana(static int card_){

    }
    */
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

        for (Map<Item, Float> users : dades.values()) { // first iterate through users
            for (Map.Entry<Item, Float> u_data : users.entrySet()) { // then iterate through user data

                if (!map_des_mitj.containsKey(u_data.getKey())) {
                    map_des_mitj.put(u_data.getKey(), new Map<Item, Float>());
                    map_freq.put(u_data.getKey(), new Map<Item, Integer>());
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
        for (Item j : map_desv_mitj.keySet()) {
            for (Item i : map_desv_mitj(j).keySet()) {
                float desviacio = map_desv_mitj.get(j).get(i).doubleValue();
                int cardinalitat = map_freq.get(j).get(i).intValue();
                map_desv_mitj.get(j).put(i, desviacio / cardinalitat);
            }
        }

    }











}