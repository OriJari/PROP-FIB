package tipus;

import java.util.*;

public class Tipus {
    String tag;
    Integer tag_numi;
    Float tag_numf;

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
     * Getter of the tag_numf
     */
    public Float getTag_numf() {
        return tag_numf;
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
    public void setTag_numf(Float tag_numf) {
        this.tag_numf = tag_numf;
    }

    /**
     * Change a String value to an Integer one
     * @param s String to obtain the corresponding value
     * @return string s converted to integer
     */
    public Integer String_to_Int(String s){
        return Integer.parseInt(s);
    }

    /**
     * Change a String value to a Float one
     * @param s String to obtain the corresponding value
     * @return string s converted to float
     */
    public Float String_to_Float(String s){
        return Float.parseFloat(s);
    }


    /**
     * Obtains the appropriate preprocess of data set for the kk-neight algorithm
     * @param mapItem data set read from the csv
     * @return the estructure Map<Integer, List<Tipus>> mapitemdata with the adequate preprocess
     */
    public Map<Integer, List<Tipus>> MapItemData(Map<Integer, List<String>> mapItem){
        Iterator<Integer> it = mapItem.keySet().iterator();
        Map<Integer, List<Tipus>> mapitemdata = new TreeMap<>();
        while(it.hasNext()){
            Integer key = it.next();
            List<String> aux = mapItem.get(key);
            List<Tipus> newtagform = new ArrayList<>();
            for (String s : aux) {
                Tipus t = null;
                if (s.equals("False")) {
                    t.tag = s;
                    t.tag_numi = 0;
                    t.tag_numf = null;
                }
                if (s.equals("True")) {
                    t.tag = s;
                    t.tag_numi = 0;
                    t.tag_numf = null;
                }
                boolean b = true;
                Integer valI = null;
                try {
                    valI = String_to_Int(s);
                } catch (NumberFormatException e) {
                    b = false;
                }
                boolean b1 = true;
                Float valF = null;
                try {
                    valF = String_to_Float(s);
                } catch (NumberFormatException e) {
                    b1 = false;
                }
                if (b) {
                    t.tag = s;
                    t.tag_numi = valI;
                    t.tag_numf = valF;
                }
                if (b1) {
                    t.tag = s;
                    t.tag_numi = valI;
                    t.tag_numf = valF;
                }
                newtagform.add(t);
            }
            mapitemdata.put(key,newtagform);
        }
        return mapitemdata;
    }

    public static void main(String[] args) {

    }
}
