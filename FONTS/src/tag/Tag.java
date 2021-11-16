package tag;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Tag {

    Integer tagID;
    List<String> tags;
    Map<Integer, List<String>> conjunt = new TreeMap<>();

    /**
     * Default builder
     */
    public Tag(){
        this.tagID = null;
        this.tags = null;
    }

    /**
     * Default builder with parameters
     * @param tag identifier to attribute
     * @param ntags set of tags that contains the tag identifier
     */
    public Tag(Integer tag, List<String> ntags){
        this.tagID = tag;
        this.tags = ntags;
    }

    /**
     * Getter of the tag identifier
     * @return the identifier of the tag
     */
    public Integer getEtiquetaID() {
        return tagID;
    }

    /**
     * Getter of the set of tags
     * @return the List of tags from the tag identifier
     */
    public List<String> getEtiquetas(){
        return tags;
    }

    /**
     * Setter of the tag identifier
     * @param tagID tag identifier to attribute
     */
    public void setEtiquetaID(Integer tagID) {
        this.tagID = tagID;
    }

    /**
     * Setter of the set of tags from the tag identifier
     * @param tags set of tags to attribute to the tag identifier
     */
    public void setEtiquetas(List<String> tags) {
        this.tags = tags;
    }
}
