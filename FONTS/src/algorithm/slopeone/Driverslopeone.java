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

    }

    public static void testdesviacio_mitjana() {
        System.out.println("testdesviacio mitjana");
        System.out.println("10 casos");
        System.out.print("\t insertar fixero con datos -> ");
        slopeone.map_data = leeropinions();
        slopeone.desviacio_mitjana();



    }


    public static void testprediccio() {

    }

    private void print_map_completo(TreeMap<Integer,TreeMap<Integer,Float>> mappa){
        for(int j : mappa.keySet()){
            for(int i : mappa.values()){

            }
        }
    }

    private void print_pred(TreeMap<Integer, Float> user) {
        for (Integer j : user.keySet()) {
            System.out.println(" " + j + " --> " + user.get(j));
        }
    }

    public void main(String[] args) {
        System.out.print("Driver Slope One");
        slopeone so = null;
        sc = new Scanner(System.in);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String linea;
        boolean salir = false;
        linea = "Primero";
        while (linea != null && !salir) {
            try {
                System.out.println("Escoja una opción:");
                System.out.println("\t 1) desviacio_mitjana()");
                System.out.println("\t\t input: 1");
                System.out.println("\t 2) prediccio()");
                System.out.println("\t\t input: 2 ");
                System.out.println("\t 3) prediccio(Map<Integer,Float>)");
                System.out.println("\t\t input: 3 ");
                System.out.println("\t 4) SlopeOne(Map<Integer,Map<Integer,Float>> data)");
                System.out.println("\t\t 4 ");
                System.out.println("\t 0) Salir");

                linea = buffer.readLine();
                String[] line = linea.split("");

                switch (line[0]) {
                    case "1":
                        try {
                            testdesviacio_mitjana();
                            System.out.println();
                        } catch (Exception E) {
                            System.out.println(E.getMessage());
                        }
                    case "2":
                        try {
                            int n = Integer.parseInt(line[1]);
                            G = new Grafo(n);
                        } catch (Exception E) {
                            System.out.println(E.getMessage());
                        }
                        break;
                    case "3":
                        try {
                            G.ponerArista(new Arista(Integer.parseInt(line[1]), Integer.parseInt(line[2]), Double.parseDouble(line[3])));
                        } catch (Exception E) {
                            System.out.println(E.getMessage());
                        }
                        break;
                    case "0":
                        salir = true;
                        break;
                    default:
                        System.out.println(linea);
                        break;
                }


            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
