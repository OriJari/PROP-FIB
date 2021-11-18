package evaluation;

import java.util.*;

/**
 * @author Manel Piera Garrigosa
 */

/** @class evaluation
 * @brief Evaluates the quality of a recommendation.
 */
public class Evaluation {
    private List<rating> recommendation;
    private Map<Integer, Float> unknown;

    /** @brief <em>recommendation</em> represents the recommendation to a user ordered decreasingly by the expected rating.
     *  <em>known</em> represents the actual ratings from users of some items.
     */

    /** @class rating
     * @brief Rating of a user of the item with id <em>id</em> and rating <em>valor</em>.
     */
    private class rating {
        int id;
        float valor;
        /** @brief <em>id</em> represents the item id.
         *  <em>valor</em> represents the item rating.
         */

        /** @brief Builder.
         *
         * @param id Item id.
         * @param valor Item rating.
         */
        public rating(Integer id, Float valor){
            this.id = id;
            this.valor = valor;
        }
    }

    /** @brief We use this class to sort our <em>recommendation</em> list.
     */
    private class SortByVal implements Comparator<rating>{
        public int compare(rating a, rating b){
            if(a.valor > b.valor){
                return -1;
            }
            else if(a.valor < b.valor){
                return 1;
            }
            else{
                return 0;
            }
        }
    }

    /** @brief Builder.
     *
     * @param unknown Sets <em>known</em> to known.
     * @param recommendation Sets <em>recomanacio</em> to recomanacio but sorted decreasingly.
     */
    public Evaluation(Map<Integer, Float> unknown, Map<Integer, Float> recommendation) {
        this.unknown = unknown;
        this.recommendation = new ArrayList<>();

        for(Map.Entry<Integer, Float> entry: recommendation.entrySet()){
            this.recommendation.add(new rating(entry.getKey(), entry.getValue()));
        }

        Collections.sort(this.recommendation, new SortByVal());
    }

    /** @brief Function that calculates the quality of a recommendation.
     *
     * @return It returns a number. The higher it is the better recommendation we have.
     */
    public float DCG(){
        int j = 1;
        float result = 0.0f;
        for(int i = 0; i < recommendation.size(); ++i){
            if(unknown.containsKey(recommendation.get(i).id)){
                result += (Math.pow(2, unknown.get(recommendation.get(i).id))-1)/(Math.log(j + 1)/Math.log(2));
            }
            if(i == 0 || recommendation.get(i).valor != recommendation.get(i-1).valor){
                ++j;
            }
        }
        return result;
    }
}