package Drivers;


import tag.Tag;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class DriverTag {
    public static void options() {
        System.out.println("Choose an option with the necessary parameters needed:");
        System.out.println("\t 0) exit");
        System.out.println("\t 1) Tag()");
        System.out.println("\t 2) Tag(List<String> ntags)");
        System.out.println("\t 3) getTag()");
        System.out.println("\t 4) setTag(List<String> tags)");
    }

    public static void main(String[] args) {
        System.out.println("Driver Tag class:");
        Tag ntag = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            boolean finish = false;

            options();
            while (!finish) {

                String line;
                String[] param;
                String op;

                line = br.readLine();

                param = line.split(" ");
                op = param[0];
                try {
                    switch (op) {
                        case "0" : finish = true;
                            break;
                        case "1" :
                            ntag = new Tag();
                            System.out.println("Tag created");
                            break;
                        case "2" :

                        case "3" :
                            ntag = new Tag(ntag.getEtiquetas());
                            System.out.println("Tag created");
                            break;
                        case "4" :
                            System.out.println("Define num of elements to insert and the elements: ");
                            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                            String line1;
                            String[] param1;
                            line1 = br1.readLine();
                            param1 = line1.split(" ");
                            int limt1 = parseInt(param1[0]);
                            List<String> aux2 = new ArrayList<>();
                            for (int j = 1; j <= limt1; ++j){
                                aux2.add(param1[j]);
                            }
                            ntag.setEtiquetas(aux2);
                            System.out.println("Tags changed");
                            break;
                        default : System.out.println("Choose another option");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
}