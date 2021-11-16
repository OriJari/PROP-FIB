package algorithm.slopeone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Oriol Martí Jariod
 */


public class Driverslopeone {

    private load_data(){
        Map<Integer,Map<Integer,Float>> data = new TreeMap<Integer,Map<Integer,Float>>();
        // items
        int item1 = new ItemId("0001");
        int item2 = new ItemId("0001");
        int item3 = new ItemId("0001");
        int item4 = new ItemId("0001");
        int item5 = new ItemId("0001");
        int item6 = new ItemId("0001");
        int item7 = new ItemId("0001");
        int item8 = new ItemId("0001");
        int item9 = new ItemId("0001");
        int item10 = new ItemId("0001");

        mAllItems = new ItemId[]{item1, item2, item3, item4, item5};

        //I'm going to fill it in
        HashMap<ItemId,Float> user1 = new HashMap<ItemId,Float>();
        HashMap<ItemId,Float> user2 = new HashMap<ItemId,Float>();
        HashMap<ItemId,Float> user3 = new HashMap<ItemId,Float>();
        HashMap<ItemId,Float> user4 = new HashMap<ItemId,Float>();
    }
}

    public void testSlopeOne(){
        System.out.print("Test SlopeOne");

    }

    public void testdesviacio_mitjana(){
        System.out.print("testdesviacio mitjana");
        System.out.print("10 casos");
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

    private void printMatrixes(Map<Integer,Float> ratings,Map<Integer,Integer> frequencies) {
        for (int j=0; j<mAllItems.length; j++) {
            System.out.format("%10.3f", ratings.get(mAllItems[j]));
            System.out.print(" ");
            System.out.format("%10d", frequencies.get(mAllItems[j]));
        }
        System.out.println();
    }

    private void print_pred(Map<Integer,Float> user) {
        for (Integer j : user.keySet()) {
            System.out.println(" "+ j+ " --> "+user.get(j));
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
