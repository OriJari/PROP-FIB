package content;

import java.util.ArrayList;

/**
 * @class Content
 * @brief  Type nedded to make a efficient proces of data sets for kk-neightbours
 * @author Miguel
 */

public class Content {
    private String tag;
    private Integer tag_numi;
    private Double tag_numd;
    private ArrayList<String> categorics;

    /**
     * @brief Default builder
     */
    public Content() {
        this.tag = null;
        this.tag_numi = null;
        this.tag_numd = null;
        this.categorics = null;

    }

    /**
     * @brief Default builder with initializers
     *
     * @param s type of the tag
     * @param i integer number associated
     * @param d double number associated
     * @param categorics list of the categoric elements
     */
    public Content(String s, Integer i, Double d, ArrayList<String> categorics) {
        this.tag = s;
        this.tag_numi = i;
        this.tag_numd = d;
        this.categorics = categorics;
    }

    /**
     * @brief Getter of the tag
     *
     * @return the content tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @brief Getter of the tag_numi
     *
     * @return the integer associated to the tag
     */
    public Integer getTag_numi() {
        return tag_numi;
    }

    /**
     * @brief Getter of the tag_numd
     *
     * @return the double associated to the tag
     */
    public Double getTag_numd() {
        return tag_numd;
    }

    /**
     * @brief Getter of categorics
     *
     * @return list of the categorics elements
     */
    public ArrayList<String> getCategorics() {
        return categorics;
    }

    /**
     * @brief Setter of the tag
     *
     * @param tag, attributes the new tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * @brief Setter of the tag_numi
     *
     * @param tag_numi , attributes the integer corresponded
     */
    public void setTag_numi(Integer tag_numi) {
        this.tag_numi = tag_numi;
    }

    /**
     * @brief Setter of the tag_numf
     *
     * @param tag_numd , attributes the double corresponded
     */
    public void setTag_numd(Double tag_numd) {
        this.tag_numd = tag_numd;
    }

    /**
     * @brief Setter of categorics
     *
     * @param categorics list of the elements categorics
     */
    public void setCategorics(ArrayList<String> categorics) {
        this.categorics = categorics;
    }
}
