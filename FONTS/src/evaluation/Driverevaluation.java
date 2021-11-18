package evaluation;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Driverevaluation {
    private static Scanner sc;

    public static Map<Integer, Float> leerknown(){
        Map<Integer, Float> known = new TreeMap<Integer, Float>();
        System.out.println("Numero de items que sabemos la valoracion del usuario por usuario :");
        int numit = sc.nextInt();
        for(int i = 0; i < numit; ++i){
            System.out.println("ID item" + (i+1) +" :");
            int ID = sc.nextInt();
            Float rating = sc.nextFloat();
            known.put(ID, rating);
        }
        return known;
    }

    public static Map<Integer, Float> leerrecommendation(){
        Map<Integer, Float> recommendation = new TreeMap<Integer, Float>();
        System.out.println("Numero de items que sabemos la valoracion del usuario por usuario :");
        int numit = sc.nextInt();
        for(int i = 0; i < numit; ++i){
            System.out.println("ID item" + (i+1) +" :");
            int ID = sc.nextInt();
            Float rating = sc.nextFloat();
            recommendation.put(ID, rating);
        }
        return recommendation;
    }

    public static void testDCG(){
        evaluation eval = new evaluation(leerknown(), leerrecommendation());
        System.out.println("El valor DCG de la recomendacio es: " + eval.DCG());
    }

    public static void main(String[] args){
        System.out.println("Driver collaborativeFiltering");
        boolean salir = false;
        sc = new Scanner(System.in);

        while (!salir) {
            System.out.println("Escoja una opción:");
            System.out.println("\t 1) testDCG()");
            System.out.println("\t 0) Salir");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    try {
                        testDCG();
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
