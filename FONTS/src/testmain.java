import java.util.*;

import dominio.clases.algorithm.collaborativefiltering.*;
import dominio.clases.algorithm.contentbasedflitering.*;
import dominio.clases.content.*;
import dominio.clases.evaluation.*;
import dominio.clases.preprocessat.*;
import dominio.clases.recommendation.Recommendation;

/**
 * @brief Clase ejecutable del proyecto con la que probar sus funcionalidades.
 */

import static java.lang.Math.max;
import static java.lang.Math.min;

public class testmain {
    private static Scanner sc;
    private static String path_item;
    private static String path_known;
    private static String path_unknown;
    private static boolean serie = false;

    public static void makerecommendation(){
        System.out.println("Vas a querer una valoración de la recomendación?");
        System.out.println("0: NO, 1: SI");
        int valoration = sc.nextInt();
        boolean val = (valoration == 1);

        CSVparserItem CSVItem = new CSVparserItem(path_item);
        List<Integer> id_reals = CSVItem.getId_Items();
        CSVItem.readLoadItem();
        CSVItem.MapItemData(CSVItem.getContent());

        CSVparserRate CSVRate_known = new CSVparserRate(path_known);
        CSVRate_known.readLoadRate();
        CSVRate_known.LoadRate(CSVRate_known.getContent());
        Map<Integer, Map<Integer, Float>> map_rate_known = CSVRate_known.getMapRate();

        CSVparserRate CSVRate_unknown = new CSVparserRate(path_unknown);
        CSVRate_unknown.readLoadRate();
        CSVRate_unknown.LoadRate(CSVRate_unknown.getContent());
        Map<Integer, Map<Integer, Float>> map_rate_unknown = CSVRate_unknown.getMapRate();

        CollaborativeFiltering CF = new CollaborativeFiltering(map_rate_known, map_rate_unknown, max(1, map_rate_known.size() / 3));

        K_NN taula = new K_NN(map_rate_known, map_rate_unknown, id_reals);
        Map<Integer, List<Content>> map_rate_item = CSVItem.getMapRatedata();
        taula.initSimilarityTable(map_rate_item);

        System.out.println("Introduzca el ID del user que quiere la recomendacion, por lo contrario si desea volver atras escribe -1 :");
        int userID = sc.nextInt();
        boolean existinguser = map_rate_known.containsKey(userID);

        while(!existinguser && userID != -1){
            System.out.println("Este user no existe! Introduzca ID del user que quiere la recomendacion  por lo contrario si desea volver atras escribe -1 :");
            userID = sc.nextInt();

            existinguser = map_rate_known.containsKey(userID);
        }

        if(existinguser) {
            System.out.println("Qué algoritmo de recomendación desea usar?");
            System.out.println("\t 1) Collaborative filtering (k-means + slope-one)");
            System.out.println("\t 2) Content based filtering (k-nn)");
            System.out.println("\t 0) Volver atras");

            Recommendation recommendation;
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    try {
                        recommendation = CF.recommend(userID, 10, val);

                        if(serie) {
                            for (Map.Entry<Integer, Float> entry : recommendation.entrySet()) {
                                System.out.println("ID item: " + entry.getKey() + " with expected rating " + min(10, entry.getValue()));
                            }
                        }
                        else{
                            for (Map.Entry<Integer, Float> entry : recommendation.entrySet()) {
                                System.out.println("ID item: " + entry.getKey() + " with expected rating " + min(5,entry.getValue()));
                            }
                        }

                    } catch (Exception E) {
                        System.out.println(E.getMessage());
                    }
                    break;
                case 2:
                    try {
                        Map<Integer, Float> similarities = taula.recommend(userID, 10,val);
                        if (val) {
                            for (Map.Entry<Integer, Float> entry : similarities.entrySet()) {
                                System.out.println("ID item: " + id_reals.get(entry.getKey()) + " with similarity " + entry.getValue());
                                recommendation.put(id_reals.get(entry.getKey()), entry.getValue());
                            }
                        }
                        else {
                            for (Map.Entry<Integer, Float> entry : similarities.entrySet()) {
                                System.out.println("ID item: " + id_reals.get(entry.getKey()));
                                recommendation.put(id_reals.get(entry.getKey()), entry.getValue());
                            }
                        }
                    } catch (Exception E) {
                        System.out.println(E.getMessage());
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println(choice);
                    break;
            }

            if (choice != 1 && choice != 2 && choice != 0) {
                System.out.println("No has elegido una opcion valida.");
            } else if(val) {
                Evaluation eval = new Evaluation(map_rate_unknown.get(userID), recommendation);
                System.out.println("DCG de la recomendacion: " + eval.DCG()+"\n");
            }
        }

    }

