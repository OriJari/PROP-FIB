package avaluacio;

import java.util.*;

/** @class avaluacio
 * @brief Evaluates the quality of a recommendation.
 */
public class avaluacio {
    private List<valoracio> recomanacio;
    private Map<Integer, Float> known;

    /** @brief <em>recomanacio</em> represents the recommendation to a user ordered decreasingly by the expected rating.
     *  <em>known</em> represents the actual ratings from users of some items.
     */

    /** @class valoracio
     * @brief Rating of a user of the item with id <em>id</em> and rating <em>valor</em>.
     */
    private class valoracio{
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
        public valoracio(Integer id, Float valor){
            this.id = id;
            this.valor = valor;
        }
    }

    /** @brief We use this class to sort our <em>recomanacio</em> list.
     */
    private class SortByVal implements Comparator<valoracio>{
        public int compare(valoracio a, valoracio b){
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
     * @param known Sets <em>known</em> to known.
     * @param recomanacio Sets <em>recomanacio</em> to recomanacio but sorted decreasingly.
     */
    public void avaluacio(Map<Integer, Float> known, List<valoracio> recomanacio) {
        this.known = known;
        this.recomanacio = recomanacio;
        Collections.sort(this.recomanacio, new SortByVal());
    }

    /** @brief Function that calculates the quality of a recommendation.
     *
     * @return It returns a number. The higher it is the better recommendation we have.
     */
    float DCG(){
        int j = 1;
        float result = 0.0f;
        for(int i = 0; i < recomanacio.size(); ++i){
            if(known.containsKey(recomanacio.get(i).id)){
                result += (Math.pow(2, known.get(recomanacio.get(i).id))-1)/(Math.log(j + 1)/Math.log(2));
            }
            if(i == 0 || recomanacio.get(i).valor != recomanacio.get(i-1).valor){
                ++j;
            }
        }
        return result;
    }
}