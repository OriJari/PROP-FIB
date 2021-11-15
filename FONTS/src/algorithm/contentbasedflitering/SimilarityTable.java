package algorithm.contentbasedflitering;

import item.Item;
import content.Content;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class SimilarityTable {
    double[][] similarityTable;

    public SimilarityTable() {}

    public void initSimilarityTable(Map<Integer, List<Content>> map) {
        //given a Map<int, List<Content>> with int = id and List<Content> = tags converted to bool/int/double/string
        int n = map.size();
        double similarity;
        List<Content> list1, list2;
        for (int i = 0; i < n; ++i) {
            list1 = map.get(i);
            for (int j = i; j < n; ++j) {
                list2 = map.get(i);
                similarity = calculate_similarity(list1, list2);
                similarityTable[i][j] = similarity;
                similarityTable[j][i] = similarity;
            }
        }
    }

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

    public double calculate_similarity(List<Content> list1, List<Content> list2) {
        int size1 = list1.size();
        int size2 = list2.size();

        double bool_base_coincidence = 10.0;
        double int_base_coincidence = 10.0;
        double double_base_coincidence = 10.0;
        double categoric_base_coincidence = 10.0;
        double string_base_coincidence = 2.0;

        double simil = 0;

        for (int i = 0; i < size1 && i < size2; ++i) {
            String type1 = list1.get(i).getTag();
            String type2 = list2.get(i).getTag();
            if (type1.equals(type2)) {
                switch (type1) {
                    case "b" -> {
                        int bool1 = list1.get(i).getTag_numi();
                        int bool2 = list2.get(i).getTag_numi();
                        if (bool1 == bool2) simil = simil + bool_base_coincidence;
                    }
                    case "i" -> {
                        int int1 = list1.get(i).getTag_numi();
                        int int2 = list2.get(i).getTag_numi();
                        if (int1 == int2) simil = simil + int_base_coincidence;
                        else {
                            double new_sum = calculate_deviance(int1, int2, int_base_coincidence);
                            simil = simil + new_sum;
                        }
                    }
                    case "d" -> {
                        double double1 = list1.get(i).getTag_numd();
                        double double2 = list2.get(i).getTag_numd();
                        if (double1 == double2) simil = simil + double_base_coincidence;
                        else {
                            double new_sum = calculate_deviance(double1, double2, double_base_coincidence);
                            simil = simil + new_sum;
                        }
                    }
                    case "c" -> {
                        List<String> sublist1 = list1.get(i).getList();
                        List<String> sublist2 = list2.get(i).getList();
                        for (String s : sublist1) {
                            if (sublist2.contains(s)) simil = simil + categoric_base_coincidence;
                        }
                    }
                    default -> simil = simil + string_base_coincidence;
                }
            }
        }
        return simil;
    }


    public double calculate_deviance(double double1, double double2, double max_val) {
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

    public ArrayList<Item> kNN(Item item1) {
        int id = item1.getID();
        int k = 5; //number of items to recommend

        PriorityQueue<Pair> queue = new PriorityQueue<>();
        double min_similarity = -1.0;
        int n = similarityTable[id].length;
        for (int j = 0; j < n; ++j) {
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
                    min_similarity = queue.peek().getSimilarity();
                }
            }
        }
        ArrayList<Item> result = new ArrayList<Item>();
        for (int j = 0; j < k; ++j) {
            Item item = new Item(queue.poll().getId());
            result.add(item);
        }
        return result;
    }

}