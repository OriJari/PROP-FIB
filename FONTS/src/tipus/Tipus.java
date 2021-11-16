package tipus;

import java.util.ArrayList;
import java.util.List;

public class Tipus {

     private List<String> tipus;

    /**
     * Default builder
     */
    public Tipus(){
        this.tipus = new ArrayList<>();
    }

    /**
     * Default builder with parameters
     * @param type set of tipus that contains the tag identifier
     */
    public Tipus(List<String> type){
        this.tipus = type;
    }

    /**
     * Getter of the set of tags
     * @return the List of tags from the tag identifier
     */
    public List<String> getTipus(){
        return tipus;
    }


    /**
     * Setter of the set of tipus
     * @param tags set of tipus to attribute to the tag identifier
     */
    public void setTipus(List<String> tags) {
        this.tipus = tags;
    }
}