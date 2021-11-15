package algorithm.kmean;

import java.util.*;

public class kmean {

        int k;
        Map<Integer, Map<Integer, Float>> opinions;

        /**
         * Default builder
         * @param k , number of clusters we want
         * @param opinions , valorations of all users
         */
        public void kmean(int k, Map<Integer, Map<Integer, Float>> opinions){
                this.k = k;
                this.opinions = opinions;
        }

        //0-1
        public static float cosineSquaredSimil(Map<Integer, Float> u1, Map<Integer, Float> u2){
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

        public static boolean equalClusters(Vector<Vector<Integer>> nuevoClusters, Vector<Vector<Integer>> clusters){
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

        public Vector<Vector<Integer>> k_means(){ // k <= opinions.size()
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
                        for (Map.Entry<Integer, Map<Integer, Float>> entry : opinions.entrySet()) {
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