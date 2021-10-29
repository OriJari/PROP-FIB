
//import java.util.Arrays;
import java.util.*;
import java.io.*;


public class CSVparser {

    Integer numCols, numRows;
    String path;
    List<List<String>> content;
    //TODO: escoger la estructura adecuada, posible opcion Map<Integer, List<string>>


    /**
     * Default builder.
     * @param path camino donde se ubica el documento csv
     */
    public CSVparser(String path){
        this.path = path;
        this.numCols = 0;
        this.numRows = 0;
        content = new ArrayList<>();
    }

    /**
     * Read the content into memory.
     */
    public void readLoadItem(){
        FileInputStream fis= null;
        try {
            fis = new FileInputStream(this.path);
            Scanner sc = new Scanner(fis);

            //For each line
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                //<-- , "alfa, beta, clala"
                // "..." ==> ,{1}, + 1 ->alfa, beta, clala   expresion: ([^"]*")|([;,	])
                //List<String> splitContent = Arrays.asList(line.split("[;,\t]"));
                List<String> splitContent = new ArrayList<>();
                boolean inQuotes = false;
                StringBuilder b = new StringBuilder();
                for (char c : line.toCharArray()){
                    if (c == ',' || c == ';' || c == '\t') {
                        if (inQuotes) {
                            b.append(c);
                        } else {
                            splitContent.add(b.toString());
                            b = new StringBuilder();
                        }
                    }
                    if (c == '\"'){
                        inQuotes = !inQuotes;
                    }
                    b.append(c);
                }
                splitContent.add(b.toString());
                //TODO: append case with " at the begin
                //seleccionar " del array y juntarlo hasta ";
                //Append content split by column
                content.add(splitContent);
                //Update cols&rows:
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
            //For each line
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                List<String> splitContent = Arrays.asList(line.split(",[:space:]"));
                content.add(splitContent);
                this.numRows += 1;
                this.numCols = Math.max(splitContent.size(), numCols);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Integer String_to_Int(String s){
        return Integer.parseInt(s);
    }
    public Float String_to_Float(String s){
        return Float.parseFloat(s);
    }

    /**
     * Cambio de estructura de List a Map
     * @param rate_content contenido del dcoumento csv
     * //https://stackoverflow.com/questions/22260564/how-to-use-an-iterator-to-find-a-specific-title-in-an-object-that-is-part-of-an
     */
    public void LoadRate (List<List<String>> rate_content){
        Map<Integer, Map<Integer, Float>> map = new TreeMap<>();
        Iterator it = map.keySet().iterator();
        //for each line take UserID, ItemID and Rate and add to the map
        for (int i = 0; i < rate_content.size(); ++i){
            Integer userID = String_to_Int(rate_content.get(i).get(0));
            Integer itemID = String_to_Int(rate_content.get(i).get(1));
            Float rate = String_to_Float(rate_content.get(i).get(2));
            //if (it.find(userID) != map.end()) map.put(userID, {itemID, rate});
            //else añadir al usuario map.put(itemID, rate);
            //map.put(userID, {itemID, rate});
        }
    }

    public String getRow(int i) {
        return String.valueOf(this.content.get(i));
    }

    public static void main(String[] args) {
        CSVparser instance = new CSVparser("/home/miguel/PROP/Project/Amazon/items.csv");
        instance.readLoadItem();
        CSVparser instance1 = new CSVparser("/home/miguel/PROP/Project/Amazon/ratings.db.csv");
        instance1.readLoadRate();
        //System.out.println(instance.getRow(1));
        //System.out.println(instance.getRow(7));
        System.out.println(instance1.getRow(1));
        System.out.println(instance1.getRow(2));
        System.out.println(instance1.getRow(3));
        /*for (int i = 0; i < instance1.numRows ; ++i){
            System.out.println(instance1.getRow(i));
        }*/
        //TODO: passar al formato corresponidente cada string
        // cuando sea numero " 47" hacer Integer n = new Integer(s.trim());
    }
}


//https://qastack.mx/programming/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes