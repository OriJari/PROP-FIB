package dominio.clases.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public Item(int id, List<String> tags) {
        ID = id;
        this.tags = tags;
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
     * @param tag , element to insert to the set
     */
    public void addTag(String tag) {
        this.tags.add(tag);
     }

    /**
     * Check if the item contains a given tag.
     * @param tag , value to check
     * @return      true if present, false if not.
     */
    public Boolean existsTag (String tag){
        return this.tags.contains(tag);
    }

    /**
     * Delete a given tag from the list of tags of the item.
     * @param tag , value to delete
     */
    public void delTag(String tag) {
        this.tags.remove(tag);
    }

    /**
     * Setter of the item's id
     * @param id ,  the new id of the item
     */
    public void setID(int id) {
        this.ID = id;
    }

    /**
     * Setter of the tags
     * @param tags ,    the new list of tags of the item
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}