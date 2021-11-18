package preprocessat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @class CSVparserRate
 * @brief Implements the lecture and the proces of data of the csv
 * @author Miguel
 */

public class CSVparserRate {

    private Integer numCols, numRows;
    private String path;
    private List<List<String>> content;
    private List<String> header;
    private Map<Integer, Map<Integer, Float>> mapRate;

    /**
     * @brief Default builder.
     * @param path where is located the csv document
     */
    public CSVparserRate(String path){
        this.path = path;
        this.numCols = 0;
        this.numRows = 0;
        this.content = new ArrayList<>();
        this.mapRate = new TreeMap<>();
        this.header = new ArrayList<>();
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
     * @brief Getter of the class, gets the mapRate
     * @return set of data of the ratings content
     */
    public Map<Integer, Map<Integer, Float>> getMapRate() {
        return mapRate;
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
     * @brief Setter of the class, sets a new mapRate
     * @param mapRate , map to redefine the new one
     */
    public void setMapRate(Map<Integer, Map<Integer, Float>> mapRate) {
        this.mapRate = mapRate;
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
     * @brief Read the content of the rates csvs into memory.
     */
    public void readLoadRate(){
        FileInputStream fis;
        try {
            fis = new FileInputStream(this.path);
            Scanner sc = new Scanner(fis);
            obtainHeader(this.path);
            sc.nextLine();  // without header
            //For each line
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                //[:space]
                List<String> splitContent = Arrays.asList(line.split(","));
                content.add(splitContent);
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
    public Integer String_to_Int(String s){
        return Integer.parseInt(s);
    }

    /**
     * @brief Change a String value to a Float one
     * @param s String to obtain the corresponding value
     */
    public Float String_to_Float(String s){
        return Float.parseFloat(s);
    }

    /**
     * @brief Change List<List<String>> to a Map<Integer, Map<Integer,Float>> structure
     * @param rate_content content of the csv document
     */
    public void LoadRate(List<List<String>> rate_content){
        //for each line take UserID, ItemID and Rate and add to the mapRate
        for (List<String> strings : rate_content) {
            Integer userID = String_to_Int(strings.get(header.indexOf("userId")));
            Integer itemID = String_to_Int(strings.get(header.indexOf("itemId")));
            Float rateID = String_to_Float(strings.get(header.indexOf("rating")));
            if(mapRate.containsKey(userID)){
                mapRate.get(userID).put(itemID,rateID);
            }
            else{
                Map<Integer, Float> map = new TreeMap<>();
                map.put(itemID, rateID);
                mapRate.put(userID, map);
            }
        }
    }

    /**
     * @brief Lecture of the List<List<String>> rows
     * @param i number of the row to obtain
     */
    public String getRow(int i) {
        return String.valueOf(this.content.get(i));
    }



    public static void main(String[] args) {

        /*CSVparserRate instance1 = new CSVparserRate("/home/miguel/PROP/Project/Amazon/puebas/series.public/250/ratings.db.csv");
        instance1.readLoadRate();
        instance1.LoadRate(instance1.content);

        CSVparserRate instance2 = new CSVparserRate("/home/miguel/PROP/Project/Amazon/puebas/series.public/250/ratings.test.known.csv");
        instance2.readLoadRate();
        instance2.LoadRate(instance2.content);

        CSVparserRate instance3 = new CSVparserRate("/home/miguel/PROP/Project/Amazon/puebas/series.public/250/ratings.test.unknown.csv");
        instance3.readLoadRate();
        instance3.LoadRate(instance3.content);
        System.out.println("hello");*/
    }
}
