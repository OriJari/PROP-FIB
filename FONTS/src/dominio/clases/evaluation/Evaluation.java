package dominio.clases.evaluation;

import java.util.*;
import dominio.clases.rating.*;

/**
 * @author Manel Piera Garrigosa
 */

/** @class dominio.controladores.clases.atribut.evaluation
 * @brief Evaluates the quality of a recommendation.
 */
public class Evaluation {
    private List<Rating> recommendation;
    private Map<Integer, Float> unknown;

    /** @brief <em>recommendation</em> represents the recommendation to a dominio.controladores.clases.atribut.user ordered decreasingly by the expected rating.
     *  <em>known</em> represents the actual ratings from users of some items.
     */


    /** @brief Builder.
     *
     * @param unknown Sets <em>unknown</em> to unknown.
     * @param recommendation Sets <em>recomanacio</em> to recomanacio but sorted decreasingly.
     *
     * \pre <em>true</em>
     * \post Creates an Evaluation.
     */
    public Evaluation(Map<Integer, Float> unknown, Map<Integer, Float> recommendation) {
        this.unknown = unknown;
        this.recommendation = new ArrayList<>();

        for(Map.Entry<Integer, Float> entry: recommendation.entrySet()){
            this.recommendation.add(new Rating(entry.getKey(), entry.getValue()));
        }

        Collections.sort(this.recommendation, new Rating.SortByVal());
    }

    /** @brief Function that calculates the quality of a recommendation.
     *
     * @return It returns a number. The higher it is the better recommendation we have.
     *
     * \pre <em>true</em>
     * \post Gives DCG value.
     */
    public float DCG(){
        int j = 1;
        float result = 0.0f;
        for(int i = 0; i < recommendation.size(); ++i){
            if(unknown.containsKey(recommendation.get(i).getId())){
                result += (Math.pow(2, unknown.get(recommendation.get(i).getId()))-1)/(Math.log(j + 1)/Math.log(2));
            }
            if(i == 0 || recommendation.get(i).getValor() != recommendation.get(i-1).getValor()){
                ++j;
            }
        }
        return result;
    }
}