    public static void main(String[] args) {
        boolean salir = false;
        sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        System.out.println("!!IMPORTANTE!!");
        System.out.println("Antes de realizar una recomendacion debe insertar el path de los csv EXCEPTO en los casos movies y series");

        while (!salir) {
            System.out.println("Escoja una opción:");
            System.out.println("\t 0) Salir");
            System.out.println("\t 1) Introduzca el path del los csv");
            System.out.println("\t 2) movies 250");
            System.out.println("\t 3) movies 750");
            System.out.println("\t 4) series 250");
            System.out.println("\t 5) series 750");
            System.out.println("\t 6) series 2250");
            System.out.println("\t 7) Hacer recomendacion (solo en el caso que haya insertado manualmente los paths)");
            int option = sc.nextInt();
            serie = false;
            switch (option) {
                case 0:
                    salir = true;
                    break;
                case 1:
                    System.out.println("\t 1) Introduzca el path del parser tipo items.csv");
                    path_item = sc1.next();
                    System.out.println("\t 2) Introduzca el path del parser tipo ratings.known.csv");
                    path_known = sc1.next();
                    System.out.println("\t 3) Introduzca el path del parser tipo ratings.unknown.csv");
                    path_unknown = sc1.next();
                    break;
                case 2:
                    path_item = "FONTS/src/persistencia/movie.sample/250/items.csv";
                    path_known = "FONTS/src/persistencia/movie.sample/250/ratings.test.known.csv";
                    path_unknown = "FONTS/src/persistencia/movie.sample/250/ratings.test.unknown.csv";
                    try {
                        makerecommendation();
                    } catch (Exception E) {
                        System.out.println(E.getMessage());
                    }
                    break;
                case 3:
                    path_item = "FONTS/src/persistencia/movie.sample/750/items.csv";
                    path_known = "FONTS/src/persistencia/movie.sample/750/ratings.test.known.csv";
                    path_unknown = "FONTS/src/persistencia/movie.sample/750/ratings.test.unknown.csv";
                    try {
                        makerecommendation();
                    } catch (Exception E) {
                        System.out.println(E.getMessage());
                    }
                    break;
                case 4:
                    path_item = "FONTS/src/persistencia/series/250/items.csv";
                    path_known = "FONTS/src/persistencia/series/250/ratings.test.known.csv";
                    path_unknown = "FONTS/src/persistencia/series/250/ratings.test.unknown.csv";
                    try {
                        makerecommendation();
                    } catch (Exception E) {
                        System.out.println(E.getMessage());
                    }
                    break;
                case 5:
                    path_item = "FONTS/src/persistencia/series/750/items.csv";
                    path_known = "FONTS/src/persistencia/series/750/ratings.test.known.csv";
                    path_unknown = "FONTS/src/persistencia/series/750/ratings.test.unknown.csv";
                    try {
                        makerecommendation();
                    } catch (Exception E) {
                        System.out.println(E.getMessage());
                    }
                    break;
                case 6:
                    path_item = "FONTS/src/persistencia/series/2250/items.csv";
                    path_known = "FONTS/src/persistencia/series/2250/ratings.test.known.csv";
                    path_unknown = "FONTS/src/persistencia/series/2250/ratings.test.unknown.csv";
                    serie = true;
                    try {
                        makerecommendation();
                    } catch (Exception E) {
                        System.out.println(E.getMessage());
                    }
                    break;
                case 7:
                    try {
                        makerecommendation();
                    } catch (Exception E) {
                        System.out.println(E.getMessage());
                    }
                    break;
                default:
                    System.out.println(option);
                    break;
            }
        }
    }
}
