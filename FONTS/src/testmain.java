import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import algorithm.collaborativeFiltering.collaborativeFiltering;
import algorithm.contentbasedflitering.K_NN;
import content.Content;
import preprocessat.*;

import static java.lang.Math.max;

public class testmain {
    private static Scanner sc;

    public void makerecommendation(){
        System.out.println("This might take a while...");

        CSVparserItem CSVItem = new CSVparserItem("FONTS/src/preprocessat/items.csv");
        CSVItem.readLoadItem();
        CSVItem.MapItemData(CSVItem.getContent());

        Map<Integer, ArrayList<Content>> map_rate_item = CSVItem.getMapRatedata();
        K_NN taula = new K_NN();
        taula.initSimilarityTable(map_rate_item);

        CSVparserRate CSVRate = new CSVparserRate("FONTS/src/preprocessat/ratings.db.csv");
        CSVRate.readLoadRate();
        CSVRate.LoadRate(CSVRate.getContent());
        Map<Integer, Map<Integer, Float>> map_rate = CSVRate.getMapRate();
        collaborativeFiltering CF = new collaborativeFiltering(map_rate, max(1, map_rate.size() / 3));

        System.out.println("ID del user que quiere la recomendacion:");
        int userID = sc.nextInt();
        Map<Integer, Flaot>
    }

    public static void main(String[] args) {
        System.out.println("Driver collaborativeFiltering");
        boolean salir = false;
        sc = new Scanner(System.in);

        while (!salir) {
            System.out.println("Escoja una opción:");
            System.out.println("\t 1) Hacer recomendacion");
            System.out.println("\t 0) Salir");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    try {
                        makerecommendation();
                    } catch (Exception E) {
                        System.out.println(E.getMessage());
                    }
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println(option);
                    break;
            }
        }
    }
}
