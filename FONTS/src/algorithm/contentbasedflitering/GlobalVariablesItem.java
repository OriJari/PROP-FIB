package algorithm.contentbasedflitering;

import tipus.Tipus;

import java.util.List;
import java.util.Map;

public class GlobalVariablesItem {
    int[][] distanceTable;

    public GlobalVariablesItem() {}

    public void init_DistanceTable(Map<Integer, List<Tipus>> mapa) {
        //given a Map<int, List<Tipus>> with int = id and List<Tipus> = tags converted to bool/int/double
        int n = mapa.size();
        Item aux1, aux2;
        int distance;
        for (int i = 0; i < n; ++i) {
            aux1 = new Item(i);
            aux1.setTags(mapa.get(i));
            for (int j = i+1; j < n; ++j) {
                aux2 = new Item(j);
                aux2.setTags(mapa.get(j));
                distance = aux1.similarity_with(aux2);
                distanceTable[i][j] = distance;
                distanceTable[j][i] = distance;
            }
        }
    }

    public void addTag_Item(Item item, String tag) {
        item.addTag(tag);
        actualitza_map(item);
        actualitza_taula(item);
    }

    public void removeTag_item(Item item, String tag) {
        item.delTag(tag);
        actualitza_map(item);
        actualitza_taula(item);
    }


    public void actualitza_taula(Item item) {

    }

}