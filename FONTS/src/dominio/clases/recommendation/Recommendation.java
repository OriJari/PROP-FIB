package dominio.clases.recommendation;

import dominio.clases.item.*;
import dominio.clases.rating.Rating;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Recommendation {
    List<Rating> conjunt;
    int ID_perfil;

    //Constructoras
    public Recommendation() {
        ID_perfil = 0;
        conjunt = new ArrayList<Rating>();
    }

    public Recommendation(int userID) {
        ID_perfil = userID;
        conjunt = new ArrayList<Rating>();
    }

    //Consultoras
    public int getID_perfil() {
        return ID_perfil;
    }

    public List<Rating> getConjunt() {
        return conjunt;
    }

    //Modificadoras
    public void setID_perfil(int id) {
        this.ID_perfil = id;
    }

    public void setConjunt(List<Rating> conj) {
        this.conjunt = conj;
    }

    public void addRating(Rating new_rating) {
        this.conjunt.add(new_rating);
    }

    public void sortR(){
        Collections.sort(this.conjunt, new Rating.SortByVal());
    }
}