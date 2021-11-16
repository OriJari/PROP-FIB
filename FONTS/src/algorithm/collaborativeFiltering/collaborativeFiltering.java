package algorithm.collaborativeFiltering;

import java.util.*;

import algorithm.kmean.kmean;

public class collaborativeFiltering {

    private Map<Integer, TreeMap<Integer, Float>> opinions;
    private Integer userID;

    public collaborativeFiltering(Map<Integer, TreeMap<Integer, Float>> opinions, Integer ID){
        this.opinions = opinions;
        this.userID = ID;
    }


    public Map<Integer, Float> recommend(int k){
        kmean Kmean = new kmean(opinions);
        Vector<Vector<Integer>> clusters = Kmean.k_means(k);
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
        TreeMap<Integer, TreeMap<Integer, Float>> valCluster = new TreeMap<Integer, TreeMap<Integer, Float>>();
        for(int i = 0; i < clusters.get(clusterUser).size(); ++i){
            valCluster.put(clusters.get(clusterUser).get(i), opinions.get(clusters.get(clusterUser).get(i)));
        }
        slopeone Slopeone = new SlopeOne(valCluster);
    }
}
