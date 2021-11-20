package dominio.clases.tag;

import java.util.ArrayList;
import java.util.List;

/**
 * @class Tag
 * @brief Structure of the tags (needed in a near future)
 * @author Miguel
 */

public class Tag {

    private List<String> tags;

    /**
     * @brief Default builder
     */
    public Tag(){
        this.tags = new ArrayList<>();
    }

    /**
     * @brief Default builder with parameters
     * @param ntags set of tags that contains the dominio.controladores.clases.atribut.tag identifier
     */
    public Tag(List<String> ntags){
        this.tags = ntags;
    }

    /**
     * @brief Getter of the set of tags
     * @return the List of tags from the dominio.controladores.clases.atribut.tag identifier
     */
    public List<String> getEtiquetas(){
        return tags;
    }


    /**
     * @brief Setter of the set of tags from the dominio.controladores.clases.atribut.tag identifier
     * @param tags set of tags to attribute to the dominio.controladores.clases.atribut.tag identifier
     */
    public void setEtiquetas(List<String> tags) {
        this.tags = tags;
    }
}
