import java.util.*;

import algorithm.collaborativefiltering.CollaborativeFiltering;
import algorithm.contentbasedflitering.K_NN;
import content.Content;
import evaluation.Evaluation;
import preprocessat.*;

import static java.lang.Math.max;

public class testmain {
    private static Scanner sc;

    public static void makerecommendation(){
        CSVparserItem CSVItem = new CSVparserItem("FONTS/src/preprocessat/items.csv");
        CSVItem.readLoadItem();
        CSVItem.MapItemData(CSVItem.getContent());
        CSVparserRate CSVRate_known = new CSVparserRate("FONTS/src/preprocessat/ratings.test.known.csv");
        CSVRate_known.readLoadRate();
        CSVRate_known.LoadRate(CSVRate_known.getContent());
        Map<Integer, Map<Integer, Float>> map_rate_known = CSVRate_known.getMapRate();
        CollaborativeFiltering CF = new CollaborativeFiltering(map_rate_known, max(1, map_rate_known.size() / 3));

        Map<Integer, List<Content>> map_rate_item = CSVItem.getMapRatedata();
        K_NN taula = new K_NN(map_rate_known);
        taula.initSimilarityTable(map_rate_item);

        CSVparserRate CSVRate_unknown = new CSVparserRate("FONTS/src/preprocessat/ratings.test.unknown.csv");
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

        System.out.println("Qué algoritmo de recomendación desea usar?");
        System.out.println("\t 1) Collaborative filtering (k-means + slope-one)");
        System.out.println("\t 2) Content based filtering (k-nn)");

        Map<Integer, Float> recommendation = new TreeMap<>();
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                try {
                    recommendation = CF.recommend(userID);
                    for (Map.Entry<Integer, Float> entry : recommendation.entrySet()) {
                        System.out.println("ID item: " + entry.getKey() + " with expected rating " + entry.getValue());
                    }
                } catch (Exception E) {
                    System.out.println(E.getMessage());
                }
                break;
            case 2:
                try {
                    List<Integer> id_reals = CSVItem.getId_Items();
                    Map<Integer, Float> similarities = taula.recommend(userID, 10, id_reals);
                    for (Map.Entry<Integer, Float> entry : similarities.entrySet()) {
                        System.out.println("ID item: " + id_reals.get(entry.getKey()) + " with similarity " + entry.getValue());
                        recommendation.put(id_reals.get(entry.getKey()),entry.getValue());
                    }
                } catch (Exception E) {
                    System.out.println(E.getMessage());
                }
                break;
            default:
                System.out.println(choice);
                break;
        }
        if(choice != 1 && choice != 2){
            System.out.println("No has elegido una opcion valida.");
        }
        else{
            Evaluation eval = new Evaluation(map_rate_unknown.get(userID) , recommendation);
            System.out.println("Calidad de la recomendacion: " + eval.DCG());
        }
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
