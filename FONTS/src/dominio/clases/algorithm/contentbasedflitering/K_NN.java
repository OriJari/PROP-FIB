package dominio.clases.algorithm.contentbasedflitering;

import dominio.clases.content.Content;
import dominio.clases.rating.Rating;
import dominio.clases.recommendation.Recommendation;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Marc Delgado Sánchez
 */

/** @class K_NN
 *  @brief Implements K-Nearest-Neighbours algorithm.
 */
public class K_NN {
    /**
     * @brief Table used to store similarities between all items
     */
    private double[][] similarityTable;
    private Map<Integer,Map<Integer,Float>> mapa_known;
    private Map<Integer,Map<Integer,Float>> mapa_unknown;
    private Map<Integer,List<Content>> mapa_items;
    List<Integer> id_reals;

    /**
     * @brief Default builder
     * \pre <em>True</em>
     * \post An object of the K_NN class is created with mapa_usuarios and id_reals equal to the given parameters
     */
    public K_NN(Map<Integer,Map<Integer,Float>> mapa, Map<Integer,Map<Integer,Float>> mapa_unknown, Map<Integer, List<Content>> map, List<Integer> ids) {
        this.mapa_known = mapa;
        this.mapa_unknown = mapa_unknown;
        this.mapa_items = map;
        this.id_reals = ids;
    }

    /**
     * @brief Initialize the similarity table between the items present in the map.
     * Similarity table is normalized; similarities range between 0 and 1 (1 if items are identical).
     * @param map       integer is the ID of the item, ArrayList is a list of its tags
     * \pre <em>True</em>
     * \post Similarity between all items has been calculated and stored in <em>similarityTable</em>
     */
    public void initSimilarityTable() {
        int n = mapa_items.size();
        similarityTable = new double[n][n];
        double similarity;
        List<Content> list1, list2;
        for (int i = 0; i < n; ++i) {
            list1 = mapa_items.get(id_reals.get(i));
            for (int j = i; j < n; ++j) {
                list2 = mapa_items.get(id_reals.get(j));
                similarity = calculate_similarity(list1, list2);
                similarityTable[i][j] = similarity;
                if (j != i) similarityTable[j][i] = similarity;
            }
        }
        double normalization;
        for (int i = 0; i < n; ++i) {
            normalization = similarityTable[i][i];
            for (int j = 0; j < n; ++j) similarityTable[i][j] = similarityTable[i][j]/normalization;
        }
    }

    public void Mod_Item(int item_id, List<Content> tags) {
        int id_fals = id_reals.indexOf(item_id);
        mapa_items.put(item_id,tags);
        actualitza_taula(id_fals);
    }

    public void actualitza_taula(int item_id){
        int mida = similarityTable.length;
        List<Content> content1 = mapa_items.get(id_reals.get(item_id));
        double normalization = calculate_similarity(content1, content1);
        double normalization_i;
        for (int i = 0; i < mida && i != item_id; ++i) {
            List<Content> content2 = mapa_items.get(id_reals.get(i));
            normalization_i = calculate_similarity(content2, content2);
            double sim = calculate_similarity(content1, content2);
            similarityTable[i][item_id] = sim/normalization_i;
            similarityTable[item_id][i] = sim/normalization;
        }
        similarityTable[item_id][item_id] = 1.0;
    }

    public void Add_Item(int item_id, List<Content> tags) {
        id_reals.add(item_id);
        int id_fals = id_reals.indexOf(item_id);
        mapa_items.put(item_id,tags);
        nou_item_taula(id_fals);
    }

    public void nou_item_taula(int item_id) {
        int n = mapa_items.size();
        List<Content> content1 = mapa_items.get(id_reals.get(item_id));
        double[][] new_table = new double[n][n];
        double normalization = calculate_similarity(content1, content1);
        double normalization_i;
        for (int i = 0; i < n-1; ++i) {
            System.arraycopy(similarityTable[i], 0, new_table[i], 0, n - 1);
            List<Content> content2 = mapa_items.get(id_reals.get(i));
            normalization_i = calculate_similarity(content2, content2);
            double sim = calculate_similarity(content1, content2);
            new_table[i][item_id] = sim/normalization_i;
            new_table[item_id][i] = sim/normalization;
        }
        similarityTable = new_table;
    }

