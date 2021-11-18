package preprocessat;

import content.Content;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


/**
 * @class CSVparserItem
 * @brief Implements the lecture and the proces of data of the csv
 * @author Miguel
 */

public class CSVparserItem {

    private Integer numCols, numRows;
    private String path;
    private List<List<String>> content;
    private List<String> header;
    private Map<Integer, List<Content>> mapRatedata;
    private List<Integer> id_Items;

    /**
     * @brief Default builder.
     * @param path where is located the csv document
     */
    public CSVparserItem(String path){
        this.path = path;
        this.numCols = 0;
        this.numRows = 0;
        this.content = new ArrayList<>();
        this.mapRatedata = new TreeMap<>();
        this.header = new ArrayList<>();
        this.id_Items = new ArrayList<>();
    }

    /**
     * @brief Getter of the num rows
     * @return the number of rows of the csv
     */
    public Integer getNumRows() {
        return numRows;
    }

    /**
     * @brief Getter of the num columns
     * @return the number of columns of the csv
     */
    public Integer getNumCols() {
        return numCols;
    }

    /**
     * @brief Getter of the header
     * @return the header of the csv as a List
     */
    public List<String> getHeader() {
        return header;
    }

    /**
     * @brief Getter of the path of the location of the csv
     * @return the path as String
     */
    public String getPath() {
        return path;
    }

    /**
     * @brief Getter of the class, gets the content
     * @return the lecture of the document csv
     */
    public List<List<String>> getContent() {
        return content;
    }

    /**
     * @brief Getter of the class, gets the mapRatedata
     * @return set of data structured
     */
    public Map<Integer, List<Content>> getMapRatedata() {
        return mapRatedata;
    }

    /**
     * @brief Getter of the class, gets the set of id items
     * @return the array list of ths different items of the csv
     */
    public List<Integer> getId_Items() {
        return id_Items;
    }

    /**
     * @brief Setter of the class, modified the number of rows
     * @param numRows, new size of the number of rows
     */
    public void setNumRows(Integer numRows) {
        this.numRows = numRows;
    }

    /**
     * @brief Setter of the class, modified the number of columns
     * @param numCols, new size of the number of columns
     */
    public void setNumCols(Integer numCols) {
        this.numCols = numCols;
    }

    /**
     * @brief Setter of the class, modified the elements of the header
     * @param header, header of the csv as a list
     */
    public void setHeader(List<String> header) {
        this.header = header;
    }

    /**
     * @brief Setter of the class, modified the path where is located ths csv
     * @param path, where the document is located, as string
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @brief Setter of the class, gets the conent
     * @param content, lecture of the csv document to attribute
     */
    public void setContent(List<List<String>> content) {
        this.content = content;
    }

    /**
     * @brief Setter of the class, gets the mapRatedata
     * @param mapRatedata, set of data to redefine a previous one
     */
    public void setMapRatedata(Map<Integer, List<Content>> mapRatedata) {
        this.mapRatedata = mapRatedata;
    }

    /**
     * @brief Setter of the class, set the id of the items
     * @param id_Items, set of data to redefine a previous one
     */
    public void setId_Items(List<Integer> id_Items) {
        this.id_Items = id_Items;
    }

    /**
     * @brief Get the header of the csv.
     * @param path where the document csv is located
     */
    public void obtainHeader(String path){
        FileInputStream fis;
        try {
            fis = new FileInputStream(this.path);
            Scanner sc = new Scanner(fis);
            String h = sc.nextLine();
            header = Arrays.asList(h.split(","));
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * @brief obtains the position where is located the id in csv
     * @param header of the csv
     * @return the position where are located the id in the header
     */
    public Integer obten_id_header(List<String> header){
        if (header.contains("id")){
            return header.indexOf("id");
        }
        return -1;
    }

    /**
     * @brief Read the content of the item csv into memory.
     */
    public void readLoadItem(){
        FileInputStream fis;
        try {
            fis = new FileInputStream(this.path);
            Scanner sc = new Scanner(fis);
            obtainHeader(this.path);
            sc.nextLine(); //without header
            //For each line
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                List<String> splitContent = new ArrayList<>(Arrays.asList(line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1)));
                content.add(splitContent);
                //Update cols&rows
                this.numRows += 1;
                this.numCols = Math.max(splitContent.size(), numCols);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Change a String value to an Integer one
     * @param s String to obtain the corresponding value
     */
    public  Integer String_to_Int(String s){
        return Integer.parseInt(s);
    }


    /**
     * @brief Change a String value to a Double one
     * @param s String to obtain the corresponding value
     * @return string s converted to double
     */
    public Double String_to_Double(String s){
        return Double.parseDouble(s);
    }


    /**
     * @brief Lecture of the List<List<String>> rows
     * @param i number of the row to obtain
     */
    public String getRow(int i) {
        return String.valueOf(this.content.get(i));
    }

    /**
     * @brief Obtains the appropriate preprocess of data set for the kk-neight algorithm
     * @param rate_content data set read from the csv
     */
    public void MapItemData(List<List<String>> rate_content){
        int index = 0;
        int id_pos = obten_id_header(this.header);
        for (List<String> aux : rate_content) {
            int pos = 0;
            List<Content> newtagform = new ArrayList<>();
            for (String s : aux) {
                if (pos == id_pos){
                    id_Items.add(String_to_Int(s));
                }
                boolean act = true;
                Content t = new Content();
                if (s.equals("False")) {
                    t.setTag("b");
                    t.setTag_numi(0);
                    t.setTag_numd(-1.0);
                    t.setCategorics(null);
                    act = false;
                }
                if (s.equals("True")) {
                    t.setTag("b");
                    t.setTag_numi(1);
                    t.setTag_numd(-1.0);
                    t.setCategorics(null);
                    act = false;
                }
                Double valD = -1.0;
                boolean bd = true;
                boolean bi = true;
                try {
                    valD = String_to_Double(s);
                } catch (NumberFormatException e) {
                    bd = false;
                    bi = false;
                }
                Integer valI = -1;
                try {
                    valI = String_to_Int(s);
                } catch (NumberFormatException e) {
                    bi = false;
                }
                if (bd) {
                    t.setTag("d");
                    t.setTag_numi(valI);
                    t.setTag_numd(valD);
                    t.setCategorics(null);
                    act = false;
                }
                if (bi) {
                    t.setTag("i");
                    t.setTag_numi(valI);
                    t.setTag_numd(valD);
                    t.setCategorics(null);
                    act = false;
                }
                if (s.contains(";")){
                    t.setTag("c");
                    t.setTag_numi(valI);
                    t.setTag_numd(valD);
                    List<String> orderlist = Arrays.asList(s.split(";"));
                    Collections.sort(orderlist);
                    t.setCategorics(orderlist);
                    act = false;
                }
                if (act) t.setTag(s);
                newtagform.add(t);
                ++pos;
            }
            mapRatedata.put(index, newtagform);
            ++index;
        }
    }
}
