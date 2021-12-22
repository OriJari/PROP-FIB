package dominio.clases.recommendation;

import dominio.clases.rating.Rating;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author
 */
public class Recommendation {
    private List<Rating> conjunt;
    private int ID_perfil;
    private int alg;

    //Constructoras
    public Recommendation() {
        ID_perfil = 0;
        conjunt = new ArrayList<Rating>();
    }

    public Recommendation(int userID, int alg) {
        ID_perfil = userID;
        this.alg = alg;
        conjunt = new ArrayList<Rating>();
    }

    //Consultoras
    public int getID_perfil() {
        return ID_perfil;
    }

    public int getAlg(){ return alg;}

    public List<Rating> getConjunt() {
        return conjunt;
    }

    public List<Integer> getItemIDs(){
        List<Integer> result = new ArrayList<>();
        for(Rating r: conjunt) {
            result.add(r.getId());
        }
        return result;
    }

    public List<Float> getItemRates(){
        List<Float> result = new ArrayList<>();
        for(Rating r: conjunt) {
            result.add(r.getValor());
        }
        return result;
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

    public float getValue(int id_item) {
        for (Rating r : conjunt) {
            if (r.getId() == id_item) return r.getValor();
        }
        return -1;
    }

    public void removeRating(int id) {
        for (Rating r : conjunt) {
            if (r.getId() == id) {
                conjunt.remove(r);
                break;
            }
        }
    }

    public void sortR(){
        Collections.sort(this.conjunt, new Rating.SortByVal());
    }
}