package drivers;

import algorithm.slopeone.*;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Oriol Martí Jariod
 */


public class Driverslopeone {
    private static Scanner sc;



    public static Map<Integer, Map<Integer, Float>> leeropinions(){
        System.out.println("Numero de users: ");
        int Nusers = sc.nextInt();
        Map<Integer, Map<Integer, Float>> opinions = new TreeMap<Integer, Map<Integer, Float>>();
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

    public static Map<Integer,Float> leerusuariopredi(){
        System.out.println("Items valorado por el usuario que queremos predecir");
        int Nitem = sc.nextInt();
        Map<Integer,Float> user = new TreeMap<Integer,Float>();
        for(int i = 0; i < Nitem; i++){
            System.out.println("ID item numero " + (i+1) + " : ");
            int itemID = sc.nextInt();
            System.out.println("Valoracion de l'item " + (i+1) + " : ");
            float itemVal = sc.nextFloat();
            user.put(itemID,itemVal);
        }
        return user;
    }

    public static void testSlopeOne() {
        System.out.println("Test SlopeOne");
        SlopeOne.map_data = leeropinions();
        Map<Integer,Float> user = leerusuariopredi();
        System.out.println("esta base de datos");
        SlopeOne.map_pred = SlopeOne.slopeone( SlopeOne.map_data,user);
        System.out.println("\n Resultado:");
        print_map_completo(SlopeOne.map_data);
        System.out.println("esta desviacion");
        print_map_completo(SlopeOne.map_des);
        System.out.println("\n\t esta prediccio");
        print_pred(SlopeOne.map_pred);
        System.out.println("Terminado");

    }

    public static void testdesviacio_mitjana() {
        System.out.println("testdesviacio mitjana");
        SlopeOne.map_data = leeropinions();
        System.out.println("esta base de datos");
        print_map_completo(SlopeOne.map_data);
        SlopeOne.desviacio_mitjana();
        System.out.println("\n Resultado:");
        System.out.println("\n\t esta desviacion");
        print_map_completo(SlopeOne.map_des);
        System.out.println("\n\t esta fequencia");
        print_map_freq(SlopeOne.map_freq);
        System.out.println("\n test terminado");

    }


    public static void testprediccio() {
        System.out.println("Test preddicio");
        SlopeOne.map_data = leeropinions();
        System.out.println("esta desviacion");
        SlopeOne.desviacio_mitjana();
        print_map_completo(SlopeOne.map_des);
        System.out.println("\n\t esta fequencia");
        print_map_freq(SlopeOne.map_freq);
        Map<Integer,Float> user = leerusuariopredi();
        SlopeOne.prediccio(user);

        System.out.println("\n Resultado:");
        System.out.println("\n\t esta prediccio");
        print_pred(SlopeOne.map_pred);
        System.out.println("\n test terminado");
    }

    private static void print_map_freq(Map<Integer, Map<Integer, Integer>> mappa){
        for(int j : mappa.keySet()){
            System.out.println("\t " + j + "-->" + mappa.get(j));

        }
    }

    private static void print_map_completo(Map<Integer, Map<Integer, Float>> mappa){
        for(int j : mappa.keySet()){
            System.out.println("\t " + j + "-->" + mappa.get(j));

        }
    }

    private static void print_pred(Map<Integer, Float> user) {
        for (Integer j : user.keySet()) {
            System.out.println("\t " + j + " --> " + user.get(j));
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
