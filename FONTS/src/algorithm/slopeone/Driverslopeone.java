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

    public static void main(String[] args) {
        System.out.print("Driver Slope One");
        slopeone so = null;
        slopeone.map_data = null;
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String linea;
        boolean salir = false;
        linea = "Primero";
        while (linea != null && !salir) {
            try {
                System.out.println("Escoja una opción:");
                System.out.println("\t 1) preprocessat()`");
                System.out.println("\t\t input: 1");
                System.out.println("\t 2) desviacio_mitjana()");
                System.out.println("\t\t input: 2 ");
                System.out.println("\t 3) prediccio()");
                System.out.println("\t\t input: 3 ");
                System.out.println("\t 4) prediccio(Map<Integer,Float>)");
                System.out.println("\t\t 4 ");
                System.out.println("\t 0) Salir");

                linea = buffer.readLine();
                String[] line = linea.split("");

                switch (line[0]) {
                    case "1":
                        try {

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