    public void modifica_map_rating(int id_user, int id_item, float valoracio) {
        mapa_known.get(id_user).put(id_item,valoracio);
    }

    /**
     * @brief Calculates the similarity between two items, given their respective tags.
     * Similarity is calculated based on the type of the tags. Having a coincidence on a
     * boolean value does not contribute the same as having a coincidence on a string.
     * For coincidences on integers and doubles, similarity is calculated based on variance
     * between the two integers/doubles.
     *
     * @param list1     tags of the first item to compare
     * @param list2     tags of the second item to compare
     * @return          the similarity between these two items
     *
     * \pre <em>True</em>
     * \post Similarity between two given items is calculated and returned.
     */
    private double calculate_similarity(List<Content> list1, List<Content> list2) {
        int size1 = list1.size();
        int size2 = list2.size();

        double bool_base_coincidence = 7.5;
        double int_base_coincidence = 2.5;
        double double_base_coincidence = 2.5;
        double categoric_base_coincidence = 5.0;
        double string_base_coincidence = 2.5;

        double simil = 0;

        for (int i = 0; i < size1 && i < size2; ++i) {
            String type1 = list1.get(i).getTag();
            String type2 = list2.get(i).getTag();
            if (type1.equals(type2)) {
                switch (type1) {
                    case "b" : {
                        int bool1 = list1.get(i).getTag_numi();
                        int bool2 = list2.get(i).getTag_numi();
                        if (bool1 == bool2) simil = simil + bool_base_coincidence;
                        break;
                    }
                    case "i" : {
                        int int1 = list1.get(i).getTag_numi();
                        int int2 = list2.get(i).getTag_numi();
                        if (int1 == int2) simil = simil + int_base_coincidence;
                        else {
                            double new_sum = calculate_deviance(int1, int2, int_base_coincidence);
                            simil = simil + new_sum;
                        }
                        break;
                    }
                    case "d" : {
                        double double1 = list1.get(i).getTag_numd();
                        double double2 = list2.get(i).getTag_numd();
                        if (double1 == double2) simil = simil + double_base_coincidence;
                        else {
                            double new_sum = calculate_deviance(double1, double2, double_base_coincidence);
                            simil = simil + new_sum;
                        }
                        break;
                    }
                    case "c" : {
                        List<String> sublist1 = list1.get(i).getCategorics();
                        List<String> sublist2 = list2.get(i).getCategorics();
                        List<String> aux = sublist1.stream().distinct().filter(sublist2::contains).collect(Collectors.toList());

                        for (String s : aux) simil = simil + categoric_base_coincidence;
                        break;
                    }
                    default : simil = simil + string_base_coincidence;
                }
            }
        }
        return simil;
    }

    /**
     * @brief Used to calculate the value of similarity between two integers/doubles.
     * @param double1   first number
     * @param double2   second number
     * @param max_val   value of similarity if numbers are identical
     * @return          value of similarity between the numbers
     *
     * \pre <em>True</em>
     * \post Similarity between two given numbers is calculated and returned.
     */
    private double calculate_deviance(double double1, double double2, double max_val) {
        double bigger, other;
        if (double1 > double2) {
            bigger = double1;
            other = double2;
        }
        else {
            bigger = double2;
            other = double1;
        }
        double variance = (bigger - other)/bigger;
        return (1-variance) * max_val;
    }

    /**
     * @brief Finds the k most similar items to the given one
     * @param id_item    id_item of the item we want to find a recommendation for
     * @param k     number of items we want to get recommended
     * @return      the k most similar items to the id_item item
     *
     * \pre <em>id_item</em> corresponds to an existing item. <em>id_usuari</em> corresponds to an existing user.
     * \post K-Nearest-Neighbours of the given item (which haven't been rated by the user) are calculated and returned.
     */
    public List<Pair> kNN(int id_item, int k, int id_usuari, boolean valoration) {
        PriorityQueue<Pair> queue = new PriorityQueue<>();
        Map<Integer,Float> valorats = mapa_known.get(id_usuari);
        Map<Integer,Float> unknown = mapa_unknown.get(id_usuari);

        double min_similarity = -1.0;
        int n = similarityTable[id_item].length;
        boolean comprobant;
        for (int j = 0; j < n; ++j) {
            int id_real = id_reals.get(j);
            if (valoration) comprobant = (id_item != j && !valorats.containsKey(id_real) && unknown.containsKey(id_real));
            else comprobant = (id_item != j && !valorats.containsKey(id_real));

            if (comprobant) {
                double similarity_aux = similarityTable[id_item][j];
                if (queue.size() == 0) {
                    queue.add(new Pair(j,similarity_aux));
                    min_similarity = similarity_aux;
                }
                else if (queue.size() < k) {
                    queue.add(new Pair(j, similarity_aux));
                    if (similarity_aux < min_similarity) min_similarity = similarity_aux;
                }
                else {
                    if (similarity_aux > min_similarity) {
                        queue.poll();
                        queue.add(new Pair(j, similarity_aux));
                        assert queue.peek() != null;
                        min_similarity = queue.peek().getSimilarity();
                    }
                }
            }
        }
        List<Pair> result = new ArrayList<>();
        int mida = queue.size();
        for (int j = 0; j < mida; ++j) {
            Pair aux = queue.poll();
            result.add(aux);
        }
        return result;
    }

