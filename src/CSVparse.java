
//import java.util.Arrays;
import java.util.*;
import java.io.*;


public class CSVparser {

    Integer numCols, numRows;
    String path;
    List<List<String>> content;
    Map<Integer, List<String>> mapItem;
    Map<Integer, Map<Integer, Float>> mapRate;

    /**
     * Default builder.
     * @param path camino donde se ubica el documento csv
     */
    public CSVparser(String path){
        this.path = path;
        this.numCols = 0;
        this.numRows = 0;
        content = new ArrayList<>();
        mapItem = new TreeMap<>();
        mapRate = new TreeMap<>();
    }

    /**
     * Read the content into memory.
     */
    public void readLoadItem(){
        FileInputStream fis= null;
        try {
            fis = new FileInputStream(this.path);
            Scanner sc = new Scanner(fis);
            sc.nextLine();
            //For each line
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                //,(?=(?:[^\"]\"[^\"]\")[^\"]$) //con punto y coma
                //String[] tokens = line.split("[,|;](?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                //Arrays.asList(line.split(",(?=(?:[^\"]\"[^\"]\")[^\"]$)"));
                List<String> splitContent = new ArrayList<>(Arrays.asList(line.split("[,|;](?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1)));
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

    public void readLoadRate(){
        FileInputStream fis= null;
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

    public static Integer String_to_Int(String s){
        return Integer.parseInt(s);
    }
    public static Float String_to_Float(String s){
        return Float.parseFloat(s);
    }

    /**
     * Cambio de estructura de List a Map
     * @param rate_content contenido del documento csv
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


    public void LoadItem(List<List<String>> rate_conent){
        List<String> aux = new ArrayList<>();
        for (int i = 0; i < rate_conent.size(); ++i){
            Integer itemID = i;
            mapItem.put(itemID, rate_conent.get(i));
        }
    }

    public String getRow(int i) {
        return String.valueOf(this.content.get(i));
    }

    public static void main(String[] args) {
        CSVparser instance = new CSVparser("/home/miguel/PROP/Project/Amazon/items.csv");
        instance.readLoadItem();
        instance.LoadItem(instance.content);

        CSVparser instance1 = new CSVparser("/home/miguel/PROP/Project/Amazon/ratings.db.csv");
        instance1.readLoadRate();
        instance1.LoadRate(instance1.content);

        CSVparser instance2 = new CSVparser("/home/miguel/PROP/Project/Amazon/ratings.test.known.csv");
        instance2.readLoadRate();
        instance2.LoadRate(instance2.content);

        CSVparser instance3 = new CSVparser("/home/miguel/PROP/Project/Amazon/ratings.test.unknown.csv");
        instance3.readLoadRate();
        instance3.LoadRate(instance3.content);

        System.out.println(instance.getRow(0));
        System.out.println(instance.getRow(7));
        System.out.println(instance1.getRow(1));
        System.out.println(instance1.getRow(2));
        System.out.println(instance1.getRow(3));
    }
}
