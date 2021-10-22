package com.company;
import java.util.*;
import java.io.*;
import Item;

public class Recomendation {
    Item[] conjunt;
    int ID_perfil;
    //Constructoras
    public Recomendation() {
        ID_perfil = 0;
        conjunt = null;
    }
    //Consultoras
    public int getID_perfil() {
        return ID_perfil;
    }

    public Item[] getConjunt() {
        return conjunt;
    }
    //Modificadoras
    public void setID_perfil(int id) {
        this.ID_perfil = id;
    }

    public void setConjunt(Item[] conj) {
        this.conjunt = conj;
    }

    public void add_Item(Item new_item) {
        int n = conjunt.length();
        String[] new_conj = new String[n+1];
        int i;
        for (i = 0; i < n; i++) new_conj[i] = conjunt[i];
        new_conj[n] = new_item;
        conjunt = new_conj;
    }
    //Other
    public int similarity_items(Item item1, Item item2) {
        int n1 = item1.getNumTags();
        int n2 = item2.getNumTags();
        String[] tags1 = item1.getTags();
        String[] tags2 = item2.getTags();
        int i, j;
        int result = 0;
        for (i = 0; i < n1; i++) {
            for (j = 0; j < n2; j++) {
                if (tags1[i] == tags2[j]) ++result;
            }
        }
        return result;
    }
    public Item[] kNN(Item item1) {
        int n = //nombre d'items al dataset
        int k = //nombre d'items veïns a calcular
        int i, dist;
        int [] dist_vec;
        int [] k_closest;
        int current_max_dist;
        for (i = 0; i < n; ++i) {
            dist = similarity_items(item1, dataset[i]);
            dist_vec[i] = dist;
            //incrementar la mida de dist_vec (still to check)
        }
        for (i = 0; i < n; ++i) {

        }
    }


}