package dominio.clases.algorithm.contentbasedflitering;

import dominio.clases.item.*;

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
}