package dominio.clases.atribut;

import java.util.List;
/**
 * @class Atribut
 * @brief Structure of the Atribut (needed in a near future)
 * @author Miguel
 */

public class Atribut {
    List<String> atribut;

    /**
     * @brief Default builder
     */
    public Atribut(){
        this.atribut = null;
    }

    /**
     * @brief Default builder with parameters
     * @param atr attribute to attribute
     */
    public Atribut(List<String> atr){
        this.atribut = atr;
    }

    /**
     * @brief Getter of the attribute
     * @return the attribute
     */
    public List<String> getAtribut() {
        return atribut;
    }

    /**
     * @brief Setter of the attribute
     * @param atribut , new attribute to define to a previous one
     */
    public void setAtribut(List<String> atribut) {
        this.atribut = atribut;
    }
}
