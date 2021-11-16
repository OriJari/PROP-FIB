package content;

public class Content {
    private String tag;
    private Integer tag_numi;
    private Double tag_numd;

    /**
     * Default builder
     */
    public Content(){
        this.tag = null;
        this.tag_numi = null;
        this.tag_numd = null;

    }

    /**
     * Default builder with initializers
     * @param s type of the tag
     * @param i integer number associated
     * @param d double number associated
     */
    public Content(String s, Integer i, Double d){
        this.tag = s;
        this.tag_numi = i;
        this.tag_numd = d;

    }

    /**
     * Getter of the tag
     * @return the content tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * Getter of the tag_numi
     * @return the integer associated to the tag
     */
    public Integer getTag_numi() {
        return tag_numi;
    }

    /**
     * Getter of the tag_numd
     * @return the double associated to the tag
     */
    public Double getTag_numd() {
        return tag_numd;
    }

    /**
     * Setter of the tag
     * @param tag, attributes the new tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Setter of the tag_numi
     * @param tag_numi , attributes the integer corresponded
     */
    public void setTag_numi(Integer tag_numi) {
        this.tag_numi = tag_numi;
    }

    /**
     * Setter of the tag_numf
     * @param tag_numd , attributes the double corresponded
     */
    public void setTag_numf(Double tag_numd) {
        this.tag_numd = tag_numd;
    }


    public static void main(String[] args) {

    }
}
