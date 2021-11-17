package algorithm.K_Means;

import java.util.*;

/** @class K_Means
 * @brief Implements K-means algorithm.
 */
public class K_Means {

        private static Map<Integer, TreeMap<Integer, Float>> opinions;
        /** @brief opinions represents the valorations, float in the nested Map, that users, the first Integer is their ID, have given about items, their ID is the integer in the nested Map.
         */

        //Builders

        /** @brief Default builder.
         *
         * \pre <em>true</em>
         * \post It creates a <em>K_Means</em> object with its attribute <em>opinions</em> empty.
         */
        public K_Means(){
                this.opinions = new TreeMap<>();
        }

        /** @brief Builder with defined <em>opinions</em>.
         *
         * @param opinions Map that represents the ratings that users have given about some items.
         *
         * \post It creates a <em>K_Means</em> object with the parameter opinions as its attribute <em>opinions</em>.
         */
        public K_Means(Map<Integer, TreeMap<Integer, Float>> opinions){
                this.opinions = opinions;
        }


        /** @brief Given two users item ratings it calculates the similarity between both users using the cosine squared simil.
         *
         * @param u1 Ratings of the first user about some items. All ratings must be greater or equal than 0.
         * @param u2 Ratings of the second user about some items. All ratings must be greater or equal than 0.
         *
         * @return It returns the similarity between user <em>u1</em> and user <em>u2</em>. A value between 0 and 1.
         */
        public float cosineSquaredSimil(Map<Integer, Float> u1, Map<Integer, Float> u2){
                float u1Squared = 0.0f;
                float u2Squared = 0.0f;
                float u1Multu2 = 0.0f;
                for(Map.Entry<Integer, Float> entry : u1.entrySet()){
                        u1Squared += Math.pow(entry.getValue(), 2);
                        if(u2.containsKey(entry.getKey())){
                                u1Multu2 += entry.getValue()*u2.get(entry.getKey());
                        }
                }
                for(Map.Entry<Integer, Float> entry : u2.entrySet()){
                        u2Squared += Math.pow(entry.getValue(), 2);
                }
                return (float) (Math.pow(u1Multu2, 2)/(u1Squared*u2Squared));
        }

        /** @brief Given two groups of clusters it determines whether they are the same two groups of clusters. They must be the same clusters and its elements in the same order.
         *
         * @param nuevoClusters Vector that represents the first, the "new" group of clusters.
         * @param clusters Vector that represents the second, the "old" group of clusters.
         * @return It returns a boolean. <em>true</em> if the two groups of clusters are the same and in the same order, <em>false</em> for all the other cases.
         */
        public boolean equalClusters(Vector<Vector<Integer>> nuevoClusters, Vector<Vector<Integer>> clusters){
                for(int i = 0; i < clusters.size(); ++i){
                        Vector<Integer> rowVectorc = clusters.get(i);
                        Vector<Integer> rowVectornew = nuevoClusters.get(i);
                        if(rowVectorc.size() != rowVectornew.size()){
                                return false;
                        }
                }

                for(int i = 0; i < clusters.size(); ++i){
                        Vector<Integer> rowVectorc = clusters.get(i);
                        Vector<Integer> rowVectornew = clusters.get(i);
                        for(int j = 0; j < rowVectorc.size(); ++j){
                                if(rowVectorc.get(j) != rowVectornew.get(j)){
                                        return false;
                                }
                        }
                }
                return true;
        }

        /** @brief Given a positive non-zero integer k, it groups the users in k different clusters based on their similarities.
         *
         * @param k Number of clusters. k > 0.
         *
         * @return It returns a vector with size k of vectors of integers that represents the k different clusters, with the integers being the ID's of the users belonging to that cluster.
         */
        public Vector<Vector<Integer>> k_means(Integer k){ // k <= opinions.size()
                Vector<Vector<Integer>> clusters = new Vector<>();
                for(int i = 0; i < k; ++i){
                        clusters.add(new Vector<Integer>(0));
                }
                Vector<Map<Integer, Float>> means = new Vector<Map<Integer, Float>>(k);
                for(int i = 0; i < k; ++i){
                        means.add(new TreeMap<Integer, Float>());
                }

                //initialize vectors
                {
                        int i = 0;
                        for (Map.Entry<Integer, TreeMap<Integer, Float>> entry : opinions.entrySet()) {
                                clusters.get(i%k).add(entry.getKey());
                                for(Map.Entry<Integer, Float> entry2 : entry.getValue().entrySet()){
                                        if(means.get(i%k).containsKey(entry2.getKey())){
                                                means.get(i%k).put(entry2.getKey(), means.get(i%k).get(entry2.getKey()) + entry2.getValue());
                                        }
                                        else{
                                                means.get(i%k).put(entry2.getKey(), entry2.getValue());
                                        }
                                }
                                ++i;
                        }
                        for(int j = 0; j < means.size(); ++j){
                                for(Map.Entry<Integer, Float> entry: means.get(j).entrySet()){
                                        means.get(j).put(entry.getKey(), entry.getValue()/clusters.get(j).size());
                                }
                        }
                }

                boolean cont = true;

                while(cont){
                        Vector<Vector<Integer>> newClusters = new Vector<>();
                        for(int i = 0; i < k; ++i){
                                newClusters.add(new Vector<Integer>(0));
                        }

                        for(int i = 0; i < clusters.size(); ++i){
                                Vector<Integer> rowVector = clusters.get(i);
                                for(int j = 0; j < rowVector.size(); ++j){
                                        float max = cosineSquaredSimil(means.get(0), opinions.get(rowVector.get(j)));
                                        int max_mean = 0;
                                        for(int p = 1; p < k; ++p){
                                                float simil = cosineSquaredSimil(means.get(p), opinions.get(rowVector.get(j)));
                                                if(simil > max){
                                                        max = simil;
                                                        max_mean = p;
                                                }
                                        }
                                        newClusters.get(max_mean).add(rowVector.get(j));
                                }
                        }


                        if(equalClusters(clusters, newClusters)){
                                cont = false;
                        }
                        else{
                                clusters = newClusters;
                        }

                        if(cont){
                                for(int i = 0; i < clusters.size(); ++i){
                                        Vector<Integer> rowVector = clusters.get(i);
                                        Map<Integer, Float> mean = new HashMap<Integer, Float>();
                                        for(int j = 0; j < rowVector.size(); ++j){ //iterate through all users
                                                Map<Integer, Float> user = opinions.get(rowVector.get(j));
                                                for(Map.Entry<Integer, Float> entry : user.entrySet()){ //iterate through opinions of user
                                                        if(mean.containsKey(entry.getKey())){
                                                                mean.put(entry.getKey(), mean.get(entry.getKey()) + user.get(entry.getKey())/rowVector.size());
                                                        }
                                                        else{
                                                                mean.put(entry.getKey(), user.get(entry.getKey())/rowVector.size());
                                                        }
                                                }
                                        }
                                        means.set(i, mean);
                                }
                        }
                }
                return clusters;
        }
}