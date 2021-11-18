package drivers;

import algorithm.collaborativefiltering.CollaborativeFiltering;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class DriverCollaborativeFiltering {
    private static Scanner sc;

    public static Map<Integer, Map<Integer, Float>> leeropinions(){
        System.out.println("Numero de users: ");
        int Nusers = sc.nextInt();
        Map<Integer, Map<Integer, Float>> opinions = new TreeMap<Integer, Map<Integer, Float>>();
        for(int i = 0; i < Nusers; ++i){
            System.out.println("ID de user num " + (i+1) + ":");
            int userID = sc.nextInt();
            opinions.put(userID, new TreeMap<Integer, Float>());
            System.out.println("Numero de items valorados por usuario " + userID + ":");
            int numItems = sc.nextInt();
            for(int j = 0; j < numItems; ++j){
                System.out.println("ID item numero " + (j+1) + ":");
                int itemID = sc.nextInt();
                System.out.println("Valoracion de l'item " + (j+1) + ":");
                float itemVal = sc.nextFloat();
                opinions.get(userID).put(itemID, itemVal);
            }
        }
        return opinions;
    }

    public static void testrecommend(){
        Map<Integer, Map<Integer, Float>> opinions = leeropinions();
        CollaborativeFiltering CF = new CollaborativeFiltering(opinions, 1);
        System.out.println("UserID del user que queremos la recomendacion:");
        int userID = sc.nextInt();
        Map<Integer, Float> recommendation = CF.recommend(userID);
        for(Map.Entry<Integer, Float> entry: recommendation.entrySet()){
            System.out.println("Expected rating for item " + entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args){
        System.out.println("Driver collaborativeFiltering");
        boolean salir = false;
        sc = new Scanner(System.in);

        while (!salir) {
            System.out.println("Escoja una opción:");
            System.out.println("\t 1) testrecommend()");
            System.out.println("\t 0) Salir");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    try {
                        testrecommend();
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
