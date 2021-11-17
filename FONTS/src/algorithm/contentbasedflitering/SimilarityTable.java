package algorithm.contentbasedflitering;

import item.Item;
import content.Content;

import java.util.*;

public class SimilarityTable {
    /**
     * Table used to store similarities between all items
     */
    double[][] similarityTable;

    /**
     * Default builder
     */
    public SimilarityTable() {}

    /**
     * Initialize the similarity table between the items present in the map.
     * Similarity table is normalized; similarities range between 0 and 1 (1 if items are identical).
     * @param map       integer is the ID of the item, ArrayList is a list of its tags
     */
    public void initSimilarityTable(Map<Integer, ArrayList<Content>> map) {
        //given a Map<int, List<Content>> with int = id and List<Content> = tags converted to bool/int/double/string
        int n = map.size();
        similarityTable = new double[n][n];
        double similarity;
        ArrayList<Content> list1, list2;
        for (int i = 0; i < n; ++i) {
            list1 = map.get(i);
            for (int j = 0; j < n; ++j) {
                list2 = map.get(j);
                similarity = calculate_similarity(list1, list2);
                similarityTable[i][j] = similarity;
            }
        }
        double normalization;
        for (int i = 0; i < n; ++i) {
            normalization = similarityTable[i][i];
            for (int j = 0; j < n; ++j) similarityTable[i][j] = similarityTable[i][j]/normalization;
        }
    }

    //FUNCTIONS NOT NEEDED FOR FIRST DELIVERY
    //public void addTag_Item(Item item, String tag) {
    //    item.addTag(tag);
    //    actualitza_map(item);
    //    actualitza_taula(item);
    //}

    //public void removeTag_item(Item item, String tag) {
    //    item.delTag(tag);
    //    actualitza_map(item);
    //    actualitza_taula(item);
    //}


    //public void actualitza_taula(Item item) {

    //}

    /**
     * Calculates the similarity between two items, given their respective tags.
     * Similarity is calculated based on the type of the tags. Having a coincidence on a
     * boolean value does not contribute the same as having a coincidence on a string.
     * For coincidences on integers and doubles, similarity is calculated based on variance
     * between the two integers/doubles.
     *
     * @param list1     tags of the first item to compare
     * @param list2     tags of the second item to compare
     * @return          the similarity between these two items
     */
    private double calculate_similarity(ArrayList<Content> list1, ArrayList<Content> list2) {
        int size1 = list1.size();
        int size2 = list2.size();

        double bool_base_coincidence = 30.0;
        double int_base_coincidence = 5.0;
        double double_base_coincidence = 5.0;
        double categoric_base_coincidence = 20.0;
        double string_base_coincidence = 2.0;

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
                        ArrayList<String> sublist1 = list1.get(i).getCategorics();
                        ArrayList<String> sublist2 = list2.get(i).getCategorics();
                        for (String s : sublist1) {
                            if (sublist2.contains(s)) simil = simil + categoric_base_coincidence;
                        }
                        break;
                    }
                    default : simil = simil + string_base_coincidence;
                }
            }
        }
        return simil;
    }

    /**
     * Used to calculate the value of similarity between two integers/doubles.
     * @param double1   first number
     * @param double2   second number
     * @param max_val   value of similarity if numbers are identical
     * @return          value of similarity between the numbers
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
     * Finds the k most similar items to the given one
     * @param id    id of the item we want to find a recommendation for
     * @param k     number of items we want to get recommended
     * @return      the k most similar items to the id item
     */
    public ArrayList<Item> kNN(int id, int k) {
        PriorityQueue<Pair> queue = new PriorityQueue<>();
        double min_similarity = -1.0;
        int n = similarityTable[id].length;
        for (int j = 0; j < n; ++j) {
            if (id != j) {
                double similarity_aux = similarityTable[id][j];
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
        ArrayList<Item> result = new ArrayList<>();
        for (int j = 0; j < k; ++j) {
            Item item = new Item(Objects.requireNonNull(queue.poll()).getId());
            result.add(item);
        }
        return result;
    }

    /**
     * Prints the similarity matrix on console
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
}