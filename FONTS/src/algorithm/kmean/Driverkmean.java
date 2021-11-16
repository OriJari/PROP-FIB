package algorithm.kmean;

import java.util.Scanner;
import java.util.TreeMap;

public class Driverkmean {
    public void testkmean(){

    }

    public void testcosineSquaredSimil(){

    }

    public void testequalClusters(){

    }

    public void testk_means(){

    }

    private TreeMap<Integer, TreeMap<Integer, Float>> leeropinions(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Numero de users: ");
        int Nusers = sc.nextInt();
        TreeMap<Integer, TreeMap<Integer, Float>> opinions = new TreeMap<Integer, TreeMap<Integer, Float>>();
        for(int i = 0; i < Nusers; ++i){
            System.out.println("ID de user num " + i+1 + " : ");
            int userID = sc.nextInt();
            opinions.put(userID, new TreeMap<Integer, Float>());
            System.out.println("Numero de items valorados por usuario " + userID + " : ");
            int numItems = sc.nextInt();
            for(int j = 0; j < numItems; ++j){
                System.out.println("ID item numero " + j+1 + " : ");
                int itemID = sc.nextInt();
                System.out.println("Valoracion de l'item " + j+1 + " : ");
                float itemVal = sc.nextFloat();
                opinions.get(userID).put(itemID, itemVal);
            }
        }
        return opinions;
    }

    public void main(String[] args){
        System.out.println("Driver Kmean");
        boolean salir = false;
        while (!salir) {
            try {
                System.out.println("Escoja una opción:");
                System.out.println("\t 1) testkmean()");
                System.out.println("\t 2) testcosineSquaredSimil");
                System.out.println("\t 3) testequalClusters()");
                System.out.println("\t 4) testk_means()");
                System.out.println("\t 0) Salir");

                Scanner sc = new Scanner(System.in);
                int option = sc.nextInt();
                switch (option) {
                    case 1:
                        try {

                            kmean Kmean = new kmean(leeropinions());
                            System.out.println("Resultado: " + NN.obtenerPrimeraSolucion(G).toString());
                        } catch (Exception E) {
                            System.out.println(E.getMessage());
                        }
                    case 2:
                        try {
                            int n = Integer.parseInt(line[1]);
                            G = new Grafo(n);
                        } catch (Exception E) {
                            System.out.println(E.getMessage());
                        }
                        break;
                    case 3:
                        try {
                            G.ponerArista(new Arista(Integer.parseInt(line[1]), Integer.parseInt(line[2]), Double.parseDouble(line[3])));
                        } catch (Exception E) {
                            System.out.println(E.getMessage());
                        }
                        break;
                    case 4:
                        salir = true;
                        break;
                    default:
                        System.out.println(linea);
                        break;
                }
            } catch (IOException | IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }
}
