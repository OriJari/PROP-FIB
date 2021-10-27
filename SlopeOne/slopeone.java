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
    Map<Item,Map<Item,Integer>> mFreqMatrix;

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
    //post:  The preprocessing phase, in which is calculated the difference between all item-item preference values
    private static void desviacio_mitjana(Map<User, Map<Item,Float>> dades){
        /*
             private static void buildDifferencesMatrix(Map<User, HashMap<Item, Double>> data) {
        for (HashMap<Item, Double> user : data.values()) {
            for (Entry<Item, Double> e : user.entrySet()) {
                if (!diff.containsKey(e.getKey())) {
                    diff.put(e.getKey(), new HashMap<Item, Double>());
                    freq.put(e.getKey(), new HashMap<Item, Integer>());
                }
                for (Entry<Item, Double> e2 : user.entrySet()) {
                    int oldCount = 0;
                    if (freq.get(e.getKey()).containsKey(e2.getKey())) {
                        oldCount = freq.get(e.getKey()).get(e2.getKey()).intValue();
                    }
                    double oldDiff = 0.0;
                    if (diff.get(e.getKey()).containsKey(e2.getKey())) {
                        oldDiff = diff.get(e.getKey()).get(e2.getKey()).doubleValue();
                    }
                    double observedDiff = e.getValue() - e2.getValue();
                    freq.get(e.getKey()).put(e2.getKey(), oldCount + 1);
                    diff.get(e.getKey()).put(e2.getKey(), oldDiff + observedDiff);
                }
            }
        }
        for (Item j : diff.keySet()) {
            for (Item i : diff.get(j).keySet()) {
                double oldValue = diff.get(j).get(i).doubleValue();
                int count = freq.get(j).get(i).intValue();
                diff.get(j).put(i, oldValue / count);
            }
        }
        printData(data);
    }


         */


    }





}