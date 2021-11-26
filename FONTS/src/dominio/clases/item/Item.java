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
     * Get the number of tags that an dominio.controladores.clases.atribut.item has.
     */
    public Integer getNumTags() {
        return tags.size();
    }

    /*
    /**
     * Add a tag to the set of tags
     * @param tag1 , element to insert to the set
     */
    public void addTag(String tag1) {
        this.tags.add(tag1);
     }

    public Boolean existsTag (String tag){
        return this.tags.contains(tag);
    }

    public void delTag(String tag1) {
        this.tags.remove(tag1);
    }

    public void setID(int id) {
        this.ID = id;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}