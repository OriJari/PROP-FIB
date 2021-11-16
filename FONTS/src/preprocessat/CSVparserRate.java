package preprocessat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CSVparserRate {

    Integer numCols, numRows;
    String path;
    List<List<String>> content;
    Map<Integer, Map<Integer, Float>> mapRate;

    /**
     * Default builder.
     * @param path where is located the csv document
     */
    public CSVparserRate(String path){
        this.path = path;
        this.numCols = 0;
        this.numRows = 0;
        this.content = new ArrayList<>();
        this.mapRate = new TreeMap<>();
    }

    /**
     * Getter of the class, gets the content
     * @return the lecture of the document csv
     */
    public List<List<String>> getContent() {
        return content;
    }

    /**
     * Getter of the class, gets the mapRate
     * @return set of data of the ratings content
     */
    public Map<Integer, Map<Integer, Float>> getMapRate() {
        return mapRate;
    }

    /**
     * Setter of the class, gets the conent
     * @param content, lecture of the csv document to attribute
     */
    public void setContent(List<List<String>> content) {
        this.content = content;
    }

    /**
     * Setter of the class, sets a new mapRate
     * @param mapRate , map to redefine the new one
     */
    public void setMapRate(Map<Integer, Map<Integer, Float>> mapRate) {
        this.mapRate = mapRate;
    }


    /**
     * Read the content of the rates csvs into memory.
     */
    public void readLoadRate(){
        FileInputStream fis;
        try {
            fis = new FileInputStream(this.path);
            Scanner sc = new Scanner(fis);
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
     * Change a String value to an Integer one
     * @param s String to obtain the corresponding value
     */
    public Integer String_to_Int(String s){
        return Integer.parseInt(s);
    }

    /**
     * Change a String value to a Float one
     * @param s String to obtain the corresponding value
     */
    public Float String_to_Float(String s){
        return Float.parseFloat(s);
    }

    /**
     * Change List<List<String>> to a Map<Integer, Map<Integer,Float>> structure
     * @param rate_content content of the csv document
     */
    public void LoadRate(List<List<String>> rate_content){
        //for each line take UserID, ItemID and Rate and add to the mapRate
        for (List<String> strings : rate_content) {
            Integer userID = String_to_Int(strings.get(0));
            Integer itemID = String_to_Int(strings.get(1));
            Float rate = String_to_Float(strings.get(2));
            if(mapRate.containsKey(userID)){
                mapRate.get(userID).put(itemID,rate);
            }
            else{
                Map<Integer, Float> map = new TreeMap<>();
                map.put(itemID, rate);
                mapRate.put(userID, map);
            }
        }
    }

    /**
     * Lecture of the List<List<String>> rows
     * @param i number of the row to obtain
     */
    public String getRow(int i) {
        return String.valueOf(this.content.get(i));
    }



    public static void main(String[] args) {

        CSVparserRate instance1 = new CSVparserRate("/subgrup-prop2-3/src/ratings.db.csv");
        instance1.readLoadRate();
        instance1.LoadRate(instance1.content);

        CSVparserRate instance2 = new CSVparserRate("/subgrup-prop2-3/src/ratings.test.known.csv");
        instance2.readLoadRate();
        instance2.LoadRate(instance2.content);

        CSVparserRate instance3 = new CSVparserRate("/subgrup-prop2-3/src/ratings.test.unknown.csv");
        instance3.readLoadRate();
        instance3.LoadRate(instance3.content);
        System.out.println("hello");
    }
}
