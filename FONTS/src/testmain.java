import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import algorithm.collaborativeFiltering.collaborativeFiltering;
import algorithm.contentbasedflitering.K_NN;
import content.Content;
import evaluation.evaluation;
import preprocessat.*;

import static java.lang.Math.max;

public class testmain {
    private static Scanner sc;

    public static void makerecommendation(){
        System.out.println("This might take a while...");

        /*CSVparserItem CSVItem = new CSVparserItem("FONTS/src/preprocessat/items.csv");
        CSVItem.readLoadItem();
        CSVItem.MapItemData(CSVItem.getContent());*/

        /*Map<Integer, ArrayList<Content>> map_rate_item = CSVItem.getMapRatedata();
        K_NN taula = new K_NN();
        taula.initSimilarityTable(map_rate_item);*/

        CSVparserRate CSVRate_known = new CSVparserRate("FONTS/src/evaluation/ratings.test.known.csv");
        CSVRate_known.readLoadRate();
        CSVRate_known.LoadRate(CSVRate_known.getContent());
        Map<Integer, Map<Integer, Float>> map_rate_known = CSVRate_known.getMapRate();
        collaborativeFiltering CF = new collaborativeFiltering(map_rate_known, max(1, map_rate_known.size() / 3));

        CSVparserRate CSVRate_unknown = new CSVparserRate("FONTS/src/evaluation/ratings.test.unknown.csv");
        CSVRate_unknown.readLoadRate();
        CSVRate_unknown.LoadRate(CSVRate_unknown.getContent());
        Map<Integer, Map<Integer, Float>> map_rate_unknown = CSVRate_unknown.getMapRate();

        System.out.println("Introduzca el ID del user que quiere la recomendacion:");
        int userID = sc.nextInt();
        boolean existinguser = map_rate_known.containsKey(userID);

        while(!existinguser){
            System.out.println("Este user no existe! Introduzca ID del user que quiere la recomendacion:");
            userID = sc.nextInt();
            existinguser = map_rate_known.containsKey(userID);
        }

        Map<Integer, Float> recommendation  = CF.recommend(userID);

        for(Map.Entry<Integer, Float> entry: recommendation.entrySet()){
            System.out.println("ID item: " + entry.getKey() + " with expected rating " + entry.getValue());
        }

        evaluation eval = new evaluation(map_rate_unknown.get(userID) , recommendation);
        System.out.println("Calidad de la recomendacion: " + eval.DCG());
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
