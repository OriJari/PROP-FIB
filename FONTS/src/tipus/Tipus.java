package tipus;

import java.util.ArrayList;
import java.util.List;

/**
 * @class Tipus
 * @brief Structure of the tipus (needed in a near future)
 * @author Miguel
 */

public class Tipus {

     private List<String> tipus;

    /**
     * @brief Default builder
     */
    public Tipus(){
        this.tipus = new ArrayList<>();
    }

    /**
     * @brief Default builder with parameters
     * @param type set of tipus that contains the tag identifier
     */
    public Tipus(List<String> type){
        this.tipus = type;
    }

    /**
     * @brief Getter of the set of tags
     * @return the List of tags from the tag identifier
     */
    public List<String> getTipus(){
        return tipus;
    }


    /**
     * @brief Setter of the set of tipus
     * @param tags set of tipus to attribute to the tag identifier
     */
    public void setTipus(List<String> tags) {
        this.tipus = tags;
    }
}