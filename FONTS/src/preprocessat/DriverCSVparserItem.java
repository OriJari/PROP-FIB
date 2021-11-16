package preprocessat;

import content.Content;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class DriverCSVparserItem {

    public static void options() {
        System.out.println("Choose an option with the necessary parameters needed:");
        System.out.println("\t 0) exit");
        System.out.println("\t 1) CSVparserItem(String path)");
        System.out.println("\t 2) getContent()");
        System.out.println("\t 3) getMapRatedata()");
        System.out.println("\t 4) setContent()");
        System.out.println("\t 5) setMapRatedata(Map<Integer, List<Content>> mapRatedata)");
        System.out.println("\t 6) readLoadItem()");
        System.out.println("\t 7) String_to_Int(String s)");
        System.out.println("\t 8) String_to_Double(String s)");
        System.out.println("\t 9) MapItemData(List<List<String>> rate_content)");
        System.out.println("\t 10) getRow(int i) (rango: 0 - (tamaño filas del csv)-1 ");
    }

    public static void main(String[] args) {
        System.out.println("Driver CSVparser class:");

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("¡¡Important!! csv need to be of type Item");
            System.out.println("Give the path of the csv document where is located:");
            String resp = br.readLine();
            CSVparserItem csv = new CSVparserItem(resp);

            boolean finish = false;
            while (!finish) {
                options();

                String line;
                String[] param;
                String op;

                line = br.readLine();

                param = line.split(" ");
                op = param[0];
                try {
                    switch (op) {
                        case "0" : finish = true;
                            break;
                        case "1" :
                            new CSVparser(resp);
                            System.out.println("CSV created");
                            break;
                        case "2" :
                            List<List<String>>  l = csv.getContent();
                            int f = 0;
                            for (List<String> aux : l){
                                System.out.println("Fila: " + f);
                                boolean p = true;
                                for(String s : aux){
                                    if (p){
                                        System.out.print(s);
                                        p = false;
                                    }
                                    System.out.println( " " + s);
                                }
                                ++f;
                            }
                            break;
                        case "3" :
                            Map<Integer, List<Content>> mapContent = csv.getMapRatedata();
                            List<Content> aux2;
                            Iterator<Integer> it2 = mapContent.keySet().iterator();
                            while(it2.hasNext()){
                                int k = it2.next();
                                aux2 = mapContent.get(k);
                                System.out.println(k + ":");
                                for(Content c : aux2){
                                    String t = c.getTag();
                                    Integer i = c.getTag_numi();
                                    Double d = c.getTag_numd();
                                    List<String> cat = c.getCategorics();
                                    System.out.println(t + ", " + i + ", " + d);
                                    boolean pri = true;
                                    for (String s : cat){
                                        if (pri) {
                                            System.out.print(s);
                                            pri = false;
                                        }
                                        else System.out.print(", " + s);
                                    }
                                }
                            }
                            break;
                        case "4" :
                            csv.setContent(csv.getContent());
                            System.out.println("Done, attributed new content");
                            break;
                        case "5" :
                            csv.setMapRatedata(csv.getMapRatedata());
                            System.out.println("Done, attributed new mapRatedata");
                            break;
                        case "6" :
                            csv.readLoadItem();
                            System.out.println("Done, csv parsered");
                            System.out.println("Execute case 3 to see results");
                            break;
                        case "7" :
                            System.out.println("Entre un número entero: ");
                            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                            String res = br1.readLine();
                            int i = csv.String_to_Int(res);
                            System.out.println("Result: " + i);
                            break;
                        case "8" :
                            System.out.println("Entre un número decimal de precisión doble: ");
                            BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
                            String res1 = br2.readLine();
                            double dl = csv.String_to_Double(res1);
                            System.out.println("Result: " + dl);
                            break;
                        case "9" :
                            csv.MapItemData(csv.getContent());
                            System.out.println("Datos Preprocesados, ejecute el caso 3 para ver los resultados");
                            break;
                        case "10" :
                            System.out.println("Inserte fila a conusltar: ");
                            BufferedReader br3 = new BufferedReader(new InputStreamReader(System.in));
                            String res2 = br3.readLine();
                            System.out.println(csv.getRow(parseInt(res2)));
                            break;

                        default : System.out.println("Choose another option");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
}
