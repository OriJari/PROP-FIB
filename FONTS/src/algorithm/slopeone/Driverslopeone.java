package algorithm.slopeone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Oriol Martí Jariod
 */


public class Driverslopeone {

    public void testSlopeOne(){
        System.out.print("Test SlopeOne");

    }

    public void testdesviacio_mitjana(){

    }

    public void testprediccio(){

    }

    public void printData() {
        for(Integer user : map_data.keySet()) {
            System.out.println(user);
            print(map_data.get(user));
        }
        for (int i=0; i<mAllItems.length; i++) {
            System.out.print("\n" + mAllItems[i] + ":");
            printMatrixes(mDiffMatrix.get(mAllItems[i]), mFreqMatrix.get(mAllItems[i]));
        }
    }

    private void printMatrixes(Map<ItemId,Float> ratings,
                               Map<ItemId,Integer> frequencies) {
        for (int j=0; j<mAllItems.length; j++) {
            System.out.format("%10.3f", ratings.get(mAllItems[j]));
            System.out.print(" ");
            System.out.format("%10d", frequencies.get(mAllItems[j]));
        }
        System.out.println();
    }

    public static void print(Map<ItemId,Float> user) {
        for (ItemId j : user.keySet()) {
            System.out.println(" "+ j+ " --> "+user.get(j).floatValue());
        }
    }

    public void main(String[] args) {
        System.out.print("Driver Slope One");
        slopeone so = null;
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
                System.out.println("\t 4) SlopeOne()");
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
