package drivers;

import algorithm.contentbasedflitering.*;
import content.Content;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;

/**
 *
 * @author Marc Delgado Sánchez
 */

public class DriverK_NN {

    static private BufferedReader buffer;

    public static Map<Integer, Map<Integer, Float>> leeropinions() throws IOException {
        System.out.println("Numero de users: ");
        String line = buffer.readLine();
        int num_users = Integer.parseInt(line);
        Map<Integer, Map<Integer, Float>> opinions = new TreeMap<>();
        for(int i = 0; i < num_users; ++i){
            System.out.println("ID de user num " + (i+1) + ":");
            line = buffer.readLine();
            int userID = Integer.parseInt(line);
            opinions.put(userID, new TreeMap<Integer, Float>());
            System.out.println("Numero de items valorados por usuario " + userID + ":");
            line = buffer.readLine();
            int numItems = Integer.parseInt(line);
            for(int j = 0; j < numItems; ++j){
                System.out.println("ID item numero " + (j+1) + ":");
                String line2 = buffer.readLine();
                int itemID = Integer.parseInt(line2);
                System.out.println("Valoracion de l'item " + (j+1) + ":");
                line2 = buffer.readLine();
                float itemVal = Float.parseFloat(line2);
                opinions.get(userID).put(itemID, itemVal);
            }
        }
        return opinions;
    }
    public static void main(String[] args) throws IOException {
        Map<Integer,ArrayList<Content>> map = new TreeMap<>();
        System.out.println("Welcome to Driver Content Based Filtering");
        buffer = new BufferedReader(new FileReader("FONTS/src/algorithm/contentbasedflitering/Prova.txt"));
        String line;
        System.out.println("How many items will you state?");
        System.out.println("int:");
        line = buffer.readLine();
        int n = Integer.parseInt(line);
        System.out.println("How many tags will each item have?");
        System.out.println("int:");
        line = buffer.readLine();
        int m = Integer.parseInt(line);
        for (int i = 0; i < n; ++i) {
            System.out.println("State an item");
            System.out.println("State all the tags of the item");
            System.out.println("input: <String> <Integer> <Double> <List of Strings>");
            System.out.println("String: 'i' if integer, 'd' if double, 'b' if boolean, 'c' if categoric, <String> if string");
            System.out.println("Integer: <Integer> if integer, 0 if false, 1 if true, -1 elsewhere");
            System.out.println("Double: <Double> if double, -1.0 elsewhere ");
            System.out.println("List of Strings: <List of Strings> if categoric, empty elsewhere");
            ArrayList<Content> lista = new ArrayList<>(m);
            for (int j = 0; j < m; ++j) {
                System.out.println("State a tag:");
                line = buffer.readLine();
                String[] values = line.split(" ");
                int mida = values.length;

                String tag = values[0];
                int integer = Integer.parseInt(values[1]);
                double decimal = Double.parseDouble(values[2]);
                ArrayList<String> sublist = null;
                if (mida > 3) sublist = new ArrayList<>(Arrays.asList(values).subList(3, mida));

                Content content = new Content(tag, integer,decimal, sublist);
                lista.add(content);
            }
            map.put(i,lista);
        }
        Map<Integer,Map<Integer,Float>> map_rate = leeropinions();
        System.out.println("RESULTS:");
        K_NN taula = new K_NN(map_rate);
        taula.initSimilarityTable(map);
        System.out.println("Similarity Table:");
        taula.print_similarity_matrix();
        System.out.println("Recommendation Phase:");
        System.out.println("If you wish to terminate execution, type 'end' in any query");
        while (true) {
            System.out.println("Which user do you want a recommendation for?");
            System.out.println("input: <Integer> ");
            line = buffer.readLine();
            if (line == null || line.equals("end")) break;
            int user_id = Integer.parseInt(line);
            System.out.println("How many items would you like to be recommended?");
            System.out.println("input: <Integer> (smaller or equal to n)");
            line = buffer.readLine();
            if (line == null || line.equals("end")) break;
            int k = Integer.parseInt(line);
            Map<Integer,Float> result = taula.recommend(user_id,k);
            System.out.println("Recommendation:");
            for (Map.Entry<Integer,Float> entry : result.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
            System.out.println(" ");
        }
        System.out.println("Thank you for your cooperation!");
    }
}
