import java.io.Map
import java.util.List
import Item

public class GlobalVariablesItem {
    int[][] distanceTable;
    List <Item> itemList;

    public void init_ItemList() {
        //given a Map<int, String[]> where int is ItemID and String[] is tags (from the DataSet).
        int n = map.size();
        itemList = new ArrayList<Item>();
        Item auxiliar;
        for (int i = 0; i < n; ++i) {
            auxiliar = new Item(i);
            auxiliar.setTags(map.get(i));
            itemList.add(auxiliar);
        }
    }

    public void init_DistanceTable() {
        int n = itemList.size();
        Item aux1, aux2;
        int distance;
        for (int i = 0; i < n; ++i) {
            aux1 = itemList.get(i);
            for (int j = i+1; j < n; ++j) {
                aux2 = itemList.get(j);
                distance = aux1.similarity_with(aux2);
                distanceTable[i][j] = distance;
                distanceTable[j][i] = distance;
            }
        }
    }

    public void addTag_Item(Item item, String tag) {
        item.addTag(tag);
        actualitza_llista(item);
        actualitza_taula(item);
    }

    public void removeTag_item(Item item, String tag) {
        item.delTag(tag);
        actualitza_llista(item);
        actualitza_taula(item);
    }

    public void actualitza_llista(Item item) {

    }

    public void actualitza_taula(Item item) {

    }
}