package algorithm.collaborativeFiltering;

import java.util.Scanner;

public class DrivercollaborativeFiltering {
    private static Scanner sc;

    public static void main(String[] args){
        System.out.println("Driver Kmean");
        boolean salir = false;
        sc = new Scanner(System.in);

        while (!salir) {
            System.out.println("Escoja una opción:");
            System.out.println("\t 1) testcosineSquaredSimil()");
            System.out.println("\t 2) testequalClusters()");
            System.out.println("\t 3) testk_means()");
            System.out.println("\t 0) Salir");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    try {

                    } catch (Exception E) {
                        System.out.println(E.getMessage());
                    }
                    break;
                case 2:
                    try {

                    } catch (Exception E) {
                        System.out.println(E.getMessage());
                    }
                    break;
                case 3:
                    try {

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
