package preprocessat;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;


public class DriverCSVparserRate {

    public static void options() {
        System.out.println("Choose an option with the necessary parameters needed:");
        System.out.println("\t 0) exit");
        System.out.println("\t 1) CSVparserRate(String path)");
        System.out.println("\t 2) getContent()");
        System.out.println("\t 3) getMapRate()");
        System.out.println("\t 4) setContent()");
        System.out.println("\t 5) setMapRate(Map<Integer, Map<Integer, Float>> mapRate)");
        System.out.println("\t 6) readLoadRate()");
        System.out.println("\t 7) String_to_Int(String s)");
        System.out.println("\t 8) String_to_Float(String s)");
        System.out.println("\t 9) LoadRate(List<List<String>> rate_content)");
        System.out.println("\t 10) getRow(int i) (rango: 0 - (tamaño filas del csv)-1 ");
    }

    public static void main(String[] args) {
        System.out.println("Driver CSVparser class:");

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("¡¡Important!! csv need to be of type Rating (UserID, ItemID, Rate)");
            System.out.println("Give the path of the csv document where is located:");
            String resp;
            resp = br.readLine();
            CSVparserRate csv = new CSVparserRate(resp);
            csv.LoadRate(csv.getContent());

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
                            csv = new CSVparserRate(resp);
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
                            Map<Integer, Map <Integer, Float>> mapRate = csv.getMapRate();
                            Map<Integer, Float> aux1;
                            Iterator<Integer> it1 = mapRate.keySet().iterator();
                            Iterator<Integer> itv;
                            while(it1.hasNext()){
                                int userid = it1.next();
                                aux1 = mapRate.get(userid);
                                itv = aux1.keySet().iterator();
                                System.out.println(userid + ":");
                                boolean p = true;
                                while(itv.hasNext()){
                                    int itemid = itv.next();
                                    float rate = aux1.get(itemid);
                                    if (p) {
                                        System.out.println(itemid + ", " + rate);
                                        p = false;
                                    }
                                    else System.out.println(", " + itemid + ", " + rate);
                                }
                            }
                            break;
                        case "4" :
                            csv.setContent(csv.getContent());
                            System.out.println("Done, attributed new content");
                            break;
                        case "5" :
                            csv.setMapRate(csv.getMapRate());
                            System.out.println("Done, attributed new mapRate");
                            break;
                        case "6" :
                            csv.readLoadRate();
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
                            System.out.println("Entre un número decimal de precisión simple: ");
                            BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
                            String res1 = br2.readLine();
                            float fl = csv.String_to_Float(res1);
                            System.out.println("Result: " + fl);
                            break;
                        case "9" :
                            csv.LoadRate(csv.getContent());
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
