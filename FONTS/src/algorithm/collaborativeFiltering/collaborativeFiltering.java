package algorithm.collaborativeFiltering;

import java.util.*;

import algorithm.kmean.kmean;
import algorithm.slopeone.slopeone;

public class collaborativeFiltering {

    private Map<Integer, TreeMap<Integer, Float>> opinions;
    private Vector<Vector<Integer>> clusters;

    public collaborativeFiltering(Map<Integer, TreeMap<Integer, Float>> opinions, Integer k){
        this.opinions = opinions;
        kmean Kmean = new kmean(opinions);
        this.clusters = Kmean.k_means(k);
    }


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

        TreeMap<Integer, TreeMap<Integer, Float>> valCluster = new TreeMap<Integer, TreeMap<Integer, Float>>();
        for(int i = 0; i < clusters.get(clusterUser).size(); ++i){
            valCluster.put(clusters.get(clusterUser).get(i), opinions.get(clusters.get(clusterUser).get(i)));
        }
        slopeone Slopeone = new slopeone();
        return slopeone.SlopeOne(valCluster);
    }
}
