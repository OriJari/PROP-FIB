package tipus;

import java.util.ArrayList;
import java.util.TreeMap;

public class Tipus {
    String tag;
    Integer tag_numi;
    Double tag_numd;

    /**
     * Default builder.
     */
    public Tipus(){
        this.tag = null;
        this.tag_numi = null;
        this.tag_numd = null;
    }

    /**
     * Getter of the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * Getter of the tag_numi
     */
    public Integer getTag_numi() {
        return tag_numi;
    }

    /**
     * Getter of the tag_numd
     */
    public Double getTag_numd() {
        return tag_numd;
    }

    /**
     * Setter of the tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Setter of the tag_numi
     */
    public void setTag_numi(Integer tag_numi) {
        this.tag_numi = tag_numi;
    }

    /**
     * Setter of the tag_numf
     */
    public void setTag_numf(Double tag_numd) {
        this.tag_numd = tag_numd;
    }


    public static void main(String[] args) {

    }
}
