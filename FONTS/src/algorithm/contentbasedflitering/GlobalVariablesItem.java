package algorithm.contentbasedflitering;

import item.Item;
import tipus.Tipus;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class GlobalVariablesItem {
    double[][] SimilarityTable;

    public GlobalVariablesItem() {}

    public void init_DistanceTable(Map<Integer, List<Tipus>> mapa) {
        //given a Map<int, List<Tipus>> with int = id and List<Tipus> = tags converted to bool/int/double
        int n = mapa.size();
        double similarity;
        List<Tipus> list1, list2;
        for (int i = 0; i < n; ++i) {
            list1 = mapa.get(i);
            for (int j = i; j < n; ++j) {
                list2 = mapa.get(i);
                similarity = calculate_similarity(list1, list2);
                SimilarityTable[i][j] = similarity;
                SimilarityTable[j][i] = similarity;
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

    public double calculate_similarity(List<Tipus> list1, List<Tipus> list2) {
        int size1 = list1.size();
        int size2 = list2.size();

        double bool_base_coincidence = 10.0;
        double int_base_coincidence = 10.0;
        double double_base_coincidence = 10.0;
        double string_base_coincidence = 2.0;

        double simil = 0;

        for (int i = 0; i < size1 && i < size2; ++i) {
            String type1 = list1.get(i).getTag();
            String type2 = list2.get(i).getTag();
            if (type1.equals(type2)) {
                if (type1.equals("b")) {
                    int bool1 = list1.get(i).getTag_numi();
                    int bool2 = list2.get(i).getTag_numi();
                    if (bool1 == bool2) simil = simil + bool_base_coincidence;
                }
                else if (type1.equals("i")) {
                    int int1 = list1.get(i).getTag_numi();
                    int int2 = list2.get(i).getTag_numi();
                    if (int1 == int2) simil = simil + int_base_coincidence;
                    else {
                        double new_sum = calculate_deviance_int(int1, int2, int_base_coincidence);
                        simil = simil + new_sum;
                    }
                }
                else if (type1.equals("d")) {
                    double double1 = list1.get(i).getTag_numd();
                    double double2 = list2.get(i).getTag_numd();
                    if (double1 == double2) simil = simil + double_base_coincidence;
                    else {
                        double new_sum = calculate_deviance_double(double1, double2, double_base_coincidence);
                        simil = simil + new_sum;
                    }
                }
                else simil = simil + string_base_coincidence;
            }
        }
        return simil;
    }

    public double calculate_deviance_int(int int1, int int2, double max_val) {
        return calculate_deviance_double((double)int1, (double)int2, max_val);
    }

    public double calculate_deviance_double(double double1, double double2, double max_val) {
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

    public List<Item> kNN(Item item1) {
        int id = item1.getID();
        int k = 5; //number of items to recommend
        PriorityQueue<Double> min_distances
        double current_min_similarity = -1.0;

        for (int i = 0; i < SimilarityTable[id].length; ++i) {
            double aux = SimilarityTable[id][i];
            if (aux > current_min_similarity) {
                current_min_similarity = aux;
            }
        }
    }

}