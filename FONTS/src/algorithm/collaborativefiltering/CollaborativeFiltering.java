package algorithm.collaborativefiltering;

import java.util.*;

import algorithm.k_means.*;
import algorithm.slopeone.*;

/**
 * @author Manel Piera Garrigosa
 */

/** @class collaborativeFiltering
 * @brief Implements recommendation using K-means and Slope One algorithm.
 */
public class CollaborativeFiltering {

    private Map<Integer, Map<Integer, Float>> opinions;
    private Vector<Vector<Integer>> clusters;

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
        clusters = new Vector<>();
    }
    /** @brief Builder with defined <em>opinions</em> and <em>k</em>.
     *
     * @param opinions Map that represents the ratings that users have given about some items.
     * @param k Desired number of clusters. Integer larger than 0.
     *
     * \post Creates <em>collaborativeFiltering</em> object with <em>opinions</em> set to opinions and computes the k clusters of users.
     */
    public CollaborativeFiltering(Map<Integer, Map<Integer, Float>> opinions, Integer k){
        this.opinions = opinions;
        K_Means Kmean = new K_Means(opinions);
        this.clusters = Kmean.k_means(k);
    }

    /** @brief Function that makes a recommendation of items to a user.
     *
     * @param userID ID of the user that receives the recommendation.
     * @return It returns a Map of item ID's together with the expected rating of the user.
     */
    public Map<Integer, Float> recommend(Integer userID){
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

        Map<Integer, Map<Integer, Float>> valCluster = new TreeMap<Integer, Map<Integer, Float>>();
        for(int i = 0; i < clusters.get(clusterUser).size(); ++i){
            valCluster.put(clusters.get(clusterUser).get(i), opinions.get(clusters.get(clusterUser).get(i)));
        }
        SlopeOne Slopeone = new SlopeOne();
        Map<Integer, Float> recommendation = Slopeone.slopeone(valCluster, opinions.get(userID));
        Map<Integer, Float> result = new TreeMap<Integer, Float>();
        if(recommendation.size() > 10) {
            for (int i = 0; i < 10; ++i) {
                Iterator<Integer> it = recommendation.keySet().iterator();
                int maxitemID = it.next();
                for (Map.Entry<Integer, Float> entry : recommendation.entrySet()) {
                    if (entry.getValue() > recommendation.get(maxitemID)) {
                        maxitemID = entry.getKey();
                    }
                }
                result.put(maxitemID, recommendation.get(maxitemID));
                recommendation.remove(maxitemID);
            }
        }
        else{
            result = recommendation;
        }
        return result;
    }
}
