package tipus;

public class Tipus {
    String tipus;


    /**
     * Default builder
     */
    public Tipus(){
        this.tipus = null;
    }

    /**
     * Default builder with parameter
     * @param type of the element
     */
    public Tipus(String type){
        this.tipus = type;
    }

    /**
     * Getter of the type
     * @return the type of the element
     */
    public String getTipus() {
        return tipus;
    }

    /**
     * Setter of the type
     * @param tipus, new type to define
     */
    public void setTipus(String tipus) {
        this.tipus = tipus;
    }
}
