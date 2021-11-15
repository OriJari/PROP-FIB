package algorithm.contentbasedflitering;

import item.Item;

public class Recommendation {
    Item[] conjunt;
    int ID_perfil;
    //Constructoras
    public Recommendation() {
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
        int n = conjunt.length;
        Item[] new_conj = new Item[n+1];
        int i;
        for (i = 0; i < n; i++) new_conj[i] = conjunt[i];
        new_conj[n] = new_item;
        conjunt = new_conj;
    }
    //Other
    //Quan rebem el data_set, podem calcular en el mateix moment la taula de distàncies
    //entre items, i quan es demani fer una recomendació, només buscar els k-NN.

    public Item[] kNN(Item item1) {
        int n = //nombre d'items al dataset
        int k = //nombre d'items veïns a calcular
        int i, dist;
        int [] dist_vec;
        int [] k_closest;
        int current_max_dist;

    }

}