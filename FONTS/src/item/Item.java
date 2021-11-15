package item;

import java.util.*;

//one-hot -> pasar a bits (interseccion y union en java mas facil)

public class Item {
    int ID;
    List<String> tags;

    /**
     * Default builder
     */
    public Item() {
        ID = 0;
        tags = new ArrayList<>();
    }
    /**
     * Default builder including the item id.
     * @param id , id which item is identified
     */
    public Item(int id) {
        ID = id;
        tags = new ArrayList<>();
    }

    /**
     * Getter of the item id
     */
    public Integer getID() {
        return this.ID;
    }

    /**
     * Get the tag of the list in a determinate position
     * @param i , position of the tag in the List
     */
    public List<String> getTags(int i) {
        return Collections.singletonList(this.tags.get(i));
    }

    /**
     * Get the number of tags that an item has.
     */
    public Integer getNumTags() {
        return tags.size();
    }


    /**
     * Add a tag to the set of tags
     * @param tag1 , element to insert to the set
     */
    public void addTag(String tag1) {
        int n = tags.length;
        String[] new_tags = new String[n+1];
        int i;
        for (i = 0; i < n; i++) new_tags[i] = tags[i];
        new_tags[n] = tag1;
        tags = new_tags;
    }

    public Boolean exsistTag (String tag, List<String>){

    }

    public void delTag(String tag1) {
        //...
    }
    public void setID(int id) {
        this.ID = id;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public int similarity_with(Item item2) {
        int n1 = this.getNumTags();
        int n2 = item2.getNumTags();
        String[] tags1 = this.getTags();
        String[] tags2 = item2.getTags();
        int result = 0;
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if (tags1[i] == tags2[j]) ++result;
            }
        }
        return result;
    }
}