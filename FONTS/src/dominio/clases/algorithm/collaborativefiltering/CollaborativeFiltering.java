package dominio.clases.algorithm.collaborativefiltering;

import dominio.clases.algorithm.k_means.*;
import dominio.clases.algorithm.slopeone.*;
import dominio.clases.rating.Rating;
import dominio.clases.recommendation.Recommendation;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

/**
 * @author Manel Piera Garrigosa
 */

/** @class collaborativeFiltering
 * @brief Implements recommendation using K-means and Slope One dominio.controladores.clases.atribut.algorithm.
 */
public class CollaborativeFiltering {

    private static Map<Integer, Map<Integer, Float>> opinions;
    private static Map<Integer, Map<Integer, Float>> unknown;
    private static Vector<Vector<Integer>> clusters;


    /** @brief <em>opinions</em> represents the ratings, float in the nested Map, that users, the first Integer is their ID, have given about items, their ID is the integer in the nested Map.
     *  <em>clusters</em> represents the k clusters of similar users.
     */

    /** @brief Default builder.
     *
     * \pre <em>true</em>
     * \post Creates a <em>collaborativeFiltering</em> object with <em>opinions</em> and <em>clusters</em> empty.
     */
    public CollaborativeFiltering(){
        opinions = new TreeMap<>();
        unknown = new TreeMap<>();
        clusters = new Vector<>();
    }
    /** @brief Builder with defined <em>opinions</em> and <em>k</em>.
     *
     * @param opinions Map that represents the ratings that users have given about some items.
     * @param k Desired number of clusters. Integer larger than 0.
     *
     * \pre <em>k</em> must be larger than 0 but smaller or equal than opinions.size().
     * \post Creates <em>collaborativeFiltering</em> object with <em>opinions</em> set to opinions and computes the k clusters of users.
     */
    public CollaborativeFiltering(Map<Integer, Map<Integer, Float>> opinions, Map<Integer, Map<Integer, Float>> unknown, Integer k){
        CollaborativeFiltering.unknown = unknown;
        CollaborativeFiltering.opinions = opinions;
        K_Means Kmean = new K_Means(opinions);
        CollaborativeFiltering.clusters = Kmean.k_means(k);
    }

    /** @brief Function that makes a recommendation of items to a dominio.controladores.clases.atribut.user.
     *
     * @param userID ID of the dominio.controladores.clases.atribut.user that receives the recommendation.
     * @return It returns a Map of dominio.controladores.clases.atribut.item ID's together with the expected rating of the dominio.controladores.clases.atribut.user.
     *
     * \pre The dominio.controladores.clases.atribut.user must exist.
     * \post Returns a Map of expected ratings with maximum size 10.
     */
    static public Recommendation recommend(Integer userID, Integer maxItems, boolean valoration){
        boolean cont = true;
        Integer clusterUser = 0;
        for(int i = 0; i < clusters.size() && cont; ++i){
            for(int j = 0; j < clusters.get(i).size() && cont; ++j){
                if(userID == clusters.get(i).get(j)){
                    clusterUser = i;
                    cont = false;
                }
            }
        }

        Map<Integer, Map<Integer, Float>> valCluster = new TreeMap<>();
        for(int i = 0; i < clusters.get(clusterUser).size(); ++i){
            valCluster.put(clusters.get(clusterUser).get(i), opinions.get(clusters.get(clusterUser).get(i)));
        }
        SlopeOne Slopeone = new SlopeOne();
        Map<Integer, Float> mapRecommendation = Slopeone.slopeone(valCluster, opinions.get(userID));
        Map<Integer, Float> aux = new TreeMap<>();

        if(valoration){
            for(Map.Entry<Integer, Float> entry: mapRecommendation.entrySet()){
                if(unknown.get(userID).containsKey(entry.getKey())){
                    aux.put(entry.getKey(), entry.getValue());
                }
            }
            mapRecommendation = aux;
            aux = new TreeMap<>();
        }

        if(mapRecommendation.size() > maxItems) {
            for (int i = 0; i < maxItems; ++i) {
                Iterator<Integer> it = mapRecommendation.keySet().iterator();
                int maxitemID = it.next();
                for (Map.Entry<Integer, Float> entry : mapRecommendation.entrySet()) {
                    if (entry.getValue() > mapRecommendation.get(maxitemID)) {
                        maxitemID = entry.getKey();
                    }
                }
                aux.put(maxitemID, mapRecommendation.get(maxitemID));
                mapRecommendation.remove(maxitemID);
            }
        }
        else{
            aux = mapRecommendation;
        }

        Recommendation result = new Recommendation(userID);
        for(Map.Entry<Integer, Float> entry: aux.entrySet()){
            result.addRating(new Rating(entry.getKey(), entry.getValue()));
        }

        result.sortR();
        return result;
    }

}
