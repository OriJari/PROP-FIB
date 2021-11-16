package preprocessat;

import content.Content;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DriverCSVparser {

    public static void options() {
        System.out.println("Choose an option with the necessary parameters needed:");
        System.out.println("\t 1) CSVparser(String path)");
        System.out.println("\t 2) getMapItem()");
        System.out.println("\t 3) getMapRate()");
        System.out.println("\t 4) getMapRatedata()");
        System.out.println("\t 5) setMapItem(Map<Integer, List<String>> mapItem)");
        System.out.println("\t 6) setMapRate(Map<Integer, Map<Integer, Float>> mapRate)");
        System.out.println("\t 7) setMapRatedata(Map<Integer, List<Content>> mapRatedata)");
        System.out.println("\t 8) readLoadItem()");
        System.out.println("\t 9) readLoadRate()");
        System.out.println("\t 10) String_to_Int(String s)");
        System.out.println("\t 11) String_to_Float(String s)");
        System.out.println("\t 12) String_to_Double(String s)");
        System.out.println("\t 13) LoadRate(List<List<String>> rate_content)");
        System.out.println("\t 14) LoadItem(List<List<String>> rate_content)");
        System.out.println("\t 15) MapItemData(List<List<String>> rate_content)");
        System.out.println("\t 16) getRow(int i)");
    }

    public static void main(String[] args) {
        System.out.println("Driver CSVparser class:");

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Give the path of the csv document where is located:");
            String resp;
            resp = br.readLine();
            CSVparser csv = new CSVparser(resp);

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
                            break;
                        case "2" :
                            Map<Integer, List<String>> mapItem = csv.getMapItem();
                            Iterator<Integer> it = mapItem.keySet().iterator();
                            List<String> aux;
                            while(it.hasNext()){
                                int i = it.next();
                                aux = mapItem.get(i);
                                System.out.println(i + ":");
                                boolean primer = true;
                                for (String s : aux){
                                    if (primer){
                                        System.out.print(s);
                                        primer = false;
                                    }
                                    else System.out.print(", " + s);
                                }
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
                        case "5" :
                            B.movements();
                            Vector<Pair> movesW = B.getWhiteMoves();
                            for (int i = 0; i < movesW.size(); i++) {
                                System.out.println(movesW.get(i).x + " " + movesW.get(i).y);
                            }
                            break;
                        case "6" :
                            B.movements();
                            Vector<Pair> movesB = B.getBlackMoves();
                            for (int i = 0; i < movesB.size(); i++) {
                                System.out.println(movesB.get(i).x + " " + movesB.get(i).y);
                            }
                            break;
                        case "7" :
                            Cell[][] cells = B.getGameBoardCells();
                            for (int i = 0; i < cells[0].length; i++) {
                                for (int j = 0; j < cells[0].length; j++) {
                                    if (cells[i][j] == Cell.border) {
                                        if (i == 0 || i == cells[0].length-1) System.out.print("-+");
                                        else System.out.print("| ");
                                    }
                                    else if (cells[i][j] == Cell.empty) System.out.print("· ");
                                    else if (cells[i][j] == Cell.white) System.out.print("W ");
                                    else System.out.print("B ");
                                }
                                System.out.println();
                            }
                            break;
                        case "8" :
                            B.changeTurn();
                            break;
                        case "9" :
                            B.setBoardCell(Integer.parseInt(param[1]), Integer.parseInt(param[2]));
                            break;
                        case "10" :
                            Pair p = B.getGameScore();
                            System.out.println("Score Black: " + p.x);
                            System.out.println("Score White: " + p.x);
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
