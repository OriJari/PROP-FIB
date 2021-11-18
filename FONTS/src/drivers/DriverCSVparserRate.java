package drivers;

import preprocessat.CSVparserRate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


public class DriverCSVparserRate {

    public static void options() {
        System.out.println("Choose an option with the necessary parameters needed:");
        System.out.println("\t 0) exit");
        System.out.println("\t 1) CSVparserRate(String path)");
        System.out.println("\t 2) getNumRows()");
        System.out.println("\t 3) getNumCols()");
        System.out.println("\t 4) getHeader()");
        System.out.println("\t 5) getPath()");
        System.out.println("\t 6) getContent()");
        System.out.println("\t 7) getMapRate()");
        System.out.println("\t 8) setNumRows(Integer numRows)");
        System.out.println("\t 9) setNumCols(Integer numCols)");
        System.out.println("\t 10) setHeader(List<String> header)");
        System.out.println("\t 11) setPath(String path)");
        System.out.println("\t 12) setContent(List<List<String>> content)");
        System.out.println("\t 13) setMapRate(Map<Integer, Map<Integer, Float>> mapRate)");
        System.out.println("\t 14) readLoadRate()");
        System.out.println("\t 15) String_to_Int(String s)");
        System.out.println("\t 16) String_to_Float(String s)");
        System.out.println("\t 17) LoadRate(List<List<String>> rate_content)");
        System.out.println("\t 18) getRow(int i) (rango: 0 - (tamaño filas del csv)-1) ");
    }

    public static void main(String[] args) {
        System.out.println("Driver CSVparserRate class:");
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("¡¡Important!! csv need to be of type Rating (UserID, ItemID, Rate)");
            System.out.println("Give the path of the csv document where is located:");
            String resp = br.readLine();
            CSVparserRate csv = new CSVparserRate(resp);
            csv.readLoadRate();

            options();
            boolean finish = false;
            while (!finish) {

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
                            System.out.println(csv.getNumRows());
                            break;
                        case "3" :
                            System.out.println(csv.getNumCols());
                            break;
                        case "4" :
                            List<String> aux = csv.getHeader();
                            for (int i = 0; i < aux.size(); ++i){
                                System.out.println(aux.get(i));
                            }
                            break;
                        case "5" :
                            System.out.println(csv.getPath());
                            break;

                        case "6" :
                            List<List<String>>  l = csv.getContent();
                            int f = 0;
                            for (List<String> aux1 : l){
                                System.out.println("Fila: " + f);
                                System.out.println(aux1);
                                ++f;
                            }
                            break;
                        case "7" :
                            csv.LoadRate(csv.getContent());
                            Map<Integer, Map<Integer, Float>> mapRate = csv.getMapRate();
                            Map<Integer, Float> aux1;
                            for (int user : mapRate.keySet()) {
                                System.out.println("User: " + user);
                                aux1 = mapRate.get(user);
                                System.out.println("Items and rates: ");
                                System.out.println(Collections.singletonList(aux1));
                            }
                            break;
                        case "8" :
                            csv.setNumRows(parseInt(param[1]));
                            System.out.println("Number of rows changed");
                            break;
                        case "9" :
                            csv.setNumCols(parseInt(param[1]));
                            System.out.println("Number of columns changed");
                            break;
                        case "10" :
                            System.out.println("Define num of elements to insert and the elements: ");
                            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                            String line1;
                            String[] param1;
                            line1 = br1.readLine();
                            param1 = line1.split(" ");
                            int limt1 = parseInt(param1[0]);
                            List<String> aux2 = new ArrayList<>();
                            for (int j = 1; j <= limt1; ++j){
                                aux2.add(param1[j]);
                            }
                            csv.setHeader(aux2);
                            System.out.println("Header changed");
                            break;
                        case "11" :
                            System.out.println("!!you will change the document!! ");
                            System.out.println("Introduce path : ");
                            BufferedReader br4 = new BufferedReader(new InputStreamReader(System.in));
                            String resp1 = br4.readLine();
                            csv.setPath(resp1);
                            //fis = new FileInputStream(resp1);
                            csv = new CSVparserRate(resp1);
                            csv.readLoadRate();
                            System.out.println("Path changed");
                            break;
                        case "12" :
                            csv.setContent(csv.getContent());
                            System.out.println("Done, attributed new content");
                            break;
                        case "13" :
                            csv.setMapRate(csv.getMapRate());
                            System.out.println("Done, attributed new mapRate");
                            break;
                        case "14" :
                            csv.readLoadRate();
                            System.out.println("Done, csv parsered");
                            System.out.println("Execute case 6 to see results");
                            break;
                        case "15" :
                            System.out.println(csv.String_to_Int(param[1]));
                            break;
                        case "16" :
                            System.out.println(csv.String_to_Float(param[1]));
                            break;
                        case "17" :
                            csv.LoadRate(csv.getContent());
                            System.out.println("Datos Preprocesados, ejecute el caso 7 para ver los resultados");
                            break;
                        case "18" :
                            System.out.println("Inserte numero fila a conusltar: ");
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
