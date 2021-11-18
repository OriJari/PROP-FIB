import java.util.List;
import java.util.Map;
import java.util.Scanner;

import content.Content;
import preprocessat.*;

public class testmain {
    private static Scanner sc;

    public void makerecommendation(){
        System.out.println("ID del user que quiere la recomendacion:");
        sc.nextInt();
        CSVparserItem CSVItem = new CSVparserItem("FONTS/src/preprocessat/items.csv");
        CSVItem.readLoadItem();
        CSVItem.MapItemData(CSVItem.getContent());

        Map<Integer, List<Content>> map_rate_item = CSVItem.getMapRatedata();

        CSVparserRate CSVRate = new CSVparserRate("FONTS/src/preprocessat/ratings.db.csv");
        CSVRate.readLoadRate();
        CSVRate.LoadRate(CSVRate.getContent());
        Map<Integer, Map<Integer, Float>>
    }

    public static void main(String[] args) {
        System.out.println("Driver collaborativeFiltering");
        boolean salir = false;
        sc = new Scanner(System.in);

        while (!salir) {
            System.out.println("Escoja una opción:");
            System.out.println("\t 1) Hacer recomendacion");
            System.out.println("\t 0) Salir");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    try {
                        makerecommendation();
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
