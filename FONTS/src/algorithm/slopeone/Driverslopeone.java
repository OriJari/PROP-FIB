package algorithm.slopeone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Oriol Martí Jariod
 */


public class Driverslopeone {
    private static Scanner sc;


    public static TreeMap<Integer, TreeMap<Integer, Float>> leeropinions(){
        System.out.println("Numero de users: ");
        int Nusers = sc.nextInt();
        TreeMap<Integer, TreeMap<Integer, Float>> opinions = new TreeMap<Integer, TreeMap<Integer, Float>>();
        for(int i = 0; i < Nusers; ++i){
            System.out.println("ID de user num " + (i+1) + " : ");
            int userID = sc.nextInt();
            opinions.put(userID, new TreeMap<Integer, Float>());
            System.out.println("Numero de items valorados por usuario " + userID + " : ");
            int numItems = sc.nextInt();
            for(int j = 0; j < numItems; ++j){
                System.out.println("ID item numero " + (j+1) + " : ");
                int itemID = sc.nextInt();
                System.out.println("Valoracion de l'item " + (j+1) + " : ");
                float itemVal = sc.nextFloat();
                opinions.get(userID).put(itemID, itemVal);
            }
        }
        return opinions;
    }


    public static void testSlopeOne() {
        System.out.print("Test SlopeOne");
        slopeone.map_data = leeropinions();
        System.out.println("esta base de datos");
        print_map_completo(slopeone.map_data);
        slopeone.map_pred = slopeone.SlopeOne( slopeone.map_data);
        System.out.println("\n Resultado:");
        System.out.println("\n\t esta prediccio");
        print_pred(slopeone.map_pred);
        System.out.println("Terminado");

    }

    public static void testdesviacio_mitjana() {
        System.out.println("testdesviacio mitjana");
        slopeone.map_data = leeropinions();
        System.out.println("esta base de datos");
        print_map_completo(slopeone.map_data);
        slopeone.desviacio_mitjana();
        System.out.println("\n Resultado:");
        System.out.println("\n\t esta desviacion");
        print_map_completo(slopeone.map_des);
        System.out.println("\n\t esta fequencia");
        print_map_freq(slopeone.map_freq);
        System.out.println("\n test terminado");

    }


    public static void testprediccio() {
        System.out.println("Test preddicio");
        System.out.println("\t 1) Directe");
        System.out.println("\t 2) Per parametre el mapa de prediccio");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                try {
                    slopeone.map_des = leeropinions();
                    System.out.println("esta desviacion");
                    print_map_completo(slopeone.map_des);
                    slopeone.prediccio();
                    System.out.println("\n Resultado:");
                    System.out.println("\n\t esta prediccio");
                    print_pred(slopeone.map_pred);
                    System.out.println("\n\t esta fequencia");
                    print_map_freq(slopeone.map_freq);
                    System.out.println("\n test terminado");
                } catch (Exception E) {
                    System.out.println(E.getMessage());
                }
                break;
            case 2:
                try {
                    slopeone.map_des = leeropinions();
                    System.out.println("esta desviacion");
                    print_map_completo(slopeone.map_des);
                    slopeone.map_pred = slopeone.prediccio(slopeone.map_pred);
                    System.out.println("\n Resultado:");
                    System.out.println("\n\t esta prediccio");
                    print_pred(slopeone.map_pred);
                    System.out.println("\n\t esta fequencia");
                    print_map_freq(slopeone.map_freq);
                    System.out.println("\n test terminado");
                } catch (Exception E) {
                    System.out.println(E.getMessage());
                }
                break;
            case 0:
                break;
            default:
                System.out.println(option);
                break;

        }


    }

    private static void print_map_freq(TreeMap<Integer, TreeMap<Integer, Integer>> mappa){
        for(int j : mappa.keySet()){
            System.out.println(" " + j + "-->" + mappa.get(j));

        }
    }

    private static void print_map_completo(TreeMap<Integer, TreeMap<Integer, Float>> mappa){
        for(int j : mappa.keySet()){
            System.out.println(" " + j + "-->" + mappa.get(j));

        }
    }

    private static void print_pred(TreeMap<Integer, Float> user) {
        for (Integer j : user.keySet()) {
            System.out.println(" " + j + " --> " + user.get(j));
        }
    }

    public static void main(String[] args){
        System.out.println("Driver SlopeOne");
        boolean salir = false;
        sc = new Scanner(System.in);

        while (!salir) {
            System.out.println("Escoja una opción:");
            System.out.println("\t 1) testdesviacio_mitjana()");
            System.out.println("\t 2) testprediccio()");
            System.out.println("\t 3) testSlopeOne()");
            System.out.println("\t 0) Salir");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    try {
                        testdesviacio_mitjana();
                    } catch (Exception E) {
                        System.out.println(E.getMessage());
                    }
                    break;
                case 2:
                    try {
                        testprediccio();
                    } catch (Exception E) {
                        System.out.println(E.getMessage());
                    }
                    break;
                case 3:
                    try {
                        testSlopeOne();
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