    /**
     * @brief Finds the k most suitable items to recommend to the given user.
     * @param id_usuari id of the user who wants a recommendation
     * @param k         number of items to recommend
     * @return          k most suitable items to recommend to <em>id_usuari</em> with their global similarities
     *
     * \pre <em>id_usuari</em> corresponds to an existing user.
     * \post the k most suitable items to recommend to <em>id_usuari</em> are calculated and returned
     */
    public Recommendation recommend(int id_usuari, int k, boolean valoration) {
        Map<Integer,Float> valoracions = mapa_known.get(id_usuari);
        Map<Integer,Float> auxiliar = new TreeMap<>();
        Map<Integer, Float> sum_simil = new TreeMap<>();
        Recommendation resultat = new Recommendation();
        List<Pair> k_nn;

        for (Map.Entry<Integer,Float> entry : valoracions.entrySet()) {
            int id_item = entry.getKey();
            float val = entry.getValue();
            int id_fals = id_reals.indexOf(id_item);
            k_nn = kNN(id_fals, k, id_usuari, valoration);
            for (int i = 0; i < k_nn.size(); ++i) {
                int id_item_knn = k_nn.get(i).getId();
                if (auxiliar.containsKey(id_item_knn)) {
                    sum_simil.replace(id_item_knn, (float) (sum_simil.get(id_item_knn) + k_nn.get(i).getSimilarity()));
                    auxiliar.replace(id_item_knn,(float)(auxiliar.get(id_item_knn) + k_nn.get(i).getSimilarity()*val));
                }
                else {
                    sum_simil.put(id_item_knn, (float)(k_nn.get(i).getSimilarity()));
                    auxiliar.put(id_item_knn, (float)(k_nn.get(i).getSimilarity()*val));
                }
            }
        }

        for(Map.Entry<Integer, Float> entry: auxiliar.entrySet()){
            auxiliar.put(entry.getKey(), entry.getValue()/sum_simil.get(entry.getKey()));
        }

        if (auxiliar.size() > k) {
            for (int i = 0; i < k; ++i) {
                Iterator<Integer> it = auxiliar.keySet().iterator();
                int maxitemID = it.next();
                for (Map.Entry<Integer, Float> entry : auxiliar.entrySet()) {
                    if (entry.getValue() > auxiliar.get(maxitemID)) {
                        maxitemID = entry.getKey();
                    }
                }
                resultat.addRating(new Rating(id_reals.get(maxitemID), auxiliar.get(maxitemID)));
                auxiliar.remove(maxitemID);
            }
        }
        else {
            for (Map.Entry<Integer,Float> entry: auxiliar.entrySet()) {
                resultat.addRating(new Rating(id_reals.get(entry.getKey()),entry.getValue()));
            }
        }
        resultat.sortR();
        resultat.setAlg(1);
        return resultat;

    }

    /**
     * @brief Prints the similarity matrix on console
     * \pre <em>similarityTable</em> has been initialized
     * \post <em>similarityTable</em> has been printed on console
     */
    public void print_similarity_matrix() {
        int n = similarityTable.length;
        for (double[] similarities : similarityTable) {
            int j;
            for (j = 0; j < n - 1; ++j) {
                System.out.print(similarities[j]);
                System.out.print(' ');
            }
            System.out.println(similarities[j]);
        }
    }

    public double getSimilarity(int i, int j) {
        return similarityTable[i][j];
    }
}