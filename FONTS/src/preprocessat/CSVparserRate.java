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
 * @brief Implements the lecture and the proces of data set of the csv
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
     * \pre the path of the file needs to be existent and the document csv needs to be from type ratings.csv
     *
     * \post It creates a <em>CSVparserRate</em> object with its attribute <em>path</em> with the value received as a parameter
     *  ,<em>numCols</em>  initialized as 0, <em>numRows</em>  initialized as 0, <em>content</em> empty, <em>mapRate</em> empty and
     *   <em>header</em> empty.
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
     * \pre the csv document needs to be created and read.
     * \post obtain the number of the rows of the content attribute.
     * @return the number of rows of the csv
     */
    public Integer getNumRows() {
        return numRows;
    }

    /**
     * @brief Getter of the num columns
     * \pre the csv document needs to be created and read.
     * \post obtain the number of the columns of the content attribute.
     * @return the number of columns of the csv
     */
    public Integer getNumCols() {
        return numCols;
    }

    /**
     * @brief Getter of the header
     * \pre the csv document needs to be created and read.
     * \post obtain the list of the header in the csv.
     * @return the header of the csv as a List
     */
    public List<String> getHeader() {
        return header;
    }

    /**
     * @brief Getter of the path of the location of the csv
     * \pre needs to be a existent path.
     * \post obtain the path where is located the csv.
     * @return the path as String
     */
    public String getPath() {
        return path;
    }

    /**
     * @brief Getter of the class, gets the content
     * \pre the document needs to be read to obtain the content
     * \post obtain the lecture of the csv ready to manipulate
     * @return the content value
     */
    public List<List<String>> getContent() {
        return content;
    }

    /**
     * @brief Getter of the class, gets the mapRate
     * \pre the document needs to be read to obtain the content
     * \post obtain the data set manipulated to make easy the operation in algorithms
     * @return set of data of the ratings content manipulated
     */
    public Map<Integer, Map<Integer, Float>> getMapRate() {
        return mapRate;
    }

    /**
     * @brief Setter of the class, modified the number of rows
     * \pre true
     * \post modify the attribute numRows of the class
     * @param numRows, new size of the number of rows
     */
    public void setNumRows(Integer numRows) {
        this.numRows = numRows;
    }

    /**
     * @brief Setter of the class, modified the number of columns
     * \pre true
     * \post modify the attribute numCols of the class
     * @param numCols, new size of the number of columns
     */
    public void setNumCols(Integer numCols) {
        this.numCols = numCols;
    }

    /**
     * @brief Setter of the class, modified the elements of the header
     * \pre true
     * \post modify the attribute header of the class
     * @param header, header of the csv as a list
     */
    public void setHeader(List<String> header) {
        this.header = header;
    }

    /**
     * @brief Setter of the class, modified the path where is located ths csv
     * \pre the path needs to be corresponded with the location file
     * \post modify the attribute path, changing to a new document.
     * @param path, where the document is located, as string
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @brief Setter of the class, gets the content
     * \pre true
     * \post modify the attribute content, changing the lecture.
     * @param content, lecture of the csv document to attribute
     */
    public void setContent(List<List<String>> content) {
        this.content = content;
    }

    /**
     * @brief Setter of the class, sets a new mapRate
     * \pre true
     * \post modify the attribute mapRate, changing the content of it.
     * @param mapRate , map to redefine the new one
     */
    public void setMapRate(Map<Integer, Map<Integer, Float>> mapRate) {
        this.mapRate = mapRate;
    }


    /**
     * @brief Get the header of the csv.
     * \pre needs a previous lecture of the csv
     * \post obtain the set of ids of the document.
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
     * \pre needs a document csv to read
     * \post obtains the lecture of the document manipulated into content.
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
     * \pre true
     * \post change the value of the string to a integer.
     * @param s String to obtain the corresponding value
     * @return string s converted to integer
     */
    public Integer String_to_Int(String s){
        return Integer.parseInt(s);
    }

    /**
     * @brief Change a String value to a Float one
     * \pre true
     * \post change the value of the string to a float.
     * @param s String to obtain the corresponding value
     * @return string s converted to float
     */
    public Float String_to_Float(String s){
        return Float.parseFloat(s);
    }

    /**
     * @brief Change List<List<String>> to a Map<Integer, Map<Integer,Float>> structure
     * \pre needs to have a content of the lecture
     * \post change the content to a estructure where the data is processed
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
     * \pre needs to have a content of the lecture
     * \post obtain a row of the content read.
     * @param i number of the row to obtain
     */
    public String getRow(int i) {
        return String.valueOf(this.content.get(i));
    }

}
