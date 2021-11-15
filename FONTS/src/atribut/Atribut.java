package atribut;

import java.util.List;

public class Atribut {
    String atribut;

    /**
     * Default builder
     */
    public Atribut(){
        this.atribut = null;
    }

    /**
     * Default builder with parameters
     * @param atr attribute to attribute
     */
    public Atribut(String atr){
        this.atribut = atr;
    }

    /**
     * Getter of the attribute
     * @return the attribute
     */
    public String getAtribut() {
        return atribut;
    }

    /**
     * Setter of the attribute
     * @param atribut , new attribute to define to a previous one
     */
    public void setAtribut(String atribut) {
        this.atribut = atribut;
    }
}
