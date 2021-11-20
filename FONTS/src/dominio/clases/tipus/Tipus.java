package dominio.clases.tipus;

import java.util.ArrayList;
import java.util.List;

/**
 * @class Tipus
 * @brief Structure of the dominio.controladores.clases.atribut.tipus (needed in a near future)
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
     * @param type set of dominio.controladores.clases.atribut.tipus that contains the dominio.controladores.clases.atribut.tag identifier
     */
    public Tipus(List<String> type){
        this.tipus = type;
    }

    /**
     * @brief Getter of the set of tags
     * @return the List of tags from the dominio.controladores.clases.atribut.tag identifier
     */
    public List<String> getTipus(){
        return tipus;
    }


    /**
     * @brief Setter of the set of dominio.controladores.clases.atribut.tipus
     * @param tags set of dominio.controladores.clases.atribut.tipus to attribute to the dominio.controladores.clases.atribut.tag identifier
     */
    public void setTipus(List<String> tags) {
        this.tipus = tags;
    }
}