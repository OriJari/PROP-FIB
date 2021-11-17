package algorithm.contentbasedflitering;

import content.Content;
import item.Item;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;

public class DriverSimilarityTable {
    public static void main(String[] args) throws IOException {
        Map<Integer,ArrayList<Content>> map = new TreeMap<>();
        System.out.println("Welcome to Driver Content Based Filtering");
        BufferedReader buffer = new BufferedReader(new FileReader("C:\\Users\\marcd\\IdeaProjects\\PROP Local\\src\\Input.txt"));
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
        System.out.println("RESULTS:");
        SimilarityTable taula = new SimilarityTable();
        taula.initSimilarityTable(map);
        System.out.println("Similarity Table:");
        taula.print_similarity_matrix();
        System.out.println("Recommendation Phase:");
        System.out.println("If you wish to terminate execution, type 'end' in any query");
        while (true) {
            System.out.println("Which item do you want a recommendation for?");
            System.out.println("input: <Integer> (ranging from 0 to n-1)");
            line = buffer.readLine();
            if (line == null || line.equals("end")) break;
            int item_id = Integer.parseInt(line);
            System.out.println("How many items would you like to be recommended?");
            System.out.println("input: <Integer> (smaller or equal to n)");
            line = buffer.readLine();
            if (line == null || line.equals("end")) break;
            int k = Integer.parseInt(line);
            ArrayList<Item> result = taula.kNN(item_id,k);
            System.out.println("Recommendation:");
            for (int i = 0; i < k; ++i) {
                System.out.print(result.get(i).getID() + " ");
            }
            System.out.println(" ");
        }
        System.out.println("Thank you for your cooperation!");
    }
}
