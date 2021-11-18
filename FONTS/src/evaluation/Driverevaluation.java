package evaluation;

import java.util.Scanner;

public class Driverevaluation {
    private static Scanner sc;

    public void testDCG(){

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
